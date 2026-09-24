package com.rollerspeed.service;

import com.rollerspeed.model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InstructorService {
    private final List<Instructor> instructores = new ArrayList<>();

    public InstructorService() {
        instructores.add(new Instructor(1L, "Prof. Alberto Martinez Cantillo", "85491029", "alberto.martinez@rollerspeed.com", "3015551234", "Patinaje de Velocidad y Carreras", 10, "Avanzado", "Lunes a Viernes 4:00 PM - 7:00 PM"));
        instructores.add(new Instructor(2L, "Lic. Diana Carolina Velez", "1082345678", "diana.velez@rollerspeed.com", "3104445678", "Iniciacion Deportiva y Semilleros", 6, "Principiante", "Martes, Jueves y Sabados 8:00 AM - 11:00 AM"));
        instructores.add(new Instructor(3L, "Entr. Jorge Luis Mendoza", "77182930", "jorge.mendoza@rollerspeed.com", "3189998877", "Tecnica de Fondo y Resistencia", 8, "Intermedio", "Lunes, Miercoles y Viernes 5:00 PM - 8:00 PM"));
        instructores.add(new Instructor(4L, "Lic. Maria Jose Serrano", "1085443322", "mariajose.serrano@rollerspeed.com", "3023332211", "Patinaje Artistico y Coreografia", 5, "Formativo", "Sabados y Domingos 9:00 AM - 1:00 PM"));
    }

    public List<Instructor> obtenerTodos() {
        return Collections.unmodifiableList(instructores);
    }

    public java.util.Optional<Instructor> obtenerPorId(Long id) {
        return instructores.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
