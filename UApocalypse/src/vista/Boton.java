package vista;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import logica.Acciones;
import logica.Mouse;


public class Boton implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage mouseAfuera, mouseDentro;
	private boolean dentroMouse;
	private Rectangle recuadro;
	private Acciones accion;
	
	public Boton(
			BufferedImage mouseAfuera,
			BufferedImage mouseDentro,
			int x, int y,
			String text,
			Acciones action
			) {
		this.mouseDentro = mouseDentro;
		this.mouseAfuera = mouseAfuera;
		recuadro = new Rectangle(x, y, mouseDentro.getWidth(), mouseDentro.getHeight());
		this.accion = action;
	}
	public void update() {
		recuadroBotonUpdate();
	}
	
	public void recuadroBotonUpdate() {
		if(recuadro.contains(Mouse.X, Mouse.Y)) {
			dentroMouse = true;
		}else {
			dentroMouse = false;
		}
		
		if(dentroMouse && Mouse.MLB) {
			accion.doAction();
		}
	}

	public void dibujar(Graphics g) {
		if(dentroMouse) {
			g.drawImage(mouseDentro, recuadro.x, recuadro.y, null);
		}else {
			g.drawImage(mouseAfuera, recuadro.x, recuadro.y, null);
		}
	}
	
}
