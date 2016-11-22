import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Colin
 */
public class FileDownloadUtil {

	/**
	 * 
	 */
	public FileDownloadUtil() {
	}

	private static final class FileDownloadSingleton {
		private static final FileDownloadUtil INSTANCE = new FileDownloadUtil();
	}

	public static FileDownloadUtil getInstance() {
		return FileDownloadSingleton.INSTANCE;
	}

	public void download(String url, String path) throws IOException {

		URL link = new URL(url);
		URLConnection conn = link.openConnection();
		HttpURLConnection connection = (HttpURLConnection) conn;

		int code = connection.getResponseCode();
		if (code == HttpURLConnection.HTTP_OK) {

			BufferedInputStream input = new BufferedInputStream(connection.getInputStream());
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path));

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = input.read(buffer)) > 0) {
				output.write(buffer, 0, len);
			}

			input.close();
			output.close();

			System.out.println(String.format("Download from %s save at %s ", url, path));

		}
		else {
			System.err.println(String.format("Download from %s returned HTTP response code: %d ", url, code));
		}
	}

	public static void httpDownload(String httpUrl, String path) {

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/28.0");
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream(path);

			byte[] buffer = new byte[1204];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, len);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				FW.write(httpUrl);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
