/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.SalaPersistence;
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
public class SalaLogic 
{

 private static final Logger LOGGER = Logger.getLogger(SalaLogic.class.getName());   
 
 @Inject
    private SalaPersistence persistence;
 
 
 /**
  * Devuelve los Salas que se encuentran en la base de datos.
  * @return  los Salas como una lista de objetos.
  * Corresponde a la lógica de GET Salas
  */
  public List<SalaEntity> getSalas() {
        LOGGER.info("Inicia proceso de consultar todos los Salas");
        List<SalaEntity>  salas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los Salas");
        return salas;
    }
  
  /**
  * Devuelve el Sala que se encuentran en la base de datos con el id dado.
  * @param id del Sala a buscar en la DB.
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de GET Salas/{id}
  */
 public SalaEntity getSala(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Sala con id={0}", id);
        SalaEntity sala = persistence.find(id);
        if (sala == null) 
        {
            LOGGER.log(Level.SEVERE, "El Sala con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Sala con id={0}", id);
        return sala;
    }
 
 /**
  * Devuelve el Sala que se hizo persistir en la base de datos.
  * @param entity Sala a persistir
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de POST/Salas
  */
   public SalaEntity crearSala(SalaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Sala");
        if (!validateId(entity.getId())) 
        {
            throw new BusinessLogicException("El  (Id) es inválido");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Sala");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un Sala.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }
    
  /**
  * Devuelve el Sala que se actualizo en la base de datos.
  * @param id  del Sala a actualizar.
  * @param entity Sala a actualizar
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de PUT Salas/{id}
  */
      public SalaEntity updateSala(Long id, SalaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Sala con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El   (id) es inválido");
        }
        SalaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Sala con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el Sala que se borrará de la base de datos.
  * @param id  del Sala a borrar.
  * Corresponde a la lógica de DELETE Salas/{id}
  */
    public void deleteSalas(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Sala con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar Sala con id={0}", id);
    }


}

