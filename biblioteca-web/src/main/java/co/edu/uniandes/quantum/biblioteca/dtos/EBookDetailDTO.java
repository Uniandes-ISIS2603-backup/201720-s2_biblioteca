package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;

import java.util.ArrayList;
import java.util.List;

public class EBookDetailDTO extends EBookDTO{

    private List<EComentarioDTO> comentarios;

    public EBookDetailDTO(){
        super();
    }

    public EBookDetailDTO(EBookEntity entity){
        super(entity);
        if(entity.getComentarios()!=null){
            for(EComentarioEntity comm : entity.getComentarios())
                comentarios.add(new EComentarioDTO(comm));
        }
    }

    public EBookEntity toEntity() {
        EBookEntity entity = super.toEntity();
        if(comentarios!=null){
            List<EComentarioEntity> list = new ArrayList<>();
            for(EComentarioDTO comm : comentarios)
                list.add(comm.toEntity());
            entity.setComentarios(list);
        }

        return entity;
    }



    public List<EComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<EComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
}
