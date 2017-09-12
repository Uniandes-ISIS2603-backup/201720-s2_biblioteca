package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class EBookPersistence {

    private static final Logger LOGGER = Logger.getLogger(EBookPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto EBook que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EBookEntity create(EBookEntity entity) {
        LOGGER.info("Creando un nuevo eBook");
        /* Note que hacemos uso de un método propio de EntityManager para persistir el eBook en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un nuevo eBook");
        return entity;
    }

    /**
     * Actualiza un EBook.
     *
     * @param entity: el EBook que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un eBook con los cambios aplicados.
     */
    public EBookEntity update(EBookEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando EBook con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la EBook con los cambios, esto es similar a
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un EBook de la base de datos recibiendo como argumento el id
     * de la EBook
     *
     * @param id: id correspondiente a la EBook a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando EBook con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public EBookEntity find(Long id) para obtener la EBook a borrar.
        EBookEntity entity = em.find(EBookEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EBookEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun EBook con el id que se envía de argumento
     *
     * @param id: id correspondiente a la EBook buscada.
     * @return un EBook.
     */
    public EBookEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando EBook con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EBookEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(EBookEntity.class, id);
    }

    /**
     * Devuelve todas las EBookes de la base de datos.
     *
     * @return una lista con todas las EBookes que encuentre en la base de
     * datos, "select u from EBookEntity u" es como un "select * from
     * EBookEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<EBookEntity> findAll() {
        LOGGER.info("Consultando todas las EBooks");
        // Se crea un query para buscar todas las EBooks en la base de datos.
        TypedQuery query = em.createQuery("select u from EBookEntity u", EBookEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de EBookes.
        return query.getResultList();
    }

    /**
     * Busca si hay algun EBook con el codigo que se envía de argumento
     *
     * @param codigo: codigo del EBook que se está buscando
     * @return null si no existe ningun EBook con el codigo del argumento.
     * Si existe alguna devuelve la primera.
     */
    public EBookEntity findByCodigo(String codigo) {
        LOGGER.log(Level.INFO, "Consultando EBook por código ", codigo);

        // Se crea un query para buscar EBookes con el codigo que recibe el método como argumento. ":codigo" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EBookEntity e where e.codigo = :codigo", EBookEntity.class);
        // Se remplaza el placeholder ":codigo" con el valor del argumento
        query = query.setParameter("codigo", codigo);
        // Se invoca el query se obtiene la lista resultado
        List<EBookEntity> sameCodigo = query.getResultList();
        if (sameCodigo.isEmpty()) {
            return null;
        } else {
            return sameCodigo.get(0);
        }
    }

}
