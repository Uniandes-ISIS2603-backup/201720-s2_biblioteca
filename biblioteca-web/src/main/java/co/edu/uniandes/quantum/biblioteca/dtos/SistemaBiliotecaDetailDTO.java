package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.SistemaBibliotecaEntity;

import java.util.ArrayList;
import java.util.List;

public class SistemaBiliotecaDetailDTO extends SistemaBibliotecaDTO {

    private List<BibliotecaDTO> bibliotecas;
    private List<EBookDTO> eBooks;
    private List<EVideoDTO> eVideo;

    public List<BibliotecaDTO> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(List<BibliotecaDTO> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public List<EBookDTO> geteBooks() {
        return eBooks;
    }

    public void seteBooks(List<EBookDTO> eBooks) {
        this.eBooks = eBooks;
    }

    public List<EVideoDTO> geteVideo() {
        return eVideo;
    }

    public void seteVideo(List<EVideoDTO> eVideo) {
        this.eVideo = eVideo;
    }

    public SistemaBiliotecaDetailDTO() {

    }

    public SistemaBiliotecaDetailDTO(SistemaBibliotecaEntity entity) {
        super(entity);
        if (entity.getBibliotecas() != null) {
            for (BibliotecaEntity b : entity.getBibliotecas()) {
                bibliotecas.add(new BibliotecaDTO(b));
            }
        }
        if (entity.geteBooks() != null) {
            for (EBookEntity e :
                    entity.geteBooks()) {
                eBooks.add(new EBookDTO(e));

            }
        }
        if(entity.geteVideo() != null){
            for (EVideoEntity e :
                    entity.geteVideo()) {
                eVideo.add(new EVideoDTO(e));

            }
        }

    }
    public SistemaBibliotecaEntity toEntity(){
        SistemaBibliotecaEntity entity = super.toEntity();
        if (getBibliotecas()!=null) {
            List<BibliotecaEntity> list = new ArrayList<>();
            for (BibliotecaDTO b :
                    getBibliotecas()) {
                list.add(b.toEntity());
            }
            entity.setBibliotecas(list);
        }
        if(geteBooks() != null){
            List<EBookEntity> list = new ArrayList<>();
            for (EBookDTO e :
                    geteBooks()) {
                list.add(e.toEntity());
            }
            entity.seteBooks(list);
        }
        if (geteVideo()!= null){
            List<EVideoEntity> list = new ArrayList<>();
            for (EVideoDTO e :
                    geteVideo()) {
                list.add(e.toEntity());
            }
            entity.seteVideo(list);
        }
        return entity;


    }


}
