package com.rollerspeed.controller.api;

import com.rollerspeed.model.Instructor;
import com.rollerspeed.service.InstructorService;
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
@RequestMapping("/api/v1/instructores")
@Tag(name = "3. Módulo de Instructores", description = "Endpoints para consulta de información y especialidad del cuerpo técnico de Roller Speed")
public class InstructorRestController {

    private final InstructorService instructorService;

    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    @Operation(summary = "Listar cuerpo técnico", description = "Retorna todos los instructores y entrenadores vinculados a la escuela, con su experiencia y turno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuerpo técnico obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class)))
    })
    public ResponseEntity<List<Instructor>> listarInstructores() {
        return ResponseEntity.ok(instructorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar instructor por ID", description = "Obtiene los detalles del instructor, especialidad formativa y disponibilidad horaria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor encontrado"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    public ResponseEntity<Instructor> obtenerPorId(
            @Parameter(description = "Identificador único del instructor", example = "1", required = true)
            @PathVariable Long id) {
        return instructorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
