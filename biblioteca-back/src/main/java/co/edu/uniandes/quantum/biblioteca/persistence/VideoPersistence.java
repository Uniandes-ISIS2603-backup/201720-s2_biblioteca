/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class VideoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(VideoPersistence.class.getName());
      @PersistenceContext(unitName = "bibliotecaPU")
         protected EntityManager em; 
      
    /**
     *
     * @param entity objeto entidad video que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public VideoEntity create(VideoEntity entity) 
    {
        LOGGER.info("Creando un Video nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el video en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Video nuevo");
        return entity;
    }

    /**
     * Actualiza un video que se encuentra en la DB.
     *
     * @param entity: el video  que viene con los nuevos cambios. Por ejemplo,
     * la duración pudo cambiar, lo actualiza.
     * @return un video con los cambios aplicados.
     */
    public VideoEntity update(VideoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando video con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el video con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina un video de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente al video que será borrado.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando video con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public VideoEntity find(Long id) para dar con la id del video a borrar.
        VideoEntity entity = em.find(VideoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from LibroEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun video con el id que se envía de argumento
     *
     * @param id: id correspondiente al video buscado.
     * @return un VideoEntity.
     */
    public VideoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando video con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from VideoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(VideoEntity.class, id);
    }
    
    
   public VideoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el video con name= ", name);
        TypedQuery<VideoEntity> q
                = em.createQuery("select u from VideoEntity u where u.name = :name", VideoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    /**
     * Devuelve todos los videos de la base de datos.
     *
     * @return una lista con todos los videos que encuentre en la base de
     * datos, "select u from VideoEntity u" es como un "select * from
     * VideoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<VideoEntity> findAll() 
    {
        LOGGER.info("Consultando todos los videos.");
        // Se crea un query para buscar los videos en la base de datos.
        TypedQuery query = em.createQuery("select u from VideoEntity u", VideoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de videos.
        return query.getResultList();
    }

    
}
