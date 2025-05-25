package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Sede  {
	protected int capacidadMaxima;
	protected String direccion;
	protected String nombre;
    
    // De tipo Puede ser un  MiniEstadio, Teatro, EstadioFutbol.
    
    protected ArrayList<Sector> sectores;
    
    
    
    
    public Sede(int capacidadMaxima, String direccion, String nombre) {
		super();
		this.capacidadMaxima = capacidadMaxima;
		this.direccion = direccion;
		this.nombre = nombre;
		this.sectores= new ArrayList<>();
		
    }
    
    // Suma lo recaudado por cada sector 
    public double calcularPrecioTotal() {
    	double acum = 0.0;
    	
    	return acum;
    }
    
    public void validarDisponibilidadAsiento() {}
    
    
    
    public void listarSectores() {}
    
    public String getNombre() {
    	return this.nombre;
    }
    public abstract boolean soyNumerada();


	
	
	public  ArrayList<String> misSectores(){
		ArrayList<String> losSectores = new ArrayList<>();
		for(Sector sector:this.sectores) {
			losSectores.add(sector.getNombre());
		}
		return losSectores;
	}
    
	public int espacioDelSector(String nombreSec) {
		for(Sector sec:this.sectores) {
			if(nombreSec.equals(sec.getNombre()))
				return sec.cualEsMiCapacidad();		
			}
		throw new IllegalArgumentException("No existe un sector llamado "+nombreSec+" en la sede "+this.nombre);	
	}


	public int buscarFila(int asiento, String sector) {
		
		throw new RuntimeException("no tiene filas");
	}


	public abstract String formatoFuncion(ArrayList<Integer> cantidadVendida) ;
	
	public double calcularPrecioParaEntradaEnSector(double precio,String nombreSec) {
		for(Sector sec:this.sectores) {
			if(nombreSec.equals(sec.getNombre()))
				return sec.calcularPrecio(precio);		
		}
		throw new RuntimeException("no existe ese sector en la sede");
	}
	
	public abstract double precioExtra() ;

	@Override
	public int hashCode() {
		return Objects.hash(capacidadMaxima, direccion, nombre, sectores);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sede other = (Sede) obj;
		return capacidadMaxima == other.capacidadMaxima && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sectores, other.sectores);
	}
	
	@Override
	public String toString() {
		StringBuilder sede = new StringBuilder();
		sede.append(this.nombre);
		sede.append(" ubicada en ");
		sede.append(this.direccion);
		sede.append(" con capacidad para   personas");
		sede.insert(sede.length()-11, this.capacidadMaxima);
		return sede.toString();
		
	}
   
}


