package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;

public class EComentarioDTO {

    private Long id;
    private String name;
    private String comentario;
    private String fechaPublicacion;
    private Double calificacion;


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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public EComentarioDTO(){

    }

    public EComentarioDTO(EComentarioEntity entity){
        if(entity==null)
            throw new NullPointerException("La entidad enviada fue nula");
        else{
            setId(entity.getId());
            setName(entity.getName());
            setComentario(entity.getComentario());
            setFechaPublicacion(entity.getFechaPublicado());
            setCalificacion(entity.getCalificacion());
        }
    }


    public EComentarioEntity toEntity(){
        EComentarioEntity entity = new EComentarioEntity();

        entity.setId(getId());
        entity.setName(getName());
        entity.setComentario(getComentario());
        entity.setCalificacion(getCalificacion());
        entity.setFechaPublicado(getFechaPublicacion());
        return entity;
    }

}
