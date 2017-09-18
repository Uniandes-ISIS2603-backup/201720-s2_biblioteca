/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author f.posada, cg.chavarro
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable
{
    
   
    private boolean completada;
     
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    
    
    
    @PodamExclude
   @OneToMany(mappedBy = "mReserva", cascade = CascadeType.ALL)
   private List<VideoEntity> videos = new ArrayList<VideoEntity>();
   
       @OneToMany(mappedBy = "miReserva", cascade = CascadeType.ALL)
   private List<LibroEntity> libros = new ArrayList<LibroEntity>();
   @PodamExclude
   @ManyToOne
   private UsuarioEntity miUsuario;
   // public List<RecursoEntity> getRecursos() {
   //     return recursos;
   // }

  //  public void setRecursos(List<RecursoEntity> recursos) {
   //     this.recursos = recursos;
    //}
     
    public boolean isCompletada()
    {return completada;}
    public void setCompletada(boolean pComplete)
    {completada=pComplete;}

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

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
    
}
