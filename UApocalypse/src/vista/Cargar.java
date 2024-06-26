package vista;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cargar {
	public static BufferedImage imagenCargar(String path) {
		try {
			return ImageIO.read(Cargar.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
