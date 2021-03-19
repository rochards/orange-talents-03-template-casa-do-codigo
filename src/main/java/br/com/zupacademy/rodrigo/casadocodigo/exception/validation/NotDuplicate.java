package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotDuplicateValidator.class)
public @interface NotDuplicate {

    String message() default "custom.validation.NotDuplicate.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();

    String fieldName();

    @Documented
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        NotDuplicate[] value();
    }
}
