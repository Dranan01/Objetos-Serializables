/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.BancoControlador;
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
    
    


    public void limpiarTextoBanco(){
        jTextField_direccion_banco.setText("");
        jTextField_jefeBanco.setText("");
        jTextField_registros.setText("");
    }
    public void cargarTabla(){
        
        String matriz[][] = new String[bancos.size()][3];
    
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).isActivo() == true) {
                
            
            matriz[i][0] = bancos.get(i).getDireccion();
            matriz[i][1] = bancos.get(i).getJefeBanco();
            matriz[i][2] = bancos.get(i).getRegistros();
            
        
        
    jTable_Banco.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Direccion", "Registros", "Jefe Banco"
            }
                    ));
   
     }
        }
}

    public void añadirATabla(){
        String[] array = new String[3];
        
        array[0] = jTextField_direccion_banco.getText();
        array[1] = jTextField_registros.getText();
        array[2] = jTextField_jefeBanco.getText();
        
        DefaultTableModel dt = (DefaultTableModel) jTable_Banco.getModel();
        dt.addRow(array);
    }
    
    
    public int getID(){
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
    
    public void habilitarBotonesBanco(){
        jButton_aniadir_banco.setEnabled(true);
        jButton_borrar_banco.setEnabled(true);
        jButton_guardar_banco.setEnabled(true);
        jButton_modificar_banco.setEnabled(true);
    }

    
    public void deshabilitarBotonesBanco(){
        jButton_aniadir_banco.setEnabled(false);
        jButton_borrar_banco.setEnabled(false);
        jButton_guardar_banco.setEnabled(false);
        jButton_modificar_banco.setEnabled(false);
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
        jPanel_Sucursal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sucursal = new javax.swing.JTable();
        jButton_aniadir_sucursal = new javax.swing.JButton();
        jButton_modificar_sucursal = new javax.swing.JButton();
        jButton_borrar_sucursa = new javax.swing.JButton();
        jLabel_nombre_ciudad = new javax.swing.JLabel();
        jLabel_pais = new javax.swing.JLabel();
        jLabel_num_habitantes = new javax.swing.JLabel();
        jTextField_nombre_ciudad = new javax.swing.JTextField();
        jTextField_pais = new javax.swing.JTextField();
        jTextField_num_habitantes = new javax.swing.JTextField();
        jButton_guardar_sucursa = new javax.swing.JButton();
        jButton_cancelar_sucursa = new javax.swing.JButton();
        jTextField_num_habitantes1 = new javax.swing.JTextField();
        jLabel_num_habitantes1 = new javax.swing.JLabel();
        jLabel_num_habitantes2 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel_Cliente = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_cliente = new javax.swing.JTable();
        jLabel_nombre_libro = new javax.swing.JLabel();
        jLabel_autor = new javax.swing.JLabel();
        jLabel_edad_recomendada = new javax.swing.JLabel();
        jLabel_biblioteca_libro = new javax.swing.JLabel();
        jTextField_edad_recomendada = new javax.swing.JTextField();
        jTextField_nombre_libro = new javax.swing.JTextField();
        jTextField_autor = new javax.swing.JTextField();
        jButton_guardar_cliente = new javax.swing.JButton();
        jButton_cancelar_cliente = new javax.swing.JButton();
        jButton_borrar_cliente = new javax.swing.JButton();
        jButton_modificar_cliente = new javax.swing.JButton();
        jButton_aniadir_cliente = new javax.swing.JButton();
        jLabel_anio_publicacion = new javax.swing.JLabel();
        jTextField_anio_publicacion = new javax.swing.JTextField();
        jRadioButton4 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel_prestamo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_prestamo = new javax.swing.JTable();
        jLabel_nombre_persona = new javax.swing.JLabel();
        jLabel_DNI = new javax.swing.JLabel();
        jLabel_edad_persona = new javax.swing.JLabel();
        jTextField_edad_persona = new javax.swing.JTextField();
        jTextField_nombre_persona = new javax.swing.JTextField();
        jTextField_DNI = new javax.swing.JTextField();
        jButton_guardar_prestamo = new javax.swing.JButton();
        jButton_cancelar_prestamo = new javax.swing.JButton();
        jButton_borrar_prestamo = new javax.swing.JButton();
        jButton_modificar_prestamo = new javax.swing.JButton();
        jButton_aniadir_prestamo = new javax.swing.JButton();
        jLabel_ciudad_natal = new javax.swing.JLabel();
        jTextField_ciudad_natal = new javax.swing.JTextField();
        jLabel_ciudad_natal1 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jComboBox3 = new javax.swing.JComboBox<>();
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
                "Direccion", "Registros", "Jefe Banco"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
                            .addComponent(jLabel_direccion1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_bancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_registros)
                            .addComponent(jTextField_jefeBanco)
                            .addComponent(jTextField_direccion_banco))
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
                    .addComponent(jLabel_direccion)
                    .addComponent(jButton_cancelar_banco))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Banco", jPanel_banco);

        jTable_Sucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nª Sucursal", "Dirección", "Empresa", "Jefe Sucursal", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
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
            jTable_Sucursal.getColumnModel().getColumn(1).setResizable(false);
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

        jButton_borrar_sucursa.setText("Borrar");
        jButton_borrar_sucursa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_sucursaActionPerformed(evt);
            }
        });

        jLabel_nombre_ciudad.setText("Nº Sucursal:");

        jLabel_pais.setText("Dirección:");

        jLabel_num_habitantes.setText("Empresa:");

        jTextField_nombre_ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_ciudadActionPerformed(evt);
            }
        });

        jButton_guardar_sucursa.setText("Guardar");
        jButton_guardar_sucursa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_sucursaActionPerformed(evt);
            }
        });

        jButton_cancelar_sucursa.setText("Cancelar");
        jButton_cancelar_sucursa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_sucursaActionPerformed(evt);
            }
        });

        jLabel_num_habitantes1.setText("Jefe Sucursal:");

        jLabel_num_habitantes2.setText("Activo:");

        jRadioButton2.setText("jRadioButton1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Banco:");

        javax.swing.GroupLayout jPanel_SucursalLayout = new javax.swing.GroupLayout(jPanel_Sucursal);
        jPanel_Sucursal.setLayout(jPanel_SucursalLayout);
        jPanel_SucursalLayout.setHorizontalGroup(
            jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_ciudad)
                            .addComponent(jLabel_num_habitantes1)
                            .addComponent(jLabel_num_habitantes2)
                            .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_num_habitantes)
                                .addComponent(jLabel_pais)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_nombre_ciudad, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(jTextField_pais)
                                    .addComponent(jTextField_num_habitantes)
                                    .addComponent(jTextField_num_habitantes1))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton_guardar_sucursa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_cancelar_sucursa, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                            .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_sucursa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                        .addComponent(jButton_borrar_sucursa))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_ciudad)
                    .addComponent(jTextField_nombre_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_pais)
                    .addComponent(jButton_guardar_sucursa)
                    .addComponent(jTextField_pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton_cancelar_sucursa))
                    .addGroup(jPanel_SucursalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_num_habitantes)
                            .addComponent(jTextField_num_habitantes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_num_habitantes1)
                    .addComponent(jTextField_num_habitantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel_SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_num_habitantes2)
                    .addComponent(jRadioButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Sucursal", jPanel_Sucursal);

        jTable_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Nº Cliente", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
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

        jLabel_biblioteca_libro.setText("Activo:");

        jTextField_nombre_libro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_libroActionPerformed(evt);
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

        jLabel_anio_publicacion.setText("Nº Cliente:");

        jRadioButton4.setText("jRadioButton1");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                            .addComponent(jLabel_edad_recomendada)
                            .addComponent(jLabel_biblioteca_libro)
                            .addComponent(jLabel_anio_publicacion))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField_anio_publicacion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_nombre_libro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(jTextField_autor, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_edad_recomendada, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton_guardar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_cancelar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                            .addGroup(jPanel_ClienteLayout.createSequentialGroup()
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGap(42, 42, 42)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_libro)
                    .addComponent(jTextField_nombre_libro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_autor)
                    .addComponent(jTextField_autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_edad_recomendada)
                    .addComponent(jTextField_edad_recomendada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_anio_publicacion)
                    .addComponent(jTextField_anio_publicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_biblioteca_libro)
                    .addComponent(jRadioButton4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Cliente", jPanel_Cliente);

        jTable_prestamo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FechadPrestamo", "Nº Préstamo", "Dinero prestado", "Intereses", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        }

        jLabel_nombre_persona.setText("Fehca de préstamo:");

        jLabel_DNI.setText("Nº de préstamo:");

        jLabel_edad_persona.setText("Dinero prestado:");

        jTextField_nombre_persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_personaActionPerformed(evt);
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

        jLabel_ciudad_natal1.setText("Activo");

        jRadioButton5.setText("jRadioButton1");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
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
                            .addComponent(jLabel_DNI)
                            .addComponent(jLabel_edad_persona)
                            .addComponent(jLabel_ciudad_natal)
                            .addComponent(jLabel_ciudad_natal1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_prestamoLayout.createSequentialGroup()
                                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_nombre_persona, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jTextField_DNI)
                            .addComponent(jTextField_edad_persona)
                            .addComponent(jTextField_ciudad_natal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_guardar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancelar_prestamo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
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
                .addGap(42, 42, 42)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_persona)
                    .addComponent(jTextField_nombre_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_DNI)
                    .addComponent(jButton_guardar_prestamo)
                    .addComponent(jTextField_DNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_edad_persona)
                    .addComponent(jTextField_edad_persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar_prestamo))
                .addGap(18, 18, 18)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ciudad_natal)
                    .addComponent(jTextField_ciudad_natal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_prestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ciudad_natal1)
                    .addComponent(jRadioButton5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(53, Short.MAX_VALUE))
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

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    /**
     * 
     * @param evt 
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    try {
       bancos = bc.leerBanco(); 
        cargarTabla();
        
        
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
        jTextField_direccion_banco.setText(direccion);
        jTextField_registros.setText(registros);
        jTextField_jefeBanco.setText(jefe_Banco);
    
    }
    
    
    
    
    private void jTable_clienteMouseClicked(java.awt.event.MouseEvent evt){}
    private void jTable_prestamoMouseClicked(java.awt.event.MouseEvent evt){}
    private void jTable_SucursalMouseClicked(java.awt.event.MouseEvent evt){}
    
    
    
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
    Banco.setContador(bancos.size());
    b = new Banco(direccion,registros ,jefeBanco );
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
     if (!jTextField_jefeBanco.getText().equals("") || !jTextField_registros.getText().equals("") || !jTextField_direccion_banco.getText().equals("") ) {
    añadirATabla();
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
        int id = getID();
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
            }
           }
        
        
        bc.EscribirBanco(bancos);
        cargarTabla();
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
      int id = getID();
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
        cargarTabla();
       } catch (IOException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
        catch(NoSuchElementException nsee){
            System.out.println("no existe ese elemento");
        }
    }
    
    
    
    
    private void jButton_aniadir_sucursalActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_modificar_sucursalActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_borrar_sucursaActionPerformed(java.awt.event.ActionEvent evt){}
    private void  jTextField_nombre_ciudadActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_guardar_sucursaActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_cancelar_sucursaActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextField_nombre_libroActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_guardar_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_cancelar_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_borrar_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_modificar_clienteActionPerformed(java.awt.event.ActionEvent evt){}   
    private void jButton_aniadir_clienteActionPerformed(java.awt.event.ActionEvent evt){}
    private void jTextField_nombre_personaActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_guardar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_cancelar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_borrar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_modificar_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    private void jButton_aniadir_prestamoActionPerformed(java.awt.event.ActionEvent evt){}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_aniadir_banco;
    private javax.swing.JButton jButton_aniadir_cliente;
    public javax.swing.JButton jButton_aniadir_prestamo;
    private javax.swing.JButton jButton_aniadir_sucursal;
    private javax.swing.JButton jButton_borrar_banco;
    private javax.swing.JButton jButton_borrar_cliente;
    public javax.swing.JButton jButton_borrar_prestamo;
    private javax.swing.JButton jButton_borrar_sucursa;
    private javax.swing.JButton jButton_cancelar_banco;
    public javax.swing.JButton jButton_cancelar_cliente;
    private javax.swing.JButton jButton_cancelar_prestamo;
    private javax.swing.JButton jButton_cancelar_sucursa;
    private javax.swing.JButton jButton_guardar_banco;
    public javax.swing.JButton jButton_guardar_cliente;
    private javax.swing.JButton jButton_guardar_prestamo;
    private javax.swing.JButton jButton_guardar_sucursa;
    private javax.swing.JButton jButton_modificar_banco;
    private javax.swing.JButton jButton_modificar_cliente;
    public javax.swing.JButton jButton_modificar_prestamo;
    private javax.swing.JButton jButton_modificar_sucursal;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_DNI;
    private javax.swing.JLabel jLabel_anio_publicacion;
    private javax.swing.JLabel jLabel_autor;
    private javax.swing.JLabel jLabel_biblioteca_libro;
    private javax.swing.JLabel jLabel_ciudad_natal;
    private javax.swing.JLabel jLabel_ciudad_natal1;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_direccion1;
    private javax.swing.JLabel jLabel_edad_persona;
    private javax.swing.JLabel jLabel_edad_recomendada;
    private javax.swing.JLabel jLabel_nombre_ciudad;
    private javax.swing.JLabel jLabel_nombre_libro;
    private javax.swing.JLabel jLabel_nombre_persona;
    private javax.swing.JLabel jLabel_num_habitantes;
    private javax.swing.JLabel jLabel_num_habitantes1;
    private javax.swing.JLabel jLabel_num_habitantes2;
    private javax.swing.JLabel jLabel_pais;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JPanel jPanel_Cliente;
    private javax.swing.JPanel jPanel_Sucursal;
    private javax.swing.JPanel jPanel_banco;
    private javax.swing.JPanel jPanel_prestamo;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable_Banco;
    private javax.swing.JTable jTable_Sucursal;
    private javax.swing.JTable jTable_cliente;
    public javax.swing.JTable jTable_prestamo;
    private javax.swing.JTextField jTextField_DNI;
    private javax.swing.JTextField jTextField_anio_publicacion;
    private javax.swing.JTextField jTextField_autor;
    private javax.swing.JTextField jTextField_ciudad_natal;
    public javax.swing.JTextField jTextField_direccion_banco;
    private javax.swing.JTextField jTextField_edad_persona;
    private javax.swing.JTextField jTextField_edad_recomendada;
    public javax.swing.JTextField jTextField_jefeBanco;
    public javax.swing.JTextField jTextField_nombre_ciudad;
    private javax.swing.JTextField jTextField_nombre_libro;
    private javax.swing.JTextField jTextField_nombre_persona;
    public javax.swing.JTextField jTextField_num_habitantes;
    public javax.swing.JTextField jTextField_num_habitantes1;
    public javax.swing.JTextField jTextField_pais;
    public javax.swing.JTextField jTextField_registros;
    // End of variables declaration//GEN-END:variables
//    private ArrayList<Ciudad> ciudades;
  //  private Ciudad ciudad_modificar;
    private DefaultTableModel table_model_ciudad;    
    private Boolean modif_ciudad;
    private int id_ciudad;
    
 //   private ArrayList<Biblioteca> bibliotecas;
 //   private Biblioteca biblioteca_modificar;
    private DefaultTableModel table_model_biblioteca;    
    private Boolean modif_biblioteca;
    private int id_biblioteca;
    
 //   private ArrayList<Libro> libros;
 //   private Libro libro_modificar;
    private DefaultTableModel table_model_libro;    
    private Boolean modif_libro;
    private int id_libro;
    
  //  private ArrayList<Persona> personas;
 //   private Persona persona_modificar;
    private DefaultTableModel table_model_persona;    
    private Boolean modif_persona;
    private int id_persona;
}