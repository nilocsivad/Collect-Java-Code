import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


/**
 * @author Colin
 *
 */
public class StreamOfObject {

	/**
	 * 
	 */
	private StreamOfObject() {
		// TODO Auto-generated constructor stub
	}

	private static final class StreamOfObjectSingleton {
		private static final StreamOfObject INSTANCE = new StreamOfObject();
	}

	public static StreamOfObject getInstance() {
		return StreamOfObjectSingleton.INSTANCE;
	}

	/**
	 * write data to file, create the file if not exists or empty content the to
	 * write
	 * 
	 * @param data
	 *            data
	 * @param toPath
	 *            data file
	 * @throws IOException
	 */
	public void write2File(final Object data, final String toPath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toPath));
		ObjectOutput output = new ObjectOutputStream(bos);
		output.writeObject(data);
		output.close();
		bos.close();
	}

	/**
	 * read data from file
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public <T> T readFileData(final String path) throws IOException, ClassNotFoundException {
		File f = new File(path);
		if (!f.exists()) {
			throw new IOException(path + " not exists!");
		}
		ObjectInput input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
		T t = (T) input.readObject();
		return t;
	}

}
