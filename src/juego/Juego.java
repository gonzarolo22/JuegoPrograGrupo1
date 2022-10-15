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
	private Tigre[] tigre;
	private Piedra piedra;
	private Selva selva;
	private GrupoSerpientes serpientes;
	
	public Juego() {
		//aca va las cosas a inicialisar.....
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800, 600);
		this.entorno.iniciar();
		this.suelo= new Suelo(); 
		this.mono= new Mono(0,entorno.alto()-suelo.alto);
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
		
		for(int i=0;i<selva.arboles.length;i++) {
		serpientes.movimientoSerpientes(entorno,selva.arboles[i]);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) ) {	
			timer++;
			
			//cuenta los saltos cada vez q se oprime la tecla
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				salto++;
			}
			
			if(timer<25 && salto <3) {				
				mono.saltar(8);
				piedra.saltar(8);
			}else {
				//si se mantiene apretado se activa gravedad
				mono.gravedad(3);
				piedra.gravedad(3);
			}
			
		}else {			
			timer=0;
			if(mono.chocaConSuelo(entorno, suelo)) {
				mono.gravedad(0);
				piedra.gravedad(0);
				salto = 0;
			}else {
				mono.gravedad(3);	
				piedra.gravedad(3);
			}
			
			
			
		}
		
		
			
			
		
		
	
}
		
		
		
		
		
		
		
		
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
