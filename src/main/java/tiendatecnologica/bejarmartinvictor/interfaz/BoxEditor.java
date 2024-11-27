/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 *
 * @author alumno
 */
public class BoxEditor  extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;
     
    public BoxEditor() {
         
        label.setOpaque(false);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(232,219,255));
         
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        panel.setBackground(new Color(125, 109, 153));
    }
     
    @Override
    public Component getEditorComponent() {
        return this.panel;
    }
     
    @Override
    public Object getItem() {
        return "[" + this.selectedItem.toString() + "]";
    }
     
    @Override
    public void setItem(Object item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }
}