/**
 * 
 */
package com.iam_vip.java_applet.rs.u;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Colin
 */
public class DTUtil {
	
	/**
	 * 
	 */
	public DTUtil() {}
	
	
	public static final String	FMT_DEFAULT		= "yyyy-MM-dd HH:mm:ss";
	public static final String	FMT_DATE		= "yyyy-MM-dd";
	public static final String	FMT_DATE_HHMM	= "yyyy-MM-dd HH:mm";
	public static final String	FMT_TIME		= "yyyy-MM-dd";
	public static final String	FMT_FILE		= "yyyy-MM-dd_HH-mm-ss";
												
												
	public static String nowDT() {
		
		return formatDT( FMT_DEFAULT );
	}
	
	public static String nowDate() {
		
		return formatDT( FMT_DATE );
	}
	
	public static String nowTime() {
		
		return formatDT( FMT_TIME );
	}
	
	public static String formatDT( String fmt ) {
		
		return formatDT( fmt, new Date() );
	}
	
	public static String formatDT( String fmt, Date date ) {
		
		return new SimpleDateFormat( fmt ).format( date );
	}
	
}
