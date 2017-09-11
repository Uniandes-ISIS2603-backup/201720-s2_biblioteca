/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.dtos;

import co.edu.uniandes.quantum.biblioteca.entities.ReservaEntity;

/**
 *
 * @author cg.chavarro
 */
public class ReservaDTO 
{
    private Long id;
    private String name;
    private boolean completada;
    private int diaInicio;
    private int mesInicio;
    private int anioInicio;
    
    public ReservaDTO(ReservaEntity l)
    {
         this.id=l.getId();
        this.name =l.getName();
        this.completada=l.isCompletada();
        this.diaInicio=l.getDiaInicio();
        this.anioInicio=l.getAnioInicio();
        this.mesInicio=l.getMesInicio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(int mesInicio) {
        this.mesInicio = mesInicio;
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }
    
    public ReservaEntity toEntity()
    {
        ReservaEntity entity=new ReservaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
       entity.setCompletada(this.completada);
       entity.setDiaInicio(this.diaInicio);
       entity.setMesInicio(this.mesInicio);
       entity.setAnioInicio(this.anioInicio);
        return entity;
    }
    
}
