package logica;

import java.awt.Graphics;

import vista.Boton;
import vista.Graficos;

public class Menu extends Estados{
	
	
	public Menu() {
		
		init();	
	}
	
	
	@Override
	public void update() {
		for(Boton b: botones) {
			b.update();
		}
	}

	@Override
	public void dibujar(Graphics g) {
		int bgWidth = Graficos.menu.getWidth();
	    int bgHeight = Graficos.menu.getHeight();
	    
	    int x = (Constantes.ANCHO - bgWidth) / 2;
	    int y = (Constantes.ALTO - bgHeight) / 2;
	    
	    g.drawImage(Graficos.menu, x, y, null);
	    
		for(Boton b: botones) {
			b.dibujar(g);
		}
	}


	@Override
	public void init() {
		botones.clear();
		botones.add(new Boton(
				Graficos.azul,
				Graficos.rojo,
				Constantes.ANCHO / 2 - Graficos.azul.getWidth()/2,
				Constantes.ALTO / 2 - Graficos.azul.getHeight(),
				Constantes.JUGAR,
				new Acciones() {
					@Override
					public void doAction() {
						Estados.cambiarEstado(new Juego());
					}
				}
				));
		
		botones.add(new Boton(
				Graficos.azul,
				Graficos.rojo,
				Constantes.ANCHO / 2 - Graficos.azul.getWidth()/2,
				Constantes.ALTO / 2 + Graficos.rojo.getHeight() * 2,
				Constantes.SALIR,
				new Acciones() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
		
	}

}