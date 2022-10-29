package juego;

import java.awt.Color;
import java.awt.Image;
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
	private Piedra[] piedra;
	private Items itemPiedra;
	private Selva[] selva;
	private Image fondo;

//
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800,
				600);

		this.selva = new Selva[2];
		this.suelo = new Suelo(entorno, entorno.ancho() / 2);
		this.mono = new Mono(0, 500);
		this.piedra = new Piedra[3];
		this.fondo = Herramientas.cargarImagen("Game_over.jpg");

		this.itemPiedra = new Items(500, 300);

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
	int vida = 3;
	double giro = 0;

	public void tick() {

		suelo.dibujarRectangulo(entorno);
		Selva.dibujarFondo(selva, entorno);
		// Procesamiento de un instante de tiempo.
		if (vida > 0) {
			giro += 0.03; // variables acumuladores

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
					if (!mono.chocaConSuelo(entorno, suelo) && !mono.chocaConArboles(arbol)) {
						mono.gravedad();
					}
				}
			} else {
				mono.setTimer(0);
				;
				if (mono.chocaConSuelo(entorno, suelo) || mono.chocaConArboles(arbol)) {

					mono.setSalto(0);
				} else {
					mono.gravedad();
				}
			}

			// condiciones de los arboles
			for (int i = 0; i < arbol.length; i++) {
				if(arbol[i]!=null) {
				arbol[i].dibujarArbol(entorno);
				arbol[i].desplazar();

				if (mono.chocaConArbol(arbol[i]) && arbol[i].isDioPuntos()==false) {
					punto+=5;
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
				if(tigre[i]!=null) {
				tigre[i].dibujarTigre(entorno);
				tigre[i].desplazar();

				if (tigre[i].saleDePantalla()) {
					tigre[i] = null;
					Tigre.agregaTigre(tigre, entorno, suelo);
				}
				if (mono.chocaConTigre(tigre[i]) && tigre[i].isPerdioVida()==false) {
						vida-=1;
						tigre[i].setPerdioVida(true);
					
				}
				if (tigre[i].chocaConPiedra(piedra)) {
					tigre[i]=null;
					Tigre.agregaTigre(tigre, entorno, suelo);
				}
				}
			}

			// condiciones de las serpientes
			for (int i = 0; i < serpiente.length; i++) {
				if(serpiente[i]!=null) {
				serpiente[i].dibujarSerpiente(entorno);
				serpiente[i].desplazar();

				if (serpiente[i].saleDePantalla()) {
					serpiente[i] = null;
					Serpiente.agregaSerpiente(this.serpiente, this.arbol);
				}

				if(mono.chocaConSerpiente(serpiente[i]) && serpiente[i].isPerdioVida()==false) {
					vida-=1;
					serpiente[i].setPerdioVida(true);
				}
				
				
				if (serpiente[i].chocaConPiedra(piedra)) {
					serpiente[i]=null;
					Serpiente.agregaSerpiente(this.serpiente, this.arbol);
				}}
				}
			for (int i = 0; i < piedra.length; i++) {
				if (piedra[i] != null) {
					piedra[i].dibujarPiedra(entorno);
					piedra[i].avanzar();
					if(piedra[i].saleDePantalla(entorno)){
						piedra[i]=null;
					}
				}
			}

			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				if(mono.getDisparosDisp()>0) {
				Piedra.agregarPiedra(piedra, mono);
				
				}
			}

			mono.dibujarMono(entorno);
			itemPiedra.dibujarPiedras(entorno, giro);
			itemPiedra.desplazar();
			entorno.cambiarFont(" ", 25, Color.red);
			entorno.escribirTexto("puntaje:"+punto,600,50) ;
			entorno.cambiarFont(" ", 25, Color.RED);
			entorno.escribirTexto("vidas:"+vida,50,50) ;
		} else {
			entorno.dibujarImagen(fondo,entorno.ancho()/2,entorno.alto()/2, 0, 1);
			entorno.cambiarFont(" ", 35, Color.red);
			entorno.escribirTexto("apreta la tecla [ENTER] para volver a empezar",50,500) ;
			if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
				vida+=3;
				punto = 0;
				mono.setDisparosDisp(3);
				for (int i=0;i<tigre.length;i++) {
				if(tigre[i]!=null) {
					tigre[i] = null;
					Tigre.agregaTigre(tigre, entorno, suelo);
				}}
				for ( int i=0;i<serpiente.length;i++) {
					if(serpiente[i]!=null) {
						serpiente[i] = null;
						Serpiente.agregaSerpiente(this.serpiente, this.arbol);
					}}
		}
		}
	}// fin tick()

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
