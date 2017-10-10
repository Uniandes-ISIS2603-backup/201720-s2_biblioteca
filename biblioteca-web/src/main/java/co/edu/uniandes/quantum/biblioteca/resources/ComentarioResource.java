/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.ComentarioDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author da.leon
 */
@Path("{idAcceso: \\d+}/comentario")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource 
{

    @Inject
    ComentarioLogic ComentarioLogic;
  
   
     @GET
    public List<ComentarioDTO> getComentarios() throws BusinessLogicException {
         if(listEntity2DTO(ComentarioLogic.getComentarios()).isEmpty())
            throw new WebApplicationException("No hay comentarios en el sistema.");
        else
        return listEntity2DTO(ComentarioLogic.getComentarios());
    }
    

    
    @GET
    @Path("{id: \\d+}")
    public ComentarioDTO getComentario(@PathParam("id") Long id) throws BusinessLogicException {
        ComentarioEntity entity = ComentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Comentario/" + id + " no existe.", 404);
        }
        return new ComentarioDTO(entity);
    }
    
     /**
     *
     * @param Comentario
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public ComentarioDTO createComentario(ComentarioDTO Comentario) throws BusinessLogicException {        
         return new ComentarioDTO(ComentarioLogic.crearComentario(Comentario.toEntity()));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public ComentarioDTO updateComentario(@PathParam("id") Long id, ComentarioDTO Comentario) throws BusinessLogicException {
        Comentario.setId(id);
        ComentarioEntity entity = ComentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Comentario/" + id + " no existe.", 404);
        }
        return new ComentarioDTO(ComentarioLogic.updateComentario(id, Comentario.toEntity()));
    }

  
    
    @DELETE
    @Path("{ComentariosId: \\d+}")
    public void deleteComentario(@PathParam("ComentariosId") Long id) throws BusinessLogicException {
        ComentarioEntity entity = ComentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Comentarios/" + id + " no existe.", 404);
        }
        ComentarioLogic.deleteComentario(id);
    }
    
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ComentarioEntity a una lista de
     * objetos ComentarioDTO (json)
     *
     * @param entityList corresponde a la lista de comentario de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de comentario en forma DTO (json)
     */
    private List<ComentarioDTO> listEntity2DTO(List<ComentarioEntity> entityList) 
    {
        List<ComentarioDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDTO(entity));
        }
        return list;
    }
    
    private void validarAccesoAdmin(Long id)
    {
        if(id!=999)
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
    }
}
