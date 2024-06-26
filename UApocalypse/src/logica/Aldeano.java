package logica;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import vista.Graficos;

public class Aldeano extends ObjetosMovibles{
	
	private Vectores2D cabecera, aceleracion;
	private Tiempo cadencia, spawn, parpadeo;
	private boolean spawning, visible;

	public Aldeano(Vectores2D posicion, Vectores2D rapidez, double maxVel, BufferedImage textura, Juego juego) {
		super(posicion, rapidez, maxVel, textura, juego);
		cabecera = new Vectores2D(0, 1);
		aceleracion = new Vectores2D();
		cadencia = new Tiempo();
		spawn = new Tiempo();
		parpadeo = new Tiempo();
		
	}

	@Override
	public void update() {
		funcionaUpdate();
		disparoUpdate();
		rotacionUpdate();
		posicionUpdate();
	}
	
	@Override
	public void destruir() {
		spawning = true;
		spawn.funcionando(Constantes.TIEMPO_SPAWN);
		juego.subtractLife();
		
        
	}

	@Override
	public void dibujar(Graphics g) {
		
		if(!visible)
			return;
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());
		
		at.rotate(angulo, Graficos.jugador.getWidth()/2, Graficos.jugador.getHeight()/2);
		
		g2d.drawImage(Graficos.jugador, at, null);
	}
	
	public void funcionaUpdate() {
		if(!spawn.estaFuncionando()) {
			spawning = false;
			visible = true;
		}
		
		if(spawning) {
			parpadeoUpdate();
		}
	}
	public void parpadeoUpdate() {
		if(!parpadeo.estaFuncionando()) {
			parpadeo.funcionando(Constantes.TIEMPO_PARPADEO);
			visible = !visible;
		}
	}
	public void disparoUpdate() {
		if(Teclado.DISPARO &&  !cadencia.estaFuncionando() && !spawning) {
			juego.obtenerObjetoMovible().add(0, new Laser(
					getCenter().sumar(cabecera.escalar(ancho)),
					cabecera,
					Constantes.LASER_VEL,
					angulo,
					Graficos.laser,
					juego
					));
			cadencia.funcionando(Constantes.CADENCIA);
		}
	}

	public void rotacionUpdate() {
		if(Teclado.RERECHO)
			angulo += Constantes.ANGULODELTA;
		
		if(Teclado.LIZQUIERDO)
			angulo -= Constantes.ANGULODELTA;
	}

	public void posicionUpdate() {
		rapidez = rapidez.sumar(aceleracion);
		
		rapidez.limitar(maxVel);
		
		cabecera = cabecera.establecerDireccion(angulo - Math.PI/2);
		
		posicion = posicion.sumar(rapidez);
		
		cadencia.update();
		spawn.update();
		parpadeo.update();
		colisionarCon();
		
	}
	public boolean estaSpawneando() {return spawning;}
	
	

}
