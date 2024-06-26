package persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatosPuntaje implements Serializable{
//variables
    private static final long serialVersionUID = 1L;
    private static final String ARCHIVO_PUNTAJE = "highScores.dat";
    private int score;
    private LocalDateTime fecha;
    
    //funciones
    public DatosPuntaje() {
    	this.fecha = LocalDateTime.now();
    }

//lista para puntaje
    public static ArrayList<DatosPuntaje> cargarPuntajeArchivo() {
        ArrayList<DatosPuntaje> puntos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PUNTAJE))) {
            while (true) {
                try {
                    DatosPuntaje puntaje = (DatosPuntaje) ois.readObject();
                    puntos.add(puntaje);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return puntos;
    }

    //guardar el puntaje creado en el archivo
    public static void guardarPuntajeArchivo(ArrayList<DatosPuntaje> puntos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PUNTAJE))) {
            for (DatosPuntaje puntaje : puntos) {
            	if (puntaje.getDate() != null) {
                    oos.writeObject(puntaje);
                }

            }
            System.out.println("Puntaje guardado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //crear archivo si no existe
    @SuppressWarnings("unused")
	private void crearArchivoPuntaje() {
        File archivo = new File(ARCHIVO_PUNTAJE);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de puntajes: " + e.getMessage());
            }
        }
    }
    

    public LocalDateTime getDate() {
        return fecha;
    }

    public void setDate(LocalDateTime date) {
        this.fecha = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}

