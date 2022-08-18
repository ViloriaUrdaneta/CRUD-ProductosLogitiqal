package com.edutecno.jpacrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edutecno.jpacrud.modelo.Producto;

import com.edutecno.jpacrud.repositorio.ProductoRepository;


@SpringBootApplication
public class JpacrudApplication {
	
	private static final Logger log = LoggerFactory.getLogger(JpacrudApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(JpacrudApplication.class, args);
	}

	/* Primera carga de productos de prueba
	@Bean
	public CommandLineRunner inicioApp(ProductoRepository repo) {
		
		return (args) ->{
			repo.save(new Producto(null, "Producto01", 1000, 002));
			repo.save(new Producto(null, "Producto02", 1000, 1224));
			repo.save(new Producto(null, "Producto03", 1000, 1223));
			
			log.info("Lista de productos a mostrar");
			for(Producto producto : repo.findAll()){
				log.info(producto.toString());
			}
		};
	
	}*/
	
}
