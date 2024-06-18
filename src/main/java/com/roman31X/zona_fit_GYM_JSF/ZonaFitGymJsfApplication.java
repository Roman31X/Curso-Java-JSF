package com.roman31X.zona_fit_GYM_JSF;

import com.roman31X.zona_fit_GYM_JSF.model.Cliente;
import com.roman31X.zona_fit_GYM_JSF.service.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitGymJsfApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	//LINEA LA CUAL NOS PERMITE ENVIAR MENSAJE A CONSOLA EN VEZ DE USAR EL PRINTLN
	private static final Logger logger = LoggerFactory.getLogger(ZonaFitGymJsfApplication.class);

	//Con esto obtendremos carácter genérico de salto de línea
	String nl = System.lineSeparator();


	public static void main(String[] args) {
		SpringApplication.run(ZonaFitGymJsfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			try{
				var opcion = mostrarMenu(consola);
				salir = ejecutarOpciones(consola,opcion);
				logger.info(nl);
			}catch (Exception e){
				logger.info("Error al digitar opción no reconocida: "+e.getMessage()+nl);
			}
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
    	|---------------------------|
    	| APLICACIÓN ZONA FIT GYM 🔱|
    	|---------------------------|
    	|  [1] - Listar Clientes    |
    	|  [2] - Buscar Cliente		|
    	|  [3] - Agregar Cliente	|
    	|  [4] - Modificar Cliente	|
    	|  [5] - Eliminar Cliente	|
    	|  [6] - Salir del App		|
    	|---------------------------|
    	|INGRESE UNA OPCIÓN DEL MENÚ|
    	|---------------------------|
    	| =>""");
		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch(opcion){
			case 1 -> {
				logger.info(nl+"|------------------LISTADO DE CLIENTES ZONA FIT GYM-------------|"+nl);
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info("| "+cliente.toString()+nl));
			}
			case 2 -> {
				logger.info("""
    			|--------BUSCAR CLIENTE ZONA FIT GYM----------|
    			| Ingrese ID: """);
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					logger.info(nl+"Cliente encontrado: "+cliente+nl);
				}else{
					logger.info(nl+"Cliente no encontrado en la Base de datos: "+cliente+nl);
				}
			}
			case 3 -> {
				logger.info(nl+"|----AGREGAR NUEVO CLIENTE ZONA FIT GYM 🔱----|"+nl);
				logger.info("| Nombre: ");
				var nombre = consola.nextLine();
				logger.info("| Apellido: ");
				var apellido = consola.nextLine();
				logger.info("| Membresía: ");
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info("| Cliente agregado: "+cliente+nl);
			}
			case 4 -> {
				logger.info("|-----MODIFICAR CLIENTE ZONA FIT GYM 🔱------|"+nl);
				logger.info("| ID Cliente: ");
				var  idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					logger.info("| Nombre: ");
					var nombre = consola.nextLine();
					logger.info("| Apellido: ");
					var apellido = consola.nextLine();
					logger.info("| Membresía: ");
					var membresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);
					logger.info("Cliente modificado: "+cliente+nl);
				}else{
					logger.info("Cliente NO encontrado en la base de dato: "+cliente+nl);
				}

			}
			case 5 -> {
				logger.info("|-----CLIENTE A ELIMINAR DE ZONA FIT GYM 🔱------|"+nl);
				logger.info("| ID Cliente: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				var cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					clienteServicio.eliminarCliente(cliente);
					logger.info("| Cliente eliminado: " + cliente + nl);
				}else{
					logger.info("| Cliente NO encontrado en la Base de datos: " + cliente);
				}
			}
			case 6 -> {
				logger.info("""
       |------------------------------------------------|
   				|   GRACIAS POR USAR LA APP ZONA FIT GYM 🔱      |
   				|               QUE TENGA BUEN DÍA               |
   				|------------------------------------------------|"""+nl+nl);
				salir = true;
			}
			default -> logger.info("| La opción ingresada no se encuentra en el MENÚ: "+opcion+nl);
		}
		return salir;
	}

}
