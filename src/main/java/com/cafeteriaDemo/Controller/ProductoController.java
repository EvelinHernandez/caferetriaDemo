package com.cafeteriaDemo.Controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.cafeteriaDemo.Entity.Producto;
import com.cafeteriaDemo.Service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService<Producto> productoService;

	//Lista de los productos
	@RequestMapping(value="/index", method = RequestMethod.GET )
	public String listaProductos(Model model) {
		List<Producto> pList = productoService.findAll();
		model.addAttribute("pList", pList);
		return "productos/index";
	}
	
	//añadir productos
	@RequestMapping(value = "/insertar", method = {RequestMethod.POST, RequestMethod.GET})
	public String insertarProductos(){
		return "productos/insertar";
	}
	
	@RequestMapping(value = "/newProducto", method = {RequestMethod.POST, RequestMethod.GET})	
    public String saveProductos(@ModelAttribute("producto") Producto producto) {
		
		if(producto.getNombreProducto()==null || producto.getCategoria()==null || producto.getPeso()==null || 
				producto.getPrecio()==null || producto.getReferencia()==null || producto.getStock()==null ) {
			return "redirect:/failed";
		}
		
		producto.setFechaCreacion(new Date());
        boolean respuesta = productoService.save(producto);
        if(respuesta) return "redirect:/success";
		else return "redirect:/failed";
    }
	

	//Editar productos
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editarProductos(@PathVariable("id") Integer id, Model model) {
		Optional<Producto> editar = productoService.findByIdProducto(id);
		model.addAttribute("productoEditar", editar.get());
		
		return "productos/editar"; 
	}
	
	@RequestMapping(value = "/editProducto/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	public String editarProductos(@PathVariable Integer id, 
            @ModelAttribute("producto") Producto producto,
            Model model){
		
		Optional<Producto> getProducto = productoService.findByIdProducto(id);
		Producto editarProducto = getProducto.get();
				
		editarProducto.setNombreProducto(producto.getNombreProducto());
		editarProducto.setReferencia(producto.getReferencia());
		editarProducto.setPrecio(producto.getPrecio());
		editarProducto.setPeso(producto.getPeso());
		editarProducto.setCategoria(producto.getCategoria());
		editarProducto.setStock(producto.getStock());
		
		boolean respuesta = productoService.update(id, editarProducto);		
		if(respuesta) return "redirect:/success";
		else return "redirect:/failed";
	}
	
	
	//Eliminar producto
	@RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	public String eliminarProductos(@PathVariable("id") Integer id, Model model){
		productoService.delete(id);			
		List<Producto> pList = productoService.findAll();
		model.addAttribute("pList", pList);		
		return "productos/index";
	}
	
	//Consultar que producto tiene mas stock
		@RequestMapping(value = "/consultaStock", method = {RequestMethod.POST, RequestMethod.GET})
		public String consultaStock(Model modelo){
			String respuesta = productoService.consultaStock();			
			modelo.addAttribute("respuesta",respuesta);    
			return "respuestas/consultas";
		}

	//Cargar Csv a bd
	@GetMapping("/cargarCSV")
	public ResponseEntity<Boolean> cargarCsv() throws ParseException {
		Boolean cargado = productoService.cargarCSV();
		return new ResponseEntity<Boolean>(cargado, HttpStatus.OK);		
	}
}
