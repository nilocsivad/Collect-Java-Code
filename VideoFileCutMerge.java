import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;

/**
 * @author Colin
 */
public class VideoFileCutMerge {

	/**
	 * java根据头文件获取文件类型<br/>
	 * http://www.cnblogs.com/suding1188/archive/2012/12/25/2832785.html<br/>
	 * <br/>
	 * java 合并mp4视频文件<br/>
	 * http://www.haolizi.net/example/view_8565.html<br/>
	 */

	/**
	 * 
	 */
	public VideoFileCutMerge() {
	}

	public static void appendVideo(String fTo, String[] videos) throws IOException {

		Movie[] inMovies = new Movie[videos.length];

		for (int i = 0; i < videos.length; i++) {
			inMovies[i] = MovieCreator.build(videos[i]);
		}

		List<Track> videoTracks = new LinkedList<Track>();
		List<Track> audioTracks = new LinkedList<Track>();

		for (Movie m : inMovies) {
			for (Track t : m.getTracks()) {
				if (t.getHandler().equals("soun")) {
					audioTracks.add(t);
				}
				if (t.getHandler().equals("vide")) {
					videoTracks.add(t);
				}
			}
		}

		Movie result = new Movie();
		for (Track tk : audioTracks) {
			result.addTrack(tk);
		}
		for (Track tk : videoTracks) {
			result.addTrack(tk);
		}
		// result.addTrack(new AppendTrack(videoTracks.toArray(new Track[0])));
		// result.addTrack(new AppendTrack(audioTracks.toArray(new Track[0])));

		Container out = new DefaultMp4Builder().build(result);

		new File(fTo).delete();

		RandomAccessFile rcf = new RandomAccessFile(fTo, "rw");
		FileChannel fc = rcf.getChannel();
		out.writeContainer(fc);
		fc.close();
		rcf.close();
	}

	public static void main(String[] args) throws IOException {


		/// 合并视频, 视频的宽度高度要一致

		String sourceVdo = "F:/2222.mp4";
		String sourceVdo2 = "F:/2222-2.mp4";

		String fTo = "F:/append2.mp4";

		String[] videos = { sourceVdo, sourceVdo2 };
		appendVideo(fTo, videos);

	}










	public static void main33(String[] args) throws IOException {

		String sourceVdo = "F:/Downloads/灵书妙探.Castle.S07E04.中英字幕.WEBrip.720X400-YYeTs人人影视.mp4";

		FileInputStream fis = new FileInputStream(sourceVdo);

		int oneM = 1 * 1024 * 1024;



		int headLen = oneM;
		byte[] head = new byte[headLen];
		fis.read(head, 0, headLen);

		byte[] body = new byte[oneM * 10];
		fis.read(body);



		// int jumpPos = fis.available() - oneM;
		// RandomAccessFile rcf = new RandomAccessFile(sourceVdo, "r");
		// rcf.seek(jumpPos);
		//
		// byte[] foot = new byte[oneM];
		// rcf.readFully(foot);
		// rcf.close();

		fis.close();



		FileOutputStream fos = new FileOutputStream("F:/out.mp4");
		fos.write(head);
		fos.write(body);
		// fos.write(foot);
		fos.close();



	}













	public static void main22(String[] args) throws IOException {

		String sourceVdo = "F:/Downloads/灵书妙探.Castle.S07E04.中英字幕.WEBrip.720X400-YYeTs人人影视.mp4";

		FileInputStream fis = new FileInputStream(sourceVdo);

		int oneM = 1 * 1024 * 1024;



		int headLen = oneM;
		byte[] head = new byte[headLen];
		fis.read(head, 0, headLen);

		byte[] body = new byte[oneM * 10];
		fis.read(body);



		// int jumpPos = fis.available() - oneM;
		// RandomAccessFile rcf = new RandomAccessFile(sourceVdo, "r");
		// rcf.seek(jumpPos);
		//
		// byte[] foot = new byte[oneM];
		// rcf.readFully(foot);
		// rcf.close();

		fis.close();



		FileOutputStream fos = new FileOutputStream("F:/out.mp4");
		fos.write(head);
		fos.write(body);
		// fos.write(foot);
		fos.close();



	}

}
