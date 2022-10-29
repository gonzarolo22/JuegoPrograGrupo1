package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Piedra {

	private double x;
	private double y;
	private double diametro;
	private Color color;
	private Image imagen;
	private double giro;
	
	public Piedra(double x, double y) {
		this.x = x;
		this.y = y;
		this.giro = 0;
		this.diametro = 20;
		this.color = Color.white;
		this.imagen = Herramientas.cargarImagen("roca.png");

	}

	public void dibujarPiedra(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
		e.dibujarImagen(imagen, x, y, giro, .15);
		this.giro += .2;
	}


	public static void agregarPiedra(Piedra[] piedra, Mono m) {
		for (int i = 0; i < piedra.length; i++) {
			if (piedra[i] == null) {
				piedra[i] = m.lanzarPiedra();
				return;
			}
		}
	}
	

	
	public boolean saleDePantalla(Entorno e) {
		if (this.x > e.ancho()) {
			return true;
		} else {
			return false;
		}
	}

	public double getDiametro() {
		return diametro;
	}

	public void avanzar() {
		this.x += 5;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
