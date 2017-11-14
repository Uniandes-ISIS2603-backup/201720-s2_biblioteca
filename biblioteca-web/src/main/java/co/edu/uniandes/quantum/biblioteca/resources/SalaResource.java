/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.SalaDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.SalaLogic;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
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
@Produces("application/json")
@Consumes("application/json")

public class SalaResource 
{
//    GET /salas  ya
//GET /salas/{id}   ya
//POST /salas         ya
//PUT /salas/{id}      ya
//DELETE /salas/{id}    ya
    @Inject
    SalaLogic SalaLogic;
   
   
     @GET
    public List<SalaDTO> getSalasBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BusinessLogicException {
        return listEntity2DTO(SalaLogic.getSalas(idBiblioteca));
    }
    
    @GET
    @Path("disponibles")
    public List<SalaDTO> getSalasDisponibles(@PathParam("idBiblioteca") Long idBiblioteca) throws BusinessLogicException {
        return listEntity2DTO(SalaLogic.getSalasDisponibles());
    }
    
     @GET
     @Path("enPrestamo")
    public List<SalaDTO> getSalasPrestamo(@PathParam("idPrestamo") Long prestamoId) throws BusinessLogicException {
        return listEntity2DTO(SalaLogic.getSalasPrestamo(prestamoId));
    }
    

    
    @GET
    @Path("{id: \\d+}")
    public SalaDTO getSala(@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long idSala) throws BusinessLogicException {
        SalaEntity entity = SalaLogic.getSala(idBiblioteca,idSala);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/salas/" + idSala + " no existe.", 404);
        }
        return new SalaDTO(entity);
    }
    
    @GET
    @Path("{id: \\d+}/ind")
    public SalaDTO getSala(@PathParam("id") Long idSala) throws BusinessLogicException {
        SalaEntity entity = SalaLogic.getSala(idSala);
        if (entity == null) {
            throw new WebApplicationException("La sala con id " + idSala + " no existe.", 404);
        }
        return new SalaDTO(entity);
    }
    
    
    
    
    
    
    
    @POST
    public SalaDTO createSala(@PathParam("idAcceso") Long idAcceso,@PathParam("idBiblioteca") Long idBiblioteca, SalaDTO sala) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        return new SalaDTO(SalaLogic.crearSala(idBiblioteca, sala.toEntity()));
    }
   
    @PUT
    @Path("{id: \\d+}")
    public SalaDTO updateSala(@PathParam("idAcceso") Long idAcceso,@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long idSala, SalaDTO sala) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        sala.setId(idSala);
        SalaEntity entity = SalaLogic.getSala(idBiblioteca,idSala);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/salas/" + idSala + " no existe.", 404);
        }
        return new SalaDTO(SalaLogic.updateSala(idBiblioteca, sala.toEntity()));
    }
    
    @PUT
     @Path("actual/{idPz: \\d+}/prestamo/{idSalita: \\d+}")
    public SalaDTO ponerSalaPrestamo(@PathParam("idPz") Long idPrestamo, @PathParam("idSalita") Long idB) throws BusinessLogicException {
        
        SalaEntity lib=SalaLogic.getSala(idB);
        return new SalaDTO(SalaLogic.colocarSalaPrestamo(lib,idPrestamo));
    }
    
    @PUT
    @Path("{id: \\d+}/devolver")
    public void devolverSala(@PathParam("id") Long idSala) throws BusinessLogicException {
        SalaEntity entity = SalaLogic.getSala(idSala);
        
        if (entity == null) {
            throw new WebApplicationException("La sala no existe");
        }
        
        SalaLogic.devolverSala(entity);
    }


  
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSala(@PathParam("idAcceso") Long idAcceso,@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("idSala") Long idSala) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        SalaEntity entity = SalaLogic.getSala(idBiblioteca,idSala);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/salas/" + idSala + " no existe.", 404);
        }
        SalaLogic.deleteSalas(idBiblioteca, idSala);
    }
    
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos SalaEntity a una lista de
     * objetos SalaDTO (json)
     *
     * @param entityList corresponde a la lista de Salas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Salas en forma DTO (json)
     */
    private List<SalaDTO> listEntity2DTO(List<SalaEntity> entityList) 
    {
        List<SalaDTO> list = new ArrayList<>();
        for (SalaEntity entity : entityList) {
            list.add(new SalaDTO(entity));
        }
        return list;
    }
    
    private void validarAccesoAdmin(Long id)
    {
        if(id!=999)
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
    }
}
