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
package co.edu.uniandes.quantum.biblioteca.dtos;
import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISIS2603
 */
public class BibliotecaDetailDTO extends BibliotecaDTO {

    private List<SalaDTO> salas;
    
    private List<VideoDTO> videos;
    
    private List<LibroDTO> libros;    
    
    /**
     * Constructor por defecto
     */
    public BibliotecaDetailDTO(BibliotecaEntity entity) {
        super(entity);
        if (entity.getSalas() != null) {
            salas = new ArrayList<>();
            for (SalaEntity entitySala : entity.getSalas()) {
                salas.add(new SalaDTO(entitySala));
            }
        }
        
        if (entity.getVideos() != null) {
            videos = new ArrayList<>();
            for (VideoEntity entityVideo : entity.getVideos()) {
                videos.add(new VideoDTO(entityVideo));
            }
        }
        
        if (entity.getLibros() != null) {
            libros = new ArrayList<>();
            for (LibroEntity entityLibro : entity.getLibros()) {
                libros.add(new LibroDTO(entityLibro));
            }
        }
    }

    public List<SalaDTO> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDTO> salas) {
        this.salas = salas;
    }

    public List<VideoDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDTO> video) {
        this.videos = video;
    }

    public List<LibroDTO> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroDTO> libro) {
        this.libros = libro;
    }
    
    

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public BibliotecaEntity toEntity() {
        BibliotecaEntity biblioteca = super.toEntity();
        if (getLibros() != null) {
            List<LibroEntity> librosEntity = new ArrayList<>();
            for (LibroDTO dtoLibro : getLibros()) {
                librosEntity.add(dtoLibro.toEntity());
            }
            biblioteca.setLibros(librosEntity);
        }
        
        if (getVideos() != null) {
            List<VideoEntity> videosEntity = new ArrayList<>();
            for (VideoDTO dtoVideo : getVideos()) {
                videosEntity.add(dtoVideo.toEntity());
            }
            biblioteca.setVideos(videosEntity);
        }
        if (getSalas() != null) {
            List<SalaEntity> salasEntity = new ArrayList<>();
            for (SalaDTO dtoSala : getSalas()) {
                salasEntity.add(dtoSala.toEntity());
            }
            biblioteca.setSalas(salasEntity);
        }
        return biblioteca;
    }

}
