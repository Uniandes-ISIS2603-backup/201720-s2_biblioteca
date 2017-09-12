package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class EVideoEntity extends BaseEntity implements Serializable {
    Integer duracion;
    String autor, direccion;

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
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