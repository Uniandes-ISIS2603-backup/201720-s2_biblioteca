/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
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
public class SalaPersistence 
{
   private static final Logger LOGGER = Logger.getLogger(SalaPersistence.class.getName());
      @PersistenceContext(unitName = "bibliotecaPU")
         protected EntityManager em; 
      
    /**
     *
     * @param entity objeto entidad sala que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SalaEntity create(SalaEntity entity) 
    {
        LOGGER.info("Creando una sala nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la sala en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando una sala nueva");
        return entity;
    }

    /**
     * Actualiza una sala que se encuentra en la DB.
     *
     * @param entity: la sala  que viene con los nuevos cambios. Por ejemplo,
     * la capacidad de la sala pudo cambiar, lo actualiza.
     * @return una sala con los cambios aplicados.
     */
    public SalaEntity update(SalaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando sala con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la sala con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina una sala de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente a la sala  que será borrado.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando la sala con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public SalaEntity find(Long id) para dar con la id de la sala a borrar.
        SalaEntity entity = em.find(SalaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from SalaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay alguna sala con el id que se envía de argumento
     * @param idBiblioteca: id correspondiente a la biblioteca donde se busca.
     * @param idSala: id correspondiente al Sala buscado.
     * @return un SalaEntity.
     
    public SalaEntity find(Long idBiblioteca, Long idSala) {
        TypedQuery<SalaEntity> q = em.createQuery("select p from SalaEntity p where (p.miBiblioteca.id = :idBiblioteca) and (p.id = :idSala)", SalaEntity.class);
        q.setParameter("idBiblioteca", idBiblioteca);
        q.setParameter("idSala", idSala);
        List<SalaEntity> results = q.getResultList();
        SalaEntity sala = null;
        if (results == null) {
            sala = null;
        } else if (results.isEmpty()) {
            sala = null;
        } else if (results.size() >= 1) {
            sala = results.get(0);
        }
        return sala;
    }*/
    
     public SalaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sala con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from LibroEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(SalaEntity.class, id);
    }
    
    
       /**
     * Devuelve todas las salas de la base de datos.
     *
     * @return una lista con todas las salas que encuentre en la base de
     * datos, "select u from SalaEntity u" es como un "select * from
     * SalaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<SalaEntity> findAll() 
    {
        LOGGER.info("Consultando todas las salas.");
        // Se crea un query para buscar las salas en la base de datos.
        TypedQuery query = em.createQuery("select u from SalaEntity u", SalaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de salas.
        return query.getResultList();
    }
}