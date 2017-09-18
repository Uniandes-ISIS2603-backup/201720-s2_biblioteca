/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.VideoDTO;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author cg.chavarro
 */
@Path("videos")
@Produces("application/json")
@Consumes("application/json")
public class VideoResource
{
    
    
    
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos VideoEntity a una lista de
     * objetos VideoDTO (json)
     *
     * @param entityList corresponde a la lista de Videos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Videos en forma DTO (json)
     */
    private List<VideoDTO> listEntity2DTO(List<VideoEntity> entityList) 
    {
        List<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }
}
