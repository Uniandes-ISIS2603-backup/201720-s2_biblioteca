package co.edu.uniandes.quantum.biblioteca.entities;

import uk.co.jemos.podam.common.PodamExclude;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Comentario anónimo sobre un eResource
 * @author de.agudelo
 */
@Entity
public class EComentarioEntity extends BaseEntity implements Serializable {

    /**
     * Fecha en la que se publicó el comentario.
     */
    private String fechaPublicado;

    /**
     * Titulo del eResource
     */
    @PodamExclude
    @ManyToOne
    private EBookEntity recurso;

    /**
     * comentario que deja el usuario en el blog
     */
    private String comentario;

    /**
     * calificacion del usuario en el comentario
     */
    private double calificacion;

    public String getFechaPublicado() {
        return fechaPublicado;
    }

    public void setFechaPublicado(String fechaPublicado) {
        this.fechaPublicado = fechaPublicado;
    }

    public EBookEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(EBookEntity recurso) {
        this.recurso = recurso;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}