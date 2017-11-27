/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author f.posada
 */
@Entity
public class PrestamoEntity extends BaseEntity implements Serializable {
    
   
    private String fechaInicio;
    private String fechaFinal;
    
    private boolean generomulta;
    private boolean retornado;
    private boolean externo;
    
    @PodamExclude
   @OneToMany(mappedBy = "miPrestamo")
   private List<SalaEntity> salas = new ArrayList<>();    
    
    @PodamExclude
   @OneToMany(mappedBy = "miPrestamo")
   private List<VideoEntity> videos = new ArrayList<>();
    
     @PodamExclude
   @OneToMany(mappedBy = "miPrestamo")
   private List<LibroEntity> libros = new ArrayList<>();
   
    @PodamExclude
   @ManyToOne
   private UsuarioEntity miUsuario;

    public List<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoEntity> videos) {
        this.videos = videos;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }

    

    public UsuarioEntity getMiUsuario() {
        return miUsuario;
    }

    public void setMiUsuario(UsuarioEntity miUsuario) {
        this.miUsuario = miUsuario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }    

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isGeneromulta() {
        return generomulta;
    }

    public void setGeneromulta(boolean generomulta) {
        this.generomulta = generomulta;
    }

    public boolean isRetornado() {
        return retornado;
    }

    public void setRetornado(boolean retornado) {
        this.retornado = retornado;
    }

    public boolean isExterno() {
        return externo;
    }

    public void setExterno(boolean externo) {
        this.externo = externo;
    }

    public List<SalaEntity> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaEntity> salas) {
        this.salas = salas;
    }
    
    
    
    
}
