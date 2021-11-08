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
import modelo.Prestamo;

/**
 *
 * @author franm
 */
public class PrestamoControlador {
     /**
     *
     */
        File f;
    FileOutputStream fOut;
    ObjectOutputStream dataOS;
    ArrayList<Prestamo> prestamos = new ArrayList();
    Iterator it;
    ObjectInputStream dataIS;
    public PrestamoControlador() {
    }

     /**
     *
     * @param sucursales
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void EscribirPrestamo(ArrayList<Prestamo> prestamos) throws FileNotFoundException, IOException {
        f = new File("Prestamo.dat");
        fOut = new FileOutputStream(f);
        dataOS = new ObjectOutputStream(fOut);
        it = prestamos.iterator();
        while (it.hasNext()) {
            Prestamo p = (Prestamo) it.next();
            dataOS.writeObject(p);
        }
        dataOS.close();

    }
    

    public ArrayList leerPrestamo() throws FileNotFoundException, IOException, ClassNotFoundException {
        Prestamo p; // defino la variable banco
        
        File f = new File("Prestamo.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) { // lectura del fichero
               p = (Prestamo) dataIS.readObject(); // leer banco
               prestamos.add(p);

            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
        return prestamos;
    }
}
