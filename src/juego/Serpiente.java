package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Serpiente {

	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Color color;
	private double escala;
	private Image imagen;
	private boolean perdioVida;

	public Serpiente(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 40;
		this.alto = 50;
		this.angulo = 0;
		this.color = Color.CYAN;
		this.escala = .3;
		this.imagen = Herramientas.cargarImagen("serpiente.gif");
		this.perdioVida = false;
	}// Arbol


	public static Serpiente[] agregaSerpiente(Serpiente[] s, Arbol[] arbol) {
		Random random = new Random();

		for (int i = 0; i < s.length; i++) {
			// elijo un arbol aleatorio disponible
			int rand = random.nextInt(arbol.length);
			
			//se debe elegir una arbol q no este en pantalla para lograr colocar serpiente
			while (arbol[rand].getX() < 800) {
				rand = random.nextInt(arbol.length);
			}
			if (s[i] == null && !arbol[rand].isConSerpiente()) {
				// se suma 30 para q la serpiente quede al borde de la rama, y se resta 25 para
				// que quede centrada en el arbol
				s[i] = new Serpiente(arbol[rand].getX() + 30, arbol[rand].getY() - 25);
				arbol[rand].setConSerpiente(true);
			}
		}
		return s;
	}

	public boolean chocaConPiedra(Piedra[] piedra) {
		for (int i = 0; i < piedra.length; i++) {

			if (piedra[i] != null && piedra[i].getX() - piedra[i].getDiametro() / 2 < x + ancho / 2							// cuando las dimensiones de nuestro objeto choquen con el objeto pasado como parametro y ese mismo objeto no sea nulo, retornamos true
					&& x - ancho / 2 < piedra[i].getX() + piedra[i].getDiametro() / 2 && piedra[i].getY() < y + alto / 2
					&& y - alto / 2 < piedra[i].getY()) {
				piedra[i] = null;																							// volvemos nula la piedra iterada 												
				return true;
			}
		}
		return false;
	}

	public void dibujarSerpiente(Entorno e) {
		e.dibujarImagen(imagen, x, y, angulo, escala);   //graficamos en el entorno el objeto
	}// dibujarSerpiente

	public boolean saleDePantalla() {
											//si el eje X es menos a -100 retorna true sino retorna false
		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}

	public void desplazar(double v) {		// restamos al eje X el valor pasado como parametro
		this.x -= v;
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

}// class Serpiente