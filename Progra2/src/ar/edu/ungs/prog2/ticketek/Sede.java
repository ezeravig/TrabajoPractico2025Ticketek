package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

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


	public int calcularPrecioSector(int precioBase) {
    	
    	return 0;
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
   
}


