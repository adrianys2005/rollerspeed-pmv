package com.rollerspeed.controller;

import com.rollerspeed.model.Aspirante;
import com.rollerspeed.service.AspiranteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InscripcionController {

    private final AspiranteService aspiranteService;

    public InscripcionController(AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping("/inscripcion")
    public String mostrarFormularioInscripcion(Model model,
                                              @RequestParam(value = "exito", required = false) String exito) {
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("exito", exito != null);
        return "inscripcion";
    }

    @PostMapping("/inscripcion")
    public String procesarInscripcion(@ModelAttribute("aspirante") Aspirante aspirante,
                                     RedirectAttributes redirectAttributes) {
        aspiranteService.registrarAspirante(aspirante);
        redirectAttributes.addFlashAttribute("mensajeExito",
                "Registro completado con exito. El aspirante " + aspirante.getNombreCompleto() +
                " ha sido dado de alta como Alumno Activo con pago registrado via " + aspirante.getMetodoPago() + ".");
        return "redirect:/alumnos";
    }
}
