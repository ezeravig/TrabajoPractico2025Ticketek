package ar.edu.ungs.prog2.ticketek;

import java.util.Objects;

public class Entrada implements IEntrada {


	    private int codigo;
	    private String espectaculo;
	    private Fecha fecha;
	    private String ubicacion;
	    private String sector;
	    private int numAsiento;
	    private int fila;
	    private String usuarioComprador;
	    private double precio;
	    private String sede;
	
	    public Entrada(String espectaculo, Fecha fecha,String sede, String sector,int numAsiento,
	    		int fila, String usuarioComprador, double precio, int codigo) {
			super();
			this.espectaculo = espectaculo;
			this.fecha = fecha;
			this.sector =sector;
			this.numAsiento= numAsiento;
			this.fila = fila;
			this.usuarioComprador = usuarioComprador;
			this.precio = precio;
			this.sede =sede;
			this.codigo = codigo;
		}
	    
	@Override
	public double precio() {
		// TODO Auto-generated method stub
		return precio;
	}
	@Override
	public String ubicacion() {
		if(this.sector.equals("CAMPO"))
			return this.sector;
		StringBuilder ubi = new StringBuilder();
		ubi.append(" Platea ");
		ubi.append(this.sector);
		ubi.append(" f:");
		ubi.append(this.fila);
		ubi.append(" a:");
		ubi.append(this.numAsiento);
		return ubi.toString();
	}
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append("- ");
		st.append(this.codigo);
		st.append(" - ");
		st.append(this.espectaculo);
		st.append(" - ");
		st.append(this.fecha.toString());
		st.append(" - ");
		st.append(this.sede);
		if(!esFutura()) {
			st.append(" P");
		}
		st.append(" - ");
		st.append(ubicacion());
		st.substring(1);
		return st.toString();
		
	}
	
	public String miComprador() {
		return this.usuarioComprador;
	}
	
	public boolean esFutura() {
		return !fecha.yaPaso();
		//Cambiar	
		}
	public int getCodigo() {
		return this.codigo;
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

	public String miEspectaulo() {
		return this.espectaculo;
	}
	
	public Fecha cuandoEs() {
		return this.fecha;
	}

	public int cualEsMiEspacio() {
		return this.numAsiento;
		
	}

	public String cualSectorEstoy() {
		return this.sector;
	}

}
