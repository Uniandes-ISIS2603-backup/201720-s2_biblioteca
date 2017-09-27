/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import java.util.Date;

/**
 *
 * @author f.posada
 */
public class PrestamoDTO {
    
    private Long id;

    
    private String fechaInicio;
    private String fechaFinal;
    
    private boolean generomulta;
    private boolean retornado;
    private boolean externo;
        
    public PrestamoDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }
    
    public PrestamoDTO(PrestamoEntity ent)
    {
        if(ent!=null){
        this.id=ent.getId();
        this.fechaInicio=ent.getFechaInicio();
        this.fechaFinal=ent.getFechaFinal();
        this.generomulta=ent.isGeneromulta();
        this.retornado=ent.isRetornado();
        this.externo=ent.isExterno();
        }
        else
            throw new NullPointerException("El prestamo fue nulo");
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
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

    public PrestamoEntity toEntity() 
    {
        PrestamoEntity ent=new PrestamoEntity();
        ent.setId(this.id);
        ent.setExterno(this.externo);
        ent.setFechaFinal(this.fechaFinal);
        ent.setFechaInicio(this.fechaInicio);
        ent.setRetornado(this.retornado);
        ent.setGeneromulta(this.generomulta);
        
        return ent;
        
    }
    
    
    
}
