package com.airasia.ordering.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MandatoryProp {
	String message();
	String type() default "String";
	String format() default "YYYY-MM-DD";
}
