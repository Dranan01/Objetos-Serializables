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
   private String fechaPrestamo;
   private int nPrestamo;
   private int dineroPrestado;
   private int intereses;
   private int nCliente;
   private boolean activo;
   private static int contador = 0;

    public Prestamo(String fechaPrestamo, int dineroPrestado, int intereses, int nCliente) {
        this.nPrestamo = contador;
        this.fechaPrestamo = fechaPrestamo;
        this.dineroPrestado = dineroPrestado;
        this.intereses = intereses;
        this.activo = true;
        this.nCliente = nCliente;
        incrementarContador();
    }

    public int getnCliente() {
        return nCliente;
    }

    public void setnCliente(int nCliente) {
        this.nCliente = nCliente;
    }
    
    
   public static void setContador(int contador) {
        Prestamo.contador = contador;
    }

    private void incrementarContador() {
        contador++;
    }

    public int getnContador() {
        return contador;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    

    public int getnPrestamo() {
        return nPrestamo;
    }
    

    public String getDineroPrestado() {
        return String.valueOf(dineroPrestado);
    }

    public void setDineroPrestado(int dineroPrestado) {
        this.dineroPrestado = dineroPrestado;
    }
    

    public String getIntereses() {
        return String.valueOf(intereses);
    }

    public void setIntereses(int intereses) {
        this.intereses = intereses;
    }
    
    

    public void setnPrestamo(int nPrestamo) {
        this.nPrestamo = nPrestamo;
    }
    

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    
    
    
}
