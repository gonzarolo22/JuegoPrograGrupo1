package juego;

import java.awt.Color;

import entorno.Entorno;

public class Puntaje {
	private double x;
	private double y;
	private String texto; 
	private Color color;
	private int tamano;
	private String font;
	private int puntos;
	
	public Puntaje() {
		this.x=600;
		this.y=50;
		this.texto="puntaje:";
		this.color=Color.red;
		this.tamano=30;
		this.font="puntaje:";
		this.puntos=0;
	}
	
	public void escribirPuntaje(Entorno e) {
	e.escribirTexto(this.texto + puntos,this.x,this.y) ;
		
	}
	public void cambiarPuntaje(Entorno e) {
	e.cambiarFont(this.font, this.tamano, this.color);
		
	}
	public void aumentaPuntos() {	
		puntos+=1;
	}
	

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getTexto() {
		return texto;
	}

	public Color getColor() {
		return color;
	}

	public int getTamano() {
		return tamano;
	}

	public String getFont() {
		return font;
	}

	public int getPuntos() {
		return puntos;
	}
	
	
	
	
	
	

}
