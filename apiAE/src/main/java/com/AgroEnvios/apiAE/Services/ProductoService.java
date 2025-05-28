package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroEnvios.apiAE.Enums.Unidad;
import com.AgroEnvios.apiAE.Models.Producto;
import com.AgroEnvios.apiAE.repo.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Implementación de métodos para manejar productos

    public Producto crearProducto(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

        public Producto crearProductoVacio() {
        Producto producto = new Producto();
        producto.setNombre("");
        producto.setDescripcion("");
        producto.setUnidadMedida(Unidad.Libra);
        producto.setCantidad(0);
        productoRepository.save(producto);
        return producto;
    }

    public void actualizarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public void sumarStock(int id, int cantidad) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setCantidad(producto.getCantidad() + cantidad);
            actualizarProducto(producto);
        }
    }
    
}
