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
    private int uniandesDisponibles;
    private int numPaginas; 
    private int anioPublicacion;
    
   /*  public UsuarioDTO(UsuarioEntity us)
    {
        this.id=us.getId();
        this.name=us.getName();
        this.direccion=us.getDireccion();
        this.telefono=us.getTelefono();
    }*/
    public LibroDTO(LibroEntity l)
    {
        this.id=l.getId();
        this.name =l.getName();
        this.unidadesExistentes=l.getUnidadesExistentes();
        this.uniandesDisponibles=l.getUnidadesDisponibles();
        this.numPaginas=l.getNumeroPaginas();
        this.anioPublicacion=l.getAnioPublicacion();
        this.autor=l.getAutor();
             
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
        return uniandesDisponibles;
    }

    public void setUniandesDisponibles(int uniandesDisponibles) {
        this.uniandesDisponibles = uniandesDisponibles;
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
    
    
    
  /*  public UsuarioEntity toEntity()
    {
        UsuarioEntity entity=new UsuarioEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        return entity;
    }*/
    
    public LibroEntity toEntity()
    {
        LibroEntity entity= new LibroEntity();
        entity.setAnioPublicacion(this.anioPublicacion);
        entity.setAutor(this.autor);
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setNumeroPaginas(this.numPaginas);
        entity.setUnidadesDisponibles(this.uniandesDisponibles);
        entity.setUnidadesExistentes(this.unidadesExistentes);
        
        return entity;
    }
    
    
}
