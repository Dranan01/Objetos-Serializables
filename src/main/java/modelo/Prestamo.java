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
public class Prestamo implements Serializable{
    String fechaPrestamo;
    int nPrestamo;
    int dineroPrestado;
    int intereses;
    boolean activo;

    public Prestamo(String fechaPrestamo, int nPrestamo, int dineroPrestado, int intereses, boolean activo) {
        this.fechaPrestamo = fechaPrestamo;
        this.nPrestamo = nPrestamo;
        this.dineroPrestado = dineroPrestado;
        this.intereses = intereses;
        this.activo = activo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public int getnPrestamo() {
        return nPrestamo;
    }

    public int getDineroPrestado() {
        return dineroPrestado;
    }

    public int getIntereses() {
        return intereses;
    }

    public boolean isActivo() {
        return activo;
    }
    
    
    
    
    
}
