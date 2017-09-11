/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author f.posada
 */
@Stateless
public class PrestamoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em; 
    
    /**
     *
     * @param entity objeto Prestamo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PrestamoEntity create(PrestamoEntity entity) {
        LOGGER.info("Creando un prestamo nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Prestamo en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un prestamo nuevo");
        return entity;
    }

    /**
     * Actualiza un prestamo.
     *
     * @param entity: el Prestamo que viene con los nuevos cambios. Por ejemplo,
     * la direccion pudo cambiar. En ese caso, se haria uso del método update.
     * @return un prestamo con los cambios aplicados.
     */
    public PrestamoEntity update(PrestamoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Prestamo con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Prestamo con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un prestamo de la base de datos recibiendo como argumento el id
     * de la Prestamo
     *
     * @param id: id correspondiente a la Prestamo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Prestamo con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public PrestamoEntity find(Long id) para obtener la Prestamo a borrar.
        PrestamoEntity entity = em.find(PrestamoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrestamoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun prestamo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Prestamo buscada.
     * @return un prestamo.
     */
    public PrestamoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Prestamo con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PrestamoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(PrestamoEntity.class, id);
    }

    /**
     * Devuelve todas las Prestamoes de la base de datos.
     *
     * @return una lista con todas las Prestamoes que encuentre en la base de
     * datos, "select u from PrestamoEntity u" es como un "select * from
     * PrestamoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<PrestamoEntity> findAll() {
        LOGGER.info("Consultando todas las Prestamoes");
        // Se crea un query para buscar todas las Prestamoes en la base de datos.
        TypedQuery query = em.createQuery("select u from PrestamoEntity u", PrestamoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Prestamoes.
        return query.getResultList();
    }
    
}
