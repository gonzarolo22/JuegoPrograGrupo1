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
		this.aguila=new Aguila[2];
		this.menu = true;

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
			entorno.escribirTexto("MENÚ", 320, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [ENTER] comenzar", 200, 100);
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				menu= false;
			}
			
		}else if (vida > 0 && !pause) {

			if (this.items.saleDePantallaP()) {
				this.items.crearPiedra();
			}
			if (this.items.saleDePantallaB()) {
				this.items.crearBananas();
			}
			if (mono.chocaConBanana(items)) {
				this.items.crearBananas();
				vida += 1;
			}
			if (mono.chocaConPiedra(items)) {
				// se crea el item piedra
				this.items.crearPiedra();
				// se reestablece la cantidad de municiones
				mono.setDisparosDisp(3);

			}
			// condicionales del salto
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				mono.aumentarTimer();

				// cuenta los saltos cada vez q se oprime la tecla
				if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
					mono.aumentarSalto();
				}

				if (mono.contadorSalto()) {
					mono.saltar(8);
				} else {
					// si se mantiene apretado se activa gravedad
					if (!mono.chocaConSuelo(entorno) && !mono.chocaConArboles(arbol)) {
						mono.gravedad();
					}
				}
			} else {
				mono.setTimer(0);
				;
				if (mono.chocaConSuelo(entorno) || mono.chocaConArboles(arbol)) {

					mono.setSalto(0);
				} else {
					mono.gravedad();
				}
			}

			// condiciones de los arboles
			for (int i = 0; i < arbol.length; i++) {
				if (arbol[i] != null) {
					arbol[i].dibujarArbol(entorno);
					arbol[i].desplazar();

					if (mono.chocaConArbol(arbol[i]) && arbol[i].isDioPuntos() == false) {
						punto += 5;
						arbol[i].setDioPuntos(true);
					}

					if (arbol[i].saleDePantalla()) {
						// si sale de la pantalla sobreescribo el arbol con uno nuevo
						arbol[i] = null;
						Arbol.crearArboles(this.arbol, entorno);
					}
				}
			}

			// condiciones de los tigres
			for (int i = 0; i < tigre.length; i++) {
				if (tigre[i] != null) {
					tigre[i].dibujarTigre(entorno);
					tigre[i].desplazar();

					if (tigre[i].saleDePantalla()) {
						tigre[i] = null;
						Tigre.agregaTigre(tigre, entorno);
					}
					if (mono.chocaConTigre(tigre[i]) && tigre[i].isPerdioVida() == false) {
						vida -= 1;
						tigre[i].setPerdioVida(true);

					}
					if (tigre[i].chocaConPiedra(piedra)) {
						tigre[i] = null;
						Tigre.agregaTigre(tigre, entorno);
					}
				}
			}
			
			// condiciones de las aguilas
						for (int i = 0; i < aguila.length; i++) {
							if (aguila[i] != null) {
								aguila[i].dibujarAguila(entorno);
								aguila[i].desplazar();
								aguila[i].descender();

								if (aguila[i].saleDePantalla()) {
									aguila[i] = null;
									Aguila.agregaAguila(aguila, entorno);
								}
								if (mono.chocaConAguila(aguila[i]) && aguila[i].isPerdioVida() == false) {
									vida -= 1;
									aguila[i].setPerdioVida(true);

								}
								if (aguila[i].chocaConPiedra(piedra)) {
									aguila[i] = null;
									Aguila.agregaAguila(aguila, entorno);
								}
							}
						}
			
			
			
			
			
			
			
			

			// condiciones de las serpientes
			for (int i = 0; i < serpiente.length; i++) {
				if (serpiente[i] != null) {
					serpiente[i].dibujarSerpiente(entorno);
					serpiente[i].desplazar();

					if (mono.chocaConSerpiente(serpiente[i]) && serpiente[i].isPerdioVida() == false) {
						vida -= 1;
						serpiente[i].setPerdioVida(true);
					}

					if (serpiente[i].chocaConPiedra(piedra) || serpiente[i].saleDePantalla()) {
						serpiente[i] = null;
						Serpiente.agregaSerpiente(this.serpiente, this.arbol);
					}

				}
			}
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

			mono.dibujarMono(entorno);
			items.dibujarPiedras(entorno);
			items.desplazarp();
			items.dibujarBananas(entorno);
			items.desplazarb();
			entorno.cambiarFont(" ", 26, Color.RED);
			entorno.escribirTexto("VIDAS: " + vida, 50, 50);
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
			entorno.escribirTexto("Presione [INICIO] para reiniciar", 160,500);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("PUNTAJE OBTENIDO: " + punto, 250, 50);
			entorno.cambiarFont(" ", 26, Color.red);
			entorno.escribirTexto("Presione [FIN] para volver al menú", 160, 550);

			if (entorno.estaPresionada(entorno.TECLA_INICIO)) {
				
				this.punto = 0;
				this.vida = 3;
				this.mono = new Mono(0, 500);
				this.piedra = new Piedra[3];
				this.items = new Items();
				this.arbol = new Arbol[5];
				this.tigre = new Tigre[2];
				this.aguila=new Aguila[2];
				this.serpiente = new Serpiente[2];

				Arbol.crearArboles(this.arbol, entorno);
				Tigre.agregaTigre(this.tigre, entorno);
				Serpiente.agregaSerpiente(this.serpiente, this.arbol);
				Aguila.agregaAguila(aguila, entorno);
				
			}
			
			if (entorno.sePresiono(entorno.TECLA_FIN)) {
				menu=true;
			}
		}
	}// fin tick()

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
