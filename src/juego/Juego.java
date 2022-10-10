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
	private Arbol[] arbol;
	
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800, 600);
		//aca va las cosas a inicialisar.....
		this.entorno.iniciar();
		this.suelo= new Suelo(entorno,entorno.ancho()/2); 
		this.mono= new Mono(0,entorno.alto()-suelo.alto);
		
		// se crea un arreglo de x arboles
		this.arbol = new Arbol[50];
		
		int k=0;
		for (int i = 0; i < arbol.length; i++) {
			// se crea un altura aleatoria para las ramas de los arboles
			Random rand1 = new Random();
			double rand = rand1.nextDouble(200) + 150;
			//a cada elemento se le asigna una clase
			arbol[i]= new Arbol(entorno.ancho()+k,rand);
			k+=250;
		}
		
		
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
		
		for (int i = 0; i < arbol.length; i++) {
			arbol[i].dibujarArbol(entorno);
			arbol[i].desplazar();
		}
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
