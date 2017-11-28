package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;

public class EComentarioDetailDTO extends EComentarioDTO {
    private EBookDTO recurso;

    public EComentarioDetailDTO(){
        super();
    }

    public EComentarioDetailDTO(EComentarioEntity entity){
        super(entity);
        if(entity.getRecurso()!=null)
            setRecurso( new EBookDTO(entity.getRecurso()));

    }


    public EComentarioEntity toEntity() {
        EComentarioEntity entity = super.toEntity();

        if(recurso!=null)entity.setRecurso(recurso.toEntity());

        return entity;
    }

    public EBookDTO getRecurso() {
        return recurso;
    }

    public void setRecurso(EBookDTO recurso) {
        this.recurso = recurso;
    }
}
