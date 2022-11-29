package com.cafeteriaDemo.Service;

import java.util.List;

public interface VentaService<E> {
	public List<E> findAll();
	public E findByIdVenta(Integer id);
	public Boolean save(E entity);
	public boolean update(Integer id, E entity);
	public boolean delete(Integer id);
	public String consultaMasVendido();
}
