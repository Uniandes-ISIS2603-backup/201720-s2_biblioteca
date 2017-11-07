package co.edu.uniandes.quantum.biblioteca.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EBookEntity extends BaseEntity implements Serializable{
    private Integer numeroPaginas;
    private String autor;
    private String direccion;

    @PodamExclude
    @OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EComentarioEntity> comentarios = new ArrayList<>();

    public List<EComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<EComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}