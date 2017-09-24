/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;

/**
 *
 * @author cg.chavarro
 */
public class LibroDTO 
{
    private Long id; 
    private String name;
    private int unidadesExistentes;
    private String autor;
    private int unidadesDisponibles;
    private int numPaginas; 
    private int anioPublicacion;
    
 
    public LibroDTO(LibroEntity l)
    {
        this.id=l.getId();
        this.name =l.getName();
        this.unidadesExistentes=l.getUnidadesExistentes();
        this.unidadesDisponibles=l.getUnidadesDisponibles();
        this.numPaginas=l.getNumeroPaginas();
        this.anioPublicacion=l.getAnioPublicacion();
        this.autor=l.getAutor();
             
    }
    
    public LibroDTO()
    {
        //Se deja el constructor vacio ya que es necesario
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnidadesExistentes() {
        return unidadesExistentes;
    }

    public void setUnidadesExistentes(int unidadesExistentes) {
        this.unidadesExistentes = unidadesExistentes;
    }

    public int getUniandesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUniandesDisponibles(int uniandesDisponibles) {
        this.unidadesDisponibles = uniandesDisponibles;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    
    
  
    
    public LibroEntity toEntity()
    {
        LibroEntity entity= new LibroEntity();
        entity.setAnioPublicacion(this.anioPublicacion);
        entity.setAutor(this.autor);
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setNumeroPaginas(this.numPaginas);
        entity.setUnidadesDisponibles(this.unidadesDisponibles);
        entity.setUnidadesExistentes(this.unidadesExistentes);
        
        return entity;
    }
    
    
}
