/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author da.leon
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
  
     @ManyToOne
    private UsuarioEntity miUsuario;
    @Temporal(TemporalType.DATE)
    private Date fechaPublicado;
    
    /**
     * comentario que deja el usuario en el blog
     */
    private String comentario;
    
    /**
     * calificacion del usuario en el comentario
    */
    private double calificacion;
    
    /**
     * obtiene la fecha de publicacion
     * @return 
     */
    public Date getFechaPublicado(){
        return fechaPublicado;
    }
    
    /**
     * obtiene el comentario que dejo el usuario
     * @return 
     */
    public String getComentario(){
        return comentario;
    }
    /**
     * pone el comentario del usuario en el blog
     * @param nuevoComentario comentario que el usuario quiere dejar
     */
    public void setComentario( String nuevoComentario){
        
        comentario = nuevoComentario;
    }
    
    /**
     * pone la calificacion que el usuario pone en el comentario
     * @param nuevaCalificacion  calificacion que el usuario pone en el comentario
     */
    public void setCalificacion(double nuevaCalificacion)
    {
        calificacion = nuevaCalificacion;
    }
    
    /**
     * obtiene la calificacionq ue el usuario dejo en el comentario
     * @return calificacion que el usuario dejo en el comentario
     */
    public double getCalificacion()
    {
        return calificacion;
    }
    
    
}
