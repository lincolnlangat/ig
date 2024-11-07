/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IGate;

import javax.swing.SwingUtilities;

/**
 *
 * @author lincoln R
 */
public class Launcher {
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                GatePerformance main = new GatePerformance();
                main.show();
                
                //testP main2 = new testP();
                //main2.init();
            }
        });
    }
}
