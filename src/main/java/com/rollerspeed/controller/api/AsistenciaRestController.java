package com.rollerspeed.controller.api;

import com.rollerspeed.model.Asistencia;
import com.rollerspeed.service.AsistenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
@Tag(name = "6. Módulo de Asistencia", description = "Endpoints para el control y registro de asistencia de los alumnos a los entrenamientos en pista")
public class AsistenciaRestController {

    private final AsistenciaService asistenciaService;

    public AsistenciaRestController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    @Operation(summary = "Listar registros de asistencia", description = "Retorna todos los registros de asistencia tomados en las diferentes pistas y horarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros de asistencia obtenidos exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asistencia.class)))
    })
    public ResponseEntity<List<Asistencia>> listarTodas() {
        return ResponseEntity.ok(asistenciaService.obtenerTodas());
    }

    @GetMapping("/alumno/{alumnoId}")
    @Operation(summary = "Historial de asistencia por alumno", description = "Consulta las asistencias y faltas registradas para un alumno específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial del alumno obtenido")
    })
    public ResponseEntity<List<Asistencia>> obtenerPorAlumno(
            @Parameter(description = "Identificador único del alumno", example = "1", required = true)
            @PathVariable Long alumnoId) {
        return ResponseEntity.ok(asistenciaService.obtenerPorAlumno(alumnoId));
    }

    @GetMapping("/clase/{claseId}")
    @Operation(summary = "Lista de asistencia por clase", description = "Consulta la lista de asistencia correspondiente a una clase o sesión determinada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de la clase obtenida")
    })
    public ResponseEntity<List<Asistencia>> obtenerPorClase(
            @Parameter(description = "Identificador único de la clase", example = "1", required = true)
            @PathVariable Long claseId) {
        return ResponseEntity.ok(asistenciaService.obtenerPorClase(claseId));
    }

    @PostMapping
    @Operation(summary = "Registrar asistencia de un alumno", description = "Registra la presencia, ausencia o justificación de un alumno en una sesión de patinaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asistencia registrada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Asistencia.class))),
            @ApiResponse(responseCode = "400", description = "Datos de asistencia incompletos")
    })
    public ResponseEntity<Asistencia> registrarAsistencia(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del registro de asistencia", required = true,
                    content = @Content(schema = @Schema(implementation = Asistencia.class)))
            @RequestBody Asistencia asistencia) {
        if (asistencia.getAlumnoId() == null || asistencia.getClaseId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Asistencia registrada = asistenciaService.registrarAsistencia(asistencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrada);
    }
}
