package juego;

import java.awt.Color;

import entorno.Entorno;

public class Reintentar {
	private double x;
	private double y;
	private String texto; 
	private Color color;
	private int tamano;
	private String font;
	private int vidas;
	
	public Reintentar() {
		this.x=50;
		this.y=500;
		this.texto="presione la tecla [ENTER] para volver a empezar";
		this.color=Color.green;
		this.tamano=35;
		this.font="";
	}
	
	public void escribirReintentar(Entorno e) {
	e.escribirTexto(this.texto + vidas,this.x,this.y) ;
		
	}
	public void cambiarReintentar(Entorno e) {
	e.cambiarFont(this.font, this.tamano, this.color);
		
	}

}
