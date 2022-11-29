package com.cafeteriaDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafeteriaDemo.Entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>{

}
