package br.com.zupacademy.rodrigo.casadocodigo.exception.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/** Retorna verdadeiro para valores nulos. */
@Documented
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsIdentifierValidator.class)
public @interface ExistsIdentifier {

    String message() default "custom.validation.not.ExistsIdentifier.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();

    String fieldName();

    @Documented
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        ExistsIdentifier[] value();
    }
}
