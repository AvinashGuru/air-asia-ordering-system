package com.airasia.ordering.mapper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.extern.log4j.Log4j2;

/**
 * @author Avinash Gurumurthy
 * 
 * Class to perform date related operations
 *
 */
@Log4j2
public class DateUtils {

	
	/**
	 * This method checks the date passed is in proper required format,  if not returns false
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean isDateStringValid(String date, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
			LocalDate.parse(date, formatter);
			return true;
		}catch (Exception e) {
			log.info("Invalid date format passed ",e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * This method checks the date passed is in proper required format,  if not returns false
	 * @param date
	 * @param format
	 * @return
	 */
	public static LocalDate getDateFromString(String date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
		LocalDate dateTime = LocalDate.parse(date, formatter);
		return dateTime;
	}
}
