package com.cafeteriaDemo.ServiceImplement;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteriaDemo.Entity.Producto;
import com.cafeteriaDemo.Entity.Venta;
import com.cafeteriaDemo.Repository.ProductoRepository;
import com.cafeteriaDemo.Repository.VentaRepository;
import com.cafeteriaDemo.Service.ProductoService;
import com.cafeteriaDemo.Service.VentaService;

@Service
public class VentaServiceImpl implements VentaService<Venta> {
	
	@Autowired
	private VentaRepository ventaRepo;
	
	@Autowired
	private ProductoService<Producto> productoService;

	@Override
	public List<Venta> findAll() {
		List<Venta> listaVentas = ventaRepo.findAll();
		return listaVentas;
	}

	@Override
	public Venta findByIdVenta(Integer id) {
		Optional<Venta> opcional = ventaRepo.findById(id);
		if(opcional.isEmpty()) {
			return null;	
		}
		return opcional.get();		
	}

	@Override
	public Boolean save(Venta entity) {
		Optional<Producto> existeProducto = productoService.findByIdProducto(entity.getIdProducto());
		
		if(existeProducto!=null) {
			System.out.println("existe producto");
			int stock = existeProducto.get().getStock();
			if(stock!=0 && stock>entity.getCantidad()) {
				int updateStock = stock - entity.getCantidad();
				existeProducto.get().setStock(updateStock);
				productoService.update(entity.getIdProducto(), existeProducto.get());		
				entity = ventaRepo.save(entity);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public boolean update(Integer id, Venta entity) {
		Optional<Venta> opcional = ventaRepo.findById(id);
		if(!opcional.isEmpty()) {
			Venta venta = ventaRepo.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		if(ventaRepo.existsById(id)) {
			ventaRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public String consultaMasVendido() {
		Integer mayor = 0; 
		Integer idMV = 0;
		List<Producto> listaP = productoService.findAll();
		for (int i = 0; i<listaP.size(); i++) {
			Producto producto = listaP.get(i);
			Integer sumaVenta = 0;
			List<Venta> listaV = findAll();
			for (int j = 0; j<listaV.size(); j++) {
				Venta venta = listaV.get(j);
				if(producto.getIdProducto()==venta.getIdProducto()) {
					sumaVenta = sumaVenta + venta.getCantidad();
				}
			}
			if(sumaVenta>mayor) {
				mayor=sumaVenta;
				idMV = producto.getIdProducto();
			}
		}
		return "El id mas vendido es: " + idMV + " con "+ mayor +" unidades vendidas";
	}


}
