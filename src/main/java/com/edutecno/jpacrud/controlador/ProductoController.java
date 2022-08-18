package com.edutecno.jpacrud.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edutecno.jpacrud.modelo.Producto;

import com.edutecno.jpacrud.servicio.ProductoService;
import com.edutecno.jpacrud.vo.ProductoVO;
import com.edutecno.jpacrud.util.Util;


@Controller
public class ProductoController {

	
	//Se inyecta el Servicio de productos
	@Autowired
	private ProductoService svc;
	
	//Log para el debug
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	/**
	 * Abre el home cargando todos los productos de la base de datos
	 * */
	@GetMapping({ "/", "/home" })
	public String home(Model model, @RequestParam(defaultValue = "1") Integer p,  @RequestParam(defaultValue = "10") Integer rowPerPag) {
		//Se le agrega el numero de filas por páginas al modelo
		model.addAttribute("rowPerPag", rowPerPag);
		//Se le agrega los productos al modelo
		model.addAttribute("VO", svc.getAllProductos());
		//Se saca el número de páginas segun el número de productos
		Integer totalPaginas = (int) svc.getPageCount(rowPerPag).getValor();
		//Se añade el objeto páginas a la vista
		model.addAttribute("paginas", Util.getArregloPaginas(p, totalPaginas));
		//Se llama al número de la pagina que se está viendo
		model.addAttribute("paginaActual", p);
		model.addAttribute("VO", svc.getPage(p-1, rowPerPag));
		//endpoint
		return "home";
	}
	

	/**
	 * Abre el formulario de edición cargando los datos del producto
	 * */
	@GetMapping("/editarForm")
	public ModelAndView editarForm(Model model, @RequestParam Integer id_producto, RedirectAttributes ra) {
		//Creamos instancia del objeto visual con los productos
		ProductoVO respuestaServicio = new ProductoVO();
		//Seteamos el mensaje por default del objeto
		respuestaServicio.setMensaje("No se pudo cargar la vista de edición, intente nuevamente.");
		//Abrimos try catch en el que usamos el método findByID que nos trae el producto
		try {
			//Cargamos la instancia con el producto que nos trae el servicio con el id
			respuestaServicio = svc.findById(id_producto);
			//log para el debug
			log.debug("RESPUESTA DEL SERVICIO " + respuestaServicio.getProductos().toString());
			//agregamos el mensaje al modelo el mensaje
			model.addAttribute("mensaje", respuestaServicio.getMensaje());
			//agregamos el producto traído al modelo
			model.addAttribute("VO", respuestaServicio.getProductos().get(0));
			//llamamos a la vista editar
			return new ModelAndView("editar");
		} catch (Exception e) {
			//imprimimos el error
			log.error("RESPUESTA DEL SERVICIO " + respuestaServicio.getProductos().toString(), e);
		}
		//Si no entra al catch nos manda al home de nuevo
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		return new ModelAndView("redirect:/home");
	}
	
	/**
	 * Llama al método del servicio que se encarga de actualizar los datos del
	 * producto en base de datos
	 */
	@PostMapping("/editar")
	public ModelAndView editar(@ModelAttribute Producto producto, RedirectAttributes ra) {
		//Ejecutamos el update con el servicio de la instancia de Producto
		ProductoVO respuestaServicio = svc.update(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		/** Validamos que se reescribio el producto y redirigimos al home, 
		 * sino de regreso a la form de editar
		 */
		if (respuestaServicio.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/editarForm");
		}
	}
	
	/**
	 * Abre el formulario de creación de productos
	 */
	@GetMapping("/agregarForm")
	public String agregarForm(Model model) {
		return "agregar";
	}
	
	/**
	 * Llama al método del servicio que se encarga de crear los datos del usuario en
	 * base de datos
	 */
	@PostMapping("/agregar")
	public ModelAndView agregarSubmit(@ModelAttribute Producto producto, RedirectAttributes ra) {
		//Agregamos el producto con el servicio y setteamos mensaje
		ProductoVO respuestaServicio = svc.add(producto);
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		//validamos y enviamos al home o volvemos a la página de agregar
		if (respuestaServicio.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/agregarForm");
		}
	}
	
	/**
	 * Llama al método del servicio que se encarga de eliminar los datos del producto
	 * en base de datos y redirecciona al home.
	 */
	@GetMapping("/eliminar")
	public ModelAndView eliminar(Model model, @RequestParam Integer id_producto, RedirectAttributes ra) {
		//Creamos instancia del objeto visual con los productos y setteamos el mensaje
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se pudo eliminar al usuario, intente nuevamente.");
		//borramos el producto
		try {
			//Creamos el producto y le setteamos el id a eliminar
			Producto eliminado = new Producto();
			eliminado.setId_producto(id_producto);
			//Eliminamos el producto del objeto y setteamos mensaje
			respuestaServicio = svc.delete(eliminado);
			ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
			//Devolvemos el modelo y vamos a home
			return new ModelAndView("redirect:/home");
		} catch (Exception e) {
			//debug
			log.error("Error en UsuarioController eliminar", e);
		}
		//Si no funciona el try vamos a home
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		ra.addAttribute("VO", respuestaServicio);
		return new ModelAndView("redirect:/home");
	}
	
	/**
	 * Llama al método del servicio que se encarga de buscar el producto por su nombre y redirige
	 * a la pantalla que lo muestra
	 */
	@PostMapping("/buscar")
	public ModelAndView buscar(Model model, @ModelAttribute Producto producto, @RequestParam String nombre, RedirectAttributes ra) {
		//Creamos instancia del objeto visual con los productos y setteamos el mensaje
		ProductoVO respuestaServicio = new ProductoVO();
		respuestaServicio.setMensaje("No se pudo cargar la vista de resultados de busqueda, intente nuevamente.");
		try {
			//traemos el producto con el servicio
			respuestaServicio = svc.findByNombre(nombre);
			//debug
			log.debug("RESPUESTA DEL SERVICIO " + respuestaServicio.getProductos().toString());
			//agregamos el producto y el mensaje al modelo
			model.addAttribute("mensaje", respuestaServicio.getMensaje());
			model.addAttribute("productos", respuestaServicio.getProductos());
			//devolvemos a la vista buscar
			return new ModelAndView("buscar");
		} catch (Exception e) {
			//debug
			log.error("RESPUESTA DEL SERVICIO " + respuestaServicio.getProductos().toString());
		}
		//Si no funciona el try vamos a home
		ra.addFlashAttribute("mensaje", respuestaServicio.getMensaje());
		respuestaServicio = svc.getAllProductos();
		return new ModelAndView("redirect:/home");
	}
	
}
