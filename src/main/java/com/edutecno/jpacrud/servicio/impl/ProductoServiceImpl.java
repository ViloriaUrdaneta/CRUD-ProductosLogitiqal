package com.edutecno.jpacrud.servicio.impl;

import java.util.ArrayList;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.jpacrud.modelo.Producto;
import com.edutecno.jpacrud.repositorio.ProductoRepository;
import com.edutecno.jpacrud.servicio.ProductoService;
import com.edutecno.jpacrud.vo.NumberVO;
import com.edutecno.jpacrud.vo.ProductoVO;

//Implementación de los metodos del servicio
@Service
public class ProductoServiceImpl implements ProductoService{

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);
	
	//Inyección del repositorio como objato de acceso de datos
	@Autowired
	ProductoRepository dao;
	
	//Declaración del objeto visual de la lista de productos
	ProductoVO respuesta;
	
	//Metodos implementados
	
	//método que trae todos los productos
	
	@Override
	@Transactional(readOnly = true)
	public ProductoVO getAllProductos() {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		//Intentamos llamar al metodo findAll de la instancia del crudRepository y setear la respuesta
		try {
			respuesta.setProductos((List<Producto>) dao.findAll());
			respuesta.setMensaje("Se han encontrado los productos");
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Usuario Service : Error en método getAllProductos", e);
		}
		//devolvemos respuesta
		return respuesta;
	}
	
	//método que consigue producto por id
	
	@Override
	@Transactional
	public ProductoVO findById(Integer id_producto) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		try {
			//Creamos una instancia de la clase producto y traemos el producto con el repository y lo tomamos
			Producto producto = dao.findById(id_producto).get();
			//Creamos una lista de productos y agregamos el producto
			List<Producto> lp = new ArrayList<Producto>();
			lp.add(producto);
			//Setteamos la respuesta con el producto, el mensaje y el codigo
			respuesta.setProductos(lp);
			respuesta.setMensaje("Se encontró el producto " + producto.getNombre());
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Producto Service : Error en método findById", e);
		}
		//devolvemos la respuesta
		return respuesta;
	}

	//método que consigue producto por nombre
	
	@Override
	@Transactional
	public ProductoVO findByNombre(String nombre) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		try {
			//Creamos lista de productos y agregamos el producto que trae el dao
			List<Producto> lp = dao.findByName(nombre);
			//Setteamos la respuesta
			respuesta.setProductos(lp);
			respuesta.setMensaje("Se encontró el producto ");
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Usuario Service : Error en método findByNombre", e);
		}
		//devolvemos la respuesta
		return respuesta;
	}

	//método que agrega productos
	
	@Override
	@Transactional
	public ProductoVO add(Producto producto) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		try {
			//agregamos el producto con el dao y setteamos respuesta
			dao.save(producto);
			respuesta.setMensaje("Se ha agregado el producto " + producto.getNombre());
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Usuario Service : Error en método add", e);
		}
		//devolvemos la respuesta
		return respuesta;
	}
	
	//método que modifica un producto

	@Override
	@Transactional
	public ProductoVO update(Producto producto) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		try {
			//agregamos el producto con el dao y setteamos respuesta
			dao.save(producto);
			respuesta.setMensaje("Se ha actualizado el producto " + producto.getNombre());
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Usuario Service : Error en método update", e);
		}
		//devolvemos la respuesta
		return respuesta;
	}

	//método que elimina un producto
	
	@Override
	@Transactional
	public ProductoVO delete(Producto producto) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		//tomamos el nombre del producto
		String nombre = producto.getNombre();
		try {
			//Eliminamos el producto con el dao
			dao.delete(producto);
			//Setteamos mensaje y código
			respuesta.setMensaje("Se ha eliminado el producto " + nombre);
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Usuario Service : Error en método delete", e);
		}
		//Devolvemos la respuesta
		return respuesta;
	}

	//método que configura la cantidad de productos por página

	@Override
	@Transactional(readOnly = true)
	public ProductoVO getPage(Integer pagina, Integer cantidad) {
		//Inicializamos el objeto visual setteando mensaje y codigo y creando lista de productos por default
		respuesta = new ProductoVO(new ArrayList<Producto>(), "Error", "102");
		try {
			//Le decimos al paginador el número de pagina y la cantidad de productos de esa página
			Pageable pageable = PageRequest.of(pagina, cantidad);
			//Agregamos a la pagina todos los productos que trae el paginador
			Page<Producto> responsePage = dao.findAll(pageable);
			//Setteamos la respuesta con los productos, el mensaje y el código
			respuesta.setProductos(responsePage.getContent());
			respuesta.setMensaje(String.format("Se han encontrado %d registros", respuesta.getProductos().size()));
			respuesta.setCodigo("0");
		}catch(Exception e) {
			//debug
			log.trace("Producto Service: Error en getPage", e);
		}
		//Devolvemos la respuesta
		return respuesta;
	}

	//método para obtener el total de páginas según la cantidad de productos por página
	
	@Override
	@Transactional(readOnly = true)
	public NumberVO getPageCount(long registrosPorPagina) {
		//Setteamos por defecto el objeto visual number
		NumberVO respuesta = new NumberVO(0, "Error", "102" );
		try {
			//Contamos el número de productos que hay
			long registros =  dao.count();
			//Si hay cero entradas por pagina y cero productos setteamos una sola pagina
			if(registrosPorPagina == 0 && registros == 0) {
				respuesta.setValor(1);
			}else {
				//dividimos el número total de productos entre los productos por página y la diferencia para calcular el número de páginas
				respuesta.setValor((registros/registrosPorPagina)+ (registros % registrosPorPagina == 0 ? 0 : 1));
			}
			//Setteamos código y mensaje
			respuesta.setCodigo("201");
			respuesta.setMensaje(String.format("Hay %d paginas", respuesta.getValor()));
		}catch(Exception e) {
			//debug
			log.trace("Producto Service: Error en getPageCount", e);
		}
		//devolvemos respuesta
		return respuesta;
	}


}
