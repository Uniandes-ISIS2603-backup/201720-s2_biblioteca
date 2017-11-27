/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author f.posada
 */
@XmlRootElement
public class PrestamoDetailDTO extends PrestamoDTO 
{
    private List<LibroDTO> libros;
    private List<VideoDTO> videos;
    private List<SalaDTO> salas;

    
    
    public PrestamoDetailDTO ()
    {
        super();
    }
    
    public PrestamoDetailDTO(PrestamoEntity entity) {
        super(entity);
        if (entity != null) {
            videos = new ArrayList<>();
            for (VideoEntity entityVideo : entity.getVideos()) {
                videos.add(new VideoDTO(entityVideo));
            }

            libros = new ArrayList<>();
            for (LibroEntity entityLibro : entity.getLibros()) {
                libros.add(new LibroDTO(entityLibro));
            }
            salas = new ArrayList<>();
            for (SalaEntity entitySala : entity.getSalas()) {
                salas.add(new SalaDTO(entitySala));
            }

        }
    }

    public List<LibroDTO> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroDTO> libros) {
        this.libros = libros;
    }

    public List<VideoDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDTO> videos) {
        this.videos = videos;
    }

    public List<SalaDTO> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDTO> salas) {
        this.salas = salas;
    }
    
    
    
    @Override
    public PrestamoEntity toEntity()
    {       
        PrestamoEntity preEnt=super.toEntity();

        if (getVideos() != null) {
            List<VideoEntity> multasEntity = new ArrayList<>();
            for (VideoDTO dtoVideo : getVideos()) {
                multasEntity.add(dtoVideo.toEntity());
            }
            preEnt.setVideos(multasEntity);
        }
        
        if (getLibros() != null) {
            List<LibroEntity> multasEntity = new ArrayList<>();
            for (LibroDTO dtoLibro : getLibros()) {
                multasEntity.add(dtoLibro.toEntity());
            }
            preEnt.setLibros(multasEntity);
        }
        if (getSalas() != null) {
            List<SalaEntity> multasEntity = new ArrayList<>();
            for (SalaDTO dtoSala : getSalas()) {
                multasEntity.add(dtoSala.toEntity());
            }
            preEnt.setSalas(multasEntity);
        }

        return preEnt;   
        
    }
    
}
