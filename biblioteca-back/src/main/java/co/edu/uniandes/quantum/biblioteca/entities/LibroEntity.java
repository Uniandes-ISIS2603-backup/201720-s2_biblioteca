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
 * @author cg.chavarro
 */
@Entity
public class LibroEntity extends RecursoEntity implements Serializable
{
    private int numPaginas;
    private int anioPublicacion;
    
    @ManyToOne    
    private BibliotecaEntity miBiblioteca;
    
    
    public int getNumeroPaginas()
    {return numPaginas;}
    public void setNumeroPaginas(int pPaginas)
    {numPaginas=pPaginas;}
    public int getAnioPublicacion()
    {return anioPublicacion;}
    public void setAnioPublicacion(int pAnio)
    {anioPublicacion=pAnio;}
    
}
