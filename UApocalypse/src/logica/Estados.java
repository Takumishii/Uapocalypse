package logica;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import vista.Boton;

public abstract class Estados {
	protected ArrayList<Boton> botones;

    public Estados() {
        botones = new ArrayList<>();
    }
    


    public void borrarBotones() {
        botones.clear();
    }

    public void añadirBotones(Boton... nuevoBoton) {
        botones.addAll(Arrays.asList(nuevoBoton));
    }
	
	private static Estados estadoActual = null;
	
	public static Estados obtenerEstadoActual() {return estadoActual;}
	public static void cambiarEstado(Estados nuevoEstado) {
		estadoActual = nuevoEstado;
		estadoActual.init(); 
	}
	public abstract void init();
	public abstract void update();
	public abstract void dibujar(Graphics g);

}
