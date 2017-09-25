/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.BlogEntity;
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
public class BlogPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());
      @PersistenceContext(unitName = "bibliotecaPU")
         protected EntityManager em; 
      
    /**
     *
     * @param entity objeto entidad Blog que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BlogEntity create(BlogEntity entity) 
    {
        LOGGER.info("Creando un Blog nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el blog en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Blog nuevo");
        return entity;
    }

    /**
     * Actualiza un Blog que se encuentra en la DB.
     *
     * @param entity: el blog  que viene con los nuevos cambios. Por ejemplo,
     * el numero de paginas para las ediciones pudo cambiar, lo actualiza.
     * @return un Blog con los cambios aplicados.
     */
    public BlogEntity update(BlogEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Blog con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el blog con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina un Blog de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente al Blog que será borrado.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Blog con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public BlogEntity find(Long id) para dar con la id del blog a borrar.
        BlogEntity entity = em.find(BlogEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from BlogEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun Blog con el id que se envía de argumento
     *
     * @param id: id correspondiente al Blog buscado.
     * @return un blogEntity.
     */
    public BlogEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando blog con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from BlogEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(BlogEntity.class, id);
    }
     
   public List<BlogEntity> findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el blog con name= ", name);
        TypedQuery<BlogEntity> q
                = em.createQuery("select u from BlogEntity u where u.name = :name", BlogEntity.class);
        q = q.setParameter("name", name);
        return q.getResultList();
    }

    /**
     * Devuelve todos los blogs de la base de datos.
     *
     * @return una lista con todos los blogs que encuentre en la base de
     * datos, "select u from blogsEntity u" es como un "select * from
     * BlogEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<BlogEntity> findAll() 
    {
        LOGGER.info("Consultando todos los blogs.");
        // Se crea un query para buscar los blogs en la base de datos.
        TypedQuery query = em.createQuery("select u from BlogEntity u", BlogEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de blogs.
        return query.getResultList();
    }

    
}
