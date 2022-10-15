package juego;

import entorno.Entorno;

public class GrupoSerpientes {
	
	Serpiente serpientes[];
	
	
	
	
	public GrupoSerpientes() {
		
		this.serpientes= new Serpiente[50];
		double n=810;
		for(int i=0; i< serpientes.length;i++) {
		double valordouble1 =  Math.floor(Math.random()*(300-190+1)+190);
			serpientes[i]=new Serpiente(n,valordouble1);
			n=n+valordouble1;
		}
		
}
	
	public void movimientoSerpientes(Entorno e, Arbol a) {
		for (int i = 0; i < serpientes.length; i++) {
			if(serpientes[i].siCoincideConArbol(a)) {
			
			
			serpientes[i].dibujarSerpiente(e);
			serpientes[i].desplazar();
			}
		}
		}
	
	
	
	
	
	
	
	
	
	
	
	

}
