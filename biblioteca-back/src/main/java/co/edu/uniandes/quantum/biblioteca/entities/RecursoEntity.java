/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author f.posada
 */
@Entity

public abstract class RecursoEntity extends BaseEntity implements Serializable
{
    private String autor;
    private int unidadesExistentes;
    private int unidadesDisponibles;
    
    
    public String getAutor()
    {
        return autor;
    }
    public void setAutor(String pAutor)
    {
        autor=pAutor;
    }
    
    public int getUnidadesExistentes()
    {
        return unidadesExistentes;
    }
    public void setUnidadesExistentes(int pUnidades)
    {
        unidadesExistentes=pUnidades;
    }
    
        public int getUnidadesDisponibles()
    {
        return unidadesDisponibles;
    }
    public void setUnidadesDisponibles(int pUnidades)
    {
        unidadesDisponibles=pUnidades;
    }
}
