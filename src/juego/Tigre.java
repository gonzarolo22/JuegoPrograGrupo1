package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;


public class Tigre {
	
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private double escala;
	private Color color;
	private Image imagen;
	
	
	public Tigre(int x, double y) {
		this.x=x;
		this.y=y;
		this.ancho=100;
		this.alto=60;
		this.angulo=0;
		this.escala = 0.4;
		this.color=Color.magenta;
		this.imagen = Herramientas.cargarImagen("tigre1.gif");
}// Arbol
	
	public void info() {
		System.out.println("x="+this.x+" y="+this.y+" ancho="+this.ancho+" alto="+this.alto+" escala="+this.escala);
	}
	
	
	public static void agregaTigre( Tigre[] t,Entorno e, Suelo s) {
		Random random = new Random();
		int n =0;
		for (int i = 0; i < t.length; i++) {
			if(t[i]==null) {
				int rand = random.nextInt(100,200);
				t[i]= new Tigre(e.ancho()+rand+n,e.alto()-s.alto);
				n+=450;
			}
		}
	}// agregarTigre
	
	
	
	
	public void dibujarTigre(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imagen, x+30, y-20, angulo, escala);
	}//dibujarTigre
	
	public boolean saleDePantalla() {
		
		if (this.x < -100) {
			return true;
		}else {
			return false;
		}
	}
	
	public void desplazar() {
		this.x-=3;
	}//desplazar

	
	
	


	
	

}// class Tigre
