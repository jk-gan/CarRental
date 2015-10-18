/**
 * 
 */
package com.carrental.view;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class is used as the utility class for User Interface capabilities
 * @author JKGan
 * @version 1.0
 * @since 1.0
 *
 */
public class UserInterfaceUtility {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static Calendar parseCalendar(String input) throws Exception {
		Date date = sdf.parse(input);
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		return calendar;
	}
}
