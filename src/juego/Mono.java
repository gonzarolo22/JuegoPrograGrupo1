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
	private int timer;
	private int salto;
	private int disparosDisp;


	// prueba

	public Mono(int x, int y) {
		this.x = x + this.ancho + 80;
		this.y = y;
		this.ancho = 50;
		this.largo = 60;
		this.angulo = 0;
		this.color = Color.white;
		this.imagen = Herramientas.cargarImagen("mono.gif");
		this.timer = 0;
		this.salto = 0;
		this.disparosDisp = 3;
	

	}

	public int getDisparosDisp() {
		return disparosDisp;
	}

	public void setDisparosDisp(int disparosDisp) {
		this.disparosDisp = disparosDisp;
	}

	public void aumentarSalto() {
		salto++;
	}

	public void aumentarTimer() {
		timer++;
	}

	public boolean contadorSalto() {
		//si timer es menor a 30 y salto es menor a 2 devuelve true
		if (timer < 30 && salto < 2) {
			return true;
		}
		return false;
	}

	public boolean chocaConSerpiente(Serpiente s) {
		// si el mono choca con serpiente devuelve true
		if (x + ancho / 2 > s.getX() - s.getAncho() / 2 && x - ancho / 2 < s.getX() + s.getAncho() / 2
				&& s.getY() - s.getAlto() / 2 < y + largo / 2 && y - largo / 2 < s.getY() + s.getAlto() / 2) {
			return true;
		}
		return false;
	}
	// si el mono choca con aguila devuelve true
	public boolean chocaConAguila(Aguila a) {
		if (x + ancho / 2 > a.getX() - a.getAncho() / 2 && x - ancho / 2 < a.getX() + a.getAncho() / 2
				&& a.getY() - a.getAlto() / 2 < y + largo / 2 && y - largo / 2 < a.getY() + a.getAlto() / 2) {
			return true;
		}
		return false;
	}
	// si el mono choca con tigre devuelve true
	public boolean chocaConTigre(Tigre t) {
		if (x + ancho / 2 > t.getX() - t.getAncho() / 2 && x - ancho / 2 < t.getX() + t.getAncho() / 2
				&& t.getY() - t.getAlto() / 2 < y + largo / 2 && y - largo / 2 < t.getY() + t.getAlto() / 2) {
			return true;
		}
		return false;
	}

	public boolean chocaConArbol(Arbol a) {
		// si el mono choca con arbol devuelve true
		if (x + ancho / 2 > a.getX() - a.getAncho() / 2 && x - ancho / 2 < a.getX() + a.getAncho() / 2
				&& a.getY() - a.getAlto() / 2 < y + largo / 2 && y + largo / 2 < a.getY() + a.getAlto() / 2) {
			return true;
		}
		return false;
	}

	public boolean chocaConBanana(Items b) {
		// si el mono choca con banana devuelve true
		if (x + ancho / 2 > b.getBx() - b.getAncho() / 2 && x - ancho / 2 < b.getBx() + b.getAncho() / 2
				&& b.getBy() - b.getAlto() / 2 < y + largo / 2 && y - largo / 2 < b.getBy() + b.getAlto() / 2) {
			return true;
		}
		return false;
	}

	public boolean chocaConPiedra(Items p) {
		// si el mono choca con piedra devuelve true
		if (x + ancho / 2 > p.getX() - p.getAncho() / 2 && x - ancho / 2 < p.getX() + p.getAncho() / 2
				&& p.getY() - p.getAlto() / 2 < y + largo / 2 && y - largo / 2 < p.getY() + p.getAlto() / 2) {
			return true;
		}
		return false;
	}

	
	public void dibujarMono(Entorno e) {
//		e.dibujarRectangulo(this.x , this.y, this.ancho, this.largo, 0, this.color);	
		e.dibujarImagen(imagen, x, y - 15, angulo, .3);
	}

	public void saltar(int s) {
		this.angulo +=1;
		this.y -= s;
	}// saltar

	public void gravedad() {
		this.angulo=0;
		this.y += 5;
	}

	public boolean chocaConSuelo() {
		//si la instancia y del mono es mayor a 500 devuelve true
		if (this.y > 500) {
			return true;
		} else {
			return false;
		}
	}// chocaConSuelo

	public boolean chocaConArboles(Arbol[] a) {
		for (int i = 0; i < a.length; i++) {
			// si choca con con arbol es true devuelve true
			if (chocaConArbol(a[i])) {
				return true;
			}
		}
		return false;
	}

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

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getSalto() {
		return salto;
	}

	public void setSalto(int salto) {
		this.salto = salto;
	}

	public Piedra lanzarPiedra() {
		this.disparosDisp--;
		return new Piedra(x, y);

	}


}//?class?Mono