package com.rollerspeed.controller.api;

import com.rollerspeed.model.Clase;
import com.rollerspeed.service.ClaseService;
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
@RequestMapping("/api/v1/clases")
@Tag(name = "4. Módulo de Clases y Cupos", description = "Operaciones para programación de sesiones de entrenamiento, pistas deportivas y cálculo dinámico de cupos")
public class ClaseRestController {

    private final ClaseService claseService;

    public ClaseRestController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    @Operation(summary = "Listar programación de clases", description = "Retorna la grilla de clases activas con instructor asignado, horarios y cupos disponibles en tiempo real.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de clases obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class)))
    })
    public ResponseEntity<List<Clase>> listarClases() {
        return ResponseEntity.ok(claseService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar clase por ID", description = "Retorna la información detallada de una clase específica, su pista y cupos restantes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clase encontrada"),
            @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    public ResponseEntity<Clase> obtenerPorId(
            @Parameter(description = "Identificador único de la clase", example = "1", required = true)
            @PathVariable Long id) {
        return claseService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Crear o programar nueva clase", description = "Registra una nueva sesión deportiva en el sistema con su capacidad y pista asignada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clase programada con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Clase.class))),
            @ApiResponse(responseCode = "400", description = "Datos de clase inválidos")
    })
    public ResponseEntity<Clase> crearClase(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la nueva clase a programar", required = true,
                    content = @Content(schema = @Schema(implementation = Clase.class)))
            @RequestBody Clase clase) {
        if (clase.getNombre() == null || clase.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Clase creada = claseService.registrarClase(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}
