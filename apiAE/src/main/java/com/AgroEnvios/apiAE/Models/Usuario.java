package com.AgroEnvios.apiAE.Models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")

public class Usuario {

    // Variables de la clase
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "organizacion", referencedColumnName = "id")
    private Organizacion organizacion;
@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario"),
        inverseJoinColumns = @JoinColumn(name = "rol")
    )
    private Set<Rol> roles = new HashSet<>();

    // Constructor por defecto

    public Usuario() {
    }

    // Métodos Getter y Setter

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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Organizacion getOrganizacion() {
        return this.organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public String getApellido() {
        return this.apellido;
    }


    public Set<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
