/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.UsuarioLogic;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
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
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    @Inject
    UsuarioLogic usuarioLogic;

    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws BusinessLogicException {
        if(listUsuarioEntity2DetailDTO(usuarioLogic.getUsuarios()).isEmpty())
            throw new WebApplicationException("No hay usuarios");
        else
        return listUsuarioEntity2DetailDTO(usuarioLogic.getUsuarios());
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        return new UsuarioDetailDTO(entity);
    }

   
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {        
         return new UsuarioDetailDTO(usuarioLogic.createUsuario(usuario.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws BusinessLogicException {
        usuario.setId(id);
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(id, usuario.toEntity()));
    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("usuariosId") Long id) throws BusinessLogicException {
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + id + " no existe.", 404);
        }
        usuarioLogic.deleteUsuario(id);
    }   

    private List<UsuarioDetailDTO> listUsuarioEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
