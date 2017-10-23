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

public class VideoDetailDTO extends VideoDTO
{
    private SalaDTO sala;
    private  BibliotecaDTO biblioteca;
    private PrestamoDTO prestamo;
    private ReservaDTO reserva;
    public VideoDetailDTO()
    {
        super();

    }

    public VideoDetailDTO(VideoEntity entity) {
        super(entity);
        if(entity.getMiBiblioteca()!=null)
        {
            this.biblioteca = new BibliotecaDTO(entity.getMiBiblioteca());
        }
        if(entity.getMiPrestamo()!=null)
            this.prestamo = new PrestamoDTO(entity.getMiPrestamo());
        if(entity.getMiReserva()!=null)
            this.reserva = new ReservaDTO(entity.getMiReserva());
        if(entity.getMiSala()!=null)
            this.sala = new SalaDTO(entity.getMiSala());
        
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
    @Override
    public VideoEntity toEntity() {
        VideoEntity s =super.toEntity();
        if(biblioteca!=null)
            s.setMiBiblioteca(this.biblioteca.toEntity());
        if(prestamo!=null)
            s.setMiPrestamo(this.prestamo.toEntity());
        if(reserva!=null)
            s.setMiReserva(this.reserva.toEntity());
        return s;
    }
}
