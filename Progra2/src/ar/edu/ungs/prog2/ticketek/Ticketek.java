package ar.edu.ungs.prog2.ticketek;

import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Ticketek implements ITicketek {
	private HashMap<String,Usuario> Usuarios = new HashMap<>();
	private HashMap<String,Espectaculo> Espectaculos = new HashMap<>();
	private HashMap<String,Sede> Sedes = new HashMap<>();
	private HashMap<Fecha,Set<String>> SedesUsadasEnFecha =   new HashMap<>();//el set string podria cambiarse por un set<Sede>
	
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
		
		if (!Sedes.containsKey(nombre)) {
			Sedes.put(nombre, new EstadioFutbol(nombre,direccion,capacidadMaxima));
		}else
			errorSedeYaRegistrada(nombre);
		
	}

	
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		
		if (!Sedes.containsKey(nombre)) {
			Sedes.put(nombre, new Teatro(nombre,direccion,capacidadMaxima,asientosPorFila,sectores,capacidad,porcentajeAdicional));
		}else
			errorSedeYaRegistrada(nombre);
		
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
		
		if (!Sedes.containsKey(nombre)) {
			Sedes.put(nombre, new MiniEstadio(nombre,direccion,capacidadMaxima,asientosPorFila,cantidadPuestos,precioConsumicion,sectores,capacidad,porcentajeAdicional));
		}else
			errorSedeYaRegistrada(nombre);
		
		}
	

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
		 if (!Usuarios.containsKey(email)) {
			Usuarios.put(email, new Usuario(email, nombre, apellido, contrasenia));
	    }else
		 	throw new RuntimeException("El email: "+email+" ya esta registrado");
		 
	}

	@Override
	public void registrarEspectaculo(String nombre) {
		if(!Espectaculos.containsKey(nombre)) {
			Espectaculos.put(nombre,new Espectaculo(nombre));
		}else
		 	throw new RuntimeException("El espectaculo con el nombre: "+nombre+" ya existe");
	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		Espectaculo espectaculoSolicitado = validarEspectaculo(nombreEspectaculo);//si no existe el espectaculo arroja error 
		Sede laSede = existeLaSede(sede);// si la sede no existe genera una exepcion
		Fecha laFecha = validarFecha(fecha);//Fijarse que la fecha tenga el formato correcto
		sedeDisponibleEnFecha(laFecha,sede);//Chequea que la sede este disponible esa fecha y no haya otro espectaculo
		espectaculoSolicitado.agregarFuncion(laFecha, laSede, precioBase);
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		if(cantidadEntradas<1) {
			throw new IllegalArgumentException("Debe indicar cuantas entradas desa");
		}
		Usuario usuarioComprador = validarUsuario(email,contrasenia);//generaun error si hay algun error con los datos recibidos delusuario
		Espectaculo espectaculoAVender = validarEspectaculo(nombreEspectaculo);
		Fecha laFecha = validarFecha(fecha);
		Funcion laFuncionAVender = validarFuncion(espectaculoAVender,laFecha);
		validarSedeNoNumerada(laFuncionAVender);
		LinkedList<IEntrada> entradasVendidas = new LinkedList<>();
		if(laFuncionAVender.quedanEntradas(cantidadEntradas)) {
			for(int i = 1;i<=cantidadEntradas; i++) {
				IEntrada entradaVendida = laFuncionAVender.venderEntrada(nombreEspectaculo,laFecha,"CAMPO",email);
				usuarioComprador.entradaComprada((Entrada)entradaVendida);
				entradasVendidas.add(entradaVendida);
			}
		}else
			throw new RuntimeException("A la funcion no le quedan "+cantidadEntradas+" entradas para vender");
		return entradasVendidas;
	}


	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		if(asientos.length<1) {
			throw new IllegalArgumentException("Debe indicar cuales asientos desea");
		}
		Usuario usuarioComprador = validarUsuario(email,contrasenia);//generaun error si hay algun error con los datos recibidos delusuario
		Espectaculo espectaculoAVender = validarEspectaculo(nombreEspectaculo);
		Fecha laFecha = validarFecha(fecha);
		Funcion laFuncionAVender = validarFuncion(espectaculoAVender,laFecha);
		validarSedeNumerada(laFuncionAVender);
		LinkedList<IEntrada> entradasVendidas = new LinkedList<>();
		if(laFuncionAVender.quedanLosAsientos(sector,asientos)) {
			for(int i = 0;i<asientos.length; i++) {
				IEntrada entradaVendida = laFuncionAVender.venderEntrada(nombreEspectaculo,laFecha,sector,email,asientos[i]);
				usuarioComprador.entradaComprada((Entrada)entradaVendida);
				entradasVendidas.add(entradaVendida);
			}
		}else
			throw new RuntimeException("Algunos asientos ya fueron vendidos");
		return entradasVendidas;
	}

	@Override
	public String listarFunciones(String nombreEspectaculo) {
		Espectaculo aListar = validarEspectaculo(nombreEspectaculo);
		String listadoFun = aListar.listarFunciones();
		return listadoFun;
	}

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
		Espectaculo elEspectaculo = validarEspectaculo(nombreEspectaculo);
		LinkedList<IEntrada> todasLasEntradasDelEspectaculo = elEspectaculo.todasLasEntradas();
		return todasLasEntradasDelEspectaculo;
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		Usuario elUsuario = validarUsuario(email, contrasenia);
		return elUsuario.ConsultarentradasFuturas();
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		Usuario elUsuario = validarUsuario(email, contrasenia);
		return elUsuario.Consultarentradas();
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		boolean laAnule = true;
		Entrada laEntrada = (Entrada)entrada;
		if(!laEntrada.esFutura()) {
			return false;
		}
		Espectaculo espectDeLaEntrada = validarEspectaculo(laEntrada.miEspectaulo());
		Funcion funcionDeLaEntrada = validarFuncion(espectDeLaEntrada, laEntrada.cuandoEs());
		laAnule &= funcionDeLaEntrada.anularEntrada(laEntrada);
		Usuario usuarioDueño = validarUsuario(laEntrada.miComprador(), contrasenia);//la Entrada tiene a su usuario
		laAnule &= usuarioDueño.anularEntrada(laEntrada, contrasenia);
		return laAnule;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		Entrada laEntradaAnular = (Entrada)entrada;
		if(!laEntradaAnular.esFutura()) {
			throw new RuntimeException("la Entrada que quiere cancelar ya ocurrio y no puede ser cancelada");
		}
		Fecha laNuevaFecha = validarFecha(fecha);
		Usuario comprador = validarUsuario(laEntradaAnular.miComprador(), contrasenia);
		Espectaculo espectaculoDeEntrda = validarEspectaculo(laEntradaAnular.miEspectaulo());
		Funcion laFuncionACambiar = validarFuncion(espectaculoDeEntrda, laNuevaFecha);
		if(!laFuncionACambiar.quedaElAsiento(sector, asiento)) {
			throw new RuntimeException("El asiento "+asiento+" del sector "+sector+
					" no esta disponible enlafuncion de la fecha: "+fecha);
		}
		anularEntrada(laEntradaAnular, contrasenia);
		IEntrada nuevaEntrada = laFuncionACambiar.venderEntrada(laEntradaAnular.miEspectaulo(), laNuevaFecha, sector, laEntradaAnular.miComprador(), asiento);
		comprador.entradaComprada((Entrada)nuevaEntrada);
		return nuevaEntrada;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		Entrada laEntradaAnular = (Entrada)entrada;
		if(!laEntradaAnular.esFutura()) {
			throw new RuntimeException("la Entrada que quiere cancelar ya ocurrio y no puede ser cancelada");
		}
		Fecha laNuevaFecha = validarFecha(fecha);
		Usuario comprador = validarUsuario(laEntradaAnular.miComprador(), contrasenia);
		Espectaculo espectaculoDeEntrda = validarEspectaculo(laEntradaAnular.miEspectaulo());
		Funcion laFuncionACambiar = validarFuncion(espectaculoDeEntrda, new Fecha(fecha));
		if(!laFuncionACambiar.quedanEntradas(1)) {
			throw new RuntimeException("la funcion de lafecha "+fecha+" no tiene lugar");
		}
		anularEntrada(laEntradaAnular, contrasenia);
		IEntrada nuevaEntrada = laFuncionACambiar.venderEntrada(laEntradaAnular.miEspectaulo(), laNuevaFecha, "CAMPO", laEntradaAnular.miComprador());
		comprador.entradaComprada((Entrada)nuevaEntrada);
		return nuevaEntrada;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		Espectaculo elEspectaculo = validarEspectaculo(nombreEspectaculo);
		Fecha laFecha = validarFecha(fecha);
		Funcion laFuncion = validarFuncion(elEspectaculo, laFecha);
		return laFuncion.valorEntrada("CAMPO");
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		Espectaculo elEspectaculo = validarEspectaculo(nombreEspectaculo);
		Fecha laFecha = validarFecha(fecha);
		Funcion laFuncion = validarFuncion(elEspectaculo, laFecha);
		return laFuncion.valorEntrada(sector);
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		Espectaculo elEspectaculo = validarEspectaculo(nombreEspectaculo);
		return elEspectaculo.consultarTotalRecaudado();
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		Espectaculo elEspectaculo = validarEspectaculo(nombreEspectaculo);
		Sede laSede = existeLaSede(nombreSede);
		return elEspectaculo.totalRecaudadoPorSede(laSede);
	}
	

	private void errorSedeYaRegistrada(String sede) {
		throw new RuntimeException("La sede: "+sede+" ya esta registrada");	
	}
	
	private Sede existeLaSede(String sede) {
		if(Sedes.containsKey(sede)){
			return Sedes.get(sede);
		}else
			throw new RuntimeException("La sede:"+ sede+" no esta cargada en el sistema");
		
	}
	
	private Espectaculo validarEspectaculo(String nombre) {
		if(Espectaculos.containsKey(nombre)) {
			return Espectaculos.get(nombre);
		}else
		 	throw new RuntimeException("El espectaculo con el nombre: "+nombre+" no existe");
	}
	
	private void sedeDisponibleEnFecha(Fecha fecha, String sede) {
		if(SedesUsadasEnFecha.containsKey(fecha)) {
			Set<String> sedesEnLaFecha = SedesUsadasEnFecha.get(fecha); 
			if(sedesEnLaFecha.contains(sede)) {
				System.out.println("la sede "+sede+" ya estaocupadala fecha "+fecha);
				throw new RuntimeException("La sede "+sede+" ya sera utilizada en la fecha"+fecha);
			}else
				sedesEnLaFecha.add(sede);
			return;
		}else {
			Set<String> sedes = new HashSet<>();
			sedes.add(sede);
			SedesUsadasEnFecha.put(fecha, sedes);
		}				
	}
	
	private Fecha validarFecha(String fecha) {
		if(Fecha.esFormatoValido(fecha)) {
			return new Fecha(fecha);
		}
		throw new IllegalArgumentException("la fecha ingresada:"+fecha+" ya ocurrio");
	}
	
	private Usuario validarUsuario(String email, String contrasenia) {
		if(Usuarios.containsKey(email)) {
			Usuarios.get(email).ValidarContrasenia(contrasenia);
		}else {
			throw new RuntimeException("el usuario: "+email+" no esta registrado");
		}
		return Usuarios.get(email);
	}
	
	private Funcion validarFuncion(Espectaculo espectaculoAVender, Fecha laFecha) {
		if(!espectaculoAVender.existeFuncionEnFecha(laFecha)) {
			throw new RuntimeException("No existe ninguna funcion programada parala fecha "+laFecha.toString()+" en el espectaculo "+ espectaculoAVender.getNombre());
		}
		return espectaculoAVender.funcionDeLaFecha(laFecha);
	}

	private void validarSedeNoNumerada(Funcion funcion) {
		if(funcion.laSedeEsNoNumerada()) {
			return;
		}
		throw new IllegalArgumentException("la funcion que desea comprar es numerada,indique los asientos que desea");
	}
	
	private void validarSedeNumerada(Funcion funcion) {
		if(funcion.laSedeEsNumerada()) {
			return;
		}
		throw new IllegalArgumentException("la funcion que desea comprar es numerada,indique los asientos que desea");
	}
	
	@Override
	public String toString() {
	StringBuilder tk = new StringBuilder();	
	tk.append("Usuarios Registrados:\n");
	for(Usuario usuario:this.Usuarios.values()) {
		tk.append(" - ");
		tk.append(usuario.toString());
		tk.append("\n");
	}
	tk.append("\nSedes Registradas:\n");
	for(Sede sede:this.Sedes.values()) {
		tk.append(" - ");
		tk.append(sede.toString());
		tk.append("\n");
	}
	tk.append("\nEspectaculos Registrados:\n");
	for(Espectaculo espectaculo:this.Espectaculos.values()) {
		tk.append(" - ");
		tk.append(espectaculo.toString());
		tk.append("\n");
		
	}
		return tk.toString();
		
	}
}
