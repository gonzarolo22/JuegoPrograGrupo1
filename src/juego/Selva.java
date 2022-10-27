package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Selva {
	
	private int x;
	private int y;
	private Image fondo;
	private int ancho;
	private int alto;
	

	public Selva(int x) {
		this.x = x;
		this.alto = 600;
		this.ancho = 2400;
		this.fondo = Herramientas.cargarImagen("escenario.jpg"); 
		
	}//constructor selva
	
	public static Selva[] iniciaSelva(Selva[] selva) {
		int ancho = 0;// esta variable representa donde comienza la imagen
		for (int i = 0; i < selva.length; i++) {
			selva[i] = new Selva(ancho);
			ancho += 2400;
		}
		return selva;
	}
	
	public static void dibujarFondo(Selva[] selva, Entorno entorno) {
		
		if (selva[1].getX() == 0) {
			selva[0].setX(2400);
		} else if (selva[0].getX() == 0) {
			selva[1].setX(2400);
		}
		// se muestran ambas imagenes a la vez
		selva[1].dibujarFondo(entorno);
		selva[1].avanzarFondo(1);
		selva[0].dibujarFondo(entorno);
		selva[0].avanzarFondo(1);
	}
	
	public void avanzarFondo(int valor) {
		this.x -= valor;
	}//avanzarFondo
	
	public void dibujarFondo(Entorno e) {
		e.dibujarImagen(this.fondo,this.x+this.ancho/2,e.alto()/2, 0, 1);
	}//dibujarFondo
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getAncho() {
		return ancho;
	}
}
