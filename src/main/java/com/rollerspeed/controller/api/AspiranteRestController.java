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

@RestController
@RequestMapping("/api/v1/aspirantes")
@Tag(name = "1. Módulo de Aspirantes e Inscripción", description = "Operaciones para la recepción, consulta y registro autónomo de nuevos aspirantes a Roller Speed")
public class AspiranteRestController {

    private final AspiranteService aspiranteService;

    public AspiranteRestController(AspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los aspirantes", description = "Retorna la colección completa de aspirantes registrados en el sistema, ordenados por fecha de inscripción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aspirantes obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aspirante.class)))
    })
    public ResponseEntity<List<Aspirante>> listarTodos() {
        return ResponseEntity.ok(aspiranteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar aspirante por ID", description = "Retorna los datos detallados de un aspirante específico mediante su identificador numérico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aspirante encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aspirante.class))),
            @ApiResponse(responseCode = "404", description = "Aspirante no encontrado con el ID suministrado",
                    content = @Content)
    })
    public ResponseEntity<Aspirante> obtenerPorId(
            @Parameter(description = "Identificador único del aspirante", example = "1", required = true)
            @PathVariable Long id) {
        return aspiranteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo aspirante en línea",
            description = "Procesa el formulario de inscripción autónoma. Al dar de alta al aspirante, el sistema actualiza automáticamente el directorio de alumnos y genera el comprobante inicial de pago.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aspirante registrado y matriculado con éxito",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aspirante.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos o incompletos",
                    content = @Content)
    })
    public ResponseEntity<Aspirante> registrarAspirante(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del aspirante a registrar", required = true,
                    content = @Content(schema = @Schema(implementation = Aspirante.class)))
            @RequestBody Aspirante aspirante) {
        if (aspirante.getNombreCompleto() == null || aspirante.getNombreCompleto().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Aspirante registrado = aspiranteService.registrarAspirante(aspirante);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
    }
}
