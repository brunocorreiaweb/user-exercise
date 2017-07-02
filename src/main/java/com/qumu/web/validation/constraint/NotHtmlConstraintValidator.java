package com.qumu.web.validation.constraint;

import com.qumu.web.validation.NotHtml;
import org.springframework.web.util.HtmlUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * Important validator to prevent XSS attacks
 *
 */
public class NotHtmlConstraintValidator implements ConstraintValidator<NotHtml, String> {

    @Override
    public void initialize(NotHtml value) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        return value == null || value.equals(HtmlUtils.htmlEscape(value));

    }

}