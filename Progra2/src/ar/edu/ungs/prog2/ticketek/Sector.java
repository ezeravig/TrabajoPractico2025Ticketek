package ar.edu.ungs.prog2.ticketek;

public class Sector {
	    private String nombre;
	    private int nroFilas;
	    private int asientosPorFila;
	    private int aumentoPrecio;
	    private boolean ultimaFilaMenosAsientos;
	    private int asientosUltFila;
	    private int capacidadMaxima;    
	    
	  
		public Sector(int asientosPorFila, String nombre, int capacidad, int aumentoPrecio) {
			super();
			this.asientosPorFila = asientosPorFila;
			this.nroFilas = (int)Math.ceil(capacidad/asientosPorFila);
			if(capacidad%asientosPorFila!=0) {
				ultimaFilaMenosAsientos =true;
				asientosUltFila = capacidad%asientosPorFila;
			}
			this.capacidadMaxima = capacidad;
			this.aumentoPrecio = aumentoPrecio;	
			;
		}

		public Sector(String nombre, int capacidadMaxima, int aumentoPrecio) {
			super();
			this.nombre = nombre;
			this.aumentoPrecio = aumentoPrecio;
			this.capacidadMaxima = capacidadMaxima;
		}


		public String getNombre() {
			return nombre;
		}

		public double calcularPrecio(double precio) {
	    	return precio + calcularAumento(precio);
	    }
		
		private  double calcularAumento(double precio){
			return precio/100*this.aumentoPrecio;
			//Para saber cuanto es el n% de precio debemos hacer precio dividido 100 por n (precio/100*n)
		}


}


