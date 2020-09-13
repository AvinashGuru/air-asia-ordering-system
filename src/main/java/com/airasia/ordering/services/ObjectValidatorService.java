package com.airasia.ordering.services;

import com.airasia.ordering.annotations.MandatoryProp;
import com.airasia.ordering.dto.ValidatedObject;
import com.airasia.ordering.exceptions.BusinessValidationException;

/**
 * @author Avinash Gurumurthy
 * 
 * This method is used for validating the passed object .
 * The object passed must have at least 1 field which needs to be validated
 * The field to be validated should have {@link MandatoryProp} annotation
 *
 */
public interface ObjectValidatorService<validatedObj extends ValidatedObject> {

	/**
	 * This method validates all mandatory fields and date formats
	 * @param obj
	 * @throws BusinessValidationException
	 * @throws Exception
	 */
	public void validate(validatedObj obj) throws BusinessValidationException, Exception;
}
