package vista;

import java.awt.image.BufferedImage;

public class Graficos {
	
	public static BufferedImage jugador, laser, enemigo, mapa, menu, vida, azul, rojo;
	public static BufferedImage[] numeros = new BufferedImage[11];
	public static void init() {
		jugador = Cargar.imagenCargar("/pj/pa.png");
		laser = Cargar.imagenCargar("/lasers/laserBlue01.png");
		enemigo = Cargar.imagenCargar("/enemy/zombi.png");
		mapa = Cargar.imagenCargar("/backgrounds/map.png");
		vida = Cargar.imagenCargar("/ui/life.png");
		for(int i = 0; i < numeros.length; i++)
			numeros[i] = Cargar.imagenCargar("/ui/"+i+".png");
		rojo = Cargar.imagenCargar("/ui/rojo.png");
		azul = Cargar.imagenCargar("/ui/azul.png");
		menu = Cargar.imagenCargar("/backgrounds/menu.png");
	}

}
