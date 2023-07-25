package com.wrxprts.ims.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Validators implements ConstraintValidator<AgeConstraint, Date>
{
	
	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context)
	{
		try
		{
			if (value == null)
				return true;
			LocalDate birthdate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate now = LocalDate.now();
			return Period.between(birthdate, now).getYears() >= 18;
		}
		
		catch (Exception e)
		{
			// Log the exception for debugging purposes
			e.printStackTrace();
			// Return true if the value is not a valid Date object
			return true;
		}
	}
	
}
