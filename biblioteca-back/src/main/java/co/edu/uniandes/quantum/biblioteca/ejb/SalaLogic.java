/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.SalaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cg.chavarro
 */
@Stateless
public class SalaLogic 
{
private static final Logger LOGGER = Logger.getLogger(SalaLogic.class.getName());   
 
 @Inject
    private SalaPersistence persistence;
 
 @Inject
    private PrestamoLogic prestamoLogic;
 
 @Inject
    private BibliotecaLogic BibliotecaLogic;
 
 
/**
     * Obtiene la lista de los registros de Salas que pertenecen a una Biblioteca.
     *
     * @param idBiblioteca id de la Biblioteca la cual es "padre" de las Salas.
     * @return Colección de objetos de SalaEntity.
     * @throws BusinessLogicException
     */
  public List<SalaEntity> getSalas(Long idBiblioteca) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todos los Salas");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        List<SalaEntity>  salas = biblioteca.getSalas();
        if (biblioteca.getSalas() == null || biblioteca.getSalas().isEmpty()) {
          throw new BusinessLogicException("La biblioteca no tiene salas asociadas.");
      }
        LOGGER.info("Termina proceso de consultar todos los Salas");
        return salas;
    }
  
  public List<SalaEntity> getSalasPrestamo(Long idPrestamo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar los salas del prestamo con id " + idPrestamo);
        List<SalaEntity> l=persistence.findByPrestamo(idPrestamo);
        if (l.isEmpty()) 
        {
            throw new BusinessLogicException("El prestamo que consulta aún no tiene salas");
        }
        else
            return l;
    }
  
  public List<SalaEntity> getSalasDisponibles() throws BusinessLogicException {
        List<SalaEntity> l=persistence.findAll();
        List<SalaEntity> res=new ArrayList();
        if (l.isEmpty()) 
        {
            throw new BusinessLogicException("No hay salas");
        }
        else
        {
            for(SalaEntity sa:l)
            {
                if(sa.getMiPrestamo()==null)
                    res.add(sa);
            }
        }
        if(res.isEmpty())
            throw new BusinessLogicException("No hay salas disponibles");
        else
            return res;
            
    }
  
  public void devolverSala(SalaEntity entity) throws BusinessLogicException
    {
        SalaEntity ent=persistence.find(entity.getId());
        ent.setMiPrestamo(null);
        persistence.update(ent);
    }
  
  public SalaEntity getSala(Long id) throws BusinessLogicException
  {
      SalaEntity e=persistence.find(id);
      if(e==null)
          throw new BusinessLogicException("La sala no existe");
      else
          return e;
  }
  
  

  /**
  * Devuelve el Sala que se encuentran en la base de datos con el id dado.
  * @param idBiblioteca de la Biblioteca a buscar en la DB.
  * @param idSala de la Sala a buscar en la DB.
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de GET Salas/{id}
  */
 public SalaEntity getSala(Long idBiblioteca, Long idSala) throws BusinessLogicException{
    List<SalaEntity> salasBiblioteca = getSalas(idBiblioteca);
       SalaEntity salaR=null;
        for (SalaEntity sala : salasBiblioteca)
        {
           if(sala.getId().equals(idSala))
           {
               salaR=sala;
           }
            
        }
        if(salaR==null)
        {
            throw new BusinessLogicException("la sala no existe");
        }
        return salaR;
    }
 
 /**
  * Devuelve el Sala que se hizo persistir en la base de datos.
  * @param entity Sala a persistir
  * @param idBiblioteca id de la Biblioteca donde se creará la sala.
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de POST/Salas
  */
   public SalaEntity crearSala(Long idBiblioteca, SalaEntity entity) {
        LOGGER.info("Inicia proceso de creación de Sala");
        BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
        LOGGER.info("Termina proceso de creación de Sala");
        return persistence.create(entity);
    }
   
   public SalaEntity colocarSalaPrestamo(SalaEntity entity, Long idPrestamo) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de agregar libro al prestamo");
        SalaEntity ent= persistence.find(entity.getId());
        PrestamoEntity p=prestamoLogic.getPrestamo(idPrestamo);
        ent.setMiPrestamo(p);
        persistence.update(ent);
        LOGGER.info("Termina proceso de colocar libro en prestamo");
        return entity;
    }
    
  /**
  * Devuelve el Sala que se actualizo en la base de datos.
  * @param idBiblioteca  de la Biblioteca a actualizarle su sala.
  * @param entity Sala a actualizar
  * @return  el Sala como un objeto Entity.
  * Corresponde a la lógica de PUT Salas/{id}
  */
      public SalaEntity updateSala(Long idBiblioteca, SalaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar Sala");
          BibliotecaEntity biblioteca = BibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar Sala con id={0}", entity.getId());
        return persistence.update(entity);
    }

  /**
  * Devuelve el Sala que se borrará de la base de datos.
  * @param idBiblioteca de donde se borrará la Sala.
  * @param idSala  del Sala a borrar.
  * Corresponde a la lógica de DELETE Salas/{id}
  */
    public void deleteSalas(Long idBiblioteca, Long idSala) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Sala con id={0}", idSala);
        try {
                    SalaEntity old = getSala(idBiblioteca, idSala);
                    persistence.delete(old.getId());
        } catch (BusinessLogicException e) {
        }
        LOGGER.log(Level.INFO, "Termina proceso de borrar Sala con id={0}", idSala);
    }
}

