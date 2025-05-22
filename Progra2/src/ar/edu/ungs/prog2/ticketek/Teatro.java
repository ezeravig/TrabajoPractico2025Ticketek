package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public class Teatro extends Sede {
	

		public Teatro (String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores2,
				int[] capacidad, int[] porcentajeAdicional) {
			super(capacidadMaxima, direccion, nombre);	
			for(int i =0; i< sectores2.length;i++) {
				this.sectores.add(new Sector(asientosPorFila,sectores2[i],capacidad[i],porcentajeAdicional[i]));	
			}
		}

		@Override
		public boolean soyNumerada() {
			return true;
		}
	
	}


