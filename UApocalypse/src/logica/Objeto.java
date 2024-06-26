package logica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Objeto {
	protected BufferedImage textura;
	protected Vectores2D posicion;
	
	public Objeto(Vectores2D posicion, BufferedImage textura) {
		this.posicion = posicion;
		this.textura = textura;
	}
	
	public abstract void update();
	
	public abstract void dibujar(Graphics g);

	public Vectores2D getPosition() {
		return posicion;
	}

	public void setPosition(Vectores2D posicion) {
		this.posicion = posicion;
	}
	
	
	
}
