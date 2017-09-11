/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author da.leon
 */
public class ComentarioDTO {
    
    private Long id;
    private String comentario;
         
    @Temporal(TemporalType.DATE)
    private Date fechaPublicado;
    
    
    public ComentarioDTO (ComentarioEntity rec)
    {
         this.id= rec.getId();
         this.comentario = rec.getComentario();
         this.fechaPublicado = rec.getFechaPublicado();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getComentario(){
        return comentario;
        
    }
    
    public void setComentario( String comentario){
        this.comentario = comentario;
    }
    
    public void setFechaPublicado(Date fecha){
        this.fechaPublicado = fecha;
    }
    
    public Date getFechaPublicado ()
    {
        return fechaPublicado;
    }
    
}
