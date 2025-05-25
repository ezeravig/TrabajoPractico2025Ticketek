package ar.edu.ungs.prog2.ticketek;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Espectaculo {

	private static int numcod = 1;
	private String nombre;
	private HashMap<Fecha, Funcion> funciones;// Cada funcion es en una sede distinta
	private String idEspectaculo;
	

	public Espectaculo(String nombre) {
		super();
		if(nombre.isEmpty())
			throw new IllegalArgumentException("El nombre del Espectaculo no debe ser vacio");
		this.nombre = nombre;
		this.funciones = new HashMap<>();
		this.idEspectaculo = getNumcod() + "";
		setNumcod(1);
	}

	public void agregarFuncion(Fecha fecha, Sede sede, double precio) {
		if (existeFuncionEnFecha(fecha)) {
			System.out.println("En la fecha " + fecha + " ya existe una funcion en " + this.nombre);
			throw new RuntimeException("Este espectaculo ya tiene una funcion en la fecha que se paso");
			}
		funciones.put(fecha, new Funcion(fecha, sede, precio));
	}

	public LinkedList<Funcion> consultarFuncionesDisponibles() {
		LinkedList<Funcion> funDisponibles = new LinkedList<>();
		for (Funcion fun : funciones.values()) {
			if (!fun.getFecha().yaPaso())
				funDisponibles.add(fun);
		}
		return funDisponibles;
	}

	public double consultarTotalRecaudado() {
		double acum = 0;
		for (Funcion fun : this.funciones.values()) {
			acum += fun.totalrecaudado();
		}
		return acum;
	}
	public double totalRecaudadoPorSede(Sede laSede) {
		double acum = 0;
		for (Funcion fun : this.funciones.values()) {
			if(fun.esEnLaSede(laSede))
				acum += fun.totalrecaudado();
		}
		return acum;
	}

	private static int getNumcod() {
		return numcod;
	}

	private static void setNumcod(int i) {
		Espectaculo.numcod += i;
	}

	public boolean existeFuncionEnFecha(Fecha fecha) {
		return funciones.containsKey(fecha);
	}

	public boolean laFuncionEsNumerada(Fecha fecha) {
		if(!existeFuncionEnFecha(fecha)) {
			throw new RuntimeException("No existe la funcion enla fecha "+fecha.toString());
		}else
			return this.funciones.get(fecha).miSede().soyNumerada();		
	}
	
	public Funcion funcionDeLaFecha(Fecha fecha) {
		if(existeFuncionEnFecha(fecha)) {
			return this.funciones.get(fecha);
		}else
			throw new RuntimeException("No existe la funcion enla fecha "+fecha.toString());
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String listarFunciones() {
		StringBuilder listado = new StringBuilder();
		for(Funcion funcion: this.funciones.values()) {
			listado.append(funcion.toString());
		}
		return listado.toString();
	}

	public LinkedList<IEntrada> todasLasEntradas() {
		LinkedList<IEntrada> todasLasEntradas = new LinkedList<>();
		for(Funcion funcion:this.funciones.values())
			todasLasEntradas.addAll(funcion.entradasVendidas());
		return todasLasEntradas;
	}

}
