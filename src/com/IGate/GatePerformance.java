/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package icdnigate;

/**
 *
 * @author lincoln R
 */

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ICDNIGate extends JFrame{

    private JTabbedPane iGate = new JTabbedPane();
    private JPanel gatePerformance = new JPanel();
    private JPanel manuals = new JPanel();
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuItemSetting = new JMenuItem("Manuals..");

    //Gate Performance
    private JLabel gPTitle = new JLabel("REPORT");
    
    private JLabel twenties = new JLabel("20");
    private JLabel forties = new JLabel("40");
    private JLabel totalUnits = new JLabel("Units");
    private JLabel teus = new JLabel("TEUs");
    
    private JLabel imports = new JLabel("Imports");
    private JLabel importsT = new JLabel("0");
    private JLabel importsTE = new JLabel("0");
    private JLabel total = new JLabel("Total");
    private JLabel total20 = new JLabel("0");
    private JLabel total40 = new JLabel("0");
    private JLabel totalT = new JLabel("0");
    private JLabel totalTE = new JLabel("0");
    private JLabel exports = new JLabel("Exports");
    private JLabel exportsT = new JLabel("0");
    private JLabel exportsTE = new JLabel("0");
    private JLabel empties = new JLabel("Empties");
    private JLabel emptiesT = new JLabel("0");
    private JLabel emptiesTE = new JLabel("0");
    private JLabel totals = new JLabel("Totals");
    private JLabel totals20 = new JLabel("0");
    private JLabel totals40 = new JLabel("0");
    private JLabel totalsT = new JLabel("0");
    private JLabel totalsTE = new JLabel("0");

    private JTextField imports20 = new JTextField(7);
    private JTextField imports40 = new JTextField(7);
    private JTextField exports20 = new JTextField(7);
    private JTextField exports40 = new JTextField(7);
    private JTextField empties20 = new JTextField(7);
    private JTextField empties40 = new JTextField(7);

    private JButton emailSend = new JButton("Email");
    
    //MANUALS
    private JLabel mTitle = new JLabel("MANUALS");
    
    private JLabel mTwenties = new JLabel("20");
    private JLabel mForties = new JLabel("40");
    private JLabel mTotalUnits = new JLabel("Units");
    private JLabel mTeus = new JLabel("TEUs");
    
    private JLabel krt = new JLabel("KR Transit");
    private JLabel krtT = new JLabel("0");
    private JLabel krtTE = new JLabel("0");
    private JLabel atp = new JLabel("Autoports");
    private JLabel atpT = new JLabel("0");
    private JLabel atpTE = new JLabel("0");
    private JLabel sicd = new JLabel("SICD");
    private JLabel sicdT = new JLabel("0");
    private JLabel sicdTE = new JLabel("0");
    private JLabel mEmpties = new JLabel("Empties");
    private JLabel mEmptiesT = new JLabel("0");
    private JLabel mEmptiesTE = new JLabel("0");
    private JLabel mOthers = new JLabel("Others");
    private JLabel mOthersT = new JLabel("0");
    private JLabel mOthersTE = new JLabel("0");
    
    private JTextField krt20 = new JTextField(7);
    private JTextField krt40 = new JTextField(7);
    private JTextField atp20 = new JTextField(7);
    private JTextField atp40 = new JTextField(7);
    private JTextField sicd20 = new JTextField(7);
    private JTextField sicd40 = new JTextField(7);
    private JTextField mEmpties20 = new JTextField(7);
    private JTextField mEmpties40 = new JTextField(7);
    private JTextField mOthers20 = new JTextField(7);
    private JTextField mOthers40 = new JTextField(7);
    
    private JPanel othersP = new JPanel();
    private JCheckBox others = new JCheckBox("Others");
    
    private JButton mSubmit = new JButton("Submit");
	
    //private JFilePicker filePicker = new JFilePicker("Attached", "Attach File...");	
    //private JTextArea textAreaMessage = new JTextArea(10, 30);
	
    private GridBagConstraints constraints = new GridBagConstraints();        
	
    public ICDNIGate() {
        super("Gate Performance");

        // set up layout
        gatePerformance.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        manuals.setLayout(new GridBagLayout());

        setupMenu();
        setupForm();

        imports20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	imports40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	exports20.addKeyListener(new java.awt.event.KeyListener() { 
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	exports40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	empties20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	empties40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
        
        //Manuals - KE
        krt20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	krt40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	atp20.addKeyListener(new java.awt.event.KeyListener() { 
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	atp40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	sicd20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	sicd40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
        mEmpties20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	mEmpties40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
        mOthers20.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
	mOthers40.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyPressed(KeyEvent e) { JTextFieldValidation(e); }
            @Override
            public void keyReleased(KeyEvent e) { JTextFieldValidation(e); }
        });
                
        pack();
        setLocationRelativeTo(null);	// center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
    }

    private void setupMenu() {
        menuItemSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                    //EmailDialog dialog = new EmailDialog(ICDNIGate.this);
                    //dialog.setVisible(true);
            }
        });

        menuFile.add(menuItemSetting);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);		
    }

    private void setupForm() {
        //Title
        constraints.gridx = 0; constraints.gridy = 0; gatePerformance.add(gPTitle, constraints);

        //titles
        constraints.gridx = 1; constraints.gridy = 1; gatePerformance.add(twenties, constraints);               
        constraints.gridx = 2; constraints.gridy = 1; gatePerformance.add(forties, constraints);                
        constraints.gridx = 3; constraints.gridy = 1; gatePerformance.add(totalUnits, constraints);                
        constraints.gridx = 4; constraints.gridy = 1; gatePerformance.add(teus, constraints);

        //Imports
        constraints.gridx = 0; constraints.gridy = 2; gatePerformance.add(imports, constraints);		
        constraints.gridx = 1; gatePerformance.add(imports20, constraints);		
        constraints.gridx = 2; gatePerformance.add(imports40, constraints);		
        constraints.gridx = 3; gatePerformance.add(importsT, constraints);		
        constraints.gridx = 4;
        //constraints.fill = GridBagConstraints.NONE;
        gatePerformance.add(importsTE, constraints);

        //Total - Imports
        constraints.gridx = 0; constraints.gridy = 3; gatePerformance.add(total, constraints);		
        constraints.gridx = 1; gatePerformance.add(total20, constraints);		
        constraints.gridx = 2; gatePerformance.add(total40, constraints);		
        constraints.gridx = 3; gatePerformance.add(totalT, constraints);		
        constraints.gridx = 4; gatePerformance.add(totalTE, constraints);

        //Exports
        constraints.gridx = 0; constraints.gridy = 4; gatePerformance.add(exports, constraints);		
        constraints.gridx = 1; gatePerformance.add(exports20, constraints);		
        constraints.gridx = 2; gatePerformance.add(exports40, constraints);		
        constraints.gridx = 3; gatePerformance.add(exportsT, constraints);		
        constraints.gridx = 4; gatePerformance.add(exportsTE, constraints);

        //Empties
        constraints.gridx = 0; constraints.gridy = 5; gatePerformance.add(empties, constraints);		
        constraints.gridx = 1; gatePerformance.add(empties20, constraints);		
        constraints.gridx = 2; gatePerformance.add(empties40, constraints);		
        constraints.gridx = 3; gatePerformance.add(emptiesT, constraints);		
        constraints.gridx = 4; gatePerformance.add(emptiesTE, constraints);

        //Totals
        constraints.gridx = 0; constraints.gridy = 6; gatePerformance.add(totals, constraints);		
        constraints.gridx = 1; gatePerformance.add(totals20, constraints);		
        constraints.gridx = 2; gatePerformance.add(totals40, constraints);		
        constraints.gridx = 3; gatePerformance.add(totalsT, constraints);		
        constraints.gridx = 4; gatePerformance.add(totalsTE, constraints);

        constraints.gridx = 4;
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.BOTH;
        emailSend.setFont(new Font("Arial", Font.BOLD, 16));
        gatePerformance.add(emailSend, constraints);

        emailSend.addActionListener((ActionEvent event) -> {
            //emailSendActionPerformed();
            GPEmail dialog;
            try {
                dialog = new GPEmail(ICDNIGate.this);
                dialog.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ICDNIGate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        iGate.addTab("Gate Performance", gatePerformance);

        //Manuals - Header
        constraints.gridx = 0; constraints.gridy = 0; manuals.add(mTitle, constraints);

        //titles
        constraints.gridx = 1; constraints.gridy = 1; manuals.add(mTwenties, constraints);                
        constraints.gridx = 2; constraints.gridy = 1; manuals.add(mForties, constraints);                
        constraints.gridx = 3; constraints.gridy = 1; manuals.add(mTotalUnits, constraints);                
        constraints.gridx = 4; constraints.gridy = 1; manuals.add(mTeus, constraints);

        //KRT
        constraints.gridx = 0; constraints.gridy = 2; manuals.add(krt, constraints);		
        constraints.gridx = 1; manuals.add(krt20, constraints);		
        constraints.gridx = 2; manuals.add(krt40, constraints);		
        constraints.gridx = 3; manuals.add(krtT, constraints);		
        constraints.gridx = 4; manuals.add(krtTE, constraints);

        //Autoports
        constraints.gridx = 0; constraints.gridy = 3; manuals.add(atp, constraints);		
        constraints.gridx = 1; manuals.add(atp20, constraints);		
        constraints.gridx = 2; manuals.add(atp40, constraints);		
        constraints.gridx = 3; manuals.add(atpT, constraints);		
        constraints.gridx = 4; manuals.add(atpTE, constraints);

        //SICD
        constraints.gridx = 0; constraints.gridy = 4; manuals.add(sicd, constraints);		
        constraints.gridx = 1; manuals.add(sicd20, constraints);		
        constraints.gridx = 2; manuals.add(sicd40, constraints);		
        constraints.gridx = 3; manuals.add(sicdT, constraints);		
        constraints.gridx = 4; manuals.add(sicdTE, constraints);

        //Empties - Manuals
        constraints.gridx = 0; constraints.gridy = 5; manuals.add(mEmpties, constraints);		
        constraints.gridx = 1; manuals.add(mEmpties20, constraints);		
        constraints.gridx = 2; manuals.add(mEmpties40, constraints);		
        constraints.gridx = 3; manuals.add(mEmptiesT, constraints);		
        constraints.gridx = 4; manuals.add(mEmptiesTE, constraints);
        
        constraints.gridx = 1; constraints.gridy = 6; manuals.add(others, constraints);
        /*others.addActionListener((ActionEvent event) -> {
            JCheckBox cb = (JCheckBox) event.getSource();
            if (cb.isSelected()){ othersP.setVisible(true); } 
            else{ othersP.setVisible(false); }
        });*/
        
        //Others - Manuals
        othersP.add(mOthers); othersP.add(mOthers20); othersP.add(mOthers40); othersP.add(mOthersT); othersP.add(mOthersTE);
        
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridheight = 1;
        constraints.gridwidth = 5;
        manuals.add(othersP, constraints);
        //othersP.setVisible(false);
        
        constraints.gridx = 4;
        constraints.gridy = 8;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        mSubmit.setFont(new Font("Arial", Font.BOLD, 16));
        manuals.add(mSubmit, constraints);

        mSubmit.addActionListener((ActionEvent event) -> { manualsSendActionPerformed(); });
        
        iGate.addTab("Manuals", manuals);

        add(iGate);
    }
    private void JTextFieldValidation(java.awt.event.KeyEvent evt) {      
        char c = evt.getKeyChar();
        int d = evt.getKeyCode();
        if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_TAB 
           || d == KeyEvent.VK_LEFT || d == KeyEvent.VK_RIGHT)){ evt.consume(); }
    else{
        int imports2 = imports20.getText().isBlank() ? 0 : Integer.parseInt(imports20.getText());
        int imports4 = imports40.getText().isBlank() ? 0 : Integer.parseInt(imports40.getText());
        int importsTotal = imports2 + imports4;
        int importsTEUs = importsTotal + imports4;            

        importsT.setText(String.valueOf(importsTotal));
        importsTE.setText(String.valueOf(importsTEUs));

        total20.setText(String.valueOf(imports2));
        total40.setText(String.valueOf(imports4));
        totalT.setText(String.valueOf(importsTotal));
        totalTE.setText(String.valueOf(importsTEUs));

        int exports2 = exports20.getText().isBlank() ? 0 : Integer.parseInt(exports20.getText());
        int exports4 = exports40.getText().isBlank() ? 0 : Integer.parseInt(exports40.getText());
        int exportsTotal = exports2 + exports4;
        int exportsTEUs = exportsTotal + exports4;            

        exportsT.setText(String.valueOf(exportsTotal));
        exportsTE.setText(String.valueOf(exportsTEUs));

        int empties2 = empties20.getText().isBlank() ? 0 : Integer.parseInt(empties20.getText());
        int empties4 = empties40.getText().isBlank() ? 0 : Integer.parseInt(empties40.getText());
        int emptiesTotal = empties2 + empties4;
        int emptiesTEUs = emptiesTotal + empties4;            

        emptiesT.setText(String.valueOf(emptiesTotal));
        emptiesTE.setText(String.valueOf(emptiesTEUs));

        int totals2 = imports2 + exports2 + empties2;
        int totals4 = imports4 + exports4 + empties4;
        int totalsTotal = totals2 + totals4;
        int totalsTEUs = totalsTotal + totals4;           

        totals20.setText(String.valueOf(totals2));
        totals40.setText(String.valueOf(totals4));
        totalsT.setText(String.valueOf(totalsTotal));
        totalsTE.setText(String.valueOf(totalsTEUs));
        
        //Manuals - Validation
        int krt2 = krt20.getText().isBlank() ? 0 : Integer.parseInt(krt20.getText());
        int krt4 = krt40.getText().isBlank() ? 0 : Integer.parseInt(krt40.getText());
        int krtTotal = krt2 + krt4;
        int krtTEUs = krtTotal + krt4;            

        krtT.setText(String.valueOf(krtTotal));
        krtTE.setText(String.valueOf(krtTEUs));
        
        int atp2 = atp20.getText().isBlank() ? 0 : Integer.parseInt(atp20.getText());
        int atp4 = atp40.getText().isBlank() ? 0 : Integer.parseInt(atp40.getText());
        int atpTotal = atp2 + atp4;
        int atpTEUs = atpTotal + atp4;            

        atpT.setText(String.valueOf(atpTotal));
        atpTE.setText(String.valueOf(atpTEUs));
        
        int sicd2 = sicd20.getText().isBlank() ? 0 : Integer.parseInt(sicd20.getText());
        int sicd4 = sicd40.getText().isBlank() ? 0 : Integer.parseInt(sicd40.getText());
        int sicdTotal = sicd2 + sicd4;
        int sicdTEUs = sicdTotal + sicd4;            

        sicdT.setText(String.valueOf(sicdTotal));
        sicdTE.setText(String.valueOf(sicdTEUs));
        
        int mEmpties2 = mEmpties20.getText().isBlank() ? 0 : Integer.parseInt(mEmpties20.getText());
        int mEmpties4 = mEmpties40.getText().isBlank() ? 0 : Integer.parseInt(mEmpties40.getText());
        int mEmptiesTotal = mEmpties2 + mEmpties4;
        int mEmptiesTEUs = mEmptiesTotal + mEmpties4;            

        mEmptiesT.setText(String.valueOf(mEmptiesTotal));
        mEmptiesTE.setText(String.valueOf(mEmptiesTEUs));
        
        int mOthers2 = mOthers20.getText().isBlank() ? 0 : Integer.parseInt(mOthers20.getText());
        int mOthers4 = mOthers40.getText().isBlank() ? 0 : Integer.parseInt(mOthers40.getText());
        int mOthersTotal = mOthers2 + mOthers4;
        int mOthersTEUs = mOthersTotal + mOthers4;            

        mOthersT.setText(String.valueOf(mOthersTotal));
        mOthersTE.setText(String.valueOf(mOthersTEUs));
        }
    }
    
    private void emailSendActionPerformed() {
        //if (!validateFields()) { return; }
        Date cDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MMM-yyy");        
        Calendar c = Calendar.getInstance();
        c.setTime(cDate);
        c.add(Calendar.DATE, -1);
        Date yDate = c.getTime();        
        String dateS2 = ft.format(yDate);
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateS4 = ft2.format(yDate);
        String month = LocalDate.now().minusDays(1).getMonth().toString().substring(0, 3);

        int imp20 = imports20.getText().isBlank() ? 0 : Integer.parseInt(imports20.getText()); 
        int exp20 = exports20.getText().isBlank() ? 0 : Integer.parseInt(exports20.getText()); 
        int emp20 = empties20.getText().isBlank() ? 0 : Integer.parseInt(empties20.getText()); 
        int imp40 = imports40.getText().isBlank() ? 0 : Integer.parseInt(imports40.getText()); 
        int exp40 = exports40.getText().isBlank() ? 0 : Integer.parseInt(exports40.getText()); 
        int emp40 = empties40.getText().isBlank() ? 0 : Integer.parseInt(empties40.getText());
        int tI20 = imp20, tI40 = imp40, tIUnits = tI20 + tI40, tITEUs = tIUnits + imp40;
        int tE20 = exp20 + emp20, tE40 = exp40 + emp40, tEUnits = tE20 + tE40, tETEUs = tEUnits + tE40;
        int ts20 = tI20 + exp20 + emp20, ts40 = tI40 + exp40 + emp40, tsTUnits = ts20 + ts40, tsTEUs = tsTUnits + ts40;

        List<Integer> dataList = new ArrayList<>();
        dataList.add(imp20); dataList.add(imp40); dataList.add(exp20); dataList.add(exp40); dataList.add(emp20);
        dataList.add(emp40); 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/igate","root","*?lhf2JHG");
            
            int mTotal20, mTotal40, eDel20, eDel40;
            String query = "select * from gateperformance where gpDate= SUBDATE(CURDATE(), 1) AND "
                         + "(krt20>1 OR krt40>1 OR atp20>1 OR atp40>1 OR sic20>1 OR sic40>1 OR empdel20>1 or empdel40>1)";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){                
                //while(rs.next()){
                    mTotal20 = rs.getInt("krt20")+rs.getInt("atp20")+rs.getInt("sic20");
                    mTotal40 = rs.getInt("krt40")+rs.getInt("atp40")+rs.getInt("sic40");
                    eDel20 = rs.getInt("empdel20"); eDel40 = rs.getInt("empdel40");
                    tI20 += mTotal20; tI40 += mTotal40;
                    tIUnits = tI20 + tI40; tITEUs = tIUnits + tI40;
                    tE20 += eDel20; tE40 += eDel40;
                    tEUnits = tE20 + tE40; tETEUs = tEUnits + tE40; 
                    ts20 = tI20 + tE20; ts40 = tI40 + tE40;
                    tsTUnits = ts20 + ts40; tsTEUs = tsTUnits + ts40;
                    
                //}
                PreparedStatement pst = con.prepareStatement("UPDATE gateperformance "
                                      + "SET imports20=?, imports40=?, exports20=?, exports40=?, empties20=?, empties40=?,"
                                      + " totalImports20=?, totalImports40=?, totalImportsTU=?, totalImportsTEUs=?,"
                                      + " totals20=?, totals40=?, totalsTU=?, totalsTEUs=?"
                                      + " WHERE gpDate = SUBDATE(CURDATE(), 1)");

                pst.setInt(1,imp20); pst.setInt(2,imp40);
                pst.setInt(3,exp20); pst.setInt(4,exp40);
                pst.setInt(5,emp20); pst.setInt(6,emp40);
                
                pst.setInt(7,tI20); pst.setInt(8,tI40);
                pst.setInt(9,tIUnits); pst.setInt(10,tITEUs);
                pst.setInt(11,ts20); pst.setInt(12,ts40);
                pst.setInt(13,tsTUnits); pst.setInt(14,tsTEUs);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Record Added");

                String excelFilePath = "D:\\gatePerformance\\ICDN GATE PERFORMANCE.xlsx";  // provide your excel file path
                Workbook workbook;
                try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
                    workbook = WorkbookFactory.create(fileInputStream);
                    org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(month.substring(0,3));
                    // Till there is an element condition holds true
                    for (org.apache.poi.ss.usermodel.Row row : sheet) {
                        if(row.getCell(0).toString() == null ? dateS2 == null : row.getCell(0).toString().equals(dateS2)){    
                            row.getCell(1).setCellValue(dataList.get(0));
                            row.getCell(2).setCellValue(dataList.get(1));
                            
                            row.getCell(3).setCellValue(mTotal20);
                            row.getCell(4).setCellValue(mTotal40);
                            
                            row.getCell(5).setCellValue(tIUnits);
                            row.getCell(6).setCellValue(tITEUs);

                            row.getCell(7).setCellValue(dataList.get(2));
                            row.getCell(8).setCellValue(dataList.get(3));

                            row.getCell(9).setCellValue(dataList.get(4));
                            row.getCell(10).setCellValue(dataList.get(5));
                            
                            row.getCell(11).setCellValue(eDel20);
                            row.getCell(12).setCellValue(eDel40);
                            
                            row.getCell(13).setCellValue(tEUnits);
                            row.getCell(14).setCellValue(tETEUs);
                            
                            row.getCell(15).setCellValue(tsTEUs);
                        }
                        //System.out.println(row.getCell(0).toString());
                    }
                    try (FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath)){
                        workbook.write(fileOutputStream);
                    }System.out.println("excel sheet updated successfully........");
                }
            }
            else{
                PreparedStatement pst = con.prepareStatement("insert into gateperformance "
                        + "(gpDate, imports20, imports40, exports20, exports40, empties20, empties40,"
                        + " totalImports20, totalImports40, totalImportsTU, totalImportsTEUs,"
                        + " totals20, totals40, totalsTU, totalsTEUs)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,dateS4);
                pst.setInt(2,imp20); pst.setInt(3,imp40);
                pst.setInt(4,exp20); pst.setInt(5,exp40);
                pst.setInt(6,emp20); pst.setInt(7,emp40);
                
                pst.setInt(8,tI20); pst.setInt(9,tI40);
                pst.setInt(10,tIUnits); pst.setInt(11,tITEUs);
                pst.setInt(12,ts20); pst.setInt(13,ts40);
                pst.setInt(14,tsTUnits); pst.setInt(15,tsTEUs);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Record Added");

                String excelFilePath = "D:\\gatePerformance\\ICDN GATE PERFORMANCE.xlsx";  // provide your excel file path
                Workbook workbook;
                try (FileInputStream fileInputStream = new FileInputStream(excelFilePath)) {
                    workbook = WorkbookFactory.create(fileInputStream);
                    org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(month.substring(0,3));
                    // Till there is an element condition holds true
                    for (org.apache.poi.ss.usermodel.Row row : sheet) {
                        if(row.getCell(0).toString() == null ? dateS2 == null : row.getCell(0).toString().equals(dateS2)){    
                            row.getCell(1).setCellValue(dataList.get(0));
                            row.getCell(2).setCellValue(dataList.get(1));
                            
                            row.getCell(3).setCellValue(0);
                            row.getCell(4).setCellValue(0);
                            
                            row.getCell(5).setCellValue(tIUnits);
                            row.getCell(6).setCellValue(tITEUs);

                            row.getCell(7).setCellValue(dataList.get(2));
                            row.getCell(8).setCellValue(dataList.get(3));

                            row.getCell(9).setCellValue(dataList.get(4));
                            row.getCell(10).setCellValue(dataList.get(5));
                            
                            row.getCell(11).setCellValue(0);
                            row.getCell(12).setCellValue(0);
                            
                            row.getCell(13).setCellValue(tEUnits);
                            row.getCell(14).setCellValue(tETEUs);
                            
                            row.getCell(15).setCellValue(tsTEUs);
                        }
                    }
                    try (FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath)){
                        workbook.write(fileOutputStream);
                    }System.out.println("excel sheet updated successfully........");
                }
            }            
        }
        catch (SQLException ex) {
        }catch(ClassNotFoundException | IOException ex) {
            Logger.getLogger(ICDNIGate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void manualsSendActionPerformed() {
        //String cdate = (LocalDate.now()).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
	System.out.println("Current Date : " + dateFormat.format(date));
        Calendar c2 = Calendar.getInstance();
	c2.setTime(date);
        c2.add(Calendar.HOUR, -7);
        Date udate = c2.getTime();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        
        int krtr20 = krt20.getText().isBlank() ? 0 : Integer.parseInt(krt20.getText());
        int krtr40 = krt40.getText().isBlank() ? 0 : Integer.parseInt(krt40.getText());
        int atps20 = atp20.getText().isBlank() ? 0 : Integer.parseInt(atp20.getText());
        int atps40 = atp40.getText().isBlank() ? 0 : Integer.parseInt(atp40.getText());
        int sy20 = sicd20.getText().isBlank() ? 0 : Integer.parseInt(sicd20.getText());
        int sy40 = sicd40.getText().isBlank() ? 0 : Integer.parseInt(sicd40.getText());
        int empdel20 = mEmpties20.getText().isBlank() ? 0 : Integer.parseInt(mEmpties20.getText());
        int empdel40 = mEmpties40.getText().isBlank() ? 0 : Integer.parseInt(mEmpties40.getText());
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/igate","root","*?lhf2JHG");
            PreparedStatement pst = con.prepareStatement("insert into gateperformance "
                                    + "(gpDate, krt20, krt40, atp20, atp40, sic20, sic40, empdel20, empdel40)"
                                                        + "values(?,?,?,?,?,?,?,?,?)");

            pst.setString(1, dateFormat2.format(udate));
            pst.setInt(2,krtr20); pst.setInt(3,krtr40);
            pst.setInt(4,atps20); pst.setInt(5,atps40);
            pst.setInt(6,sy20); pst.setInt(7,sy40);
            pst.setInt(8,empdel20); pst.setInt(9,empdel40);
                        
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Manuals Added");
        }
        catch (SQLException ex) {
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(ICDNIGate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*private boolean validateFields() {
        if (fieldTo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter To address!", "Error", JOptionPane.ERROR_MESSAGE);
            fieldTo.requestFocus();
            return false;
        }

        if (fieldSubject.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter subject!", "Error", JOptionPane.ERROR_MESSAGE);
            fieldSubject.requestFocus();
            return false;
        }

        if (textAreaMessage.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter message!", "Error", JOptionPane.ERROR_MESSAGE);
            textAreaMessage.requestFocus();
            return false;
        }
        return true;
    }*/
    public static void main(String[] args) {
        // TODO code application logic here
        // set look and feel to system dependent
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
        }

        SwingUtilities.invokeLater(() -> {
            new ICDNIGate().setVisible(true);
        });
    }    
}
