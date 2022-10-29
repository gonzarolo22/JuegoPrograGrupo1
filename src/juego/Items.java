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
	private double giro;
	private Image imagen1;
	private double bx;
	private double by;
	
	public Items(int x, int y, int bx, int by) {
		this.x = x;
		this.y = y;
		this.by=by;
		this.bx=bx;
		this.ancho = 50;
		this.alto = 50;
		this.giro = 0;
		this.imagen = Herramientas.cargarImagen("rocas.png");
		this.imagen1 = Herramientas.cargarImagen("banana.png");
		
	}
	
	public void crearPiedra() {
		Random random = new Random();
		int rand = random.nextInt(300,450);
		this.x =2000;
		this.y = rand;
	}
	public void crearBananas(Items items) {
		 Random random = new Random();
		 int rand = random.nextInt(300,450);
		 this.bx =2000;
		 this.by = rand;
	}
	
	public boolean saleDePantallaP() {
		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}
	public boolean saleDePantallaB() {
		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}
	public void dibujarBananas(Entorno e) {
		e.dibujarRectangulo(this.bx, this.by, ancho, alto, 0,Color.red);
		e.dibujarImagen(imagen1, bx, by,giro,.1);
		this.giro += 0.03;
	}
	
	public void dibujarPiedras(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, ancho, alto, 0,Color.red);
		e.dibujarImagen(imagen, x, y, giro, .4);
		this.giro += 0.03;
	}//dibujarPiedras
	
	
	public void desplazarp() {
		this.x -=2;
	}
	public void desplazarb() {
		this.bx -=3;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public double getBx() {
		return bx;
	}

	public double getBy() {
		return by;
	}
	
	
}
