package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
@Entity
public class EBookEntity extends BaseEntity{
    private Integer numeroPaginas;
    private String autor, direccion;


}