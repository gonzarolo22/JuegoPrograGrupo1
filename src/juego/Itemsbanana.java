package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Itemsbanana {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imagen;
	private Color color;
	private double giro;
	
	public Itemsbanana(int x, int y) {
		this.x = x;
		this.y = x;
		this.ancho = 50;
		this.alto = 50;
		this.imagen = Herramientas.cargarImagen("banana.png");
		this.giro=0;
	}
	public void crearBananas(Itemsbanana banana) {
		 Random random = new Random();
		 int rand = random.nextInt(200,500);
		 this.x =2000;
		 this.y = rand;
	}
	public boolean saleDePantalla() {
		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}
	public void dibujarBananas(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, ancho, alto, 0,Color.red);
		e.dibujarImagen(imagen, x, y,giro,.1);
		this.giro += 0.03;
	}//dibujarPiedras
	
	public void desplazar() {
		this.x -= 2;
	}
	public double getAncho() {
		return ancho;
	}
	public double getAlto() {
		return alto;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	

}
