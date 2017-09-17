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
 * @author da.leon
 */
@Entity
public class BlogEntity extends BaseEntity implements Serializable{
    
       @ManyToOne
    private UsuarioEntity miUsuario;
       
    /**
     * titulo del libro sobre el cual se crea el blog
     */   
       
    private String tituloLibro;
    
    /**
     * cambia el titulo del libro
     * @param nuevoTitulo nuevo titulo del libro
     */
   public void setTituloLibro(String nuevoTitulo)
   {
       tituloLibro = nuevoTitulo;
              
   }
   
   /**
    * obtiene el titulo del libro
    * @return devuelve el titulo del libro
    */
   public String getTituloLibro()
  {
      
    return tituloLibro;
               
  
  }
    
    
    
}
