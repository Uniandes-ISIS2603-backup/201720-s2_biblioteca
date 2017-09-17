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
    
    /**
     * id identificador del comentario
     */
    private Long id;
    
    /**
     * comentario que deja el usuario
     */
    private String comentario;
    
    /**
     * calificacion que deja el usuario
     */
    private double calificacion;
         
    /**
     * fecha en la que se hizo el comentario
     */
    @Temporal(TemporalType.DATE)
    private Date fechaPublicado;
    
    
    public ComentarioDTO (ComentarioEntity rec)
    {
         this.id= rec.getId();
         this.comentario = rec.getComentario();
         this.fechaPublicado = rec.getFechaPublicado();
         this.calificacion = rec.getCalificacion();
    }
    
    /**
     * obtiene el id
     * @return id identificador del comentario
     */
    public Long getId() {
        return id;
    }

    /**
     * cambia el id del comentario
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * obtiene el comentario que dejó un usuario 
     * @return comentario
     */
    public String getComentario(){
        return comentario;
        
    }
    
    /**
     * pone un comentario en un blog
     * @param comentario 
     */
    public void setComentario( String comentario){
        this.comentario = comentario;
    }
    
    /**
     * pone la fecha de publicacion del comentario
     * @param fecha 
     */
    public void setFechaPublicado(Date fecha){
        this.fechaPublicado = fecha;
    }
    
    /**
     * obtiene la fecha de publicacion del comentario
     * @return fecha de publicacion del comentario
     */
    public Date getFechaPublicado ()
    {
        return fechaPublicado;
    }
    
    /**
     * obtiene la calificacion puesta en un comentario
     * @return  la calificacion de tipo double que dejo un usuario en un comentario
     */
    public double getCalificacion()
    {
        return calificacion;
    }
    
    /**
     * cambia la calificacion del comentario
     * @param nuevaCalificacion la nueva calificacion que se pone en el comentario
     */
    public void setCalificacion ( double nuevaCalificacion)
    {
        this.calificacion = nuevaCalificacion;
    }
    
     public ComentarioEntity toEntity()
    {
        ComentarioEntity entity = new ComentarioEntity();
        entity.setId(this.id);
        entity.setComentario(this.comentario);
        entity.setCalificacion(this.calificacion);
        return entity;
    }
}
