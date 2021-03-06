/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import java.util.Date;

/**
 *
 * @author cg.chavarro
 */
public class ReservaDTO 
{
    private Long id;
    private String name;
    private boolean completada;
    private Date fechaInicio;
    private String imagen;
    private String mensaje;
    
    public ReservaDTO(ReservaEntity l)
    {
        if(l!=null){
         this.id=l.getId();
        this.name =l.getName();
        this.completada=l.isCompletada();
        this.imagen = l.getImagen();
        this.mensaje =l.getMensaje();
        this.fechaInicio=l.getFechaInicio();}
        else
            throw new NullPointerException("La reserva fue nula");
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

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    
    public ReservaDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }
  
    
    public ReservaEntity toEntity()
    {
        ReservaEntity entity=new ReservaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
       entity.setCompletada(this.completada);
       entity.setFechaInicio(this.fechaInicio);
       entity.setImagen(this.imagen);
       entity.setMensaje(this.mensaje);
        return entity;
    }
    
}
