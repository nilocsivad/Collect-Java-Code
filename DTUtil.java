import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Colin
 */
public class DTUtil {


	public static final String FMT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_DATE = "yyyy-MM-dd";
	public static final String FMT_MONTH = "yyyy-MM";
	public static final String FMT_DATE_HHMM = "yyyy-MM-dd HH:mm";
	public static final String FMT_TIME = "HH:mm:ss";
	public static final String FMT_FILE = "yyyy-MM-dd_HH-mm-ss";

	private static final SimpleDateFormat SDF_DEFAULT = new SimpleDateFormat(FMT_DEFAULT);
	private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(FMT_DATE);
	private static final SimpleDateFormat SDF_MONTH = new SimpleDateFormat(FMT_MONTH);
	private static final SimpleDateFormat SDF_DATE_HHMM = new SimpleDateFormat(FMT_DATE_HHMM);
	private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat(FMT_TIME);
	private static final SimpleDateFormat SDF_FILE = new SimpleDateFormat(FMT_FILE);

	private static final Map<String, SimpleDateFormat> MAP = new HashMap<String, SimpleDateFormat>(6);

	static {
		MAP.put(FMT_DEFAULT, SDF_DEFAULT);
		MAP.put(FMT_DATE, SDF_DATE);
		MAP.put(FMT_MONTH, SDF_MONTH);
		MAP.put(FMT_DATE_HHMM, SDF_DATE_HHMM);
		MAP.put(FMT_TIME, SDF_TIME);
		MAP.put(FMT_FILE, SDF_FILE);
	}


	protected DTUtil() {
	}

	public static Date now() {
		return new Date();
	}

	public static String nowDT() {
		return formatDT(FMT_DEFAULT);
	}

	public static String nowDate() {
		return formatDT(FMT_DATE);
	}

	public static String nowTime() {
		return formatDT(FMT_TIME);
	}

	public static String formatDT(String fmt) {
		return formatDT(fmt, new Date());
	}

	public static String formatDT(String fmt, Date date) {
		return getFmt(fmt).format(date);
	}






	public static Date parseDT(String timeline) throws ParseException {
		return getFmt(FMT_DEFAULT).parse(timeline);
	}

	public static Date parseDT(String fmt, String timeline) throws ParseException {
		return getFmt(fmt).parse(timeline);
	}





	public static DateFormat getFmt(String fmt) {
		return MAP.get(fmt);
	}
	
	
	
	
	

	public int calcAge( Date date ) {
		int age = 0;

		Calendar nc = Calendar.getInstance();
		int ny = nc.get( Calendar.YEAR );
		int nm = nc.get( Calendar.MONTH );
		int nd = nc.get( Calendar.DAY_OF_MONTH );

		Calendar bc = Calendar.getInstance();
		bc.setTime( date );
		int by = bc.get( Calendar.YEAR );
		int bm = bc.get( Calendar.MONTH );
		int bd = bc.get( Calendar.DAY_OF_MONTH );

		age = ny - by;
		if ( nm < bm ) age --;
		else if ( nm == bm && nd < bd ) age --;

		return age;
	}

}
