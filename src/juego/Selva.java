package juego;

import java.util.Random;

import entorno.Entorno;

import java.util.*;


public class Selva {
	
	Arbol []arboles;
	
	
	public Selva() {
		
		this.arboles= new Arbol[50];
		
		for(int i=0; i< arboles.length;i++) {
		double valordouble1 =  Math.floor(Math.random()*(200-200+1)+200);
		int valordouble2 =  (int) Math.floor(Math.random()*(200-200+1)+200);
			arboles[i]=new Arbol(valordouble2,valordouble1);
			
		}
		

	
}
	public void movimientoSelva(Entorno e) {
		for (int i = 0; i < arboles.length; i++) {
			arboles[i].dibujarArbol(e);
			arboles[i].desplazar();
			}
		}
	
	
}
