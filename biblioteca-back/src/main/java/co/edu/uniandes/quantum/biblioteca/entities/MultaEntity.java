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
    
    public final static String MULTA_NO_PAGADA = "https://openclipart.org/image/2400px/svg_to_png/279690/Button---Sad.png";
    
    public final static String MULTA_PAGADA = "https://openclipart.org/image/2400px/svg_to_png/279689/Button---Happy.png";
    
    @PodamExclude
    @ManyToOne    
    private UsuarioEntity miUsuario;
    
    /**
     * Fecha en que se pusó la multa.
     */
    private String fecha;
    
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
     * Imagen multa.
     */
    private String imagen;
    
    /**
     * Medio de pago de la multa.
     */
    @PodamExclude
    @OneToOne
    private MedioPagoEntity medioPago;
    
    
    public UsuarioEntity getMiUsuario() {
        return miUsuario;
    }

    public void setMiUsuario(UsuarioEntity miUsuario) {
        this.miUsuario = miUsuario;
    }    
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

   public void setImagen(String imagen)
   {
       this.imagen = imagen;
   }
   
   public String getImagen()
   {
        if(pagada == false)
        {
            return MULTA_NO_PAGADA;
        }    
        else
        {
            return MULTA_PAGADA;
        }
   }
}
