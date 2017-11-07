/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.VideoPersistence;
import java.util.ArrayList;
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
  
 @Inject
    private PrestamoLogic PrestamoLogic;
 
 @Inject
    private ReservaLogic reservaLogic;
 
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
        LOGGER.info("Inicia proceso de consultar todos los videos en una bblioteca");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        if (biblioteca.getVideos() == null) {
            throw new BusinessLogicException("La biblioteca que consulta aún no tiene videos");
        }
        if (biblioteca.getVideos().isEmpty()) {
            throw new BusinessLogicException("La biblioteca que consulta aún no tiene videos");
        }
        return biblioteca.getVideos();
    }
    
        public List<VideoEntity> getVideosDisponibles() {
        LOGGER.info("Inicia proceso de consultar todos los videos");
        List<VideoEntity> Videos = persistence.findAll();
        List<VideoEntity> finalL =new ArrayList();
        for(VideoEntity le:Videos)
        {
            if(le.getMiPrestamo()==null)
            {
                finalL.add(le);
            }
        }
        LOGGER.info("Termina proceso de consultar todos los videos");
        return finalL;
    }
        
        public List<VideoEntity> getVideosPrestamo(Long idPrestamo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar los videos del prestamo con id " + idPrestamo);
        List<VideoEntity> l=persistence.findByPrestamo(idPrestamo);
        if (l.isEmpty()) 
        {
            throw new BusinessLogicException("El prestamo que consulta aún no tiene videos");
        }
        else
            return l;
    }
  
  /**
  * Devuelve el Video que se encuentran en la base de datos con el id dado.
  * @param idBiblioteca de la Biblioteca a buscar en la DB.
  * @param idVideo del Video a buscar en la DB.
  * @return  el Video como un objeto Entity.
  * @throws BusinessLogicException
  * Corresponde a la lógica de GET Videos/{id}
  */
 public VideoEntity getVideo(Long idBiblioteca, Long idVideo) throws BusinessLogicException{
 List<VideoEntity> VideosBiblioteca = getVideos(idBiblioteca);
        VideoEntity videoR = null;
        for (VideoEntity Video : VideosBiblioteca) {
            if (Video.getId().equals(idVideo)) {
                videoR = Video;
            }

        }
        if (videoR == null) {
            throw new BusinessLogicException("el video no existe");
        }
        return videoR;
    }
 
  public VideoEntity getVideo(Long idVideo) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Video con id={0}", idVideo);
        VideoEntity Video = persistence.find(idVideo);
        if (Video == null) 
        {
            LOGGER.log(Level.SEVERE, "El Video con el id {0} no existe", idVideo);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Video con id={0}", idVideo);
        return Video;
    }
 
      /**
     * Devuelve el video que se hizo persistir en la base de datos.
     *
     * @param entity Video a persistir
     * @return el Video como un objeto Entity. Corresponde a la lógica de
     * POST/Videos
     */
    public VideoEntity crearVideo(VideoEntity entity) {
        LOGGER.info("Inicia proceso de creación de Video");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Video");
        return entity;
    }
  
 /**
  * Devuelve el Video que se hizo persistir en la base de datos.
  * @param entity Video a persistir
  * @param idBiblioteca id de la Biblioteca donde se creará la Video.
  * @return  el Video como un objeto Entity.
  * Corresponde a la lógica de POST/Videos
  */
   public VideoEntity createVideo(Long idBiblioteca, VideoEntity entity) {
        LOGGER.info("Inicia proceso de creación de Video");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
        LOGGER.info("Termina proceso de creación de Video");
        return persistence.create(entity);
    }
   
       public VideoEntity colocarVideoPrestamo(VideoEntity entity, Long idPrestamo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de agregar video al prestamo");
        VideoEntity ent= persistence.find(entity.getId());
        PrestamoEntity p = PrestamoLogic.getPrestamo(idPrestamo);
        ent.setMiPrestamo(p);
        persistence.update(ent);
        LOGGER.info("Termina proceso de colocar video en prestamo");
        return entity;
    }
        public VideoEntity colocarVideoReserva(VideoEntity entity, Long idPrestamo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de agregar video a la reserva");
        VideoEntity ent= persistence.find(entity.getId());
        ReservaEntity p=reservaLogic.getReserva(idPrestamo);
        ent.setMiReserva(p);
        persistence.update(ent);
        LOGGER.info("Termina proceso de colocar video en reserva");
        return entity;
    }
   
       public VideoEntity colocarVideoBiblioteca(VideoEntity entity, Long idBiblioteca) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de agregar Video a la biblioteca");
        VideoEntity ent= persistence.find(entity.getId());
        BibliotecaEntity p= BibliotecaLogic.getBiblioteca(idBiblioteca);
        ent.setMiBiblioteca(p);
        persistence.update(ent);
        LOGGER.info("Termina proceso de colocar Video en la biblioteca");
        return entity;
    }
     public void devolverVideo(VideoEntity entity) throws BusinessLogicException
    {
        VideoEntity ent=persistence.find(entity.getId());
        ent.setMiPrestamo(null);
        persistence.update(ent);
    }
    public void devolverVideoReserva(VideoEntity entity) throws BusinessLogicException
    {
        VideoEntity ent=persistence.find(entity.getId());
        ent.setMiReserva(null);
        persistence.update(ent);
    }
       
           /**
     * Método privado desde el cual se verifica que un id sea valido para un
     * video.
     *
     * @param id a verificar.
     * @return true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id) {
        return !(id == null || id == 0);
    }
    
  /**
  * Devuelve el Video que se actualizo en la base de datos.
  * @param id  del video.
  * @param entity Video a actualizar
  * @return  el Video como un objeto Entity.
  * @throws BusinessLogicException
  * Corresponde a la lógica de PUT Videos/{id}
  */
    public VideoEntity updateVideo(Long id, VideoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar video con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El ISBN  (id) es inválido");
        }
        VideoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar V¿video con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el Video que se borrará de la base de datos.
  * @param idVideo  del Video a borrar.
  * Corresponde a la lógica de DELETE Videos/{id}
  */
    public void deleteVideo(Long idVideo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar video con id={0}", idVideo);
        VideoEntity old = getVideo(idVideo);
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", idVideo);
    }
}