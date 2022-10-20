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
	
	
	public Serpiente(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 40;
		this.alto = 50;
		this.angulo = 0;
		this.color = Color.CYAN;
		this.escala = .3;
		this.imagen = Herramientas.cargarImagen("serpiente.gif");
	}// Arbol

	public void info() {
		System.out.println("x=" + this.x + " y=" + this.y + " ancho=" + this.ancho + " alto=" + this.alto + " escala="
				+ this.escala);
	}

	public static Serpiente[] agregaSerpiente(Serpiente[] s, Arbol[] arbol) {
		Random random = new Random();
		
		for (int i = 0; i < s.length; i++) {
			// elijo un arbol aleatorio disponible
			int rand = random.nextInt(arbol.length);
			
			while(arbol[rand].getX()<800) {
				rand = random.nextInt(arbol.length);
			}
			
			if(s[i] == null ) {

				//se suma 30 para q la serpiente quede al borde de la rama
				s[i] = new Serpiente(arbol[rand].getX()+30, arbol[rand].getY());
			}
		}
		return s;
	}

	public void dibujarSerpiente(Entorno e) {
		e.dibujarRectangulo(this.x, this.y - this.alto / 2, this.ancho, this.alto, this.angulo, this.color);
		e.dibujarImagen(imagen, x, y-25, angulo, escala);
	}// dibujarSerpiente

	public boolean saleDePantalla() {

		if (this.x < -100) {
			return true;
		} else {
			return false;
		}
	}

	public void desplazar() {
		this.x -=1.2 ;
	}// desplazar

	public void mostrar() {
		System.out.println(this.x);
	}

}// class Tigre
