/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.quantum.biblioteca.entities.LibroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author cg.chavarro
 */

@Path("libros")
@Produces("application/json")
@Consumes("application/json")
public class LibroResource 
{
    
    
    
    
    
    
    
    
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos LibroEntity a una lista de
     * objetos LibroDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<LibroDTO> listEntity2DTO(List<LibroEntity> entityList) 
    {
        List<LibroDTO> list = new ArrayList<>();
        for (LibroEntity entity : entityList) {
            list.add(new LibroDTO(entity));
        }
        return list;
    }
}
