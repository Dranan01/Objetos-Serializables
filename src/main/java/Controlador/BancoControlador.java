/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author usuario
 */
public class BancoControlador {

    File f;
    FileOutputStream fOut;
    ObjectOutputStream dataOS;
    ArrayList<Banco> bancos = new ArrayList();
    ObjectInputStream dataIS;
    Iterator it;

    public BancoControlador() throws FileNotFoundException, IOException {
       
    }

    public void añadirBanco(/*caracteristicas del banco al arrayList*/) {
    }

    public void EscribirBanco(ArrayList<Banco> bancos) throws FileNotFoundException, IOException {
        f = new File("Banco.dat");
        fOut = new FileOutputStream(f);
        dataOS = new ObjectOutputStream(fOut);
        it = bancos.iterator();
        while (it.hasNext()) {
            Banco b = (Banco) it.next();
            dataOS.writeObject(b);
        }
        dataOS.close();

    }

    public ArrayList leerBanco() throws FileNotFoundException, IOException, ClassNotFoundException {
        Banco b; // defino la variable banco
        
        File f = new File("Banco.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        int i = 1;
        try {
            while (true) { // lectura del fichero
                b = (Banco) dataIS.readObject(); // leer banco
                i++;
               bancos.add(b);

            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
        return bancos;
    }
    
    
    
}
