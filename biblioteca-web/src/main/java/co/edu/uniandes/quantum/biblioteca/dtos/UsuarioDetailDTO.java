/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.ComentarioEntity;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;
import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;
import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f.posada
 */
public class UsuarioDetailDTO extends UsuarioDTO
{
    private List<MultaDTO> multas;
    private List<PrestamoDTO> prestamos;
    private List<ComentarioDTO> comentarios;
    private List<ReservaDTO> reservas;
    private MedioPagoDTO medioPago;

    public List<MultaDTO> getMultas() {
        return multas;
    }

    public void setMultas(List<MultaDTO> multas) {
        this.multas = multas;
    }

    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<ReservaDTO> getreservas() {
        return reservas;
    }

    public void setreservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public MedioPagoDTO getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPagoDTO medioPago) {
        this.medioPago = medioPago;
    }
    
    
    public UsuarioDetailDTO ()
    {
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        if (entity.getPrestamos() != null) {
            prestamos = new ArrayList<>();
            for (PrestamoEntity entityPrestamo : entity.getPrestamos()) {
                prestamos.add(new PrestamoDTO(entityPrestamo));
            }
        }
        if (entity.getMultas() != null) {
            multas = new ArrayList<>();
            for (MultaEntity entityMulta : entity.getMultas()) {
                multas.add(new MultaDTO(entityMulta));
            }
        }
        if (entity.getComentarios() != null) {
            comentarios = new ArrayList<>();
            for (ComentarioEntity entityComentario : entity.getComentarios()) {
                comentarios.add(new ComentarioDTO(entityComentario));
            }
        }
        if (entity.getReservas() != null) {
            reservas = new ArrayList<>();
            for (ReservaEntity entityReserva : entity.getReservas()) {
                reservas.add(new ReservaDTO(entityReserva));
            }
        }
        
        
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity usEnt=super.toEntity();
        if (getMultas() != null) {
            List<MultaEntity> multasEntity = new ArrayList<>();
            for (MultaDTO dtoMulta : getMultas()) {
                multasEntity.add(dtoMulta.toEntity());
            }
            usEnt.setMultas(multasEntity);
        }
        if (getreservas() != null) {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for (ReservaDTO dtoReserva : getreservas()) {
                reservasEntity.add(dtoReserva.toEntity());
            }
            usEnt.setReservas(reservasEntity);
        }
        if (getPrestamos() != null) {
            List<PrestamoEntity> prestamosEntity = new ArrayList<>();
            for (PrestamoDTO dtoReserva : getPrestamos()) {
                prestamosEntity.add(dtoReserva.toEntity());
            }
            usEnt.setPrestamos(prestamosEntity);
        }
        if (getComentarios() != null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO dtoReserva : getComentarios()) {
                comentariosEntity.add(dtoReserva.toEntity());
            }
            usEnt.setComentarios(comentariosEntity);
        }
        return usEnt;    
    }
    
}
