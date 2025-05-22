package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {
	
	    private Sede sede;
	    private Fecha fecha;
	    private double precioBase;
	    private HashMap<String,HashMap<Integer,Entrada>> entradasPorSectorVendidas;
	   

	    public Funcion( Fecha fecha,Sede sede, double precioBase) {
			super();
			this.sede = sede;
			this.fecha = fecha;
			this.precioBase = precioBase;
			this.entradasPorSectorVendidas = new HashMap<>();
			for(String sector:this.sede.misSectores()) {
				HashMap<Integer,Entrada> entradasVendidas = new HashMap<>();
				this.entradasPorSectorVendidas.put(sector, entradasVendidas);
			}
		}

		public void calcularPrecioPorSector() {}
	    
	    public void registrarVenta() {}
	    
	    public void verificarDisponibilidad() {}

		public Fecha getFecha() {
			// TODO Auto-generated method stub
			return fecha;
			//Modificar
		}

		public int totalrecaudado() {
			
			//que la sede devuelva el total recaudado, de acuerdo al precio base de la funcion
			
			
			return 0;
		}
	   public Sede miSede() {
		   
		   return this.sede;
	   }

	public boolean quedanEntradas(int cantidadEntradas) {
		return this.sede.quedanEntradas(cantidadEntradas,this.entradasPorSectorVendidas.get("CAMPO").size());
	}

	public IEntrada venderEntrada(String nombreEspectaculo, Fecha laFecha, String sector, Usuario usuarioComprador) {
			this.sede.VenderEntrada();
			Entrada entradaGenerada= new Entrada(nombreEspectaculo, laFecha, sector, 0, usuarioComprador, this.precioBase, entradasPorSectorVendidas.get("CAMPO").size()+1);
			return (IEntrada)entradaGenerada;
	}
	

	
	

}


