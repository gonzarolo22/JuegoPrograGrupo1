package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;
	private Mono mono;
	private Arbol[] arbol;
	private Tigre[] tigre;
	private Serpiente[] serpiente;
	private Aguila[] aguila;
	private Piedra[] piedra;
	private Items items;
	private Selva[] selva;
	private Image gameOver;
	private Image menuImg;
	private int punto;
	private int vida;
	private boolean pause;
	private boolean menu;
	private int enemigosMuertos;
	private int nivel;

//
	public Juego() {

		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800,
				600);
		this.punto = 0;
		this.vida = 3;
		this.selva = new Selva[2];
		this.mono = new Mono(0, 500);
		this.piedra = new Piedra[3];
		this.gameOver = Herramientas.cargarImagen("game_over.jpg");
		this.menuImg = Herramientas.cargarImagen("menu.jpg");
		this.items = new Items();
		this.pause = false;
		this.arbol = new Arbol[5];
		this.tigre = new Tigre[2];
		this.serpiente = new Serpiente[2];
		this.aguila = new Aguila[2];
		this.menu = true;
		this.nivel = 1;
		this.enemigosMuertos = 0;

		// se crean el fondo de pantalla, los tigres, arboles y las serpientes
		Selva.iniciaSelva(selva);
		Arbol.crearArboles(this.arbol, entorno);
		Tigre.agregaTigre(this.tigre, entorno);
		Serpiente.agregaSerpiente(this.serpiente, this.arbol);
		Aguila.agregaAguila(this.aguila, entorno);

		// una vez q se cargan los datos se inicia el juego
		this.entorno.iniciar();
	}// juego

	public void tick() {

		Selva.dibujarFondo(selva, entorno);
		// Procesamiento de un instante de tiempo.
		if (menu) {

			entorno.dibujarImagen(this.menuImg, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
			entorno.cambiarFont(" ", 50, Color.red);
			entorno.escribirTexto("MEN�", 320, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [ENTER] comenzar", 235, 100);
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				menu = false;
			}

		} else if (vida > 0 && !pause) {

			// si la piedra sale de la pantalla se crea una nueva
			if (this.items.saleDePantallaP()) {
				this.items.crearPiedra();
			}
			// si la banana sale de la pantalla se crea una nueva
			if (this.items.saleDePantallaB()) {
				this.items.crearBananas();
			}
			// si el mono agarra la banana entonces se crea una nueva, e incrementa su vida
			// en una unidad
			if (mono.chocaConBanana(items)) {
				this.items.crearBananas();
				vida += 1;
			}
			// si el mono agarra las piedras(item) entonces se crea una nueva, e incrementa
			// sus municiones en 3 unidades
			if (mono.chocaConPiedra(items)) {
				// se crea el item piedra
				this.items.crearPiedra();
				// se reestablece la cantidad de municiones
				mono.setDisparosDisp(3);
			}

			// condicionales del salto
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				// aumenta el contador para controlar el tiempo del salto del mono
				mono.aumentarTimer();

				// cuenta los saltos cada vez q se oprime la tecla
				if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
					mono.aumentarSalto();
				}

				// aumenta el salto en 1 para no realizar mas saltos en el aire
				if (mono.contadorSalto()) {
					mono.saltar(8);
				} else {
					// si se mantiene apretado se activa gravedad
					if (!mono.chocaConSuelo() && !mono.chocaConArboles(arbol)) {
						mono.gravedad();
					}
				}
			} else {
				mono.setTimer(0);
				if (mono.chocaConSuelo() || mono.chocaConArboles(arbol)) {
					mono.setSalto(0);
				} else {
					mono.gravedad();
				}
			}

			// condiciones de los arboles
			for (int i = 0; i < arbol.length; i++) {
				if (arbol[i] != null) {

					arbol[i].dibujarArbol(entorno);

					if (nivel == 1) {
						arbol[i].desplazar(2);
					} else if (nivel == 2) {
						arbol[i].desplazar(2.5);
					} else if (nivel == 3) {
						arbol[i].desplazar(3);
					} else {
						arbol[i].desplazar(3.5);
					}

					if (mono.chocaConArbol(arbol[i]) && arbol[i].isDioPuntos() == false) {
						punto += 5;
						arbol[i].setDioPuntos(true);
					}

					if (arbol[i].saleDePantalla()) {
						arbol[i] = null;
						Arbol.crearArboles(this.arbol, entorno);
					}
				}
			} // for arboles

			// condiciones de los tigres
			for (int i = 0; i < tigre.length; i++) {
				if (tigre[i] != null) {
					tigre[i].dibujarTigre(entorno);

					if (nivel == 1) {
						tigre[i].desplazar(4);
					} else if (nivel == 2) {
						tigre[i].desplazar(5);
					} else if (nivel == 3) {
						tigre[i].desplazar(6);
					} else {
						tigre[i].desplazar(7);
					}

					if (tigre[i].saleDePantalla()) {
						tigre[i] = null;
						Tigre.agregaTigre(tigre, entorno);
					} else {

						if (mono.chocaConTigre(tigre[i]) && tigre[i].isPerdioVida() == false) {
							vida -= 1;
							tigre[i].setPerdioVida(true);
						}

						if (tigre[i].chocaConPiedra(piedra)) {
							punto += 5;
							this.enemigosMuertos++;
							tigre[i] = null;
							Tigre.agregaTigre(tigre, entorno);
						}
					}
				}
			}

			// condiciones de las serpientes
			for (int i = 0; i < serpiente.length; i++) {
				if (serpiente[i] != null) {
					serpiente[i].dibujarSerpiente(entorno);
					if (nivel == 1) {
						serpiente[i].desplazar(2);
					} else if (nivel == 2) {
						serpiente[i].desplazar(2.5);
					} else if (nivel == 3) {
						serpiente[i].desplazar(3);
					} else {
						serpiente[i].desplazar(3.5);
					}

					if (serpiente[i].saleDePantalla()) {
						serpiente[i] = null;
						Serpiente.agregaSerpiente(this.serpiente, this.arbol);
					} else {
						if (mono.chocaConSerpiente(serpiente[i]) && serpiente[i].isPerdioVida() == false) {
							vida -= 1;
							serpiente[i].setPerdioVida(true);
						}

						if (serpiente[i].chocaConPiedra(piedra)) {
							punto += 5;
							this.enemigosMuertos++;
							serpiente[i] = null;
							Serpiente.agregaSerpiente(this.serpiente, this.arbol);
						}
					}

				}
			}
			// condiciones de las aguilas
			for (int i = 0; i < aguila.length; i++) {
				if (aguila[i] != null && nivel > 2) {

					aguila[i].dibujarAguila(entorno);
					if (nivel == 1) {
						aguila[i].desplazar(6);
					} else if (nivel == 2) {
						aguila[i].desplazar(6.2);
					} else if (nivel == 3) {
						aguila[i].desplazar(6.4);
					} else {
						aguila[i].desplazar(6.6);
					}
					aguila[i].descender();

					if (aguila[i].saleDePantalla()) {
						aguila[i] = null;
						Aguila.agregaAguila(aguila, entorno);
					} else {
						if (mono.chocaConAguila(aguila[i]) && aguila[i].isPerdioVida() == false) {
							vida -= 1;
							aguila[i].setPerdioVida(true);

						}
						if (aguila[i].chocaConPiedra(piedra)) {
							punto += 10;
							this.enemigosMuertos++;
							aguila[i] = null;
							Aguila.agregaAguila(aguila, entorno);
						}
					}
				}

			}
			
			//condicional de la piedra
			for (int i = 0; i < piedra.length; i++) {
				if (piedra[i] != null) {
					piedra[i].dibujarPiedra(entorno);
					piedra[i].avanzar();
					if (piedra[i].saleDePantalla(entorno)) {
						piedra[i] = null;
					}
				}
			}

			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				if (mono.getDisparosDisp() > 0) {
					Piedra.agregarPiedra(piedra, mono);
				}
			}

			if (entorno.sePresiono(entorno.TECLA_DELETE)) {
				pause = true;
			}
			
			//condicionales de puntajes y niveles
			if (punto > 120) {
				nivel = 4;
			} else if (punto > 80) {
				nivel = 3;
			} else if (punto > 30) {
				nivel = 2;
			}

			mono.dibujarMono(entorno);
			items.dibujarPiedras(entorno);
			items.desplazarp();
			items.dibujarBananas(entorno);
			items.desplazarb();
			entorno.cambiarFont(" ", 26, Color.RED);
			entorno.escribirTexto("VIDAS: " + vida, 50, 50);
			entorno.cambiarFont(" ", 26, Color.RED);
			entorno.escribirTexto("NIVEL: " + nivel, 50, 575);
			entorno.cambiarFont(" ", 26, Color.RED);
			entorno.escribirTexto("PIEDRAS: " + mono.getDisparosDisp(), 300, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("PUNTAJE: " + punto, 600, 50);
			entorno.cambiarFont(" ", 20, Color.red);
			entorno.escribirTexto("Presione [DELETE] para PAUSE", 250, 590);
		} else if (pause) {

			entorno.cambiarFont(" ", 50, Color.red);
			entorno.escribirTexto("PAUSE", 320, 300);
			if (entorno.sePresiono(entorno.TECLA_DELETE)) {
				pause = false;
			}

		} else {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0, 1.35);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [INICIO] para reiniciar |", 15, 550);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("PUNTAJE OBTENIDO: " + punto, 250, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [FIN] para volver al men�", 390, 550);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("ENEMIGOS ABATIDOS: " + this.enemigosMuertos, 250, 80);

			if (entorno.estaPresionada(entorno.TECLA_INICIO)) {
				
				//reinicio de parametros iniciales
				this.nivel = 1;
				this.punto = 0;
				this.vida = 3;
				this.enemigosMuertos = 0;
				this.mono = new Mono(0, 500);
				this.piedra = new Piedra[3];
				this.items = new Items();
				this.arbol = new Arbol[5];
				this.tigre = new Tigre[2];
				this.aguila = new Aguila[2];
				this.serpiente = new Serpiente[2];

				Arbol.crearArboles(this.arbol, entorno);
				Tigre.agregaTigre(this.tigre, entorno);
				Serpiente.agregaSerpiente(this.serpiente, this.arbol);
				Aguila.agregaAguila(aguila, entorno);

			}

			if (entorno.sePresiono(entorno.TECLA_FIN)) {
				
				//reinicio de parametros iniciales
				menu = true;
				this.nivel = 1;
				this.punto = 0;
				this.vida = 3;
				this.enemigosMuertos = 0;
				this.mono = new Mono(0, 500);
				this.piedra = new Piedra[3];
				this.items = new Items();
				this.arbol = new Arbol[5];
				this.tigre = new Tigre[2];
				this.aguila = new Aguila[2];
				this.serpiente = new Serpiente[2];
				Arbol.crearArboles(this.arbol, entorno);
				Tigre.agregaTigre(this.tigre, entorno);
				Serpiente.agregaSerpiente(this.serpiente, this.arbol);
				Aguila.agregaAguila(aguila, entorno);
			}
		}
	}// fin tick()

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
