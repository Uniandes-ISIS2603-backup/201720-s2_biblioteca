/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;


import co.edu.uniandes.quantum.biblioteca.dtos.BlogDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.BlogLogic;
import co.edu.uniandes.quantum.biblioteca.entities.BlogEntity;
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
 * @author da.leon
 */
@Path("blog")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BlogResource 
{

    @Inject
    BlogLogic BlogLogic;
  
   
     @GET
    public List<BlogDTO> getBlogs() throws BusinessLogicException {
         if(listEntity2DTO(BlogLogic.getBlogs()).isEmpty())
            throw new WebApplicationException("No hay blogs en el sistema.");
        else
        return listEntity2DTO(BlogLogic.getBlogs());
    }
    

    
    @GET
    @Path("{id: \\d+}")
    public BlogDTO getBlog(@PathParam("id") Long id) throws BusinessLogicException {
        BlogEntity entity = BlogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Blog/" + id + " no existe.", 404);
        }
        return new BlogDTO(entity);
    }
    
     /**
     *
     * @param Blog
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public BlogDTO createBlog(BlogDTO Blog) throws BusinessLogicException {        
         return new BlogDTO(BlogLogic.crearBlog(Blog.toEntity()));
    }
    
    
    @PUT
    @Path("{id: \\d+}")
    public BlogDTO updateBlog(@PathParam("id") Long id, BlogDTO Blog) throws BusinessLogicException {
        Blog.setId(id);
        BlogEntity entity = BlogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Blog/" + id + " no existe.", 404);
        }
        return new BlogDTO(BlogLogic.updateBlog(id, Blog.toEntity()));
    }

  
    
    @DELETE
    @Path("{BlogsId: \\d+}")
    public void deleteBlog(@PathParam("BlogsId") Long id) throws BusinessLogicException {
        BlogEntity entity = BlogLogic.getBlog(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Blogs/" + id + " no existe.", 404);
        }
        BlogLogic.deleteBlog(id);
    }
    
    
     /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BlogEntity a una lista de
     * objetos BlogDTO (json)
     *
     * @param entityList corresponde a la lista de blog de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de blogs en forma DTO (json)
     */
    private List<BlogDTO> listEntity2DTO(List<BlogEntity> entityList) 
    {
        List<BlogDTO> list = new ArrayList<>();
        for (BlogEntity entity : entityList) {
            list.add(new BlogDTO(entity));
        }
        return list;
    }
}
