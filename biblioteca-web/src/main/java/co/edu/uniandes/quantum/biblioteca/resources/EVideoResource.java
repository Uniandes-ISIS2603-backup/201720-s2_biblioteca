package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.EVideoDTO;
import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.ejb.EVideoLogic;

import java.util.ArrayList;
import java.util.List;
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
@Path("eVideos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EVideoResource {

    private static final String MEN_ERROR = "El recurso /usuarios/";
    private static final String NO_EXISTE = "no existe.";

    @Inject
    EVideoLogic EVideoLogic;

    @GET
    public List<EVideoDTO> getEVideos() throws BusinessLogicException {
        if (EVideoLogic.getEVideos().isEmpty())
            throw new WebApplicationException("No hay eVideos");
        else
            return listEntity2DTO(EVideoLogic.getEVideos());
    }

    @GET
    @Path("{id: \\d+}")
    public EVideoDTO getEVideo(@PathParam("id") Long id) throws BusinessLogicException {
        EVideoEntity entity = EVideoLogic.getEVideo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /EVideo/" + id + " no existe.", 404);
        }
        return new EVideoDTO(entity);
    }

    @POST
    public EVideoDTO createEVideo(EVideoDTO eVideo) throws BusinessLogicException {
        return new EVideoDTO(EVideoLogic.createEVideo(eVideo.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public EVideoDTO updateEVideo(@PathParam("id") Long id, EVideoDTO eVideo) throws BusinessLogicException {
        eVideo.setId(id);
        EVideoEntity entity = EVideoLogic.getEVideo(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        return new EVideoDTO(EVideoLogic.updateEVideo(id, eVideo.toEntity()));
    }

    @DELETE
    @Path("{eVideosId: \\d+}")
    public void deleteEVideo(@PathParam("eVideosId") Long id) throws BusinessLogicException {
        EVideoEntity entity = EVideoLogic.getEVideo(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        EVideoLogic.deleteEVideo(id);
    }


    private List<EVideoDTO> listEntity2DTO(List<EVideoEntity> entityList) {
        List<EVideoDTO> list = new ArrayList<>();
        for (EVideoEntity entity : entityList) {
            list.add(new EVideoDTO(entity));
        }
        return list;


    }
}