/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.BibliotecaDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.MultaDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.MultaDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.quantum.biblioteca.ejb.MultaLogic;
import co.edu.uniandes.quantum.biblioteca.ejb.MultaLogic;
import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
public class MultaResource {
    
    @Inject
    MultaLogic multaLogic;

    @GET
    public List<MultaDTO> getMultas(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return listEntity2DTO(multaLogic.getMultas(idUsuario));
    }

    
    @GET
    @Path("{id: \\d+}")
    public MultaDTO getMulta(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        MultaEntity entity = multaLogic.getMulta(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/multas/" + id + " no existe.", 404);
        }
        return new MultaDTO(entity);
    }

    @POST
    public MultaDTO createMulta(@PathParam("idUsuario") Long idUsuario, MultaDTO multa) throws BusinessLogicException {
        return new MultaDTO(multaLogic.createMulta(idUsuario, multa.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public MultaDTO updateMulta(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id, MultaDTO multa) throws BusinessLogicException {
        multa.setId(id);
        MultaEntity entity = multaLogic.getMulta(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/multas/" + id + " no existe.", 404);
        }
        return new MultaDTO(multaLogic.updateMulta(idUsuario, multa.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteMulta(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        MultaEntity entity = multaLogic.getMulta(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/multas/" + id + " no existe.", 404);
        }
        multaLogic.deleteMulta(idUsuario, id);
    }

    private List<MultaDTO> listEntity2DTO(List<MultaEntity> entityList) {
        List<MultaDTO> list = new ArrayList<>();
        for (MultaEntity entity : entityList) {
            list.add(new MultaDTO(entity));
        }
        return list;
    }

}
