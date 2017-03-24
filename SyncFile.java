/**
 * 
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author Colin
 *
 */
public class SyncFile {

	/**
	 * 
	 */
	public SyncFile() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String url = "";
		Document doc = Jsoup.connect(url).header("Referer", url).timeout(1000 * 60 * 3).get();

		String folder = "";
		String[] files = new File(folder).list();
		List<String> list = Arrays.asList(files);

		doc.getElementsByClass("href-link").forEach(ele -> {
			if (ele.attr("href").startsWith("http")) {
				String fileName = ele.text();
				if (list.contains(fileName) == false) {
					String href = ele.absUrl("href");
					new Thread() {
						public void run() {
							download(href, folder, fileName);
						}
					}.start();
				}
			}
		});

		// System.out.println(doc.html());

	}

	/**
	 * @param href
	 */
	private static void download(String httpUrl, String folder, String fileName) {
		
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/28.0");
			InputStream inStream = conn.getInputStream();

			File f = new File(folder, fileName);
			FileOutputStream fs = new FileOutputStream(f.getAbsolutePath());

			byte[] buffer = new byte[1204];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, len);
			}
			inStream.close();
			fs.close();
			
			System.out.println(String.format("Download from %s save at %s ", httpUrl, f.getAbsolutePath()));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("Download from %s error ", httpUrl));
		}

//		try {
//
//
//			URL link = new URL(httpUrl);
//			URLConnection conn = link.openConnection();
//			HttpURLConnection connection = (HttpURLConnection) conn;
//
//			File f = new File(folder, fileName);
//
//			int code = connection.getResponseCode();
//			if (code == HttpURLConnection.HTTP_OK) {
//
//				BufferedInputStream input = new BufferedInputStream(connection.getInputStream());
//				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(f));
//
//				byte[] buffer = new byte[1024];
//				int len = 0;
//				while ((len = input.read(buffer)) > 0) {
//					output.write(buffer, 0, len);
//				}
//
//				input.close();
//				output.close();
//
//				System.out.println(String.format("Download from %s save at %s ", httpUrl, f.getAbsolutePath()));
//
//			}
//			else {
//				System.err.println(String.format("Download from %s returned HTTP response code: %d ", httpUrl, code));
//			}
//
//
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
