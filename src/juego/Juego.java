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

			// se grafican las palabras en el menu
			entorno.dibujarImagen(this.menuImg, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
			entorno.cambiarFont(" ", 50, Color.red);
			entorno.escribirTexto("MENÚ", 320, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [ENTER] comenzar", 235, 100);
			// si se oprime enter entonces se sale del menu
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
				// cuando el mono esta sobre el suelo o esta en arbol entonces puede saltar
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

					// dependiendo del nivel cambia la velocidad de desplazamiento
					if (nivel == 1) {
						arbol[i].desplazar(2);
					} else if (nivel == 2) {
						arbol[i].desplazar(2.5);
					} else if (nivel == 3) {
						arbol[i].desplazar(3);
					} else {
						arbol[i].desplazar(3.5);
					}

					// si el mono choca con el arbol y el arbol no dio punto entonces incrementa su
					// puntaje
					if (mono.chocaConArbol(arbol[i]) && arbol[i].isDioPuntos() == false) {
						punto += 5;
						arbol[i].setDioPuntos(true);
					}

					// si el arbol sale de la pantalla entonces se anula el elemento y se crea uno
					// nuevo
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

					// dependiendo del nivel cambia la velocidad de desplazamiento
					if (nivel == 1) {
						tigre[i].desplazar(4);
					} else if (nivel == 2) {
						tigre[i].desplazar(5);
					} else if (nivel == 3) {
						tigre[i].desplazar(6);
					} else {
						tigre[i].desplazar(7);
					}
					// si el tigre sale de la pantalla entonces se anula el elemento y se crea uno
					// nuevo
					if (tigre[i].saleDePantalla()) {
						tigre[i] = null;
						Tigre.agregaTigre(tigre, entorno);
					} else {

						// si el mono choca con el tigre y el mono aun no perdio vida con este tigre
						// entonces se resta una vida
						if (mono.chocaConTigre(tigre[i]) && tigre[i].isPerdioVida() == false) {
							vida -= 1;
							tigre[i].setPerdioVida(true);
						}

						// si el tigre choca con la piedra se aumenta el puntaje y aumenta la cantidad
						// de enemigos aniquilados,
						// el tigre se pone null y se instancia uno nuevo
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
					// si el tigre sale de la pantalla entonces se anula el elemento y se crea uno
					// nuevo
					if (nivel == 1) {
						serpiente[i].desplazar(2);
					} else if (nivel == 2) {
						serpiente[i].desplazar(2.5);
					} else if (nivel == 3) {
						serpiente[i].desplazar(3);
					} else {
						serpiente[i].desplazar(3.5);
					}
					// si la serpiente sale de la pantalla entonces se anula el elemento y se crea
					// uno nuevo
					if (serpiente[i].saleDePantalla()) {
						serpiente[i] = null;
						Serpiente.agregaSerpiente(this.serpiente, this.arbol);
					} else {
						// si el mono choca con la serpiente y ademas no perdio vida con esta serpiente
						// entonces pierde una vida
						if (mono.chocaConSerpiente(serpiente[i]) && serpiente[i].isPerdioVida() == false) {
							vida -= 1;
							serpiente[i].setPerdioVida(true);
						}
						// si la serpiente choca con la piedra entonces el mono obtiene 5 puntos e
						// incrementa los enemigos aniquilados
						// la serpiente se pone en null y se crea una nueva
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
					// si el aguila sale de la pantalla entonces se anula el elemento y se crea uno
					// nuevo
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
					// si el aguila sale de la pantalla entonces se anula el elemento y se crea uno
					// nuevo
					if (aguila[i].saleDePantalla()) {
						aguila[i] = null;
						Aguila.agregaAguila(aguila, entorno);
					} else {
						// si el aguila choca con la piedra entonces el mono obtiene 5 puntos e
						// incrementa los enemigos aniquilados
						// el aguila se pone en null y se crea una nueva
						if (mono.chocaConAguila(aguila[i]) && aguila[i].isPerdioVida() == false) {
							vida -= 1;
							aguila[i].setPerdioVida(true);
						}
						// si el aguila choca con la piedra entonces el mono gana puntos, incrementa los
						// enemigos aniquilados y el aguila se coloca null y se crea uno nuevo
						if (aguila[i].chocaConPiedra(piedra)) {
							punto += 10;
							this.enemigosMuertos++;
							aguila[i] = null;
							Aguila.agregaAguila(aguila, entorno);
						}
					}
				}

			}

			// condicional de la piedra
			for (int i = 0; i < piedra.length; i++) {
				//si la piedra no es null entonces la dibuja y la hace desplazar
				if (piedra[i] != null) {
					piedra[i].dibujarPiedra(entorno);
					piedra[i].avanzar();
					// si la piedra sale de la pantalla entonces se coloca nula
					if (piedra[i].saleDePantalla(entorno)) {
						piedra[i] = null;
					}
				}
			}
			//si se presiona la tecla espacio y tiene disparos disponibles entonces realiza disparo
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				if (mono.getDisparosDisp() > 0) {
					Piedra.agregarPiedra(piedra, mono);
				}
			}
			// si se oprimi la tecla supr entonces el juego se coloca en pause
			if (entorno.sePresiono(entorno.TECLA_DELETE)) {
				pause = true;
			}

			// condicionales de puntajes y niveles
			if (punto > 120) {
				nivel = 4;
			} else if (punto > 80) {
				nivel = 3;
			} else if (punto > 30) {
				nivel = 2;
			}
			
			// se grafican los objetos y las estadisticas del juego
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
			
			//se grafica el mensaje "pause"
			entorno.cambiarFont(" ", 50, Color.red);
			entorno.escribirTexto("PAUSE", 320, 300);
			if (entorno.sePresiono(entorno.TECLA_DELETE)) {
				pause = false;
			}

		} else {
			// se grafican las opciones luego de la partida junto con las estadisticas del juego
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0, 1.35);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [INICIO] para reiniciar |", 15, 550);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("PUNTAJE OBTENIDO: " + punto, 250, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [FIN] para volver al menú", 390, 550);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("ENEMIGOS ABATIDOS: " + this.enemigosMuertos, 250, 80);

			if (entorno.estaPresionada(entorno.TECLA_INICIO)) {

				// reinicio de parametros iniciales
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

				// reinicio de parametros iniciales
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
