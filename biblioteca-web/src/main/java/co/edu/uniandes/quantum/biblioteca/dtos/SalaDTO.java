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
        this.id=l.getId();
        this.capacidad=l.getCapacidad();
     
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
    
}
