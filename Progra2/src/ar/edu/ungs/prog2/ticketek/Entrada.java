package ar.edu.ungs.prog2.ticketek;

import java.util.Objects;

public class Entrada implements IEntrada {


	    private String codigo;
	    private String espectaculo;
	    private Fecha fecha;
	    private String ubicacion;
	    private String sector;
	    private int numAsiento;
	    private int fila;
	    private Usuario usuarioComprador;
	    private double precio;
	
	    public Entrada(String espectaculo, Fecha fecha, String sector,int numAsiento, int fila, Usuario usuarioComprador, double precio, int odigo) {
			super();
			this.espectaculo = espectaculo;
			this.fecha = fecha;
			this.sector =sector;
			this.numAsiento= numAsiento;
			this.fila = fila;
			this.usuarioComprador = usuarioComprador;
			this.precio = precio;
		}
	    
	@Override
	public double precio() {
		// TODO Auto-generated method stub
		return precio;
	}
	@Override
	public String ubicacion() {
		if(numAsiento==0)
			return "CAMPO";
		return null;
	}
	@Override
	public String toString() {
		return " ";
		
	}
	
	public boolean esFutura() {
		return true;
		//Cambiar	
		}
	public String getCodigo() {
		return "";
		//Cambiar	
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, espectaculo, fecha, numAsiento, sector, usuarioComprador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(espectaculo, other.espectaculo)
				&& Objects.equals(fecha, other.fecha) && numAsiento == other.numAsiento
				&& Objects.equals(sector, other.sector) && Objects.equals(usuarioComprador, other.usuarioComprador);
	}

}
