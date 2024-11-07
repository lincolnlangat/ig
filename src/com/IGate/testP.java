/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IGate;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lincoln R
 */
public class testP {
    public JFrame frame;
    public JCheckBox chb;
    public JLabel lbl;
    
    public testP(){
        
        init();
    }
    
    public void init(){
        frame = new JFrame();
        frame.setTitle("Gate Performance Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        
        chb = new JCheckBox("test");
        chb.setBounds(160, 100, 80, 40);
        frame.add(chb);
        
       
           System.out.println("Checked");
           
        
    }
}
