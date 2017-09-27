/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;

/**
 *
 * @author cg.chavarro
 */
public class SalaDTO 
{
    private Long id;
 
    private int capacidad;
    
    public SalaDTO(SalaEntity l)
    {
        if(l!=null){
        this.id=l.getId();
        this.capacidad=l.getCapacidad();}
        else
            throw new NullPointerException("La sala fue nula");
     
    }
    
    public SalaDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public SalaEntity toEntity()
    {
        SalaEntity sa = new SalaEntity();
        sa.setCapacidad(capacidad);
        sa.setId(id);
        return sa;
    }
}
