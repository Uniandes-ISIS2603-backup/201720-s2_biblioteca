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
    
    public BlogDTO()
    {
        //Se deja vacio
    }
    
    public BlogDTO(BlogEntity req)
  {
      if(req!=null){
      this.id = req.getId();
      this.tituloLibro = req.getTituloLibro();   }
      else
          throw new NullPointerException("El blog fue nulo");
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
   
     /**
     * obtiene el id
     * @return id identificador del blog
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id del blog
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
     public BlogEntity toEntity()
    {
        BlogEntity entity = new BlogEntity();
        entity.setId(this.id);
        entity.setTituloLibro(this.tituloLibro);
        return entity;
    }
    
    
    
    
    
    
    
}
