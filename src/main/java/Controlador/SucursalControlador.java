/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Sucursal;

/**
 *
 * @author franm
 */
public class SucursalControlador {
    File f;
    FileOutputStream fOut;
    ObjectOutputStream dataOS;
    ArrayList<Sucursal> sucursales = new ArrayList();
    Iterator it;
    ObjectInputStream dataIS;

    /**
     * 
     */
    public SucursalControlador() {
    }

     /**
     *
     * @param sucursales
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void EscribirSucursal(ArrayList<Sucursal> sucursales) throws FileNotFoundException, IOException {
        f = new File("Sucursal.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        fOut = new FileOutputStream(f);
        dataOS = new ObjectOutputStream(fOut);
        it = sucursales.iterator();
        while (it.hasNext()) {
            Sucursal s = (Sucursal) it.next();
            dataOS.writeObject(s);
        }
        dataOS.close();

    }
    

    public ArrayList leerSucursal() throws FileNotFoundException, IOException, ClassNotFoundException {
        Sucursal s; // defino la variable banco
        
        File f = new File("Sucursal.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) { // lectura del fichero
               s = (Sucursal) dataIS.readObject(); // leer banco
               sucursales.add(s);

            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
        return sucursales;
    }
    
    
}
