/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;
import co.edu.uniandes.quantum.biblioteca.dtos.MedioPagoDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.MedioPagoLogic;
import co.edu.uniandes.quantum.biblioteca.entities.MedioPagoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author jp.sanmiguel
 */

@Produces("application/json")
@Consumes("application/json")
public class MedioPagoResource {
    
    @Inject
    MedioPagoLogic medioPagoLogic;

    @GET
    public List<MedioPagoDTO> getMedioPagos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return listEntity2DTO(medioPagoLogic.getMedioPagos(idUsuario));
    }

    
    @GET
    @Path("{id: \\d+}")
    public MedioPagoDTO getMedioPago(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        MedioPagoEntity entity = medioPagoLogic.getMedioPago(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/medioPagos/" + id + " no existe.", 404);
        }
        return new MedioPagoDTO(entity);
    }
    

     @POST
    public MedioPagoDTO createMedioPago(@PathParam("idUsuario") Long idUsuario, MedioPagoDTO medioPago) throws BusinessLogicException {
        return new MedioPagoDTO(medioPagoLogic.createMedioPago(idUsuario, medioPago.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public MedioPagoDTO updateMedioPago(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id, MedioPagoDTO medioPago) throws BusinessLogicException {
        medioPago.setId(id);
        MedioPagoEntity entity = medioPagoLogic.getMedioPago(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/medioPagos/" + id + " no existe.", 404);
        }
        return new MedioPagoDTO(medioPagoLogic.updateMedioPago(idUsuario, medioPago.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMedioPago(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        MedioPagoEntity entity = medioPagoLogic.getMedioPago(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/medioPagos/" + id + " no existe.", 404);
        }
        medioPagoLogic.deleteMedioPago(idUsuario, id);
    }

    private List<MedioPagoDTO> listEntity2DTO(List<MedioPagoEntity> entityList) {
        List<MedioPagoDTO> list = new ArrayList<>();
        for (MedioPagoEntity entity : entityList) {
            list.add(new MedioPagoDTO(entity));
        }
        return list;
    }
    

}
