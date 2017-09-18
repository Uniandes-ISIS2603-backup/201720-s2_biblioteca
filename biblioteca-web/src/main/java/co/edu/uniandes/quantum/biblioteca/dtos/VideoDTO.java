/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;

/**
 *
 * @author cg.chavarro
 */
public class VideoDTO 
{
 
    private Long id; 
    private String name;
    private int unidadesExistentes;
    private String autor;
    private int uniandesDisponibles;
    private int duracion;
    
    public VideoDTO(VideoEntity l)
    {
        this.id=l.getId();
        this.name =l.getName();
        this.unidadesExistentes=l.getUnidadesExistentes();
        this.uniandesDisponibles=l.getUnidadesDisponibles();
     this.duracion=l.getDuracion();
        this.autor=l.getAutor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnidadesExistentes() {
        return unidadesExistentes;
    }

    public void setUnidadesExistentes(int unidadesExistentes) {
        this.unidadesExistentes = unidadesExistentes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getUniandesDisponibles() {
        return uniandesDisponibles;
    }

    public void setUniandesDisponibles(int uniandesDisponibles) {
        this.uniandesDisponibles = uniandesDisponibles;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    public VideoEntity toEntity() 
    {
        VideoEntity ent=new VideoEntity();
        ent.setId(this.id);
        ent.setAutor(this.autor);
        ent.setDuracion(duracion);
          ent.setName(name);
        ent.setUnidadesDisponibles(uniandesDisponibles);
        ent.setUnidadesExistentes(uniandesDisponibles);
        return ent;
        
    }
}
