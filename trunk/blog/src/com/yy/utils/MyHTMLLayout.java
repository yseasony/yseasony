package com.yy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.spi.LoggingEvent;

public class MyHTMLLayout extends org.apache.log4j.HTMLLayout {

	// RegEx pattern looks for <tr> <td> nnn...nnn </td> (all whitespace
	// ignored)

	private String conversionPattern ="";

	private static final String rxTimestamp = "\\s*<\\s*tr\\s*>\\s*<\\s*td\\s*>\\s*(\\d*)\\s*<\\s*/td\\s*>";

	private String timestampFormat = "yyyy-MM-dd HH:mm:ss"; // Default
																// format.
																// Example:
																// 2008-11-21-18:35:21.472-0800

	private SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);

	public MyHTMLLayout() {
		super();
	}

	/** Override HTMLLayout's format() method */

	public String format(LoggingEvent event) {
		String record = super.format(event); // Get the log record in the
												// default HTMLLayout format.

		Pattern pattern = Pattern.compile(rxTimestamp); // RegEx to find the
														// default timestamp
		Matcher matcher = pattern.matcher(record);

		if (!matcher.find()) // If default timestamp cannot be found,
		{
			return record; // Just return the unmodified log record.
		}

		StringBuffer buffer = new StringBuffer(record);

		buffer.replace(matcher.start(1), // Replace the default timestamp with
											// one formatted as desired.
				matcher.end(1), sdf.format(new Date(event.timeStamp)));

		return buffer.toString(); // Return the log record with the desired
									// timestamp format.
	}

	/**
	 * Setter for timestamp format. Called if
	 * log4j.appender.<category>.layout.TimestampFormat property is specfied
	 */

	public void setTimestampFormat(String format) {
		this.timestampFormat = format;
		this.sdf = new SimpleDateFormat(format); // Use the format specified by
													// the TimestampFormat
													// property
	}

	/** Getter for timestamp format being used. */

	public String getTimestampFormat() {
		return this.timestampFormat;
	}

	public String getConversionPattern() {
		return conversionPattern;
	}

	public void setConversionPattern(String conversionPattern) {
		this.conversionPattern = conversionPattern;
	}
}
