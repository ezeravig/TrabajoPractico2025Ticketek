package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public class EstadioFutbol extends Sede {

	
	
	public EstadioFutbol(String nombre, String direccion, int capacidadMaxima) {
		super(capacidadMaxima, direccion, nombre);
		
		
	}



	@Override
	public double calcularPrecioTotal() {
		// TODO Auto-generated method stub
		return super.calcularPrecioTotal();
	}
	
	
	

	@Override
	public void validarDisponibilidadAsiento() {
		// TODO Auto-generated method stub
		super.validarDisponibilidadAsiento();
	}

	@Override
	public void listarSectores() {
		// TODO Auto-generated method stub
		super.listarSectores();
	}



	@Override
	public boolean soyNumerada() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String formatoFuncion(ArrayList<Integer> cantidadVendida) {
		StringBuilder st=new StringBuilder(this.nombre);
		st.append(" - ");
		st.append(cantidadVendida.get(0));
		st.append("/");
		st.append(this.capacidadMaxima);
		return st.toString();		
	}



	@Override
	public double precioExtra() {
		return 0;
	}



	@Override
	public ArrayList<String> misSectores() {
		ArrayList<String> campo = new ArrayList<>();
		campo.add("CAMPO");
		return campo;
	}



	@Override
	public int espacioDelSector(String nombreSec) {
		if(nombreSec.equals("CAMPO")) {
			return this.capacidadMaxima;
		}
		throw new RuntimeException("el setor "+nombreSec+" no existe en los estadios de futbol, solo existe el CAMPO");
	}



	@Override
	public double calcularPrecioParaEntradaEnSector(double precio, String nombreSec) {
		if(nombreSec.equals("CAMPO")) {
			return precio;
		}
		throw new RuntimeException("el setor "+nombreSec+" no existe en los estadios de futbol, solo existe el CAMPO");
	}
	
}





