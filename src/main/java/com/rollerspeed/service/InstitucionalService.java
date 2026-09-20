package com.rollerspeed.service;

import com.rollerspeed.model.Evento;
import com.rollerspeed.model.Servicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InstitucionalService {
    private final List<Servicio> servicios = new ArrayList<>();
    private final List<Evento> eventos = new ArrayList<>();

    public InstitucionalService() {
        servicios.add(new Servicio(1L, "Semillero de Iniciacion", "Programa formativo para ninos y jovenes desde los 4 anos de edad, con enfasis en equilibrio y coordinacion.", "Formativo", 100000, "Martes y Jueves 8:00 AM", "bi-person-arms-up"));
        servicios.add(new Servicio(2L, "Patinaje de Velocidad y Carreras", "Entrenamiento riguroso en tecnica de salida, curva, remate y resistencia cardiovascular en pista y ruta.", "Competitivo", 130000, "Lunes a Viernes 4:00 PM", "bi-lightning-charge-fill"));
        servicios.add(new Servicio(3L, "Patinaje Recreativo para Adultos", "Clases disenadas para acondicionamiento fisico, diversion y bienestar sin importar experiencia previa.", "Salud y Recreacion", 90000, "Fines de Semana 7:00 AM", "bi-heart-pulse-fill"));
        servicios.add(new Servicio(4L, "Preparacion de Alto Rendimiento", "Acompanamiento personalizado con nutricion, psicologia deportiva y participacion en ranking nacional.", "Elite", 160000, "Lunes a Sabado Doble Jornada", "bi-trophy-fill"));

        eventos.add(new Evento(1L, "I Valida Departamental de Patinaje del Magdalena", "18 de Octubre, 2026", "Patinodromo Bolivariano de Santa Marta", "Competencia oficial de velocidad en pista con la participacion de clubes de la costa Caribe.", "Torneo Oficial", "Confirmado"));
        eventos.add(new Evento(2L, "Festival Semillero Roller Speed 2026", "2 de Noviembre, 2026", "Pista Los Almendros", "Jornada deportiva y familiar con pruebas de habilidades basicas para las categorias infantil y menor.", "Festival Formativo", "Inscripciones Abiertas"));
        eventos.add(new Evento(3L, "Campamento de Tecnica y Aerodinamica", "5 de Diciembre, 2026", "Ruta de la Sierra Nevada", "Clinica deportiva con entrenadores internacionales invitados para perfeccionar la postura y eficiencia.", "Clinica Deportiva", "Proximo"));
    }

    public List<Servicio> obtenerServicios() {
        return Collections.unmodifiableList(servicios);
    }

    public List<Evento> obtenerEventos() {
        return Collections.unmodifiableList(eventos);
    }
}
