package logica;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends ObjetosMovibles{

	public Laser(Vectores2D position, Vectores2D velocity, double maxVel, double angle, BufferedImage texture, Juego gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.angulo = angle;
		this.rapidez = velocity.escalar(maxVel);
	}

	@Override
	public void update() {
		posicion = posicion.sumar(rapidez);
		if(posicion.getX() < 0 || posicion.getX() > Constantes.ANCHO || posicion.getY() < 0 || posicion.getY() > Constantes.ALTO) {
			destruir();
		}
		colisionarCon();
		
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(posicion.getX() - ancho/2, posicion.getY());
		
		at.rotate(angulo, ancho/2, 0);
		
		g2d.drawImage(textura, at, null);
		
	}
	
	@Override
	public Vectores2D getCenter() {
		return new Vectores2D(posicion.getX() + ancho/2, posicion.getY() + ancho/2);
	}

}
