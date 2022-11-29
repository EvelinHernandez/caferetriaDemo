package com.cafeteriaDemo.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idProducto", nullable=false)
	private Integer idProducto;
	
	@Column(name="nombreProducto", nullable=false)
	private String nombreProducto;// OBLIGATORIO
	
	@Column(name="referencia", nullable=false)
	private String referencia;// OBLIGATORIO
	
	@Column(name="precio", nullable=false)
	private Integer precio; // OBLIGATORIO
	
	@Column(name="peso", nullable=false)
	private Integer peso; //ENTERO: OBLIGATORIO
	
	@Column(name="categoria", nullable=false)
	private String categoria; // OBLIGATORIO
	
	@Column(name="stock", nullable=false)
	private Integer stock;// (Cantidad del producto en bodega) ENTERO: OBLIGATORIO
	
	@Column(name="fechaCreacion", nullable=false)
	private Date fechaCreacion;//: date OBLIGATORIO
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(Integer idProducto, String nombreProducto, String referencia, Integer precio, Integer peso,
			String categoria, Integer stock, Date fechaCreacion) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.referencia = referencia;
		this.precio = precio;
		this.peso = peso;
		this.categoria = categoria;
		this.stock = stock;
		this.fechaCreacion = fechaCreacion;
	}

	//constructor para el csv
	public Producto(String[] datos) throws ParseException {
		System.out.println(datos);
		this.nombreProducto = datos[0];
		this.referencia = datos[1];
		this.precio = Integer.parseInt(datos[2]);
		this.peso = Integer.parseInt(datos[3]);
		this.categoria = datos[4];
		this.stock = Integer.parseInt(datos[5]);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaCreacion = formato.parse(datos[6]);
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", referencia="
				+ referencia + ", precio=" + precio + ", peso=" + peso + ", categoria=" + categoria + ", stock=" + stock
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	
}
