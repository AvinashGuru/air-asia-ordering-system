package com.airasia.ordering.services;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.airasia.ordering.annotations.MandatoryProp;
import com.airasia.ordering.dto.ValidatedObject;
import com.airasia.ordering.exceptions.BusinessValidationException;
import com.airasia.ordering.mapper.utils.Constants;
import com.airasia.ordering.mapper.utils.DateUtils;

import lombok.extern.log4j.Log4j2;

/**
 * @author Avinash Gurumurthy
 *
 */
@Service
@Log4j2
public class ObjectValidatorServiceImpl<validatedObj extends ValidatedObject> implements ObjectValidatorService<validatedObj> {
	
	/**
	 * This method validates all mandatory fields and date formats
	 * @param obj
	 * @throws BusinessValidationException
	 * @throws Exception
	 */
	@Override
	public void validate(validatedObj obj) throws BusinessValidationException, Exception {
		
		if(obj == null) {
			throw new BusinessValidationException(Constants.EMPTY_REQ_ERROR);
		}

		List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());

		for(Field fld : fields) {
			List<Annotation> fldAnnotations = Arrays.asList(fld.getDeclaredAnnotations());
			boolean isMandatoryPropsAnnotated = false;
			MandatoryProp mandatoryPropAnnotation = null;
			for(Annotation fldAnnotation : fldAnnotations) {
				isMandatoryPropsAnnotated = MandatoryProp.class.isAssignableFrom(fldAnnotation.annotationType());
				mandatoryPropAnnotation = (MandatoryProp) fldAnnotation;
			}
			
			if(isMandatoryPropsAnnotated && mandatoryPropAnnotation != null) {
				validateField(obj, fld, mandatoryPropAnnotation);
			}
		}
		log.info("Object validator, successfully validated the request");
	}

	/**
	 * This method validates mandatory fields and date formats
	 * @param obj
	 * @param fld
	 * @param fldAnnotation
	 * @throws Exception 
	 */
	private void validateField(validatedObj obj, Field fld, MandatoryProp mandatoryPropAnnotation) throws BusinessValidationException, Exception {

		String type = mandatoryPropAnnotation.type();
		String message = mandatoryPropAnnotation.message();

		Object value = invokeGetterMethod(fld, obj);
		if("String".equalsIgnoreCase(type)) {
			if(value == null) {
				throw new BusinessValidationException(message);
			}else {
				if(StringUtils.isEmpty(String.valueOf(value))){
					throw new BusinessValidationException(message);
				}
			}
		}
		
		if("Integer".equalsIgnoreCase(type)) {
			if(value == null) {
				throw new BusinessValidationException(message);
			}
		}
		
		if("Date".equalsIgnoreCase(type)) {
			String format = mandatoryPropAnnotation.format();
			if(value == null) {
				throw new BusinessValidationException(message);
			}else {
				if(StringUtils.isEmpty(String.valueOf(value))){
					throw new BusinessValidationException(message);
				}else {
					if(!isDateStringValid(String.valueOf(value), format)) {
						throw new BusinessValidationException(" Invalida date format passed : "+ String.valueOf(value) +". Please provide date in proper format : "+format);
					}
				}
			}
		}
		
	}

	/**
	 * This method is used to invoke getter object for a field
	 * @param field
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Object invokeGetterMethod(Field field, Object obj) throws Exception {
		Class<?> classObj = obj.getClass();
		for(Method method : classObj.getMethods()) {
			if(method.getName().startsWith("get") && method.getName().length() == (field.getName().length()+3)) {
				if(method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
					return method.invoke(obj);
				}
			}
		}
		return null;
	}
	
	/**
	 * This method checks the date passed is in proper required format,  if not returns false
	 * @param date
	 * @param format
	 * @return
	 */
	private boolean isDateStringValid(String date, String format) {
		return DateUtils.isDateStringValid(date, format);
	}
}
