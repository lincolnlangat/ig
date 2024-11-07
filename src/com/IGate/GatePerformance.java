/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IGate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author lincoln R
 */
public class GatePerformance {
    
    private JFrame frame;
    private JPanel panel, manuals, manualSicdc, manualKr, manualAut;
    private JButton button;
    private JLabel gpr, el, twenties, forties, units, teus, imports, importsu, importst, 
            manuall, sicd, sicdu, sicdt, kr, kru, krt, aut, autu, autt, ti, ti20, ti40, tiu, tit,
            exports, exportsu, exportst, empties, emptiesu, emptiest, 
            totals, totals20, totals40, totalsu, totalst;
    private JTextField imports20, sicd20, kr20, aut20, exports20, empties20,
            imports40, sicd40, kr40, aut40, exports40, empties40;
    private JCheckBox sicdc, krc, autc;
    
    java.sql.Connection con; 
    PreparedStatement pst;
    
    public GatePerformance(){
        
        initialize();
    }
    
    private void initialize(){
        frame = new JFrame();
        frame.setTitle("Gate Performance Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        gpr = new JLabel("GATE PERFORMANCE REPORT");
        //frame.add(gpr, BorderLayout.NORTH);
        gpr.setBounds(160, 5, 300, 50);
        gpr.setFont(new Font("Cambria", Font.BOLD, 20));
        gpr.setForeground(Color.blue);
        frame.add(gpr);        
        
        panel = new JPanel(new GridLayout(6,5));
        panel.setBounds(160, 50, 500, 140);
        //panel.setFont(new Font("Cambria", Font.PLAIN ,12));
        frame.add(panel);
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2,5));
        //panel.setBackground(Color.RED);
        
        el = new JLabel("CType");
        twenties = new JLabel("20");
        forties = new JLabel("40");
        units = new JLabel("Units");
        teus = new JLabel("TEUs");
        panel.add(el);
        panel.add(twenties);
        panel.add(forties);
        panel.add(units);
        panel.add(teus);
        
        imports = new JLabel("Imports");
        imports.setFont(new Font("Cambria", Font.PLAIN, 14));
        imports20 = new JTextField(7);
        imports40 = new JTextField(7);
        importsu = new JLabel();
        importst = new JLabel();
        panel.add(imports);
        panel.add(imports20);
        panel.add(imports40);
        panel.add(importsu);
        panel.add(importst);
        //label.setForeground(Color.WHITE);
        
            
        ti = new JLabel("Totals(In)");
        ti.setFont(new Font("Cambria", Font.BOLD, 14));
        ti20 = new JLabel();
        ti40 = new JLabel();
        tiu = new JLabel();
        tit = new JLabel();
        panel.add(ti);
        panel.add(ti20);
        panel.add(ti40);
        panel.add(tiu);
        panel.add(tit);
        
        exports = new JLabel("Exports");
        exports.setFont(new Font("Cambria", Font.PLAIN, 14));
        exports20 = new JTextField(7);
        exports40 = new JTextField(7);
        exportsu = new JLabel();
        exportst = new JLabel();
        panel.add(exports);
        panel.add(exports20);
        panel.add(exports40);
        panel.add(exportsu);
        panel.add(exportst);
        
        empties = new JLabel("Empties");
        empties.setFont(new Font("Cambria", Font.PLAIN, 14));
        empties20 = new JTextField(7);
        empties40 = new JTextField(7);
        emptiesu = new JLabel();
        emptiest = new JLabel();
        panel.add(empties);
        panel.add(empties20);
        panel.add(empties40);
        panel.add(emptiesu);
        panel.add(emptiest);
        
        totals = new JLabel("Totals");
        totals.setFont(new Font("Cambria", Font.BOLD, 16));
        totals20 = new JLabel();
        totals40 = new JLabel();
        totalsu = new JLabel();
        totalst = new JLabel();
        panel.add(totals);
        panel.add(totals20);
        panel.add(totals40);
        panel.add(totalsu);
        panel.add(totalst);
       
        
        manuals = new JPanel(new GridLayout(1,5));
        manuals.setBounds(160, 180, 400, 50);
        frame.add(manuals);
        
        manuall = new JLabel("Manuals");
        manuall.setFont(new Font("Cambria", Font.BOLD, 16));
        manuall.setForeground(Color.blue);
        manuals.add(manuall);
        
        sicdc = createSicdc();
        manuals.add(sicdc);
        
        krc = new JCheckBox("KR Transit");
        manuals.add(krc);
        //krc.setText("KR Transit");
        krc.addActionListener((ActionEvent event) -> {
            JLabel manualKr1 = new JLabel("KR Transit Shed");
            manualKr1.setBounds(160, 250, 500, 40);
            JCheckBox cb = (JCheckBox) event.getSource();
            if (cb.isSelected()) {
                // do something if check box is selected
                manualKr.setVisible(true);
               
            } else {
                // check box is unselected, do something else
                manualKr.setVisible(false);
            }
        });
        
        
        
        autc = new JCheckBox();
        autc.setText("Autoports");
        autc.addActionListener((ActionEvent event) -> {
            JCheckBox cb = (JCheckBox) event.getSource();
            if (cb.isSelected()) {
                // do something if check box is selected
                manualAut.setVisible(true);
            } else {
                // check box is unselected, do something else
                manualAut.setVisible(false);
            }
        });                
        manuals.add(autc);
         
        manualSicdc = new JPanel(new GridLayout(1,5));
        manualSicdc.setBounds(160, 230, 500, 20);
        frame.add(manualSicdc);
        manualSicdc.setVisible(false);
        sicd = new JLabel("SICD:");
        sicd20 = new JTextField(7);
        sicd40 = new JTextField(7);
        sicdu = new JLabel();
        sicdt = new JLabel();        
        manualSicdc.add(sicd);
        manualSicdc.add(sicd20);
        manualSicdc.add(sicd40);
        manualSicdc.add(sicdu);
        manualSicdc.add(sicdt);
        
        manualKr = new JPanel(new GridLayout(1,5));
        manualKr.setBounds(160, 250, 500, 20);
        frame.add(manualKr);
        manualKr.setVisible(false);
        kr = new JLabel("KR Transit:");
        kr20 = new JTextField(7);
        kr40 = new JTextField(7);
        kru = new JLabel();
        krt = new JLabel();
        manualKr.add(kr);
        manualKr.add(kr20);
        manualKr.add(kr40);
        manualKr.add(kru);
        manualKr.add(krt);
        
        manualAut = new JPanel(new GridLayout(1,5));
        manualAut.setBounds(160, 270, 500, 20);
        frame.add(manualAut);
        manualAut.setVisible(false);
        aut = new JLabel("Autoports:");
        aut20 = new JTextField(7);
        aut40 = new JTextField(7);
        autu = new JLabel();
        autt = new JLabel();
        manualAut.add(aut);
        manualAut.add(aut20);
        manualAut.add(aut40);
        manualAut.add(autu);
        manualAut.add(autt);
        
        button = createButton();
        button.setBounds(160, 300, 80, 40);
        frame.add(button);         
        
    }   
    
    private JCheckBox createSicdc(){
        JCheckBox sicdc1 = new JCheckBox();
        sicdc1.setText("SICD");
        
        sicdc1.addActionListener((ActionEvent event) -> {
            JCheckBox cb = (JCheckBox) event.getSource();
            if (cb.isSelected()) {
                // do something if check box is selected
                manualSicdc.setVisible(true);
            } else {
                // check box is unselected, do something else
                manualSicdc.setVisible(false);
            }
        });      
         
        return sicdc1;
    }
    
    private JButton createButton(){
        JButton button1 = new JButton("Submit");
        button1.setFocusable(false);
        /*ImageIcon pI = new ImageIcon("pi.png");
        button.setIcon(pI);*/
        button1.setToolTipText("Submit Data");
        button1.addActionListener((ActionEvent e) -> {
            
            String imp20 = imports20.getText(); String exp20 = exports20.getText();
            String emp20 = empties20.getText(); String imp40 = imports40.getText();
            String exp40 = exports40.getText(); String emp40 = empties40.getText();

            java.util.List<String> dataList = new ArrayList<String>();		
                dataList.add(imp20); dataList.add(imp40); dataList.add(exp20); dataList.add(exp40); dataList.add(emp20);
                dataList.add(emp40);

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/igate","root","*?lhf2JHG");
                pst = con.prepareStatement("insert into gateperformance (imports20, imports40, exports20, exports40, empties20, empties40)values(?,?,?,?,?,?)");

                //pst.setDate(1, (java.sql.Date) new Date());
                pst.setString(1,imp20); pst.setString(2,imp40);
                pst.setString(3,exp20); pst.setString(4,exp40);
                pst.setString(5,emp20); pst.setString(6,emp40);

                pst.executeUpdate();
                //JOptionPane.showMessageDialog(this,"Record Added");
                JOptionPane.showMessageDialog(null, "Record Added");

                String excelFilePath = "D:\\gatePerformance\\gP.xlsx";  // provide your excel file path
                FileInputStream fileInputStream = new FileInputStream(excelFilePath);

                Workbook workbook = WorkbookFactory.create(fileInputStream);		
                Sheet sheet = workbook.getSheetAt(0);

                int lastRowCount = sheet.getLastRowNum();

                Row dataRow = sheet.createRow(++lastRowCount);

                for (int i = 0; i < dataList.size(); i++) {                            
                    dataRow.createCell(i).setCellValue(dataList.get(i));                           
                }

                fileInputStream.close();			
                FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                System.out.println("excel sheet updated successfully........");

            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }catch(ClassNotFoundException | IOException ex) {
                Logger.getLogger(GatePerformance.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        });
        return button1;
    }
    
    public void show(){
        this.frame.setVisible(true);
    }

   
}
