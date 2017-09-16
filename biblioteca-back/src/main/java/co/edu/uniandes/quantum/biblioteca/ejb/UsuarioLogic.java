/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.UsuarioPersistence;
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
public class UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;

    public List<UsuarioEntity> getUsuarios() {
        LOGGER.info("Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> Usuarios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los usuarios");
        return Usuarios;
    }

    public UsuarioEntity getUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar usuario con id={0}", id);
        UsuarioEntity Usuario = persistence.find(id);
        if (Usuario == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar usuario con id={0}", id);
        return Usuario;
    }

    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de usuario");
        if (!validateID(entity.getId())) {
            throw new BusinessLogicException("El ID es inválido");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de usuario");
        return entity;
    }

    public UsuarioEntity updateUsuario(Long id, UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar usuario con id={0}", id);
        if (!validateID(entity.getId())) {
            throw new BusinessLogicException("El ID es inválido");
        }
        UsuarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar usuario con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar usuario con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar usuario con id={0}", id);
    }

    private boolean validateID(Long id) {
        if (id == null) {
            return false;
        }
        return true;
    }

    /**
     * Obtiene una colección de instancias de PrestamoEntity asociadas a una
     * instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de PrestamoEntity asociadas a la instancia
     * de Usuario
     * 
     */
    public List<PrestamoEntity> listPrestamos(Long UsuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los prestamos del usuario con id = {0}", UsuarioId);
        return getUsuario(UsuarioId).getPrestamos();
    }

    /**
     * Obtiene una instancia de PrestamoEntity asociada a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param prestamosId Identificador de la instancia de Prestamo
     * 
     */
    public PrestamoEntity getPrestamo(Long UsuarioId, Long prestamosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un prestamo del usuario con id = {0}", UsuarioId);
        List<PrestamoEntity> list = getUsuario(UsuarioId).getPrestamos();
        PrestamoEntity prestamosEntity = new PrestamoEntity();
        prestamosEntity.setId(prestamosId);
        int index = list.indexOf(prestamosEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Prestamo existente a un Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param prestamosId Identificador de la instancia de Prestamo
     * @return Instancia de PrestamoEntity que fue asociada a Usuario
     * 
     */
    public PrestamoEntity addPrestamo(Long UsuarioId, Long prestamosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un prestamo al usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        PrestamoEntity prestamosEntity = new PrestamoEntity();
        prestamosEntity.setId(prestamosId);
        UsuarioEntity.getPrestamos().add(prestamosEntity);
        return getPrestamo(UsuarioId, prestamosId);
    }

    /**
     * Remplaza las instancias de Prestamo asociadas a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param list Colección de instancias de PrestamoEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de PrestamoEntity asociada a la instancia de Usuario
     * 
     */
    public List<PrestamoEntity> replacePrestamos(Long UsuarioId, List<PrestamoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un prestamo del usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        UsuarioEntity.setPrestamos(list);
        return UsuarioEntity.getPrestamos();
    }

    /**
     * Desasocia un Prestamo existente de un Usuario existente
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param prestamosId Identificador de la instancia de Prestamo
     * 
     */
    public void removePrestamo(Long UsuarioId, Long prestamosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un prestamo del usuario con id = {0}", UsuarioId);
        UsuarioEntity entity = getUsuario(UsuarioId);
        PrestamoEntity prestamosEntity = new PrestamoEntity();
        prestamosEntity.setId(prestamosId);
        entity.getPrestamos().remove(prestamosEntity);
    }
    
    /**
     * Obtiene una colección de instancias de MultaEntity asociadas a una
     * instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de MultaEntity asociadas a la instancia
     * de Usuario
     * 
     */
    public List<MultaEntity> listMultas(Long UsuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los multas del usuario con id = {0}", UsuarioId);
        return getUsuario(UsuarioId).getMultas();
    }

    /**
     * Obtiene una instancia de MultaEntity asociada a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param multasId Identificador de la instancia de Multa
     * 
     */
    public MultaEntity getMulta(Long UsuarioId, Long multasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un multa del usuario con id = {0}", UsuarioId);
        List<MultaEntity> list = getUsuario(UsuarioId).getMultas();
        MultaEntity multasEntity = new MultaEntity();
        multasEntity.setId(multasId);
        int index = list.indexOf(multasEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Multa existente a un Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param multasId Identificador de la instancia de Multa
     * @return Instancia de MultaEntity que fue asociada a Usuario
     * 
     */
    public MultaEntity addMulta(Long UsuarioId, Long multasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un multa al usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        MultaEntity multasEntity = new MultaEntity();
        multasEntity.setId(multasId);
        UsuarioEntity.getMultas().add(multasEntity);
        return getMulta(UsuarioId, multasId);
    }

    /**
     * Remplaza las instancias de Multa asociadas a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param list Colección de instancias de MultaEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de MultaEntity asociada a la instancia de Usuario
     * 
     */
    public List<MultaEntity> replaceMultas(Long UsuarioId, List<MultaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un multa del usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        UsuarioEntity.setMultas(list);
        return UsuarioEntity.getMultas();
    }

    /**
     * Desasocia un Multa existente de un Usuario existente
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param multasId Identificador de la instancia de Multa
     * 
     */
    public void removeMulta(Long UsuarioId, Long multasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un multa del usuario con id = {0}", UsuarioId);
        UsuarioEntity entity = getUsuario(UsuarioId);
        MultaEntity multasEntity = new MultaEntity();
        multasEntity.setId(multasId);
        entity.getMultas().remove(multasEntity);
    }
    
    /**
     * Obtiene una colección de instancias de ReservaEntity asociadas a una
     * instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de ReservaEntity asociadas a la instancia
     * de Usuario
     * 
     */
    public List<ReservaEntity> listReservas(Long UsuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los reservas del usuario con id = {0}", UsuarioId);
        return getUsuario(UsuarioId).getReservas();
    }

    /**
     * Obtiene una instancia de ReservaEntity asociada a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param reservasId Identificador de la instancia de Reserva
     * 
     */
    public ReservaEntity getReserva(Long UsuarioId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un reserva del usuario con id = {0}", UsuarioId);
        List<ReservaEntity> list = getUsuario(UsuarioId).getReservas();
        ReservaEntity reservasEntity = new ReservaEntity();
        reservasEntity.setId(reservasId);
        int index = list.indexOf(reservasEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Reserva existente a un Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param reservasId Identificador de la instancia de Reserva
     * @return Instancia de ReservaEntity que fue asociada a Usuario
     * 
     */
    public ReservaEntity addReserva(Long UsuarioId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un reserva al usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        ReservaEntity reservasEntity = new ReservaEntity();
        reservasEntity.setId(reservasId);
        UsuarioEntity.getReservas().add(reservasEntity);
        return getReserva(UsuarioId, reservasId);
    }

    /**
     * Remplaza las instancias de Reserva asociadas a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param list Colección de instancias de ReservaEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de ReservaEntity asociada a la instancia de Usuario
     * 
     */
    public List<ReservaEntity> replaceReservas(Long UsuarioId, List<ReservaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un reserva del usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        UsuarioEntity.setReservas(list);
        return UsuarioEntity.getReservas();
    }

    /**
     * Desasocia un Reserva existente de un Usuario existente
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param reservasId Identificador de la instancia de Reserva
     * 
     */
    public void removeReserva(Long UsuarioId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un reserva del usuario con id = {0}", UsuarioId);
        UsuarioEntity entity = getUsuario(UsuarioId);
        ReservaEntity reservasEntity = new ReservaEntity();
        reservasEntity.setId(reservasId);
        entity.getReservas().remove(reservasEntity);
    }
    
    /**
     * Obtiene una colección de instancias de ComentarioEntity asociadas a una
     * instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @return Colección de instancias de ComentarioEntity asociadas a la instancia
     * de Usuario
     * 
     */
    public List<ComentarioEntity> listComentarios(Long UsuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarios del usuario con id = {0}", UsuarioId);
        return getUsuario(UsuarioId).getComentarios();
    }

    /**
     * Obtiene una instancia de ComentarioEntity asociada a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param comentariosId Identificador de la instancia de Comentario
     * 
     */
    public ComentarioEntity getComentario(Long UsuarioId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un comentarios del usuario con id = {0}", UsuarioId);
        List<ComentarioEntity> list = getUsuario(UsuarioId).getComentarios();
        ComentarioEntity comentariosEntity = new ComentarioEntity();
        comentariosEntity.setId(comentariosId);
        int index = list.indexOf(comentariosEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Comentario existente a un Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param comentariosId Identificador de la instancia de Comentario
     * @return Instancia de ComentarioEntity que fue asociada a Usuario
     * 
     */
    public ComentarioEntity addComentario(Long UsuarioId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un comentarios al usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        ComentarioEntity comentariosEntity = new ComentarioEntity();
        comentariosEntity.setId(comentariosId);
        UsuarioEntity.getComentarios().add(comentariosEntity);
        return getComentario(UsuarioId, comentariosId);
    }

    /**
     * Remplaza las instancias de Comentario asociadas a una instancia de Usuario
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param list Colección de instancias de ComentarioEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de ComentarioEntity asociada a la instancia de Usuario
     * 
     */
    public List<ComentarioEntity> replaceComentarios(Long UsuarioId, List<ComentarioEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un comentarios del usuario con id = {0}", UsuarioId);
        UsuarioEntity UsuarioEntity = getUsuario(UsuarioId);
        UsuarioEntity.setComentarios(list);
        return UsuarioEntity.getComentarios();
    }

    /**
     * Desasocia un Comentario existente de un Usuario existente
     *
     * @param UsuarioId Identificador de la instancia de Usuario
     * @param comentariosId Identificador de la instancia de Comentario
     * 
     */
    public void removeComentario(Long UsuarioId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un comentarios del usuario con id = {0}", UsuarioId);
        UsuarioEntity entity = getUsuario(UsuarioId);
        ComentarioEntity comentariosEntity = new ComentarioEntity();
        comentariosEntity.setId(comentariosId);
        entity.getComentarios().remove(comentariosEntity);
    }



    
    
}
