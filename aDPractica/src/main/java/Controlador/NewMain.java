/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import modelo.Banco;

/**
 *
 * @author usuario
 */
public class NewMain {
    Banco b;
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
         File f = new File("ficheroPrueba.dat");
        FileOutputStream fOut = new FileOutputStream(f, true);
        ObjectOutputStream dataOS = new ObjectOutputStream(fOut);
       BancoControlador a = new BancoControlador();
        
         Banco c = new Banco(1,"sdfasdfsa","sadfsdafsaf","xcvbzxvxcv");
         
         //a.añadirBanco(c);
         //a.EscribirBanco();
           a.leerBanco();
        
           //jtable.repaint(); Repinta las celdas de la tabla

        
    }
    
}
