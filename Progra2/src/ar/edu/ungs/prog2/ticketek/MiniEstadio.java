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
		
		@Override
		public String formatoFuncion(ArrayList<Integer> cantidadVendida) {
			StringBuilder st=new StringBuilder(this.nombre);
			st.append(" -");
			for (int i = 0; i < this.sectores.size(); i++) {
				st.append(this.sectores.get(i).formatoFuncion(cantidadVendida.get(i)));
			}
			st.setLength(st.length()-2);
			
			return st.toString();
		}

		@Override
		public double precioExtra() {
			return this.precioConsumicion;
		}

}


