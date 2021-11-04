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
    
    int nBanco;
    String direccion;
    String registros;
    String jefeBanco;
    boolean activo;

    public Banco(int nBanco, String direccion, String registros, String jefeBanco) {
        this.nBanco = nBanco;
        this.direccion = direccion;
        this.registros = registros;
        this.jefeBanco = jefeBanco;
        this.activo = true;
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
    
    
    
    
}
