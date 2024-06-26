package logica;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter{
	
	private boolean[] keys = new boolean[256];
	
	public static boolean LIZQUIERDO, RERECHO, DISPARO;
	
	public Teclado() {
		LIZQUIERDO = false;
		RERECHO = false;
		DISPARO = false;
	}
	
	public void update() {
		LIZQUIERDO = keys[KeyEvent.VK_LEFT];
		RERECHO = keys[KeyEvent.VK_RIGHT];
		DISPARO = keys[KeyEvent.VK_SPACE];
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
}
