/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.LibroLogic;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author cg.chavarro
 */

@Path("libros")
@Produces("application/json")
@Consumes("application/json")
public class LibroResource 
{
//    GET /libros   ya
//GET /libros/{id}   ya
//POST /libros      ya
//PUT /libros/{id}   ya
//DELETE /libros/{id}  ya
    @Inject
    LibroLogic LibroLogic;
    
     @GET
    public List<LibroDTO> getBooks() throws BusinessLogicException {
        return listEntity2DTO(LibroLogic.getBooks());
    }
    
    
    @GET
    @Path("{id: \\d+}")
    public LibroDTO getBook(@PathParam("id") Long id) throws BusinessLogicException {
        LibroEntity entity = LibroLogic.getLibro(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        return new LibroDTO(entity);
    }
    
     /**
     *
     * @param book
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public LibroDTO createBook(LibroDTO book) throws BusinessLogicException {        
         return new LibroDTO(LibroLogic.crearLibro(book.toEntity()));
    }
    
     /**
     *
     * Ejemplo: { "description": "Las habilidades gerenciales en arquitectos de
     * software.", "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param id
     * @param book
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public LibroDTO updateBook(@PathParam("id") Long id, LibroDTO book) throws BusinessLogicException {
        book.setId(id);
        LibroEntity entity = LibroLogic.getLibro(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        return new LibroDTO(LibroLogic.updateBook(id, book.toEntity()));
    }

    @DELETE
    @Path("{booksId: \\d+}")
    public void deleteBook(@PathParam("booksId") Long id) throws BusinessLogicException {
        LibroEntity entity = LibroLogic.getLibro(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + id + " no existe.", 404);
        }
        LibroLogic.deleteBook(id);
    }
    
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos LibroEntity a una lista de
     * objetos LibroDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<LibroDTO> listEntity2DTO(List<LibroEntity> entityList) 
    {
        List<LibroDTO> list = new ArrayList<>();
        for (LibroEntity entity : entityList) {
            list.add(new LibroDTO(entity));
        }
        return list;
    }
}
