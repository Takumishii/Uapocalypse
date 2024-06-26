package logica;

import java.io.Serializable;

public class Tiempo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1810943727044880539L;
	private long delta, ultimoTiempo, tiempo;
	private boolean funcionando;
	
	public Tiempo() {
		delta = 0;
		ultimoTiempo = 0;
		funcionando = false;
	}
	
	public void funcionando(long time) {
		funcionando = true;
		this.tiempo = time;
	}
	
	public void update() {
		if(funcionando)
			delta += System.currentTimeMillis() - ultimoTiempo;
		if(delta >= tiempo) {
			funcionando = false;
			delta = 0;
		}
		
		ultimoTiempo = System.currentTimeMillis();
	}
	
	public boolean estaFuncionando() {
		return funcionando;
	}

}
