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
public class Cliente implements Serializable{
    String dni;
    String nombre;
    String apellido1;
    String apellido2;
    int nCliente;
    boolean activo;

    public Cliente(String dni, String nombre, String apellido1, String apellido2, int nCliente, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nCliente = nCliente;
        this.activo = activo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getnCliente() {
        return nCliente;
    }

    public boolean isActivo() {
        return activo;
    }
    
    
    
    
}
