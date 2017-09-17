/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.RecursoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
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
        UsuarioEntity usuario = usuarioLogic.getUsuario(usuarioid);
        if (usuario.getPrestamos() == null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene prestamos");
        }
        if (usuario.getPrestamos().isEmpty()) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene prestamos");
        }
        return usuario.getPrestamos();
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
    public PrestamoEntity getPrestamo(Long usuarioid, Long reviewid) {
        return persistence.find(usuarioid, reviewid);
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
        LOGGER.info("Inicia proceso de crear review");
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
}
