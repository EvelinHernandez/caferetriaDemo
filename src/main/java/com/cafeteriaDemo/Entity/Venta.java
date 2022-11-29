package com.cafeteriaDemo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "venta")
public class Venta {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idVenta", nullable=false)
	private Integer idVenta;// OBLIGATORIO
	
	@Column(name="idProducto", nullable=false)
	private Integer idProducto;
	
	@Column(name="cantidad", nullable=false)
	private Integer cantidad; // OBLIGATORIO
	
	public Venta() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", idProducto=" + idProducto + ", cantidad=" + cantidad + "]";
	}
	
}