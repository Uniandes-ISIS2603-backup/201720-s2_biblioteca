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
    private String autor;
    private int numPaginas; 
    private int anioPublicacion;
       private String imagen;
    
 
    public LibroDTO(LibroEntity l)
    {
        if(l!=null){
        this.id=l.getId();
        this.name =l.getName();
        this.numPaginas=l.getNumeroPaginas();
        this.anioPublicacion=l.getAnioPublicacion();
        this.autor=l.getAutor();
         this.imagen =l.getImagen();}
        else
            throw new NullPointerException("El libro fue nulo");
             
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
  
     public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public LibroEntity toEntity()
    {
        LibroEntity entity= new LibroEntity();
        entity.setAnioPublicacion(this.anioPublicacion);
        entity.setAutor(this.autor);
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setNumeroPaginas(this.numPaginas); 
                entity.setImagen(imagen);
        
        return entity;
    }
    
    
}
