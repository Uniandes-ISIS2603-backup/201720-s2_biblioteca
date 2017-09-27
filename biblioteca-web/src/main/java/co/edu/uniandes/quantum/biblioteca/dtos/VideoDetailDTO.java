/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;

/**
 *
 * @author cg.chavarro
 */

//TODO se debe implementar esta clase
public class VideoDetailDTO extends VideoDTO
{
    private SalaDTO sala;

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
    
    public VideoDetailDTO()
    {
        super();
    }
    
    
    public VideoDetailDTO(VideoEntity l)
    {
        super(l);
        if(l!=null){
        sala = new SalaDTO(l.getSala());}
        else
            throw new NullPointerException("El video fue nula");
    }
    public VideoEntity toEntity()
    {
        VideoEntity preEnt=super.toEntity();
        preEnt.setSala(sala.toEntity());
        return preEnt;    
    }
}
