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
@Path("salas")
@Produces("application/json")
@Consumes("application/json")
public class SalaResources 
{
//    GET /salas  ya
//GET /salas/{id}   ya
//POST /salas         ya
//PUT /salas/{id}      ya
//DELETE /salas/{id}    ya
    @Inject
    SalaLogic SalaLogic;
  
   
     @GET
    public List<SalaDTO> getSalas() throws BusinessLogicException {
        return listEntity2DTO(SalaLogic.getSalas());
    }
    
    
    @GET
    @Path("{id: \\d+}")
    public SalaDTO getSala(@PathParam("id") Long id) throws BusinessLogicException {
        SalaEntity entity = SalaLogic.getSala(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Salas/" + id + " no existe.", 404);
        }
        return new SalaDTO(entity);
    }
    
     /**
     *
     * @param Sala
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public SalaDTO createSala(SalaDTO Sala) throws BusinessLogicException {        
         return new SalaDTO(SalaLogic.crearSala(Sala.toEntity()));
    }
    
     /**
     *
     * Ejemplo: { "description": "Las habilidades gerenciales en arquitectos de
     * software.", "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param id
     * @param Sala
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public SalaDTO updateSala(@PathParam("id") Long id, SalaDTO Sala) throws BusinessLogicException {
        Sala.setId(id);
        SalaEntity entity = SalaLogic.getSala(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Salas/" + id + " no existe.", 404);
        }
        return new SalaDTO(SalaLogic.updateSala(id, Sala.toEntity()));
    }

    @DELETE
    @Path("{SalasId: \\d+}")
    public void deleteSala(@PathParam("SalasId") Long id) throws BusinessLogicException {
        SalaEntity entity = SalaLogic.getSala(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Salas/" + id + " no existe.", 404);
        }
        SalaLogic.deleteSalas(id);
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
}
