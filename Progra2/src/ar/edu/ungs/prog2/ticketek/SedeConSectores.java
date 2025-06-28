package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.Objects;

public abstract class SedeConSectores extends Sede {

	protected ArrayList<Sector> sectores;
	
	public SedeConSectores(int capacidadMaxima, String direccion, String nombre) {
		super(capacidadMaxima, direccion, nombre);
		this.sectores= new ArrayList<>();
	}

	@Override
	public abstract boolean soyNumerada() ;

	@Override
	public abstract String formatoFuncion(ArrayList<Integer> cantidadVendida) ;

	@Override
	public double precioExtra() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public ArrayList<String> misSectores(){
		ArrayList<String> losSectores = new ArrayList<>();
		for(Sector sector:this.sectores) {
			losSectores.add(sector.getNombre());
		}
		return losSectores;
	}
	
	public int espacioDelSector(String nombreSec){
		for(Sector sec:this.sectores) {
			if(nombreSec.equals(sec.getNombre()))
				return sec.cualEsMiCapacidad();		
			}
		throw new IllegalArgumentException("No existe un sector llamado "+nombreSec+" en la sede "+this.nombre);	
	}
	
	public double calcularPrecioParaEntradaEnSector(double precio,String nombreSec) {
		for(Sector sec:this.sectores) {
			if(nombreSec.equals(sec.getNombre()))
				return sec.calcularPrecio(precio);		
		}
		throw new RuntimeException("no existe ese sector en la sede");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(sectores);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SedeConSectores other = (SedeConSectores) obj;
		return Objects.equals(sectores, other.sectores);
	}
	
}


