
package com.popUps;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.HomePage;

/**
 *
 * @author Asus
 */
public class StudentInfo extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pr;
    String path;
    String Npath;

    int ID;

    public StudentInfo(int ID) {
        initComponents();
        initValues(ID);
        this.ID = ID;
    }

    //methiod to resizr image
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imgLable.getWidth(), imgLable.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void initValues(int ID) {

        CompanyL.hide();
        CompanyTF.hide();
        JobTf.hide();
        Jobl.hide();
        
        jButton3.setVisible(false);
        jButton2.setVisible(false);
        jButton1.setVisible(false);
        
        
        NameTF1.setEditable(false);
        MobTF.setEditable(false);
        EmailTF.setEditable(false);
        AddTF.setEditable(false);
        CollTF1.setEditable(false);
        BranchTF.setEditable(false);
        FeesTF.setEditable(false);
        CompanyTF.setEditable(false);
        JobTf.setEditable(false);
        
        
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs-institute-management", "root", "sayan");

            String sql = "Select * From addmitions where stdID='" + ID + "' ";

            pr = con.prepareStatement(sql);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {

                String Name = rs.getString("name");
                String Email = rs.getString("email");
                String Mob = rs.getString("mobile");
                String add = rs.getString("address");
                String coll = rs.getString("collage");
                String branch = rs.getString("branch");

                String feesP = rs.getString("feesPaid");
                path = rs.getString("img");

                String IDs = String.valueOf(ID);

                StdID.setText(IDs);
                NameTF1.setText(Name);
                EmailTF.setText(Email);
                MobTF.setText(Mob);
                AddTF.setText(add);
                AddTF.setText(coll);
                BranchTF.setText(branch);
                FeesTF.setText(feesP);
                imgLable.setIcon(ResizeImage(path));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        StdID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        FeesTF = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        imgLable = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        NameTF1 = new javax.swing.JTextField();
        MobTF = new javax.swing.JTextField();
        EmailTF = new javax.swing.JTextField();
        JobTf = new javax.swing.JTextField();
        BranchTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        AddTF = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        PlacedCKB = new javax.swing.JCheckBox();
        Jobl = new javax.swing.JLabel();
        CompanyL = new javax.swing.JLabel();
        CollTF1 = new javax.swing.JTextField();
        CompanyTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Branch : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 56, 24));

        StdID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(StdID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 56, 24));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Mobile : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 56, 24));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Email : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 56, 24));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Collage : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 56, 24));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Address : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 72, 24));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Batch : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 56, 24));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Fees paid : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 72, 24));

        FeesTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeesTFActionPerformed(evt);
            }
        });
        jPanel1.add(FeesTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 155, -1));

        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));

        imgLable.setBackground(new java.awt.Color(255, 255, 255));
        imgLable.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 30, 97, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 147, 97, -1));

        NameTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTF1ActionPerformed(evt);
            }
        });
        jPanel1.add(NameTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 155, -1));

        MobTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobTFActionPerformed(evt);
            }
        });
        jPanel1.add(MobTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 155, -1));

        EmailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTFActionPerformed(evt);
            }
        });
        jPanel1.add(EmailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 155, -1));

        JobTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobTfActionPerformed(evt);
            }
        });
        jPanel1.add(JobTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 110, -1));

        BranchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BranchTFActionPerformed(evt);
            }
        });
        jPanel1.add(BranchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 155, -1));

        AddTF.setColumns(20);
        AddTF.setRows(5);
        jScrollPane1.setViewportView(AddTF);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 160, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Name : ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 56, 24));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Student ID : ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 24));

        jButton2.setBackground(new java.awt.Color(0, 204, 0));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 51));
        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, -1, -1));

        PlacedCKB.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        PlacedCKB.setText("Placed");
        PlacedCKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlacedCKBActionPerformed(evt);
            }
        });
        jPanel1.add(PlacedCKB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, -1));

        Jobl.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Jobl.setText("Job Role :");
        jPanel1.add(Jobl, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, -1));

        CompanyL.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        CompanyL.setText("Company Name :");
        jPanel1.add(CompanyL, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        CollTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CollTF1ActionPerformed(evt);
            }
        });
        jPanel1.add(CollTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 155, -1));

        CompanyTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompanyTFActionPerformed(evt);
            }
        });
        jPanel1.add(CompanyTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 110, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_Edit_Account_20px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 20, 20));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 510, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/icons8_close_30px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 34, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

        this.dispose();

    }//GEN-LAST:event_jLabel1MouseClicked

    private void FeesTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeesTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FeesTFActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            imgLable.setIcon(ResizeImage(path));
        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void NameTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTF1ActionPerformed

    private void MobTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MobTFActionPerformed

    private void EmailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailTFActionPerformed

    private void JobTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JobTfActionPerformed

    private void BranchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BranchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BranchTFActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        initValues(ID);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String Name = NameTF1.getText();
        String Email = EmailTF.getText();
        String mob = MobTF.getText();
        String Add = AddTF.getText();
        String Branch = BranchTF.getText();
        String Coll = JobTf.getText();
        String Fees = FeesTF.getText();

        String Npath = "";

        String[] substrings = path.split("");

        for (int x = 0; x < substrings.length; x++) {

            if (substrings[x].equalsIgnoreCase("\\")) {
                substrings[x] = "//";
            }
            Npath = Npath + substrings[x];

        }

        try {
            
            String sql = "update addmitions set  name='" + Name + "',email='" + Email + "'  ,mobile='" + mob + "'  ,address='" + Add + "'  ,collage='" + Coll + "' ,branch='" + Branch + "',feesPaid='" + Fees + "',img='" + Npath + "' where stdID='" + ID + "'  ;";

             if(PlacedCKB.isSelected()){
                String Company = CompanyTF.getText();
                String jobR = JobTf.getText();
                sql = "update addmitions set  name='" + Name + "',email='" + Email + "'  ,mobile='" + mob + "'  ,address='" + Add + "'  ,collage='" + Coll + "' ,branch='" + Branch + "',feesPaid='" + Fees + "',img='" + Npath + "', company='"+Company+"', jobRole='"+jobR+"'    where stdID='" + ID + "' ;";
            }
            
            PreparedStatement ps = con.prepareCall(sql);

            int i = ps.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(jPanel1, "Detials Updated");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void CollTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CollTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CollTF1ActionPerformed

    private void CompanyTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompanyTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CompanyTFActionPerformed

    private void PlacedCKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlacedCKBActionPerformed
        // TODO add your handling code here:

        if (PlacedCKB.isSelected()) {
            CompanyL.show();
            CompanyTF.show();
            JobTf.show();
            Jobl.show();

        } else if (!PlacedCKB.isSelected()) {
            CompanyL.hide();
            CompanyTF.hide();
            JobTf.hide();
            Jobl.hide();

        }

    }//GEN-LAST:event_PlacedCKBActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        
        // Student card edit btn
        
        jButton3.setVisible(true);
        jButton2.setVisible(true);
        jButton1.setVisible(true);
        
        NameTF1.setEditable(true);
        MobTF.setEditable(true);
        EmailTF.setEditable(true);
        AddTF.setEditable(true);
        CollTF1.setEditable(true);
        BranchTF.setEditable(true);
        FeesTF.setEditable(true);
        CompanyTF.setEditable(true);
        JobTf.setEditable(true);
        
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentInfo(4).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AddTF;
    private javax.swing.JTextField BranchTF;
    private javax.swing.JTextField CollTF1;
    private javax.swing.JLabel CompanyL;
    private javax.swing.JTextField CompanyTF;
    private javax.swing.JTextField EmailTF;
    private javax.swing.JTextField FeesTF;
    private javax.swing.JTextField JobTf;
    private javax.swing.JLabel Jobl;
    private javax.swing.JTextField MobTF;
    private javax.swing.JTextField NameTF1;
    private javax.swing.JCheckBox PlacedCKB;
    private javax.swing.JLabel StdID;
    private javax.swing.JLabel imgLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
