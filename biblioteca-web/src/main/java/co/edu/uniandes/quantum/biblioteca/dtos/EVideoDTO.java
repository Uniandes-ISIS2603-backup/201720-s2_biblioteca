package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;

public class EVideoDTO {

    private Long id;
    private String name;
    private Integer duracion;

    private String autor;
    private String direccion;

    private String imagen;

    public EVideoDTO() {
//Se deja el constructor vacio ya que es necesario
    }

    public EVideoDTO(EVideoEntity entity) {

        if(entity!=null){
        setId(entity.getId());
        setName(entity.getName());
        setAutor(entity.getAutor());
        setDireccion(entity.getDireccion());
        setDuracion(entity.getDuracion());
        setImagen(entity.getImagen());}
        else
            throw new NullPointerException("El Evideo fue nula");
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public EVideoEntity toEntity() {
        EVideoEntity entity = new EVideoEntity();

        entity.setId(getId());
        entity.setName(getName());
        entity.setAutor(getAutor());
        entity.setDireccion(getDireccion());
        entity.setDuracion(getDuracion());
        entity.setImagen(getImagen());

        return entity;
    }

}
