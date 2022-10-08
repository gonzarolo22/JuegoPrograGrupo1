package juego;

import java.awt.Color;

public class Suelo {
	int x;
	int y;
	int ancho;
	int largo;
	int angulo;
	Color color;
	
	public Suelo (int x, int y){
		this.x=x;
		this.y=y;
		this.ancho= 500;
		this.largo=100;
		this.angulo=0;
		this.color=Color.green;
	}

}
