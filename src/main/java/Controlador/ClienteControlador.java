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
import modelo.Sucursal;

/**
 *
 * @author franm
 */
public class ClienteControlador {

    File f;
    FileOutputStream fOut;
    ObjectOutputStream dataOS;
    ArrayList<Sucursal> clientes = new ArrayList();
    Iterator it;
    ObjectInputStream dataIS;

    /**
     *
     */
    public ClienteControlador() {
    }

    public void añadirCliente(Sucursal s) {
        clientes.add(s);
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void EscribirCliente() throws FileNotFoundException, IOException {
        f = new File("Cliente.dat");
        fOut = new FileOutputStream(f, true);
        dataOS = new ObjectOutputStream(fOut);
        it = clientes.iterator();
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
        Sucursal s;
        f = new File("Cliente.dat");
        it = clientes.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        int i = 1;
        try {
            while (true) { // lectura del fichero
                s = (Sucursal) dataIS.readObject(); // leer un Banco
                System.out.print(i + "=>");
                i++;
                System.out.printf("Direccion " + s.getDireccion() + " JEFE " + s.getJefe_sucursal());

            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
    }

    public void leerToArray() throws FileNotFoundException, IOException, ClassNotFoundException {
        Sucursal s;
        f = new File("Cliente.dat");
        it = clientes.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) {
                s = (Sucursal) dataIS.readObject();
                añadirCliente(s);
            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }
    }
}
