import java.io.File;
import java.io.IOException;

/**
 * @author Colin
 *
 */
public class CmdMove2 {

	/**
	 * 
	 */
	public CmdMove2() {
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String from = "F:\\Downloads", dest = "F:\\33333333";

		File folder = new File(from);

		String[] suffix = { ".td", ".torrent" };
		list4(folder, dest, suffix);
		// move2(folder, dest, suffix, false);

	}

	static void list4(File folder, String dest, final String[] suffix) throws IOException {
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				list4(f, dest, suffix);
			} else {
				if (suffix.length > 0) {
					boolean _jmp = false;
					for (String _suf : suffix) {
						if (f.getName().endsWith(_suf)) {
							_jmp = true;
							break;
						}
					}
					if (_jmp)
						continue;
				}
				String p = f.getAbsolutePath();
				System.out.println(p);
				Runtime.getRuntime().exec(String.format(" cmd /c move \"%s\" \"%s\" ", f.getAbsolutePath(), dest));
			}
		}
	}

	static void move2(File folder, String dest, final String[] suffix, boolean createFolder) throws IOException {

		File _dest = new File(dest);
		if (!_dest.exists() || _dest.isFile())
			throw new RuntimeException(dest + " not exists or not a folder!");

		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				if (createFolder) {
					File _f = new File(dest, f.getName());
					_f.mkdirs();
					move2(f, _f.getAbsolutePath(), suffix, createFolder);
				} else {
					move2(f, dest, suffix, createFolder);
				}
			} else {

				if (suffix.length > 0) {
					boolean _jmp = false;
					for (String _suf : suffix) {
						if (f.getName().endsWith(_suf)) {
							_jmp = true;
							break;
						}
					}
					if (_jmp)
						continue;
				}

				Runtime.getRuntime().exec(String.format(" cmd /c move \"%s\" \"%s\" ", f.getAbsolutePath(), dest));
			}
		}
	}

}
