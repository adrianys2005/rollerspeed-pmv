package com.rollerspeed.service;

import com.rollerspeed.model.Clase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClaseService {
    private final List<Clase> clases = new ArrayList<>();

    public ClaseService() {
        clases.add(new Clase(1L, "Semillero Infantil Rodantes", "Principiante", "Mar - Jue - Sab | 8:00 AM - 10:00 AM", "Diana Carolina Velez", 20, 14, "Pista de Ruta Bolivariana"));
        clases.add(new Clase(2L, "Transicion y Fondo Tecnico", "Intermedio", "Lun - Mie - Vie | 4:00 PM - 6:00 PM", "Jorge Luis Mendoza", 18, 12, "Pista Central Los Almendros"));
        clases.add(new Clase(3L, "Elite Carreras y Velocidad", "Avanzado", "Lun - Mie - Vie | 6:00 PM - 8:30 PM", "Alberto Martinez Cantillo", 15, 11, "Patinodromo Polideportivo Santa Marta"));
        clases.add(new Clase(4L, "Acondicionamiento y Patinaje Adultos", "Principiante", "Sab - Dom | 6:30 AM - 8:30 AM", "Diana Carolina Velez", 15, 8, "Pista El Rodadero"));
        clases.add(new Clase(5L, "Patinaje Artistico Formativo", "Intermedio", "Sab - Dom | 10:00 AM - 12:00 PM", "Maria Jose Serrano", 16, 9, "Coliseo Mayor Samario"));
    }

    public List<Clase> obtenerTodas() {
        return Collections.unmodifiableList(clases);
    }

    public java.util.Optional<Clase> obtenerPorId(Long id) {
        return clases.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public synchronized Clase registrarClase(Clase clase) {
        if (clase.getId() == null) {
            long nuevoId = clases.stream().mapToLong(Clase::getId).max().orElse(0) + 1;
            clase.setId(nuevoId);
        }
        clases.add(clase);
        return clase;
    }
}
