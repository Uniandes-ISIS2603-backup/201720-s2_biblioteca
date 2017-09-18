/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.BibliotecaDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.MultaDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.PrestamoDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.quantum.biblioteca.ejb.MultaLogic;
import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
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
@Path("multas")
@Produces("application/json")
@Consumes("application/json")
public class MultaResource {
    
    @Inject
    MultaLogic multaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(MultaResource.class.getName());

    /**
     * POST http://localhost:8080/biblioteca-web/api/multas
     *
     * @param Multa correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BibliotecaDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public MultaDTO createMulta(MultaDTO multa) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MultaEntity multaEntity = multa.toEntity();
        // Invoca la lógica para crear la Multa nueva
        MultaEntity nuevaBiblioteca = multaLogic.createMulta(multaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new MultaDTO(nuevaBiblioteca);
    }

    /**
     * GET para todas las Multas.
     * http://localhost:8080/biblioteca-web/api/multas
     *
     * @return la lista de todas las Multas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<MultaDTO> getMultas() throws BusinessLogicException {
        return listEntity2DTO(multaLogic.getMultas());
    }

   
    /**
     * PUT http://localhost:8080/biblioteca-web/api/multas/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Multa a actualizar.
     * @param multa corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Multa actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Multa a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public MultaDTO updateBiblioteca(@PathParam("id") Long id, MultaDTO multa) throws BusinessLogicException, UnsupportedOperationException {
        multa.setId(id);
        MultaEntity entity = multaLogic.getMulta(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso multa" + id + " no existe.", 404);
        }
        return new MultaDTO(multaLogic.updateEntity(entity));
      
    }

    /**
     * DELETE http://localhost:8080/biblioteca-web/api/bibliotecas/{id}
     *
     * @param id corresponde a la Biblioteca a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Biblioteca a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMulta(@PathParam("id") Long id) throws BusinessLogicException {
         if(multaLogic.getMulta(id) == null)
         {
             throw new WebApplicationException("El recurso multa " + id+ " no existe.", 404);
         }
         multaLogic.deleteMulta(id);
    }

    @GET
    public List<MultaDTO> getUsuarios() throws BusinessLogicException {
        return listEntity2DTO(multaLogic.getMultas());
    }
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BibliotecaEntity a una lista de
     * objetos BibliotecaDTO (json)
     *
     * @param entityList corresponde a la lista de Bibliotecaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Bibliotecaes en forma DTO (json)
     */
    private List<MultaDTO> listEntity2DTO(List<MultaEntity> entityList) {
        List<MultaDTO> list = new ArrayList<>();
        for (MultaEntity entity : entityList) {
            list.add(new MultaDTO(entity));
        }
        return list;
    }

}
