/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;

/**
 *
 * @author jp.sanmiguel
 */
public class MultaDetailDTO extends MultaDTO{

    private MedioPagoDTO medioPago;

    private PrestamoDTO prestamo;

    private UsuarioDTO usuario;

    public MedioPagoDTO getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPagoDTO medioPago) {
        this.medioPago = medioPago;
    }

    public PrestamoDTO getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(PrestamoDTO prestamo) {
        this.prestamo = prestamo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    public MultaDetailDTO(MultaEntity entity)
    {
        super(entity);
        medioPago = new MedioPagoDTO(entity.getMedioPago());
        prestamo = new PrestamoDTO(entity.getPrestamo());
        usuario = new UsuarioDTO(entity.getMiUsuario());
    }
    
    public MultaEntity toEntity()
    {
        MultaEntity multa = super.toEntity();
        multa.setMedioPago(medioPago.toEntity());
        multa.setPrestamo(prestamo.toEntity());
        multa.setMiUsuario(usuario.toEntity());
        return multa;
    }
}
