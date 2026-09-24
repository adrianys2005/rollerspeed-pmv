package com.rollerspeed.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad representativa del registro de asistencia a clases de patinaje")
public class Asistencia {

    @Schema(description = "Identificador único de la asistencia", example = "1")
    private Long id;

    @Schema(description = "Identificador del alumno", example = "101")
    private Long alumnoId;

    @Schema(description = "Nombre completo del alumno", example = "Valeria Gómez")
    private String alumnoNombre;

    @Schema(description = "Identificador de la clase", example = "1")
    private Long claseId;

    @Schema(description = "Nombre o denominación de la clase", example = "Iniciación y Equilibrio")
    private String claseNombre;

    @Schema(description = "Fecha de la sesión de entrenamiento", example = "2026-09-24")
    private String fecha;

    @Schema(description = "Estado de asistencia del alumno", example = "Presente", allowableValues = {"Presente", "Ausente", "Justificado"})
    private String estado;

    @Schema(description = "Observaciones técnicas o disciplinarias", example = "Excelente avance en giros y frenada en T")
    private String observaciones;

    public Asistencia() {}

    public Asistencia(Long id, Long alumnoId, String alumnoNombre, Long claseId, String claseNombre,
                      String fecha, String estado, String observaciones) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.alumnoNombre = alumnoNombre;
        this.claseId = claseId;
        this.claseNombre = claseNombre;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public String getAlumnoNombre() { return alumnoNombre; }
    public void setAlumnoNombre(String alumnoNombre) { this.alumnoNombre = alumnoNombre; }

    public Long getClaseId() { return claseId; }
    public void setClaseId(Long claseId) { this.claseId = claseId; }

    public String getClaseNombre() { return claseNombre; }
    public void setClaseNombre(String claseNombre) { this.claseNombre = claseNombre; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
