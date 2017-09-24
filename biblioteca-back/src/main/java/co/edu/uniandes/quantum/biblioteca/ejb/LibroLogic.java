/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.LibroPersistence;
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
public class LibroLogic 
{

 private static final Logger LOGGER = Logger.getLogger(LibroLogic.class.getName());   
 
 @Inject
    private LibroPersistence persistence;
 
 @Inject
    private BibliotecaLogic bibliotecaLogic;
 /**
  * Devuelve los libros que se encuentran en la base de datos.
  * @return  los libros como una lista de objetos.
  * Corresponde a la lógica de GET libros
  */
  public List<LibroEntity> getBooks() {
        LOGGER.info("Inicia proceso de consultar todos los libros");
        List<LibroEntity> books = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los libros");
        return books;
    }
  
  /**
  * Devuelve el libro que se encuentran en la base de datos con el id dado.
  * @param id del libro a buscar en la DB.
  * @return  el libro como un objeto Entity.
  * Corresponde a la lógica de GET libros/{id}
  */
// public LibroEntity getLibro(Long id) {
//        LOGGER.log(Level.INFO, "Inicia proceso de consultar libro con id={0}", id);
//        LibroEntity book = persistence.find(id);
//        if (book == null) 
//        {
//            LOGGER.log(Level.SEVERE, "El libro con el id {0} no existe", id);
//        }
//        LOGGER.log(Level.INFO, "Termina proceso de consultar libro con id={0}", id);
//        return book;
//    }
 
    /**
     * Obtiene la lista de los registros de Multa que pertenecen a un Usuario.
     *
     * @param usuarioid id del Usuario el cual es padre de los Multas.
     * @return Colección de objetos de MultaEntity.
     * @throws co.edu.uniandes.csw.usuariostore.exceptions.BusinessLogicException
     */
    public List<LibroEntity> getLibros(Long idBiblio) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todos los multas");
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(idBiblio);
        if (biblioteca.getLibros()== null) {
            throw new BusinessLogicException("El usuario que consulta aún no tiene libros");
        }

        return biblioteca.getLibros();
    }
    
    public LibroEntity getLibro (Long idBiblio, Long idlibro) throws BusinessLogicException
    {
        List<LibroEntity> librosBiblioteca = getLibros(idBiblio);
       LibroEntity libroR=null;
        for (LibroEntity libro : librosBiblioteca)
        {
           if(libro.getId()==idlibro)
           {
               libroR=libro;
           }
            
        }
        if(libroR==null)
        {
            throw new BusinessLogicException("el libro no existe");
        }
        return libroR;
    }
   
 /**
  * Devuelve el libro que se hizo persistir en la base de datos.
  * @param entity libro a persistir
  * @return  el libro como un objeto Entity.
  * Corresponde a la lógica de POST/libros
  */

    public LibroEntity crearLibro(LibroEntity entity)
    {
        LOGGER.info("Inicia proceso de creación de libro");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de libro");
        return entity;
    }
   public LibroEntity crearLibro(LibroEntity entity, Long idBiblioteca) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de libro");
        BibliotecaEntity biblioteca =bibliotecaLogic.getBiblioteca(idBiblioteca);
        entity.setMiBiblioteca(biblioteca);
       List<LibroEntity> libros = biblioteca.getLibros();
                               
              for(LibroEntity libro:libros)
              {
                  if(libro!=null)
                  {
                      if(libro.getAnioPublicacion()==entity.getAnioPublicacion()&& libro.getAutor().equals(entity.getAutor()))
                      {
                          throw new BusinessLogicException("Ya existe un usuario con el mismo nombre, telefono y dirección.");
                      }
                  }
              }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de libro");
        return entity;
    }
    /**
     * Método privado desde el cual se verifica que un id sea valido para un libro.
     * @param id a verificar.
     * @return  true si es valido, false en caso contrario.
     */
    private boolean validateId(Long id)
    {
        return !(id==null||id ==0);
    }
    
  /**
  * Devuelve el libro que se actualizo en la base de datos.
  * @param id ISBN del libro a actualizar.
  * @param entity libro a actualizar
  * @return  el libro como un objeto Entity.
  * Corresponde a la lógica de PUT libros/{id}
  */
      public LibroEntity updateBook(Long id, LibroEntity entity, Long idBiblioteca) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id={0}", id);
        entity.setMiBiblioteca(bibliotecaLogic.getBiblioteca(idBiblioteca));
        if (!validateId(entity.getId())) {
            throw new BusinessLogicException("El ISBN  (id) es inválido");
        }
        LibroEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar libro con id={0}", entity.getId());
        return newEntity;
    }

  /**
  * Devuelve el libro que se borrará de la base de datos.
  * @param id ISBN del libro a borrar.
  * Corresponde a la lógica de DELETE libros/{id}
  */
    public void deleteBook(Long id, Long idBiblioteca) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar libro con id={0}", id);
           LibroEntity old = getLibro(idBiblioteca, id);
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar libro con id={0}", id);
    }

}
