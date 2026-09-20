package com.rollerspeed.model;

public class Evento {
    private Long id;
    private String titulo;
    private String fecha;
    private String lugar;
    private String descripcion;
    private String categoria;
    private String estado;

    public Evento() {}

    public Evento(Long id, String titulo, String fecha, String lugar,
                  String descripcion, String categoria, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
