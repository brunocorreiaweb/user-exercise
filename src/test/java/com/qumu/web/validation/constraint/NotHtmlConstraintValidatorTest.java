package com.qumu.web.validation.constraint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

@RunWith(MockitoJUnitRunner.class)
public class NotHtmlConstraintValidatorTest {

    private NotHtmlConstraintValidator notHtmlConstraintValidator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setup() {
        this.notHtmlConstraintValidator = new NotHtmlConstraintValidator();
    }


    @Test
    public void isValidWithHTML() throws Exception {
        Assert.assertEquals(false, notHtmlConstraintValidator.isValid( "<script>alert('');</script>",
                constraintValidatorContext) );
    }

    @Test
    public void isValidWithoutHTML() throws Exception {
        Assert.assertEquals(true, notHtmlConstraintValidator.isValid("Test",
                constraintValidatorContext) );
    }

    @Test
    public void isValidWithNull() throws Exception {
        Assert.assertEquals(true, notHtmlConstraintValidator.isValid(null,
                constraintValidatorContext));
    }

}