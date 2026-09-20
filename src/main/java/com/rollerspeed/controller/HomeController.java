package com.rollerspeed.controller;

import com.rollerspeed.service.AspiranteService;
import com.rollerspeed.service.ClaseService;
import com.rollerspeed.service.InstitucionalService;
import com.rollerspeed.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final InstitucionalService institucionalService;
    private final AspiranteService aspiranteService;
    private final InstructorService instructorService;
    private final ClaseService claseService;

    public HomeController(InstitucionalService institucionalService,
                          AspiranteService aspiranteService,
                          InstructorService instructorService,
                          ClaseService claseService) {
        this.institucionalService = institucionalService;
        this.aspiranteService = aspiranteService;
        this.instructorService = instructorService;
        this.claseService = claseService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("totalAlumnos", aspiranteService.contarAlumnos());
        model.addAttribute("totalInstructores", instructorService.obtenerTodos().size());
        model.addAttribute("totalClases", claseService.obtenerTodas().size());
        model.addAttribute("serviciosDestacados", institucionalService.obtenerServicios());
        model.addAttribute("proximosEventos", institucionalService.obtenerEventos());
        return "index";
    }

    @GetMapping("/mision")
    public String mision() {
        return "mision";
    }

    @GetMapping("/vision")
    public String vision() {
        return "vision";
    }

    @GetMapping("/valores")
    public String valores() {
        return "valores";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("servicios", institucionalService.obtenerServicios());
        return "servicios";
    }

    @GetMapping("/eventos")
    public String eventos(Model model) {
        model.addAttribute("eventos", institucionalService.obtenerEventos());
        return "eventos";
    }
}
