package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
@Entity
public class EVideoEntity extends BaseEntity {
    Integer duracion;
    String autor, direccion;
}