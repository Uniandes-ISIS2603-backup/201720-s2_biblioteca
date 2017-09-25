package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class EBookEntity extends BaseEntity implements Serializable{
    private Integer numeroPaginas;
    private String autor;
    private String direccion;

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