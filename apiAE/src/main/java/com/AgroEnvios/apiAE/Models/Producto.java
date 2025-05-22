package com.AgroEnvios.apiAE.Models;

import java.util.ArrayList;
import java.util.List;

import com.AgroEnvios.apiAE.Enums.Unidad;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto") // Nombre de la tabla en la base de datos
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id") // Nombre de la columna en la base de datos
    private int id;
    
    @Column(name = "nombre", nullable = false) // Nombre del producto, no puede ser nulo
    private String nombre; // Nombre del producto

    @Column(name = "descripcion")
    private String descripcion; // Descripción del producto
    
    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida", nullable = false)
    private Unidad unidadMedida;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad; // Cantidad del producto en inventario

    // Relación con EnvioDetalle

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<EnvioDetalle> envioDetalle = new ArrayList<>();

    // Constructor vacío (requerido por JPA)
    
    public Producto() {
    }

    //Getters y Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Unidad getUnidadMedida() {
        return this.unidadMedida;
    }

    public void setUnidadMedida(Unidad unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<EnvioDetalle> getEnvioDetalle() {
        return this.envioDetalle;
    }

    public void setEnvioDetalle(List<EnvioDetalle> envioDetalle) {
        this.envioDetalle = envioDetalle;
    }
}
