/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
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

    /**
     * @param entity
     * @return MultaEntity creado.
     * @throws BusinessLogicException
     */
    public MultaEntity createMulta(MultaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de una multa");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de una multa");
        return entity;
    }

    public MultaEntity updateEntity(MultaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualización de una multa.");
        MultaEntity entityActualizado = persistence.update(entity);
        LOGGER.info("Termina el proceso de actualización de una multa.");
        return entityActualizado;
    }

    public void deleteMulta(Long id) {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar la multa con el id: {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina el proceso de eliminar la multa con el id: {0}", id);
    }

    public MultaEntity getMulta(Long id){
        LOGGER.log(Level.INFO, "Inicia el proceso de encontrar la multa con id: {0}", id);
        MultaEntity entity = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina el proceso de encontrar la multa con id: {0}", id);
        return entity;
    }
    
    public List<MultaEntity> getMultas(){
        LOGGER.info("Inicia el proceso de encontrar todas las multas.");
        List<MultaEntity> multas = persistence.findAll();
        LOGGER.info("Termina el proceso de encontrar todas las multas.");
        return multas;
    }
}
