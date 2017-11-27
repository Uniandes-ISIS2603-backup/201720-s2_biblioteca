/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.PrestamoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.posada
 */
@Stateless
public class PrestamoLogic {

    private static final Logger LOGGER = Logger.getLogger(PrestamoLogic.class.getName());

    @Inject
    private PrestamoPersistence persistence;
    

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Obtiene la lista de los registros de Prestamo que pertenecen a un Usuario.
     *
     * @param usuarioid id del Usuario el cual es padre de los Prestamos.
     * @return Colección de objetos de PrestamoEntity.
     * @throws co.edu.uniandes.csw.usuariostore.exceptions.BusinessLogicException
     */
    public List<PrestamoEntity> getPrestamos(Long usuarioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los prestamos");
        List<PrestamoEntity> pres=persistence.findAll(usuarioid);
        if (pres == null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene prestamos");
        }
        if (pres.isEmpty()) {
            throw new BusinessLogicException("El usuario que consulta tiene su lista de préstamos vacía");
        }
        return pres;
    }

    /**
     * Obtiene los datos de una instancia de Prestamo a partir de su ID.
     *
     * @param usuarioid
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reviewid) Identificador del Prestamo a consultar
     * @return Instancia de PrestamoEntity con los datos del Prestamo consultado.
     * 
     */
    public PrestamoEntity getPrestamo(Long usuarioid, Long prestamoid) {
        return persistence.find(usuarioid, prestamoid);
    }
    
    public PrestamoEntity getPrestamo(Long prestamoid) {
        return persistence.findPorId(prestamoid);
    }

    /**
     * Se encarga de crear un Prestamo en la base de datos.
     *
     * @param entity Objeto de PrestamoEntity con los datos nuevos
     * @param usuarioid id del Usuario el cual sera padre del nuevo Prestamo.
     * @return Objeto de PrestamoEntity con los datos nuevos y su ID.
     * 
     */
    public PrestamoEntity createPrestamo(Long usuarioid, PrestamoEntity entity) {
        LOGGER.info("Inicia proceso de crear prestamo");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Prestamo.
     *
     * @param entity Instancia de PrestamoEntity con los nuevos datos.
     * @param usuarioid id del Usuario el cual sera padre del Prestamo actualizado.
     * @return Instancia de PrestamoEntity con los datos actualizados.
     * 
     */
    public PrestamoEntity updatePrestamo(Long usuarioid, PrestamoEntity entity) {
        LOGGER.info("Inicia proceso de actualizar review");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Prestamo de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param usuarioid id del Usuario el cual es padre del Prestamo.
     * 
     */
    public void deletePrestamo(Long usuarioid, Long id) {
        LOGGER.info("Inicia proceso de borrar review");
        PrestamoEntity old = getPrestamo(usuarioid, id);
        persistence.delete(old.getId());
    }

    public List<PrestamoEntity> getPrestamos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
public List<LibroEntity> listLibros(Long usuarioId, Long prestamoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consuarioIsultar todos los Libros del prestamo con id = {0}", prestamoId);
        return getPrestamo(usuarioId, prestamoId).getLibros();
    }

    /**
     * Obtiene una instancia de LibroEntity asociada a una instancia de Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param libId Identificador de la instancia de Libro
     * 
     */
    public LibroEntity getLibro(Long usuarioId, Long prestamoId, Long libId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Libro del prestamo con id = {0}", prestamoId);
        List<LibroEntity> list = getPrestamo(usuarioId,prestamoId).getLibros();
        LibroEntity libEnt = new LibroEntity();
        libEnt.setId(libId);
        int index = list.indexOf(libEnt);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Libro existente a un Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param libId Identificador de la instancia de Libro
     * @return Instancia de LibroEntity que fue asociada a Prestamo
     * 
     */
    public LibroEntity addLibro(Long usuarioId,Long prestamoId, Long libId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un Libro al prestamo con id = {0}", prestamoId);
        PrestamoEntity PrestamoEntity = getPrestamo(usuarioId,prestamoId);
        LibroEntity libEnt = new LibroEntity();
        libEnt.setId(libId);
        PrestamoEntity.getLibros().add(libEnt);
        return getLibro(usuarioId, prestamoId, libId);
    }

    /**
     * Remplaza las instancias de Libro asociadas a una instancia de Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param list Colección de instancias de LibroEntity a asociar a instancia
     * de Prestamo
     * @return Nueva colección de LibroEntity asociada a la instancia de Prestamo
     * 
     */
    public List<LibroEntity> replaceLibros(Long usuarioId,Long prestamoId, List<LibroEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un Libro del prestamo con id = {0}", prestamoId);
        PrestamoEntity prestEnt = getPrestamo(usuarioId,prestamoId);
        prestEnt.setLibros(list);
        return prestEnt.getLibros();
    }

    /**
     * Desasocia un Libro existente de un Prestamo existente
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param libId Identificador de la instancia de Libro
     * 
     */
    public void removeLibro(Long usuarioId, Long prestamoId, Long libId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Libro del prestamo con id = {0}", prestamoId);
        PrestamoEntity entity = getPrestamo(usuarioId,prestamoId);
        LibroEntity libEnt = new LibroEntity();
        libEnt.setId(libId);
        entity.getLibros().remove(libEnt);
    }
    
    
public List<VideoEntity> listVideos(Long usuarioId, Long prestamoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consuarioIsultar todos los Videos del prestamo con id = {0}", prestamoId);
        return getPrestamo(usuarioId, prestamoId).getVideos();
    }

    /**
     * Obtiene una instancia de VideoEntity asociada a una instancia de Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param vidId Identificador de la instancia de Video
     * 
     */
    public VideoEntity getVideo(Long usuarioId, Long prestamoId, Long vidId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Video del prestamo con id = {0}", prestamoId);
        List<VideoEntity> list = getPrestamo(usuarioId,prestamoId).getVideos();
        VideoEntity vidEnt = new VideoEntity();
        vidEnt.setId(vidId);
        int index = list.indexOf(vidEnt);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Video existente a un Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param vidId Identificador de la instancia de Video
     * @return Instancia de VideoEntity que fue asociada a Prestamo
     * 
     */
    public VideoEntity addVideo(Long usuarioId,Long prestamoId, Long vidId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un Video al prestamo con id = {0}", prestamoId);
        PrestamoEntity presEnt = getPrestamo(usuarioId,prestamoId);
        VideoEntity vidEnt = new VideoEntity();
        vidEnt.setId(vidId);
        presEnt.getVideos().add(vidEnt);
        return getVideo(usuarioId, prestamoId, vidId);
    }

    /**
     * Remplaza las instancias de Video asociadas a una instancia de Prestamo
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param list Colección de instancias de VideoEntity a asociar a instancia
     * de Prestamo
     * @return Nueva colección de VideoEntity asociada a la instancia de Prestamo
     * 
     */
    public List<VideoEntity> replaceVideos(Long usuarioId,Long prestamoId, List<VideoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un Video del prestamo con id = {0}", prestamoId);
        PrestamoEntity presEnt = getPrestamo(usuarioId,prestamoId);
        presEnt.setVideos(list);
        return presEnt.getVideos();
    }

    /**
     * Desasocia un Video existente de un Prestamo existente
     *
     * @param prestamoId Identificador de la instancia de Prestamo
     * @param vidId Identificador de la instancia de Video
     * 
     */
    public void removeVideo(Long usuarioId, Long prestamoId, Long vidId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Video del prestamo con id = {0}", prestamoId);
        PrestamoEntity entity = getPrestamo(usuarioId,prestamoId);
        VideoEntity vidEnt = new VideoEntity();
        vidEnt.setId(vidId);
        entity.getVideos().remove(vidEnt);
    }


}
