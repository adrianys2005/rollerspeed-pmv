package com.rollerspeed.model;

public class Servicio {
    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double tarifaMensual;
    private String horarioSugerido;
    private String icono;

    public Servicio() {}

    public Servicio(Long id, String nombre, String descripcion, String categoria,
                    double tarifaMensual, String horarioSugerido, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.tarifaMensual = tarifaMensual;
        this.horarioSugerido = horarioSugerido;
        this.icono = icono;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getTarifaMensual() { return tarifaMensual; }
    public void setTarifaMensual(double tarifaMensual) { this.tarifaMensual = tarifaMensual; }

    public String getHorarioSugerido() { return horarioSugerido; }
    public void setHorarioSugerido(String horarioSugerido) { this.horarioSugerido = horarioSugerido; }

    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
}
