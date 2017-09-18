/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.quantum.biblioteca.entities;

import co.edu.uniandes.quantum.biblioteca.entities.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603, jp.sanmiguel
 */
@Entity
public class BibliotecaEntity extends BaseEntity implements Serializable {
   
    /**
     * Nombre biblioteca
     */
    private String nombre;
    
    /**
     * Ubicación biblioteca
     */
    private String ubicacion;
    
    @PodamExclude
    @OneToMany(mappedBy = "miBiblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaEntity> salas  = new ArrayList<SalaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miBiblioteca")
    private List<LibroEntity> libros = new ArrayList<LibroEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "miBiblioteca")
    private List<VideoEntity> videos = new ArrayList<VideoEntity>();
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<SalaEntity> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaEntity> salas) {
        this.salas = salas;
    }

    public List<LibroEntity> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }

    public List<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoEntity> videos) {
        this.videos = videos;
    }     
    
}
