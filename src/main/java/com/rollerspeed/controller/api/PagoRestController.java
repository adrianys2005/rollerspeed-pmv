package com.rollerspeed.controller.api;

import com.rollerspeed.model.Pago;
import com.rollerspeed.service.PagoService;
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
@RequestMapping("/api/v1/pagos")
@Tag(name = "5. Módulo de Pagos y Finanzas", description = "Endpoints para el registro, consulta y métricas financieras de pagos y mensualidades")
public class PagoRestController {

    private final PagoService pagoService;

    public PagoRestController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    @Operation(summary = "Listar transacciones y comprobantes de pago", description = "Retorna el historial completo de pagos emitidos en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial de pagos obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class)))
    })
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar comprobante de pago por ID", description = "Obtiene los detalles de una transacción específica por su número de comprobante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comprobante de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Comprobante no encontrado")
    })
    public ResponseEntity<Pago> obtenerPorId(
            @Parameter(description = "Identificador del comprobante de pago", example = "1", required = true)
            @PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/metricas")
    @Operation(summary = "Obtener balance financiero y métricas de recaudo", description = "Calcula el total recaudado, el número de pagos pendientes y el total de transacciones registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Métricas financieras calculadas con éxito")
    })
    public ResponseEntity<Map<String, Object>> obtenerMetricas() {
        return ResponseEntity.ok(Map.of(
                "totalRecaudado", pagoService.calcularTotalRecaudado(),
                "pagosPendientes", pagoService.contarPendientes(),
                "totalTransacciones", pagoService.obtenerTodos().size(),
                "moneda", "COP"
        ));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo pago o mensualidad", description = "Registra una transacción manual o en línea por concepto de matrícula, mensualidad o indumentaria deportiva.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class))),
            @ApiResponse(responseCode = "400", description = "Datos de pago inválidos")
    })
    public ResponseEntity<Pago> registrarPago(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del pago a registrar", required = true,
                    content = @Content(schema = @Schema(implementation = Pago.class)))
            @RequestBody Pago pago) {
        if (pago.getAlumnoNombre() == null || pago.getMonto() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        pagoService.registrarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }
}
