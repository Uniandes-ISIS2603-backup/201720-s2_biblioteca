/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author f.posada, cg.chavarro
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable
{
    private boolean completada;
    private int diaInicio;
    private int mesInicio;
    private int anioInicio;
    
     //@ManyToMany(mappedBy = "id", cascade = CascadeType.ALL)
    //private  List<RecursoEntity> recursos = new ArrayList<RecursoEntity>();

   // public List<RecursoEntity> getRecursos() {
   //     return recursos;
   // }

  //  public void setRecursos(List<RecursoEntity> recursos) {
   //     this.recursos = recursos;
    //}
     
    public boolean isCompletada()
    {return completada;}
    public void setCompletada(boolean pComplete)
    {completada=pComplete;}
    public int getDiaInicio()
    {return diaInicio;}
    public void setDiaInicio(int pDia)
    {diaInicio=pDia;}
     public int getMesInicio()
    {return mesInicio;}
    public void setMesInicio(int pMes)
    {mesInicio=pMes;}
     public int getAnioInicio()
    {return anioInicio;}
    public void setAnioInicio(int pAnio)
    {anioInicio=pAnio;}
    
}
