/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author cg.chavarro
 */
@Entity
public class LibroEntity extends BaseEntity implements Serializable
{
    private int numPaginas;
    private int anioPublicacion;
    
       @PodamExclude
    @ManyToOne   
    private PrestamoEntity miPrestamo; 
    @PodamExclude
    @ManyToOne   
    private ReservaEntity miReserva; 
    @PodamExclude
    @ManyToOne    
    private BibliotecaEntity miBiblioteca;
    
    private String autor;    
    
    public String getAutor()
    {
        return autor;
    }
    public void setAutor(String pAutor)
    {
        autor=pAutor;
    }    
  

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public BibliotecaEntity getMiBiblioteca() {
        return miBiblioteca;
    }

    public void setMiBiblioteca(BibliotecaEntity miBiblioteca) {
        this.miBiblioteca = miBiblioteca;
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
    
 
   
    
    public int getNumeroPaginas()
    {return numPaginas;}
    public void setNumeroPaginas(int pPaginas)
    {numPaginas=pPaginas;}
    public int getAnioPublicacion()
    {return anioPublicacion;}
    public void setAnioPublicacion(int pAnio)
    {anioPublicacion=pAnio;}
    
}
