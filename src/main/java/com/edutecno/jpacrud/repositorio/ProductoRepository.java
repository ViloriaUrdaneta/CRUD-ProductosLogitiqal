package com.edutecno.jpacrud.repositorio;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edutecno.jpacrud.modelo.Producto;

/**
 * Repositorio que extiende de CrudRepository con su tipo de entidad y tipo de dato de su id, también extiende de PagingAndSorting
 * */
public interface ProductoRepository extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer> {

	//Query para génerar método de busqueda por el id
	@Query("from Producto where id_producto = ?1")
	public Optional<Producto> findById(Integer id);
	
	//Query para génerar método de busqueda por el nombre
	@Query("from Producto where nombre = ?1")
	public List<Producto> findByName(String nombre);
	
	
}
