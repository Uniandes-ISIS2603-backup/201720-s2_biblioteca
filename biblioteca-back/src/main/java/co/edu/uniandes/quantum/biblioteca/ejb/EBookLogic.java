package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.EBookPersistence;
import co.edu.uniandes.quantum.biblioteca.persistence.UsuarioPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EBookLogic {

    private static final Logger LOGGER = Logger.getLogger(EBookLogic.class.getName());

    @Inject
    private EBookPersistence persistence;

    public List<EBookEntity> getEBooks() {
        LOGGER.info("Inicia proceso de consultar todos los eBooks");
        List<EBookEntity> eBooks = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los eBooks");
        return eBooks;
    }

    public EBookEntity getEBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar eBook con id= " + id, id);
        EBookEntity eBook = persistence.find(id);
        if (eBook == null) {
            LOGGER.log(Level.SEVERE, "El eBook con el id= " + id + " no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar EBook con id=" + id, id);
        return eBook;
    }

    public EBookEntity createEBook(EBookEntity entity) throws BusinessLogicException {
        LOGGER.info("Verifica que no haya eBooks con el mismo nombre y autor.");
        List<EBookEntity> eBooks = persistence.findByName(entity.getName());
        if (eBooks != null) {
            for (EBookEntity e : eBooks) {
                if (e != null) {
                    if (e.getAutor().equals(entity.getAutor())) {
                        throw new BusinessLogicException("Ya existe un eBook con el mismo nombre y autor.");
                    }
                }
            }
        }
        LOGGER.info("Ya que no hay eBooks con el mismo nombre y autor, se inicia el proceso de creación del eBook");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del eBook");
        return entity;
    }

    public EBookEntity updateEBook(Long id, EBookEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar eBook con id={0}", id);

        EBookEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar eBook con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteEBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar eBook con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar eBook con id={0}", id);
    }


}
