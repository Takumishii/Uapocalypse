package logica;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class ObjetosMovibles extends Objeto{
	
	protected Vectores2D rapidez;
	protected AffineTransform at;
	protected double angulo;
	protected double maxVel;
	protected int ancho;
	protected int alto;
	protected Juego juego;
	
	public ObjetosMovibles(Vectores2D position, Vectores2D rapidez, double maxVel, BufferedImage textura, Juego juego) {
		super(position, textura);
		this.rapidez = rapidez;
		this.maxVel = maxVel;
		this.juego = juego;
		ancho = textura.getWidth();
		alto = textura.getHeight();
		angulo = 0;
		
	}
	
	protected void colisionarCon() {//detectar colisiones
		ArrayList<ObjetosMovibles> objectoMovible = juego.obtenerObjetoMovible();
		
		for(int i = 0; i < objectoMovible.size(); i++) {
			ObjetosMovibles m = objectoMovible.get(i);
			
			if(m.equals(this))
				continue;
			
			double distancia = m.getCenter().restar(getCenter()).obtenerMagnitud();
			
			if(distancia < m.ancho/2 + ancho/2 && objectoMovible.contains(this)) {
				colisionObjeto(m, this);
				
			}
		}
		
	}
	
	private void colisionObjeto(ObjetosMovibles a, ObjetosMovibles b) {//destruccion si toca
		
		
		if(a instanceof Aldeano && ((Aldeano)a).estaSpawneando()) {
			return;
		}
		if(b instanceof Aldeano && ((Aldeano)b).estaSpawneando()) {
			return;
		}
		
		if(!(a instanceof Infectado && b instanceof Infectado)) {
			a.destruir();
			b.destruir();
		}
	}
	
	protected void destruir() {
		juego.obtenerObjetoMovible().remove(this);
	}
	
	protected Vectores2D getCenter() {
		return new Vectores2D(posicion.getX() + ancho/2, posicion.getY() + alto/2);
	}

}
