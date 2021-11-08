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
   private String nombreBanco;
   private boolean activo;
   private static int contador = 0;
   
   
    public Banco(String direccion, String registros, String jefeBanco, String nombreBanco) {
        this.nBanco = contador;
        this.direccion = direccion;
        this.registros = registros;
        this.jefeBanco = jefeBanco;
        this.nombreBanco = nombreBanco;
        this.activo = true;
        incrementarContador();
        
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }
    
    

    public static void setContador(int contador) {
        Banco.contador = contador;
    }
    
    
    
    private void incrementarContador(){
        contador++;
    }
    
    public int getnContador(){
        return contador;
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
