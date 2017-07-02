package com.qumu.web.validation;

import com.qumu.web.validation.constraint.NotHtmlConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotHtmlConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotHtml {


    String message() default "{NotHtml}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
