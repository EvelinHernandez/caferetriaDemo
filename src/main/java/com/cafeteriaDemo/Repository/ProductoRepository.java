package com.cafeteriaDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteriaDemo.Entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
}
