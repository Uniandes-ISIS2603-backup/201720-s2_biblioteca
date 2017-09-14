/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;

/**
 *
 * @author jp.sanmiguel
 */
public class SalaEntity implements Serializable{
    
    private Long  id;
    
    private Integer capacidad;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    public Integer getCapacidad()
    {
        return capacidad;
    }
    
    public void setCapacidad(Integer pCapacidad)
    {
        capacidad = pCapacidad;
    }
}

