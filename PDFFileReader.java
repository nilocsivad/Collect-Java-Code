import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * @author Colin
 */
public class PDFFileReader {

	/**
	 * 
	 */
	public PDFFileReader() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		String path = "C:/Users/Colin/Downloads/话语攻心术：把话说到对方心坎里--成杰着.pdf";

		// FileInputStream fis = new FileInputStream(file);
		File file = new File(path);

		RandomAccessRead rdac = new RandomAccessFile(file, "rw");

		PDFParser parser = new PDFParser(rdac);
		parser.parse();

		COSDocument cosDoc = parser.getDocument();
		PDFTextStripper stripper = new PDFTextStripper();
		String docText = stripper.getText(new PDDocument(cosDoc));
		System.out.println(docText);

	}

}
