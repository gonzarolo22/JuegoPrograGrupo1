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
	private Image imagen2;
	
	
	public Piedra(double x, double y) {
		this.x=x;
		this.y=y;
		this.diametro=20;
		this.color= Color.white;
		this.imagen = Herramientas.cargarImagen("roca.png");
		this.imagen = Herramientas.cargarImagen("rocas.png");
	}
	
	public void crearPiedra(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
		e.dibujarImagen(imagen, x, y, 0, .15);
	}
	
<<<<<<< HEAD
	public boolean chocaConTigre(Tigre t) {
		return (t.getX() - t.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < t.getX() + t.getAncho()/2 &&
				
				t.getY() - t.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < t.getY() + t.getAlto()/2);
	}
		
	public boolean chocaConSerpiente(Serpiente s) {
		return (s.getX() - s.getAncho()/2 < x + diametro/2 && 
				x - diametro/2 < s.getX() + s.getAncho()/2 &&
				
				s.getY() - s.getAlto()/2 < y + diametro/2 &&
				y - diametro/2 < s.getY() + s.getAlto()/2);
	}
	}
=======
	public void crearPiedras(Entorno e) {
		Random random = new Random();
		
		int rand = random.nextInt(300,450);
		
		e.dibujarRectangulo(e.ancho()+100, rand, 50, 50, 0, color.brighter());
	}
	
>>>>>>> 0fc104a4d1ea86a05d6c9187d687ec8e1f9d4268
	public void lanzar(double g) {
		this.x += g;
	}
	public void saltar(double s) {
		this.y -=s;
	}
	public void gravedad(int g) {
		this.y += g;
	}
	
	
	
	public boolean saleDePantalla(Entorno e) {
		if (this.x > e.ancho() ) {
			return true;
		}else {
			return false;
		}
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
