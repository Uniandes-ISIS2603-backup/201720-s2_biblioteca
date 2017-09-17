/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.VideoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cg.chavarro
 */
@Stateless
public class VideoLogic 
{

 private static final Logger LOGGER = Logger.getLogger(VideoLogic.class.getName());   
 
 @Inject
    private VideoPersistence persistence;
 
 
 /**
  * Devuelve los Videos que se encuentran en la base de datos.
  * @return  los Videos como una lista de objetos.
  * Corresponde a la lógica de GET Videos
  */
  public List<VideoEntity> getVideos() {
        LOGGER.info("Inicia proceso de consultar todos los Videos");
        List<VideoEntity> videos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Videos");
        return videos;
    }
  
  /**
  * Devuelve el Video que se encuentran en la base de datos con el id dado.
  * @param id del Video a buscar en la DB.
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de GET Videos/{id}
  */
 public VideoEntity getVideo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Video con id={0}", id);
        VideoEntity video = persistence.find(id);
        if (video == null) 
        {
            LOGGER.log(Level.SEVERE, "El Video con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Video con id={0}", id);
        return video;
    }
 
 /**
  * Devuelve el Video que se hizo persistir en la base de datos.
  * @param entity Video a persistir
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de POST/Videos
     * @throws co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException si no halla el video, o incumple lógica
  */
   public VideoEntity crearVideo(VideoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Video");
        if (!validateId(entity.getId())) 
        {
            throw new BusinessLogicException("El ISBN (Id) es inválido");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Video");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un Video.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }
    
  /**
  * Devuelve el Video que se actualizo en la base de datos.
  * @param id  del Video a actualizar.
  * @param entity Video a actualizar
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de PUT Videos/{id}
  */
      public VideoEntity updateVideo(Long id, VideoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Video con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El (id) es inválido");
        }
        VideoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Video con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el Video que se borrará de la base de datos.
  * @param id  del Video a borrar.
  * Corresponde a la lógica de DELETE Videos/{id}
  */
    public void deleteVideo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Video con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar Video con id={0}", id);
    }

}