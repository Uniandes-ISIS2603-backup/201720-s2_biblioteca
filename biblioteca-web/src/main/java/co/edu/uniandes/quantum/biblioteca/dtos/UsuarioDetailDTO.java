/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.UsuarioEntity;

/**
 *
 * @author f.posada
 */
public class UsuarioDetailDTO extends UsuarioDTO
{
    public UsuarioDetailDTO ()
    {
    }
    
    public UsuarioDetailDTO(UsuarioEntity ent)
    {
        super(ent);
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity usEnt=super.toEntity();
        return usEnt;    
    }
    
}
