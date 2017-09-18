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
public class VideoDetalDTO extends VideoDTO
{
    private SalaDTO sala;

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
    
    
    public VideoDetalDTO(VideoEntity l)
    {
        super(l);
        sala = new SalaDTO(l.getSala());
    }
    public VideoEntity toEntity()
    {
        VideoEntity preEnt=super.toEntity();
        preEnt.setSala(sala.toEntity());
        return preEnt;    
    }
}
