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
	private Tigre[] tigre;
	private Serpiente[] serpiente;
	private Piedra piedra;
	
	public Juego() {
		this.entorno = new Entorno(this, "Escape del mono - Grupo 1 - Correa A - Rolon G - Bentacor L - V0.01", 800, 600);
		//aca va las cosas a inicialisar.....
		this.entorno.iniciar();
		this.suelo= new Suelo(entorno,entorno.ancho()/2); 
		this.mono= new Mono(0,entorno.alto()-suelo.alto);
		this.piedra=new Piedra(50,mono.getY());
		
		// se crea un arreglo de x arboles
		this.arbol = new Arbol[50];
		this.tigre = new Tigre[50];
		this.serpiente = new Serpiente[40];
		
		int k=0;
		int n =0;
		for (int i = 0; i < arbol.length; i++) {
			// se crea un altura aleatoria para las ramas de los arboles
			Random random = new Random();
			int rand = random.nextInt(200,350);
			int rand2 = random.nextInt(100,300) ;
			
			
			//a cada elemento se le asigna una clase
			arbol[i]= new Arbol(entorno.ancho()+n+rand2 , rand);	
			tigre[i] = new Tigre(entorno.ancho()+k+rand,entorno.alto()-suelo.alto);
			k+=400; //tigre
			n+=250; // arbol
			
		}// for
		for (int i = 0; i < serpiente.length; i++) {
			Random random = new Random();
			int rand = random.nextInt(serpiente.length);
			
			if(rand %2 ==0) {
				serpiente[i]= new Serpiente(arbol[rand].getX(),arbol[rand].getY());
			}else {
				serpiente[i]= new Serpiente(arbol[rand+1].getX(),arbol[rand+1].getY());
			}			
		}
		
	
	}

	
	int timer = 0;
	int salto =0;
	
	public void tick() {
		// Procesamiento de un instante de tiempo.
		
		suelo.dibujarRectangulo(entorno);
		mono.dibujarMono(entorno);
		piedra.CrearPiedra(entorno);
		
		
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA) ) {	
			timer++;
			
			//cuenta los saltos cada vez q se oprime la tecla
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				salto++;
			}
			
			if(timer<25 && salto <3) {				
				mono.saltar(8);
				if(piedra.getX()<=50) { //cuando la piedra es lanzada no sera afecta por el salto del mono
					  piedra.saltar(8);
		}
			}else {
				//si se mantiene apretado se activa gravedad
				if (mono.chocaConSuelo(entorno, suelo)==false) {
				    mono.gravedad(3);
				    if(piedra.getX()<=50) { //cuando la piedra es lanzada no sera afectada por la gravedad del mono
						   piedra.gravedad(3);
				    }
			}
			}
			
		}else {			
			timer=0;
			if(mono.chocaConSuelo(entorno, suelo)) {
				mono.gravedad(0);
				piedra.gravedad(0);
				salto = 0;
			}else {
				mono.gravedad(3);	
				if(piedra.getX()<=50) { // cuando la piedra es lanzada no sera afectada por la gravedad del mono
					piedra.gravedad(3);

			}
			
			
			
		}
		} 
		for (int i = 0; i < arbol.length; i++) {
			arbol[i].dibujarArbol(entorno);
			arbol[i].desplazar();
			
			tigre[i].dibujarTigre(entorno);
			tigre[i].desplazar();
		}
		
		for (int i = 0; i < serpiente.length; i++) {
			serpiente[i].dibujarSerpiente(entorno);
			serpiente[i].desplazar();
		}
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) ) {	
			piedra.lanzar(8);
			}else { // si se presiona se seguira moviendo la piedra
				if(piedra.getX()>50) {
					piedra.lanzar(8);
				}
			}
			}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
