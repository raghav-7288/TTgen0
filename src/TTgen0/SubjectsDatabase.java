/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTgen0;

import static TTgen0.ClassesDatabase.ClassIds;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 91870
 */
public class SubjectsDatabase extends javax.swing.JFrame {

    /**
     * Creates new form TeachersDatabase
     */
    Connection myCon=null;
    Statement myStat=null;
    ResultSet myResult=null;
    String TableName,TableName0;
    public static String[] SubjectIds=new String[0];


    
    public SubjectsDatabase() {
        initComponents();
        try{
        TableName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_subjects").toUpperCase();
        TableName0=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_Selected_Teacher_Detail").toUpperCase();
        }
        catch(NullPointerException e){
        TableName="btech_cse_1_subjects".toUpperCase();
        }
        SelectedTeacherDetailTable();
        ViewData();
    }
    
    public void ViewData()
    {
        try{
        myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
        myStat=myCon.createStatement();
        
        DatabaseMetaData dbm =myCon.getMetaData();
        ResultSet tables = dbm.getTables(null, null, TableName, null);
        
        if(tables.next()){
        myResult=myStat.executeQuery("Select * from "+TableName);
        SubjectsTable.setModel(DbUtils.resultSetToTableModel(myResult));
        }
        else{
            DefaultTableModel dm = (DefaultTableModel) SubjectsTable.getModel();
            dm.setRowCount(0);
        } 
    }
        catch(SQLException e){
            e.printStackTrace();
                }
       
    }
    
    public void SelectedTeacherDetailTable(){

        try{ 
            myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
            myStat=myCon.createStatement();
            DatabaseMetaData dbm = myCon.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName, null);

            if(tables.next()){
            myResult=myStat.executeQuery("Select SUBJECTID from "+TableName);
            List a = new ArrayList();
            while (myResult.next()) {
            a.add(myResult.getString("SUBJECTID"));
                }               
            SubjectIds = new String[a.size()];
            SubjectIds = (String[]) a.toArray(new String[a.size()]);
            //for(int i=0;i<SubjectIds.length;i++){System.out.println(SubjectIds[i]);}
            }
        }
        catch(SQLException e){
                e.printStackTrace();
                }
        
        if(ClassIds.length!=0 && SubjectIds.length!=0){
        
            try{
                myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
                myStat=myCon.createStatement();
                DatabaseMetaData dbm =myCon.getMetaData();
                ResultSet tables = dbm.getTables(null, null, TableName0, null);
                String query="create table "+TableName0+" (SUBJECT_IDS VARCHAR(10),";
                String query1="insert into "+TableName0+" values(?,";

                for(int k=0;k<ClassIds.length;k++){
                query=query+ClassIds[k]+" VARCHAR(10),";
                }
                query=query.substring(0, query.length()-1)+")";

                for(int k=0;k<ClassIds.length;k++){
                query1=query1+"?,";
                }
                query1=query1.substring(0, query1.length()-1)+")";

                if(tables.next()){
                    String query2="drop table "+TableName0;       
                    Statement delete=myCon.createStatement();
                    delete.executeUpdate(query2);
                }

                    myStat=myCon.createStatement();
                    myStat.executeUpdate(query);

                for(int i=1;i<SubjectIds.length;i++){                    
                    PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query1);
                    insert.setString(1,SubjectIds[i]);
                    for(int k=2;k<ClassIds.length+2;k++)
                        insert.setString(k,"");
                        int row=insert.executeUpdate();
                }
             }
            catch(SQLException e){
                    e.printStackTrace();
                    }
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        SubjectsTable = new javax.swing.JTable();
        SubjectName = new javax.swing.JTextField();
        SubjectType = new javax.swing.JTextField();
        SubjectId = new javax.swing.JTextField();
        TeacherId = new javax.swing.JTextField();
        Load = new javax.swing.JTextField();
        Delete = new javax.swing.JLabel();
        Edit = new javax.swing.JLabel();
        Add = new javax.swing.JLabel();
        Back = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SubjectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Subject Name", "Subject Type ", "Subject Id", "Load"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(SubjectsTable);
        if (SubjectsTable.getColumnModel().getColumnCount() > 0) {
            SubjectsTable.getColumnModel().getColumn(0).setPreferredWidth(400);
            SubjectsTable.getColumnModel().getColumn(1).setResizable(false);
            SubjectsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            SubjectsTable.getColumnModel().getColumn(2).setResizable(false);
            SubjectsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            SubjectsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 760, 320));

        SubjectName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SubjectName.setForeground(new java.awt.Color(255, 255, 255));
        SubjectName.setBorder(null);
        SubjectName.setOpaque(false);
        jPanel1.add(SubjectName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 220, 30));
        SubjectName.getAccessibleContext().setAccessibleName("TextName");

        SubjectType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SubjectType.setForeground(new java.awt.Color(255, 255, 255));
        SubjectType.setBorder(null);
        SubjectType.setOpaque(false);
        jPanel1.add(SubjectType, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 90, 30));

        SubjectId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SubjectId.setForeground(new java.awt.Color(255, 255, 255));
        SubjectId.setBorder(null);
        SubjectId.setOpaque(false);
        jPanel1.add(SubjectId, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 90, 30));

        TeacherId.setEditable(false);
        TeacherId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TeacherId.setForeground(new java.awt.Color(255, 255, 255));
        TeacherId.setBorder(null);
        jPanel1.add(TeacherId, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 90, 30));

        Load.setEditable(false);
        Load.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Load.setForeground(new java.awt.Color(255, 255, 255));
        Load.setBorder(null);
        jPanel1.add(Load, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, 60, 30));

        Delete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Delete.setText("Delete");
        Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteMouseClicked(evt);
            }
        });
        jPanel1.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 478, 129, 30));

        Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditMouseClicked(evt);
            }
        });
        jPanel1.add(Edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 478, 126, 32));

        Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddMouseClicked(evt);
            }
        });
        jPanel1.add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, 127, 30));

        Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
        });
        jPanel1.add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 478, 126, 30));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Type");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 40, 20));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SubjectsDatabse.png"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("StaffDatabaseFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackMouseClicked
        this.dispose();
        new Dashboard().setVisible(true);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_BackMouseClicked

    private void DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseClicked
    if("".equals(SubjectId.getText()))
    {
    JOptionPane.showMessageDialog(this, "ENTER SUBJECT ID","Error",JOptionPane.WARNING_MESSAGE);}
    else{   
        try{
        String query="DELETE from "+TableName+" where subjectid ='"+SubjectId.getText()+"'";
        Statement add=myCon.createStatement();
        add.executeUpdate(query);
        SubjectId.setText("");
    }                                   
   catch(SQLException e)
        {
            e.printStackTrace();
        }
    ViewData();
    }    // TODO add your handling code here:
    }//GEN-LAST:event_DeleteMouseClicked

    private void EditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseClicked
    if("".equals(SubjectId.getText()))
    {
    JOptionPane.showMessageDialog(this, "ENTER SUBJECT  ID","Error",JOptionPane.WARNING_MESSAGE);}
    else{   
    
        try{
        String query="update "+TableName+" set subjectname ='"+SubjectName.getText()+"' "+",subjectid='"+SubjectId.getText()+"' "+",subjecttype='"+SubjectType.getText()+"' "+"where subjectid ='"+SubjectId.getText()+"'";       
        Statement update=myCon.createStatement();
        update.executeUpdate(query);
        SubjectId.setText("");
        
       
    }                                   
   catch(SQLException e)
        {
            e.printStackTrace();
        }
    ViewData();
    }    // TODO add your handling code here:
    }//GEN-LAST:event_EditMouseClicked

    private void AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMouseClicked
    if("".equals(SubjectName.getText())||"".equals(SubjectId.getText())||"".equals(SubjectType.getText()))
    {JOptionPane.showMessageDialog(this, "ENTER COMPLETE DATA","Error",JOptionPane.WARNING_MESSAGE);
    }
    else{
        try{
            String query="Insert into "+TableName+" values(?,?,?,?)";
            String q1="create table "+TableName+" (SUBJECTNAME VARCHAR(100) not null,SUBJECTTYPE VARCHAR(100) not null,SUBJECTID VARCHAR(100) not null primary key,LOAD INTEGER)";
            String sname=SubjectName.getText();
            String sid=SubjectId.getText();
            String stype=SubjectType.getText();
            
            DatabaseMetaData dbm = myCon.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName,null);
            
            if(!tables.next()){
            myStat=myCon.createStatement();
            myStat.executeUpdate(q1);
            }
            
            PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query);
            insert.setString(1,sname);
            insert.setString(2,stype);
            insert.setString(3, sid);
            insert.setInt(4, 0);
            
            int row=insert.executeUpdate();
            
            SubjectId.setText("");
                  
            }
        catch(SQLException e){
            e.printStackTrace();
        } 
    ViewData();
            }    // TODO add your handling code here:
    }//GEN-LAST:event_AddMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SubjectsDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectsDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectsDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectsDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectsDatabase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add;
    private javax.swing.JLabel BG;
    private javax.swing.JLabel Back;
    private javax.swing.JLabel Delete;
    private javax.swing.JLabel Edit;
    private javax.swing.JTextField Load;
    private javax.swing.JTextField SubjectId;
    private javax.swing.JTextField SubjectName;
    private javax.swing.JTextField SubjectType;
    private javax.swing.JTable SubjectsTable;
    private javax.swing.JTextField TeacherId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
