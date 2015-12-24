import java.util.regex.Pattern;

/**
 * @author Colin
 */
public class RegexUtil {
	
	/**
	 * 
	 */
	private RegexUtil() {}
	
	/**
	 * 
	 * @param txt "520"
	 * @return
	 */
	public static boolean isInteger( String txt ) {
		
		return Pattern.matches( "\\d*", txt );
	}
	
	/**
	 * 
	 * @param txt "1,2,3,4"
	 * @return
	 */
	public static boolean isIntegerString( String txt ) {
		
		return Pattern.matches( "\\d*|(\\d*,\\d*)*", txt );
	}
	
}
