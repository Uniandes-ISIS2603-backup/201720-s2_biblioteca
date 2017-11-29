/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;


import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.EBookPersistence;
import co.edu.uniandes.quantum.biblioteca.persistence.EComentarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author de.agudelo
 */
@Stateless
public class EComentarioLogic {

    private static final Logger LOGGER = Logger.getLogger(EComentarioLogic.class.getName());

    @Inject
    private EComentarioPersistence persistence;

    @Inject
    private EBookPersistence eBookPersistence;


    /**
     * Devuelve los EComentario que se encuentran en la base de datos.
     *
     * @return los EComentarios como una lista de objetos.
     * Corresponde a la lógica de GET EComentarios
     */
    public List<EComentarioEntity> getEComentarios() {
        LOGGER.info("Inicia proceso de consultar todos los EComentarios");
        List<EComentarioEntity> EComentario = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los EComentarios");
        return EComentario;
    }

    /**
     * Devuelve los comentarios asociados a un eBook.
     * @param idEbook
     * @return
     * @throws BusinessLogicException
     */
    public List<EComentarioEntity> getEComentariosEBook(Long idEbook)throws BusinessLogicException{
        LOGGER.info("Se verifica que el eBook con el id " + idEbook + "dado existe.");
        if(eBookPersistence.find(idEbook)==null)
            throw new BusinessLogicException("No existe un eBook con el id dado.");

        LOGGER.info("El eBook deseado existe.");
        LOGGER.info("Inicia proceso de consultar todos los EComentarios");
        List<EComentarioEntity> EComentario = persistence.findCommentsEBook(idEbook);
        LOGGER.info("Termina proceso de consultar todos los EComentarios");
        return EComentario;
    }

    /**
     * Devuelve el EComentario que se encuentran en la base de datos con el id dado.
     *
     * @param id del EComentario a buscar en la DB.
     * @return el EComentario como un objeto Entity.
     * Corresponde a la lógica de GET EComentarios/{id}
     */
    public EComentarioEntity getEComentario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar coomentario con id={0}", id);
        EComentarioEntity EComentario = persistence.find(id);
        if (EComentario == null) {
            LOGGER.log(Level.SEVERE, "El EComentario con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar EComentario con id={0}", id);
        return EComentario;
    }

    /**
     * Devuelve el EComentario que se hizo persistir en la base de datos.
     *
     * @param entity EComentario a persistir
     * @return el EComentario como un objeto Entity.
     * Corresponde a la lógica de POST/EComentario
     */
    public EComentarioEntity crearEComentario(EComentarioEntity entity, Long idEBook) throws BusinessLogicException {
        LOGGER.info("Se verifica que el eBook con el id dado exista");

        EBookEntity eBook = eBookPersistence.find(idEBook);
        if (eBook!= null) {
            LOGGER.info("El eBook existe de creación de EComentario");
            LOGGER.info("El eBook existe de creación de EComentario");
            
            if(persistence.find(entity.getId())!=null)
                throw new BusinessLogicException("Ya existe un comentario con el id " + entity.getId());
            entity.setRecurso(eBook);
            persistence.create(entity);
            LOGGER.info("Termina proceso de creación de EComentario");
            return entity;
        }
        else throw new BusinessLogicException("No existe el eBook que se quiere comentar");
    }

    /**
     * Método privado desde el cual se verifica que un id sea valido para un EComentario.
     *
     * @param id a verificar.
     * @return true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id) {
        return !(id == null || id == 0);
    }

    /**
     * Devuelve el EComentario que se actualizo en la base de datos.
     *
     * @param id     del EComentario a actualizar.
     * @param entity EComentario a actualizar
     * @return el EComentario como un objeto Entity.
     * Corresponde a la lógica de PUT EComentario/{id}
     */
    public EComentarioEntity updateEComentario(Long id, EComentarioEntity entity, Long idEBook) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar EComentario con id={0}", id);
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El (id) es inválido");
        }
        EBookEntity eBook = eBookPersistence.find(idEBook);
        if (eBook!= null) {
            LOGGER.info("El eBook existe de creación de EComentario");
            
            if(persistence.find(entity.getId())==null)
                throw new BusinessLogicException("No existe un comentario con el id " + entity.getId());
            entity.setRecurso(eBook);
            EComentarioEntity newEntity = persistence.update(entity);
            LOGGER.log(Level.INFO, "Termina proceso de actualizar EComentario con id={0}", entity.getId());
            return newEntity;
        }
        else throw new BusinessLogicException("No existe el eBook que se quiere comentar");
    }

    /**
     * Devuelve el EComentario que se borrará de la base de datos.
     *
     * @param id del EComentario a borrar.
     *           Corresponde a la lógica de DELETE EComentario/{id}
     */
    public void deleteEComentario(Long id)throws BusinessLogicException {
        if(getEComentario(id)==null)
            throw new BusinessLogicException("No existe el comentario que se quiere eliminar");

        LOGGER.log(Level.INFO, "Inicia proceso de borrar EComentario con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar EComentario con id={0}", id);
    }


}