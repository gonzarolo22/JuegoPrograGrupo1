package juego;

import java.util.Random;

import entorno.Entorno;

import java.util.*;


public class Selva {
	
	Arbol []arboles;
	
	
	public arboles() {
		int k=0;
		int n =0;
		Entorno e;
		for (int i = 0; i < arboles.length; i++) {
			// se crea un altura aleatoria para las ramas de los arboles
			Random random = new Random();
			int rand = random.nextInt(300);
			int rand2 = random.nextInt(100) ;
			
			
			//a cada elemento se le asigna una clase
			arboles[i]= new Arbol(e.ancho()+n+rand2 , rand);	
	}

}
	
	
}	
