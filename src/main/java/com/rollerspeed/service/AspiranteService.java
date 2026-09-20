package com.rollerspeed.service;

import com.rollerspeed.model.Aspirante;
import com.rollerspeed.model.Pago;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AspiranteService {
    private final List<Aspirante> aspirantes = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);
    private final PagoService pagoService;

    public AspiranteService(PagoService pagoService) {
        this.pagoService = pagoService;
        inicializarDatos();
    }

    private void inicializarDatos() {
        aspirantes.add(new Aspirante(seq.getAndIncrement(), "Mariana Sofia Pacheco", "TI", "1082938471", "2012-05-14", "Femenino", "mariana.pacheco@gmail.com", "3004589211", "Barrio Bavaria, Santa Marta", "Intermedio", "PSE", "Activo", "2026-08-15"));
        aspirantes.add(new Aspirante(seq.getAndIncrement(), "Carlos Andres Bermudez", "CC", "1083472819", "2006-11-20", "Masculino", "carlos.bermudez@hotmail.com", "3128471928", "El Rodadero, Santa Marta", "Avanzado", "Transferencia Bancaria", "Activo", "2026-08-20"));
        aspirantes.add(new Aspirante(seq.getAndIncrement(), "Valeria Lucia Gomez", "RC", "1081928374", "2016-03-08", "Femenino", "padres.gomez@gmail.com", "3159283746", "Mamatoco, Santa Marta", "Principiante", "Efectivo", "Activo", "2026-09-01"));
        aspirantes.add(new Aspirante(seq.getAndIncrement(), "Juan David Fernandez", "TI", "1085738291", "2010-09-25", "Masculino", "juan.fernandez@outlook.com", "3209182736", "Jardin, Santa Marta", "Principiante", "Tarjeta de Credito", "Activo", "2026-09-04"));
        aspirantes.add(new Aspirante(seq.getAndIncrement(), "Camila Andrea Rojas", "TI", "1089283746", "2009-01-18", "Femenino", "camila.rojas@gmail.com", "3018273645", "Bello Horizonte, Santa Marta", "Avanzado", "PSE", "Activo", "2026-09-12"));
    }

    public synchronized List<Aspirante> obtenerTodos() {
        return Collections.unmodifiableList(new ArrayList<>(aspirantes));
    }

    public synchronized Aspirante registrarAspirante(Aspirante aspirante) {
        aspirante.setId(seq.getAndIncrement());
        aspirante.setEstado("Activo");
        if (aspirante.getFechaRegistro() == null || aspirante.getFechaRegistro().isBlank()) {
            aspirante.setFechaRegistro(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        }
        aspirantes.add(0, aspirante);

        // Registro automatico del pago inicial segun requerimiento del caso de estudio
        double montoInscripcion = 120000;
        String concepto = "Inscripcion en linea - Nivel " + (aspirante.getNivel() != null ? aspirante.getNivel() : "Principiante");
        Pago nuevoPago = new Pago(
                null,
                aspirante.getNombreCompleto(),
                aspirante.getNumeroDocumento(),
                concepto,
                montoInscripcion,
                aspirante.getMetodoPago(),
                "Pagado",
                aspirante.getFechaRegistro(),
                "AUT-" + System.currentTimeMillis() % 100000
        );
        pagoService.registrarPago(nuevoPago);

        return aspirante;
    }

    public synchronized long contarAlumnos() {
        return aspirantes.size();
    }
}
