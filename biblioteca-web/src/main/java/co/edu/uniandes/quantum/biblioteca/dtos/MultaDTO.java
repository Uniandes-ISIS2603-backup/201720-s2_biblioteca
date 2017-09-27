/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import java.util.Date;

/**
 *
 * @author jp.sanmiguel
 */
public class MultaDTO {

    private Long id;
    /**
     * Fecha en que se pusó la multa.
     */
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
    
     public MultaDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }
      
     
    public MultaDTO(MultaEntity entityMulta) {
        if(entityMulta!=null){
        this.id = entityMulta.getId();
        this.costo=entityMulta.getCosto();
        this.pagada=entityMulta.isPagada();
        this.descripcion=entityMulta.getDescripcion();
        this.fecha=entityMulta.getFecha();
        this.tituloServicio=entityMulta.getTituloServicio();}
        else
            throw new NullPointerException("La multa fue nula");
        
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
   

    public MultaEntity toEntity() {
        MultaEntity entity = new MultaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setCosto(costo);
        entity.setDescripcion(descripcion);
        entity.setPagada(pagada);
        entity.setTituloServicio(tituloServicio);
        return entity;
    }
    
}
