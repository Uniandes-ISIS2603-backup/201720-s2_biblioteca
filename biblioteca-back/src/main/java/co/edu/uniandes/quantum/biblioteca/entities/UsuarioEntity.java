/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Clase que representa un usuario de biblioteca
 * @author f.posada
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
    /**
     * Telefono del usuario
     */
    private String telefono;
    
    /**
     * Direccion de residencia del usuario
     */
    private String direccion;

    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrestamoEntity> prestamos = new ArrayList<PrestamoEntity>();
    
    //@OneToMany(mappedBy = "multa", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<MultaEntity> multas = new ArrayList<MultaEntity>();
    
    //@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    // @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ComentarioEntity> comentarios = new ArrayList<ComentarioEntity>();


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
    
    
    
}
