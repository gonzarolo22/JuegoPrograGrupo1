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
		double valordouble1 =  300;
		for(int i=0; i< arboles.length;i++) {
			
			double rand=  Math.floor(Math.random()*(300-190+1)+190);
			
			arboles[i]=new Arbol(a,rand);
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
