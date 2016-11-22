import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

/**
 * @author Colin
 */
public class Image2RoundedCorner {
	
	/**
	 * 
	 */
	public Image2RoundedCorner() {}
	
	@Test
	public void run() throws Exception {
		
		String path = "E:\\tmp\\1.jpg";
		String to2 = String.format( "E:\\tmp\\1-%d.jpg", System.currentTimeMillis() );
		
		BufferedImage source = ImageIO.read( new File( path ) );
		
		// BufferedImage dest = makeRoundedCorner( source, 30 );
		
		int w = source.getWidth(), h = source.getHeight();
		
		BufferedImage dest = new BufferedImage( w, h, BufferedImage.TYPE_INT_ARGB );
		
		Graphics2D g2 = dest.createGraphics();
		g2.setComposite( AlphaComposite.Src );
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor( Color.WHITE );
		double arcw = w * 0.15, arch = h * 0.15;
		g2.fill( new RoundRectangle2D.Double( 0, 0, w, h, arcw, arch ) );
		g2.setComposite( AlphaComposite.SrcAtop );
		g2.drawImage( source, 0, 0, null );
		g2.dispose();
		
		ImageIO.write( dest, "jpg", new File( to2 ) );
		
	}
	
	public static BufferedImage makeRoundedCorner( BufferedImage image, int cornerRadius ) {
		
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage( w, h, BufferedImage.TYPE_INT_RGB );
		
		Graphics2D g2 = output.createGraphics();
		
		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)
		
		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...
		g2.setComposite( AlphaComposite.Src );
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor( Color.WHITE );
		g2.fill( new RoundRectangle2D.Float( 0, 0, w, h, cornerRadius, cornerRadius ) );
		
		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite( AlphaComposite.SrcAtop );
		g2.drawImage( image, 0, 0, null );
		
		g2.dispose();
		
		return output;
	}
	
}
