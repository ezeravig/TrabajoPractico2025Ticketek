package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public class EstadioFutbol extends Sede {

	
	
	public EstadioFutbol(String nombre, String direccion, int capacidadMaxima) {
		super(capacidadMaxima, direccion, nombre);
		this.sectores.add(new Sector("CAMPO",capacidadMaxima,0));
		
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



	




	
	
	
	
}





