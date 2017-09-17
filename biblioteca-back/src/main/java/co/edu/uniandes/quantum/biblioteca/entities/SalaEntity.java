/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author jp.sanmiguel
 */
@Entity
public class SalaEntity extends BaseEntity implements Serializable{
    
    @ManyToOne    
    private BibliotecaEntity miBiblioteca;
     
    
    private Integer capacidad;
    
   
    
    public Integer getCapacidad()
    {
        return capacidad;
    }
    
    public void setCapacidad(Integer pCapacidad)
    {
        capacidad = pCapacidad;
    }
}

