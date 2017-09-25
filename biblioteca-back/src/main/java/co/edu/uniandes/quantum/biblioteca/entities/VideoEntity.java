/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.sanmiguel
 */
@Entity
public class VideoEntity extends RecursoEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne    
    private BibliotecaEntity miBiblioteca;
    @PodamExclude
    @ManyToOne    
    private ReservaEntity miReserva;
    @PodamExclude
    @ManyToOne   
    private PrestamoEntity miPrestamo;
    
    @PodamExclude
    @OneToOne
    private SalaEntity sala;
    
    
    private int duracion;
    
    public BibliotecaEntity getMiBiblioteca()
    {
        return miBiblioteca;
    }
    
    public void setMiBiblioteca(BibliotecaEntity pMiBiblioteca)
    {
        miBiblioteca = pMiBiblioteca;
    }
    
    public int getDuracion()
    {
        return duracion;
    }
    
    public void setDuracion(int pDuracion)
    {
        duracion = pDuracion;
    }

    public SalaEntity getSala() {
        return sala;
    }

    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

}
