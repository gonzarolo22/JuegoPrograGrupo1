package juego;

import entorno.Entorno;

import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;
	private Mono mono;
	private Suelo suelo;
	private Arbol[] arbol;
	private Tigre[] tigre;
	private Serpiente[] serpiente;
	private Piedra [] piedra;
	private Items itemPiedra;
	private Selva[] selva;
	private Puntaje puntaje;
	private Vidas vidas;
	private Game_over game_over;
	private Reintentar reintentar;

//
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800,
				600);

		this.selva = new Selva[2];
		this.suelo = new Suelo(entorno, entorno.ancho() / 2);
		this.mono = new Mono(0, 500);
		this.piedra=new Piedra[3];
	
		this.itemPiedra = new Items(500, 300);
		this.puntaje = new Puntaje();
		this.vidas = new Vidas();
		this.game_over = new Game_over(100);
		this.reintentar = new Reintentar();

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

	
	int salto = 0;
	int punto = 0;
	int vida = 0;
	double giro = 0;

	public void tick() {
		
	suelo.dibujarRectangulo(entorno);
	Selva.dibujarFondo(selva, entorno);
																	//		 Procesamiento de un instante de tiempo.
	if (vidas.getVidas() > 0) {
		giro += 0.03;												// variables acumuladores


	if (this.itemPiedra.saleDePantalla()) {
		this.itemPiedra.crearPiedras();
		}


																	// condicionales del doble salto
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
	if (!mono.chocaConSuelo(entorno, suelo)|| !mono.chocaConArboles(arbol)) {
		mono.gravedad();
	}
				}
	} else {
				mono.setTimer(0);;
			if (mono.chocaConSuelo(entorno, suelo)|| mono.chocaConArboles(arbol)) {
				mono.setSalto(0);
				} else {
					mono.gravedad();
					
				}
			}

			// condiciones de los arboles
			for (int i = 0; i < arbol.length; i++) {
				arbol[i].dibujarArbol(entorno);
				arbol[i].desplazar();

				if (mono.chocaConArbol(arbol[i])) {
					punto++;
					if (punto < 2) {
						puntaje.aumentaPuntos();
					}

				}

				if (arbol[i].saleDePantalla()) {
					// si sale de la pantalla sobreescribo el arbol con uno nuevo
					arbol[i] = null;
					Arbol.crearArboles(this.arbol, entorno);
					punto = 0;
				}
			}

			// condiciones de los tigres
			for (int i = 0; i < tigre.length; i++) {
				tigre[i].dibujarTigre(entorno);
				tigre[i].desplazar();

				if (tigre[i].saleDePantalla()) {
					tigre[i] = null;
					Tigre.agregaTigre(tigre, entorno, suelo);
					vida = 0;
				}
				if (mono.chocaConTigre(tigre[i])) {
					if (vida < 1) {
						vida++;
						vidas.disminuirVidas();

					}
				}
//				if (piedra.chocaConTigre(tigre[i])) {
//					tigre[i] = null;
//					Tigre.agregaTigre(tigre, entorno, suelo);
//				}
			}

			// condiciones de las serpientes
			for (int i = 0; i < serpiente.length; i++) {
				serpiente[i].dibujarSerpiente(entorno);
				serpiente[i].desplazar();

				if (serpiente[i].saleDePantalla()) {
					serpiente[i] = null;
					Serpiente.agregaSerpiente(this.serpiente, this.arbol);

				}
				if (mono.chocaConSerpiente(serpiente[i])) {
					if (vida < 1) {
						vida++;
						vidas.disminuirVidas();
					}
				}
			}

//			if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
//				piedra.lanzar(8);
//			} else { // si se presiona se seguira moviendo la piedra
//				
//			}

			for (int i = 0; i < piedra.length; i++) {
				if (piedra[i] != null) {
					piedra[i].crearPiedra(entorno);
					piedra[i].lanzar();
				}
			}
			
			if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				Piedra.agregarPiedra(piedra, mono);
			}
			


			
			mono.dibujarMono(entorno);
			itemPiedra.dibujarPiedras(entorno, giro);
			itemPiedra.desplazar();
			puntaje.cambiarPuntaje(entorno);
			puntaje.escribirPuntaje(entorno);
			vidas.cambiarVida(entorno);
			vidas.escribirVida(entorno);
		} else {
			game_over.dibujarOver(entorno);
			reintentar.cambiarReintentar(entorno);
			reintentar.escribirReintentar(entorno);
			if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
				vidas.setVidas(3);
				punto = 0;
			}
		}

	}// fin tick()

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
