package com.rollerspeed.model;

public class Clase {
    private Long id;
    private String nombre;
    private String nivel;
    private String horario;
    private String instructor;
    private int cupoMaximo;
    private int inscritos;
    private String pista;

    public Clase() {}

    public Clase(Long id, String nombre, String nivel, String horario, String instructor,
                 int cupoMaximo, int inscritos, String pista) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.horario = horario;
        this.instructor = instructor;
        this.cupoMaximo = cupoMaximo;
        this.inscritos = inscritos;
        this.pista = pista;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public int getInscritos() { return inscritos; }
    public void setInscritos(int inscritos) { this.inscritos = inscritos; }

    public String getPista() { return pista; }
    public void setPista(String pista) { this.pista = pista; }

    public int getCupoDisponible() { return cupoMaximo - inscritos; }
}
