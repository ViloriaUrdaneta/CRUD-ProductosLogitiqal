package com.edutecno.jpacrud.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//Se crea POJO entidad de producto
@Entity
@SequenceGenerator(name="id_producto_seq", initialValue=1, sequenceName = "id_producto_seq", allocationSize =1)
public class Producto {

	//se marca el id y se crea secuencia, y se crean los otros atributos
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_producto_seq")
	private Integer id_producto;
	private String nombre;
	private Integer precio;
	private Integer stock;
	
	//Constructor vacio y con atributos
	public Producto() {
		super();
	}
	public Producto(Integer id_producto, String nombre, Integer precio, Integer stock) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	//Getters y Setters
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}
	
	
	
}
