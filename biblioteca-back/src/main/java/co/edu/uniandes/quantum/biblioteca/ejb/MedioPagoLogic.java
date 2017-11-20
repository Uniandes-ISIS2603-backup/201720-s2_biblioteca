/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.MedioPagoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MedioPagoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.MedioPagoPersistence;
import co.edu.uniandes.quantum.biblioteca.persistence.MedioPagoPersistence;
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
public class MedioPagoLogic {

    private static final Logger LOGGER = Logger.getLogger(MedioPagoLogic.class.getName());

    @Inject
    private MedioPagoPersistence persistence;

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Obtiene la lista de los registros de MedioPago que pertenecen a un Usuario.
     *
     * @param usuarioid id del Usuario el cual es padre de los MedioPagos.
     * @return Colección de objetos de MedioPagoEntity.
     * @throws co.edu.uniandes.csw.usuariostore.exceptions.BusinessLogicException
     */
    public List<MedioPagoEntity> getMedioPagos(Long usuarioid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los medioPagos");
        List<MedioPagoEntity> med=persistence.findAll(usuarioid);
        if (med == null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene medioPagos");
        }
        if (med.isEmpty()) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene medioPagos");
        }
        return med;
    }

    /**
     * Obtiene los datos de una instancia de MedioPago a partir de su ID.
     *
     * @param usuarioid
     * @pre La existencia del elemento padre Usuario se debe garantizar.
     * @param reviewid) Identificador del MedioPago a consultar
     * @return Instancia de MedioPagoEntity con los datos del MedioPago consultado.
     * 
     */
    public MedioPagoEntity getMedioPago(Long usuarioid, Long reviewid) {
        return persistence.find(usuarioid, reviewid);
    }

    /**
     * Se encarga de crear un MedioPago en la base de datos.
     *
     * @param entity Objeto de MedioPagoEntity con los datos nuevos
     * @param usuarioid id del Usuario el cual sera padre del nuevo MedioPago.
     * @return Objeto de MedioPagoEntity con los datos nuevos y su ID.
     * 
     */
    public MedioPagoEntity createMedioPago(Long usuarioid, MedioPagoEntity entity) {
        LOGGER.info("Inicia proceso de crear medioPago");
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        entity.setMiUsuario(usuario);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de MedioPago.
     *
     * @param entity Instancia de MedioPagoEntity con los nuevos datos.
     * @param usuarioid id del Usuario el cual sera padre del MedioPago actualizado.
     * @return Instancia de MedioPagoEntity con los datos actualizados.
     * 
     */
    public MedioPagoEntity updateMedioPago(Long usuarioid, MedioPagoEntity entity) {
        LOGGER.info("Inicia proceso de actualizar review");
        MedioPagoEntity med = persistence.find(entity.getId());
        entity.setMiUsuario(usuarioLogic.getUsuario(usuarioid));
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de MedioPago de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param usuarioid id del Usuario el cual es padre del MedioPago.
     * 
     */
    public void deleteMedioPago(Long usuarioid, Long id) {
        LOGGER.info("Inicia proceso de borrar review");
        MedioPagoEntity old = getMedioPago(usuarioid, id);
        persistence.delete(old.getId());
    }

    public List<MedioPagoEntity> getMedioPagos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
