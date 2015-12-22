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
	 * @param txt
	 * @return
	 */
	public static boolean isInteger( String txt ) {
		
		return Pattern.matches( "\\d*", txt );
	}
	
	public static void main( String[] args ) {
		
		System.out.println( isInteger( "233" ) );
		System.out.println( isInteger( "23b" ) );
		System.out.println( isInteger( "23x" ) );
	}
	
}
