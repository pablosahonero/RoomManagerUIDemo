package org.roommanager.framework.utilities.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Generator {
	private static String format = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'";
	private static int differenceOfHours = 4;
	private static int defaultMeetingDuration = 30;
	private static DateFormat dateFormat = new SimpleDateFormat(format);
	
	public static String getStartTime(){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR, differenceOfHours);
		return dateFormat.format(calendar.getTime());
	}
	public static String getEndTime(){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR, differenceOfHours);
		calendar.add(Calendar.MINUTE,defaultMeetingDuration);
		return dateFormat.format(calendar.getTime());
	}
	public static String getStartTimeAfterCurrentTime(int afterCurrentTime){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR, (differenceOfHours + afterCurrentTime));
		return dateFormat.format(calendar.getTime());
	}
	public static String getEndTimeLateAfterCurrentTime(int afterCurrentTime){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR, (differenceOfHours + afterCurrentTime));
		calendar.add(Calendar.MINUTE,defaultMeetingDuration);
		return dateFormat.format(calendar.getTime());
	}
	
}
