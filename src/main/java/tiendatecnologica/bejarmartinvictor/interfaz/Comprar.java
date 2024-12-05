/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.TrayIcon;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import tiendatecnologica.bejarmartinvictor.baseDatos.UsarBBDD;
import tiendatecnologica.bejarmartinvictor.objetos.Compra;
import tiendatecnologica.bejarmartinvictor.objetos.Producto;
import tiendatecnologica.bejarmartinvictor.objetos.Usuario;


/**
 *
 * @author alumno
 */
public class Comprar extends javax.swing.JFrame {
    
    private JFrame inicio;
    private DefaultComboBoxModel<Usuario> comboListUsuarios;
    private DefaultComboBoxModel<String> comboListCategorias;
    private DefaultComboBoxModel<Producto> comboListProductos;
    private int numImg = 0;    
    /**
     * Creates new form DatosUsuario
     */
    public Comprar(JFrame ventanaPrincipal){
        this.inicio =ventanaPrincipal;
        initComponents();
        modificarComponentes();
    }
    
    private void modificarComponentes() {
        createComboBox(usuarios);
        createComboBox(categoria);
        createComboBox(producto);
        botonUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        imagen1.setEnabled(false);
    }
    
    private void createComboBox(JComboBox<String> comboBox) {
        comboBox.setRenderer(new MiBoxRenderer());
        comboBox.setEditor(new BoxEditor());
        comboBox.setEditable(true);        
    }
    
    private DefaultComboBoxModel databaseUsuario(){
        comboListUsuarios = new DefaultComboBoxModel();
        Usuario seleciona = new Usuario(0, "SELECIONA UNO", "", "", 0, "", "");
        comboListUsuarios.addElement(seleciona);
        for(Usuario usuario :  UsarBBDD.usuarioBBDDSacar()){
            comboListUsuarios.addElement(usuario);
        }
        return comboListUsuarios;
    }
    
    private DefaultComboBoxModel databaseCategorias(){
        comboListCategorias = new DefaultComboBoxModel<>();
        comboListCategorias.addElement("Default");
        for(String categorias : UsarBBDD.categoriasBBDDSacar()){
            comboListCategorias.addElement(categorias);
        }
        return comboListCategorias;
    }

    
    private DefaultComboBoxModel databaseProducto(){
       comboListProductos = new DefaultComboBoxModel();
        Producto seleciona = new Producto(0, "Seleciona producto", 0);
        comboListProductos.addElement(seleciona);
        int numero = categoria.getSelectedIndex();
        System.out.println(numero);
        if(numero>0){
            String categoriaS = comboListCategorias.getElementAt(numero);
            for(Producto producto :  UsarBBDD.prodcutoBBDDSacar(categoriaS)){
                comboListProductos.addElement(producto);
            }   
        }

        return comboListProductos;
    }
    
    private void caragarImg(){
        int anchoMaximo  = 600;
        int altoMaximo  = 290;

        // Cargar la imagen
        int num = producto.getSelectedIndex();
        if(num != 0){
            Producto producto = comboListProductos.getElementAt(num);
            productoDetails.setText(producto.datos());
            String url = producto.getImagenes().get(numImg);
            ImageIcon img = new ImageIcon(getClass().getResource("/"+url));
            ImageIcon icono = redimensionarImagen(img, altoMaximo, anchoMaximo);

            imagen.setIcon(icono);
            imagen.setHorizontalAlignment(JLabel.CENTER);
        }else{
            ImageIcon icono = new ImageIcon(getClass().getResource("/sinCargar.jpg"));
            imagen.setIcon(icono);
        }
    }
    
    private static ImageIcon redimensionarImagen(ImageIcon iconoOriginal, int anchoMaximo, int altoMaximo) {
        int anchoOriginal = iconoOriginal.getIconWidth();
        int altoOriginal = iconoOriginal.getIconHeight();

        // Comprobar si necesita redimensionar
        if (anchoOriginal > anchoMaximo || altoOriginal > altoMaximo) {
            double escalaAncho = (double) anchoMaximo / anchoOriginal;
            double escalaAlto = (double) altoMaximo / altoOriginal;
            double escala = Math.min(escalaAncho, escalaAlto);

            // Calcular nuevo tamaño
            int nuevoAncho = (int) (anchoOriginal * escala);
            int nuevoAlto = (int) (altoOriginal * escala);

            // Escalar la imagen
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        }
        return iconoOriginal; // Retornar la imagen original si no necesita redimensionar
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
        usuarios = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        productoDetails = new javax.swing.JTextArea();
        imagen = new javax.swing.JLabel();
        imagen1 = new javax.swing.JLabel();
        imagen2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        producto = new javax.swing.JComboBox<>();
        categoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 800));

        jPanel1.setBackground(new java.awt.Color(209, 182, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 232));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("NSimSun", 0, 79)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("VICTOR’S INFORMATIC'S");

        atras.setBackground(new java.awt.Color(209, 182, 255));
        atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atras.png"))); // NOI18N
        atras.setBorderPainted(false);
        atras.setContentAreaFilled(false);
        atras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atras.setFocusPainted(false);
        atras.setFocusable(false);
        atras.setMinimumSize(new java.awt.Dimension(50, 50));
        atras.setOpaque(true);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
        botonUsuario.setText("COMPRAR");
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

        usuarios.setBackground(new java.awt.Color(237, 226, 255));
        usuarios.setModel(databaseUsuario());
        usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usuarios.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        usuarios.setFocusable(false);
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(250, 248, 255));

        productoDetails.setEditable(false);
        productoDetails.setBackground(new java.awt.Color(250, 248, 255));
        productoDetails.setColumns(20);
        productoDetails.setForeground(new java.awt.Color(146, 127, 179));
        productoDetails.setRows(5);
        productoDetails.setBorder(null);
        productoDetails.setFocusable(false);

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sinCargar.jpg"))); // NOI18N

        imagen1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        imagen1.setText("<");
        imagen1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imagen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagen1MouseClicked(evt);
            }
        });

        imagen2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        imagen2.setText(">");
        imagen2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imagen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagen2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(productoDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(imagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(productoDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        producto.setBackground(new java.awt.Color(237, 226, 255));
        producto.setModel(databaseProducto());
        producto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        producto.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        producto.setFocusable(false);
        producto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productoItemStateChanged(evt);
            }
        });
        producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoActionPerformed(evt);
            }
        });

        categoria.setBackground(new java.awt.Color(237, 226, 255));
        categoria.setModel(databaseCategorias());
        categoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        categoria.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        categoria.setFocusable(false);
        categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoriaItemStateChanged(evt);
            }
        });
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(producto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(producto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addComponent(botonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseClicked
        
        Usuario usuario = (Usuario) usuarios.getSelectedItem();
        if(usuario.getId()!=0){
            int num = producto.getSelectedIndex();
            if(num!=0){
                Producto producto = comboListProductos.getElementAt(num);
                Compra compra = new Compra(producto, (Integer) jSpinner1.getValue(), LocalDate.now());
                UsarBBDD.compraBBDDInsert(compra, usuario.getId());
                JOptionPane.showMessageDialog(this, "SE HA HA INSERTADO A LA BASE DE DATOS");
            }else{
                JOptionPane.showMessageDialog(this, "ESCOJE UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "ESCOJE UN USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonUsuarioMouseClicked

    private void botonUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseEntered
        botonUsuario.setBackground(new Color(84, 73, 102));
    }//GEN-LAST:event_botonUsuarioMouseEntered

    private void botonUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUsuarioMouseExited
        botonUsuario.setBackground(new Color(125, 109, 153));
    }//GEN-LAST:event_botonUsuarioMouseExited

    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariosActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasActionPerformed

    private void productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productoActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void categoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoriaItemStateChanged
        producto.setModel(databaseProducto());
    }//GEN-LAST:event_categoriaItemStateChanged

    private void productoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productoItemStateChanged
        caragarImg();
    }//GEN-LAST:event_productoItemStateChanged

    private void imagen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagen2MouseClicked
        if(imagen2.isEnabled()){
            int num = producto.getSelectedIndex();
            Producto producto = comboListProductos.getElementAt(num);
            if(producto.getId() != 0){
                numImg = 1;
                imagen1.setEnabled(true);
                imagen2.setEnabled(false);
                caragarImg();
            }else{
                JOptionPane.showMessageDialog(this, "ESCOJE UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_imagen2MouseClicked

    private void imagen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagen1MouseClicked
        if(imagen1.isEnabled()){
            int num = producto.getSelectedIndex();
            Producto producto = comboListProductos.getElementAt(num);
            if(producto.getId() != 0){
                numImg = 0;
                imagen1.setEnabled(false);
                imagen2.setEnabled(true);
                caragarImg();
            }else{
                JOptionPane.showMessageDialog(this, "ESCOJE UN PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_imagen1MouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JLabel botonUsuario;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel imagen1;
    private javax.swing.JLabel imagen2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox<String> producto;
    private javax.swing.JTextArea productoDetails;
    private javax.swing.JComboBox<String> usuarios;
    // End of variables declaration//GEN-END:variables
}
