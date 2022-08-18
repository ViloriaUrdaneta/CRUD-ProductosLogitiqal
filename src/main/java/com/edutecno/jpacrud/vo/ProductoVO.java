package com.edutecno.jpacrud.vo;

import java.util.List;


import com.edutecno.jpacrud.modelo.Producto;

//Objeto de vista de lista productos que hereda mensaje y código de GenericVO
public class ProductoVO extends GenericVO {

	//Lista de productos
	List<Producto> productos;
	
	//construtores con y sin atributos
	public ProductoVO(List<Producto> productos, String mensaje, String codigo) {
		super(mensaje,codigo);
		this.productos = productos;
	}
	public ProductoVO() {
		super();
	}
	
	//geteters y setters
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	//HashCode y equals
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
