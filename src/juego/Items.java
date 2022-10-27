package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Items {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image imagen;
	private Color color;
	
	public Items(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 50;
		this.alto = 50;
		this.imagen = Herramientas.cargarImagen("rocas.png");
	}
	
	public void crearPiedras() {
		Random random = new Random();
		int rand = random.nextInt(300,450);
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
	
	public void dibujarPiedras(Entorno e,double giro) {
		e.dibujarRectangulo(this.x, this.y, ancho, alto, 0,Color.red);
		e.dibujarImagen(imagen, x, y, giro, .4);
	}//dibujarPiedras
	
	public void desplazar() {
		this.x -= 1.2;
	}
}
