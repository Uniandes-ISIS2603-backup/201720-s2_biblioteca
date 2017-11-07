/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;


import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.EComentarioPersistence;
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
public class EComentarioLogic
{

    private static final Logger LOGGER = Logger.getLogger(EComentarioLogic.class.getName());

    @Inject
    private EComentarioPersistence persistence;


    /**
     * Devuelve los EComentario que se encuentran en la base de datos.
     * @return  los EComentarios como una lista de objetos.
     * Corresponde a la lógica de GET EComentarios
     */
    public List<EComentarioEntity> getEComentarios() {
        LOGGER.info("Inicia proceso de consultar todos los EComentarios");
        List<EComentarioEntity>  EComentario = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los EComentarios");
        return EComentario;
    }

    /**
     * Devuelve el EComentario que se encuentran en la base de datos con el id dado.
     * @param id del EComentario a buscar en la DB.
     * @return  el EComentario como un objeto Entity.
     * Corresponde a la lógica de GET EComentarios/{id}
     */
    public EComentarioEntity getEComentario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar coomentario con id={0}", id);
        EComentarioEntity EComentario = persistence.find(id);
        if (EComentario == null)
        {
            LOGGER.log(Level.SEVERE, "El EComentario con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar EComentario con id={0}", id);
        return EComentario;
    }

    /**
     * Devuelve el EComentario que se hizo persistir en la base de datos.
     * @param entity EComentario a persistir
     * @return  el EComentario como un objeto Entity.
     * Corresponde a la lógica de POST/EComentario
     */
    public EComentarioEntity crearEComentario(EComentarioEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de EComentario");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de EComentario");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un EComentario.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }

    /**
     * Devuelve el EComentario que se actualizo en la base de datos.
     * @param id  del EComentario a actualizar.
     * @param entity EComentario a actualizar
     * @return  el EComentario como un objeto Entity.
     * Corresponde a la lógica de PUT EComentario/{id}
     */
    public EComentarioEntity updateEComentario(Long id, EComentarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar EComentario con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El   (id) es inválido");
        }
        EComentarioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar EComentario con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Devuelve el EComentario que se borrará de la base de datos.
     * @param id  del EComentario a borrar.
     * Corresponde a la lógica de DELETE EComentario/{id}
     */
    public void deleteEComentario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar EComentario con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar EComentario con id={0}", id);
    }


}