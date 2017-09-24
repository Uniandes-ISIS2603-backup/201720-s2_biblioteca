package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;

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
public class EVideoPersistence {

    private static final Logger LOGGER = Logger.getLogger(EVideoPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto EVideo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EVideoEntity create(EVideoEntity entity) {
        LOGGER.info("Creando un EVideo nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la EVideo en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un EVideo nueva");
        return entity;
    }

    /**
     * Actualiza un EVideo.
     *
     * @param entity: la EVideo que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un EVideo con los cambios aplicados.
     */
    public EVideoEntity update(EVideoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando EVideo con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la EVideo con los cambios, esto es similar a
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un EVideo de la base de datos recibiendo como argumento el id
     * de la EVideo
     *
     * @param id: id correspondiente a la EVideo a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando EVideo con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public EVideoEntity find(Long id) para obtener la EVideo a borrar.
        EVideoEntity entity = em.find(EVideoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EVideoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun EVideo con el id que se envía de argumento
     *
     * @param id: id correspondiente a la EVideo buscada.
     * @return un EVideo.
     */
    public EVideoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando EVideo con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EVideoEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(EVideoEntity.class, id);
    }

    /**
     * Devuelve todas las EVideoes de la base de datos.
     *
     * @return una lista con todas las EVideoes que encuentre en la base de
     * datos, "select u from EVideoEntity u" es como un "select * from
     * EVideoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<EVideoEntity> findAll() {
        LOGGER.info("Consultando todas las EVideoes");
        // Se crea un query para buscar todas las EVideoes en la base de datos.
        TypedQuery query = em.createQuery("select u from EVideoEntity u", EVideoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de EVideoes.
        return query.getResultList();
    }

    /**
     * Busca si hay algun EVideo con el codigo que se envía de argumento
     *
     * @param codigo: codigo del EVideo que se está buscando
     * @return null si no existe ningun EVideo con el codigo del argumento.
     * Si existe alguna devuelve la primera.
     */
    public EVideoEntity findByName(String codigo) {
        LOGGER.log(Level.INFO, "Consultando EVideo por código ", codigo);

        // Se crea un query para buscar EVideoes con el codigo que recibe el método como argumento. ":codigo" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EVideoEntity e where e.codigo = :codigo", EVideoEntity.class);
        // Se remplaza el placeholder ":codigo" con el valor del argumento
        query = query.setParameter("codigo", codigo);
        // Se invoca el query se obtiene la lista resultado
        List<EVideoEntity> sameCodigo = query.getResultList();
        if (sameCodigo.isEmpty()) {
            return null;
        } else {
            return sameCodigo.get(0);
        }
    }

}