package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdentifierValidator implements ConstraintValidator<ExistsIdentifier, Object> {

    private String domainAttribute;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsIdentifier constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.clazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Query query = entityManager.createQuery(String.format("SELECT 1 FROM %s WHERE %s=:field", clazz.getName(),
                domainAttribute)).setParameter("field", value);
        List<?> resultList = query.getResultList();

        return !resultList.isEmpty();
    }
}
