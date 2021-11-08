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
import java.util.ArrayList;
import java.util.Iterator;
import modelo.Banco;
import modelo.Cliente;
import modelo.Prestamo;
import modelo.Sucursal;

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
       // File f = new File("Bancos.dat");
        File f = new File("Banco.dat");
        File f2 = new File("Cliente.dat");
        File f3 = new File("Prestamo.dat");
        File f4 = new File("Sucursal.dat");
    ArrayList<Banco> bancos;
    ArrayList<Sucursal> sucursales;
    ArrayList<Prestamo> prestamos;
    ArrayList<Cliente> clientes;
    BancoControlador bc = new BancoControlador(); 
    ClienteControlador cc = new ClienteControlador();
    PrestamoControlador pc = new PrestamoControlador();
    SucursalControlador sc = new SucursalControlador();
         
         Banco b = new Banco("Calle malaga","registros1","Monoxido","Caixa");
         Banco b2 = new Banco("Calle Granada","registros2","Peroxido","cajaRural");
         Sucursal s = new Sucursal("calle Alfeizar","BancosSL","Patata", 0);
         Prestamo p = new Prestamo("Potasio", 100, 5, 0 );
         Prestamo p2 = new Prestamo("Monocromo", 200, 1, 1 );
         Cliente c = new Cliente("88512364X","Alferez", "Cabo",0);
         Cliente c2 = new Cliente("44587415X","General","Raso",1);
         Sucursal s2 = new Sucursal("calle Parasyta","BancosSinSL","Patatita",1);
         bancos = new ArrayList();
         sucursales = new ArrayList();
         prestamos = new ArrayList();
         clientes = new ArrayList();
         
         bancos.add(b);
         sucursales.add(s);
         prestamos.add(p);
         clientes.add(c);
         bancos.add(b2);
         sucursales.add(s2);
         prestamos.add(p2);
         clientes.add(c2);
         
         
         bc.EscribirBanco(bancos);
         cc.EscribirCliente(clientes);
         pc.EscribirPrestamo(prestamos);
         sc.EscribirSucursal(sucursales);
         
         

         
         
         
        /* 
         //a.añadirBanco(c);
         bancos = a.leerBanco();
         Iterator it = bancos.iterator();
         while (it.hasNext()) {
            c = (Banco) it.next();
             System.out.println(c.getnBanco());
             System.out.println(x.getnBanco());

             
            
        }*/
           //jtable.repaint(); Repinta las celdas de la tabla

        
    }
    }
    

