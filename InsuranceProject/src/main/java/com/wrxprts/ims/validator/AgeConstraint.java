package com.wrxprts.ims.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Validators.class)
public @interface AgeConstraint
{
	String message() default "Customer must be older than 18";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
