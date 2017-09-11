/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
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
public class UsuarioPersistence{ private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em; 
    
    /**
     *
     * @param entity objeto Usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UsuarioEntity create(UsuarioEntity entity) {
        LOGGER.info("Creando un usuario nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la Usuario en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un usuario nuevo");
        return entity;
    }

    /**
     * Actualiza un usuario.
     *
     * @param entity: el Usuario que viene con los nuevos cambios. Por ejemplo,
     * la direccion pudo cambiar. En ese caso, se haria uso del método update.
     * @return un usuario con los cambios aplicados.
     */
    public UsuarioEntity update(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Usuario con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la Usuario con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id
     * de la Usuario
     *
     * @param id: id correspondiente a la Usuario a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Usuario con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener la Usuario a borrar.
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from UsuarioEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Usuario buscada.
     * @return un usuario.
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Usuario con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from UsuarioEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(UsuarioEntity.class, id);
    }
    
    
   public UsuarioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando usuario con name= ", name);
        TypedQuery<UsuarioEntity> q
                = em.createQuery("select u from UsuarioEntity u where u.name = :name", UsuarioEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    /**
     * Devuelve todas las Usuarioes de la base de datos.
     *
     * @return una lista con todas las Usuarioes que encuentre en la base de
     * datos, "select u from UsuarioEntity u" es como un "select * from
     * UsuarioEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando todas las Usuarioes");
        // Se crea un query para buscar todas las Usuarioes en la base de datos.
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Usuarioes.
        return query.getResultList();
    }

    
}
