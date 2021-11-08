/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.BancoControlador;
import Controlador.ClienteControlador;
import Controlador.PrestamoControlador;
import Controlador.SucursalControlador;
import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Banco;
import modelo.Cliente;
import modelo.Prestamo;
import modelo.Sucursal;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Fran
 */
public class NewJFrame extends javax.swing.JFrame {
ArrayList<Banco> bancos;
ArrayList<Sucursal> sucursales;
ArrayList<Prestamo> prestamos;
ArrayList<Cliente> clientes;
BancoControlador bc = new BancoControlador(); 
ClienteControlador cc = new ClienteControlador();
PrestamoControlador pc = new PrestamoControlador();
SucursalControlador sc = new SucursalControlador();
    


    public void limpiarTextoBanco(){
        jTextField_direccion_banco.setText("");
        jTextField_jefeBanco.setText("");
        jTextField_registros.setText("");
    }
    
    public void limpiarTextoSucursal(){
        jTextField_Direccion_Sucursal.setText("");
        jTextField_Empresa_Sucursal.setText("");
        jTextField_Jefe_Sucursal.setText("");
   }
    
    public void limpiarTextoCliente(){
        jTextField_Nombre_Cliente.setText("");
        jTextField_cliente_apellido.setText("");
        jTextField_Cliente_Dni.setText("");
    }
    
    public void limpiarTextoPrestamo(){
        jTextField_prestamo_dinero.setText("");
        jTextField_prestamo_fecha_prestamo.setText("");
        jTextField_prestamo_intereses.setText("");
    }
    
    public void cargarTablaBancos(){
        
        String matriz[][] = new String[bancos.size()][4];
    
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).isActivo() == true) {
                
            
            matriz[i][0] = bancos.get(i).getDireccion();
            matriz[i][1] = bancos.get(i).getJefeBanco();
            matriz[i][2] = bancos.get(i).getRegistros();
            matriz[i][3] = bancos.get(i).getNombreBanco();
        
        
    jTable_Banco.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Direccion", "Registros", "Jefe Banco", "Nombre Banco"
            }
                    ));
   
     }
        }
}
    
    
    public void cargarTablaClientes(){
        
        String matriz[][] = new String[clientes.size()][4];
        
        for (int i = 0; i < clientes.size(); i++) {
            int id;
            if (clientes.get(i).isActivo() == true) {
            id = clientes.get(i).getnSucursal();
            
            matriz[i][0] = clientes.get(i).getApellido();
            matriz[i][1] = clientes.get(i).getNombre();
            matriz[i][2] = clientes.get(i).getDni();
            matriz[i][3] = getNombreSucursal(id);
            
        
        
    jTable_cliente.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Apellido", "Nombre", "DNI", "Sucursal"
            }
                    ));
   
     }
        }
    }
    
    public void cargarTablaPrestamos(){
        String matriz[][] = new String[prestamos.size()][4];
        int id;
                
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).isActivo() == true) {
            id = prestamos.get(i).getnCliente();
            
            matriz[i][0] = prestamos.get(i).getFechaPrestamo();
            matriz[i][1] = prestamos.get(i).getDineroPrestado();
            matriz[i][2] = prestamos.get(i).getIntereses();
            matriz[i][3] = getNombreCliente(id);    
        
        
    jTable_prestamo.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Fecha prestamo", "Dinero prestado", "Intereses", "Cliente"
            }
                    ));
   
     }
        }
    }
    
    
    public void cargarTablaSucursal(){
         String matriz[][] = new String[sucursales.size()][4];
        int id;
        for (int i = 0; i < sucursales.size(); i++) {
            
            if (sucursales.get(i).isActivo() == true) {
            id = sucursales.get(i).getnBanco();
            
            matriz[i][0] = sucursales.get(i).getDireccion();
            matriz[i][1] = sucursales.get(i).getEmpresa();
            matriz[i][2] = sucursales.get(i).getJefe_sucursal();
            matriz[i][3] = getNombreBanco(id);
            
        
        
    jTable_Sucursal.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Direccion", "Empresa", "Jefe Sucursal", "Banco"
            }
                    ));
   
     }
        }
    }
    
    public void rellenarComboBoxSucursal() throws IOException, FileNotFoundException, ClassNotFoundException{
        bancos = bc.leerBanco();
        Banco b;
        Iterator it = bancos.iterator();
        jComboBox_Sucursal_to_Banco.removeAllItems();
        while(it.hasNext()){
            b = (Banco) it.next();
            jComboBox_Sucursal_to_Banco.addItem(b.getNombreBanco());
            
        }
        bancos.clear();
    }
   
    public void rellenarComboBoxCliente()throws IOException, FileNotFoundException, ClassNotFoundException{
        sucursales = sc.leerSucursal();
        Sucursal s;
        Iterator it = sucursales.iterator();
        jComboBox_Cliente_to_Sucursal.removeAllItems();
        while(it.hasNext()){
            s = (Sucursal) it.next();
            jComboBox_Cliente_to_Sucursal.addItem(s.getEmpresa());
            
        }
        sucursales.clear();
    }
    
    public void rellenarComboBoxPrestamo() throws IOException, FileNotFoundException, ClassNotFoundException{
         clientes = cc.leerCliente();
        Cliente c;
        Iterator it = clientes.iterator();
        jComboBox_prestamo_to_cliente.removeAllItems();
        while(it.hasNext()){
            c = (Cliente) it.next();
            jComboBox_prestamo_to_cliente.addItem(c.getNombre());
           }
        clientes.clear();
    }
    
    
    
    
    

    public void añadirATablaBanco(){
        String[] array = new String[3];
        
        array[0] = jTextField_direccion_banco.getText();
        array[1] = jTextField_registros.getText();
        array[2] = jTextField_jefeBanco.getText();
        array[3] = jTextField_Nombre_Banco.getText();
        
        DefaultTableModel dt = (DefaultTableModel) jTable_Banco.getModel();
        dt.addRow(array);
    }
    
    
    public void añadirATablaSucursal(){ //direccion empresa jefe Banco
        String[] array = new String[4];
        
        array[0] = jTextField_Direccion_Sucursal.getText();
        array[1] = jTextField_Empresa_Sucursal.getText();
        array[2] = jTextField_Jefe_Sucursal.getText();
        array[3] = jComboBox_Sucursal_to_Banco.getSelectedItem().toString();
        
        DefaultTableModel dt = (DefaultTableModel) jTable_Sucursal.getModel();
        dt.addRow(array);
    }
    
    public void añadirATablaCliente(){ // DNI NOMBRE APELLIDO SUCURSAL
        String[] array = new String[4];
        
        array[0] = jTextField_Cliente_Dni.getText();
        array[1] = jTextField_Nombre_Cliente.getText();
        array[2] = jTextField_cliente_apellido.getText();
        array[3] = jComboBox_Cliente_to_Sucursal.getSelectedItem().toString();
        
        DefaultTableModel dt = (DefaultTableModel) jTable_cliente.getModel();
        dt.addRow(array);
        
    }
    
    public void añadirATablaPrestamo(){ //fecha dineroprestado intereses cliente
        String array[] = new String[4];
        
        array[0] = jTextField_prestamo_fecha_prestamo.getText();
        array[1] = jTextField_prestamo_dinero.getText();
        array[2] = jTextField_prestamo_intereses.getText();
        array[3] = jComboBox_prestamo_to_cliente.getSelectedItem().toString();
        
        
        
        
    }
    
    
    
    public String getNombreBanco(int id){
        String nombre ="";
        Banco b;
        Iterator it = bancos.iterator();
        while(it.hasNext()) {
            b = (Banco) it.next();
             if(b.getnBanco() == id) {
                nombre = b.getNombreBanco();
            }
    }
        return nombre;
    }
    
    
    public String getNombreSucursal(int id){
        String nombre ="";
        Sucursal b;
        Iterator it = sucursales.iterator();
        while(it.hasNext()) {
            b = (Sucursal) it.next();
             if(b.getnSucursal()== id) {
                nombre = b.getEmpresa();
            }
    }
        return nombre;
    }
        
    public String getNombreCliente(int id){
        String nombre ="";
        Cliente b;
        Iterator it = clientes.iterator();
        while(it.hasNext()) {
            b = (Cliente) it.next();
             if(b.getnCliente()== id) {
                nombre = b.getNombre();
            }
    }
        return nombre;
        
    }
    
    
    public int getIdPorNombreBanco(String nombre){
        int id = 0 ;
        Banco b;
        Iterator it = bancos.iterator();
        while(it.hasNext()) {
            b = (Banco) it.next();
             if(b.getNombreBanco().equals(nombre)) {
                id = b.getnBanco();
            }
    }
        return id;
    }
   
    public int getIdPorNombreSucursal(String nombre){
        int id = 0;
        Sucursal b;
        Iterator it = sucursales.iterator();
        while(it.hasNext()) {
            b = (Sucursal) it.next();
             if(b.getEmpresa().equals(nombre)) {
                id = b.getnSucursal();
            }
    }
        return id;
    }
    
    
    public int getIdPorNombreCliente(String nombre){
        int id = 0;
        Cliente b;
        Iterator it = clientes.iterator();
        while(it.hasNext()) {
            b = (Cliente) it.next();
             if(b.getNombre().equals(nombre)) {
                id = b.getnCliente();
            }
    }
        return id;
        
        
    }
    
    
    
    
        
    public int getIDDesdeTablaBanco(){
        int id = 0;
        Banco b;
        int fila;
        fila = jTable_Banco.getSelectedRow();
        String registros = jTable_Banco.getValueAt(fila,2).toString();
        String jefe_Banco = jTable_Banco.getValueAt(fila,1).toString();
        Iterator it = bancos.iterator();
        while(it.hasNext()) {
            b = (Banco) it.next();
            String registrosb = b.getRegistros();
            String jefeBancob = b.getJefeBanco();
            
            
            if (registros.equals(registrosb) && jefe_Banco.equals(jefeBancob)) {
                id = b.getnBanco();
            }
            
    }
        return id;
    }
    
    
    

    /**
     * Creates new form Ventana1
     */
    /**
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws NotSerializableException
     * @throws SAXException 
     */
    public NewJFrame() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException {
        initComponents();
        rellenarComboBoxSucursal();
        rellenarComboBoxPrestamo();
        rellenarComboBoxCliente();
        
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NewJFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_banco = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Banco = new javax.swing.JTable();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_direccion = new javax.swing.JLabel();
        jTextField_direccion_banco = new javax.swing.JTextField();
        jTextField_registros = new javax.swing.JTextField();
        jButton_guardar_banco = new javax.swing.JButton();
        jButton_cancelar_banco = new javax.swing.JButton();
        jButton_aniadir_banco = new javax.swing.JButton();
        jButton_modificar_banco = new javax.swing.JButton();
        jButton_borrar_banco = new javax.swing.JButton();
        jTextField_jefeBanco = new javax.swing.JTextField();
        jLabel_direccion1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Nombre_Banco = new javax.swing.JTextField();
        jPanel_Sucursal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sucursal = new javax.swing.JTable();
        jButton_aniadir_sucursal = new javax.swing.JButton();
        jButton_modificar_sucursal = new javax.swing.JButton();
        jButton_borrar_sucursal = new javax.swing.JButton();
        jLabel_pais = new javax.swing.JLabel();
        jLabel_num_habitantes = new javax.swing.JLabel();
        jTextField_Direccion_Sucursal = new javax.swing.JTextField();
        jTextField_Jefe_Sucursal = new javax.swing.JTextField();
        jButton_guardar_sucursal = new javax.swing.JButton();
        jButton_cancelar_sucursal = new javax.swing.JButton();
        jTextField_Empresa_Sucursal = new javax.swing.JTextField();
        jLabel_num_habitantes1 = new javax.swing.JLabel();
        jComboBox_Sucursal_to_Banco = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel_Cliente = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_cliente = new javax.swing.JTable();
        jLabel_nombre_libro = new javax.swing.JLabel();
        jLabel_autor = new javax.swing.JLabel();
        jLabel_edad_recomendada = new javax.swing.JLabel();
        jTextField_cliente_apellido = new javax.swing.JTextField();
        jTextField_Cliente_Dni = new javax.swing.JTextField();
        jTextField_Nombre_Cliente = new javax.swing.JTextField();
        jButton_guardar_cliente = new javax.swing.JButton();
        jButton_cancelar_cliente = new javax.swing.JButton();
        jButton_borrar_cliente = new javax.swing.JButton();
        jButton_modificar_cliente = new javax.swing.JButton();
        jButton_aniadir_cliente = new javax.swing.JButton();
        jComboBox_Cliente_to_Sucursal = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel_prestamo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_prestamo = new javax.swing.JTable();
        jLabel_nombre_persona = new javax.swing.JLabel();
        jLabel_edad_persona = new javax.swing.JLabel();
        jTextField_prestamo_dinero = new javax.swing.JTextField();
        jTextField_prestamo_fecha_prestamo = new javax.swing.JTextField();
        jButton_guardar_prestamo = new javax.swing.JButton();
        jButton_cancelar_prestamo = new javax.swing.JButton();
        jButton_borrar_prestamo = new javax.swing.JButton();
        jButton_modificar_prestamo = new javax.swing.JButton();
        jButton_aniadir_prestamo = new javax.swing.JButton();
        jLabel_ciudad_natal = new javax.swing.JLabel();
        jTextField_prestamo_intereses = new javax.swing.JTextField();
        jComboBox_prestamo_to_cliente = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable_Banco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Direccion", "Registros", "Jefe Banco", "Nombre Banco"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Banco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BancoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Banco);

        jLabel_telefono.setText("Dirección:");

        jLabel_direccion.setText("Jefe Banco:");

        jTextField_direccion_banco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_direccion_bancoFocusLost(evt);
            }
        });
        jTextField_direccion_banco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField_direccion_bancoMouseExited(evt);
            }
        });
        jTextField_direccion_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_direccion_bancoActionPerformed(evt);
            }
        });
        jTextField_direccion_banco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_direccion_bancoKeyPressed(evt);
            }
        });

        jButton_guardar_banco.setText("Guardar");
        jButton_guardar_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_bancoActionPerformed(evt);
            }
        });

        jButton_cancelar_banco.setText("Cancelar");
        jButton_cancelar_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_bancoActionPerformed(evt);
            }
        });

        jButton_aniadir_banco.setText("Añadir");
        jButton_aniadir_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_bancoActionPerformed(evt);
            }
        });

        jButton_modificar_banco.setText("Modificar");
        jButton_modificar_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_bancoActionPerformed(evt);
            }
        });

        jButton_borrar_banco.setText("Borrar");
        jButton_borrar_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_bancoActionPerformed(evt);
            }
        });

        jLabel_direccion1.setText("Registros:");

        jLabel4.setText("Nombre Banco:");

        javax.swing.GroupLayout jPanel_bancoLayout = new javax.swing.GroupLayout(jPanel_banco);
        jPanel_banco.setLayout(jPanel_bancoLayout);
        jPanel_bancoLayout.setHorizontalGroup(
            jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bancoLayout.createSequentialGroup()
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_bancoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_bancoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_telefono)
                            .addComponent(jLabel_direccion)
                            .addComponent(jLabel_direccion1)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_registros)
                            .addComponent(jTextField_jefeBanco)
                            .addComponent(jTextField_direccion_banco)
                            .addComponent(jTextField_Nombre_Banco))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_guardar_banco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_cancelar_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(171, 171, 171)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_bancoLayout.setVerticalGroup(
            jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bancoLayout.createSequentialGroup()
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_bancoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_banco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_banco))
                    .addGroup(jPanel_bancoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono)
                    .addComponent(jTextField_direccion_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_banco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_registros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_direccion1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_jefeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_direccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_Nombre_Banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_banco))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Banco", jPanel_banco);

        jTable_Sucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dirección", "Empresa Sucursal", "Jefe Sucursal", "Banco"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Sucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SucursalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Sucursal);
        if (jTable_Sucursal.getColumnModel().getColumnCount() > 0) {
            jTable_Sucursal.getColumnModel().getColumn(0).setResizable(false);
        }

        jButton_aniadir_sucursal.setText("Añadir");
        jButton_aniadir_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_sucursalActionPerformed(evt);
            }
        });

        jButton_modificar_sucursal.setText("Modificar");
        jButton_modificar_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_sucursalActionPerformed(evt);
            }
        });

        jButton_borrar_sucursal.setText("Borrar");
        jButton_borrar_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_sucursalActionPerformed(evt);
            }
        });

        jLabel_pais.setText("Dirección:");

        jLabel_num_habitantes.setText("Empresa:");

        jButton_guardar_sucursal.setText("Guardar");
        jButton_guardar_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_sucursalActionPerformed(evt);
            }
        });

        jButton_cancelar_sucursal.setText("Cancelar");
        jButton_cancelar_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_sucursalActionPerformed(evt);
            }
        });

        jLabel_num_habitantes1.setText("Jefe Sucursal:");

        jComboBox_Sucursal_to_Banco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Banco:");

        javax.swing.GroupLayout jPanel_SucursalLayout = new javax.swing.GroupLayout(jPanel_Sucursal);
        jPanel_Sucursal.setLayout(jPanel_SucursalLayout);
        jPanel_SucursalLayout.setHorizontalGroup(
            jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_num_habitantes1)
                            .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_num_habitantes)
                                .addComponent(jLabel_pais)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Jefe_Sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField_Empresa_Sucursal)
                            .addComponent(jTextField_Direccion_Sucursal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_cancelar_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_guardar_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Sucursal_to_Banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_SucursalLayout.setVerticalGroup(
            jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_sucursal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_sucursal))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_pais)
                    .addComponent(jButton_guardar_sucursal)
                    .addComponent(jTextField_Direccion_Sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addComponent(jLabel_num_habitantes)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addComponent(jTextField_Empresa_Sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_num_habitantes1)
                    .addComponent(jTextField_Jefe_Sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_sucursal))
                .addGap(38, 38, 38)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_Sucursal_to_Banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Sucursal", jPanel_Sucursal);

        jTable_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_clienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_cliente);
        if (jTable_cliente.getColumnModel().getColumnCount() > 0) {
            jTable_cliente.getColumnModel().getColumn(0).setResizable(false);
            jTable_cliente.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_nombre_libro.setText("DNI:");

        jLabel_autor.setText("Nombre:");

        jLabel_edad_recomendada.setText("Apellido:");

        jTextField_Cliente_Dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Cliente_DniActionPerformed(evt);
            }
        });

        jButton_guardar_cliente.setText("Guardar");
        jButton_guardar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_clienteActionPerformed(evt);
            }
        });

        jButton_cancelar_cliente.setText("Cancelar");
        jButton_cancelar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_clienteActionPerformed(evt);
            }
        });

        jButton_borrar_cliente.setText("Borrar");
        jButton_borrar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_clienteActionPerformed(evt);
            }
        });

        jButton_modificar_cliente.setText("Modificar");
        jButton_modificar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_clienteActionPerformed(evt);
            }
        });

        jButton_aniadir_cliente.setText("Añadir");
        jButton_aniadir_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_clienteActionPerformed(evt);
            }
        });

        jComboBox_Cliente_to_Sucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Sucursal");

        javax.swing.GroupLayout jPanel_ClienteLayout = new javax.swing.GroupLayout(jPanel_Cliente);
        jPanel_Cliente.setLayout(jPanel_ClienteLayout);
        jPanel_ClienteLayout.setHorizontalGroup(
            jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_libro)
                            .addComponent(jLabel_autor)
                            .addComponent(jLabel_edad_recomendada))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField_Cliente_Dni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(jTextField_Nombre_Cliente, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_cliente_apellido, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton_guardar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_cancelar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Cliente_to_Sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_ClienteLayout.setVerticalGroup(
            jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_cliente))
                    .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_libro)
                    .addComponent(jTextField_Cliente_Dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_autor)
                    .addComponent(jTextField_Nombre_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_edad_recomendada)
                    .addComponent(jTextField_cliente_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_cliente))
                .addGap(49, 49, 49)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Cliente_to_Sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Cliente", jPanel_Cliente);

        jTable_prestamo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FechadPrestamo", "Dinero prestado", "Intereses", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_prestamoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_prestamo);
        if (jTable_prestamo.getColumnModel().getColumnCount() > 0) {
            jTable_prestamo.getColumnModel().getColumn(0).setResizable(false);
            jTable_prestamo.getColumnModel().getColumn(1).setResizable(false);
            jTable_prestamo.getColumnModel().getColumn(2).setResizable(false);
            jTable_prestamo.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel_nombre_persona.setText("Fehca de préstamo:");

        jLabel_edad_persona.setText("Dinero prestado:");

        jTextField_prestamo_fecha_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_prestamo_fecha_prestamoActionPerformed(evt);
            }
        });

        jButton_guardar_prestamo.setText("Guardar");
        jButton_guardar_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_prestamoActionPerformed(evt);
            }
        });

        jButton_cancelar_prestamo.setText("Cancelar");
        jButton_cancelar_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_prestamoActionPerformed(evt);
            }
        });

        jButton_borrar_prestamo.setText("Borrar");
        jButton_borrar_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_prestamoActionPerformed(evt);
            }
        });

        jButton_modificar_prestamo.setText("Modificar");
        jButton_modificar_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_prestamoActionPerformed(evt);
            }
        });

        jButton_aniadir_prestamo.setText("Añadir");
        jButton_aniadir_prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_prestamoActionPerformed(evt);
            }
        });

        jLabel_ciudad_natal.setText("Intereses:");

        jComboBox_prestamo_to_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_prestamo_to_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_prestamo_to_clienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Cliente");

        javax.swing.GroupLayout jPanel_prestamoLayout = new javax.swing.GroupLayout(jPanel_prestamo);
        jPanel_prestamo.setLayout(jPanel_prestamoLayout);
        jPanel_prestamoLayout.setHorizontalGroup(
            jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_persona)
                            .addComponent(jLabel_edad_persona)
                            .addComponent(jLabel_ciudad_natal))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_prestamo_fecha_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jTextField_prestamo_dinero)
                            .addComponent(jTextField_prestamo_intereses, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                    .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_prestamo_to_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_prestamoLayout.setVerticalGroup(
            jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_prestamo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_prestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_prestamo))
                    .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_persona)
                    .addComponent(jTextField_prestamo_fecha_prestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_prestamo))
                .addGap(15, 15, 15)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_edad_persona)
                    .addComponent(jTextField_prestamo_dinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_cancelar_prestamo)
                    .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_ciudad_natal)
                        .addComponent(jTextField_prestamo_intereses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox_prestamo_to_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Prestamo", jPanel_prestamo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("Ciudad");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_prestamo_to_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_prestamo_to_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_prestamo_to_clienteActionPerformed

    /**
     * 
     * @param evt 
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    try {
       prestamos = pc.leerPrestamo();
       clientes = cc.leerCliente();
       sucursales = sc.leerSucursal();
       bancos = bc.leerBanco(); 
        cargarTablaBancos();
        cargarTablaClientes();
        cargarTablaSucursal();
        cargarTablaPrestamos();
        
        
    } catch (IOException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowActivated

    private void jTextField_direccion_bancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_direccion_bancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_direccion_bancoActionPerformed

    private void jTextField_direccion_bancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_direccion_bancoKeyPressed
  
    }//GEN-LAST:event_jTextField_direccion_bancoKeyPressed

    private void jTextField_direccion_bancoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_direccion_bancoFocusLost
       
    }//GEN-LAST:event_jTextField_direccion_bancoFocusLost

    private void jTextField_direccion_bancoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_direccion_bancoMouseExited

    }//GEN-LAST:event_jTextField_direccion_bancoMouseExited

    /**
     * 
     * @param evt 
     */
    private void jTable_BancoMouseClicked(java.awt.event.MouseEvent evt){
        int fila = 0;
        fila = jTable_Banco.getSelectedRow();
        String direccion = (String) jTable_Banco.getValueAt(fila,0);
        String registros = (String) jTable_Banco.getValueAt(fila,1);
        String jefe_Banco = (String) jTable_Banco.getValueAt(fila,2);
        String nombreBanco = (String) jTable_Banco.getValueAt(fila, 3);
        jTextField_direccion_banco.setText(direccion);
        jTextField_registros.setText(registros);
        jTextField_jefeBanco.setText(jefe_Banco);
        jTextField_Nombre_Banco.setText(nombreBanco);
    
    }
    
    private void jTable_clienteMouseClicked(java.awt.event.MouseEvent evt){
        int fila = 0;
        fila = jTable_cliente.getSelectedRow();
        String dni = (String) jTable_cliente.getValueAt(fila,0);
        String nombre = (String) jTable_cliente.getValueAt(fila,1);
        String apellido = (String) jTable_cliente.getValueAt(fila,2);
        String sucursal = (String) jTable_cliente.getValueAt(fila, 3);
        jTextField_Cliente_Dni.setText(dni);
        jTextField_Nombre_Cliente.setText(nombre);
        jTextField_cliente_apellido.setText(apellido);
        jComboBox_Cliente_to_Sucursal.setSelectedItem(sucursal);
    }
    
    private void jTable_prestamoMouseClicked(java.awt.event.MouseEvent evt){
        int fila = 0;
        fila = jTable_prestamo.getSelectedRow();
        String fechaPrestamo = (String) jTable_prestamo.getValueAt(fila,0);
        String dineroPrestado = (String) jTable_prestamo.getValueAt(fila,1);
        String intereses = (String) jTable_prestamo.getValueAt(fila,2);
        String cliente = (String) jTable_prestamo.getValueAt(fila, 3);
        jTextField_prestamo_fecha_prestamo.setText(fechaPrestamo);
        jTextField_prestamo_dinero.setText(dineroPrestado);
        jTextField_prestamo_intereses.setText(intereses);
        jComboBox_prestamo_to_cliente.setSelectedItem(cliente);
    }
    
    private void jTable_SucursalMouseClicked(java.awt.event.MouseEvent evt){
    
        int fila = 0;
        fila = jTable_Sucursal.getSelectedRow();
        String direccionSucursal = (String) jTable_Sucursal.getValueAt(fila,0);
        String empresa = (String) jTable_Sucursal.getValueAt(fila,1);
        String jefeSucursal = (String) jTable_Sucursal.getValueAt(fila,2);
        String banco = (String) jTable_Sucursal.getValueAt(fila, 3);
        jTextField_Direccion_Sucursal.setText(direccionSucursal);
        jTextField_Empresa_Sucursal.setText(empresa);
        jTextField_Jefe_Sucursal.setText(jefeSucursal);
        jComboBox_Sucursal_to_Banco.setSelectedItem(banco);
    }
    
    
    
    /**
     * 
     * @param evt 
     */
    private void jButton_guardar_bancoActionPerformed(java.awt.event.ActionEvent evt){
        if (!jTextField_jefeBanco.getText().equals("") || !jTextField_registros.getText().equals("") || !jTextField_direccion_banco.getText().equals("") ) {
            
        
    Banco b;
    String direccion = jTextField_direccion_banco.getText();
    String registros = jTextField_registros.getText();
    String jefeBanco = jTextField_jefeBanco.getText();
    String nombreBanco = jTextField_Nombre_Banco.getText();
    Banco.setContador(bancos.size());
    b = new Banco(direccion,registros ,jefeBanco ,nombreBanco );
    bancos.add(b);
    
    try {
        bc.EscribirBanco(bancos);
    } catch (IOException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
        else{
            JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void jButton_cancelar_bancoActionPerformed(java.awt.event.ActionEvent evt){
        limpiarTextoBanco();
    }
    
    
    private void jButton_aniadir_bancoActionPerformed(java.awt.event.ActionEvent evt){
     if (!jTextField_jefeBanco.getText().equals("") || !jTextField_registros.getText().equals("") || !jTextField_direccion_banco.getText().equals("") || !jTextField_Nombre_Banco.getText().equals("") ) {
    añadirATablaBanco();
    limpiarTextoBanco();
     }
     else{
        JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
     }
    }
    
    
    
    /**
     * 
     * @param evt 
     */
    private void  jButton_modificar_bancoActionPerformed(java.awt.event.ActionEvent evt) {
        if (!jTextField_jefeBanco.getText().equals("") || !jTextField_registros.getText().equals("") || !jTextField_direccion_banco.getText().equals("") ) {
        int id = getIDDesdeTablaBanco();
        Banco b;
    try {
    
      
        Iterator it;
        it = bancos.iterator();
        while(it.hasNext()){
            b = (Banco) it.next();
            if (b.getnBanco() == id) {
                b.setJefeBanco(jTextField_jefeBanco.getText());
                b.setRegistros(jTextField_registros.getText());
                b.setDireccion(jTextField_direccion_banco.getText());
                b.setNombreBanco(jTextField_Nombre_Banco.getText());
            }
           }
        
        
        bc.EscribirBanco(bancos);
        cargarTablaBancos();
       } catch (IOException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
        catch(NoSuchElementException nsee){
            System.out.println("no existe ese elemento");
        }
        }
        else{
            JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    /**
     * 
     * @param evt
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    private void jButton_borrar_bancoActionPerformed(java.awt.event.ActionEvent evt){
      int id = getIDDesdeTablaBanco();
       Banco b;
       int fila;
        try {
    
      
        Iterator it;
        it = bancos.iterator();
        while(it.hasNext()){
            b = (Banco) it.next();
            if (b.getnBanco() == id) {
                b.setActivo(false);
                fila = jTable_Banco.getSelectedRow();
                DefaultTableModel modelo = (DefaultTableModel) jTable_Banco.getModel();
                modelo.removeRow(fila);
                jTable_Banco.setModel(modelo);
            }
           }
        
        
        bc.EscribirBanco(bancos);
        cargarTablaBancos();
       } catch (IOException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
        catch(NoSuchElementException nsee){
            System.out.println("no existe ese elemento");
        }
    }
    
    
    
    
    private void jButton_aniadir_sucursalActionPerformed(java.awt.event.ActionEvent evt){
        if (!jTextField_Jefe_Sucursal.getText().equals("") || !jTextField_Empresa_Sucursal.getText().equals("") || !jTextField_Direccion_Sucursal.getText().equals("")) {
    añadirATablaSucursal();
    limpiarTextoSucursal();
     }
     else{
        JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
     }
    
    
    
    }
    
    private void jButton_modificar_sucursalActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_borrar_sucursalActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_guardar_sucursalActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_cancelar_sucursalActionPerformed(java.awt.event.ActionEvent evt){
    limpiarTextoSucursal();
    }
    
    private void jTextField_Cliente_DniActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_guardar_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_cancelar_clienteActionPerformed(java.awt.event.ActionEvent evt){
    limpiarTextoCliente();
    }
    
    private void jButton_borrar_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_modificar_clienteActionPerformed(java.awt.event.ActionEvent evt){}   
    
    private void jButton_aniadir_clienteActionPerformed(java.awt.event.ActionEvent evt){
        if (!jTextField_Cliente_Dni.getText().equals("") || !jTextField_Nombre_Cliente.getText().equals("") || !jTextField_cliente_apellido.getText().equals("")) {
    añadirATablaCliente();
    limpiarTextoSucursal();
     }
     else{
        JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
     }
    
    
    
    }
    
    private void jButton_guardar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_cancelar_prestamoActionPerformed(java.awt.event.ActionEvent evt){
    limpiarTextoPrestamo();
    }
    
    private void jButton_borrar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_modificar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    
    private void jButton_aniadir_prestamoActionPerformed(java.awt.event.ActionEvent evt){
    if (!jTextField_prestamo_dinero.getText().equals("") || !jTextField_prestamo_intereses.getText().equals("") || !jTextField_prestamo_intereses.getText().equals("")) {
    añadirATablaCliente();
    limpiarTextoSucursal();
     }
     else{
        JOptionPane.showMessageDialog(null,"Hay un campo o varios campos vacios", " Mensaje de error", JOptionPane.ERROR_MESSAGE);
     }
    
    }
    
    private void jTextField_prestamo_fecha_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_aniadir_banco;
    private javax.swing.JButton jButton_aniadir_cliente;
    public javax.swing.JButton jButton_aniadir_prestamo;
    private javax.swing.JButton jButton_aniadir_sucursal;
    private javax.swing.JButton jButton_borrar_banco;
    private javax.swing.JButton jButton_borrar_cliente;
    public javax.swing.JButton jButton_borrar_prestamo;
    private javax.swing.JButton jButton_borrar_sucursal;
    private javax.swing.JButton jButton_cancelar_banco;
    public javax.swing.JButton jButton_cancelar_cliente;
    private javax.swing.JButton jButton_cancelar_prestamo;
    private javax.swing.JButton jButton_cancelar_sucursal;
    private javax.swing.JButton jButton_guardar_banco;
    public javax.swing.JButton jButton_guardar_cliente;
    private javax.swing.JButton jButton_guardar_prestamo;
    private javax.swing.JButton jButton_guardar_sucursal;
    private javax.swing.JButton jButton_modificar_banco;
    private javax.swing.JButton jButton_modificar_cliente;
    public javax.swing.JButton jButton_modificar_prestamo;
    private javax.swing.JButton jButton_modificar_sucursal;
    private javax.swing.JComboBox<String> jComboBox_Cliente_to_Sucursal;
    private javax.swing.JComboBox<String> jComboBox_Sucursal_to_Banco;
    private javax.swing.JComboBox<String> jComboBox_prestamo_to_cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_autor;
    private javax.swing.JLabel jLabel_ciudad_natal;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_direccion1;
    private javax.swing.JLabel jLabel_edad_persona;
    private javax.swing.JLabel jLabel_edad_recomendada;
    private javax.swing.JLabel jLabel_nombre_libro;
    private javax.swing.JLabel jLabel_nombre_persona;
    private javax.swing.JLabel jLabel_num_habitantes;
    private javax.swing.JLabel jLabel_num_habitantes1;
    private javax.swing.JLabel jLabel_pais;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JPanel jPanel_Cliente;
    private javax.swing.JPanel jPanel_Sucursal;
    private javax.swing.JPanel jPanel_banco;
    private javax.swing.JPanel jPanel_prestamo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable_Banco;
    private javax.swing.JTable jTable_Sucursal;
    private javax.swing.JTable jTable_cliente;
    public javax.swing.JTable jTable_prestamo;
    private javax.swing.JTextField jTextField_Cliente_Dni;
    public javax.swing.JTextField jTextField_Direccion_Sucursal;
    public javax.swing.JTextField jTextField_Empresa_Sucursal;
    public javax.swing.JTextField jTextField_Jefe_Sucursal;
    private javax.swing.JTextField jTextField_Nombre_Banco;
    private javax.swing.JTextField jTextField_Nombre_Cliente;
    private javax.swing.JTextField jTextField_cliente_apellido;
    public javax.swing.JTextField jTextField_direccion_banco;
    public javax.swing.JTextField jTextField_jefeBanco;
    private javax.swing.JTextField jTextField_prestamo_dinero;
    private javax.swing.JTextField jTextField_prestamo_fecha_prestamo;
    private javax.swing.JTextField jTextField_prestamo_intereses;
    public javax.swing.JTextField jTextField_registros;
    // End of variables declaration//GEN-END:variables

}