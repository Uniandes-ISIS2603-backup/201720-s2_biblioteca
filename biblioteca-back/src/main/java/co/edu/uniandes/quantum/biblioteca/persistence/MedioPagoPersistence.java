/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.MedioPagoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.sanmiguel
 */
@Stateless
public class MedioPagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MedioPagoPersistence.class.getName());

    @PersistenceContext(unitName = "bibliotecaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto MedioPago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MedioPagoEntity create(MedioPagoEntity entity) {
        LOGGER.info("Creando un medioPago nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la medioPago en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(entity);
        LOGGER.info("Creando un MedioPago nueva");
        return entity;
    }

    /**
     * Actualiza una medioPago.
     *
     * @param entity: la MedioPago que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return una medioPago con los cambios aplicados.
     */
    public MedioPagoEntity update(MedioPagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando MedioPago con id={0}", entity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la MedioPago con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }

    /**
     *
     * Borra un MedioPago de la base de datos recibiendo como argumento el id
     * de la MedioPago
     *
     * @param id: id correspondiente a la MedioPago a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando MedioPago con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public MedioPagoEntity find(Long id) para obtener la MedioPago a borrar.
       MedioPagoEntity entity = em.find(MedioPagoEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from MedioPagoEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
        em.remove(entity);
    }

    /**
     * Busca si hay algun medioPago con el id que se envía de argumento
     *
     * @param id: id correspondiente a la MedioPago buscada.
     * @return un medioPago.
     */
    public MedioPagoEntity find(Long usuarioid, Long medioPagoid) {
        TypedQuery<MedioPagoEntity> q = em.createQuery("select p from MedioPagoEntity p where (p.miUsuario.id = :usuarioid) and (p.id = :medioPagoid)", MedioPagoEntity.class);
        q.setParameter("usuarioid", usuarioid);
        q.setParameter("medioPagoid", medioPagoid);
        List<MedioPagoEntity> results = q.getResultList();
        MedioPagoEntity medioPago = null;
        if (results == null) {
            medioPago = null;
        } else if (results.isEmpty()) {
            medioPago = null;
        } else if (results.size() >= 1) {
            medioPago = results.get(0);
        }
        return medioPago;
    }      
    
    public List<MedioPagoEntity> findAll(Long usuarioid) {
        TypedQuery<MedioPagoEntity> q = em.createQuery("select p from MedioPagoEntity p where p.miUsuario.id = :usuarioid", MedioPagoEntity.class);
        q.setParameter("usuarioid", usuarioid);
        List<MedioPagoEntity> results = q.getResultList();
        return results;
    }   

    
      
    
}

