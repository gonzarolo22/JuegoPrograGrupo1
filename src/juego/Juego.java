package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	
	private Entorno entorno;
	private Rectangulo rectangulo;
	private Mono mono;
	
	public Juego() {
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N - Apellido1 - Apellido2 -Apellido3 - V0.01", 800, 600);
		//aca va las cosas a inicialisar.....
		this.rectangulo= new Rectangulo(0,600);
		this.entorno.iniciar();
		this.mono= new Mono(0,entorno.alto()-mono.largo);
		
	}

	
	 
	public void tick() {
		// Procesamiento de un instante de tiempo
		
		mono.dibujarMono(entorno);
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
			mono.saltar(entorno);
		}
	
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
