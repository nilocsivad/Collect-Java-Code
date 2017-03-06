package com.iam_vip.picture_image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Hello world!
 */
public class ResizeImageSize {


	public static void main(String[] args) throws IOException {

		int[] sizes = { 29, 57, 58, 60, 80, 87, 114, 120, 180 };

		String folder = "/Users/Colin/Downloads/app图标";
		String[] imgs = { "zhi-jiao.png" };

		System.out.println("Hello World!");

		for (String img : imgs) {

			File fimg = new File(folder, img);

			String fname = fimg.getName();
			int idx = fname.lastIndexOf(".");
			String prefix = fname.substring(0, idx), suffix = idx > 0 ? fname.substring(idx + 1) : "jpg";

			String tofmt = "%s_%dx%d.%s";

			for (int size : sizes) {

				String toName = String.format(tofmt, prefix, size, size, suffix);
				File toimg = new File(folder, toName);

				Image source = ImageIO.read(fimg);
				BufferedImage dest = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
				dest.getGraphics().drawImage(source, 0, 0, size, size, null);
				ImageIO.write(dest, suffix, toimg);

				System.out.println(toimg.getAbsolutePath());

			}

		} // for (String img : imgs) {}

		// ImageIO.write

	}

}
