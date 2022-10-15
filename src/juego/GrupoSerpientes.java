package juego;

import entorno.Entorno;

public class GrupoSerpientes {
	
	Serpiente serpientes[];
	
	
	
	
	public GrupoSerpientes() {
		
		this.serpientes= new Serpiente[50];
		
		for(int i=0; i< serpientes.length;i++) {
			serpientes[i]=new Serpiente();
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
