/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;
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
public class EComentarioPersistence
{
    private static final Logger LOGGER = Logger.getLogger(EComentarioPersistence.class.getName());
    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto entidad EComentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EComentarioEntity create(EComentarioEntity entity)
    {
        LOGGER.info("Creando un EComentario nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el EComentario en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Comentatrio nuevo");
        return entity;
    }

    /**
     * Actualiza un EComentario que se encuentra en la DB.
     *
     * @param entity: el EComentario  que viene con los nuevos cambios. Por ejemplo,
     * el numero de paginas para las ediciones pudo cambiar, lo actualiza.
     * @return un EComentario con los cambios aplicados.
     */
    public EComentarioEntity update(EComentarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando EComentario con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el EComentario con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina un EComentario de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente al EComentario que será borrado.
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando EComentario con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public EComentarioEntity find(Long id) para dar con la id del EComentario a borrar.
        EComentarioEntity entity = em.find(EComentarioEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EComentarioEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun EComentario con el id que se envía de argumento
     *
     * @param id: id correspondiente al EComentario buscado.
     * @return un EComentarioEntity.
     */
    public EComentarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando EComentario con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EComentarioEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(EComentarioEntity.class, id);
    }

    /**
     * Retorna la lista de comentarios de el eBook con id dado
     * @param id
     * @return
     */
    public List<EComentarioEntity> findCommentsEBook(Long id){
        TypedQuery<EComentarioEntity> query =  em.createQuery("select c from EComentarioEntity c where c.recurso.id = :idRecurso", EComentarioEntity.class);
        query.setParameter("idRecurso", id);
        return query.getResultList();
    }


    /**
     * Encuentra el comentario con el nombre(título) dado por parámetro
     * @param name
     * @return
     */
    public List<EComentarioEntity> findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el EComentario con name= ", name);
        TypedQuery<EComentarioEntity> q
                = em.createQuery("select u from EComentarioEntity u where u.name = :name", EComentarioEntity.class);
        q = q.setParameter("name", name);
        return q.getResultList();
    }

    /**
     * Devuelve todos los EComentarios de la base de datos.
     *
     * @return una lista con todos los EComentarios que encuentre en la base de
     * datos, "select u from EComentarioEntity u" es como un "select * from
     * EComentarioEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<EComentarioEntity> findAll()
    {
        LOGGER.info("Consultando todos los EComentarios.");
        // Se crea un query para buscar los EComentarios en la base de datos.
        TypedQuery query = em.createQuery("select u from EComentarioEntity u", EComentarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de EComentarios.
        return query.getResultList();
    }


}