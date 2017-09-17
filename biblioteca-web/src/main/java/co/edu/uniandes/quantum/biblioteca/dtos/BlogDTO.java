/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;


import co.edu.uniandes.quantum.biblioteca.entities.BlogEntity;
/**
 *
 * @author da.leon
 */
public class BlogDTO 
{
    
    private long id;
    
    /**
     * stributo que corresponde al titulo del libro con el cual se relaciona el blog
     */
    private String tituloLibro;
    
    
    public BlogDTO(BlogEntity req)
  {
      this.id = req.getId();
      this.tituloLibro = req.getTituloLibro();   
  }
    
    /**
     * retorna el titulo del libro
     * @return titulo del libro
     */
    public String getTituloLibro()
    {
        return tituloLibro;
    }
    
    /**
     * cambia el titulo del libro
     * @param nuevoTitulo nuevlo nombre del libro
     */
    public void setTituloLibro( String nuevoTitulo)
    {
        this.tituloLibro = nuevoTitulo;
    }
   
     public BlogEntity toEntity()
    {
        BlogEntity entity = new BlogEntity();
        entity.setId(this.id);
        entity.setTituloLibro(this.tituloLibro);
        return entity;
    }
    
    
    
    
    
    
    
}
