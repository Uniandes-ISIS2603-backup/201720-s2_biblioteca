package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class EBookEntity extends BaseEntity implements Serializable{
    private Integer numeroPaginas;
    private String autor, direccion;


}