package com.rollerspeed.controller.api;

import com.rollerspeed.model.Evento;
import com.rollerspeed.model.Servicio;
import com.rollerspeed.service.InstitucionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/institucional")
@Tag(name = "7. Módulo de Información Institucional", description = "Endpoints para consulta de catálogo de servicios, planes, cronograma deportivo y filosofía corporativa")
public class InstitucionalRestController {

    private final InstitucionalService institucionalService;

    public InstitucionalRestController(InstitucionalService institucionalService) {
        this.institucionalService = institucionalService;
    }

    @GetMapping("/servicios")
    @Operation(summary = "Listar planes y servicios deportivos", description = "Retorna el portafolio de programas formativos, competitivos y recreativos con sus respectivas tarifas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catálogo de servicios obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class)))
    })
    public ResponseEntity<List<Servicio>> listarServicios() {
        return ResponseEntity.ok(institucionalService.obtenerServicios());
    }

    @GetMapping("/eventos")
    @Operation(summary = "Listar cronograma deportivo y válidas", description = "Retorna el calendario de competencias oficiales, festivales y campamentos de patinaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cronograma de eventos obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)))
    })
    public ResponseEntity<List<Evento>> listarEventos() {
        return ResponseEntity.ok(institucionalService.obtenerEventos());
    }

    @GetMapping("/corporativo")
    @Operation(summary = "Obtener información corporativa (Misión, Visión, Valores)", description = "Retorna los pilares estratégicos y la filosofía institucional de Roller Speed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información corporativa obtenida")
    })
    public ResponseEntity<Map<String, Object>> obtenerInfoCorporativa() {
        return ResponseEntity.ok(Map.of(
                "nombreInstitucion", "Escuela de Patinaje Roller Speed",
                "sedePrincipal", "Santa Marta, Magdalena, Colombia",
                "mision", "Formar atletas íntegros a través de la disciplina del patinaje de velocidad y artístico, fomentando valores de perseverancia, juego limpio y superación personal.",
                "vision", "Para el año 2030, ser reconocidos como el semillero y club de patinaje líder en la región Caribe colombiana, aportando campeones a la selección nacional.",
                "valores", List.of("Disciplina", "Pasión por el Deporte", "Trabajo en Equipo", "Respeto", "Excelencia Deportiva")
        ));
    }
}
