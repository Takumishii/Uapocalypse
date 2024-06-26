package vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import logica.Constantes;
import logica.Estados;
import logica.Juego;
import logica.Menu;
import logica.Mouse;
import logica.Teclado;

public class Escenario extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canvas lienzo;
	private Thread hilo;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TIEMPOIDEAL = 1000000000/FPS;
	private double delta = 0;
	private int FPSPROMEDIO = FPS;
	
	private Teclado teclado;
	private Mouse mouse;
	public Escenario() {
		setTitle("University Apocalypse");
		setSize(Constantes.ANCHO, Constantes.ALTO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		lienzo = new Canvas();
		teclado = new Teclado();
		mouse = new Mouse();
		
		lienzo.setPreferredSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
		lienzo.setMaximumSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
		lienzo.setMinimumSize(new Dimension(Constantes.ANCHO, Constantes.ALTO));
		lienzo.setFocusable(true);
		lienzo.requestFocus();
		
		add(lienzo);
		lienzo.addKeyListener(teclado);
		lienzo.addMouseListener(mouse);
		lienzo.addMouseMotionListener(mouse);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new Escenario().start();

	}
	
	private void update() {
		Estados.obtenerEstadoActual().update();
		teclado.update();
	}
	
	private void dibujar() {
		bs = lienzo.getBufferStrategy();
		
		
		if (bs == null) {
			lienzo.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		int anchoFondo, altoFondo, x, y;
	    
	    if (Estados.obtenerEstadoActual() instanceof Menu) {
	        anchoFondo = Graficos.menu.getWidth();
	        altoFondo = Graficos.menu.getHeight();
	        x = (Constantes.ANCHO - anchoFondo) / 2;
	        y = (Constantes.ALTO - altoFondo) / 2;
	        g.drawImage(Graficos.menu, x, y, null);
	    } else if (Estados.obtenerEstadoActual() instanceof Juego) {
	        anchoFondo = Graficos.mapa.getWidth();
	        altoFondo = Graficos.mapa.getHeight();
	        x = (Constantes.ANCHO - anchoFondo) / 2;
	        y = (Constantes.ALTO - altoFondo) / 2;
	        g.drawImage(Graficos.mapa, x, y, null);
	    }
		
		Estados.obtenerEstadoActual().dibujar(g);
		
		g.setColor(Color.WHITE);
		
		
		g.drawString(""+FPSPROMEDIO, 10, 20);
		
		
		g.dispose();
		bs.show();
	}
	
	private void init() {
		Graficos.init();
		Estados.cambiarEstado(new Menu());
	}
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/TIEMPOIDEAL;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1) {
				update();
				dibujar();
				delta--;
				frames++;
			}
			if(time >= 1000000000) {
				FPSPROMEDIO = frames;
				frames = 0;
				time = 0;
			}
		}
		
		stop();
	}
	private void start() {
		
		hilo = new Thread(this);
		hilo.start();
		running = true;
		
	}
	private void stop() {
		try {
			hilo.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
