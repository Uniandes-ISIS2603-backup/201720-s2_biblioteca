/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.sanmiguel
 */
@Entity
public class SalaEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
   @ManyToOne    
    private BibliotecaEntity miBiblioteca;
    
    @PodamExclude
    @ManyToOne   
    private PrestamoEntity miPrestamo;
     
    
    private Integer capacidad;
    
    public BibliotecaEntity getMiBiblioteca() {
        return miBiblioteca;
    }

    public void setMiBiblioteca(BibliotecaEntity miBiblioteca) {
        this.miBiblioteca = miBiblioteca;
    }   
    
    public Integer getCapacidad()
    {
        return capacidad;
    }
    
    public void setCapacidad(Integer pCapacidad)
    {
        capacidad = pCapacidad;
    }

    public PrestamoEntity getMiPrestamo() {
        return miPrestamo;
    }

    public void setMiPrestamo(PrestamoEntity miPrestamo) {
        this.miPrestamo = miPrestamo;
    }
    
    
}

