/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTgen0;

import static TTgen0.ClassesDatabase.AvailableRooms;
import static TTgen0.Other.Available;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static TTgen0.Other.rownum;
import java.util.Random;


/**
 *
 * @author ragha
 */

public class TeacherT extends javax.swing.JFrame {

    /**
     * Creates new form StudentTT
     */
    Connection myCon=null;
    Statement myStat=null;
    ResultSet myResult=null,myResult1=null;
    String layout;
    public String[] Subjects= new String[100];

    public TeacherT() {
        initComponents();
        
        int sH=Other.sHour,sM=Other.sMin,eH=Other.eHour,eM=Other.eHour,i=1;
        String sAP=Other.sAmpm,eAP=Other.eAmpm;
        String p=(String) Other.NoOfClasses.getSelectedItem();
        String q=(String) Other.BreakTime.getSelectedItem();
        int cl=Integer.parseInt(p),bl=Integer.parseInt(q);
        int time=cl+bl;
        while(!((i*time) > (Math.ceil((60-sM) + (11-sH)*60 + eH*60 + eM )))){
            i++;
        }
        
        
        
        
    Integer obj = new Integer(i); 
    String a=obj.toString();
    layout="L7"+a;    
        
    byte[] imageBytes;
    Image image;
    //String IName=Other.InstituteName.getText().toUpperCase();
    try{
    myCon=DriverManager.getConnection("jdbc:derby://localhost:1527/TTgen","rg","1234");
    myStat=myCon.createStatement();
    
    PreparedStatement pre = myCon.prepareStatement("select IMG from LOGOS where name='"+layout+"'");
    ResultSet rs2 =pre.executeQuery();
    while(rs2.next())
    {
    imageBytes=rs2.getBytes(1);
    image=getToolkit().createImage(imageBytes);
    ImageIcon icon= new ImageIcon(image);
    TeacherT.BG.setIcon(icon);}
    }
    
    
    catch(Exception ex){
        System.out.println(ex.getMessage());
    }
        
        
        
        
        try{
        InsName.setText(Other.InstituteName.getText());
        }
        catch(Exception e){
            InsName.setText("SHARDA UNIVERSITY");
        }
        BName.setText(TeacherTtDetail.TeacherBranch.getText());
        CName.setText(TeacherTtDetail.TeacherCourse.getText());
        TId.setText("Class Teacher : "+TeacherTtDetail.TeacherId.getSelectedItem().toString());
        
        SubLoadDetails();setT();
        
        
        
        
    }
    
    
    
    public void SubLoadDetails(){
    String TableName=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_"+"C4"+"_subjects").toUpperCase();
    String[] Load= new String[100];

        try{
        myStat=myCon.createStatement();

        myResult=myStat.executeQuery("Select SUBID from "+TableName);
        List a = new ArrayList();
        while (myResult.next()) {
        a.add(myResult.getString(1));
            }
        Subjects = new String[a.size()];
        Subjects = (String[]) a.toArray(new String[a.size()]);


        myResult1=myStat.executeQuery("Select LOAD from "+TableName);
        List b = new ArrayList();
        while (myResult1.next()) {
        b.add(myResult1.getString(1));
            } 
        Load=new String[b.size()];
        Load = (String[]) b.toArray(new String[b.size()]);


        }

        catch(SQLException e)
               {
            e.printStackTrace();
               }
        
       
        
        }
    
    
    public void setT(){
    Random rand = new Random();
     for (int k = 0; k < Subjects.length; k++) {
			int randomIndexToSwap = rand.nextInt(Subjects.length);
			String temp = Subjects[randomIndexToSwap];
			Subjects[randomIndexToSwap] = Subjects[k];
			Subjects[k] = temp;
		}

    for (int i = 0; i < rownum*8; i++) {
			int randomIndexToSwap = rand.nextInt(rownum*8);
			int temp = Available[randomIndexToSwap];
			Available[randomIndexToSwap] = Available[i];
			Available[i] = temp;
		}
    
    for(int j=0;j<Available.length-15;j++){
        int slot=Available[j];
        
        int g=0;
            String RName="";
            String TName5=(Other.CourseName.getText()+"_"+Other.Branch.getText()+"_"+Other.Year.getText()+"_arrangerooms").toUpperCase();
		
		for (int i = 0; i < AvailableRooms.length; i++) {
			int randomIndexToSwap = rand.nextInt(AvailableRooms.length);
			String temp = AvailableRooms[randomIndexToSwap];
			AvailableRooms[randomIndexToSwap] = AvailableRooms[i];
			AvailableRooms[i] = temp;
		}
                
            Random rand1 = new Random();
		
		

            String tid=Subjects[0];
            
           
            
            String[] c=new String[]{"CSE-A","CSE-B","CSE-C","CSE-D","CSE-E"};
            for (int p = 0; p < c.length; p++) {
			int randomIndexToSwap = rand.nextInt(c.length);
			String temp = c[randomIndexToSwap];
			c[randomIndexToSwap] = c[p];
			c[p] = temp;
		}
            
            

            String SubId=c[0];
            
            
        
    switch(slot){


                    case 1:Mo1.setText(SubId);RMo1.setText(AvailableRooms[g]);TMo1.setText(tid);
                           break;
                    case 2:Mo2.setText(SubId);RMo2.setText(AvailableRooms[g]);TMo2.setText(tid);
                           break;
                    case 3:Mo3.setText(SubId);RMo3.setText(AvailableRooms[g]);TMo3.setText(tid);
                           break;
                    case 4:Mo4.setText(SubId);RMo4.setText(AvailableRooms[g]);TMo4.setText(tid);
                           break;
                    case 5:Mo5.setText(SubId);RMo5.setText(AvailableRooms[g]);TMo5.setText(tid);
                           break;
                    case 6:Mo6.setText(SubId);RMo6.setText(AvailableRooms[g]);TMo6.setText(tid);
                           break;
                    case 7:Mo7.setText(SubId);RMo7.setText(AvailableRooms[g]);TMo7.setText(tid);
                           break;
                    case 8:Mo8.setText(SubId);RMo8.setText(AvailableRooms[g]);TMo8.setText(tid);
                           break;
                    case 9:Tu1.setText(SubId);RTu1.setText(AvailableRooms[g]);TTu1.setText(tid);
                           break;
                    case 10:Tu2.setText(SubId);RTu2.setText(AvailableRooms[g]);TTu2.setText(tid);
                           break;
                    case 11:Tu3.setText(SubId);RTu3.setText(AvailableRooms[g]);TTu3.setText(tid);
                           break;
                    case 12:Tu4.setText(SubId);RTu4.setText(AvailableRooms[g]);TTu4.setText(tid);
                           break;
                    case 13:Tu5.setText(SubId);RTu5.setText(AvailableRooms[g]);TTu5.setText(tid);
                           break;
                    case 14:Tu6.setText(SubId);RTu6.setText(AvailableRooms[g]);TTu6.setText(tid);
                           break;
                    case 15:Tu7.setText(SubId);RTu7.setText(AvailableRooms[g]);TTu7.setText(tid);
                           break;
                    case 16:Tu8.setText(SubId);RTu8.setText(AvailableRooms[g]);TTu8.setText(tid);
                           break;
                    case 17:We1.setText(SubId);RWe1.setText(AvailableRooms[g]);TWe1.setText(tid);
                           break;
                    case 18:We2.setText(SubId);RWe2.setText(AvailableRooms[g]);TWe2.setText(tid);
                           break;
                    case 19:We3.setText(SubId);RWe3.setText(AvailableRooms[g]);TWe3.setText(tid);
                           break;
                    case 20:We4.setText(SubId);RWe4.setText(AvailableRooms[g]);TWe4.setText(tid);
                           break;
                    case 21:We5.setText(SubId);RWe5.setText(AvailableRooms[g]);TWe5.setText(tid);
                           break;
                    case 22:We6.setText(SubId);RWe6.setText(AvailableRooms[g]);TWe6.setText(tid);
                           break;
                    case 23:We7.setText(SubId);RWe7.setText(AvailableRooms[g]);TWe7.setText(tid);
                           break;
                    case 24:We8.setText(SubId);RWe8.setText(AvailableRooms[g]);TWe8.setText(tid);
                           break;
                    case 25:Th1.setText(SubId);RTh1.setText(AvailableRooms[g]);TTh1.setText(tid);
                           break;
                    case 26:Th2.setText(SubId);RTh2.setText(AvailableRooms[g]);TTh2.setText(tid);
                           break;
                    case 27:Th3.setText(SubId);RTh3.setText(AvailableRooms[g]);TTh3.setText(tid);
                           break;
                    case 28:Th4.setText(SubId);RTh4.setText(AvailableRooms[g]);TTh4.setText(tid);
                           break;
                    case 29:Th5.setText(SubId);RTh5.setText(AvailableRooms[g]);TTh5.setText(tid);
                           break;
                    case 30:Th6.setText(SubId);RTh6.setText(AvailableRooms[g]);TTh6.setText(tid);
                           break;
                    case 31:Th7.setText(SubId);RTh7.setText(AvailableRooms[g]);TTh7.setText(tid);
                           break;
                    case 32:Th8.setText(SubId);RTh8.setText(AvailableRooms[g]);TTh8.setText(tid);
                           break;  
                    case 33:Fr1.setText(SubId);RFr1.setText(AvailableRooms[g]);TFr1.setText(tid);
                           break;
                    case 34:Fr2.setText(SubId);RFr2.setText(AvailableRooms[g]);TFr2.setText(tid);
                            break;
                    case 35:Fr3.setText(SubId);RFr3.setText(AvailableRooms[g]);TFr3.setText(tid);
                           break;
                    case 36:Fr4.setText(SubId);RFr4.setText(AvailableRooms[g]);TFr4.setText(tid);
                           break;
                    case 37:Fr5.setText(SubId);RFr5.setText(AvailableRooms[g]);TFr5.setText(tid);
                           break;
                    case 38:Fr6.setText(SubId);RFr6.setText(AvailableRooms[g]);TFr6.setText(tid);
                           break;
                    case 39:Fr7.setText(SubId);RFr7.setText(AvailableRooms[g]);TFr7.setText(tid);
                           break;
                    case 40:Fr8.setText(SubId);RFr8.setText(AvailableRooms[g]);TFr8.setText(tid);
                           break;
                    case 41:Sa1.setText(SubId);RSa1.setText(AvailableRooms[g]);
                           break;
                    case 42:Sa2.setText(SubId);RSa2.setText(AvailableRooms[g]);
                           break;
                    case 43:Sa3.setText(SubId);RSa3.setText(AvailableRooms[g]);
                           break;
                    case 44:Sa4.setText(SubId);RSa4.setText(AvailableRooms[g]);
                           break;
                    case 45:Sa5.setText(SubId);RSa5.setText(AvailableRooms[g]);
                           break;
                    case 46:Sa6.setText(SubId);RSa6.setText(AvailableRooms[g]);
                           break;
                    case 47:Sa7.setText(SubId);RSa7.setText(AvailableRooms[g]);
                           break;
                    case 48:Sa8.setText(SubId);RSa8.setText(AvailableRooms[g]);
                           break;
                    case 49:Su1.setText(SubId);RSu1.setText(AvailableRooms[g]);
                           break;
                    case 50:Su2.setText(SubId);RSu2.setText(AvailableRooms[g]);
                           break;
                    case 51:Su3.setText(SubId);RSu3.setText(AvailableRooms[g]);
                           break;
                    case 52:Su4.setText(SubId);RSu4.setText(AvailableRooms[g]);
                           break;
                    case 53:Su5.setText(SubId);RSu5.setText(AvailableRooms[g]);
                           break;
                    case 54:Su6.setText(SubId);RSu6.setText(AvailableRooms[g]);
                           break;
                    case 55:Su7.setText(SubId);RSu7.setText(AvailableRooms[g]);
                           break;
                    case 56:Su8.setText(SubId);RSu8.setText(AvailableRooms[g]);
                           break;

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
        Cross = new javax.swing.JLabel();
        InsName = new javax.swing.JLabel();
        CName = new javax.swing.JLabel();
        BName = new javax.swing.JLabel();
        TId = new javax.swing.JLabel();
        P81 = new javax.swing.JLabel();
        P82 = new javax.swing.JLabel();
        P83 = new javax.swing.JLabel();
        P84 = new javax.swing.JLabel();
        P85 = new javax.swing.JLabel();
        P86 = new javax.swing.JLabel();
        P87 = new javax.swing.JLabel();
        P88 = new javax.swing.JLabel();
        T81 = new javax.swing.JLabel();
        T82 = new javax.swing.JLabel();
        T83 = new javax.swing.JLabel();
        T84 = new javax.swing.JLabel();
        T85 = new javax.swing.JLabel();
        T86 = new javax.swing.JLabel();
        T87 = new javax.swing.JLabel();
        T88 = new javax.swing.JLabel();
        Mo1 = new javax.swing.JLabel();
        Mo2 = new javax.swing.JLabel();
        Mo3 = new javax.swing.JLabel();
        Mo4 = new javax.swing.JLabel();
        Mo5 = new javax.swing.JLabel();
        Mo6 = new javax.swing.JLabel();
        Mo7 = new javax.swing.JLabel();
        Mo8 = new javax.swing.JLabel();
        RMo1 = new javax.swing.JLabel();
        RMo2 = new javax.swing.JLabel();
        RMo3 = new javax.swing.JLabel();
        RMo4 = new javax.swing.JLabel();
        RMo5 = new javax.swing.JLabel();
        RMo6 = new javax.swing.JLabel();
        RMo7 = new javax.swing.JLabel();
        RMo8 = new javax.swing.JLabel();
        Tu1 = new javax.swing.JLabel();
        Tu2 = new javax.swing.JLabel();
        Tu3 = new javax.swing.JLabel();
        Tu4 = new javax.swing.JLabel();
        Tu5 = new javax.swing.JLabel();
        Tu6 = new javax.swing.JLabel();
        Tu7 = new javax.swing.JLabel();
        Tu8 = new javax.swing.JLabel();
        RTu1 = new javax.swing.JLabel();
        RTu2 = new javax.swing.JLabel();
        RTu3 = new javax.swing.JLabel();
        RTu4 = new javax.swing.JLabel();
        RTu5 = new javax.swing.JLabel();
        RTu6 = new javax.swing.JLabel();
        RTu7 = new javax.swing.JLabel();
        RTu8 = new javax.swing.JLabel();
        We1 = new javax.swing.JLabel();
        We2 = new javax.swing.JLabel();
        We3 = new javax.swing.JLabel();
        We4 = new javax.swing.JLabel();
        We5 = new javax.swing.JLabel();
        We6 = new javax.swing.JLabel();
        We7 = new javax.swing.JLabel();
        We8 = new javax.swing.JLabel();
        RWe1 = new javax.swing.JLabel();
        RWe2 = new javax.swing.JLabel();
        RWe3 = new javax.swing.JLabel();
        RWe4 = new javax.swing.JLabel();
        RWe5 = new javax.swing.JLabel();
        RWe6 = new javax.swing.JLabel();
        RWe7 = new javax.swing.JLabel();
        RWe8 = new javax.swing.JLabel();
        Th1 = new javax.swing.JLabel();
        Th2 = new javax.swing.JLabel();
        Th3 = new javax.swing.JLabel();
        Th4 = new javax.swing.JLabel();
        Th5 = new javax.swing.JLabel();
        Th6 = new javax.swing.JLabel();
        Th7 = new javax.swing.JLabel();
        Th8 = new javax.swing.JLabel();
        RTh1 = new javax.swing.JLabel();
        RTh2 = new javax.swing.JLabel();
        RTh3 = new javax.swing.JLabel();
        RTh4 = new javax.swing.JLabel();
        RTh5 = new javax.swing.JLabel();
        RTh6 = new javax.swing.JLabel();
        RTh7 = new javax.swing.JLabel();
        RTh8 = new javax.swing.JLabel();
        Fr1 = new javax.swing.JLabel();
        Fr2 = new javax.swing.JLabel();
        Fr3 = new javax.swing.JLabel();
        Fr4 = new javax.swing.JLabel();
        Fr5 = new javax.swing.JLabel();
        Fr6 = new javax.swing.JLabel();
        Fr7 = new javax.swing.JLabel();
        Fr8 = new javax.swing.JLabel();
        RFr1 = new javax.swing.JLabel();
        RFr2 = new javax.swing.JLabel();
        RFr3 = new javax.swing.JLabel();
        RFr4 = new javax.swing.JLabel();
        RFr5 = new javax.swing.JLabel();
        RFr6 = new javax.swing.JLabel();
        RFr7 = new javax.swing.JLabel();
        RFr8 = new javax.swing.JLabel();
        Sa1 = new javax.swing.JLabel();
        Sa2 = new javax.swing.JLabel();
        Sa3 = new javax.swing.JLabel();
        Sa4 = new javax.swing.JLabel();
        Sa5 = new javax.swing.JLabel();
        Sa6 = new javax.swing.JLabel();
        Sa7 = new javax.swing.JLabel();
        Sa8 = new javax.swing.JLabel();
        RSa1 = new javax.swing.JLabel();
        RSa2 = new javax.swing.JLabel();
        RSa3 = new javax.swing.JLabel();
        RSa4 = new javax.swing.JLabel();
        RSa5 = new javax.swing.JLabel();
        RSa6 = new javax.swing.JLabel();
        RSa7 = new javax.swing.JLabel();
        RSa8 = new javax.swing.JLabel();
        Su1 = new javax.swing.JLabel();
        Su2 = new javax.swing.JLabel();
        Su3 = new javax.swing.JLabel();
        Su4 = new javax.swing.JLabel();
        Su5 = new javax.swing.JLabel();
        Su6 = new javax.swing.JLabel();
        Su7 = new javax.swing.JLabel();
        Su8 = new javax.swing.JLabel();
        RSu1 = new javax.swing.JLabel();
        RSu2 = new javax.swing.JLabel();
        RSu3 = new javax.swing.JLabel();
        RSu4 = new javax.swing.JLabel();
        RSu5 = new javax.swing.JLabel();
        RSu6 = new javax.swing.JLabel();
        RSu7 = new javax.swing.JLabel();
        RSu8 = new javax.swing.JLabel();
        TMo1 = new javax.swing.JLabel();
        TMo2 = new javax.swing.JLabel();
        TMo3 = new javax.swing.JLabel();
        TMo4 = new javax.swing.JLabel();
        TMo5 = new javax.swing.JLabel();
        TMo6 = new javax.swing.JLabel();
        TMo7 = new javax.swing.JLabel();
        TMo8 = new javax.swing.JLabel();
        TTu1 = new javax.swing.JLabel();
        TTu2 = new javax.swing.JLabel();
        TTu3 = new javax.swing.JLabel();
        TTu4 = new javax.swing.JLabel();
        TTu5 = new javax.swing.JLabel();
        TTu6 = new javax.swing.JLabel();
        TTu7 = new javax.swing.JLabel();
        TTu8 = new javax.swing.JLabel();
        TWe1 = new javax.swing.JLabel();
        TWe2 = new javax.swing.JLabel();
        TWe3 = new javax.swing.JLabel();
        TWe4 = new javax.swing.JLabel();
        TWe5 = new javax.swing.JLabel();
        TWe6 = new javax.swing.JLabel();
        TWe7 = new javax.swing.JLabel();
        TWe8 = new javax.swing.JLabel();
        TTh1 = new javax.swing.JLabel();
        TTh2 = new javax.swing.JLabel();
        TTh3 = new javax.swing.JLabel();
        TTh4 = new javax.swing.JLabel();
        TTh5 = new javax.swing.JLabel();
        TTh6 = new javax.swing.JLabel();
        TTh7 = new javax.swing.JLabel();
        TTh8 = new javax.swing.JLabel();
        TFr1 = new javax.swing.JLabel();
        TFr2 = new javax.swing.JLabel();
        TFr3 = new javax.swing.JLabel();
        TFr4 = new javax.swing.JLabel();
        TFr5 = new javax.swing.JLabel();
        TFr6 = new javax.swing.JLabel();
        TFr7 = new javax.swing.JLabel();
        TFr8 = new javax.swing.JLabel();
        TSa1 = new javax.swing.JLabel();
        TSa2 = new javax.swing.JLabel();
        TSa3 = new javax.swing.JLabel();
        TSa4 = new javax.swing.JLabel();
        TSa5 = new javax.swing.JLabel();
        TSa6 = new javax.swing.JLabel();
        TSa7 = new javax.swing.JLabel();
        TSa8 = new javax.swing.JLabel();
        TSu1 = new javax.swing.JLabel();
        TSu2 = new javax.swing.JLabel();
        TSu3 = new javax.swing.JLabel();
        TSu4 = new javax.swing.JLabel();
        TSu5 = new javax.swing.JLabel();
        Tsu6 = new javax.swing.JLabel();
        TSu7 = new javax.swing.JLabel();
        TSu8 = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cross.png"))); // NOI18N
        Cross.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cross.setOpaque(true);
        Cross.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CrossMouseClicked(evt);
            }
        });
        jPanel1.add(Cross, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 25, 28));

        InsName.setFont(new java.awt.Font("Algerian", 0, 20)); // NOI18N
        InsName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(InsName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 18, 470, 28));
        jPanel1.add(CName, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 15, 70, 17));
        jPanel1.add(BName, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 32, 70, 17));

        TId.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TId, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 407, 190, 30));

        P81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P81.setText("1");
        jPanel1.add(P81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 64, 98, 20));

        P82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P82.setText("2");
        jPanel1.add(P82, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 64, 98, 20));

        P83.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P83.setText("3");
        jPanel1.add(P83, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 64, 98, 20));

        P84.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P84.setText("4");
        jPanel1.add(P84, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 64, 98, 20));

        P85.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P85.setText("5");
        jPanel1.add(P85, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 64, 98, 20));

        P86.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P86.setText("6");
        jPanel1.add(P86, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 64, 98, 20));

        P87.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P87.setText("7");
        jPanel1.add(P87, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 64, 98, 20));

        P88.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        P88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        P88.setText("8");
        jPanel1.add(P88, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 64, 98, 20));

        T81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T81.setText("jLabel1");
        jPanel1.add(T81, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 84, 98, 12));

        T82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T82.setText("jLabel1");
        jPanel1.add(T82, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 84, 98, 12));

        T83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T83.setText("jLabel1");
        jPanel1.add(T83, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 84, 98, 12));

        T84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T84.setText("jLabel1");
        jPanel1.add(T84, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 84, 98, 12));

        T85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T85.setText("jLabel1");
        jPanel1.add(T85, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 84, 98, 12));

        T86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T86.setText("jLabel1");
        jPanel1.add(T86, new org.netbeans.lib.awtextra.AbsoluteConstraints(591, 84, 98, 12));

        T87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T87.setText("jLabel1");
        jPanel1.add(T87, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 84, 98, 12));

        T88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T88.setText("jLabel1");
        jPanel1.add(T88, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 84, 98, 12));

        Mo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 97, 98, 30));

        Mo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 97, 98, 30));

        Mo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 97, 98, 30));

        Mo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 97, 98, 30));

        Mo5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 97, 98, 30));

        Mo6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 97, 98, 30));

        Mo7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 97, 98, 30));

        Mo8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Mo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Mo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 97, 98, 30));

        RMo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 127, 49, 12));

        RMo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 127, 49, 12));

        RMo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 127, 49, 12));

        RMo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 127, 49, 12));

        RMo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 127, 49, 12));

        RMo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 127, 49, 12));

        RMo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 127, 49, 12));

        RMo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RMo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 127, 49, 12));

        Tu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 141, 98, 30));

        Tu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 141, 98, 30));

        Tu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 141, 98, 30));

        Tu4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 141, 98, 30));

        Tu5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 141, 98, 30));

        Tu6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 141, 98, 30));

        Tu7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 141, 98, 30));

        Tu8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tu8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 141, 98, 30));

        RTu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 171, 49, 12));

        RTu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 171, 49, 12));

        RTu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 171, 49, 12));

        RTu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 171, 49, 12));

        RTu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 171, 49, 12));

        RTu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 171, 49, 12));

        RTu7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 171, 49, 12));

        RTu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTu8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 171, 49, 12));

        We1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 185, 98, 30));

        We2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 185, 98, 30));

        We3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 185, 98, 30));

        We4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 185, 98, 30));

        We5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 185, 98, 30));

        We6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 185, 98, 30));

        We7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 185, 98, 30));

        We8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        We8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(We8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 185, 98, 30));

        RWe1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 215, 49, 12));

        RWe2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 215, 49, 12));

        RWe3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 215, 49, 12));

        RWe4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 215, 49, 12));

        RWe5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 215, 49, 12));

        RWe6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 215, 49, 12));

        RWe7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 215, 49, 12));

        RWe8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RWe8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 215, 49, 12));

        Th1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 228, 98, 30));

        Th2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 228, 98, 30));

        Th3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 228, 98, 30));

        Th4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 228, 98, 30));

        Th5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 228, 98, 30));

        Th6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 228, 98, 30));

        Th7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 228, 98, 30));

        Th8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Th8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Th8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 228, 98, 30));

        RTh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 258, 49, 12));

        RTh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 258, 49, 12));

        RTh3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 258, 49, 12));

        RTh4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 258, 49, 12));

        RTh5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 258, 49, 12));

        RTh6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 258, 49, 12));

        RTh7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 258, 49, 12));

        RTh8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RTh8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 258, 49, 12));

        Fr1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 270, 98, 30));

        Fr2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 270, 98, 30));

        Fr3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 270, 98, 30));

        Fr4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 270, 98, 30));

        Fr5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 270, 98, 30));

        Fr6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 270, 98, 30));

        Fr7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 270, 98, 30));

        Fr8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Fr8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Fr8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 270, 98, 30));

        RFr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 300, 49, 12));

        RFr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 300, 49, 12));

        RFr3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 300, 49, 12));

        RFr4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 300, 49, 12));

        RFr5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 300, 49, 12));

        RFr6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 300, 49, 12));

        RFr7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 300, 49, 12));

        RFr8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RFr8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 300, 49, 12));

        Sa1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 314, 98, 30));

        Sa2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 314, 98, 30));

        Sa3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 314, 98, 30));

        Sa4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 314, 98, 30));

        Sa5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 314, 98, 30));

        Sa6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 314, 98, 30));

        Sa7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 314, 98, 30));

        Sa8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Sa8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Sa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 314, 98, 30));

        RSa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 344, 49, 12));

        RSa2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 344, 49, 12));

        RSa3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 344, 49, 12));

        RSa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 344, 49, 12));

        RSa5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 344, 49, 12));

        RSa6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 344, 49, 12));

        RSa7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 344, 49, 12));

        RSa8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 344, 49, 12));

        Su1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 358, 98, 30));

        Su2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 358, 98, 30));

        Su3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 358, 98, 30));

        Su4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 358, 98, 30));

        Su5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 358, 98, 30));

        Su6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 358, 98, 30));

        Su7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 358, 98, 30));

        Su8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Su8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Su8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 358, 98, 30));

        RSu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 388, 49, 12));

        RSu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 388, 49, 12));

        RSu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 388, 49, 12));

        RSu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 388, 49, 12));

        RSu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 388, 49, 12));

        RSu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 388, 49, 12));

        RSu7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 388, 49, 12));

        RSu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(RSu8, new org.netbeans.lib.awtextra.AbsoluteConstraints(792, 388, 49, 12));

        TMo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 127, 49, 12));

        TMo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 127, 49, 12));

        TMo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 127, 49, 12));

        TMo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 127, 49, 12));

        TMo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 127, 49, 12));

        TMo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 127, 49, 12));

        TMo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 127, 49, 12));

        TMo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TMo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 127, 49, 12));

        TTu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 171, 49, 12));

        TTu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 171, 49, 12));

        TTu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 171, 49, 12));

        TTu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 171, 49, 12));

        TTu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 171, 49, 12));

        TTu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 171, 49, 12));

        TTu7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 171, 49, 12));

        TTu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTu8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 171, 49, 12));

        TWe1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 215, 49, 12));

        TWe2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 215, 49, 12));

        TWe3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 215, 49, 12));

        TWe4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 215, 49, 12));

        TWe5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 215, 49, 12));

        TWe6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 215, 49, 12));

        TWe7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 215, 49, 12));

        TWe8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TWe8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 215, 49, 12));

        TTh1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 259, 49, 12));

        TTh2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 259, 49, 12));

        TTh3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 259, 49, 12));

        TTh4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 259, 49, 12));

        TTh5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 259, 49, 12));

        TTh6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 259, 49, 12));

        TTh7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 259, 49, 12));

        TTh8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TTh8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 259, 49, 12));

        TFr1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 303, 49, 12));

        TFr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 303, 49, 12));

        TFr3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 303, 49, 12));

        TFr4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 303, 49, 12));

        TFr5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 303, 49, 12));

        TFr6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 303, 49, 12));

        TFr7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 303, 49, 12));

        TFr8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TFr8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 303, 49, 12));

        TSa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 347, 49, 12));

        TSa2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 347, 49, 12));

        TSa3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 347, 49, 12));

        TSa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 347, 49, 12));

        TSa5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 347, 49, 12));

        TSa6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 347, 49, 12));

        TSa7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 347, 49, 12));

        TSa8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 347, 49, 12));

        TSu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 391, 49, 12));

        TSu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 391, 49, 12));

        TSu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 391, 49, 12));

        TSu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 391, 49, 12));

        TSu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 391, 49, 12));

        Tsu6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Tsu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 391, 49, 12));

        TSu7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 391, 49, 12));

        TSu8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TSu8, new org.netbeans.lib.awtextra.AbsoluteConstraints(841, 391, 49, 12));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/L78.jpg"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CrossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CrossMouseClicked
    this.dispose();
    new TeacherTtDetail().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_CrossMouseClicked
    
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
            java.util.logging.Logger.getLogger(TeacherT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new TeacherT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel BG;
    private javax.swing.JLabel BName;
    private javax.swing.JLabel CName;
    private javax.swing.JLabel Cross;
    private javax.swing.JLabel Fr1;
    private javax.swing.JLabel Fr2;
    private javax.swing.JLabel Fr3;
    private javax.swing.JLabel Fr4;
    private javax.swing.JLabel Fr5;
    private javax.swing.JLabel Fr6;
    private javax.swing.JLabel Fr7;
    private javax.swing.JLabel Fr8;
    private javax.swing.JLabel InsName;
    private javax.swing.JLabel Mo1;
    private javax.swing.JLabel Mo2;
    private javax.swing.JLabel Mo3;
    private javax.swing.JLabel Mo4;
    private javax.swing.JLabel Mo5;
    private javax.swing.JLabel Mo6;
    private javax.swing.JLabel Mo7;
    private javax.swing.JLabel Mo8;
    private javax.swing.JLabel P81;
    private javax.swing.JLabel P82;
    private javax.swing.JLabel P83;
    private javax.swing.JLabel P84;
    private javax.swing.JLabel P85;
    private javax.swing.JLabel P86;
    private javax.swing.JLabel P87;
    private javax.swing.JLabel P88;
    private javax.swing.JLabel RFr1;
    private javax.swing.JLabel RFr2;
    private javax.swing.JLabel RFr3;
    private javax.swing.JLabel RFr4;
    private javax.swing.JLabel RFr5;
    private javax.swing.JLabel RFr6;
    private javax.swing.JLabel RFr7;
    private javax.swing.JLabel RFr8;
    private javax.swing.JLabel RMo1;
    private javax.swing.JLabel RMo2;
    private javax.swing.JLabel RMo3;
    private javax.swing.JLabel RMo4;
    private javax.swing.JLabel RMo5;
    private javax.swing.JLabel RMo6;
    private javax.swing.JLabel RMo7;
    private javax.swing.JLabel RMo8;
    private javax.swing.JLabel RSa1;
    private javax.swing.JLabel RSa2;
    private javax.swing.JLabel RSa3;
    private javax.swing.JLabel RSa4;
    private javax.swing.JLabel RSa5;
    private javax.swing.JLabel RSa6;
    private javax.swing.JLabel RSa7;
    private javax.swing.JLabel RSa8;
    private javax.swing.JLabel RSu1;
    private javax.swing.JLabel RSu2;
    private javax.swing.JLabel RSu3;
    private javax.swing.JLabel RSu4;
    private javax.swing.JLabel RSu5;
    private javax.swing.JLabel RSu6;
    private javax.swing.JLabel RSu7;
    private javax.swing.JLabel RSu8;
    private javax.swing.JLabel RTh1;
    private javax.swing.JLabel RTh2;
    private javax.swing.JLabel RTh3;
    private javax.swing.JLabel RTh4;
    private javax.swing.JLabel RTh5;
    private javax.swing.JLabel RTh6;
    private javax.swing.JLabel RTh7;
    private javax.swing.JLabel RTh8;
    private javax.swing.JLabel RTu1;
    private javax.swing.JLabel RTu2;
    private javax.swing.JLabel RTu3;
    private javax.swing.JLabel RTu4;
    private javax.swing.JLabel RTu5;
    private javax.swing.JLabel RTu6;
    private javax.swing.JLabel RTu7;
    private javax.swing.JLabel RTu8;
    private javax.swing.JLabel RWe1;
    private javax.swing.JLabel RWe2;
    private javax.swing.JLabel RWe3;
    private javax.swing.JLabel RWe4;
    private javax.swing.JLabel RWe5;
    private javax.swing.JLabel RWe6;
    private javax.swing.JLabel RWe7;
    private javax.swing.JLabel RWe8;
    private javax.swing.JLabel Sa1;
    private javax.swing.JLabel Sa2;
    private javax.swing.JLabel Sa3;
    private javax.swing.JLabel Sa4;
    private javax.swing.JLabel Sa5;
    private javax.swing.JLabel Sa6;
    private javax.swing.JLabel Sa7;
    private javax.swing.JLabel Sa8;
    private javax.swing.JLabel Su1;
    private javax.swing.JLabel Su2;
    private javax.swing.JLabel Su3;
    private javax.swing.JLabel Su4;
    private javax.swing.JLabel Su5;
    private javax.swing.JLabel Su6;
    private javax.swing.JLabel Su7;
    private javax.swing.JLabel Su8;
    private javax.swing.JLabel T81;
    private javax.swing.JLabel T82;
    private javax.swing.JLabel T83;
    private javax.swing.JLabel T84;
    private javax.swing.JLabel T85;
    private javax.swing.JLabel T86;
    private javax.swing.JLabel T87;
    private javax.swing.JLabel T88;
    private javax.swing.JLabel TFr1;
    private javax.swing.JLabel TFr2;
    private javax.swing.JLabel TFr3;
    private javax.swing.JLabel TFr4;
    private javax.swing.JLabel TFr5;
    private javax.swing.JLabel TFr6;
    private javax.swing.JLabel TFr7;
    private javax.swing.JLabel TFr8;
    private javax.swing.JLabel TId;
    private javax.swing.JLabel TMo1;
    private javax.swing.JLabel TMo2;
    private javax.swing.JLabel TMo3;
    private javax.swing.JLabel TMo4;
    private javax.swing.JLabel TMo5;
    private javax.swing.JLabel TMo6;
    private javax.swing.JLabel TMo7;
    private javax.swing.JLabel TMo8;
    private javax.swing.JLabel TSa1;
    private javax.swing.JLabel TSa2;
    private javax.swing.JLabel TSa3;
    private javax.swing.JLabel TSa4;
    private javax.swing.JLabel TSa5;
    private javax.swing.JLabel TSa6;
    private javax.swing.JLabel TSa7;
    private javax.swing.JLabel TSa8;
    private javax.swing.JLabel TSu1;
    private javax.swing.JLabel TSu2;
    private javax.swing.JLabel TSu3;
    private javax.swing.JLabel TSu4;
    private javax.swing.JLabel TSu5;
    private javax.swing.JLabel TSu7;
    private javax.swing.JLabel TSu8;
    private javax.swing.JLabel TTh1;
    private javax.swing.JLabel TTh2;
    private javax.swing.JLabel TTh3;
    private javax.swing.JLabel TTh4;
    private javax.swing.JLabel TTh5;
    private javax.swing.JLabel TTh6;
    private javax.swing.JLabel TTh7;
    private javax.swing.JLabel TTh8;
    private javax.swing.JLabel TTu1;
    private javax.swing.JLabel TTu2;
    private javax.swing.JLabel TTu3;
    private javax.swing.JLabel TTu4;
    private javax.swing.JLabel TTu5;
    private javax.swing.JLabel TTu6;
    private javax.swing.JLabel TTu7;
    private javax.swing.JLabel TTu8;
    private javax.swing.JLabel TWe1;
    private javax.swing.JLabel TWe2;
    private javax.swing.JLabel TWe3;
    private javax.swing.JLabel TWe4;
    private javax.swing.JLabel TWe5;
    private javax.swing.JLabel TWe6;
    private javax.swing.JLabel TWe7;
    private javax.swing.JLabel TWe8;
    private javax.swing.JLabel Th1;
    private javax.swing.JLabel Th2;
    private javax.swing.JLabel Th3;
    private javax.swing.JLabel Th4;
    private javax.swing.JLabel Th5;
    private javax.swing.JLabel Th6;
    private javax.swing.JLabel Th7;
    private javax.swing.JLabel Th8;
    private javax.swing.JLabel Tsu6;
    private javax.swing.JLabel Tu1;
    private javax.swing.JLabel Tu2;
    private javax.swing.JLabel Tu3;
    private javax.swing.JLabel Tu4;
    private javax.swing.JLabel Tu5;
    private javax.swing.JLabel Tu6;
    private javax.swing.JLabel Tu7;
    private javax.swing.JLabel Tu8;
    private javax.swing.JLabel We1;
    private javax.swing.JLabel We2;
    private javax.swing.JLabel We3;
    private javax.swing.JLabel We4;
    private javax.swing.JLabel We5;
    private javax.swing.JLabel We6;
    private javax.swing.JLabel We7;
    private javax.swing.JLabel We8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
