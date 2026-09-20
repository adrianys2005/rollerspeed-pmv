package com.rollerspeed.controller;

import com.rollerspeed.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructores")
    public String listarInstructores(Model model) {
        model.addAttribute("instructores", instructorService.obtenerTodos());
        return "instructores";
    }
}
