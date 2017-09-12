package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;

public class EBookDTO {

    private Long id;
    private String name;
    private Integer numeroPaginas;
    private String autor, direccion;

    public EBookDTO(){

    }

    public EBookDTO(EBookEntity entity){

        setId(entity.getId());
        setName(entity.getName());
        setAutor(entity.getAutor());
        setDireccion(entity.getDireccion());
        setNumeroPaginas(entity.getNumeroPaginas());
    }


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



    public EBookEntity toEntity(){
        EBookEntity entity = new EBookEntity();

        entity.setId(getId());
        entity.setName(getName());
        entity.setAutor(getAutor());
        entity.setDireccion(getDireccion());
        entity.setNumeroPaginas(getNumeroPaginas());


        return entity;
    }
}
