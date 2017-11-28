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

import javax.persistence.Entity;

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
    
    private static final String MENSAJECOMPLETADA ="Tu reserva ya está lista para ser reclamada en nuestra biblioteca.";
    private static final String MENSAJENOCOMPLETADA ="Animo, tu reserva podría estar lista en unos momentos.";
     private static final String IMAGENNOCOMPLETADA ="https://upload.wikimedia.org/wikipedia/en/a/a4/Red_x_large.png";
      private static final String  IMAGENCOMPLETADA ="https://ayuda.tigoune.co/hc/article_attachments/115011237588/Chulo.png";
      
    private boolean completada;
    private String imagen;
    private String mensaje;
     
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    public String getImagen() {
        if(completada){ imagen =IMAGENCOMPLETADA;} 
        else imagen = IMAGENNOCOMPLETADA;
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMensaje() {
       if(completada) mensaje= MENSAJECOMPLETADA;
        else mensaje =MENSAJENOCOMPLETADA;
       return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    
    @PodamExclude
   @OneToMany(mappedBy = "miReserva")
   private List<VideoEntity> videos = new ArrayList<VideoEntity>();
   
         @PodamExclude
       @OneToMany(mappedBy = "miReserva")
   private List<LibroEntity> libros = new ArrayList<LibroEntity>();
   @PodamExclude
   @ManyToOne
   private UsuarioEntity miUsuario;
  
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
