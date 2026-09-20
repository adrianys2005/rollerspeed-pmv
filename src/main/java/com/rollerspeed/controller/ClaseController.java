package com.rollerspeed.controller;

import com.rollerspeed.service.ClaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping("/clases")
    public String listarClases(Model model) {
        model.addAttribute("clases", claseService.obtenerTodas());
        return "clases";
    }
}
