package co.edu.uniandes.quantum.biblioteca.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class EVideoEntity extends BaseEntity implements Serializable {
    Integer duracion;
    String autor, direccion;
}