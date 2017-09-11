/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;


import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author cg.chavarro
 */
@Stateless
public class ReservaPersistence
{
 private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());    
      @PersistenceContext(unitName = "reservaPU")
         protected EntityManager em; 
      
     /**
     *
     * @param entity objeto reserva que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReservaEntity create(ReservaEntity entity) {
        LOGGER.info("Creando una nueva reserva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la reserva en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una nueva reserva");
        return entity;
    }

    /**
     * Actualiza una reserva.
     *
     * @param entity: la reserva que viene con los nuevos cambios. Por ejemplo,
     * El estado de la reserva cambio, lo actualiza en la DB.
     * @return una reserva con los cambios aplicados.
     */
    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando la reserva con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la reserva con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra una reserva de la base de datos recibiendo como argumento el id
     * de la misma
     *
     * @param id: id correspondiente a la reserva a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando la reserva con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public ReservaEntity find(Long id) para obtener la reserva a borrar.
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from ReservaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna reserva con el id que se envía de argumento
     *
     * @param id: id correspondiente a la reserva buscada.
     * @return una entidad de reserva.
     */
    public ReservaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando la reserva con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ReservaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(ReservaEntity.class, id);
    }

    /**
     * Devuelve todas las reservas de la base de datos.
     *
     * @return una lista con todas las reservas que encuentre en la base de
     * datos, "select u from RerservaEntity u" es como un "select * from
     * ReservaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<ReservaEntity> findAll() {
        LOGGER.info("Consultando todas las reservas");
        // Se crea un query para buscar todas las reservas en la base de datos.
        TypedQuery query = em.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de reservas.
        return query.getResultList();
    }
    
}
