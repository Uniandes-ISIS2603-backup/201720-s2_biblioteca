package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.EComentarioDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.EComentarioLogic;
import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;
import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Produces("application/json")
@Consumes("application/json")
public class EComentarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(EComentarioResource.class.getName());


    @Inject
    EComentarioLogic logic;

    @GET
    public List<EComentarioDTO> getComentariosEBook(@PathParam("idEBook")Long idEbook) throws BusinessLogicException{
        
        if(idEbook==0)return getComentarios();
        
        List<EComentarioDTO> res = new ArrayList<>();
        for(EComentarioEntity entity : logic.getEComentariosEBook(idEbook)){
            res.add(new EComentarioDTO(entity));
        }
        return res;
        
    }
    
    public List<EComentarioDTO> getComentarios() throws BusinessLogicException{
        List<EComentarioDTO> res = new ArrayList<>();
        for(EComentarioEntity entity : logic.getEComentarios()){
            res.add(new EComentarioDTO(entity));
        }
        return res;
    }

    @GET
    @Path("{id: \\d+}")
    public EComentarioDTO getEComentario(@PathParam("idEbook") Long idEBook, @PathParam("id") Long id) throws BusinessLogicException {
        EComentarioEntity entity = logic.getEComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /eBooks/" + idEBook + "/comments/" + id + " no existe.", 404);
        }
        return new EComentarioDTO(entity);
    }

    @PUT
    @Path("{id: \\d+}")
    public EComentarioDTO updateEComentario(@PathParam("idEBook")Long idEBook, @PathParam("id")Long id, EComentarioDTO EComentario) throws BusinessLogicException{
        EComentarioEntity entity = logic.updateEComentario(id, EComentario.toEntity(), idEBook);
       
        if (entity == null) {
            throw new WebApplicationException("El recurso /eBooks/" + idEBook + "/comments/" + id + " no existe.", 404);
        }
        return new EComentarioDTO(entity);
    }

    @POST
    public EComentarioDTO createEComentario(@PathParam("idEBook") Long idEBook, EComentarioDTO EComentario) throws BusinessLogicException {
        return new EComentarioDTO(logic.crearEComentario(EComentario.toEntity(),idEBook));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteEComentario(@PathParam("idEBook")Long idEBook, @PathParam("id")Long id)throws BusinessLogicException {
        try{
            logic.deleteEComentario(id);
        }catch(BusinessLogicException e){
            throw new WebApplicationException("El recurso /eBooks/" + idEBook + "/comments/" + id + " no existe.", 404);
        }
    }






}
