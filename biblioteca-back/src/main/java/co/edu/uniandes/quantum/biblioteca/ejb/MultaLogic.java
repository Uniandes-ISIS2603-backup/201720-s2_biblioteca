/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.MultaPersistence;
import co.edu.uniandes.quantum.biblioteca.persistence.MultaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.sanmiguel
 */
@Stateless
public class MultaLogic {

    private static final Logger LOGGER = Logger.getLogger(MultaLogic.class.getName());

    @Inject
    private MultaPersistence persistence;

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Obtiene la lista de los registros de Multa que pertenecen a un Usuario.
     *
     * @param usuarioid id del Usuario el cual es padre de los Multas.
     * @return Colección de objetos de MultaEntity.
     * @throws co.edu.uniandes.csw.usuariostore.exceptions.BusinessLogicException
     */
    public List<MultaEntity> getMultas(Long usuarioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los multas");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        if (usuario.getMultas() == null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene multas");
        }
        if (usuario.getMultas().isEmpty()) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene multas");
        }
        return usuario.getMultas();
    }

    /**
     * Obtiene los datos de una instancia de Multa a partir de su ID.
     *
     * @param usuarioid
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reviewid) Identificador del Multa a consultar
     * @return Instancia de MultaEntity con los datos del Multa consultado.
     * 
     */
    public MultaEntity getMulta(Long usuarioid, Long reviewid) {
        return persistence.find(usuarioid, reviewid);
    }

    /**
     * Se encarga de crear un Multa en la base de datos.
     *
     * @param entity Objeto de MultaEntity con los datos nuevos
     * @param usuarioid id del Usuario el cual sera padre del nuevo Multa.
     * @return Objeto de MultaEntity con los datos nuevos y su ID.
     * 
     */
    public MultaEntity createMulta(Long usuarioid, MultaEntity entity) {
        LOGGER.info("Inicia proceso de crear review");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Multa.
     *
     * @param entity Instancia de MultaEntity con los nuevos datos.
     * @param usuarioid id del Usuario el cual sera padre del Multa actualizado.
     * @return Instancia de MultaEntity con los datos actualizados.
     * 
     */
    public MultaEntity updateMulta(Long usuarioid, MultaEntity entity) {
        LOGGER.info("Inicia proceso de actualizar review");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Multa de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param usuarioid id del Usuario el cual es padre del Multa.
     * 
     */
    public void deleteMulta(Long usuarioid, Long id) {
        LOGGER.info("Inicia proceso de borrar review");
        MultaEntity old = getMulta(usuarioid, id);
        persistence.delete(old.getId());
    }

    public List<MultaEntity> getMultas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
