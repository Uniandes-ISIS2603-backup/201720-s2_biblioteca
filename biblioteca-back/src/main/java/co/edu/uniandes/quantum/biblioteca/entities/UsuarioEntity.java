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
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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

    @PodamExclude
    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrestamoEntity> prestamos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<MultaEntity> multas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentarios = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioPagoEntity> medioPago=new ArrayList<>();

    public List<MedioPagoEntity> getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(List<MedioPagoEntity> medioPago) {
        this.medioPago = medioPago;
    }

    public List<PrestamoEntity> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoEntity> prestamos) {
        this.prestamos = prestamos;
    }

    public List<MultaEntity> getMultas() {
        return multas;
    }

    public void setMultas(List<MultaEntity> multas) {
        this.multas = multas;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
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
    
    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
      return false;
    }
        UsuarioEntity us=(UsuarioEntity)obj;
        if(us.telefono.equals(us.getTelefono())&&us.direccion.equals(us.getDireccion()))
        {
            return true;
        }
        return false;
    }
    
    
    
}
