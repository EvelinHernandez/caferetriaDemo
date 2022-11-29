   package com.cafeteriaDemo.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.cafeteriaDemo.Entity.Producto;

public interface ProductoService<E> {
	public List<E> findAll();
	public Optional<E> findByIdProducto(Integer id);
	public boolean save(E entity);
	public boolean update(Integer id, E entity);
	public boolean delete(Integer id);
	public Boolean cargarCSV() throws ParseException;
	public String consultaStock();
}
