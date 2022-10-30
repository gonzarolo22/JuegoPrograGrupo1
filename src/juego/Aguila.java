package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Aguila {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private double escala;
	private Color color;
	private Image imagen;
	private boolean perdioVida;

	public Aguila(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 90;
		this.alto = 70;
		this.angulo = 0;
		this.escala = 0.7;
		this.color = Color.magenta;
		this.imagen = Herramientas.cargarImagen("aguila.gif");
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


	public static void agregaAguila(Aguila[] t, Entorno e) {
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
				t[i] = new Aguila(1000, 100);
				return;
			}
		}

	}// agregarTigre
	
	public void descender() {
		if(x<700 && x>400) {
			this.y+=5;
		}
	}

	public void dibujarAguila(Entorno e) {
	//e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imagen, x , y, angulo, escala);
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

}
