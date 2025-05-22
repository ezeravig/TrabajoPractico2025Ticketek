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


	public void VenderEntrada() {
		// TODO Auto-generated method stub
		
	}


	public boolean quedanEntradas(int cantidadEntradas, int cantYaVendidas) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public  ArrayList<String> misSectores(){
		ArrayList<String> misSectores = new ArrayList<>();
		for(Sector sector:this.sectores) {
			misSectores.add(sector.getNombre());
		}
		return misSectores;
	}
    
   
}


