package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Usuario {
	private String email;
	private String nombre,apellido;
	private String contrasenia;
	private Set<Entrada> EntradasCompradas;
	
	public Usuario(String email, String nombre, String apellido, String contrasenia) {
		super();
		if(esVacio(email)||esVacio(nombre)||esVacio(apellido)||esVacio(contrasenia)) {
			throw new RuntimeException("Unoo varios cmbos estan vacios, por avor completelos ");
		}
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia ;
		this.EntradasCompradas = new HashSet<>();
		
	}
	
	private boolean esVacio(String s) {
		return (s==null||s.isEmpty());
	}
	
	public List<IEntrada> Consultarentradas(){
		List<IEntrada> misEntradas = new LinkedList<>();
		for(Entrada entrada: EntradasCompradas) {
			misEntradas.add(entrada);
		}
		return misEntradas;
	}
	
	public List<IEntrada> ConsultarentradasFuturas(){
		List<IEntrada> futuras = new LinkedList<>();
		for(Entrada entrada: EntradasCompradas) {
			if(entrada.esFutura())
				futuras.add(entrada);
		}
		return futuras;
			
	}
	
	public void entradaComprada(Entrada entrada) {
		EntradasCompradas.add(entrada);
	}
	
	public boolean ValidarContrasenia(String contrasenia) {
		if(this.contrasenia.equals(contrasenia)){
			return true;
		}
		throw new RuntimeException("La ontraseña "+contrasenia+" esincorrecta");
	}
	public boolean anularEntrada(Entrada entrada, String contrasenia) {
		if(!ValidarContrasenia(contrasenia))
			throw new RuntimeException("La contraseña no coincide con la del dueño de la entrada");
		if(this.EntradasCompradas.contains(entrada)) {
			this.EntradasCompradas.remove(entrada);
			return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email);
	}
	@Override 
	public String toString() {
		StringBuilder usuario = new StringBuilder();
		usuario.append("Nombre: ");
		usuario.append(this.nombre);
		usuario.append(" ");
		usuario.append(this.apellido);
		usuario.append(" | Email: ");
		usuario.append(this.email);
		return usuario.toString();
	}
	
}
