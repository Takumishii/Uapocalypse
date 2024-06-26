package logica;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import persistencia.DatosPuntaje;
import vista.Graficos;

public class Juego extends Estados{
	
	private Aldeano player;
	private ArrayList<ObjetosMovibles> movingObjects = new ArrayList<ObjetosMovibles>();
	private int score = 0;
	private int lives = 3;
	private Estados currentState;
    public Juego() {
		player = new Aldeano(new Vectores2D(Constantes.ANCHO/2 - Graficos.jugador.getWidth()/2,
				Constantes.ALTO/2 - Graficos.jugador.getHeight()/2), new Vectores2D(), Constantes.JUGADOR_MAX_RAPIDEZ, Graficos.jugador, this);
		movingObjects.add(player);
		new PantallaPuntaje();
	}
	
	public void addScore(int value) {
		score += value;
	}
	public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
    
    
    public void addScoreToHighScores(int score) {
        DatosPuntaje scoreData = new DatosPuntaje();
        scoreData.setScore(score);

        ArrayList<DatosPuntaje> scores = DatosPuntaje.cargarPuntajeArchivo();
        scores.add(scoreData);

        DatosPuntaje.guardarPuntajeArchivo(scores);
    }

    
    
    
	public void addRandomEnemy() {
        double x = Math.random() * Constantes.ANCHO;
        double y = Math.random() * Constantes.ALTO;
        Vectores2D enemyPosition = new Vectores2D(x, y);

        Infectado enemy = new Infectado(enemyPosition, enemyPosition, Constantes.ENEMIGO_MAX_VEL, Graficos.enemigo, this);
        enemy.setTarget(player.getPosition());

        movingObjects.add(enemy);
    }
	
	public Vectores2D getPlayerPosition() {
	    return player.getPosition();
	}
	
	public void update() {
		
		for (int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).update();
		
		if (Math.random() < Constantes.ENEMIGO_SPAWN_PROBABILIDAD) {
            addRandomEnemy();
        }
	}
	
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for (int i = 0; i < movingObjects.size(); i++) 
			movingObjects.get(i).dibujar(g);
		
		drawScore(g);
		drawLives(g);
	}
	
	private void drawScore(Graphics g) {
		Vectores2D pos = new Vectores2D(700, 25);
		
		String scoreToString = Integer.toString(score);
		
		for(int i = 0; i < scoreToString.length();i++) {
			g.drawImage(Graficos.numeros[Integer.parseInt(scoreToString.substring(i, i + 1))], (int)pos.getX(), (int)pos.getY(), null);
			pos.setX(pos.getX() + 20);
		}
	}
	private void drawLives(Graphics g){
		
		
		Vectores2D livePosition = new Vectores2D(50, 50);
		
		g.drawImage(Graficos.vida, (int)livePosition.getX(), (int)livePosition.getY(), null);
		
		g.drawImage(Graficos.numeros[10], (int)livePosition.getX() + 40,
				(int)livePosition.getY() + 5, null);
		
		String livesToString = Integer.toString(lives);
		
		Vectores2D pos = new Vectores2D(livePosition.getX(), livePosition.getY());
		
		for(int i = 0; i < livesToString.length(); i ++)
		{
			int number = Integer.parseInt(livesToString.substring(i, i+1));
			
			if(number <= 0)
				break;
			g.drawImage(Graficos.numeros[number],
					(int)pos.getX() + 60, (int)pos.getY() + 5, null);
			pos.setX(pos.getX() + 20);
		}
		
	}

	public ArrayList<ObjetosMovibles> obtenerObjetoMovible() {
		return movingObjects;
	}

	public void subtractLife() {
		lives --;
		if (lives <= 0) {
            // If no lives remaining, set game over and return to main menu
			addScoreToHighScores(score);
            currentState = new PantallaPuntaje(); // Replace MenuState with your actual menu state class
            Estados.cambiarEstado(currentState);
        }
	}

	@Override
	public void init() {
		borrarBotones();
		
	}
	
	
}
