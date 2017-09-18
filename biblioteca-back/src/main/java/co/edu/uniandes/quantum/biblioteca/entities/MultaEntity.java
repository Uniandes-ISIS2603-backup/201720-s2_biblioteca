/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.sanmiguel
 */
@Entity
public class MultaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne    
    private UsuarioEntity miUsuario;
    
    /**
     * Fecha en que se pusó la multa.
     */
     @Temporal(TemporalType.DATE)
    private Date fecha;
    
    /**
     * Costo de la multa.
     */
    private double costo;
    
    /**
     * Veifica si ya está pagada la multa.
     */
    private boolean pagada;
    
    /**
     * Descripción de la multa.
     */
    private String descripcion;
    
    /**
     * Nombre del servicio por el que se creó la multa.
     */
    private String tituloServicio;
    
    /**
     * Medio de pago de la multa.
     */
    @PodamExclude
    @OneToOne
    private MedioPagoEntity medioPago;
    
    /**
     * Prestamo al que se le crea la multa.
     */
    @PodamExclude
    @OneToOne
    private PrestamoEntity prestamo;

    public UsuarioEntity getMiUsuario() {
        return miUsuario;
    }

    public void setMiUsuario(UsuarioEntity miUsuario) {
        this.miUsuario = miUsuario;
    }    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTituloServicio() {
        return tituloServicio;
    }

    public void setTituloServicio(String tituloServicio) {
        this.tituloServicio = tituloServicio;
    }

    public MedioPagoEntity getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPagoEntity medioPago) {
        this.medioPago = medioPago;
    }

    public PrestamoEntity getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(PrestamoEntity prestamo) {
        this.prestamo = prestamo;
    }
}
