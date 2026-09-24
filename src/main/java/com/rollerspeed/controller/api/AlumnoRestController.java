package com.rollerspeed.controller.api;

import com.rollerspeed.model.Aspirante;
import com.rollerspeed.service.AspiranteService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alumnos")
@Tag(name = "2. Módulo de Alumnos", description = "Operaciones de consulta y administración del directorio de alumnos activos y categorizados por nivel")
public class AlumnoRestController {

    private final AspiranteService aspiranteService;

    public AlumnoRestController(AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping
    @Operation(summary = "Listar directorio de alumnos", description = "Retorna todos los alumnos registrados y matriculados en la escuela de patinaje Roller Speed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Directorio de alumnos obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aspirante.class)))
    })
    public ResponseEntity<List<Aspirante>> listarAlumnos() {
        return ResponseEntity.ok(aspiranteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar alumno por ID", description = "Obtiene la ficha técnica y datos de contacto de un alumno matriculado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public ResponseEntity<Aspirante> obtenerPorId(
            @Parameter(description = "Identificador único del alumno", example = "1", required = true)
            @PathVariable Long id) {
        return aspiranteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nivel/{nivel}")
    @Operation(summary = "Filtrar alumnos por nivel deportivo", description = "Filtra la lista de alumnos según su nivel: Principiante, Intermedio o Avanzado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista filtrada por nivel")
    })
    public ResponseEntity<List<Aspirante>> filtrarPorNivel(
            @Parameter(description = "Nivel formativo", example = "Principiante", required = true)
            @PathVariable String nivel) {
        return ResponseEntity.ok(aspiranteService.obtenerPorNivel(nivel));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de matrícula del alumno", description = "Permite modificar el estado operativo del alumno (Activo, Inactivo, Retirado).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public ResponseEntity<Map<String, Object>> actualizarEstado(
            @Parameter(description = "ID del alumno", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevo estado de la matrícula", example = "Inactivo", required = true)
            @RequestParam String estado) {
        boolean actualizado = aspiranteService.actualizarEstado(id, estado);
        if (actualizado) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Estado del alumno actualizado exitosamente",
                    "id", id,
                    "nuevoEstado", estado
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Alumno no encontrado"));
        }
    }
}
