package com.cmc.rest.test;

import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ServicioPersonas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestActualizar {

	public static void main(String[] args) throws ValidationException {
		Logger logger = LogManager.getLogger(TestActualizar.class);
		Persona persona=new Persona("1231231","Cristian","Mora");
		System.out.println(persona.toString());
		Persona n = new Persona();
		try{
		 n=ServicioPersonas.actualizar(persona);
		}catch(ValidationException e){
			e.printStackTrace();
			logger.error("Se a producido un error en el programa", e);
		}
		System.out.println(n.toString());
		

	}

}
