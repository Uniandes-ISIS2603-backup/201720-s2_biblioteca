/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;


import co.edu.uniandes.quantum.biblioteca.entities.BlogEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.BlogPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.leon
 */
@Stateless
public class BlogLogic 
{

 private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());   
 
 @Inject
    private BlogPersistence persistence;
 
 
 /**
  * Devuelve los Blog que se encuentran en la base de datos.
  * @return  los Blogs como una lista de objetos.
  * Corresponde a la lógica de GET Blogs
  */
  public List<BlogEntity> getBlogs() {
        LOGGER.info("Inicia proceso de consultar todos los blogs");
        List<BlogEntity>  blog = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los blogs");
        return blog;
    }
  
  /**
  * Devuelve el Blog que se encuentran en la base de datos con el id dado.
  * @param id del blog a buscar en la DB.
  * @return  el blog como un objeto Entity.
  * Corresponde a la lógica de GET Blogs/{id}
  */
 public BlogEntity getBlog(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar coomentario con id={0}", id);
        BlogEntity blog = persistence.find(id);
        if (blog == null) 
        {
            LOGGER.log(Level.SEVERE, "El blog con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar blog con id={0}", id);
        return blog;
    }
 
 /**
  * Devuelve el blog que se hizo persistir en la base de datos.
  * @param entity blog a persistir
  * @return  el blog como un objeto Entity.
  * Corresponde a la lógica de POST/Blog
  */
   public BlogEntity crearBlog(BlogEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Blog");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Blog");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un Blog.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }
    
  /**
  * Devuelve el blog que se actualizo en la base de datos.
  * @param id  del blog a actualizar.
  * @param entity blog a actualizar
  * @return  el blog como un objeto Entity.
  * Corresponde a la lógica de PUT Blog/{id}
  */
      public BlogEntity updateBlog(Long id, BlogEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Blog con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El   (id) es inválido");
        }
        BlogEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Blog con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el Blog que se borrará de la base de datos.
  * @param id  del Blog a borrar.
  * Corresponde a la lógica de DELETE Blog/{id}
  */
    public void deleteBlog(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar blog con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar blog con id={0}", id);
    }


}