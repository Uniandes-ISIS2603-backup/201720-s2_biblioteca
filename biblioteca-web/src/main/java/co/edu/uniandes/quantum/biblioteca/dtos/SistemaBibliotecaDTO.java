package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.SistemaBibliotecaEntity;

public class SistemaBibliotecaDTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SistemaBibliotecaDTO(){
        //Se deja el constructor vacio ya que es necesario

    }
    public SistemaBibliotecaDTO(SistemaBibliotecaEntity entity){
    this.id = entity.getId();
    this.name = entity.getName();

    }

    public SistemaBibliotecaEntity toEntity(){
        SistemaBibliotecaEntity entity = new SistemaBibliotecaEntity();
        entity.setId(this.id);
        entity.setName(this.name);

        return entity;
    }

}
