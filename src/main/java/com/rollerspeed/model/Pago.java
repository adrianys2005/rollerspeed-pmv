package com.rollerspeed.model;

public class Pago {
    private Long id;
    private String alumnoNombre;
    private String documento;
    private String concepto;
    private double monto;
    private String metodoPago;
    private String estado;
    private String fecha;
    private String referencia;

    public Pago() {}

    public Pago(Long id, String alumnoNombre, String documento, String concepto,
                double monto, String metodoPago, String estado, String fecha, String referencia) {
        this.id = id;
        this.alumnoNombre = alumnoNombre;
        this.documento = documento;
        this.concepto = concepto;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.fecha = fecha;
        this.referencia = referencia;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlumnoNombre() { return alumnoNombre; }
    public void setAlumnoNombre(String alumnoNombre) { this.alumnoNombre = alumnoNombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}
