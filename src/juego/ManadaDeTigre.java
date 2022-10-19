package juego;

import entorno.Entorno;

public class ManadaDeTigre {
	
	private Tigre [] tigres;
	
	
	
	
	public ManadaDeTigre() {
		this.tigres= new Tigre [30];
		
		int rand=  (int) Math.floor(Math.random()*(2000-810+1)+810);
		for(int i=0; i<tigres.length;i++) {
			int rand2=  (int) Math.floor(Math.random()*(2000-810+1)+810);
			tigres[i]= new Tigre(rand,400);
			rand=rand+rand2;
		}
		
	}
	
	
	public void movimientoTigres(Entorno e) {
		//MOVIMIENTO ARBOLES
		for (int i = 0; i < tigres.length; i++) {
			tigres[i].dibujarTigre(e);
			tigres[i].desplazar();
			}
	}

	
	
	}
