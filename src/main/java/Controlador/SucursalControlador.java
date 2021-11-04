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
import modelo.Banco;
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

    

    
    
    
    public void añadirSucursal(Sucursal s){
        
        sucursales.add(s);
        
    }
    
    /**
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void EscribirSucursal() throws FileNotFoundException, IOException{
        f = new File("Sucursal.dat");
        fOut = new FileOutputStream(f,true);
        dataOS = new ObjectOutputStream(fOut);
        it = sucursales.iterator();
        while(it.hasNext()){
            Sucursal s = (Sucursal) it.next();
            dataOS.writeObject(s);
        }
            dataOS.close();
        
    }
    
    /**
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void leerBanco() throws FileNotFoundException, IOException, ClassNotFoundException{
        Sucursal s;
        f = new File("Sucursal.dat");
        it = sucursales.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        int i = 1;
		try {
			while (true) { // lectura del fichero
				s = (Sucursal) dataIS.readObject(); // leer un Banco
				System.out.print(i + "=>");
				i++;
				System.out.printf("Direccion "  +s.getDireccion() + " JEFE " + s.getJefe_sucursal());

			}
		} catch (EOFException eo) {
			System.out.println("\n FIN DE LECTURA.");
		} catch (StreamCorruptedException x) {
		}

		dataIS.close(); // cerrar stream de entrada
	}
    
    public void leerToArray() throws FileNotFoundException, IOException, ClassNotFoundException {
        Sucursal s;
        f = new File("Sucursal.dat");
        it = sucursales.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) {
                s = (Sucursal) dataIS.readObject();
                añadirSucursal(s);
            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }
    }
    
    
}
