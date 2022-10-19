package juego;

import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	
	private Entorno entorno;
	private Mono mono;
	private Suelo suelo;
	private Piedra piedra;
	private Selva selva;
	private GrupoSerpientes serpientes;
	
	public Juego() {
		//aca va las cosas a inicialisar.....
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800, 600);
		this.entorno.iniciar();
		this.suelo= new Suelo(); 
		this.mono= new Mono(300,entorno.alto()-suelo.alto);
		this.selva=new Selva();
		this.piedra=new Piedra(50,mono.getY());
		this.serpientes=new GrupoSerpientes();
	}
		
	
	

	
	int timer = 0;
	int salto =0;
	
	public void tick() {
		// Procesamiento de un instante de tiempo.
		
		suelo.dibujarRectangulo(entorno);
		
		mono.dibujarMono(entorno);
		
		piedra.CrearPiedra(entorno);
		
		selva.movimientoSelva(entorno);
		
		serpientes.recorrerSelva(selva, entorno);
		
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) ) {
			mono.saltar();
		
		}
		else if(!entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				mono.gravedad(entorno);
		}
		
		for(int i=0; i<selva.arboles.length;i++) {
		if(mono.chocaConArbol(selva.arboles[i])) {
			mono.monoEnArbol(selva.arboles[i]);
		}
		}
		
		
		
		
		
		
			
		
		
	
}
		
		
		
		
		
		
		
		
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
