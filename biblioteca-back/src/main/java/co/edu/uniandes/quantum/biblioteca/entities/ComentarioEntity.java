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
    
    private String comentario;
    
    public Date getFechaPublicado(){
        return fechaPublicado;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public void setComentario( String nuevoComentario){
        
        comentario = nuevoComentario;
    }
    
    
    
}
