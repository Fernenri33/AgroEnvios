package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AgroEnvios.apiAE.Models.Rol;
import com.AgroEnvios.apiAE.repo.RolRepository;

@Service
public class RolService {

    @Autowired 
    private RolRepository rolRepository;

    //Metodo para obtener todos los roles
    public List<Rol> getTodosLosRoles(){
        List<Rol> roles = rolRepository.findAll();
        return roles;
    }

    //Metodo para obtener un rol por su ID
    public Rol getRolPorId(int id) {
        return rolRepository.findById(id).orElse(null);
    }

    //Metodo para guardar un nuevo rol
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    //Metodo para eliminar un rol por su ID
    public void eliminarRol(int id) {
        rolRepository.deleteById(id);
    }
}
