package juego;

import java.util.Random;

import entorno.Entorno;

import java.util.*;


// METODO CONSTRUCTOR
public class Selva {
	
	Arbol []arboles;
	
	
	
	public Selva() {
		int a=810;					//810 ES DONDE VAN A APARECER POR 1RA VEZ LOS ARBOLES, 800 ES EL ANCHO DEL ENTORNO
		
		
		this.arboles= new Arbol[50];
		
		//CREO ARBOLES
		
		for(int i=0; i< arboles.length;i++) {
			int valorEntero = (int) Math.floor(Math.random()*(10-5+1)+5);  //CREO EL NUMERO ALEATORIO PARA COMPARAR CON LA SERPIENTES Y CREARLA.
			double rand=  Math.floor(Math.random()*(300-190+1)+190);		// ESTE NUMERO ALEATORIO DICTA LA ALTURA DE LA RAMA
			
			arboles[i]=new Arbol(a,rand,valorEntero);
				a=(int) (a+rand); 								
			
		}
		
	
		
	
		
// ACA TERMINA EL METODO CONSTRUCTOR		

		
		
		
		
// ACA EMPIEZAN LOS METODOS
	
}
	public void movimientoSelva(Entorno e) {
		//MOVIMIENTO ARBOLES
		for (int i = 0; i < arboles.length; i++) {
			arboles[i].dibujarArbol(e);
			arboles[i].desplazar();
			}
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
