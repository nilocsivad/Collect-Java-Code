import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Colin
 */
public class File2Base64 {

	/**
	 * 
	 */
	public File2Base64() {
	}

	public String Translate(final String path) throws IOException {
		return this.Translate(new File(path));
	}

	public String Translate(final File file) throws IOException {

		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		byte[] buf = new byte[(int) file.length()];
		input.read(buf);
		input.close();

		String base64 = Base64.encodeBase64String(buf);
		return base64;
	}

}
