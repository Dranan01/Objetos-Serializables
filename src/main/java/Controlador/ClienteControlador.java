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


/**
 *
 * @author franm
 */
public class ClienteControlador {

    File f;
    FileOutputStream fOut;
    ObjectOutputStream dataOS;
    ArrayList<Cliente> clientes = new ArrayList();
    Iterator it;
    ObjectInputStream dataIS;

    /**
     *
     */
    public ClienteControlador() {
    }

   

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void EscribirCliente(ArrayList<Cliente> clientes) throws FileNotFoundException, IOException {
        f = new File("Cliente.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        fOut = new FileOutputStream(f);
        dataOS = new ObjectOutputStream(fOut);
        it = clientes.iterator();
        while (it.hasNext()) {
            Cliente c = (Cliente) it.next();
            dataOS.writeObject(c);
        }
        dataOS.close();

    }
    

    public ArrayList leerCliente() throws FileNotFoundException, IOException, ClassNotFoundException {
        Cliente c; // defino la variable banco
        
        File f = new File("Cliente.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) { // lectura del fichero
               c = (Cliente) dataIS.readObject(); // leer banco
               clientes.add(c);

            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
        return clientes;
    }
}
