/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author da.leon
 */
@Stateless
public class ComentarioPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());
      @PersistenceContext(unitName = "bibliotecaPU")
         protected EntityManager em; 
      
    /**
     *
     * @param entity objeto entidad comentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComentarioEntity create(ComentarioEntity entity) 
    {
        LOGGER.info("Creando un Comentario nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el comentario en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Comentatrio nuevo");
        return entity;
    }

    /**
     * Actualiza un Comentario que se encuentra en la DB.
     *
     * @param entity: el comentario  que viene con los nuevos cambios. Por ejemplo,
     * el numero de paginas para las ediciones pudo cambiar, lo actualiza.
     * @return un comentario con los cambios aplicados.
     */
    public ComentarioEntity update(ComentarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando comentario con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el comentario con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina un comentario de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente al comentario que será borrado.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando comentario con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public ComentarioEntity find(Long id) para dar con la id del comentario a borrar.
        ComentarioEntity entity = em.find(ComentarioEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from ComentarioEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun Comentario con el id que se envía de argumento
     *
     * @param id: id correspondiente al comentario buscado.
     * @return un ComentarioEntity.
     */
    public ComentarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando comentario con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ComentarioEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(ComentarioEntity.class, id);
    }
     
   public List<ComentarioEntity> findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el comentario con name= ", name);
        TypedQuery<ComentarioEntity> q
                = em.createQuery("select u from ComentarioEntity u where u.name = :name", ComentarioEntity.class);
        q = q.setParameter("name", name);
        return q.getResultList();
    }

    /**
     * Devuelve todos los comentarios de la base de datos.
     *
     * @return una lista con todos los comentarios que encuentre en la base de
     * datos, "select u from ComentarioEntity u" es como un "select * from
     * ComentarioEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<ComentarioEntity> findAll() 
    {
        LOGGER.info("Consultando todos los comentarios.");
        // Se crea un query para buscar los comentarios en la base de datos.
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de comentarios.
        return query.getResultList();
    }

    
}
