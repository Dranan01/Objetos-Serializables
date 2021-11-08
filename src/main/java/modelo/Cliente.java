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
   private String dni;
   private String nombre;
   private String apellido;
   private int nCliente;
   private boolean activo;
   private int nSucursal;
   private static int contador = 0;

    public Cliente(String dni, String nombre, String apellido, int nSucursal) {
        this.nCliente = contador;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = true;
        this.nSucursal = nSucursal;
        incrementarContador();
    }

    
    
    
    
    
    
    public int getnSucursal() {
        return nSucursal;
    }

    public void setnSucursal(int nSucursal) {
        this.nSucursal = nSucursal;
    }
    
    
    
    
     public static void setContador(int contador) {
        Cliente.contador = contador;
    }

    private void incrementarContador() {
        contador++;
    }

    public int getnContador() {
        return contador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getnCliente() {
        return nCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    
}
