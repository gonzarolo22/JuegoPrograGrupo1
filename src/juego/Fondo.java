package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private int x;
	private int y;
	private Image fondo;
	private int ancho;
	private int alto;


public Fondo(Image i) {
	this.x=0;
	this.y=300;
	this.alto=600;
	this.ancho=2400;
	this.fondo=i;
}

public void correrFondo(Entorno e) {
	this.x=x-2;
	
	
}

public void dibujarFondo(Entorno e) {
	e.dibujarImagen(fondo, x+ancho/2, e.alto()/2,0,1);
}

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

public int getAncho() {
	return ancho;
}

public void setAncho(int ancho) {
	this.ancho = ancho;
}








}
