/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.ReservaPersistence;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.posada
 */
@Stateless
public class ReservaLogic {

    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());

    @Inject
    private ReservaPersistence persistence;

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Obtiene la lista de los registros de Reserva que pertenecen a un Usuario.
     * Responde a la lógica de GET reservas/{idUser}
     * @param usuarioid id del Usuario el cual es padre de los Reservas.
     * @return Colección de objetos de ReservaEntity.
     * @throws co.edu.uniandes.csw.usuariostore.exceptions.BusinessLogicException
     */
    public List<ReservaEntity> getReservas(Long usuarioid) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de consultar todos los Reservas");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        if (usuario.getReservas() == null) 
        {
            throw new BusinessLogicException("El usuario que consulta aún no tiene Reservas");
        }
        if (usuario.getReservas().isEmpty())
        {
            throw new BusinessLogicException("El usuario que consulta aún no tiene Reservas");
        }
        return usuario.getReservas();
    }
    
    /**
     * Retorna los videos asociados a las  reservas de un usuario
     * @param usuarioId a consultar los videos en sus reservas
     * @return videos en las reservas de él.
     * GET /reservas/idUser/videos
     * @throws BusinessLogicException 
     */
    public ArrayList<VideoEntity> getVideosReservas(Long usuarioId) throws BusinessLogicException 
    {
       List<ReservaEntity> listaRecursosReservasUser = getReservas(usuarioId);
       ArrayList<VideoEntity> videosReservasUsuario = new ArrayList<VideoEntity>();
        for(int i =0;i<listaRecursosReservasUser.size();i++)
        {
            ReservaEntity actual = listaRecursosReservasUser.get(i);
            videosReservasUsuario.addAll(actual.getVideos());
        }
        return videosReservasUsuario;
    }

    /**
     * Retorna los Libros asociados a las  reservas de un usuario
     * @param usuarioId a consultar los Libros en sus reservas
     * @return Llibros en las reservas de él.
     * GET /reservas/idUser/libros
     * @throws BusinessLogicException 
     */
    public ArrayList<LibroEntity> getLibrosReservas(Long usuarioId) throws BusinessLogicException 
    {
       List<ReservaEntity> listaRecursosReservasUser = getReservas(usuarioId);
       ArrayList<LibroEntity> librosReservasUsuario = new ArrayList<LibroEntity>();
        for(int i =0;i<listaRecursosReservasUser.size();i++)
        {
            ReservaEntity actual = listaRecursosReservasUser.get(i);
            librosReservasUsuario.addAll(actual.getLibros());
        }
        return librosReservasUsuario;
    }
    /**
     * Obtiene los datos de una instancia de Reserva a partir de su ID.
     *
     * @param usuarioid el parametro del usuario del cual se buscará la reserva
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reviewid) Identificador del Reserva a consultar
     * @return Instancia de ReservaEntity con los datos del Reserva consultado.
     * 
     */
    public ReservaEntity getReserva(Long usuarioid, Long reviewid) {
        return persistence.find(usuarioid, reviewid);
    }

     /**
     * Obtiene los videos de una instancia de Reserva a partir de su ID.
     *
     * @param usuarioid el parametro del usuario del cual se buscará la reserva
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reservaId Identificador del Reserva a consultar
     * @return Instancia de ReservaEntity con los datos del Reserva consultado.
     * GET /reservas/{idReserva}/videos
     */
    public List<VideoEntity> getVideosReserva (Long usuarioid, Long reservaId)
    {
     return getReserva(usuarioid, reservaId).getVideos();
    }
    
     /**
     * Obtiene los libros de una instancia de Reserva a partir de su ID.
     *
     * @param usuarioid el parametro del usuario del cual se buscará la reserva
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reservaId Identificador del Reserva a consultar
     * @return Instancia de ReservaEntity con los datos del Reserva consultado.
     * GET /reservas/{idReserva}/videos
     */
    public List<LibroEntity> getLibroReserva (Long usuarioid, Long reservaId)
    {
     return getReserva(usuarioid, reservaId).getLibros();
    }
    /**
     * Se encarga de crear un Reserva en la base de datos.
     * Se encarga de POST Reservas
     * @param entity Objeto de ReservaEntity con los datos nuevos
     * @param usuarioid id del Usuario el cual sera padre del nuevo Reserva.
     * @return Objeto de ReservaEntity con los datos nuevos y su ID.
     * 
     */
    public ReservaEntity createReserva(Long usuarioid, ReservaEntity entity) {
        LOGGER.info("Inicia proceso de crear reserva");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Reserva.
     * PUT /reservas/{idReserva}
     * @param entity Instancia de ReservaEntity con los nuevos datos.
     * @param usuarioid id del Usuario el cual sera padre del Reserva actualizado.
     * @return Instancia de ReservaEntity con los datos actualizados.
     * 
     */
    public ReservaEntity updateReserva(Long usuarioid, ReservaEntity entity) {
        LOGGER.info("Inicia proceso de actualizar una reserva");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Reserva de la base de datos.
     * DELETE reservas/{idReserva}
     * @param id Identificador de la instancia a eliminar.
     * @param usuarioid id del Usuario el cual es padre del Reserva.
     * 
     */
    public void deleteReserva(Long usuarioid, Long idReserva) 
    {
        LOGGER.info("Inicia proceso de borrar una reserva");
        ReservaEntity old = getReserva(usuarioid, idReserva);
        persistence.delete(old.getId());
    }

   /* public List<ReservaEntity> getReservas()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   public ReservaEntity getReserva(Long prestamoid) {
        return persistence.findPorId(prestamoid);
    }

}