package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mono {
	private int x;
	private int y;
	private int ancho;
	private int largo;
	private int angulo;
	private Color color;
	
	
	//prueba


	public Mono(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=60;
		this.largo=100;
		this.angulo=0;
		this.color=Color.white;
		
}
	
	public void dibujarMono(Entorno e) {
		e.dibujarRectangulo(this.x+this.ancho,this.y, this.ancho, this.largo, this.angulo, this.color);
	}
	
	public void saltar(Entorno e) {
		
		this.y -=10;
		//this.y debe disminuir hasta Entorno.alto()-450, luego this.y debe aumentar hasta la altura de un objeto(suelo, vibora,tigre, rama)
		
	}//saltar
	
	public void gravedad(int g) {
		this.y += g;
	}
	
	public boolean chocaConSuelo(Entorno e, Suelo s) {
		if (this.y > e.alto()-s.alto) {
			return true;
		}else {
			return false;
		}
		
	}//chocaConSuelo
	
	public void detieneEnSuelo(Entorno e, Suelo s) {
		if (this.y >= e.alto()-s.alto) {
			
		}
		
	}//detieneEnSuelo
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}//class Mono
