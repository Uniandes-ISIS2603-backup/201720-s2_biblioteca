/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author jp.sanmiguel
 */
@Entity
public class VideoEntity extends RecursoEntity implements Serializable {
    
    @ManyToOne    
    private BibliotecaEntity miBiblioteca;
    
    @ManyToOne    
    private ReservaEntity mReserva;
    
    @ManyToOne   
    private PrestamoEntity miPrestamo;
    
    private Long duracion;
    
    public Long getDuracion()
    {
        return duracion;
    }
    
    public void setDuracion(Long pDuracion)
    {
        duracion = pDuracion;
    }
    
}

