    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Banco implements Serializable{
    
    private int nBanco;
   private String direccion;
   private String registros;
   private String jefeBanco;
   private boolean activo;
   
   
    private static int contador;
    public Banco(String direccion, String registros, String jefeBanco) {
        this.nBanco = contador;
        this.direccion = direccion;
        this.registros = registros;
        this.jefeBanco = jefeBanco;
        this.activo = true;
        contador++;
        
    }

    public int getnBanco() {
        return nBanco;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRegistros() {
        return registros;
    }

    public String getJefeBanco() {
        return jefeBanco;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setnBanco(int nBanco) {
        this.nBanco = nBanco;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public void setJefeBanco(String jefeBanco) {
        this.jefeBanco = jefeBanco;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
    
}
