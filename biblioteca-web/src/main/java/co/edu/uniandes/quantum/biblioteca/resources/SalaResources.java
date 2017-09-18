/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.SalaDTO;
import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author cg.chavarro
 */
@Path("salas")
@Produces("application/json")
@Consumes("application/json")
public class SalaResources 
{
 
    
    
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
