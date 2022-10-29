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
	private boolean perdioVida;

	public Tigre(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 80;
		this.alto = 60;
		this.angulo = 0;
		this.escala = 0.4;
		this.color = Color.magenta;
		this.imagen = Herramientas.cargarImagen("tigre1.gif");
		this.perdioVida = false;
	}// Arbol

	public boolean chocaConPiedra(Piedra[] piedra) {
		for (int i = 0; i < piedra.length; i++) {

			if (piedra[i] != null && piedra[i].getX() - piedra[i].getDiametro() / 2 < x + ancho / 2
					&& x - ancho / 2 < piedra[i].getX() + piedra[i].getDiametro() / 2 &&
					piedra[i].getY() < y + alto / 2 && y - alto / 2 < piedra[i].getY())
			{
				piedra[i] = null;
				return true;
			}
		}
		return false;
	}

	public void info() {
		System.out.println("x=" + this.x + " y=" + this.y + " ancho=" + this.ancho + " alto=" + this.alto + " escala="
				+ this.escala);
	}

	public static void agregaTigre(Tigre[] t, Entorno e) {
		Random random = new Random();
		double max = e.ancho();

		for (int i = 0; i < t.length; i++) {
			if (t[i] != null && t[i].x > max) {
				max = t[i].x;
			}
		}
		for (int i = 0; i < t.length; i++) {
			if (t[i] == null) {
				int rand = random.nextInt(100, 200);
				t[i] = new Tigre(rand + max, 500);
				return;
			}
		}

	}// agregarTigre

	public void dibujarTigre(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imagen, x + 30, y - 20, angulo, escala);
	}// dibujarTigre

	public boolean saleDePantalla() {

		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}

	public void desplazar() {
		this.x -= 5;
	}// desplazar

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

	public boolean isPerdioVida() {
		return perdioVida;
	}

	public void setPerdioVida(boolean perdioVida) {
		this.perdioVida = perdioVida;
	}

}// class Tigre
