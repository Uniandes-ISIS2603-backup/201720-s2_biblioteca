/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author f.posada
 */
@Entity
public class PrestamoEntity extends BaseEntity implements Serializable {
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    
    private boolean generomulta;
    private boolean retornado;
    private boolean externo;
    
   // @ManyToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<RecursoEntity> reservas = new ArrayList<RecursoEntity>();

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public List<RecursoEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<RecursoEntity> reservas) {
        this.reservas = reservas;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isGeneromulta() {
        return generomulta;
    }

    public void setGeneromulta(boolean generomulta) {
        this.generomulta = generomulta;
    }

    public boolean isRetornado() {
        return retornado;
    }

    public void setRetornado(boolean retornado) {
        this.retornado = retornado;
    }

    public boolean isExterno() {
        return externo;
    }

    public void setExterno(boolean externo) {
        this.externo = externo;
    }
    
    
    
}
