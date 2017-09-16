/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.PrestamoDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.PrestamoDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.PrestamoLogic;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
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
 * @author f.posada
 */


@Produces("application/json")
@Consumes("application/json")
public class PrestamoResource {

    @Inject
    PrestamoLogic prestamoLogic;

    @GET
    public List<PrestamoDTO> getPrestamos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return listEntity2DTO(prestamoLogic.getPrestamos(idUsuario));
    }

    @GET
    @Path("{id: \\d+}")
    public PrestamoDTO getPrestamo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        PrestamoEntity entity = prestamoLogic.getPrestamo(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/prestamos/" + id + " no existe.", 404);
        }
        return new PrestamoDTO(entity);
    }

    @POST
    public PrestamoDTO createPrestamo(@PathParam("idUsuario") Long idUsuario, PrestamoDTO prestamo) throws BusinessLogicException {
        return new PrestamoDTO(prestamoLogic.createPrestamo(idUsuario, prestamo.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public PrestamoDTO updatePrestamo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id, PrestamoDTO prestamo) throws BusinessLogicException {
        prestamo.setId(id);
        PrestamoEntity entity = prestamoLogic.getPrestamo(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/prestamos/" + id + " no existe.", 404);
        }
        return new PrestamoDTO(prestamoLogic.updatePrestamo(idUsuario, prestamo.toEntity()));

    }

    @DELETE
    @Path("{id: \\d+}")
    public void deletePrestamo(@PathParam("idUsuario") Long idUsuario, @PathParam("id") Long id) throws BusinessLogicException {
        PrestamoEntity entity = prestamoLogic.getPrestamo(idUsuario, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + idUsuario + "/prestamos/" + id + " no existe.", 404);
        }
        prestamoLogic.deletePrestamo(idUsuario, id);
    }

    private List<PrestamoDTO> listEntity2DTO(List<PrestamoEntity> entityList) {
        List<PrestamoDTO> list = new ArrayList<>();
        for (PrestamoEntity entity : entityList) {
            list.add(new PrestamoDTO(entity));
        }
        return list;
    }


}