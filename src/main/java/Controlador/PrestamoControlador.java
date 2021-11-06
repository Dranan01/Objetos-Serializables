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
import modelo.Cliente;
import modelo.Prestamo;
import modelo.Sucursal;

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

    public void añadirPrestamo(Prestamo p) {
        prestamos.add(p);
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void escribirPrestamo() throws FileNotFoundException, IOException {
        f = new File("Prestamo.dat");
        fOut = new FileOutputStream(f, true);
        dataOS = new ObjectOutputStream(fOut);
        it = prestamos.iterator();
        while (it.hasNext()) {
            Cliente c = (Cliente) it.next();
            dataOS.writeObject(c);
        }
        dataOS.close();

    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void leerCliente() throws FileNotFoundException, IOException, ClassNotFoundException {
        Prestamo p;
        f = new File("Prestamo.dat");
        it = prestamos.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        int i = 1;
        try {
            while (true) { // lectura del fichero
                p = (Prestamo) dataIS.readObject(); // leer un Banco
                System.out.print(i + "=>");
                i++;
                System.out.printf("Direccion " + p.getIntereses() + " JEFE " + p.getDineroPrestado());

            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
    }

    public void leerToArray() throws FileNotFoundException, IOException, ClassNotFoundException {
        Prestamo p;
        f = new File("Prestamo.dat");
        it = prestamos.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) {
                p = (Prestamo) dataIS.readObject();
                añadirPrestamo(p);
            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }
    }
}
