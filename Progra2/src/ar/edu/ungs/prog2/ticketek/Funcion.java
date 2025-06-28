package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Funcion {

	private Sede sede;
	private Fecha fecha;
	private double precioBase;
	private HashMap<String, ArrayList<IEntrada>> entradasPorSectorVendidas;
	// En lasnumeras representa a losasientos, en
																// lasnonumeradas El numero de entrada que se vende
	// pero todasson de sectorCAMPO y se puede estar en cualquier parte delmismo
	

	public Funcion(Fecha fecha, Sede sede, double precioBase) {
		super();
		if(!Fecha.esFormatoValido(fecha.toString())||sede==null||precioBase<0) {
			throw new RuntimeException("Unode los camposesta vacio o es incorrecto,verifique elformato de la fecha y que el precio no sa negativo");
		}
		this.sede = sede;
		this.fecha = fecha;
		this.precioBase = precioBase;
		this.entradasPorSectorVendidas = new HashMap<>();
		for (String sector : this.sede.misSectores()) {
			this.entradasPorSectorVendidas.put
			(sector, new ArrayList<IEntrada>(Collections.nCopies(this.sede.espacioDelSector(sector), null)));
		}
	}

	public void calcularPrecioPorSector() {
	}

	public Fecha getFecha() {
		return fecha;
	}

	public double totalrecaudado() {
		double totalRecaudado = 0;
		for(String secto:this.sede.misSectores()) {
			totalRecaudado += cantidadVendidadEn(secto)*valorEntrada(secto);
		}
		return totalRecaudado;
	}

	public Sede miSede() {
		return this.sede;
	}
	
	public boolean quedanEntradas(int cantidadEntradas) {
		int espacioDisponible=0;
		for(IEntrada espacio:this.entradasPorSectorVendidas.get("CAMPO")) {
			if((Entrada)espacio==null) {
				espacioDisponible++;
			} 	
			if(espacioDisponible>=cantidadEntradas)
				return true;
		}
		return false;
	}

	public boolean quedanLosAsientos(String sector, int[] asientos) {
		boolean quedan = true;
		for (int asiento : asientos) {
			quedan &= quedaElAsiento(sector, asiento);
		}
		return quedan;
	}

	public boolean quedaElAsiento(String sector, int asiento) {
		if(this.entradasPorSectorVendidas.get(sector).get(asiento)==null)
			return true;
		return false;
	}
	//sedes no Numeradas
	public IEntrada venderEntrada(String nombreEspectaculo, Fecha laFecha, String sector, String usuarioComprador) {
		int espacioDisponibleParaCodigo = buscarEspacioDisponible();
		Entrada entradaGenerada = new Entrada(nombreEspectaculo, laFecha,this.sede.getNombre(), sector, espacioDisponibleParaCodigo,0, usuarioComprador,
				valorEntrada("CAMPO"),espacioDisponibleParaCodigo);
		//en asento pongo eso por que sino no funciona el hash set, pero comoel sector es campo el asiento no se usa enlaubicacion
		guardarEntrada(entradaGenerada, "CAMPO",espacioDisponibleParaCodigo);
		return (IEntrada) entradaGenerada;
	}

	private int buscarEspacioDisponible() {
		ArrayList<IEntrada> buscarEspacio = this.entradasPorSectorVendidas.get("CAMPO");
		Iterator<IEntrada> iterador = buscarEspacio.iterator();
		int numeroEspacio=0;
		while (iterador.hasNext()) {
			if(iterador.next()==null)
				return numeroEspacio;
			numeroEspacio++;
		}
		throw new RuntimeException("No hay espacio disponible en el CAMPO");
	}
	//Sedes Numeradas
	public IEntrada venderEntrada(String nombreEspectaculo, Fecha laFecha, String sector, String usuarioComprador,int asiento) {
		int fila = this.sede.buscarFila(asiento, sector);
		Entrada entradaGenerada = new Entrada(nombreEspectaculo, laFecha,this.sede.getNombre(), sector, asiento, fila, usuarioComprador,
				valorEntrada(sector),asiento-1);
		guardarEntrada(entradaGenerada, sector,asiento-1);
		return entradaGenerada;
	}
	


	private void guardarEntrada(IEntrada entradaGenerada, String sector, int lugar) {
		if(this.entradasPorSectorVendidas.containsKey(sector)) {
			this.entradasPorSectorVendidas.get(sector).set(lugar, entradaGenerada);
			return;
			
		}
		throw new RuntimeException("El sector indicado no existe  ");
		}


	public boolean laSedeEsNoNumerada() {
		if (this.sede.soyNumerada()) {
			return false;
		}
		return true;

	}

	public boolean laSedeEsNumerada() {
		return this.sede.soyNumerada();
	}

	@Override
	public String toString() {
		StringBuilder funcion= new StringBuilder();
		funcion.append(" - (");
		funcion.append(this.fecha.toString());
		funcion.append(") ");
		ArrayList<Integer> cantidadVendida= new ArrayList<Integer>();
		for(String sector : this.sede.misSectores()){
			cantidadVendida.add(cantidadVendidadEn(sector));
		}
		funcion.append(this.sede.formatoFuncion(cantidadVendida));				
		return funcion.toString();
	}

	private Integer cantidadVendidadEn(String sector) {
		int vendidas =0;
		for(IEntrada seVendio:this.entradasPorSectorVendidas.get(sector))
			if(seVendio!=null) {
				vendidas++;
			}
		return vendidas;
	}

	//podemos agregar una funcion que nos retorne boolean s el sector existe, paraahorrar codigo
	
	public LinkedList<IEntrada> entradasVendidas(){
		LinkedList<IEntrada> entradasYaVendidas = new LinkedList<>();
		for(String sector:this.sede.misSectores())//paraque se pasan enorden los sectores de ls entradas ya vendidas :)
			for(IEntrada entrada : this.entradasPorSectorVendidas.get(sector)) {
				if(entrada!=null)
					entradasYaVendidas.add(entrada);
			}
			
		return entradasYaVendidas;
		
	}

	public boolean anularEntrada(Entrada laEntrada) {
		int lugarDeEntrada = laEntrada.getCodigo();
		String sector = laEntrada.cualSectorEstoy();
		if(this.entradasPorSectorVendidas.get(sector).get(lugarDeEntrada)!=null) {
			this.entradasPorSectorVendidas.get(sector).set(lugarDeEntrada,null);
			return true;
		}
		throw new RuntimeException("El lugar ya estaba desocupado la Entrada no era valida");
	}

	public double valorEntrada(String sector) {
		return this.sede.calcularPrecioParaEntradaEnSector(this.precioBase, sector)+this.sede.precioExtra();
	}

	public boolean esEnLaSede(Sede laSede) {
		return this.sede.equals(laSede);
	}

}


