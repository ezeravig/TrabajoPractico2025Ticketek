package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {


	    private String codigo;
	    private String espectaculo;
	    private Fecha fecha;
	    private String ubicacion;
	    private String sector;
	    private int numAsiento;
	    private Usuario usuarioComprador;
	    private double precio;
	
	    public Entrada(String espectaculo, Fecha fecha, String sector,int numAsiento, Usuario usuarioComprador, double precio, int odigo) {
			super();
			this.espectaculo = espectaculo;
			this.fecha = fecha;
			this.sector =sector;
			this.numAsiento= numAsiento;
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
		if
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

}
