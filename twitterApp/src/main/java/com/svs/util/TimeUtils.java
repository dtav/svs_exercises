package com.svs.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


public class TimeUtils {
	
	public static Date getFullDateFromDay(String partDate) {
		int[] todayMonthYear = TimeUtils.getTodaysMonthAndYear();
		String fullString = null;
		String[] subStr = partDate.split(",");
		int realLength = 0;
		if (subStr[subStr.length-1].trim().length() > 0){
			realLength = subStr.length;
		} else {
			realLength = subStr.length -1;
		}
		if (realLength == 3) {
			fullString = subStr[0] + "/" + subStr[1] + "/" + subStr[2];
		}
		if (realLength == 2) {
			fullString = subStr[0] + "/" + subStr[1] + "/" + todayMonthYear[1];
		}
		if (realLength == 1) {
			fullString = subStr[0] + "/" + todayMonthYear[0] + "/" + todayMonthYear[1];
		}

		Date d = null;
		try {
			d = DateUtils.parseDateStrictly(fullString, "dd/MM/yyyy");

		} catch (ParseException e) {
			System.out.println("Error in arguments parsing!");
		} catch (IllegalArgumentException e1) {
			System.out.println("Error in arguments length!");

		}
		return d;

	}
	
	public static int[] getTodaysMonthAndYear(){
		int[] monthYear = new int[2];
		Date d = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		monthYear[0] = month+1; //months start from 0 ffs
		monthYear[1] = year;
		
		return monthYear;
	}


}
