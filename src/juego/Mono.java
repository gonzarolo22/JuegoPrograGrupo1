package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mono {
	int x;
	int y;
	int ancho;
	int largo;
	int angulo;
	Color color;
	
	//prueba
	
	public Mono(int x, int y) {
		this.x=x;
		this.y=y;
		this.ancho=120;
		this.largo=100;
		this.angulo=0;
		this.color=Color.white;
}
	
	public void dibujarMono(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.largo, this.angulo, this.color);
	}
	
	public void saltar(Entorno e) {
		
		//this.y debe disminuir hasta Entorno.alto()-450, luego this.y debe aumentar hasta la altura de un objeto(suelo, vibora,tigre, rama)
		
	}//saltar

}//class Mono
