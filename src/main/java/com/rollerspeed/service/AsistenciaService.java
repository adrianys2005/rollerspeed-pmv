package com.rollerspeed.service;

import com.rollerspeed.model.Asistencia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    private final List<Asistencia> asistencias = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public AsistenciaService() {
        asistencias.add(new Asistencia(contadorId.getAndIncrement(), 1L, "Valeria Gómez", 1L, "Iniciación y Equilibrio", "2026-09-20", "Presente", "Buen dominio de postura básica"));
        asistencias.add(new Asistencia(contadorId.getAndIncrement(), 2L, "Mateo Arango", 2L, "Técnica de Curvas y Velocidad", "2026-09-21", "Presente", "Práctica de cruce de piernas"));
        asistencias.add(new Asistencia(contadorId.getAndIncrement(), 3L, "Luciana Díaz", 3L, "Fondo y Resistencia", "2026-09-22", "Justificado", "Excusa médica por virosis"));
        asistencias.add(new Asistencia(contadorId.getAndIncrement(), 4L, "Santiago Vélez", 1L, "Iniciación y Equilibrio", "2026-09-23", "Presente", "Uso correcto de protecciones"));
        asistencias.add(new Asistencia(contadorId.getAndIncrement(), 1L, "Valeria Gómez", 1L, "Iniciación y Equilibrio", "2026-09-24", "Presente", "Avance en frenado en T"));
    }

    public List<Asistencia> obtenerTodas() {
        return new ArrayList<>(asistencias);
    }

    public List<Asistencia> obtenerPorAlumno(Long alumnoId) {
        return asistencias.stream()
                .filter(a -> a.getAlumnoId().equals(alumnoId))
                .collect(Collectors.toList());
    }

    public List<Asistencia> obtenerPorClase(Long claseId) {
        return asistencias.stream()
                .filter(a -> a.getClaseId().equals(claseId))
                .collect(Collectors.toList());
    }

    public Asistencia registrarAsistencia(Asistencia asistencia) {
        if (asistencia.getId() == null) {
            asistencia.setId(contadorId.getAndIncrement());
        }
        if (asistencia.getEstado() == null || asistencia.getEstado().trim().isEmpty()) {
            asistencia.setEstado("Presente");
        }
        asistencias.add(asistencia);
        return asistencia;
    }
}
