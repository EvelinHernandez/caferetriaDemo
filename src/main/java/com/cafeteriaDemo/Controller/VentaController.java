package com.cafeteriaDemo.Controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
import com.cafeteriaDemo.Entity.Venta;
import com.cafeteriaDemo.Service.ProductoService;
import com.cafeteriaDemo.Service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService<Venta> ventaService;

	//Lista de los productos
	@RequestMapping(value="/listaVentas", method = RequestMethod.GET )
	public String listaVentas(Model model, Model modeloR) {
		List<Venta> vList = ventaService.findAll();
		model.addAttribute("vList", vList);
		modeloR.addAttribute("respuestaVenta", "sin respuesta");
		return "ventas/index";
	}
	
	//añadir productos
	@RequestMapping(value = "/insertarVenta", method = {RequestMethod.POST, RequestMethod.GET})
	public String insertarVenta(){
		return "ventas/insertar";
	}
	
	@RequestMapping(value = "/newVenta", method = {RequestMethod.POST, RequestMethod.GET})	
    public String saveVenta(@ModelAttribute("venta") Venta venta, Model modelo) {
		
		if(venta.getIdProducto()==null || venta.getCantidad()==null) {
			return "redirect:/failed";
		}
		
		Boolean respuesta = ventaService.save(venta);
		if(respuesta) return "redirect:/success";
		else return "redirect:/failedVenta";
    }
	
	//Consultar que id es mas vendido
			@RequestMapping(value = "/consultaIdMasVendido", method = {RequestMethod.POST, RequestMethod.GET})
			public String consultaId(Model modelo){
				String respuesta = ventaService.consultaMasVendido();			
				modelo.addAttribute("respuesta",respuesta);    
				return "respuestas/consultas";
			}

}
