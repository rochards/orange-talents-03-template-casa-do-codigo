package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotDuplicateValidator implements ConstraintValidator<NotDuplicate, Object> {

    private String domainAttribute;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(NotDuplicate constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.clazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery(String.format("SELECT 1 FROM %s WHERE %s=:field", clazz.getName(), domainAttribute));
        query.setParameter("field", value);
        List<?> resultList = query.getResultList();
        Assert.state(resultList.size() <= 1, String.format("Foi encontrado mais de um %s com o atributo %s",
                clazz.getName(), domainAttribute));

        return resultList.isEmpty();
    }
}
