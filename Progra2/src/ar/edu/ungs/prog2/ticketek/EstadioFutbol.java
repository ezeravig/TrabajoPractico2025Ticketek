package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public class EstadioFutbol extends Sede {

	private int entradasYaVendidas;
	
	
	public EstadioFutbol(String nombre, String direccion, int capacidadMaxima) {
		super(capacidadMaxima, direccion, nombre);
		this.sectores.add(new Sector("CAMPO",capacidadMaxima,0));
		this.entradasYaVendidas = 0;
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
	public boolean quedanEntradas(int cantidadEntradas, int cantYaVendidas) {
		if(this.capacidadMaxima-cantYaVendidas > cantidadEntradas)
			return true;
		else
			throw new RuntimeException("no quedan "+cantidadEntradas+" disponibles para el Estadio");
	}



	




	
	
	
	
}





