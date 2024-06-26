package logica;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import vista.Graficos;

public class Infectado extends ObjetosMovibles
{
	private double speed;

	public Infectado(Vectores2D position, Vectores2D velocity, double maxVel, BufferedImage texture, Juego gameState) {
		super(position, velocity, maxVel, texture, gameState);
		new Vectores2D();
        speed = 1.0; 
		
	}

	@Override
	public void update() {
		
        Vectores2D playerPosition = juego.getPlayerPosition();

        Vectores2D direction = playerPosition.sumar(posicion.escalar(-1)).normaliza();

     
        posicion = posicion.sumar(direction.escalar(speed));

    
        rapidez.limitar(maxVel);

        if (posicion.getX() < 0 || posicion.getX() > Constantes.ANCHO || posicion.getY() < 0 || posicion.getY() > Constantes.ALTO) {
            super.destruir();
        }
		
		
	}

	@Override
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());


        g2d.drawImage(Graficos.enemigo, at, null);
		
	}
	
	@Override
	public void destruir() {
		juego.addScore(Constantes.ZOMBIE_PUNTAJE);
		super.destruir();
	}
	
	public void setTarget(Vectores2D target) {
    }
	
	

}
