package com.rollerspeed.controller;

import com.rollerspeed.service.AspiranteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlumnoController {

    private final AspiranteService aspiranteService;

    public AlumnoController(AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", aspiranteService.obtenerTodos());
        model.addAttribute("totalAlumnos", aspiranteService.contarAlumnos());
        return "alumnos";
    }
}
