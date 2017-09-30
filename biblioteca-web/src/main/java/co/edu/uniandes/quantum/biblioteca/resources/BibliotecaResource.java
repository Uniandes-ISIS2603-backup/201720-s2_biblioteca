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
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.BibliotecaDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.quantum.biblioteca.dtos.BibliotecaDetailDTO;
import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "Bibliotecas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Bibliotecas". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Bibliotecas"
 *
 * @author ISIS2603, jp.sanmiguel
 *
 */
@Path("bibliotecas")
@Produces("application/json")
@Consumes("application/json")
public class BibliotecaResource {

    @Inject
    BibliotecaLogic bibliotecaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(BibliotecaResource.class.getName());

    /**
     * POST http://localhost:8080/biblioteca-web/api/bibliotecas
     *
     * @param Biblioteca correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BibliotecaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public BibliotecaDTO createBiblioteca(BibliotecaDTO Biblioteca) throws BusinessLogicException {
        return new BibliotecaDetailDTO(bibliotecaLogic.createBiblioteca(Biblioteca.toEntity()));
    }

    /**
     * GET para todas las Bibliotecaes.
     * http://localhost:8080/biblioteca-web/api/Bibliotecas
     *
     * @return la lista de todas las Bibliotecaes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<BibliotecaDTO> getBibliotecas() throws BusinessLogicException {
        if(listEntity2DetailDTO(bibliotecaLogic.getBibliotecas()).isEmpty())
            throw new WebApplicationException("No hay bibliotecas");
        else
        return listEntity2DTO(bibliotecaLogic.getBibliotecas());
    }

    /**
     * PUT http://localhost:8080/biblioteca-web/api/bibliotecas/1 Ejemplo json {
     * "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Biblioteca a actualizar.
     * @param biblioteca corresponde al objeto con los cambios que se van a
     * realizar.
     * @return La Biblioteca actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Biblioteca a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{idBiblioteca: \\d+}")
    public BibliotecaDTO updateBiblioteca(@PathParam("idBiblioteca") Long id, BibliotecaDTO biblioteca) throws BusinessLogicException, UnsupportedOperationException {
        biblioteca.setId(id);
        BibliotecaEntity entity = bibliotecaLogic.getBiblioteca(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso biblioteca " + id + " no existe.", 404);
        }
        return new BibliotecaDetailDTO(bibliotecaLogic.updateBiblioteca(entity));

    }
    
    @Path("{idBiblioteca: \\d+}/libros")
    public Class<LibroResource> getLibrosBiblioteca(@PathParam("idBiblioteca") Long id) {
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(id);
        if (biblioteca == null) {
            throw new WebApplicationException("El recurso biblioteca con id:" + id + " no existe.", 404);
        }
        return LibroResource.class;
    }
    
     @Path("{idBiblioteca: \\d+}/videos")
    public Class<VideoResource> getVideosBiblioteca(@PathParam("idBiblioteca") Long id) {
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(id);
        if (biblioteca == null) {
            throw new WebApplicationException("El recurso biblioteca con id:" + id + " no existe.", 404);
        }
        return VideoResource.class;
    }
    
     @Path("{idBiblioteca: \\d+}/salas")
    public Class<SalaResource> getSalasBiblioteca(@PathParam("idBiblioteca") Long id) {
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(id);
        if (biblioteca == null) {
            throw new WebApplicationException("El recurso biblioteca con id:" + id + " no existe.", 404);
        }
        return SalaResource.class;
    }

    /**
     * DELETE http://localhost:8080/biblioteca-web/api/bibliotecas/{id}
     *
     * @param id corresponde a la Biblioteca a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Biblioteca a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{idBiblioteca: \\d+}")
    public void deleteBiblioteca(@PathParam("idBiblioteca") Long id) throws BusinessLogicException {
        BibliotecaEntity entity = bibliotecaLogic.getBiblioteca(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso biblioteca " + id + "  no existe.", 404);
        }
        LOGGER.log(Level.WARNING, "Inicia el proceso de eliminar la Biblioteca con el id: {0}", id);
        bibliotecaLogic.deleteBiblioteca(id);
    }   
    
    @GET
    @Path("{idBiblioteca: \\d+}")
    public BibliotecaDTO getBiblioteca(@PathParam("idBiblioteca") Long id) {
        BibliotecaEntity entity = bibliotecaLogic.getBiblioteca(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso biblioteca " + id + " no existe.", 404);
        }
        return new BibliotecaDTO(entity);
    }
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BibliotecaEntity a una lista
     * de objetos BibliotecaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Bibliotecaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Bibliotecaes en forma DTO (json)
     */
    private List<BibliotecaDetailDTO> listEntity2DetailDTO(List<BibliotecaEntity> entityList) {
        List<BibliotecaDetailDTO> list = new ArrayList<>();
        for (BibliotecaEntity entity : entityList) {
            list.add(new BibliotecaDetailDTO(entity));
        }
        return list;
    }
    
    private List<BibliotecaDTO> listEntity2DTO(List<BibliotecaEntity> entityList) {
        List<BibliotecaDTO> list = new ArrayList<>();
        for (BibliotecaEntity entity : entityList) {
            list.add(new BibliotecaDTO(entity));
        }
        return list;
    }
}
