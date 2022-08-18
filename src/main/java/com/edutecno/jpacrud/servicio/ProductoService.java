package com.edutecno.jpacrud.servicio;

import com.edutecno.jpacrud.modelo.Producto;
import com.edutecno.jpacrud.vo.NumberVO;
import com.edutecno.jpacrud.vo.ProductoVO;

//Interfaz con declaración de todos los métodos del servicio
public interface ProductoService {
	
	//método que trae todos los productos
	public ProductoVO getAllProductos();
	
	//método que trae producto por id
	public ProductoVO findById(Integer id);
	
	//método que trae producto por nombre
	public ProductoVO findByNombre(String nombre);
	
	//método que agrega un producto
	public ProductoVO add(Producto producto);
	
	//método que modifica un producto
	public ProductoVO update(Producto producto);
	
	//método que elimina un producto
	public ProductoVO delete(Producto producto);
	
	//método que configura la cantidad de productos por página
	public ProductoVO getPage(Integer pagina, Integer cantidad);
	
	//método para obtener el total de páginas según la cantidad de productos por página
	public NumberVO getPageCount(long registrosPorPagina);
}
