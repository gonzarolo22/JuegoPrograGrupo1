package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Items {
	private double x;
	private double y;
	private double bx;
	private double by;
	private double ancho;
	private double alto;
	private double giro;
	private Image imagenRoca;
	private Color color;
	private Image imagenBanana;
	
	public Items() {
		this.x = 2000;
		this.y =400;
		this.bx=3000;
		this.by=400;
		this.ancho = 50;
		this.alto = 50;
		this.giro = 0;
		this.imagenRoca = Herramientas.cargarImagen("rocas.png");
		this.imagenBanana = Herramientas.cargarImagen("banana.png");
		
	}
	
	public void crearPiedra() {
		Random random = new Random();
		int rand = random.nextInt(300,450);
		this.x =2000;
		this.y = rand;
	}
	public void crearBananas() {
		 Random random = new Random();
		 int rand = random.nextInt(300,450);
		 this.bx =3000;
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
		e.dibujarImagen(imagenBanana, bx, by,giro,.1);
		this.giro += 0.03;
	}
	
	public void dibujarPiedras(Entorno e) {
		e.dibujarImagen(imagenRoca, x, y, giro, .4);
		this.giro += 0.03;
	}//dibujarPiedras
	
	
	public void desplazarp() {
		this.x -=3;
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
