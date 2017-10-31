/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.ReservaDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
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
 * @author cg.chavarro
 */

//TODO implementar clase y api rest
@Produces("application/json")
@Consumes("application/json")
//@RequestScoped
public class ReservaResource 
{
 private static final String MEN_ERROR="El recurso /usuarios/";
    private static final String NO_EXISTE="no existe.";  
    
     @Inject
    ReservaLogic reservaLogic;
     
      @GET
    public List<ReservaDTO> getReservas(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return listEntity2DTO(reservaLogic.getReservas(idUsuario));
    }
    
    
//    @GET
//    @Path("{id: \\d+}")
//    public PrestamoDTO getPrestamo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
//        PrestamoEntity entity = prestamoLogic.getPrestamo(idUsuario, id);
//        if (entity == null) {
//            throw new WebApplicationException(MEN_ERROR + idUsuario + "/prestamos/" + id + NO_EXISTE, 404);
//        }
//        return new PrestamoDTO(entity);
//    }
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException
    {
        ReservaEntity entity = reservaLogic.getReserva(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + idUsuario + "/reservas/" + id + NO_EXISTE, 404);
        }
        return new ReservaDTO(entity);
    }
    @POST
    public ReservaDTO createReserva(@PathParam("idUsuario") Long idUsuario, ReservaDTO reserva) throws BusinessLogicException {
        return new ReservaDTO(reservaLogic.createReserva(idUsuario, reserva.toEntity()));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO updateReserva(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id, ReservaDTO reserva) throws BusinessLogicException {
        reserva.setId(id);
        ReservaEntity entity = reservaLogic.getReserva(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + idUsuario + "/reservas/" + id + NO_EXISTE, 404);
        }
        return new ReservaDTO(reservaLogic.updateReserva(idUsuario, reserva.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
       ReservaEntity entity = reservaLogic.getReserva(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + idUsuario + "/reservas/" + id + NO_EXISTE, 404);
        }
        reservaLogic.deleteReserva(idUsuario, id);
    }
    
    
     private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
   
     @Path("{idReserva: \\d+}/libros")
    public Class<LibroResource> getLibroResource(@PathParam("idUsuario") Long idUsuario,@PathParam("idReserva") Long reservaId) {
        ReservaEntity entity = reservaLogic.getReserva(idUsuario,reservaId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + idUsuario + "/reservas/" + reservaId + NO_EXISTE, 404);
        }
        return LibroResource.class;
    }

    @Path("{idReserva: \\d+}/videos")
    public Class<VideoResource> getVideoResource(@PathParam("idUsuario") Long idUsuario,@PathParam("idReserva") Long reservaId) {
        ReservaEntity entity = reservaLogic.getReserva(idUsuario,reservaId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + idUsuario + "/reservas/" + reservaId + NO_EXISTE, 404);
        }
        return VideoResource.class;
    }
    
    private void validarAccesoAdmin(Long id)
    {
        if(id!=999)
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
    }
}
