/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import tiendatecnologica.bejarmartinvictor.baseDatos.UsarBBDD;
import tiendatecnologica.bejarmartinvictor.objetos.Compra;
import tiendatecnologica.bejarmartinvictor.objetos.Usuario;


/**
 *
 * @author alumno
 */
public class HistorialCompras extends javax.swing.JFrame {

    private JFrame inicio;
    private DefaultComboBoxModel<Usuario> comboList;

    public HistorialCompras(JFrame inicio) {
        this.inicio = inicio;
        initComponents();
        modificarComponentes();
    }
    
    private void modificarComponentes() {
        jComboBox1.setRenderer(new MiBoxRenderer());
        jComboBox1.setEditor(new BoxEditor());
        jComboBox1.setEditable(true);       
        botonUsuario.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private DefaultComboBoxModel databaseUsuario(){
        comboList = new DefaultComboBoxModel();
        Usuario seleciona = new Usuario(0, "SELECIONA UNO", "", "", 0, "", "");
        comboList.addElement(seleciona);
        for(Usuario usuario :  UsarBBDD.usuarioBBDDSacar()){
            comboList.addElement(usuario);
        }
        return comboList;
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        atras = new javax.swing.JButton();
        botonUsuario = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(209, 182, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 232));

        jLabel1.setFont(new java.awt.Font("NSimSun", 0, 79)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("VICTOR’S INFORMATIC’S");

        atras.setBackground(new java.awt.Color(209, 182, 255));
        atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atras.png"))); // NOI18N
        atras.setBorderPainted(false);
        atras.setContentAreaFilled(false);
        atras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atras.setFocusPainted(false);
        atras.setFocusable(false);
        atras.setPreferredSize(new java.awt.Dimension(50, 50));
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165)
                .addComponent(jLabel1)
                .addGap(282, 282, 282))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonUsuario.setBackground(new java.awt.Color(125, 109, 153));
        botonUsuario.setFont(new java.awt.Font("Lucida Sans", 0, 24)); // NOI18N
        botonUsuario.setForeground(new java.awt.Color(232, 219, 255));
        botonUsuario.setText("BUSCAR PRODUCTOS");
        botonUsuario.setToolTipText("");
        botonUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonUsuario.setOpaque(true);
        botonUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonUsuarioMouseExited(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(237, 226, 255));
        jComboBox1.setModel(databaseUsuario());
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setBackground(new java.awt.Color(250, 248, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(146, 127, 179));
        jTextArea1.setRows(5);
        jTextArea1.setText("COMPRA :");
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1486, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(232, 232, 232)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(botonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(100, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseClicked
        int numero = jComboBox1.getSelectedIndex();
        if(numero!=0){
            Usuario usuario = comboList.getElementAt(numero);
            usuario = UsarBBDD.prodcutoBBDDSacar(usuario);
            String lable = "";
            for(Compra compra : usuario.getHistorialCompras()){
                lable+="COMPRA :"+compra+"\n";
            }
            jTextArea1.setText(lable);
        }else{
            jTextArea1.setText("COMPRA :");
        }
        
    }//GEN-LAST:event_botonUsuarioMouseClicked

    private void botonUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseEntered
        botonUsuario.setBackground(new Color(84, 73, 102));
    }//GEN-LAST:event_botonUsuarioMouseEntered

    private void botonUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseExited
        botonUsuario.setBackground(new Color(125, 109, 153));
    }//GEN-LAST:event_botonUsuarioMouseExited

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JLabel botonUsuario;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
