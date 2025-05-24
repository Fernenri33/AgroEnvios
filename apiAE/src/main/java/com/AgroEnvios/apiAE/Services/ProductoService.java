package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroEnvios.apiAE.Models.Producto;
import com.AgroEnvios.apiAE.repo.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Implementación de métodos para manejar productos

    public void crearProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    
}
