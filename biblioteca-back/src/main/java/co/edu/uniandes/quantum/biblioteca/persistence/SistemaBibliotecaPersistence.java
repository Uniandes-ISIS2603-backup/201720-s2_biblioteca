package co.edu.uniandes.quantum.biblioteca.persistence;


import co.edu.uniandes.quantum.biblioteca.entities.SistemaBibliotecaEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class SistemaBibliotecaPersistence {
    private static final Logger LOGGER = Logger.getLogger(SistemaBibliotecaPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

   /**
     *
     * @param entity objeto SistemaBiblioteca que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SistemaBibliotecaEntity create(SistemaBibliotecaEntity entity) {
        LOGGER.info("Creando un SistemaBiblioteca nuevo");

        em.persist(entity);
        LOGGER.info("Creando un SistemaBiblioteca nuevo");
        return entity;
    }

    /**
     * Actualiza un SistemaBiblioteca.
     *
     * @param entity: el SistemaBiblioteca que viene con los nuevos cambios. Por ejemplo,
     * la direccion pudo cambiar. En ese caso, se haria uso del método update.
     * @return un SistemaBiblioteca con los cambios aplicados.
     */
    public SistemaBibliotecaEntity update(SistemaBibliotecaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando SistemaBiblioteca con id={0}", entity.getId());

        return em.merge(entity);
    }

    /**
     *
     * Borra un SistemaBiblioteca de la base de datos recibiendo como argumento el id
     * de la SistemaBiblioteca
     *
     * @param id: id correspondiente a la SistemaBiblioteca a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando SistemaBiblioteca con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public SistemaBibliotecaEntity find(Long id) para obtener la SistemaBiblioteca a borrar.
        SistemaBibliotecaEntity entity = em.find(SistemaBibliotecaEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from SistemaBibliotecaEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun SistemaBiblioteca con el id que se envía de argumento
     *
     * @param id: id correspondiente a la SistemaBiblioteca buscada.
     * @return un SistemaBiblioteca.
     */
    public SistemaBibliotecaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando SistemaBiblioteca con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from SistemaBibliotecaEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        return em.find(SistemaBibliotecaEntity.class, id);
    }


    public SistemaBibliotecaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando SistemaBiblioteca con name= ", name);
        TypedQuery<SistemaBibliotecaEntity> q
                = em.createQuery("select u from SistemaBibliotecaEntity u where u.name = :name", SistemaBibliotecaEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    /**
     * Devuelve todas las SistemaBibliotecaes de la base de datos.
     *
     * @return una lista con todas las SistemaBibliotecaes que encuentre en la base de
     * datos, "select u from SistemaBibliotecaEntity u" es como un "select * from
     * SistemaBibliotecaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    public List<SistemaBibliotecaEntity> findAll() {
        LOGGER.info("Consultando todas las SistemaBibliotecaes");
        // Se crea un query para buscar todas las SistemaBibliotecaes en la base de datos.
        TypedQuery query = em.createQuery("select u from SistemaBibliotecaEntity u", SistemaBibliotecaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de SistemaBibliotecaes.
        return query.getResultList();
    }
}
