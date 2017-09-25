/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.MedioPagoEntity;

/**
 *
 * @author f.posada
 */
public class MedioPagoDTO {
    private long id;
    private int tipo;
    private String descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public MedioPagoDTO()
    {
        //Se deja vacio
    }
    
    

   public MedioPagoDTO(MedioPagoEntity medioPago) {
   
       this.id=medioPago.getId();
       this.tipo=medioPago.getTipo();
       this.descripcion=medioPago.getDescripcion();
   }

   public MedioPagoEntity toEntity() {
       MedioPagoEntity ent=new MedioPagoEntity();
              ent.setId(this.id);
       ent.setDescripcion(this.descripcion);
       ent.setTipo(this.tipo);
       return ent;
    }
    
}
