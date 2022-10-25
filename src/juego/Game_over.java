package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Game_over {
		
		private int x;
		private int y;
		private Image fondo;
		private int ancho;
		private int alto;
		

		public Game_over(int x) {
			this.x = x;
			this.alto = 200;
			this.ancho = 600;
			this.fondo = Herramientas.cargarImagen("Game_over.jpg"); 
		}
		
		public void dibujarOver(Entorno e) {
			e.dibujarImagen(this.fondo,this.x+this.ancho/2,e.alto()/2, 0, 1);
		}//dibujar Game over
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}

		public int getAncho() {
			return ancho;
		}

		public int getY() {
			return y;
		}
		
	}


