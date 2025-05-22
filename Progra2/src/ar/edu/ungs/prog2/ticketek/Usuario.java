package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.LinkedList;

public class Usuario {
	private String email;
	private String nombre,apellido;
	private String contrasenia;
	private HashMap<String,Entrada> EntradasCompradas;
	
	public Usuario(String email, String nombre, String apellido, String contrasenia) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia ;
		this.EntradasCompradas = new HashMap<>();
		
	}
	public LinkedList<Entrada> Consultarentradas(){
		LinkedList<Entrada> misEntradas = new LinkedList<>();
		for(Entrada entrada: EntradasCompradas.values()) {
			misEntradas.add(entrada);
		}
		return misEntradas;
	}
	
	public LinkedList<Entrada> ConsultarentradasFuturas(){
		LinkedList<Entrada> futuras = new LinkedList<>();
		for(Entrada entrada: EntradasCompradas.values()) {
			if(entrada.esFutura())
				futuras.add(entrada);
		}
		return futuras;
			
	}
	
	public void entradaComprada(Entrada entrada) {
		EntradasCompradas.put(entrada.getCodigo(),entrada);
	}
	
	public boolean ValidarContrasenia(String contrasenia) {
		if(this.contrasenia.equals(contrasenia)){
			return true;
		}
		throw new RuntimeException("La ontraseña "+contrasenia+" esincorrecta");
	}
	public void AnularEntrada(Entrada entrada, String contrasenia) {
		if(!ValidarContrasenia(contrasenia))
			throw new RuntimeException("La contraseña no coincide con la del dueño de la entrada");
		EntradasCompradas.remove(entrada.getCodigo());
	}
}
