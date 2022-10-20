package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;


public class Arbol {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private double escala;
	private Image imagen;
	
	
	public Arbol(int x, double y, double escala) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=5;
		this.angulo=0;
		this.color=Color.GRAY;
		this.escala = escala;
		this.imagen = Herramientas.cargarImagen("arbol.png");
}// Arbol
	
	public void info() {
		System.out.println("x="+this.x+" y="+this.y+" ancho="+this.ancho+" alto="+this.alto+" escala="+this.escala);
	}
	

	public void dibujarArbol(Entorno e) {
		e.dibujarImagen(imagen, this.x, this.y, angulo, this.escala);
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
	}//crearArbol
	
	
	
	public static Arbol agregarArbol(Entorno e) {
		Random random = new Random();
		// se crea un altura aleatoria para las ramas de los arboles
		int rand = random.nextInt(300,350);
		//se crea las distancia para los arboles fuera de la pantalla (lado derecho)
		int rand2 = random.nextInt(1000);
		//escala para los arboles
		double escala = Math.round(random.nextDouble(0.08,0.12)*100.0)/100.0;

		return new Arbol(e.ancho()+rand2 , rand, escala);
		
	}//agregaArbol
	
	
	public static Arbol[] crearArboles(Arbol[] arboles, Entorno e) {
				int n =0;
				for (int i = 0; i < arboles.length; i++) {
					if(arboles[i]==null) {	
						 arboles[i] = Arbol.agregarArbol(e);
					}
					n+=250;
				}// for
				n=0;
				return arboles;
	}//crearArboles
	
	
	public boolean saleDePantalla() {
	
		if (this.x < -100) {
			return true;
		}else {
			return false;
		}
	}
	

	public void desplazar() {
		this.x-=2;
	}//desplazar

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
	

}// class Arbol
