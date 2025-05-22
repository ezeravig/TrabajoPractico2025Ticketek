package ar.edu.ungs.prog2.ticketek;


import java.util.ArrayList;

public class MiniEstadio extends Sede {
	
		private int cantidadPuestos;
	    private int cantidadPuestosComida;
	    private double precioConsumicion;

	    public MiniEstadio(int capacidadMaxima, String direccion, String nombre) {
			super(capacidadMaxima, direccion, nombre);
			// TODO Auto-generated constructor stub
		}

		public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
				int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
				int[] porcentajeAdicional) {
	
			super(capacidadMaxima, direccion, nombre);
			
			this.cantidadPuestos = cantidadPuestos;
			this.precioConsumicion = precioConsumicion;
			for(int i =0; i< sectores.length;i++) {
				this.sectores.add(new Sector(asientosPorFila,sectores[i],capacidad[i],porcentajeAdicional[i]));	
			}
		}

		@Override
		public boolean soyNumerada() {
			// TODO Auto-generated method stub
			return true;
		}

}


