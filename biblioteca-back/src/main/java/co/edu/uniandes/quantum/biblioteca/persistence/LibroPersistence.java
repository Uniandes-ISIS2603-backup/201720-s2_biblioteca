/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
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
public class LibroPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());
      @PersistenceContext(unitName = "bibliotecaPU")
         protected EntityManager em; 
      
    /**
     *
     * @param entity objeto entidad libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public LibroEntity create(LibroEntity entity) 
    {
        LOGGER.info("Creando un Libro nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el libro en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un Libro nuevo");
        return entity;
    }

    /**
     * Actualiza un libro que se encuentra en la DB.
     *
     * @param entity: el libro  que viene con los nuevos cambios. Por ejemplo,
     * el numero de paginas para las ediciones pudo cambiar, lo actualiza.
     * @return un libro con los cambios aplicados.
     */
    public LibroEntity update(LibroEntity entity) {
        LOGGER.log(Level.INFO, "Actualizandolibro con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el libro con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Elimina un libro de la base de datos recibiendo como argumento el id
     * del mismo.
     *
     * @param id: id correspondiente al libro que será borrado.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando libro con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public LibroEntity find(Long id) para dar con la id del libro a borrar.
        LibroEntity entity = em.find(LibroEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from LibroEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun libro con el id que se envía de argumento
     *
     * @param id: id correspondiente al libro buscado.
     * @return un libroEntity.
     */
    public LibroEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando libro con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from LibroEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(LibroEntity.class, id);
    }
     
   public List<LibroEntity> findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el libro con name= ", name);
        TypedQuery<LibroEntity> q
                = em.createQuery("select u from LibroEntity u where u.name = :name", LibroEntity.class);
        q = q.setParameter("name", name);
        return q.getResultList();
    }
   
   public List<LibroEntity> findByPrestamo(Long idP) {
        LOGGER.log(Level.INFO, "Consultando los libros con idPrestamo= ", idP);
        TypedQuery<LibroEntity> q
                = em.createQuery("select * from LibroEntity where miprestamo_id = idP", LibroEntity.class);
        q = q.setParameter("idP", idP);
        return q.getResultList();
    }

    /**
     * Devuelve todos los libros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de
     * datos, "select u from LibroEntity u" es como un "select * from
     * LibroEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<LibroEntity> findAll() 
    {
        LOGGER.info("Consultando todos los libros.");
        // Se crea un query para buscar los libros en la base de datos.
        TypedQuery query = em.createQuery("select u from LibroEntity u", LibroEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de libros.
        return query.getResultList();
    }

    
}
