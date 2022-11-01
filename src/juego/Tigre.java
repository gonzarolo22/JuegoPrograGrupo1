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
            // si piedra no es null y tigre choque con piedra, esta ultima se vuelve null y retorna true
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


	public static void agregaTigre(Tigre[] t, Entorno e) {
		Random random = new Random();
		double max = e.ancho();

		for (int i = 0; i < t.length; i++) {
			// si el tigre no es null y el eje x de tigre se pasa del entorno, max se vuelve el eje de x del tigre 
			if (t[i] != null && t[i].x > max) {
				max = t[i].x;
			}
		}
		
		for (int i = 0; i < t.length; i++) {
			// si el tigre es null se crea un nuevo tigre a partir de un random limitado sumado a max 
			if (t[i] == null) {
				int rand = random.nextInt(100, 200);
				t[i] = new Tigre(rand + max, 500);
				return;
			}
		}

	}// agregarTigre

	public void dibujarTigre(Entorno e) {
//		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imagen, x + 30, y - 20, angulo, escala);
	}// dibujarTigre

	public boolean saleDePantalla() {
        //cuando el eje x pasa a ser mayor a -100 devuelve true
		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}

	public void desplazar(double v) {
		this.x -=v;
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

}// class Tigre