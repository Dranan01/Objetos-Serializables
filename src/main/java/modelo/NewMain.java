/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File("Banco.dat");
        File f2 = new File("Cliente.dat");
        File f3 = new File("Prestamo.dat");
        File f4 = new File("Sucursal.dat");
        
        FileOutputStream fOut = new FileOutputStream(f, true);
        ObjectOutputStream dataOS = new ObjectOutputStream(fOut);
        
        
        
        
        
    }
    
}
