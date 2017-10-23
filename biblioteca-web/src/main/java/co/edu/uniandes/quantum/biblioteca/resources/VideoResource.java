/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.resources;

import co.edu.uniandes.quantum.biblioteca.dtos.VideoDTO;
import co.edu.uniandes.quantum.biblioteca.dtos.VideoDetailDTO;
import co.edu.uniandes.quantum.biblioteca.ejb.VideoLogic;
import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jf.garcia
 */
@Path("{idAcceso: \\\\d+}/videos")
@Produces("application/json")
@Consumes("application/json")

public class VideoResource {

    @Inject
    VideoLogic VideoLogic;

    private static final String MENSAJE_ERROR = "El recurso /Videos/";
    private static final String NO_EXISTE = "no existe.";

    @GET
    public List<VideoDTO> getVideosBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BusinessLogicException {
        if (listEntity2DTO(VideoLogic.getVideos(idBiblioteca)).isEmpty()) {
            throw new WebApplicationException("No hay Videos en el sistema.");
        } else {
            return listEntity2DTO(VideoLogic.getVideos(idBiblioteca));
        }
    }
    
    @GET
    public List<VideoDetailDTO> getVideos() throws BusinessLogicException {
        if (listEntity2DTO(VideoLogic.getVideos()).isEmpty()) {
            throw new WebApplicationException("No hay videos en el sistema.");
        } else {
            return listEntity2DetailDTO(VideoLogic.getVideos());
        }
    }

    @GET
    @Path("{id: \\d+}/bib")
    public VideoDTO getVideo(@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long idVideo) throws BusinessLogicException {
        VideoEntity entity = VideoLogic.getVideo(idBiblioteca, idVideo);
        if (entity == null) {
            throw new WebApplicationException(MENSAJE_ERROR + idVideo + NO_EXISTE, 404);
        }
        return new VideoDTO(entity);
    }
    
    @GET
    @Path("{id: \\d+}")
    public VideoDTO getVideo(@PathParam("id") Long idVideo) throws BusinessLogicException {
        VideoEntity entity = VideoLogic.getVideo(idVideo);
        if (entity == null) {
            throw new WebApplicationException(MENSAJE_ERROR + idVideo + NO_EXISTE, 404);
        }
        return new VideoDTO(entity);
    }
    
     /**
     *
     * @param idAcceso
     * @param video
     * @param idBiblioteca
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{bib}")
    public VideoDTO ponerVideoBiblioteca(@PathParam("idAcceso") Long idAcceso,VideoDTO video, @PathParam("idBiblioteca") Long idBiblioteca) throws BusinessLogicException {
         validarAccesoAdmin(idAcceso);
        return new VideoDTO(VideoLogic.colocarVideoBiblioteca(video.toEntity(), idBiblioteca));
    }
    
     @PUT
     @Path("{actual}/{prestamo}")
    public VideoDTO ponerVideoPrestamo(VideoDTO video, @PathParam("idPrestamo") Long idPrestamo) throws BusinessLogicException {
        
        VideoEntity vid = video.toEntity();
        return new VideoDTO(VideoLogic.colocarVideoPrestamo(vid,idPrestamo));
    }

    @POST
    public VideoDTO createVideo(@PathParam("idAcceso") Long idAcceso,VideoDTO video) throws BusinessLogicException {
         validarAccesoAdmin(idAcceso);
        return new VideoDTO(VideoLogic.crearVideo(video.toEntity()));
    }

    @PUT
    @Path("{id: \\d+}")
    public VideoDTO updateVideo(@PathParam("idAcceso") Long idAcceso,@PathParam("id") Long id, VideoDTO video) throws BusinessLogicException {
        validarAccesoAdmin(idAcceso);
        video.setId(id);
        VideoEntity entity = VideoLogic.getVideo(id);
        if (entity == null) 
        {
            throw new WebApplicationException(MENSAJE_ERROR + id + " no existe.", 404);
        }
        return new VideoDTO(VideoLogic.updateVideo(id, video.toEntity()));
    }

    @DELETE
    @Path("{VideosId: \\d+}")
    public void deleteVideo(@PathParam("idAcceso") Long idAcceso,@PathParam("VideosId") Long id) throws BusinessLogicException {
       validarAccesoAdmin(idAcceso);
        VideoEntity entity = VideoLogic.getVideo(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Videos/" + id + " no existe.", 404);
        }
        VideoLogic.deleteVideo(id);
    }
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos VideoEntity a una lista de
     * objetos VideoDTO (json)
     *
     * @param entityList corresponde a la lista de Videos de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Videos en forma DTO (json)
     */
    private List<VideoDTO> listEntity2DTO(List<VideoEntity> entityList) {
        List<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }

    private List<VideoDetailDTO> listEntity2DetailDTO(List<VideoEntity> entityList) {
        List<VideoDetailDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDetailDTO(entity));
        }
        return list;
    }
    
    private void validarAccesoAdmin(Long id)
    {
        if(id!=999)
            throw new WebApplicationException("Sólo un administrador del sistema puede realizar esta operación.");
    }
}