package com.yy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate {

	private static final long serialVersionUID = -8074035369662984413L;

	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final int size = 15;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myvt2.www.dao.MyDate#FirstDayInMonth(sun.util.calendar.BaseCalendar
	 *      .Date)
	 */
	public static final Date FirstDayInMonth(GregorianCalendar cal) {
		cal.add(Calendar.DATE, -(cal.get(Calendar.DATE) - 1));
		return cal.getTime();
	}

	public static final Date FirstDayInMonth() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -(cal.get(Calendar.DATE) - 1));
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myvt2.www.dao.MyDate#LastDayInMonth(sun.util.calendar.BaseCalendar
	 *      .Date)
	 */
	public static final Date LastDayInMonth(GregorianCalendar cal) {
		cal.add(Calendar.DATE, -(cal.get(Calendar.DATE) - 1));
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static final Date LastDayInMonth() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -(cal.get(Calendar.DATE) - 1));
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myvt2.www.dao.MyDate#FirstDayInWeek(sun.util.calendar.BaseCalendar
	 *      .Date)
	 */
	public static final Date FirstDayInWeek(GregorianCalendar cal) {
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		return cal.getTime();
	}

	public static final Date FirstDayInWeek() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myvt2.www.dao.MyDate#LastDayInWeek(sun.util.calendar.BaseCalendar
	 *      .Date)
	 */
	public static final Date LastDayInWeek(GregorianCalendar cal) {
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	public static final Date LastDayInWeek() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#StartDateToString(java.util.Date)
	 */
	public static Date StartDateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(new SimpleDateFormat("yyyy-MM-dd")
					.format(date)
					+ " 00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#EndDateToString(java.util.Date)
	 */
	public static Date EndDateToString(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)
				+ " 23:59:59.999");
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateFormat(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#FirstDayInYear()
	 */
	public static Date FirstDayInYear() {
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_YEAR));
		cal.add(Calendar.DATE, 1);
		try {
			return formatter.parse(new SimpleDateFormat("yyyy-MM-dd")
					.format(cal.getTime())
					+ " 00:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#FirstDayInYear(java.util.GregorianCalendar )
	 */
	public static Date FirstDayInYear(GregorianCalendar cal)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_YEAR));
		cal.add(Calendar.DATE, 1);
		return formatter.parse(new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime())
				+ " 00:00:00.000");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#LastDayInYear()
	 */
	public static Date LastDayInYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.YEAR, 1);

		try {
			MyDate.FirstDayInYear(cal);
			cal.add(Calendar.DATE, -1);
			return formatter.parse(new SimpleDateFormat("yyyy-MM-dd")
					.format(cal.getTime())
					+ " 23:59:59.999");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.mayidata.spring.dao.MyDate#LastDayInYear(java.util.GregorianCalendar )
	 */
	public static Date LastDayInYear(GregorianCalendar cal)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.add(Calendar.DATE, cal.get(Calendar.DAY_OF_YEAR));
		cal.add(Calendar.DATE, -1);
		return formatter.parse(new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime())
				+ " 23:59:59.999");
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date StringToDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);

		return cal.getTime();
	}

	/**
	 * 
	 * @return
	 */
	public static int timestamp() {
		return Integer.valueOf(String.valueOf(new Date().getTime() / 1000));
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int timestamp(Date date) {
		return Integer.valueOf(String.valueOf(date.getTime() / 1000));
	}

}
