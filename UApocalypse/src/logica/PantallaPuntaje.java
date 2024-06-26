package logica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import persistencia.DatosPuntaje;
import vista.Boton;

public class PantallaPuntaje extends Estados implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boton botonVolver;
    private ArrayList<DatosPuntaje> highScores;
    private ArrayList<DatosPuntaje> cargarPuntajes;

    private DatosPuntaje[] auxArray;
    
    private static final String ARCHIVO_PUNTAJE = "highScores.dat";
    

    public PantallaPuntaje() {

        highScores = new ArrayList<>();
    }
    

    @Override
    public void update() {
        botonVolver.update();

        auxArray = highScores.toArray(new DatosPuntaje[highScores.size()]);
    }
    

    private void crearArchivoPuntos() {
    	try {
            Files.createFile(Paths.get(ARCHIVO_PUNTAJE));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	private boolean archivoPuntosExiste() {
		return new File(ARCHIVO_PUNTAJE).exists();
	}
	//cargar los puntajes y mostrar en consola
	public void mostrarHighScores() {
 
        cargarPuntajes = DatosPuntaje.cargarPuntajeArchivo();
        if (cargarPuntajes.isEmpty()) {
            System.out.println("No hay puntajes disponibles.");
        } else {
            System.out.println("High Scores:");

            for (DatosPuntaje puntos : cargarPuntajes) {
                System.out.println("Score: " + puntos.getScore() + " | Date: " + puntos.getDate());
            }
        }
    }

	//mostrar los puntos en pantalla
	@Override
    public void dibujar(Graphics g) {
        int fondoAncho = vista.Graficos.menu.getWidth();
        int fondoAlto = vista.Graficos.menu.getHeight();
        int x = (Constantes.ANCHO - fondoAncho) / 2;
        int y = (Constantes.ALTO - fondoAlto) / 2;
        g.drawImage(vista.Graficos.menu, x, y, null);
        botonVolver.dibujar(g);

        auxArray = highScores.toArray(new DatosPuntaje[highScores.size()]);

        int puntajePosX = Constantes.ANCHO / 5 + 100;
        int fechaPosX = Constantes.ANCHO /  2 + 01;
        int posY = 100;

        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.WHITE);

        g.drawString("Puntos", puntajePosX, posY);
        g.drawString("fecha", fechaPosX, posY);

        for (int i = auxArray.length - 1; i > -1; i--) {

        	int puntos = auxArray[i].getScore();
            LocalDateTime fecha = auxArray[i].getDate();

            posY += 30; 
            g.drawString(String.valueOf(puntos), puntajePosX, posY);
            
            g.drawString(fecha.toString(), fechaPosX, posY);
        }
        for (int i = cargarPuntajes.size() - 1; i >= 0; i--) {
            int puntos = cargarPuntajes.get(i).getScore();
            LocalDateTime fecha = cargarPuntajes.get(i).getDate();

            posY += 30;
            g.drawString(String.valueOf(puntos), puntajePosX, posY);
            g.drawString(fecha.toString(), fechaPosX, posY);
        }
    }

    @Override
    public void init() {
        borrarBotones();
        botonVolver = new Boton(
                vista.Graficos.azul,
                vista.Graficos.rojo,
                vista.Graficos.rojo.getHeight(),
                Constantes.ALTO - vista.Graficos.rojo.getHeight() * 2,
                Constantes.VOLVER,
                new Acciones() {
                    @Override
                    public void doAction() {
                        Estados.cambiarEstado(new Menu());
                    }
                }
        );
        if (!archivoPuntosExiste()) {
            crearArchivoPuntos();
        }
        mostrarHighScores();
        añadirBotones(botonVolver);
    }
}

