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
		@Override
		public int buscarFila(int asiento, String sector) {
			if(misSectores().contains(sector)) {
				for(Sector sec:this.sectores) {
					if(sec.getNombre().equals(sector))
						return sec.cualFilaEs(asiento);
				}
			}
			throw new RuntimeException("la sede"+this.nombre+" no tiene ningun sector llamado "+sector);
		}
	}


