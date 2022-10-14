package juego;

import java.util.Random;

import entorno.Entorno;

import java.util.*;


public class Selva {
	
	Arbol []arboles;
	
	
	public Selva() {
		
		this.arboles= new Arbol[50];
		
		for(int i=0; i< arboles.length;i++) {
		int valorEntero = (int) Math.floor(Math.random()*(350-200+1)+350);
			arboles[i]=new Arbol(810,valorEntero);
			
		}
		

}
	
	
}	
