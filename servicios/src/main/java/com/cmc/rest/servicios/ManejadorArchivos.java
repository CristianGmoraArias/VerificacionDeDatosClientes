package com.cmc.rest.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.DateUtil;
import com.cmc.rest.entidades.Persona;

public class ManejadorArchivos {
	private String rutaArchivo;
	//private Logger logger = LogManager.getLogger(ManejadorArchivos.class);

	public ManejadorArchivos(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public ArrayList<Persona> leer() throws ArchivoException {
		File file = new File(rutaArchivo);
		System.out.println("***"+file.getAbsolutePath()+"****");
		BufferedReader br = null;
		FileReader fileReader = null;
		ArrayList<Persona> personas = new ArrayList<Persona>();

		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String linea = "";
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("-");
				Persona p = new Persona(partes[0], partes[1], partes[2]);
				p.setEdad(Integer.parseInt(partes[3]));
				Date fecha=new Date();
				p.setFechaCreacion(DateUtil.convertir(fecha));
				Date fechaConvertida1=DateUtil.convertirDate(partes[4]);
				String fechaNacimiento=DateUtil.convertirDate2(fechaConvertida1);
				p.setFechaNacimiento(fechaNacimiento);
				personas.add(p);
			}

		} catch (FileNotFoundException e) {
			//logger.error("No existe el archivo", e);
			System.out.println("No existe el archivo"+ e);
			throw new ArchivoException("No existe el archivo" + rutaArchivo);

		} catch (IOException e) {
			//logger.error("Error al leer el archivo", e);
			System.out.println("Error al leer el archivo"+ e);
			throw new ArchivoException("No se pudo leer el archivo" + rutaArchivo);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fileReader.close();
			} catch (IOException e) {
				//logger.error("Error al cerrar el BufferReader", e);
				System.out.println("Error al cerrar el bufferReader"+ e);
			}
		}
		return personas;
	}

}
