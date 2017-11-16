/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.BibliotecaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603, jp.sanmiguel
 */
@Stateless
public class BibliotecaLogic {

    private static final Logger LOGGER = Logger.getLogger(BibliotecaLogic.class.getName());

    @Inject
    private BibliotecaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public BibliotecaEntity createBiblioteca(BibliotecaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Biblioteca");  
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Biblioteca");
        return entity;
    }
    
    public void deleteBiblioteca(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar la Biblioteca con el id: {0}", id);  
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina el proceso de eliminar la Biblioteca con el id: {0}", id);  
    }
    
    public BibliotecaEntity getBiblioteca(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de buscar la Biblioteca con el id: {0}", id);  
        BibliotecaEntity entity = persistence.find(id);
        LOGGER.log(Level.INFO, "Termina el proceso de buscar la Biblioteca con el id: {0}", id);  
        return entity;
    }
    
    public BibliotecaEntity updateBiblioteca(BibliotecaEntity entity)
    {
        LOGGER.info("Inicia el proceso de actualizar una Biblioteca");  
        BibliotecaEntity entityActualizado;
        entityActualizado = persistence.update(entity);
        LOGGER.info("Termina el proceso de actualizar una Biblioteca");  
        return entityActualizado;
    }

    /**
     * 
     * Obtener todas las Bibliotecaes existentes en la base de datos.
     *
     * @return una lista de Bibliotecaes.
     */
    public List<BibliotecaEntity> getBibliotecas() {
        LOGGER.info("Inicia proceso de consultar todas las Bibliotecaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BibliotecaEntity> Bibliotecas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Bibliotecaes");
        return Bibliotecas;
    }
    
    public List<LibroEntity> getLibros(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros de la biblioteca {0}", id);
        List<LibroEntity> libros = getBiblioteca(id).getLibros();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los libros de la biblioteca {0}", id);
        return libros;
    }
    
    public LibroEntity getLibroBibliotecca(Long idBiblioteca, Long idLibro)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro {0} de la biblioteca {1}", new Object[]{idLibro, idBiblioteca});
        List<LibroEntity> libros = getBiblioteca(idBiblioteca).getLibros();
        for(LibroEntity libro:libros)
        {
            if(libro.getId() == idLibro)
            {
                return libro;
            }
        }
        return null;
    }
    
    public LibroEntity postLibro(Long bibliotecaId, Long libroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un libro a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity biblioteca = getBiblioteca(bibliotecaId);
        LibroEntity libro = new LibroEntity();
        libro.setId(libroId);
        biblioteca.addLibro(libro);
        LOGGER.log(Level.INFO, "Termina el proceso de asociar un libro a la biblioteca con id = {0}", bibliotecaId);
        persistence.update(biblioteca);
        return getLibroBibliotecca(bibliotecaId, libroId);
    }
    
    public BibliotecaEntity updateLibros(Long idBiblioteca, List<LibroEntity> libros)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la lista de libros a la biblioteca con id = {0}", idBiblioteca);
        BibliotecaEntity biblioteca = getBiblioteca(idBiblioteca);
        biblioteca.setLibros(libros);
        persistence.update(biblioteca);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la lista de libros a la biblioteca con id = {0}", idBiblioteca);
        return biblioteca;
    }
    
    public void deleteLibro(Long idBiblioteca, Long idLibro)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminar el libro {0} de la biblioteca {1}", new Object[]{idLibro, idBiblioteca});
        LibroEntity libro = getLibroBibliotecca(idBiblioteca, idLibro);
        BibliotecaEntity entity = getBiblioteca(idBiblioteca);
        entity.removeLibro(libro); 
        persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de eliminar el libro {0} de la biblioteca {1}", new Object[]{idLibro, idBiblioteca});
    }

    public List<VideoEntity> getVideos(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los videos de la biblioteca {0}", id);
        List<VideoEntity> videos = getBiblioteca(id).getVideos();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los videos de la biblioteca {0}", id);
        return videos;
    }
    
    public VideoEntity getVideoBibliotecca(Long idBiblioteca, Long idVideo)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el video {0} de la biblioteca {1}", new Object[]{idVideo, idBiblioteca});
        List<VideoEntity> videos = getBiblioteca(idBiblioteca).getVideos();
        for(VideoEntity video:videos)
        {
            if(video.getId() == idVideo)
            {
                return video;
            }
        }
        return null;
    }
    
    public VideoEntity postVideo(Long bibliotecaId, Long videoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un video a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity biblioteca = getBiblioteca(bibliotecaId);
        VideoEntity video = new VideoEntity();
        video.setId(videoId);
        biblioteca.addVideo(video);
        LOGGER.log(Level.INFO, "Termina el proceso de asociar un video a la biblioteca con id = {0}", bibliotecaId);
        persistence.update(biblioteca);
        return getVideoBibliotecca(bibliotecaId, videoId);
    }
    
    public BibliotecaEntity updateVideos(Long idBiblioteca, List<VideoEntity> videos)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la lista de videos a la biblioteca con id = {0}", idBiblioteca);
        BibliotecaEntity biblioteca = getBiblioteca(idBiblioteca);
        biblioteca.setVideos(videos);
        persistence.update(biblioteca);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la lista de videos a la biblioteca con id = {0}", idBiblioteca);
        return biblioteca;
    }
    
    public void deleteVideo(Long idBiblioteca, Long idVideo)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminar el video {0} de la biblioteca {1}", new Object[]{idVideo, idBiblioteca});
        VideoEntity video = getVideoBibliotecca(idBiblioteca, idVideo);
        BibliotecaEntity entity = getBiblioteca(idBiblioteca);
        entity.removeVideo(video); 
        persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de eliminar el video {0} de la biblioteca {1}", new Object[]{idVideo, idBiblioteca});
    }
    
    public List<SalaEntity> getSalas(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los salas de la biblioteca {0}", id);
        List<SalaEntity> salas = getBiblioteca(id).getSalas();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los salas de la biblioteca {0}", id);
        return salas;
    }
    
    public SalaEntity getSalaBibliotecca(Long idBiblioteca, Long idSala)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el sala {0} de la biblioteca {1}", new Object[]{idSala, idBiblioteca});
        List<SalaEntity> salas = getBiblioteca(idBiblioteca).getSalas();
        for(SalaEntity sala:salas)
        {
            if(sala.getId() == idSala)
            {
                return sala;
            }
        }
        return null;
    }
    
    public SalaEntity postSala(Long bibliotecaId, Long salaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un sala a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity biblioteca = getBiblioteca(bibliotecaId);
        SalaEntity sala = new SalaEntity();
        sala.setId(salaId);
        biblioteca.addSala(sala);
        LOGGER.log(Level.INFO, "Termina el proceso de asociar un sala a la biblioteca con id = {0}", bibliotecaId);
        persistence.update(biblioteca);
        return getSalaBibliotecca(bibliotecaId, salaId);
    }
    
    public BibliotecaEntity updateSalas(Long idBiblioteca, List<SalaEntity> salas)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la lista de salas a la biblioteca con id = {0}", idBiblioteca);
        BibliotecaEntity biblioteca = getBiblioteca(idBiblioteca);
        biblioteca.setSalas(salas);
        persistence.update(biblioteca);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la lista de salas a la biblioteca con id = {0}", idBiblioteca);
        return biblioteca;
    }
    
    public void deleteSala(Long idBiblioteca, Long idSala)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminar el sala {0} de la biblioteca {1}", new Object[]{idSala, idBiblioteca});
        SalaEntity sala = getSalaBibliotecca(idBiblioteca, idSala);
        BibliotecaEntity entity = getBiblioteca(idBiblioteca);
        entity.removeSala(sala); 
        persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de eliminar el sala {0} de la biblioteca {1}", new Object[]{idSala, idBiblioteca});
    }
}
