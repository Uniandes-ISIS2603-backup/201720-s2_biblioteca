package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.List;

public class SistemaBilbiotecaEntity extends BaseEntity implements Serializable {

    private String nombre;
    private List<BibliotecaEntity> bibliotecas;
    private List<EBookEntity> eBooks;
    private List<EVideoEntity> eVideo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<BibliotecaEntity> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(List<BibliotecaEntity> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public List<EBookEntity> geteBooks() {
        return eBooks;
    }

    public void seteBooks(List<EBookEntity> eBooks) {
        this.eBooks = eBooks;
    }

    public List<EVideoEntity> geteVideo() {
        return eVideo;
    }

    public void seteVideo(List<EVideoEntity> eVideo) {
        this.eVideo = eVideo;
    }
}
