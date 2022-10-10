package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	
	private Entorno entorno;
	
	private Mono mono;
	private Suelo suelo;
	
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800, 600);
		//aca va las cosas a inicialisar.....
		this.entorno.iniciar();
		this.suelo= new Suelo(entorno,entorno.ancho()/2); 
		this.mono= new Mono(0,entorno.alto()-suelo.alto);
		
		
	}

	int i = 0;
	 
	public void tick() {
		// Procesamiento de un instante de tiempo.
		
		suelo.dibujarRectangulo(entorno);
		mono.dibujarMono(entorno);
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {			
			mono.saltar(entorno);
		}else {
			mono.gravedad(2);
		}
		
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
