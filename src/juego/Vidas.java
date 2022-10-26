package juego;

import java.awt.Color;

import entorno.Entorno;

public class Vidas {
		private double x;
		private double y;
		private String texto; 
		private Color color;
		private int tamano;
		private String font;
		private int vidas;
		
		public Vidas() {
			this.x=120;
			this.y=50;
			this.texto="Vidas: ";
			this.color=Color.red;
			this.tamano=25;
			this.font=" ";
			this.vidas=3;
		}
		
		public void escribirVida(Entorno e) {
		e.escribirTexto(this.texto + vidas,this.x,this.y) ;
			
		}
		public void cambiarVida(Entorno e) {
		e.cambiarFont(this.font, this.tamano, this.color);
			
		}
		public void disminuirVidas() {
			vidas-=1;
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

		public int getVidas() {
			return vidas;
		}

		public void setVidas(int vidas) {
			this.vidas = vidas;
		}
		
		

}
