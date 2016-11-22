import java.util.regex.Pattern;

/**
 * @author Colin
 */
public class RegexUtil {

	/**
	 * 
	 */
	public RegexUtil() {
	}
	
	public static boolean isInteger( String txt ) {
		return isEqualsFormat("^\\d+$", txt);
	}

	public static boolean isDouble(String txt) {
		return isEqualsFormat("^\\d+(\\.\\d+)?$", txt);
	}

	public static boolean isNumString(String txt) {
		return isEqualsFormat("^\\d+(,\\d+)*$", txt);
	}

	public static boolean isDate(String txt) {
		return isEqualsFormat("^\\d{4}-\\d{1,2}-\\d{1,2}$", txt);
	}

	public static boolean isMAC(String txt) {
		return isEqualsFormat("^[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}$", txt);
	}

	public static boolean isDateTime(String txt) {
		return isEqualsFormat("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", txt);
	}

	public static boolean isDateTime_(String txt) {
		return isEqualsFormat("^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}$", txt);
	}
	
	public static boolean isMobile(String txt) {
		return isEqualsFormat("^1\\d{10}$", txt);
	}

	public static boolean isEqualsFormat(String regex, String txt) {
		return txt != null && Pattern.matches(regex, txt);
	}
	
}
