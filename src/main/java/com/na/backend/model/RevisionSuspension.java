package com.na.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "revisiones_suspension")
public class RevisionSuspension {

    @Id
    @Column(name = "rs_codigo")
    private String codigo;

    @Column(name = "rs_fecha_revision")
    private LocalDate fecha_revision;

    @Column(name = "rs_estado_revision")
    private String estado_revision;

    @Column(name = "rs_observacion")
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_suspension", referencedColumnName = "sus_codigo")
    private Suspensiones suspensiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_moderador", referencedColumnName = "mod_codigo")
    private Moderador moderador;

    public RevisionSuspension(String codigo, LocalDate fecha_revision, String estado_revision, String observacion,
            Suspensiones suspensiones, Moderador moderador) {
        this.codigo = codigo;
        this.fecha_revision = fecha_revision;
        this.estado_revision = estado_revision;
        this.observacion = observacion;
        this.suspensiones = suspensiones;
        this.moderador = moderador;
    }

    public RevisionSuspension() {
    }

    @Override
    public String toString() {
        return "RevisionSuspension{" +
                "codigo='" + codigo + '\'' +
                ", fecha_revision=" + fecha_revision +
                ", estado_revision='" + estado_revision + '\'' +
                ", observacion='" + observacion + '\'' +
                ", suspensiones=" + suspensiones +
                ", moderador=" + moderador +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha_revision() {
        return fecha_revision;
    }

    public void setFecha_revision(LocalDate fecha_revision) {
        this.fecha_revision = fecha_revision;
    }

    public String getEstado_revision() {
        return estado_revision;
    }

    public void setEstado_revision(String estado_revision) {
        this.estado_revision = estado_revision;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Suspensiones getSuspensiones() {
        return suspensiones;
    }

    public void setSuspensiones(String codigo) {
        if (codigo != null) {
            Suspensiones suspensiones = new Suspensiones();
            suspensiones.setCodigo(codigo);
            this.suspensiones = suspensiones;
        }
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(String codigo) {

        if (codigo != null) {
            Moderador moderador = new Moderador();
            moderador.setCodigo(codigo);
            this.moderador = moderador;
        }
    }
}
