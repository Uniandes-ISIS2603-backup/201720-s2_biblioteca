/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.PrestamoEntity;

/**
 *
 * @author f.posada
 */
public class PrestamoDetailDTO extends PrestamoDTO 
{
    public PrestamoDetailDTO ()
    {
    }
    
    public PrestamoDetailDTO(PrestamoEntity ent)
    {
        super(ent);
    }
    
    public PrestamoEntity toEntity()
    {
        PrestamoEntity preEnt=super.toEntity();
        return preEnt;    
    }
    
}
