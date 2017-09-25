/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
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
 
  @Inject
    private BibliotecaLogic BibliotecaLogic;
 
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
     * Obtiene la lista de los registros de Video que pertenecen a una Biblioteca.
     *
     * @param idBiblioteca id de la Biblioteca la cual es "padre" de los Videos.
     * @return Colección de objetos de VideoEntity.
     * @throws BusinessLogicException
     */
    public List<VideoEntity> getVideos(Long idBiblioteca) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los multas");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        if (biblioteca.getVideos() == null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene multas");
        }
        if (biblioteca.getVideos().isEmpty()) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene multas");
        }
        return biblioteca.getVideos();
    }
  
  /**
  * Devuelve el Video que se encuentran en la base de datos con el id dado.
  * @param idBiblioteca de la Biblioteca a buscar en la DB.
  * @param idVideo del Video a buscar en la DB.
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de GET Videos/{id}
  */
 public VideoEntity getVideo(Long idBiblioteca, Long idVideo) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Video con id={0}", idVideo);
        VideoEntity Video = persistence.find(idBiblioteca, idVideo);
        if (Video == null) 
        {
            LOGGER.log(Level.SEVERE, "El Video con el id {0} no existe", idVideo);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Video con id={0}", idVideo);
        return Video;
    }
 
 /**
  * Devuelve el Video que se hizo persistir en la base de datos.
  * @param entity Video a persistir
  * @param idBiblioteca id de la Biblioteca donde se creará la Video.
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de POST/Videos
  */
   public VideoEntity crearVideo(Long idBiblioteca, VideoEntity entity) {
        LOGGER.info("Inicia proceso de creación de Video");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
        LOGGER.info("Termina proceso de creación de Video");
        return persistence.create(entity);
    }
    
  /**
  * Devuelve el Video que se actualizo en la base de datos.
  * @param idBiblioteca  de la Biblioteca a actualizarle su Video.
  * @param entity Video a actualizar
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de PUT Videos/{id}
  */
      public VideoEntity updateVideo(Long idBiblioteca, VideoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Video");
          BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Video con id={0}", entity.getId());
        return persistence.update(entity);
    }

  /**
  * Devuelve el Video que se borrará de la base de datos.
  * @param idBiblioteca de donde se borrará el Video.
  * @param idVideo  del Video a borrar.
  * Corresponde a la lógica de DELETE Videos/{id}
  */
    public void deleteVideo(Long idBiblioteca, Long idVideo) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Video con id={0}", idVideo);
        VideoEntity old = getVideo(idBiblioteca, idVideo);
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar Video con id={0}", idVideo);
    }
}