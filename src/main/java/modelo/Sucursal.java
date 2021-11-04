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
public class Sucursal implements Serializable{

    private int nSucursal;
    private String direccion;
    private String empresa;
    private String jefe_sucursal;
    private boolean activo;

    public Sucursal(int nSucursal, String direccion, String empresa, String jefe_sucursal, boolean activo) {
        this.nSucursal = nSucursal;
        this.direccion = direccion;
        this.empresa = empresa;
        this.jefe_sucursal = jefe_sucursal;
        this.activo = activo;
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

    @Override
    public String toString() {
        return "Sucursal{" + "nSucursal=" + nSucursal + ", direccion=" + direccion + ", empresa=" + empresa + ", jefe_sucursal=" + jefe_sucursal + ", activo=" + activo + '}';
    }
    
    

}
