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
    Iterator it;
    ObjectInputStream dataIS;

    public BancoControlador() {
    }

    
    
    
    public void añadirBanco(Banco b){
        
        bancos.add(b);
        
    }
    
    public void EscribirBanco() throws FileNotFoundException, IOException{
        f = new File("Banco.dat");
        fOut = new FileOutputStream(f,true);
        dataOS = new ObjectOutputStream(fOut);
        it = bancos.iterator();
        while(it.hasNext()){
            Banco b = (Banco) it.next();
            dataOS.writeObject(b);
        }
            dataOS.close();
        
    }
    
    public void leerBanco() throws FileNotFoundException, IOException, ClassNotFoundException{
        Banco b;
        f = new File("Banco.dat");
        it = bancos.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        int i = 1;
		try {
			while (true) { // lectura del fichero
				b = (Banco) dataIS.readObject(); // leer un Banco
				System.out.print(i + "=>");
				i++;
				System.out.printf("Direccion "  +b.getDireccion() + " JEFE " + b.getJefeBanco());

			}
		} catch (EOFException eo) {
			System.out.println("\n FIN DE LECTURA.");
		} catch (StreamCorruptedException x) {
		}

		dataIS.close(); // cerrar stream de entrada
	}
    
    public void leerToArray() throws FileNotFoundException, IOException, ClassNotFoundException {
        Banco b;
        f = new File("Banco.dat");
        it = bancos.iterator();
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(f));
        try {
            while (true) {
                b = (Banco) dataIS.readObject();
                añadirBanco(b);
            }
        } catch (EOFException eo) {
            System.out.println("\n FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }
    }
    
    
    
}
