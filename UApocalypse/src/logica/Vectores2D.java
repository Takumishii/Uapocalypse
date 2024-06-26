package logica;

import java.io.Serializable;

public class Vectores2D implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x,y;
	
	public Vectores2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vectores2D() {
		x = 0;
		y = 0;
	}
	
	public Vectores2D sumar(Vectores2D v) {
		return new Vectores2D(x + v.getX(), y + v.getY());
	}
	
	public Vectores2D restar(Vectores2D v) {
		return new Vectores2D(x - v.getX(), y - v.getY());
	}
	
	public Vectores2D escalar(double value) {
		return new Vectores2D(x*value,y*value);
	}
	
	public void limitar(double value) {
		if(x > value)
			x = value;
		if(x < -value)
			x = -value;
		if(y > value)
			y = value;
		if(y < -value)
			y = -value;
	}
	
	public Vectores2D normaliza() {
		double magnitud = obtenerMagnitud();
		
		return new Vectores2D(x /magnitud, y / magnitud);
	}
	
	public double obtenerMagnitud() {
		return Math.sqrt(x*x + y*y);
	}
	
	public Vectores2D establecerDireccion(double angulo) {
		double magnitud = obtenerMagnitud();
		return new Vectores2D(Math.cos(angulo)*magnitud, Math.sin(angulo)*magnitud);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
