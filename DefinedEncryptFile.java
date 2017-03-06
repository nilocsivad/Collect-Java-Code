/**
 * 
 */
package org.zizi.sbyte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.security.NoSuchAlgorithmException;

import org.zizi.utils.AppMD5;

/**
 * @author Administrator
 *
 */
public class ByteAppRun {

	/**
	 * 
	 */
	public ByteAppRun() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String ENCRYPTED = "#FILE_ENCRYPTED#";
	private static final String PWDSUFFIX = "#FILE__PASSWORD#";

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
//		final String fileTxtPath = "E:\\TopDesk\\logo.txt";
//		File2Stream("E:\\TopDesk\\logo.jpg", "E:\\TopDesk\\logo-1.jpg", fileTxtPath);
//		Txt3File(fileTxtPath, "E:\\TopDesk\\logo-2.jpg");
		
		String path= "E:\\tools\\res.vips.com\\logo.jpg.jpg";
		final String pwd = "11111";
		
//		System.out.println("### No.1: " + path);
//		
//		path = EEEFile(path, pwd);
//		System.out.println("### No.2: " + path);
//		
//		path = DDDFile(path, pwd);
//		System.out.println("### No.3: " + path);
		
		System.out.println(ValidPWD(path, pwd, true));
		System.out.println(AppMD5.getMD5(pwd));
	}
	
	public static boolean ValidFile(final String filePath) throws IOException {

		File file = new File(filePath);
		if (!file.exists()) {
			
		}
		
		byte[] ebs = ENCRYPTED.getBytes("UTF-8");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		byte[] bs = new byte[ebs.length];
		bis.read(bs);
		bis.close();
		
		boolean equals = true;
		
		for (int ix = 0, max = ebs.length; max > ix; ++ix) {
			if (ebs[ix] != bs[ix]) {
				equals = false;
				break;
			}
		}
		
		return equals;
	}
	
	public static boolean ValidPWD(final String filePath, final String pwd) throws IOException, NoSuchAlgorithmException {
		return ValidPWD(filePath, pwd, false);
	}
	
	public static boolean ValidPWD(final String filePath, final String pwd, boolean out) throws IOException, NoSuchAlgorithmException {
		
		File file = new File(filePath);
		if (!file.exists()) {
			
		}
		
		final String md5 = AppMD5.getMD5(pwd);
		
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		
		byte[] buf = new byte[ (PWDSUFFIX + md5).getBytes("UTF-8").length];
		long seek = file.length() - buf.length;
		raf.seek(seek);
		raf.read(buf);
		raf.close();
		
		String result = new String(buf, "UTF-8");
		
		boolean right = false;
		
		if (out) {
			System.out.println(result);
		}
		
		if (result.equals(PWDSUFFIX + md5)) {
			right = true;
		}
		
		return right;
	}
	
	public static String DDDFile(final String filePath, final String pwd) throws IOException, NoSuchAlgorithmException {
		
		if ( ! ValidFile(filePath) ) {
			throw new RuntimeException("It's not a ecrypt file!");
		}
		if ( ! ValidPWD(filePath, pwd) ) {
			throw new RuntimeException("Password not correct!");
		}
		
		File file = new File(filePath);
		

		byte[] keys = (ENCRYPTED + "-" + PWDSUFFIX + "-" + pwd).getBytes("UTF-8");
		int m = 0, l = keys.length;
		
		
		int idx = file.getName().lastIndexOf(".");
		String suffix = idx > 0 ? file.getName().substring(idx) : ".de";
		String path = filePath + suffix;
		

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
		
		final String md5 = AppMD5.getMD5(pwd);

		bis.skip(ENCRYPTED.getBytes("UTF-8").length);
		
		long seek = file.length() - (PWDSUFFIX + md5).getBytes("UTF-8").length - ENCRYPTED.getBytes("UTF-8").length;
		
		int b = 0; 
		long len = 0;
		while ( (b = bis.read()) > -1 ) {
			
			if (len == seek)
				break;
			++len;
			
//			b = b == 0 ? 0 : -b;
			b ^= keys[m];
			bos.write(b);
			bos.flush();
			
			++m;
			m = m == l ? 0 : m;
		}
		bis.close();
		bos.close();
		
		return path;

	}
	
	public static String EEEFile(final String filePath, final String pwd) throws IOException, NoSuchAlgorithmException {
		
		File file = new File(filePath);
		if (!file.exists()) {
			
		}

		
		int idx = file.getName().lastIndexOf(".");
		String suffix = idx > 0 ? file.getName().substring(idx) : ".ed";
		String finalPath = filePath + suffix;
		
		
		byte[] keys = (ENCRYPTED + "-" + PWDSUFFIX + "-" + pwd).getBytes("UTF-8");
		int m = 0, l = keys.length;
		
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(finalPath));
		
		byte[] buf = ENCRYPTED.getBytes("UTF-8");
		bos.write(buf);
		bos.flush();
		
		int b = 0;
		while ( (b = bis.read()) > -1) {
			
			b ^= keys[m];
			bos.write(b);
			bos.flush();
			
			++m;
			m = m == l ? 0 : m;
		}
		
		byte[] pwds = (PWDSUFFIX + AppMD5.getMD5(pwd)).getBytes("UTF-8");
		bos.write(pwds);
		bos.flush();
		
		bis.close();
		bos.close();
		
		return finalPath;
	}
	
	public static void File2Stream(final String filePath, final String toFilePath, final String fileTxtPath) throws IOException {
		
		File file = new File(filePath);
		if (!file.exists()) {
			
		}
		
		System.out.println("file length: " + file.length());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		
		System.out.println("bis length: " + bis.available());
		
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = bis.read(buf)) > 0) {
			bos.write(buf, 0, len);
			bos.flush();
		}
		byte[] bytes = baos.toByteArray();
		bis.close();
		bos.close();
		
		System.out.println("bytes length: " + bytes.length);
		
		FileOutputStream fos = new FileOutputStream(toFilePath);
		fos.write(bytes);
		fos.close();
		
		FileWriter fw = new FileWriter(fileTxtPath);
		fw.write("This is first line!\r\n");
		fw.flush();
		
		for (byte b : bytes) {
			int i = b * 2;
			System.out.println(b + " - " + i);
			fw.write(i + "\r\n");
			fw.flush();
		}
		fw.close();
		
	}
	
	public static void Txt3File(final String fileTxtPath, final String toFilePath) throws IOException {

		File file = new File(fileTxtPath);
		if (!file.exists()) {
			
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		
		Reader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		System.out.println(br.readLine());
		
		String line = null;
		while ((line = br.readLine()) != null) {
			int i = Integer.parseInt(line);
			byte b = (byte) (i / 2);
			bos.write(b);
			bos.flush();
		}
		br.close();
		fr.close();
		
		byte[] bytes = baos.toByteArray();
		bos.close();
		
		BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(toFilePath));
		fileOut.write(bytes);
		fileOut.close();
		
	}

}
