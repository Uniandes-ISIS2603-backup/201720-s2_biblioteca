/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.PrestamoDetailDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.UsuarioDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.PrestamoLogic;
import co.edu.uniandes.quantum.biblioteca.ejb.UsuarioLogic;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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


@Path("{idAcceso: \\d+}/usuarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class UsuarioResource {

    private static final String MEN_ERROR = "El recurso /usuarios/";
    private static final String NO_EXISTE = "no existe.";
    private static final String SOLOADMIN = "Sólo un admin o el usuario con id ";
    private static final String PUEDE = " puede ver esta información.";

    @Inject
    UsuarioLogic usuarioLogic;

    @Inject
    PrestamoLogic prestamoLogic;

    @GET
    public List<UsuarioDTO> getUsuarios(@PathParam("idAcceso") Long idAcceso) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        if (listUsuarioEntity2DetailDTO(usuarioLogic.getUsuarios()).isEmpty()) {
            throw new WebApplicationException("No hay usuarios");
        } else {
            return listUsuarioEntity2DTO(usuarioLogic.getUsuarios());
        }
    }

    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("idAcceso") Long idAcceso, @PathParam("id") Long id) throws BusinessLogicException {
        if (!Objects.equals(idAcceso, id) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + id + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        return new UsuarioDetailDTO(entity);
    }

    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {
        return new UsuarioDetailDTO(usuarioLogic.createUsuario(usuario.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("idAcceso") Long idAcceso, @PathParam("id") Long id, UsuarioDetailDTO usuario) throws BusinessLogicException {
        if (!Objects.equals(idAcceso, id) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + id + PUEDE);
        }
        usuario.setId(id);
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(id, usuario.toEntity()));
    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("idAcceso") Long idAcceso, @PathParam("usuariosId") Long id) throws BusinessLogicException {
        if (!Objects.equals(idAcceso, id) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + id + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(id);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + id + NO_EXISTE, 404);
        }
        usuarioLogic.deleteUsuario(id);
    }

    @Path("{idUsuario: \\d+}/prestamos")
    public Class<PrestamoResource> getPrestamoResource(@PathParam("idAcceso") Long idAcceso, @PathParam("idUsuario") Long usuarioId) throws BusinessLogicException {
        if (!Objects.equals(idAcceso, usuarioId) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + usuarioId + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(usuarioId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + usuarioId + "/prestamos no existe.", 404);
        }
        return PrestamoResource.class;
    }

    @Path("{idUsuario: \\d+}/multas")
    public Class<MultaResource> getMultaResource(@PathParam("idAcceso") Long idAcceso, @PathParam("idUsuario") Long usuariosId) {
        if (!Objects.equals(idAcceso, usuariosId) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + usuariosId + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(usuariosId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + usuariosId + "/multas no existe.", 404);
        }
        return MultaResource.class;
    }

    @Path("{idUsuario: \\d+}/medioPago")
    public Class<MedioPagoResource> getMedioPagoResource(@PathParam("idAcceso") Long idAcceso, @PathParam("idUsuario") Long usuariosId) {
        if (!Objects.equals(idAcceso, usuariosId) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + usuariosId + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(usuariosId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + usuariosId + "/medioPago no existe.", 404);
        }
        return MedioPagoResource.class;
    }

    @Path("{idUsuario: \\d+}/reservas")
    public Class<ReservaResource> getReservaResource(@PathParam("idAcceso") Long idAcceso, @PathParam("idUsuario") Long usuariosId) {
        if (!Objects.equals(idAcceso, usuariosId) && idAcceso != 999) {
            throw new WebApplicationException(SOLOADMIN + usuariosId + PUEDE);
        }
        UsuarioEntity entity = usuarioLogic.getUsuario(usuariosId);
        if (entity == null) {
            throw new WebApplicationException(MEN_ERROR + usuariosId + "/reservas no existe.", 404);
        }
        return ReservaResource.class;
    }

    private List<UsuarioDetailDTO> listUsuarioEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    private List<UsuarioDTO> listUsuarioEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }

   

    private void validarAccesoAdmin(Long id) {
        if (id != 999) {
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
        }
    }
}
