package com.AgroEnvios.apiAE.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "envio_detalle")
public class EnvioDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id") // Nombre de la columna en la base de datos
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "envio", nullable = false) // Relación con Envio
    @JsonIgnore
    private Envio envio;

    @Column(name = "cantidad", nullable = false)
    private int cantidad; // Cantidad del producto en el envío

    // Constructor vacío requerido por JPA
    public EnvioDetalle() {
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Envio getEnvio() {
        return this.envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
