package com.rollerspeed.model;

public class Instructor {
    private Long id;
    private String nombreCompleto;
    private String documento;
    private String email;
    private String telefono;
    private String especialidad;
    private int experienciaAnios;
    private String nivelAsignado;
    private String horarioTurno;

    public Instructor() {}

    public Instructor(Long id, String nombreCompleto, String documento, String email, String telefono,
                      String especialidad, int experienciaAnios, String nivelAsignado, String horarioTurno) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
        this.nivelAsignado = nivelAsignado;
        this.horarioTurno = horarioTurno;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public int getExperienciaAnios() { return experienciaAnios; }
    public void setExperienciaAnios(int experienciaAnios) { this.experienciaAnios = experienciaAnios; }

    public String getNivelAsignado() { return nivelAsignado; }
    public void setNivelAsignado(String nivelAsignado) { this.nivelAsignado = nivelAsignado; }

    public String getHorarioTurno() { return horarioTurno; }
    public void setHorarioTurno(String horarioTurno) { this.horarioTurno = horarioTurno; }
}
