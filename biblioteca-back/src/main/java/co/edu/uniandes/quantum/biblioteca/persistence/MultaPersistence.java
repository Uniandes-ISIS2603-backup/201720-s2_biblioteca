/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.sanmiguel
 */
@Stateless
public class MultaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MultaPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Multa que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MultaEntity create(MultaEntity entity) {
        LOGGER.info("Creando un multa nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la multa en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Multa nueva");
        return entity;
    }

    /**
     * Actualiza una multa.
     *
     * @param entity: la Multa que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return una multa con los cambios aplicados.
     */
    public MultaEntity update(MultaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Multa con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Multa con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un Multa de la base de datos recibiendo como argumento el id
     * de la Multa
     *
     * @param id: id correspondiente a la Multa a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Multa con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public MultaEntity find(Long id) para obtener la Multa a borrar.
       MultaEntity entity = em.find(MultaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from MultaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun multa con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Multa buscada.
     * @return un multa.
     */
    public MultaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Multa con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from MultaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(MultaEntity.class, id);
    }

    /**
     * Devuelve todas las Multas de la base de datos.
     *
     * @return una lista con todas las Multaes que encuentre en la base de
     * datos, "select u from MultaEntity u" es como un "select * from
     * MultaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<MultaEntity> findAll() {
        LOGGER.info("Consultando todas las Multas");
        // Se crea un query para buscar todas las Multas en la base de datos.
        TypedQuery query = em.createQuery("select u from MultaEntity u", MultaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Multaes.
        return query.getResultList();
    }
      
    
}
