/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;


import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.ComentarioPersistence;
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
public class ComentarioLogic 
{

 private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());   
 
 @Inject
    private ComentarioPersistence persistence;
 
 
 /**
  * Devuelve los comentario que se encuentran en la base de datos.
  * @return  los comentarios como una lista de objetos.
  * Corresponde a la lógica de GET comentarios
  */
  public List<ComentarioEntity> getComentarios() {
        LOGGER.info("Inicia proceso de consultar todos los Comentarios");
        List<ComentarioEntity>  comentario = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Comentarios");
        return comentario;
    }
  
  /**
  * Devuelve el Comentario que se encuentran en la base de datos con el id dado.
  * @param id del comentario a buscar en la DB.
  * @return  el comentario como un objeto Entity.
  * Corresponde a la lógica de GET Comentarios/{id}
  */
 public ComentarioEntity getComentario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar coomentario con id={0}", id);
        ComentarioEntity comentario = persistence.find(id);
        if (comentario == null) 
        {
            LOGGER.log(Level.SEVERE, "El Comentario con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Comentario con id={0}", id);
        return comentario;
    }
 
 /**
  * Devuelve el Comentario que se hizo persistir en la base de datos.
  * @param entity Comentario a persistir
  * @return  el Comentario como un objeto Entity.
  * Corresponde a la lógica de POST/Comentario
  */
   public ComentarioEntity crearComentario(ComentarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Comentario");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Comentario");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un Comentario.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }
    
  /**
  * Devuelve el Comentario que se actualizo en la base de datos.
  * @param id  del Comentario a actualizar.
  * @param entity Comentario a actualizar
  * @return  el Comentario como un objeto Entity.
  * Corresponde a la lógica de PUT Comentario/{id}
  */
      public ComentarioEntity updateComentario(Long id, ComentarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Comentario con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El   (id) es inválido");
        }
        ComentarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Comentario con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el Comentario que se borrará de la base de datos.
  * @param id  del Comentario a borrar.
  * Corresponde a la lógica de DELETE Comentario/{id}
  */
    public void deleteComentario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar comentario con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar comentario con id={0}", id);
    }


}