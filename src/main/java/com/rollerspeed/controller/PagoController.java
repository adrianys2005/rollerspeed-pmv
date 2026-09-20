package com.rollerspeed.controller;

import com.rollerspeed.service.PagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/pagos")
    public String listarPagos(Model model) {
        model.addAttribute("pagos", pagoService.obtenerTodos());
        model.addAttribute("totalRecaudado", pagoService.calcularTotalRecaudado());
        model.addAttribute("pagosPendientes", pagoService.contarPendientes());
        return "pagos";
    }
}
