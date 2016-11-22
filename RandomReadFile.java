import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Colin
 */
public final class RandomReadFile {

	/**
	 * 
	 */
	public RandomReadFile() {
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String suffix = "txt";
		String path = String.format("E:\\%s.%s", "__说明__", suffix);
		RandomAccessFile aFile = new RandomAccessFile(path, "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(96);

		int byteReade = inChannel.read(buf);
		while (byteReade != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			System.out.println();
			buf.clear();
			byteReade = inChannel.read(buf);
		}

		aFile.close();

	}

}
