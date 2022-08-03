package com.popUps;

import java.awt.Color;
import java.awt.List;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.HomePage;

public class BatchInfo extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pr;

    public BatchInfo() {

        initComponents();

        initValues();

    }

    private void initValues() {
         int i = 0;
         
        ArrayList<JLabel> a1 = new ArrayList();
        a1.add(batchLable1);
        a1.add(batchLable2);
        a1.add(batchLable3);
        a1.add(batchLable4);
        a1.add(batchLable5);
        a1.add(batchLable6);
        
        ArrayList<JLabel> a2 = new ArrayList();
        a2.add(BatchName1);
        a2.add(BatchName2);
        a2.add(BatchName3);
        a2.add(BatchName4);
        a2.add(BatchName5);
        a2.add(BatchName6);
        
        
        ArrayList<JLabel> a3 = new ArrayList();
        a3.add(Btiming1);
        a3.add(Btiming2);
        a3.add(Btiming3);
        a3.add(Btiming4);
        a3.add(Btiming5);
        a3.add(Btiming6);
        
        
        ArrayList<JLabel> a4 = new ArrayList();
        a4.add(Status1);
        a4.add(Status2);
        a4.add(Status3);
        a4.add(Status4);
        a4.add(Status5);
        a4.add(Status6);
        
        
        ArrayList<JLabel> a5 = new ArrayList();
        a5.add(seats1);
        a5.add(seats2);
        a5.add(seats3);
        a5.add(seats4);
        a5.add(seats5);
        a5.add(seats6);
        
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs-institute-management", "root", "sayan");
            String sql = "Select *  From batches where Status='Active' ;";

            pr = con.prepareStatement(sql);

            ResultSet rs = pr.executeQuery();

            while (rs.next()) {

                String BatchNo = rs.getString("Batch_No");
                String BatchName = rs.getString("Batch_name");
                String Timing = rs.getString("Timing");
                String Status = rs.getString("Status");
                
                int Seats=0;
                
                try{
                int TotalStd = Integer.parseInt( rs.getString("Total_Students"));
                int Stds = Integer.parseInt( rs.getString("Students"));
                Seats= TotalStd-Stds;
                }catch(Exception e){
                    System.out.println(e);
                }
                
//                System.out.println(Status);
                
                 

                JLabel j = a1.get(i);
                JLabel n = a2.get(i);
                JLabel t = a3.get(i);
                JLabel s = a4.get(i);
                JLabel l = a5.get(i);

                j.setText(BatchNo);
                n.setText(BatchName);
                t.setText("Timing:"+Timing);
                s.setText(Status);
                s.setBackground(Color.BLACK);
                s.setOpaque(true);
                s.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                
                l.setText(""+Seats);

                i = i + 1;

//                System.out.println(BatchNo);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        batchLable1 = new javax.swing.JLabel();
        BatchName1 = new javax.swing.JLabel();
        Btiming1 = new javax.swing.JLabel();
        Status1 = new javax.swing.JLabel();
        batchLable7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        seats1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        batchLable2 = new javax.swing.JLabel();
        BatchName2 = new javax.swing.JLabel();
        Btiming2 = new javax.swing.JLabel();
        Status2 = new javax.swing.JLabel();
        batchLable8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        seats2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        batchLable3 = new javax.swing.JLabel();
        BatchName3 = new javax.swing.JLabel();
        Btiming3 = new javax.swing.JLabel();
        Status3 = new javax.swing.JLabel();
        batchLable9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        seats3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        batchLable4 = new javax.swing.JLabel();
        BatchName4 = new javax.swing.JLabel();
        Btiming4 = new javax.swing.JLabel();
        Status4 = new javax.swing.JLabel();
        batchLable12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        seats4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        batchLable5 = new javax.swing.JLabel();
        BatchName5 = new javax.swing.JLabel();
        Btiming5 = new javax.swing.JLabel();
        Status5 = new javax.swing.JLabel();
        batchLable11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        seats5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        batchLable6 = new javax.swing.JLabel();
        BatchName6 = new javax.swing.JLabel();
        Btiming6 = new javax.swing.JLabel();
        Status6 = new javax.swing.JLabel();
        batchLable10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        seats6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(660, 330));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        batchLable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable1.setText(" 1");

        BatchName1.setText("Batch Name");

        Btiming1.setText("Timing");

        Status1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status1.setForeground(new java.awt.Color(0, 255, 51));
        Status1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Status1.setText("Status");
        Status1.setBorder(new javax.swing.border.MatteBorder(null));
        Status1.setOpaque(true);

        batchLable7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable7.setText("Batch No");

        jLabel2.setText("Seats left: ");

        seats1.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BatchName1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btiming1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(batchLable7, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(batchLable1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Status1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seats1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable1)
                    .addComponent(batchLable7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btiming1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(seats1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Status1)
                .addContainerGap())
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 120));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        batchLable2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable2.setText("Null");

        BatchName2.setText("Batch Name");

        Btiming2.setText("Timing");

        Status2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status2.setForeground(new java.awt.Color(102, 255, 0));
        Status2.setText("Status");
        Status2.setBorder(new javax.swing.border.MatteBorder(null));
        Status2.setOpaque(true);

        batchLable8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable8.setText("Batch No");

        jLabel3.setText("Seats left: ");

        seats2.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Status2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(BatchName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(batchLable8, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGap(9, 9, 9)
                        .addComponent(batchLable2)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seats2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Btiming2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable2)
                    .addComponent(batchLable8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btiming2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(seats2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status2)
                .addContainerGap())
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 150, 120));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        batchLable3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable3.setText("null");

        BatchName3.setText("Batch Name");

        Btiming3.setText("Timing");

        Status3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status3.setForeground(new java.awt.Color(102, 255, 0));
        Status3.setText("Status");
        Status3.setBorder(new javax.swing.border.MatteBorder(null));

        batchLable9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable9.setText("Batch No");

        jLabel4.setText("Seats left: ");

        seats3.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(Status3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seats3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(Btiming3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(batchLable9, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(batchLable3)
                        .addGap(37, 37, 37))
                    .addComponent(BatchName3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable3)
                    .addComponent(batchLable9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btiming3)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(seats3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 150, 120));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        batchLable4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable4.setText("Null");

        BatchName4.setText("Batch Name");

        Btiming4.setText("Timing");

        Status4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status4.setForeground(new java.awt.Color(102, 255, 0));
        Status4.setText("Status");
        Status4.setBorder(new javax.swing.border.MatteBorder(null));

        batchLable12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable12.setText("Batch No");

        jLabel5.setText("Seats left: ");

        seats4.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(Status4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seats4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(BatchName4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(batchLable12, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(batchLable4)
                        .addGap(21, 21, 21))
                    .addComponent(Btiming4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable4)
                    .addComponent(batchLable12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName4)
                .addGap(1, 1, 1)
                .addComponent(Btiming4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(seats4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 150, 110));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        batchLable5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable5.setText("null");

        BatchName5.setText("Batch Name");

        Btiming5.setText("Timing");

        Status5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status5.setForeground(new java.awt.Color(102, 255, 0));
        Status5.setText("Status");
        Status5.setBorder(new javax.swing.border.MatteBorder(null));

        batchLable11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable11.setText("Batch No");

        jLabel6.setText("Seats left: ");

        seats5.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(BatchName5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(batchLable11, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(batchLable5)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seats5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Btiming5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Status5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable5)
                    .addComponent(batchLable11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName5)
                .addGap(2, 2, 2)
                .addComponent(Btiming5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(seats5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Status5)
                .addContainerGap())
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 150, 110));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        batchLable6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable6.setText("Null");

        BatchName6.setText("null");

        Btiming6.setText("null");

        Status6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Status6.setForeground(new java.awt.Color(102, 255, 0));
        Status6.setText("Null");
        Status6.setBorder(new javax.swing.border.MatteBorder(null));

        batchLable10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batchLable10.setText("Batch No");

        jLabel7.setText("Seats left: ");

        seats6.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btiming6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(batchLable10, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(batchLable6)
                .addGap(24, 24, 24))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BatchName6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Status6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seats6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchLable6)
                    .addComponent(batchLable10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BatchName6)
                .addGap(4, 4, 4)
                .addComponent(Btiming6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(seats6))
                .addGap(4, 4, 4)
                .addComponent(Status6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 150, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 640, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/icons8_close_30px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 30, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        
        int b =Integer.parseInt(batchLable1.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        this.dispose();
        
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        
        int b =Integer.parseInt(batchLable2.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        System.out.println(b);
        this.dispose();
        
        
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        
        int b =Integer.parseInt(batchLable3.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        this.dispose();
        
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        int b =Integer.parseInt(batchLable5.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        this.dispose();
        
        
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        
        int b =Integer.parseInt(batchLable6.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        this.dispose();
        
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        int b =Integer.parseInt(batchLable4.getText());
        HomePage h = new HomePage();
        h.setBatchNo(b);
        this.dispose();

    }//GEN-LAST:event_jPanel7MouseClicked

    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BatchInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BatchName1;
    private javax.swing.JLabel BatchName2;
    private javax.swing.JLabel BatchName3;
    private javax.swing.JLabel BatchName4;
    private javax.swing.JLabel BatchName5;
    private javax.swing.JLabel BatchName6;
    private javax.swing.JLabel Btiming1;
    private javax.swing.JLabel Btiming2;
    private javax.swing.JLabel Btiming3;
    private javax.swing.JLabel Btiming4;
    private javax.swing.JLabel Btiming5;
    private javax.swing.JLabel Btiming6;
    private javax.swing.JLabel Status1;
    private javax.swing.JLabel Status2;
    private javax.swing.JLabel Status3;
    private javax.swing.JLabel Status4;
    private javax.swing.JLabel Status5;
    private javax.swing.JLabel Status6;
    private javax.swing.JLabel batchLable1;
    private javax.swing.JLabel batchLable10;
    private javax.swing.JLabel batchLable11;
    private javax.swing.JLabel batchLable12;
    private javax.swing.JLabel batchLable2;
    private javax.swing.JLabel batchLable3;
    private javax.swing.JLabel batchLable4;
    private javax.swing.JLabel batchLable5;
    private javax.swing.JLabel batchLable6;
    private javax.swing.JLabel batchLable7;
    private javax.swing.JLabel batchLable8;
    private javax.swing.JLabel batchLable9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel seats1;
    private javax.swing.JLabel seats2;
    private javax.swing.JLabel seats3;
    private javax.swing.JLabel seats4;
    private javax.swing.JLabel seats5;
    private javax.swing.JLabel seats6;
    // End of variables declaration//GEN-END:variables
}
