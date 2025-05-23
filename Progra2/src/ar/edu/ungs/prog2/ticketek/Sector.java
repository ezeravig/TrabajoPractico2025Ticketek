package ar.edu.ungs.prog2.ticketek;

public class Sector {
	    private String nombre;
	    private int asientosPorFila;
	    private int aumentoPrecio;
	    private int asientosUltFila;
	    private int capacidadMaxima;    
	    
	  
		public Sector(int asientosPorFila, String nombre, int capacidad, int aumentoPrecio) {
			super();
			this.asientosPorFila = asientosPorFila;
			this.capacidadMaxima = capacidad;
			this.aumentoPrecio = aumentoPrecio;	
			this.nombre = nombre;
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
		public int cualEsMiCapacidad() {
			return this.capacidadMaxima;
		}

		public int cualFilaEs(int asiento) {
			if(asiento<this.capacidadMaxima) {
				int fila =  (int)Math.ceil(this.capacidadMaxima/asientosPorFila);
				return fila;
			}else
				throw new IllegalArgumentException(this.nombre+" no tiene elasiento "+asiento+" por que cuenta con "+this.capacidadMaxima+" asientos");
		}

}


