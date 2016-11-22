import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Colin
 */
public final class EncryptText {

	public static final String TYPE_MD5 = "MD5";
	public static final String TYPE_SHA1 = "SHA1";
	public static final String TYPE_SHA512 = "SHA-512";

	/**
	 * 
	 */
	private EncryptText() {
	}

	private static final class EncryptTextSingleton {
		private static final EncryptText INSTANCE = new EncryptText();
	}

	public static EncryptText getInstance() {
		return EncryptTextSingleton.INSTANCE;
	}

	public String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Parameter text is empty or null!");
		}

		MessageDigest md = MessageDigest.getInstance(TYPE_MD5);
		byte[] bytes = md.digest(text.getBytes("UTF-8"));

		StringBuffer buf = new StringBuffer();
		for (byte b : bytes) {
			int n = b & 0xFF;
			String s = Integer.toHexString(n);
			buf.append(n > 15 ? s : "0" + s);
		}
		return buf.toString();
	}

	public String MD5_16(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Parameter text is empty or null!");
		}

		MessageDigest md = MessageDigest.getInstance(TYPE_MD5);
		byte[] bytes = md.digest(text.getBytes("UTF-8"));

		StringBuffer buf = new StringBuffer();
		for (byte b : bytes) {
			int n = b & 0xFF;
			String s = Integer.toHexString(n);
			buf.append(n > 15 ? s : "0" + s);
		}
		return buf.toString().substring(8, 24);
	}

	public String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Parameter text is empty or null!");
		}

		MessageDigest md = MessageDigest.getInstance(TYPE_SHA1);
		byte[] bytes = md.digest(text.getBytes("UTF-8"));

		StringBuffer buf = new StringBuffer();
		for (byte b : bytes) {
			int n = b & 0xFF;
			String s = Integer.toHexString(n);
			buf.append(n > 15 ? s : "0" + s);
		}
		return buf.toString();
	}

	public String SHA512(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Parameter text is empty or null!");
		}

		MessageDigest md = MessageDigest.getInstance(TYPE_SHA512);
		byte[] bytes = md.digest(text.getBytes("UTF-8"));

		StringBuffer buf = new StringBuffer();
		for (byte b : bytes) {
			int n = b & 0xFF;
			String s = Integer.toHexString(n);
			buf.append(n > 15 ? s : "0" + s);
		}
		return buf.toString();
	}

}
