package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;
	private Mono mono;
	private Suelo suelo;
	private Arbol[] arbol;
	private Tigre[] tigre;
	private Serpiente[] serpiente;
	private Piedra piedra;
	private Selva[] selva;
//
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800,
				600);

		// aca va las cosas a inicializar.....

		this.selva = new Selva[2];
		this.suelo = new Suelo(entorno, entorno.ancho() / 2);
		this.mono = new Mono(0, entorno.alto() - suelo.alto);
		this.piedra = new Piedra(50, mono.getX());

		// se crea un arreglo de x arboles
		this.arbol = new Arbol[5];
		this.tigre = new Tigre[2];
		this.serpiente = new Serpiente[2];

		Selva.iniciaSelva(selva);

		Arbol.crearArboles(this.arbol, entorno);

		Tigre.agregaTigre(this.tigre, entorno, suelo);

		Serpiente.agregaSerpiente(this.serpiente, this.arbol);

		// una vez q se cargan los datos se inicia el juego
		this.entorno.iniciar();
	}// juego

	int timer = 0;
	int salto = 0;

	public void tick() {
//		// Procesamiento de un instante de tiempo.

		suelo.dibujarRectangulo(entorno);
//	
		// si la primera imagen sale de la pantalla vuelve al final de la segunda imagen
		if (selva[1].getX() == 0) {
			selva[0].setX(2400);
		} else if (selva[0].getX() == 0) {
			selva[1].setX(2400);
		}
		// se muestran ambas imagenes a la vez
		selva[1].dibujarFondo(entorno);
		selva[1].avanzarFondo(1);
		selva[0].dibujarFondo(entorno);
		selva[0].avanzarFondo(1);

//		piedra.CrearPiedra(entorno);
//
//		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			timer++;

			// cuenta los saltos cada vez q se oprime la tecla
			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				salto++;
			}

			if (timer < 25 && salto < 3) {
				mono.saltar(8);
//				if (piedra.getX() <= 50) { // cuando la piedra es lanzada no sera afecta por el salto del mono
//					piedra.saltar(8);
//				}
			} else {
				// si se mantiene apretado se activa gravedad
				if (mono.chocaConSuelo(entorno, suelo) == false) {
					mono.gravedad();
//					if (piedra.getX() <= 50) { // cuando la piedra es lanzada no sera afectada por la gravedad del mono
//						piedra.gravedad(3);
//					}
				}
			}
		} else {
			timer = 0;
			if (mono.chocaConSuelo(entorno, suelo)) {
				salto = 0;
			} else {
				mono.gravedad();
				if (piedra.getX() <= 50) { // cuando la piedra es lanzada no sera afectada por la gravedad del mono
					piedra.gravedad(3);
				}
			}
		}

		// condiciones de los arboles
		for (int i = 0; i < arbol.length; i++) {
			arbol[i].dibujarArbol(entorno);
			arbol[i].desplazar();
			
			if (mono.chocaConArbol(arbol[i])) {
	
				mono.monoEnArbol(arbol[i]);
			}

			if (arbol[i].saleDePantalla()) {
				// si sale de la pantalla sobreescribo el arbol con uno nuevo
				arbol[i] = null;
				Arbol.crearArboles(this.arbol, entorno);
			}
		}

		// condiciones de los tigres
		for (int i = 0; i < tigre.length; i++) {
			tigre[i].dibujarTigre(entorno);
			tigre[i].desplazar();
			if (tigre[i].saleDePantalla()) {
				tigre[i] = null;
				Tigre.agregaTigre(tigre, entorno, suelo);
			}
		}
		mono.dibujarMono(entorno);

		for (int i = 0; i < serpiente.length; i++) {
			serpiente[i].dibujarSerpiente(entorno);
			serpiente[i].desplazar();

			if (serpiente[i].saleDePantalla()) {
				serpiente[i] = null;
				Serpiente.agregaSerpiente(this.serpiente, this.arbol);
			}
		}

//		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) ) {	
//			piedra.lanzar(8);
//			}else { // si se presiona se seguira moviendo la piedra
//				if(piedra.getX()>50) {
//					piedra.lanzar(8);
//				}
//			}
//		

	}// fin tick()

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
