package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mono {
	private double x;
	private double y;
	private int ancho;
	private int largo;
	private int angulo;
	private Color color;
	private Image imagen;

	// prueba

	public Mono(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 50;
		this.largo = 60;
		this.angulo = 0;
		this.color = Color.white;
		this.imagen = Herramientas.cargarImagen("mono.gif");

	}

	public boolean chocaConArbol(Arbol a) {
		
		if ( x + ancho / 2 > a.getX() - a.getAncho() / 2&& x - ancho / 2 < a.getX() + a.getAncho() / 2 &&

				a.getY() - a.getAlto() / 2 >= y + largo / 2 && y - largo / 2 > a.getY()-largo-5 ) {
//			
			return true;
		} else {
			return false;
		}
	}

	public void monoEnArbol(Arbol a) {
		this.y = a.getY();
	}

	public void dibujarMono(Entorno e) {
		e.dibujarRectangulo(this.x + this.ancho, this.y, this.ancho, this.largo, this.angulo, this.color);
		
		e.dibujarImagen(imagen, x + this.ancho, y - 15, angulo, .3);
	}

	public void saltar(int s) {
		this.y -= s;
	}// saltar

	public void gravedad() {
		this.y += 4.5;
	}

	public boolean chocaConSuelo(Entorno e, Suelo s) {
		if (this.y > e.alto() - s.alto) {
			return true;
		} else {
			return false;
		}
	}// chocaConSuelo

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}// class Mono
