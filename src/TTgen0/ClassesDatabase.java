/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTgen0;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import static TTgen0.Other.Days;
import static TTgen0.Other.NoOfClasses;

/**
 *
 * @author 91870
 */
public class ClassesDatabase extends javax.swing.JFrame {

    /**
     * Creates new form TeachersDatabase
     */
    Connection myCon=null;
    Statement myStat=null;
    ResultSet myResult=null,myResult2=null;
    String TableName1,TableName2;
    public static String[] ClassIds=new String[0];
    public static String[] AvailableRooms=new String[10];
    String TotalClassLoad[];


    int i,j,k;

    
    public ClassesDatabase() {
        initComponents();
        try{
        TableName1=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_classes").toUpperCase();}
        catch(NullPointerException e){
        TableName1="btech_cse_1_classes".toUpperCase();
        }
        try{
        TableName2=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_classes_loads").toUpperCase();}
        catch(NullPointerException e){
        TableName2="btech_cse_1_classes_loads".toUpperCase();
        }

        
        ViewData();
        String TName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_maxclasscount").toUpperCase();
        try{
                myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");            
                DatabaseMetaData dbm = myCon.getMetaData();
                ResultSet tables = dbm.getTables(null, null,TName,null);


                    if(!tables.next()){
                        //System.out.println("Adding");
                        AddMaxRoomsReq();    
                    }
            }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
    
    public void AddMaxRoomsReq(){
        DefaultTableModel model = (DefaultTableModel) ClassesLoadTable.getModel();
        
        double s=0;
        double noc=Integer.parseInt(NoOfClasses.getSelectedItem().toString());
        double nod=Days.getRowCount();
        
        for(i=0;i<ClassesLoadTable.getRowCount();i++){
        s+=Integer.parseInt(model.getValueAt(i, 0).toString());
        }
        
        int maxRoomsReq=(int)Math.ceil(s/(noc*nod));
        //System.out.println(maxRoomsReq+"%");

        String[] dayNames=new String[]{"Mo","Tu","We","Th","Fr","Sa","Su"};
        String TName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_maxroomsreq").toUpperCase();
        String q1 ="create table "+TName+" (SLOTS VARCHAR(100) ,MAXROOMSREQ INTEGER)";
        String query="Insert into "+TName+" values(?,?)";
        String TName2=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_availablerooms").toUpperCase();
        String q2 ="create table "+TName2+" (ROOMNAME VARCHAR(100))";
        String query2="Insert into "+TName2+" values(?)";
        String TName4=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_Rooms").toUpperCase();

        
        try{
        if(ClassIds.length>0){
            myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
            myStat=myCon.createStatement();
            DatabaseMetaData dbm =myCon.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TName2, null);
            ResultSet tables0 = dbm.getTables(null, null, TName4, null);
           
            if(tables0.next() && ClassIds.length!=0){
                if(!tables.next()){
                myStat=myCon.createStatement();
                myStat.executeUpdate(q2);
                }
                else
                {
                String query1="delete from "+TName2;       
                Statement delete=myCon.createStatement();
                delete.executeUpdate(query1);
                }
                DefaultTableModel model1 = (DefaultTableModel) RoomsDatabase.RoomsTable.getModel();
                for(i=0;i<maxRoomsReq;i++){      
                        PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query2);
                        String RName = model1.getValueAt(i, 1).toString();
                        insert.setString(1,RName);
                        int row=insert.executeUpdate();                
                }
            }
        }
            
        }
        catch(SQLException e){
            e.printStackTrace();
                }

        
        try{
                    if(ClassIds.length>0){

            if(ClassIds.length>0){
                myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
                myStat=myCon.createStatement();
                DatabaseMetaData dbm =myCon.getMetaData();


                ResultSet tables = dbm.getTables(null, null, TName, null);

                if(!tables.next()){

                myStat=myCon.createStatement();
                myStat.executeUpdate(q1);
                }
                else
                {
                String query1="delete from "+TName;       
                Statement delete=myCon.createStatement();
                delete.executeUpdate(query1);
                }

                for(i=0;i<Days.getRowCount();i++){
                    for(j=1;j<=8;j++){                    
                        PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query);
                        insert.setString(1,dayNames[i]+j);
                        insert.setInt(2, maxRoomsReq);
                        int row=insert.executeUpdate();

                    }
                }
            }
                    }
        }
        catch(SQLException e){
            e.printStackTrace();
                }
        
    try{
        DatabaseMetaData dbm =myCon.getMetaData();
        ResultSet tables3 = dbm.getTables(null, null, TName2, null);

        if(tables3.next()){
            
                String query1="Select ROOMNAME from "+TName2;
                myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
                myStat=myCon.createStatement();
                myResult2=myStat.executeQuery(query1);
                List e = new ArrayList();
                while (myResult2.next()) {
                e.add(myResult2.getString(1));
                    }
                AvailableRooms = new String[e.size()];
                AvailableRooms = (String[]) e.toArray(new String[e.size()]);


            if(AvailableRooms.length>0){
                String TName3=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_arrangerooms").toUpperCase();
                
                ResultSet tables = dbm.getTables(null, null, TName3, null);
       
                String a="create table "+TName3+" ( DAY VARCHAR(100),";
                String b="";
                for(i=0;i<AvailableRooms.length;i++){
                b=b+" R"+AvailableRooms[i]+" INTEGER,";
                }
                String q3=a+b.substring(0,b.length()-1)+")";

                if(tables.next()){
                    String query3="drop table "+TName3;       
                    Statement delete=myCon.createStatement();
                    delete.executeUpdate(query3);  
                }
                
                myStat=myCon.createStatement();
                myStat.executeUpdate(q3);
                
                String query0="Insert into "+TName3+" values(?,";
                String z="";
                for(i=0;i<AvailableRooms.length;i++){
                z=z+"?,";
                }
                query0=query0+z.substring(0,z.length()-1)+")";

                for(i=0;i<Days.getRowCount();i++){
                    for(j=1;j<=8;j++){                    
                        PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query0);
                        insert.setString(1,dayNames[i]+j);
                        for(k=2;k<AvailableRooms.length+2;k++){
                        insert.setInt(k, 0);}
                        int row=insert.executeUpdate();

                    }
                }
            }
          }

        }
        catch(SQLException e){
            e.printStackTrace();
                }
        
    
    
    
    }
    
    public void AddLoadData(){
        String TName;
        try{ 
            myStat=myCon.createStatement();
            DatabaseMetaData dbm = myCon.getMetaData();
            
            
            ResultSet tables5 = dbm.getTables(null, null, TableName1, null);

            if(tables5.next()){
            myResult=myStat.executeQuery("Select CLASSID from "+TableName1);
            List a = new ArrayList();
            while (myResult.next()) {
            a.add(myResult.getString(1));
                }   
            
            ClassIds = new String[a.size()];

            ClassIds = (String[]) a.toArray(new String[a.size()]);
         //   for(i=0;i<ClassIds.length;i++){System.out.println(ClassIds[i]);}
            }
            
            
            ResultSet tables1 = dbm.getTables(null, null, TableName2, null);
            String q2 ="create table "+TableName2+" (CLASSID VARCHAR (100),LOAD INTEGER)";
            
            if(tables1.next()){

            String query1="delete from "+TableName2;       
            Statement delete=myCon.createStatement();
            delete.executeUpdate(query1);
          //  System.out.println("done1");
            }else
            {
                if(ClassIds.length>0){
            myStat=myCon.createStatement();
            myStat.executeUpdate(q2);
          //  System.out.println("done2");
                }
            }
            
        
            
            
        for(i=0;i<ClassIds.length;i++){
            
          //  System.out.println(ClassIds.length+"^");

            TName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_"+ClassIds[i]+"_subjects").toUpperCase();
            int load=0;
            
           
            ResultSet tables = dbm.getTables(null, null, TName, null);
            
            if(tables.next()){
                
                myResult=myStat.executeQuery("Select LOAD from "+TName);
                List b = new ArrayList();
                while (myResult.next()) {
                b.add(myResult.getString(1));
                    }   
                
                TotalClassLoad = new String[b.size()];
                
                TotalClassLoad = (String[]) b.toArray(new String[b.size()]);
                
                
                    for(k=0;k<TotalClassLoad.length;k++){
                    load+=(Integer.parseInt(TotalClassLoad[k]));
                }
                  // System.out.println(load+"*");
                
                String query="Insert into "+TableName2+" values(?,?)";
                PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query);
                insert.setString(1, ClassIds[i]);
                insert.setInt(2, load);
                int row=insert.executeUpdate();
  
            }
            else{
                
                String query="Insert into "+TableName2+" values(?,?)";
                PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query);
                insert.setString(1, ClassIds[i]);
                insert.setInt(2, load);   
                int row=insert.executeUpdate();
            
         
            }
        }
        
        }
        catch(SQLException e){
            e.printStackTrace();
                }
    
    }
    
    public void ViewData()
    {
        try{
        myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
        myStat=myCon.createStatement();
        DatabaseMetaData dbm =myCon.getMetaData();
        ResultSet tables = dbm.getTables(null, null, TableName1, null);
        ResultSet tables1 = dbm.getTables(null, null, TableName2, null);
        
        
        AddLoadData();
        if(tables.next()){
        myResult=myStat.executeQuery("Select * from "+TableName1);
        ClassesTable.setModel(DbUtils.resultSetToTableModel(myResult));
        }
        else{
            DefaultTableModel dm = (DefaultTableModel) ClassesTable.getModel();
            dm.setRowCount(0);
        }
        
        
        
        if(tables1.next()){
        myResult=myStat.executeQuery("Select LOAD from "+TableName2);
        ClassesLoadTable.setModel(DbUtils.resultSetToTableModel(myResult));
        }
        else{
            DefaultTableModel dm = (DefaultTableModel) ClassesLoadTable.getModel();
            dm.setRowCount(0);
        }
        
        
        
        
    }
        catch(SQLException e){
            e.printStackTrace();
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
        ClassesTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ClassesLoadTable = new javax.swing.JTable();
        ClassName = new javax.swing.JTextField();
        ClassId = new javax.swing.JTextField();
        Subjects = new javax.swing.JTextField();
        Ainvyyy = new javax.swing.JTextField();
        ViewSubjects = new javax.swing.JLabel();
        Delete = new javax.swing.JLabel();
        Edit = new javax.swing.JLabel();
        Add = new javax.swing.JLabel();
        Back = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ClassesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Class Name", "Class Id", "Subjects"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ClassesTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 600, 320));

        ClassesLoadTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Load"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ClassesLoadTable);
        if (ClassesLoadTable.getColumnModel().getColumnCount() > 0) {
            ClassesLoadTable.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 160, 320));

        ClassName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ClassName.setForeground(new java.awt.Color(255, 255, 255));
        ClassName.setBorder(null);
        ClassName.setOpaque(false);
        jPanel1.add(ClassName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 220, 30));
        ClassName.getAccessibleContext().setAccessibleName("TextName");

        ClassId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ClassId.setForeground(new java.awt.Color(255, 255, 255));
        ClassId.setBorder(null);
        ClassId.setOpaque(false);
        jPanel1.add(ClassId, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 90, 30));

        Subjects.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Subjects.setForeground(new java.awt.Color(255, 255, 255));
        Subjects.setBorder(null);
        Subjects.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Subjects.setOpaque(false);
        jPanel1.add(Subjects, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 90, 30));

        Ainvyyy.setEditable(false);
        Ainvyyy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Ainvyyy.setForeground(new java.awt.Color(255, 255, 255));
        Ainvyyy.setBorder(null);
        jPanel1.add(Ainvyyy, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 390, 40, 30));

        ViewSubjects.setBackground(new java.awt.Color(255, 255, 204));
        ViewSubjects.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ViewSubjects.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ViewSubjects.setText("View Subjects");
        ViewSubjects.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewSubjects.setOpaque(true);
        ViewSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewSubjectsMouseClicked(evt);
            }
        });
        jPanel1.add(ViewSubjects, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 100, 30));

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
        jPanel1.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 129, 30));

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
        jPanel1.add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 478, 127, 30));

        Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackMouseClicked(evt);
            }
        });
        jPanel1.add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 478, 126, 30));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ClassDatabse.png"))); // NOI18N
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
        String TName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_maxclasscount").toUpperCase();
        try{
                myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");            
                DatabaseMetaData dbm = myCon.getMetaData();
                ResultSet tables = dbm.getTables(null, null,TName,null);


                    if(!tables.next()){
                        AddMaxRoomsReq();    
                    }
            }
        catch(SQLException e){
            e.printStackTrace();
        }
        this.dispose();
        new Dashboard().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_BackMouseClicked

    private void DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseClicked
    if("".equals(ClassId.getText()))
    {
    JOptionPane.showMessageDialog(this, "ENTER CLASS ID","Error",JOptionPane.WARNING_MESSAGE);}
    else{   
        try{
        String query="DELETE from "+TableName1+" where classid ='"+ClassId.getText()+"'";
        Statement add=myCon.createStatement();
        add.executeUpdate(query);
        ClassId.setText("");
    }//GEN-LAST:event_DeleteMouseClicked
   catch(SQLException e)
        {
            e.printStackTrace();
        }
    ViewData();
    }
    }
    
    private void EditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseClicked
    if("".equals(ClassId.getText()))
    {
    JOptionPane.showMessageDialog(this, "ENTER CLASS ID","Error",JOptionPane.WARNING_MESSAGE);}
    else{   
    
        try{
        String query="update "+TableName1+" set classname ='"+ClassName.getText()+"' "+",subjects='"+Subjects.getText()+"' "+"where classid ='"+ClassId.getText()+"'";       
        Statement update=myCon.createStatement();
        update.executeUpdate(query);
        ClassId.setText("");
        ClassName.setText("");
        
       
    }                                   
   catch(SQLException e)
        {
            e.printStackTrace();
        }
    ViewData();
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_EditMouseClicked

    private void AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddMouseClicked
    if("".equals(ClassName.getText())||"".equals(ClassId.getText())||"".equals(Subjects.getText()))
    {JOptionPane.showMessageDialog(this, "ENTER COPLETE DATA","Error",JOptionPane.ERROR_MESSAGE);
    }
    else{
        try{
            String query="Insert into "+TableName1+" values(?,?,?)";
            String q1 ="create table "+TableName1+" (CLASSNAME VARCHAR(100) ,CLASSID VARCHAR(100) ,SUBJECTS VARCHAR(100))";
            
            String cname=ClassName.getText();
            String cid=ClassId.getText();
            String sub=Subjects.getText();
           
            DatabaseMetaData dbm = myCon.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName1,null);
            
            if(!tables.next()){
            myStat=myCon.createStatement();
            myStat.executeUpdate(q1);
            }
            
            PreparedStatement insert=(PreparedStatement) myCon.prepareStatement(query);
            insert.setString(1,cname);
            insert.setString(2,cid);
            insert.setString(3, sub);
            
            int row=insert.executeUpdate();
            ClassName.setText("");
            ClassId.setText("");
                  
            }
        catch(SQLException e){
            e.printStackTrace();
        } 
    ViewData();
            }// TODO add your handling code here:
    }//GEN-LAST:event_AddMouseClicked

    private void ViewSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewSubjectsMouseClicked
        this.dispose();
        new ClassSub().setVisible(true);
        
    }//GEN-LAST:event_ViewSubjectsMouseClicked
     
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
            java.util.logging.Logger.getLogger(ClassesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClassesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClassesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClassesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassesDatabase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Add;
    private javax.swing.JTextField Ainvyyy;
    private javax.swing.JLabel BG;
    private javax.swing.JLabel Back;
    private javax.swing.JTextField ClassId;
    private javax.swing.JTextField ClassName;
    public static javax.swing.JTable ClassesLoadTable;
    private javax.swing.JTable ClassesTable;
    private javax.swing.JLabel Delete;
    private javax.swing.JLabel Edit;
    private javax.swing.JTextField Subjects;
    private javax.swing.JLabel ViewSubjects;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
