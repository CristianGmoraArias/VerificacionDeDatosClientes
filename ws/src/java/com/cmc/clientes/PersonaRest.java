package com.cmc.clientes;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.DateUtil;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ManejadorArchivos;
import com.cmc.rest.servicios.ServicioPersonas;

@Path("/personas")
public class PersonaRest {
	static Logger logger = LogManager.getLogger(PersonaRest.class);

	@Path("/cambiar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Persona modificar(Persona persona) throws ValidationException {
		System.out.println(persona.toString());
		Date date = new Date();
		String fechaCreacion = DateUtil.convertir(date);
		Persona n = ServicioPersonas.actualizar(persona);
		n.setFechaCreacion(fechaCreacion);
		return n;
	}

	@Path("/cambio")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response cambiar(Persona persona) {
		Persona personaNueva = new Persona();
		try {
			Date date = new Date();
			String fechaCreacion = DateUtil.convertir(date);
			personaNueva = ServicioPersonas.actualizar(persona);
			personaNueva.setFechaCreacion(fechaCreacion);
		} catch (ValidationException e) {
			logger.error("no se pudo ejecutar el cambio");
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(personaNueva).build();

	}

	@Path("/consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonas() {
		ManejadorArchivos manejadorArchivos = new ManejadorArchivos("miArchivo.txt"); // Cambia
																						// ruta
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			Date date = new Date();
			String fechaCreacion = DateUtil.convertir(date);
			personas.addAll(manejadorArchivos.leer());
			for (Persona persona : personas) {
				persona.setFechaCreacion(fechaCreacion);
			}

		} catch (ArchivoException e) {
			logger.error("No se pudo leer el archivo", e);
			e.printStackTrace();
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(personas).build();
	}
	@Path("/cedula")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Persona personaBuscada(Persona persona){
		Persona p=ServicioPersonas.buscarPorCedula(persona.getCedula());
		return p;
	}
	@Path("/agregar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Persona persona) {
		if (persona != null) {
			ServicioPersonas.guardarPersona(persona);
		}
	}
	
}
