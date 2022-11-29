package com.cafeteriaDemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RespuestasController {

	@RequestMapping("/success")
	  public String respuesta(Model modelo) {
		modelo.addAttribute("mensaje","Acción realizada con exito");    
		return "respuestas/success";
	 }
	
	@RequestMapping("/failed")
	  public String respuestaFallo(Model modelo) {
		modelo.addAttribute("mensaje","No es posible realizar esta acción, revise que haya ingresado todos los campos");    
		return "respuestas/failed";
	 }
	
	@RequestMapping("/failedVenta")
	  public String respuestaVenta(Model modelo) {
		modelo.addAttribute("mensaje","La accion ha fallado, revise que el id del producto exista o que haya por lo menos un producto de este tipo en stock");    
		return "respuestas/failedVenta";
	 }
}
