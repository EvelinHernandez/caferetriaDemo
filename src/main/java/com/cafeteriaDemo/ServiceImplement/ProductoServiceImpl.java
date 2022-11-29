package com.cafeteriaDemo.ServiceImplement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteriaDemo.Entity.Producto;
import com.cafeteriaDemo.Repository.ProductoRepository;
import com.cafeteriaDemo.Service.ProductoService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class ProductoServiceImpl implements ProductoService<Producto> {
	
	@Autowired
	private ProductoRepository productoRepo;

	@Override
	public List<Producto> findAll() {
		List<Producto> listaProductos = productoRepo.findAll();
		return listaProductos;
	}

	@Override
	public Optional<Producto> findByIdProducto(Integer id) {
		Optional<Producto> opcional = productoRepo.findById(id);
		if(opcional.isEmpty()) {
			return null;	
		}
		return opcional;
	}

	@Override
	public boolean save(Producto entity) {
		productoRepo.save(entity);
		return true;
	}

	@Override
	public boolean update(Integer id, Producto entity) {
		Optional<Producto> opcional = productoRepo.findById(id);
		if(!opcional.isEmpty()) {
			Producto producto = productoRepo.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		if(productoRepo.existsById(id)) {
			productoRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean cargarCSV() throws ParseException{
		try {
			List<Producto> usuarios = new ArrayList<Producto>();
			FileReader archivoCSV = new FileReader("Productos.csv");
			CSVReader leerProfesionales = new CSVReaderBuilder(archivoCSV).withSkipLines(1).build();                    
			String[] nextRecord;
			  
	        // we are going to read data line by line
	        while ((nextRecord = leerProfesionales.readNext()) != null) {
	            for (String cell : nextRecord) {
	            	System.out.println(cell);
	            	String[] attributes = cell.split(";");
	            	Producto pAgregar = new Producto(attributes);
	            	productoRepo.save(pAgregar);
	            }
	        }
	        leerProfesionales.close();
		} catch(FileNotFoundException e) {
	        e.printStackTrace();
	    } catch(CsvValidationException e) {
	        e.printStackTrace();
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
		
		return true;
	}
	
	@Override
	public String consultaStock() {
		List<Producto> listaP = findAll();
		int mayor = 0;
		String nombreP = "";
		Integer idProducto = 0;
		for (int i = 0; i<listaP.size(); i++) {
			Producto producto = listaP.get(i);
			if(producto.getStock()>mayor) {
				nombreP = producto.getNombreProducto();
				idProducto = producto.getIdProducto();
				mayor = producto.getStock();
			}
		}
		
		return "" + nombreP + " con id "+ idProducto + " es el producto con mas stock, con un total de "+ mayor + " unidades";
	}

}
