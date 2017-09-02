/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.quantum.biblioteca.dtos.BibliotecaDetailDTO;
import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;


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
 * Clase que implementa el recurso REST correspondiente a "Bibliotecas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Bibliotecas". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Bibliotecas"
 *
 * @author ISIS2603
 *
 */
@Path("bibliotecas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BibliotecaResource {

    @Inject
    BibliotecaLogic bibliotecaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(BibliotecaResource.class.getName());

    /**
     * POST http://localhost:8080/biblioteca-web/api/bibliotecas
     *
     * @param Biblioteca correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BibliotecaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public BibliotecaDetailDTO createBiblioteca(BibliotecaDetailDTO Biblioteca) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        BibliotecaEntity BibliotecaEntity = Biblioteca.toEntity();
        // Invoca la lógica para crear la Biblioteca nueva
        BibliotecaEntity nuevoBiblioteca = bibliotecaLogic.createBiblioteca(BibliotecaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new BibliotecaDetailDTO(nuevoBiblioteca);
    }

    /**
     * GET para todas las Bibliotecaes.
     * http://localhost:8080/biblioteca-web/api/Bibliotecas
     *
     * @return la lista de todas las Bibliotecaes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<BibliotecaDetailDTO> getBibliotecas() throws BusinessLogicException {
        return listEntity2DetailDTO(bibliotecaLogic.getBibliotecas());
    }

   
    /**
     * PUT http://localhost:8080/biblioteca-web/api/bibliotecas/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Biblioteca a actualizar.
     * @param biblioteca corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Biblioteca actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Biblioteca a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public BibliotecaDetailDTO updateBiblioteca(@PathParam("id") Long id, BibliotecaDetailDTO biblioteca) throws BusinessLogicException, UnsupportedOperationException {
          throw new UnsupportedOperationException("Este servicio  no está implementado");
      
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
    public void deleteBiblioteca(@PathParam("id") Long id) throws BusinessLogicException {
         throw new UnsupportedOperationException("Este servicio no está implementado");
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BibliotecaEntity a una lista de
     * objetos BibliotecaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Bibliotecaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Bibliotecaes en forma DTO (json)
     */
    private List<BibliotecaDetailDTO> listEntity2DetailDTO(List<BibliotecaEntity> entityList) {
        List<BibliotecaDetailDTO> list = new ArrayList<>();
        for (BibliotecaEntity entity : entityList) {
            list.add(new BibliotecaDetailDTO(entity));
        }
        return list;
    }

}
