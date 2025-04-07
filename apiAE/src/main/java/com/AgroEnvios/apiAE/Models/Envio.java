package com.AgroEnvios.apiAE.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.AgroEnvios.apiAE.Enums.Estado;

@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "supervisor", referencedColumnName = "id")
    private Usuario supervisor;

    @OneToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "id")
    private Usuario proveedor;

    @Column(name = "comentario_proveedor", columnDefinition = "TEXT")
    private String comentarioProveedor;

    @Column(name = "motorisata")
    private String motorisata;

    @Column(name = "comentario_supervisor", columnDefinition = "TEXT")
    private String comentarioSupervisor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (Kilo, Libra, etc.)
    @Column(name = "unidad_medida", nullable = false)
    private Estado estado;

    // Relación con EnvioDetalle
    
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<EnvioDetalle> envioDetalle = new ArrayList<>();

    // Constructor vacío (requerido por JPA)

    public Envio() {
    }

    // Getters y Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Usuario supervisor) {
        this.supervisor = supervisor;
    }

    public Usuario getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Usuario proveedor) {
        this.proveedor = proveedor;
    }

    public String getComentarioProveedor() {
        return this.comentarioProveedor;
    }

    public void setComentarioProveedor(String comentarioProveedor) {
        this.comentarioProveedor = comentarioProveedor;
    }

    public String getMotorisata() {
        return this.motorisata;
    }

    public void setMotorisata(String motorisata) {
        this.motorisata = motorisata;
    }

    public String getComentarioSupervisor() {
        return this.comentarioSupervisor;
    }

    public void setComentarioSupervisor(String comentarioSupervisor) {
        this.comentarioSupervisor = comentarioSupervisor;
    }

    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDateTime getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaEntrega() {
        return this.fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado Estado) {
        this.estado = Estado;
    }

    public List<EnvioDetalle> getEnvioDetalle() {
        return this.envioDetalle;
    }

    public void setEnvioDetalle(List<EnvioDetalle> envioDetalle) {
        this.envioDetalle = envioDetalle;
    }
}
