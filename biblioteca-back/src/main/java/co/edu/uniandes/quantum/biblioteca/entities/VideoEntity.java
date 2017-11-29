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
public class VideoEntity extends BaseEntity implements Serializable {
    
    private int duracion;
    private String autor;
    private String imagen;
    
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
    private SalaEntity miSala; 
    
    
    public String getAutor()
    {
        return autor;
    }
    public void setAutor(String pAutor)
    {
        autor=pAutor;
    }
    
    
    
    public BibliotecaEntity getMiBiblioteca()
    {
        return miBiblioteca;
    }
    
    public void setMiBiblioteca(BibliotecaEntity pMiBiblioteca)
    {
        miBiblioteca = pMiBiblioteca;
    }
    
        public PrestamoEntity getMiPrestamo() {
        return miPrestamo;
    }

    public void setMiPrestamo(PrestamoEntity miPrestamo) {
        this.miPrestamo = miPrestamo;
    }

    public ReservaEntity getMiReserva() {
        return miReserva;
    }

    public void setMiReserva(ReservaEntity miReserva) {
        this.miReserva = miReserva;
    }
    
    public SalaEntity getMiSala() {
        return miSala;
    }

    public void setMiSala(SalaEntity miSala) {
        this.miSala = miSala;
    }
    
    public int getDuracion()
    {
        return duracion;
    }
    
    public void setDuracion(int pDuracion)
    {
        duracion = pDuracion;
    }
    
       public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    } 

}
