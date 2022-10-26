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


public Fondo(int x) {
	this.x=x;
	this.alto=600;
	this.ancho=2400;
	this.fondo=Herramientas.cargarImagen("escenario.jpg");
}

public static Fondo[] iniciaFondo(Fondo[]fondo) {
	int ancho=0;
	for(int i=0; i<fondo.length;i++) {
		fondo[i]=new Fondo(ancho);
		ancho+=2400;
	}
	return fondo;
	
}

public void avanzarFondo(int valor) {
	this.x -= valor;
}

public void dibujarFondo(Entorno e) {
	e.dibujarImagen(fondo, x+ancho/2, e.alto()/2,0,1);
}





}
