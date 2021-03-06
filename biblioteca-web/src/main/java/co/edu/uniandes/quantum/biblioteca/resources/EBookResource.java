
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.EBookDTO;
import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.ejb.EBookLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * @author de.agudelo
 */
@Path("{idAcceso: \\d+}/eBooks")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EBookResource {

    private static final String MEN_ERROR = "El recurso /eBooks/";
    private static final String NO_EXISTE = "no existe.";
    private static final String SOLOADMIN = "Sólo un admin o el usuario con id ";
    private static final String PUEDE = " puede ver esta información.";


    @Inject
    EBookLogic logic;

    @GET
    public List<EBookDTO> getEBooks() throws BusinessLogicException {
        if (logic.getEBooks().isEmpty())
            throw new WebApplicationException("No hay eBooks");
        else
            return listEntity2DTO(logic.getEBooks());
    }


    @GET
    @Path("{id: \\d+}")
    public EBookDTO getEBook(@PathParam("id") Long id) throws BusinessLogicException {
        EBookEntity entity = logic.getEBook(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /EBook/" + id + " no existe.", 404);
        }
        return new EBookDTO(entity);
    }

    @POST
    public EBookDTO createEBook(@PathParam("idAcceso") Long idAcceso,EBookDTO eBook) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        return new EBookDTO(logic.createEBook(eBook.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public EBookDTO updateEBook(@PathParam("idAcceso") Long idAcceso,@PathParam("id") Long id, EBookDTO ebook) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        ebook.setId(id);
        EBookEntity entity = logic.getEBook(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        return new EBookDTO(logic.updateEBook(id, ebook.toEntity()));
    }

    @DELETE
    @Path("{eBooksId: \\d+}")
    public void deleteEBook(@PathParam("idAcceso") Long idAcceso,@PathParam("eBooksId") Long id) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        EBookEntity entity = logic.getEBook(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        logic.deleteEBook(id);
    }


    private List<EBookDTO> listEntity2DTO(List<EBookEntity> entityList) {
        List<EBookDTO> list = new ArrayList<>();
        for (EBookEntity entity : entityList) {
            list.add(new EBookDTO(entity));
        }
        return list;


    }

    @Path("{idEBook: \\d+}/comments")
    public Class<EComentarioResource> getEComentarioResource(@PathParam("idAcceso") Long idAcceso, @PathParam("idEBook") Long eBookId) {
        if (!Objects.equals(idAcceso, eBookId) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + eBookId + PUEDE);
        }
        EBookEntity entity = logic.getEBook(eBookId);
        if (entity == null && eBookId!=0) {
            throw new WebApplicationException(MEN_ERROR + eBookId + "/comments no existe.", 404);
        }
        return EComentarioResource.class;
    }

    private void validarAccesoAdmin(Long id)
    {
        if(id!=999)
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
    }


}