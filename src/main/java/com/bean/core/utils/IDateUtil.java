package com.bean.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.expression.ParseException;

public class IDateUtil extends DateUtils{
	private static String[] dateFormat = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yy-MM-dd","HH:mm:ss", "MM-dd"};

	public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date)
	{
		if (date == null)
			return null;

		return new Timestamp(date.getTimeInMillis());
	}

	public static Calendar convSqlTimestampToUtilCalendar(Timestamp date)
	{
		if (date == null)
			return null;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		return gc;
	}

	public static Calendar parseDate(String dateStr)
	{
		if ((dateStr == null) || (dateStr.trim().length() == 0))
			return null;

		Date result = parseDate(dateStr, 0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(result);

		return cal;
	}

	public static String toDateTimeStr(Calendar date)
	{
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
	}

	public static String toDateTimeStr(int format, Calendar date)
	{
		if (date == null)
			return null;

		return new SimpleDateFormat(dateFormat[format]).format(date.getTime());
	}

	public static String toDateStr(Calendar date)
	{
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
	}

	public static String toDateStrByFormatIndex(Calendar date, int formatIndex) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[formatIndex]).format(date.getTime());
	}

	public static String toStrByIndex(Date date, int formatIndex) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[formatIndex]).format(date.getTime());
	}

	public static int calendarMinus(Calendar d1, Calendar d2) {
		if ((d1 == null) || (d2 == null)) {
			return 0;
		}

		d1.set(11, 0);
		d1.set(12, 0);
		d1.set(13, 0);

		d2.set(11, 0);
		d2.set(12, 0);
		d2.set(13, 0);

		long t1 = d1.getTimeInMillis();
		long t2 = d2.getTimeInMillis();

		long daylong = 86400000L;
		t1 -= t1 % daylong;
		t2 -= t2 % daylong;

		long t = t1 - t2;
		int value = (int)(t / daylong);


		return value;
	}

	public static long calendarminus(Calendar d1, Calendar d2)
	{
		if ((d1 == null) || (d2 == null))
			return 0L;

		return ((d1.getTimeInMillis() - d2.getTimeInMillis()) / 86400000L);
	}

	public static Date parseDate(String dateStr, int index)
	{
		DateFormat df = null;
		try {
			df = new SimpleDateFormat(dateFormat[index]);

			try {
				return df.parse(dateStr);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
		} catch (ParseException pe) {
			return parseDate(dateStr, index + 1);
		} catch (ArrayIndexOutOfBoundsException aioe) {
			return null;
		}
	}

	public static Date StringToDate(String dateStr)
	{
		if ((dateStr == null) || (dateStr.trim().length() == 0))
			return null;

		return parseDate(dateStr, 3);
	}

	public static String dateToString(Date date, int index)
	{
		if (date == null)
			return null;

		return new SimpleDateFormat(dateFormat[index]).format(date);
	}
	/**
	 * 获取当前时间
	 * **/
	 public static String getCurrentTimeStr(){
		Date date = new Date();
		return new SimpleDateFormat(dateFormat[0]).format(date);
	}
	/**
	 * 获取当前时间
	 * **/
	 public static Date getCurrentTimeDate(){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = null;
		 try {
			 date = sdf.parse(getCurrentTimeStr());
		 } catch (java.text.ParseException e) {
			 e.printStackTrace();
		 }
		 return date;
	 }
	 public static String dateToString(Date date)
	 {
		 if (date == null)
			 return null;

		 return new SimpleDateFormat(dateFormat[1]).format(date);
	 }
	public static String dateTimeToString(Date date)
	{
		if (date == null)
			return null;

		return new SimpleDateFormat(dateFormat[0]).format(date);
	}
	 public static Timestamp convUtilDateToSqlTimestamp(Date date)
	 {
		 if (date == null)
			 return null;

		 return new Timestamp(date.getTime()); }

	 public static Calendar convUtilDateToUtilCalendar(Date date) {
		 if (date == null)
			 return null;

		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(date.getTime());
		 return gc;
	 }

	 public static Timestamp parseTimestamp(String dateStr, int index)
	 {
		 DateFormat df = null;
		 try {
			 df = new SimpleDateFormat(dateFormat[index]);

			 try {
				 return new Timestamp(df.parse(dateStr).getTime());
			 } catch (java.text.ParseException e) {
				 e.printStackTrace();
				 return null;
			 }
		 } catch (ParseException pe) {
			 return new Timestamp(parseDate(dateStr, index + 1).getTime());
		 } catch (ArrayIndexOutOfBoundsException aioe) {
			 return null;
		 }
	 }

	 public static Timestamp parseTimestamp(String dateStr)
	 {
		 DateFormat df = null;
		 try {
			 df = new SimpleDateFormat(dateFormat[0]);
			 try {
				 return new Timestamp(df.parse(dateStr).getTime());
			 } catch (java.text.ParseException e) {
				 e.printStackTrace();
				 return null;
			 }
		 } catch (ParseException pe) {
			 return null;
		 } catch (ArrayIndexOutOfBoundsException aioe) {
			 return null;
		 }
	 }

}