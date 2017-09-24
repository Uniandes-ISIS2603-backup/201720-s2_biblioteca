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

    

   

    public MedioPagoDTO getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPagoDTO medioPago) {
        this.medioPago = medioPago;
    }
    
    
    public MultaDetailDTO(MultaEntity entity)
    {
        super(entity);
        medioPago = new MedioPagoDTO(entity.getMedioPago());
        
    }
    
    public MultaEntity toEntity()
    {
        MultaEntity multa = super.toEntity();
        multa.setMedioPago(medioPago.toEntity());      
        return multa;
    }
}
