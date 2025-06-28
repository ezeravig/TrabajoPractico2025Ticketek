package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public abstract class Sede  {
	protected int capacidadMaxima;
	protected String direccion;
	protected String nombre;
    
   
    
    
    
    public Sede(int capacidadMaxima, String direccion, String nombre) {
		super();
		if(capacidadMaxima<=0) {
			throw new RuntimeException("La capacidad maximano puede ser 0 o menos ");
		}
		if(esVacio(direccion)||esVacio(nombre)) {
			throw new RuntimeException("La capacidad maximano puede ser 0 o menos ");
		}
		this.capacidadMaxima = capacidadMaxima;
		this.direccion = direccion;
		this.nombre = nombre;
		
    }
    
    private boolean esVacio(String s) {
		return (s==null||s.isEmpty());
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


	
	
	public abstract ArrayList<String> misSectores();
    
	public abstract int espacioDelSector(String nombreSec); 


	public int buscarFila(int asiento, String sector) {
		
		throw new RuntimeException("no tiene filas");
	}


	public abstract String formatoFuncion(ArrayList<Integer> cantidadVendida) ;
	
	public abstract double calcularPrecioParaEntradaEnSector(double precio,String nombreSec) ;

	
	public abstract double precioExtra() ;

	@Override
	public int hashCode() {
		return Objects.hash(capacidadMaxima, direccion, nombre);
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
				&& Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString() {
		StringBuilder sede = new StringBuilder();
		sede.append(this.nombre);
		sede.append(" ubicada en la");
		sede.append(this.direccion);
		sede.append(". Con capacidad para  personas");
		sede.insert(sede.length()-9, this.capacidadMaxima);
		return sede.toString();
		
	}
   
}


