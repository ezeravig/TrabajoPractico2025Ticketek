package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Funcion {

	private Sede sede;
	private Fecha fecha;
	private double precioBase;
	private HashMap<String, Set<IEntrada>> entradasPorSectorVendidas;
	private HashMap<String, Set<Integer>> espaciosDisponibles;// En lasnumeras representa a losasientos, en
																// lasnonumeradas El numero de entrada que se vende
	// pero todasson de sectorCAMPO y se puede estar en cualquier parte delmismo

	public Funcion(Fecha fecha, Sede sede, double precioBase) {
		super();
		this.sede = sede;
		this.fecha = fecha;
		this.precioBase = precioBase;
		this.entradasPorSectorVendidas = new HashMap<>();
		this.espaciosDisponibles = new HashMap<>();
		for (String sector : this.sede.misSectores()) {
			this.entradasPorSectorVendidas.put(sector, new HashSet<>());
			this.espaciosDisponibles.put(sector, generarEspacioDisponible(this.sede.espacioDelSector(sector)));

		}
	}

	public void calcularPrecioPorSector() {
	}

	public void registrarVenta() {
	}

	public void verificarDisponibilidad() {
	}

	public Fecha getFecha() {
		// TODO Auto-generated method stub
		return fecha;
		// Modificar
	}

	public int totalrecaudado() {

		// que la sede devuelva el total recaudado, de acuerdo al precio base de la
		// funcion

		return 0;
	}

	public Sede miSede() {

		return this.sede;
	}

	private static Set<Integer> generarEspacioDisponible(int espacio) {
		Set<Integer> espacioCreado = new HashSet<>();
		for (int i = 1; i <= espacio; i++)
			espacioCreado.add(i);
		return espacioCreado;
	}

	public boolean quedanEntradas(int cantidadEntradas) {
		return this.espaciosDisponibles.get("CAMPO").size() > cantidadEntradas;
	}

	public boolean quedanLosAsientos(String sector, int[] asientos) {
		boolean quedan = true;
		for (int asiento : asientos) {
			quedan &= quedaElAsiento(sector, asiento);
		}
		return quedan;
	}

	public boolean quedaElAsiento(String sector, int asiento) {
		return this.espaciosDisponibles.get(sector).contains(asiento);
	}

	public IEntrada venderEntrada(String nombreEspectaculo, Fecha laFecha, String sector, Usuario usuarioComprador) {
		int espacioDisponibleParaCodigo = buscarEspacioDisponible();
		Entrada entradaGenerada = new Entrada(nombreEspectaculo, laFecha, sector, 0,0, usuarioComprador, this.precioBase,
				espacioDisponibleParaCodigo);
		guardarEntrada(entradaGenerada, "CAMPO");
		return (IEntrada) entradaGenerada;
	}

	private int buscarEspacioDisponible() {
		int numeroEspacio;
		Set<Integer> buscarEspacio = this.espaciosDisponibles.get("CAMPO");
		Iterator<Integer> iterador = buscarEspacio.iterator();
		while (iterador.hasNext()) {
			numeroEspacio = iterador.next();
			iterador.remove();
			return numeroEspacio;
		}
		throw new RuntimeException("No hay espacio disponible en el CAMPO");
	}
	public IEntrada venderEntrada(String nombreEspectaculo, Fecha laFecha, String sector, Usuario usuarioComprador,int asiento) {
		int fila = this.sede.buscarFila(asiento, sector);
		Entrada entradaGenerada = new Entrada(nombreEspectaculo, laFecha, sector, asiento, fila, usuarioComprador, this.precioBase,
				asiento);
		ocuparAsiento(sector,asiento);
		guardarEntrada(entradaGenerada, sector);
		return entradaGenerada;
	}
	

	private void ocuparAsiento(String sector, int asiento) {
		if(this.espaciosDisponibles.containsKey(sector)) {
			if(this.espaciosDisponibles.get(sector).remove(asiento)) {
				return;
			}
			throw new RuntimeException("El asiento ya estabaocupado o no existe ");
		}
		throw new RuntimeException("El sector ndcado no existe  ");
	}

	private void guardarEntrada(IEntrada entradaGenerada, String sector) {
		if(this.entradasPorSectorVendidas.containsKey(sector))
			this.entradasPorSectorVendidas.get(sector).add(entradaGenerada);
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

	//podemos agregar una funcion que nos retorne boolean s el sector existe, paraahorrar codigo
	

}


