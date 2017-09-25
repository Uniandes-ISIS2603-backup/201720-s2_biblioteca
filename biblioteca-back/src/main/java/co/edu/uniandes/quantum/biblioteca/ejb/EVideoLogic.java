package co.edu.uniandes.quantum.biblioteca.ejb;
import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.EVideoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EVideoLogic {

    private static final Logger LOGGER = Logger.getLogger(EVideoLogic.class.getName());

    @Inject
    private EVideoPersistence persistence;

    public List<EVideoEntity> getEVideos() {
        LOGGER.info("Inicia proceso de consultar todos los eVideos");
        List<EVideoEntity> eVideos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los eVideos");
        return eVideos;
    }

    public EVideoEntity getEVideo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar eVideo con id= " + id , id);
        EVideoEntity eVideo = persistence.find(id);
        if (eVideo == null) {
            LOGGER.log(Level.SEVERE, "El eVideo con el id= " + id +" no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar eVideo con id=" + id , id);
        return eVideo;
    }

    public EVideoEntity createEVideo(EVideoEntity entity) throws BusinessLogicException {
        LOGGER.info("Verifica que no haya eVideos con el mismo nombre y autor.");
        List<EVideoEntity> eVideos = persistence.findByName(entity.getName());
        for (EVideoEntity e: eVideos){
            if(e!=null)
            {
                if (e.getAutor().equals(entity.getAutor()))
                {
                    throw new BusinessLogicException("Ya existe un eVideo con el mismo nombre y autor.");
                }
            }}
        LOGGER.info("Ya que no hay eVideos con el mismo nombre y autor, se inicia el proceso de creación del eVideo");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del eVideo");
        return entity;
    }

    public EVideoEntity updateEVideo(Long id, EVideoEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar eVideo con id={0}", id);

        EVideoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar eVideo con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteEVideo(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar eVideo con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar eVideo con id={0}", id);
    }









}
