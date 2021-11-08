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
public class Sucursal implements Serializable {

    private int nSucursal;
    private String direccion;
    private String empresa;
    private String jefe_sucursal;
    private boolean activo;
    private int nBanco;
    
    private static int contador = 0;

    public Sucursal(String direccion, String empresa, String jefe_sucursal, int nBanco) {
        this.nSucursal = contador;
        this.direccion = direccion;
        this.empresa = empresa;
        this.jefe_sucursal = jefe_sucursal;
        this.activo = true;
        this.nBanco = nBanco;
        incrementarContador();
    }

    public int getnBanco() {
        return nBanco;
    }
    
    
    
    
    public static void setContador(int contador) {
        Sucursal.contador = contador;
    }

    private void incrementarContador() {
        contador++;
    }

    public int getnContador() {
        return contador;
    }

    public int getnSucursal() {
        return nSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getJefe_sucursal() {
        return jefe_sucursal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setJefe_sucursal(String jefe_sucursal) {
        this.jefe_sucursal = jefe_sucursal;
    }

    public void setnBanco(int nBanco) {
        this.nBanco = nBanco;
    }
    
    

    @Override
    public String toString() {
        return "Sucursal{" + "nSucursal=" + nSucursal + ", direccion=" + direccion + ", empresa=" + empresa + ", jefe_sucursal=" + jefe_sucursal + ", activo=" + activo + '}';
    }

}
