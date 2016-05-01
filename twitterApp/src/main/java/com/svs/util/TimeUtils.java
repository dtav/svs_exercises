package com.svs.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class TimeUtils {
	
	public static Date getFullDateFromDay(String day) throws ParseException {
		int[] todayMonthYear = TimeUtils.getTodaysMonthAndYear();
		String fullString = day+"/"+todayMonthYear[0]+"/"+todayMonthYear[1];
		Date d = DateUtils.parseDateStrictly(fullString, "dd/MM/yyyy");
		return d;
	}
	
	public static int[] getTodaysMonthAndYear(){
		int[] monthYear = new int[2];
		Date d = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		monthYear[0] = month+1; //months start with 0 ffs
		monthYear[1] = year;
		
		return monthYear;
	}


}
