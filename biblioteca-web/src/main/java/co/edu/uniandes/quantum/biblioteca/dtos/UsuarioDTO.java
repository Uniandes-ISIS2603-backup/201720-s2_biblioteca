/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;

/**
 *
 * @author f.posada
 */
public class UsuarioDTO {
    
    private Long id;
    private String name;
    private String telefono;
    private String direccion;
    
    public UsuarioDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }
    
    public UsuarioDTO(UsuarioEntity us)
    {
        this.id=us.getId();
        this.name=us.getName();
        this.direccion=us.getDireccion();
        this.telefono=us.getTelefono();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity=new UsuarioEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        return entity;
    }
    
    
    
}
