package main;

import com.mysql.cj.Session;
import com.popUps.BatchInfo;
import com.popUps.StudentInfo;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.*;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class MyTimeTask extends TimerTask {

    Connection con;
    PreparedStatement pr;
    String path;

    public void run() {
        System.out.println("fired");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs-institute-management", "root", "sayan");

            String sql = "update  batches set Status='Deactive' where Batch_No=2 ;";

            pr = con.prepareStatement(sql);

            int i = pr.executeUpdate();

            if (i > 0) {
                System.out.println("");
            }

        } catch (Exception e) {
            System.out.println("deactivated");
        }

    }
}

public class HomePage extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pr;
    String path;
    String Attachpath;
    ArrayList<String> emailList = new ArrayList<String>();

    int selectedBatchNo = 0;

    public HomePage() {
        initComponents();
        ConnectionInit();

        Homepannel.setVisible(true);
        PendingAdd.setVisible(false);
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(false);
        NewAddPannel.setVisible(false);
        MailStudentPannel.setVisible(false);

    }

    void ConnectionInit() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs-institute-management", "root", "sayan");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //static batch no fun
    public void setBatchNo(int n) {
        System.out.println(n);
        selectedBatchNo = n;
        selectedBatch.setText("Batch " + n);

    }

    //increase month 
    public static String EndDayIncrese(String date, long M) {
        return LocalDate
                .parse(date)
                .plusDays(M)
                .toString();
    }

    //methiod to resizr image
    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imgLable.getWidth(), imgLable.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void ApplicationClear() {

        StdID.setText("");
        namrtf.setText("");
        emailtf.setText("");
        mobiletf.setText("");
        addtf.setText("");
        branchtf.setText("");
        feetf.setText("");
        imgLable.setIcon(ResizeImage(""));

    }

    //this is responsible to send email..
    private static void sendEmail(String message, String subject, String to, String from) {

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
//		System.out.println("PROPERTIES "+properties);

        //setting important information to properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("sharesayan22@gmail.com", "srwqvclzbhenvpxn");
            }
        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(new InternetAddress(from));

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send 
            //Step 3 : send the message using Transport class
            Transport.send(m);

            System.out.println("Sent success...................");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void sendAttach(String message, String subject, String to, String from, String Attach) {

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //setting important information to properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("sharesayan22@gmail.com", "srwqvclzbhenvpxn");
            }
        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(new InternetAddress(from));

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //attachement..
            //file path
            String path = Attach;

            MimeMultipart mimeMultipart = new MimeMultipart();
            //text
            //file

            MimeBodyPart textMime = new MimeBodyPart();

            MimeBodyPart fileMime = new MimeBodyPart();

            try {

                textMime.setText(message);

                File file = new File(path);
                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            } catch (Exception e) {

                e.printStackTrace();
            }

            m.setContent(mimeMultipart);
            //send 

            //Step 3 : send the message using Transport class
            Transport.send(m);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sent success...................");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Homepannel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PensingPannel = new javax.swing.JLabel();
        mailTab = new javax.swing.JLabel();
        PendingAdd = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        PendingTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        FeesPannel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        FeesTable = new javax.swing.JTable();
        MailStudentPannel = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        MailSendBtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        selectManually = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        seletEmails = new javax.swing.JPanel();
        selectOpt1 = new javax.swing.JButton();
        selectOpt3 = new javax.swing.JButton();
        selectOpt4 = new javax.swing.JButton();
        selectOpt5 = new javax.swing.JButton();
        selectOpt6 = new javax.swing.JButton();
        selectOpt2 = new javax.swing.JButton();
        NewAddPannel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        StdID = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        namrtf = new javax.swing.JTextField();
        mobiletf = new javax.swing.JTextField();
        emailtf = new javax.swing.JTextField();
        branchtf = new javax.swing.JTextField();
        colltf = new javax.swing.JTextField();
        feetf = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        addtf = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        imgLable = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        selectedBatch = new javax.swing.JLabel();
        StudentPannel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StdTable = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        searchTF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TotalSTD = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        ActiveSTD = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        PlacedSTD = new javax.swing.JLabel();
        Batchpannel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        BatchTable = new javax.swing.JTable();
        AddNewBatch = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        BatchNo1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        AddBtn = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        Tfees = new javax.swing.JTextField();
        Bname = new javax.swing.JTextField();
        Cname = new javax.swing.JTextField();
        Btimeing = new javax.swing.JTextField();
        stds = new javax.swing.JTextField();
        Active = new javax.swing.JCheckBox();
        jLabel51 = new javax.swing.JLabel();
        TotalStd = new javax.swing.JTextField();
        enddate = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        startdate = new com.toedter.calendar.JDateChooser();
        Canclebtn = new javax.swing.JButton();
        AddBatchToggle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1242, 600));

        Homepannel.setBackground(new java.awt.Color(237, 236, 236));
        Homepannel.setMinimumSize(new java.awt.Dimension(1010, 650));
        Homepannel.setPreferredSize(new java.awt.Dimension(1007, 650));
        Homepannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Footlight MT Light", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 153));
        jLabel32.setText("HS");
        Homepannel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 30, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/hs logo.png"))); // NOI18N
        Homepannel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 90, 90));

        jLabel33.setFont(new java.awt.Font("Footlight MT Light", 1, 30)); // NOI18N
        jLabel33.setText(" Group");
        Homepannel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 130, 50));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setOpaque(true);
        Homepannel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 10, 320));

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setOpaque(true);
        Homepannel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 10, 320));

        jPanel5.setBackground(new java.awt.Color(180, 50, 50));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        Homepannel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 480, 110));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        Homepannel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 550, 530, -1));

        jLabel37.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel37.setText("LARAVEL FRAMEWORK");
        Homepannel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 250, 40));

        jLabel38.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel38.setText("JAVA FRAMEWORK");
        Homepannel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 200, 40));

        jLabel39.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel39.setText("SAILENT FEATURES");
        Homepannel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 200, 40));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        jPanel3.setBackground(new java.awt.Color(255, 0, 51));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("HS Group");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo/IMG-20220720-WA0000.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("New Addmition");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Students");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fees");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Batches");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        PensingPannel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PensingPannel.setForeground(new java.awt.Color(255, 255, 255));
        PensingPannel.setText("Pending Addmition");
        PensingPannel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PensingPannelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PensingPannelMouseEntered(evt);
            }
        });

        mailTab.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mailTab.setForeground(new java.awt.Color(255, 255, 255));
        mailTab.setText("Mail Students");
        mailTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailTabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mailTab, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PensingPannel, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PensingPannel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mailTab, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PendingAdd.setBackground(new java.awt.Color(203, 255, 131));
        PendingAdd.setMinimumSize(new java.awt.Dimension(1150, 650));
        PendingAdd.setPreferredSize(new java.awt.Dimension(1150, 640));
        PendingAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel27.setText("Pending Addmitions");
        PendingAdd.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 19, 380, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_change_user_50px_1.png"))); // NOI18N
        PendingAdd.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 11, 54, -1));

        jPanel13.setBackground(new java.awt.Color(204, 255, 102));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PendingTable.setBackground(new java.awt.Color(102, 102, 102));
        PendingTable.setBorder(new javax.swing.border.MatteBorder(null));
        PendingTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(PendingTable);

        jPanel13.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 662, 388));

        PendingAdd.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 700, 410));

        jButton5.setBackground(new java.awt.Color(0, 255, 255));
        jButton5.setText("Show Pending Add");
        jButton5.setBorder(new javax.swing.border.MatteBorder(null));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        PendingAdd.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 87, 140, 30));

        FeesPannel.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel4.setText("Fees Page");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_money_bag_rupee_50px.png"))); // NOI18N

        FeesTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        FeesTable.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        FeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Name", "Batch", "FeesPaid", "Pending"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(FeesTable);

        javax.swing.GroupLayout FeesPannelLayout = new javax.swing.GroupLayout(FeesPannel);
        FeesPannel.setLayout(FeesPannelLayout);
        FeesPannelLayout.setHorizontalGroup(
            FeesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeesPannelLayout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(418, 418, 418))
            .addGroup(FeesPannelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FeesPannelLayout.setVerticalGroup(
            FeesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FeesPannelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FeesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        MailStudentPannel.setBackground(new java.awt.Color(153, 153, 153));
        MailStudentPannel.setMinimumSize(new java.awt.Dimension(1000, 650));
        MailStudentPannel.setPreferredSize(new java.awt.Dimension(1000, 650));
        MailStudentPannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel53.setText("Mail Students");
        MailStudentPannel.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 270, -1));

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_gmail_logo_50px.png"))); // NOI18N
        MailStudentPannel.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 50, 40));

        MailSendBtn.setBackground(new java.awt.Color(0, 255, 0));
        MailSendBtn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        MailSendBtn.setText("Send Mail");
        MailSendBtn.setBorder(new javax.swing.border.MatteBorder(null));
        MailSendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MailSendBtnActionPerformed(evt);
            }
        });
        MailStudentPannel.add(MailSendBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, 160, 30));

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        MailStudentPannel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, -190, 660, 330));

        jPanel7.setBackground(new java.awt.Color(0, 204, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        MailStudentPannel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -140, 590, 230));

        jPanel10.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        MailStudentPannel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 50, 490, 70));

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        MailStudentPannel.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 210, 30));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setText("Attachment :");
        MailStudentPannel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 120, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        MailStudentPannel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 270, 30));

        jButton4.setBackground(new java.awt.Color(255, 204, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Attach");
        jButton4.setBorder(new javax.swing.border.MatteBorder(null));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        MailStudentPannel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 80, 30));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel42.setText("To :");
        MailStudentPannel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 40, 30));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        MailStudentPannel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 420, 30));

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel55.setText("Subject :");
        MailStudentPannel.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 90, 30));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel56.setText("Messege :");
        MailStudentPannel.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 90, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        MailStudentPannel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 550, 140));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        MailStudentPannel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 420, 30));

        selectManually.setBackground(new java.awt.Color(0, 204, 255));
        selectManually.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        selectManually.setText("Select Bulk");
        selectManually.setBorder(new javax.swing.border.MatteBorder(null));
        selectManually.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectManuallyActionPerformed(evt);
            }
        });
        MailStudentPannel.add(selectManually, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 120, 30));

        jPanel12.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        MailStudentPannel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 220, -1));

        seletEmails.setBackground(new java.awt.Color(255, 255, 153));
        seletEmails.setBorder(new javax.swing.border.MatteBorder(null));
        seletEmails.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectOpt1.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt1.setText("Unplaced students");
        selectOpt1.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt1ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 130, 20));

        selectOpt3.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt3.setText("All Students");
        selectOpt3.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt3ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 120, 20));

        selectOpt4.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt4.setText("fees paid");
        selectOpt4.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt4ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 120, 20));

        selectOpt5.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt5.setText("fees unpaid");
        selectOpt5.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt5ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 120, 20));

        selectOpt6.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt6.setText("active batchs");
        selectOpt6.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt6ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 120, 20));

        selectOpt2.setBackground(new java.awt.Color(255, 0, 0));
        selectOpt2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        selectOpt2.setText("placed students");
        selectOpt2.setBorder(new javax.swing.border.MatteBorder(null));
        selectOpt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOpt2ActionPerformed(evt);
            }
        });
        seletEmails.add(selectOpt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 120, 20));

        MailStudentPannel.add(seletEmails, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 220, 320));

        NewAddPannel.setPreferredSize(new java.awt.Dimension(1000, 650));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel14.setText("Addmission Page");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_add_user_male_50px.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel16.setText("Student ID :");

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel17.setText("Email :");

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel18.setText("Name :");

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel19.setText("Mobile :");

        StdID.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel21.setText("Address :");

        jLabel22.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel22.setText("Batch :");

        jLabel23.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel23.setText("College :");

        jLabel24.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel24.setText("Branch :");

        jLabel25.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel25.setText("Fees Paid :");

        colltf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colltfActionPerformed(evt);
            }
        });

        addtf.setColumns(20);
        addtf.setRows(5);
        jScrollPane2.setViewportView(addtf);

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("WAITING");

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        imgLable.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
        );

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_image_gallery_15px.png"))); // NOI18N
        jButton3.setText("Browse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        selectedBatch.setBackground(new java.awt.Color(102, 102, 102));
        selectedBatch.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        selectedBatch.setForeground(new java.awt.Color(255, 255, 255));
        selectedBatch.setText("      Select Batch      ");
        selectedBatch.setOpaque(true);
        selectedBatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedBatchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout NewAddPannelLayout = new javax.swing.GroupLayout(NewAddPannel);
        NewAddPannel.setLayout(NewAddPannelLayout);
        NewAddPannelLayout.setHorizontalGroup(
            NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewAddPannelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewAddPannelLayout.createSequentialGroup()
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(StdID, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namrtf)
                            .addComponent(mobiletf)
                            .addComponent(emailtf)
                            .addComponent(branchtf)
                            .addComponent(feetf)
                            .addComponent(colltf)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NewAddPannelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(232, 232, 232))
                            .addGroup(NewAddPannelLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(NewAddPannelLayout.createSequentialGroup()
                        .addComponent(selectedBatch)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewAddPannelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );
        NewAddPannelLayout.setVerticalGroup(
            NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewAddPannelLayout.createSequentialGroup()
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NewAddPannelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StdID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namrtf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewAddPannelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobiletf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colltf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(branchtf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feetf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(NewAddPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        StudentPannel.setBackground(new java.awt.Color(255, 255, 255));
        StudentPannel.setPreferredSize(new java.awt.Dimension(1000, 650));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel10.setText("Students Page");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_user_groups_50px_2.png"))); // NOI18N

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 0, 0)));

        StdTable.setBackground(new java.awt.Color(255, 204, 204));
        StdTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 51), 1, true));
        StdTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "Student Id", "Name", "Batch", "Mobile"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StdTable.setEditingColumn(0);
        StdTable.setEditingRow(0);
        StdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StdTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StdTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(StdTable);

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_reset_20px.png"))); // NOI18N
        jLabel12.setText("Refresh");
        jLabel12.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        searchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTFActionPerformed(evt);
            }
        });
        searchTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTFKeyPressed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_search_20px.png"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel29.setText("Toatal Students :");

        TotalSTD.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel30.setText("Active Students :");

        ActiveSTD.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel31.setText("Placed Students :");

        PlacedSTD.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        javax.swing.GroupLayout StudentPannelLayout = new javax.swing.GroupLayout(StudentPannel);
        StudentPannel.setLayout(StudentPannelLayout);
        StudentPannelLayout.setHorizontalGroup(
            StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPannelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StudentPannelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(StudentPannelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPannelLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(PlacedSTD, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(StudentPannelLayout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addGap(18, 18, 18)
                                    .addComponent(TotalSTD, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(StudentPannelLayout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addGap(18, 18, 18)
                                    .addComponent(ActiveSTD, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(90, 90, 90))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentPannelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(270, 270, 270))
        );
        StudentPannelLayout.setVerticalGroup(
            StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentPannelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StudentPannelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StudentPannelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(TotalSTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(ActiveSTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(StudentPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(PlacedSTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Batchpannel.setBackground(new java.awt.Color(216, 206, 206));
        Batchpannel.setMinimumSize(new java.awt.Dimension(1000, 650));
        Batchpannel.setPreferredSize(new java.awt.Dimension(1000, 650));
        Batchpannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        jLabel5.setText("Batches");
        Batchpannel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 155, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_classroom_50px.png"))); // NOI18N
        Batchpannel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 42));

        BatchTable.setBackground(new java.awt.Color(255, 204, 204));
        BatchTable.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        BatchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Batch No", "Cource Name", "Batch timing", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BatchTable.setGridColor(new java.awt.Color(0, 0, 0));
        BatchTable.setRowHeight(40);
        BatchTable.setRowMargin(5);
        BatchTable.setRowSelectionAllowed(false);
        BatchTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        BatchTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        BatchTable.setShowGrid(true);
        BatchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BatchTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(BatchTable);

        Batchpannel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 510, 270));

        AddNewBatch.setBackground(new java.awt.Color(0, 0, 0));
        AddNewBatch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel41.setText("Add New Batch");
        jPanel8.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 130, 20));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewLogo/icons8_google_classroom_25px_2.png"))); // NOI18N
        jPanel8.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 30, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Batch No : ");
        jPanel8.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        BatchNo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel8.add(BatchNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 60, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Total Fees :");
        jPanel8.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Cource Name : ");
        jPanel8.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Batch Timing : ");
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("End date : ");
        jPanel8.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Students :");
        jPanel8.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 20));

        AddBtn.setBackground(new java.awt.Color(0, 204, 255));
        AddBtn.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        AddBtn.setForeground(new java.awt.Color(51, 51, 51));
        AddBtn.setText("Add Batch");
        AddBtn.setBorder(new javax.swing.border.MatteBorder(null));
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });
        jPanel8.add(AddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 90, 20));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Batch Name: ");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        Tfees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfeesActionPerformed(evt);
            }
        });
        jPanel8.add(Tfees, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 120, -1));

        Bname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnameActionPerformed(evt);
            }
        });
        jPanel8.add(Bname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 120, -1));

        Cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CnameActionPerformed(evt);
            }
        });
        jPanel8.add(Cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 120, -1));

        Btimeing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtimeingActionPerformed(evt);
            }
        });
        jPanel8.add(Btimeing, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 120, -1));

        stds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdsActionPerformed(evt);
            }
        });
        jPanel8.add(stds, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 90, -1));

        Active.setBackground(new java.awt.Color(0, 255, 0));
        Active.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Active.setText("Active");
        Active.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel8.add(Active, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Total Students :");
        jPanel8.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, 20));

        TotalStd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalStdActionPerformed(evt);
            }
        });
        jPanel8.add(TotalStd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 90, -1));
        jPanel8.add(enddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Duration  ");
        jPanel8.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, 20));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Start date : ");
        jPanel8.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 20));
        jPanel8.add(startdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        Canclebtn.setBackground(new java.awt.Color(255, 0, 51));
        Canclebtn.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Canclebtn.setForeground(new java.awt.Color(255, 255, 255));
        Canclebtn.setText("Cancle");
        Canclebtn.setBorder(new javax.swing.border.MatteBorder(null));
        Canclebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CanclebtnMouseClicked(evt);
            }
        });
        Canclebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanclebtnActionPerformed(evt);
            }
        });
        jPanel8.add(Canclebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 70, 20));

        AddNewBatch.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 410));

        Batchpannel.add(AddNewBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 340, 430));

        AddBatchToggle.setBackground(new java.awt.Color(255, 0, 51));
        AddBatchToggle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AddBatchToggle.setForeground(new java.awt.Color(255, 255, 255));
        AddBatchToggle.setText("Cancle Adding");
        AddBatchToggle.setBorder(new javax.swing.border.MatteBorder(null));
        AddBatchToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBatchToggleActionPerformed(evt);
            }
        });
        Batchpannel.add(AddBatchToggle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 130, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewAddPannel, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(StudentPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(FeesPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Batchpannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PendingAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(236, Short.MAX_VALUE)
                    .addComponent(MailStudentPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(233, 233, 233)
                    .addComponent(Homepannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FeesPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PendingAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(StudentPannel, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Batchpannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewAddPannel, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MailStudentPannel, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(Homepannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

        NewAddPannel.setVisible(true);
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(false);
        PendingAdd.setVisible(false);
        Homepannel.setVisible(false);
        MailStudentPannel.setVisible(false);

        seletEmails.setVisible(false);

        //Student ID
        try {

            String sql = "Select max(stdID) From addmitions ; ";
            PreparedStatement ps = con.prepareCall(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt(1);

                StdID.setText("" + (ID + 1) + "");
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Students page :-
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(true);
        Batchpannel.setVisible(false);
        NewAddPannel.setVisible(false);
        PendingAdd.setVisible(false);
        Homepannel.setVisible(false);
        MailStudentPannel.setVisible(false);

        seletEmails.setVisible(false);

        //table
        try {
            String sql = "select stdId,name,mobile,branch from addmitions";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StdTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        try {
            String sql = "select Count(stdID) From addmitions; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String TS = rs.getString(1);
                TotalSTD.setText(TS);
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        try {
            String sql = "select Count(company) From addmitions; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String pstd = rs.getString(1);
                PlacedSTD.setText(pstd);
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        try {
            String sql = "select batch From addmitions; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int count = 0;
            if (rs.next()) {

                String sql1 = "select Batch_No From batches where Status='Active' ; ";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    count++;
                }

            }

            ActiveSTD.setText("" + count);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        //feesTable
        try {
            String sql = "select stdId,name,feesPaid,TotalFees from addmitions where feesPaid!=TotalFees ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            FeesTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        Homepannel.setVisible(false);
        FeesPannel.setVisible(true);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(false);
        NewAddPannel.setVisible(false);
        PendingAdd.setVisible(false);
        MailStudentPannel.setVisible(false);

        seletEmails.setVisible(false);


    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        // Batches page
        PendingAdd.setVisible(false);
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(true);
        NewAddPannel.setVisible(false);
        Homepannel.setVisible(false);
        MailStudentPannel.setVisible(false);

        seletEmails.setVisible(false);

        jLabel41.setText("Add New Batch");
        Canclebtn.setVisible(false);
        AddBtn.setText("Add Batch");
        AddBtn.setBackground(Color.CYAN);

        //table
        try {
            String sql = "select Batch_No, Batch_Name , Timing , Status from batches ; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            BatchTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        try {
            String sql = "select Count(Batch_No) From batches ; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int TS = rs.getInt(1);
                BatchNo1.setText("" + (TS + 1) + "");
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_jLabel6MouseClicked

    private void PensingPannelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PensingPannelMouseClicked
        // TODO add your handling code here:

        PendingAdd.setVisible(true);
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(false);
        NewAddPannel.setVisible(false);
        Homepannel.setVisible(false);
        MailStudentPannel.setVisible(false);

        seletEmails.setVisible(false);
        
        jPanel13.setVisible(false);

    }//GEN-LAST:event_PensingPannelMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:

        //Refresh
        try {

            String sql = "select stdId,name,mobile,branch from addmitions";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StdTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

        searchTF.setText("");


    }//GEN-LAST:event_jLabel12MouseClicked

    private void searchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTFActionPerformed
        // TODO add your handling code here:

        try {
            String srh = searchTF.getText();

            String sql = "select stdID , name , batch, mobile from addmitions where name like '%" + srh + "%' or stdID like '%" + srh + "%' or batch like '%" + srh + "%'  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StdTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }//GEN-LAST:event_searchTFActionPerformed

    private void colltfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colltfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colltfActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String ids = StdID.getText();
        int Id = Integer.parseInt(ids);

        String Name = namrtf.getText();
        String Email = emailtf.getText();
        String Mob = mobiletf.getText();
        String Add = addtf.getText();
        String Col = colltf.getText();
        String Branch = branchtf.getText();
        String FeesP = feetf.getText();

        String Npath = "";

        String[] substrings = path.split("");

        for (int x = 0; x < substrings.length; x++) {

            if (substrings[x].equalsIgnoreCase("\\")) {
                substrings[x] = "//";
            }
            Npath = Npath + substrings[x];

        }

        try {

            String sql = "insert into addmitions (stdID,name,email,mobile,address,collage,branch,feesPaid,img,batch) values('" + Id + "', '" + Name + "', '" + Email + "', '" + Mob + "', '" + Add + "', '" + Col + "', '" + Branch + "', '" + FeesP + "', '" + Npath + "','" + selectedBatchNo + "'   ) ;";

            PreparedStatement ps = con.prepareCall(sql);

            int i = ps.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(NewAddPannel, "Addimition Done 👍");
                ApplicationClear();

                String msg = "Hello " + Name + "!! "
                        + "\nYour addmition is confirm"
                        + " \n\nFees paid:- " + FeesP + "";
                String sub = "Addmition DONE!!!!";

                sendEmail(msg, sub, Email, "sharesayan22@gmail.com");

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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


    }//GEN-LAST:event_jButton3ActionPerformed

    private void StdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StdTableMouseClicked

        //student select table
        try {

            TableModel model = StdTable.getModel();
            int row = StdTable.getSelectedRow();
            int StdID = (int) model.getValueAt(row, 0);

            System.out.println(StdID);
            new StudentInfo(StdID).setVisible(true);

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }//GEN-LAST:event_StdTableMouseClicked

    private void searchTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTFKeyPressed

        try {
            String srh = searchTF.getText();

            String sql = "select stdID , name , batch, mobile from addmitions where name like '%" + srh + "%' or stdID like '%" + srh + "%' or batch like '%" + srh + "%'  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            StdTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }//GEN-LAST:event_searchTFKeyPressed

    private void TfeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfeesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfeesActionPerformed

    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BnameActionPerformed

    private void CnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CnameActionPerformed

    private void BtimeingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtimeingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtimeingActionPerformed

    private void stdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stdsActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed

        try {

            String Status = "Deactive";

            //add batch button
            int Bno = Integer.parseInt(BatchNo1.getText());
            String name = Bname.getText();
            String Course = Cname.getText();
            String Timing = Btimeing.getText();
            String Std = stds.getText();
            String Tstd = TotalSTD.getText();
            String TFees = Tfees.getText();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Date SD = startdate.getDate();
            String SD1 = df.format(SD);
            Date SDate = df.parse(SD1);

            System.out.println(SD1);

            Date ED = enddate.getDate();
            String ED1 = df.format(ED);
            Date EDate = df.parse(ED1);

            if (Active.isSelected()) {

                Status = "Active";

                Date startDate = new Date();

//                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = null;
//                try {
//                    date = dateFormatter.parse("2022-07-24 01:42:00");
//                } catch (java.text.ParseException ex) {
//                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//                }
                //Now create the time and schedule it
                Timer timer = new Timer();

                //Use this if you want to execute it once
                timer.schedule(new MyTimeTask(), EDate);

                //Use this if you want to execute it repeatedly
                //int period = 10000;//10secs
                //timer.schedule(new MyTimeTask(), date, period );
            }

            try {

                String sql = "insert into batches ( Batch_No,Batch_Name,Course,Timing,Status,startTIme,endTime) values( '" + Bno + "', '" + name + "', '" + Course + "', '" + Timing + "',  '" + Status + "' , '" + SD1 + "' , '" + ED1 + "'  ) ;";
                PreparedStatement ps = con.prepareCall(sql);

                int i = ps.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(NewAddPannel, "Batch Added");

                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_AddBtnActionPerformed

    private void BatchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BatchTableMouseClicked

        // batch table 
        try {

            TableModel model = BatchTable.getModel();
            int row = BatchTable.getSelectedRow();
            int Bno = (int) model.getValueAt(row, 0);

            System.out.println(Bno);

            AddNewBatch.setVisible(true);
            jLabel41.setText("Batch Detials");
            AddBtn.setVisible(true);
            AddBtn.setText("Edit");
            AddBtn.setBackground(Color.gray);

            Canclebtn.setVisible(true);
            Canclebtn.setBackground(Color.red);

            //set all values
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs-institute-management", "root", "sayan");
            String sql = "Select * From batches where Batch_No='" + Bno + "' ";

            pr = con.prepareStatement(sql);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {

                BatchNo1.setText(rs.getString("Batch_No"));
                Bname.setText(rs.getString("Batch_Name"));
                Cname.setText(rs.getString("Course"));
                Btimeing.setText(rs.getString("Timing"));
                TotalStd.setText(rs.getString("Total_Students"));
                stds.setText(rs.getString("Students"));
                Tfees.setText(rs.getString("Total_Fees"));

                String SDate = rs.getString("startTime");
                String EDate = rs.getString("endTime");

                System.out.println(SDate);

                Date d1 = new SimpleDateFormat("dd/mm/yyyy").parse(SDate);
                Date d2 = new SimpleDateFormat("dd/mm/yyyy").parse(EDate);

                startdate.setDate(d1);
                enddate.setDate(d2);

            }

            //make all non editable
            Bname.setEditable(false);
            Cname.setEditable(false);
            Btimeing.setEditable(false);
            startdate.setEnabled(false);
            startdate.setEnabled(false);
            stds.setEditable(false);
            TotalStd.setEditable(false);
            Tfees.setEditable(false);

        } catch (Exception e) {
            System.out.println("" + e);
            e.printStackTrace();
        }


    }//GEN-LAST:event_BatchTableMouseClicked

    private void AddBatchToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBatchToggleActionPerformed
        // TODO add your handling code here:

        if (AddNewBatch.isVisible()) {
            AddNewBatch.setVisible(false);
            AddBatchToggle.setText("Add New Batch");
            AddBatchToggle.setBackground(Color.green);

            try {
                String sql = "select Count(Batch_No) From batches ; ";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int TS = rs.getInt(1);
                    BatchNo1.setText("" + (TS + 1) + "");
                }

            } catch (Exception ex) {
                System.out.println("" + ex);
            }

        } else {
            AddNewBatch.setVisible(true);
            AddBatchToggle.setText("Cancle Adding");
            AddBatchToggle.setBackground(Color.red);
        }


    }//GEN-LAST:event_AddBatchToggleActionPerformed

    private void TotalStdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalStdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalStdActionPerformed

    private void mailTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailTabMouseClicked
        // TODO add your handling code here:

        PendingAdd.setVisible(false);
        FeesPannel.setVisible(false);
        StudentPannel.setVisible(false);
        Batchpannel.setVisible(false);
        NewAddPannel.setVisible(false);
        Homepannel.setVisible(false);
        MailStudentPannel.setVisible(true);

        emailList.clear();


    }//GEN-LAST:event_mailTabMouseClicked

    private void CanclebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanclebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CanclebtnActionPerformed

    private void CanclebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CanclebtnMouseClicked
        // TODO add your handling code here:

        Canclebtn.setVisible(false);
        AddNewBatch.setVisible(false);
        AddBatchToggle.setText("Add New Batch");
        AddBatchToggle.setBackground(Color.green);

    }//GEN-LAST:event_CanclebtnMouseClicked

    private void StdTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StdTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_StdTableMouseEntered

    private void selectedBatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedBatchMouseClicked
        // TODO add your handling code here:

        BatchInfo bi = new BatchInfo();
        bi.setVisible(true);

    }//GEN-LAST:event_selectedBatchMouseClicked

    private void MailSendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MailSendBtnActionPerformed
        // TODO add your handling code here:

        String msg = jTextArea1.getText();
        String to = jTextField2.getText();
        String sub = jTextField3.getText();
        String From = "sharesayan22@gmail.com";

        String attach = Attachpath;
        System.out.println(attach);

        if (emailList.size() > 0) {
            for (int i = 0; i < emailList.size(); i++) {

                to = emailList.get(i);

                if (attach != null) {
                    sendEmail(msg, sub, to, From);
                    JOptionPane.showConfirmDialog(MailStudentPannel, "sending mail 🔃");
                } else {
                    sendAttach(msg, sub, to, From, attach);
                    JOptionPane.showConfirmDialog(MailStudentPannel, "sending mail 🔃");
                }

            }
        } else {

            if (attach != null) {
                sendEmail(msg, sub, to, From);
            } else {
                sendAttach(msg, sub, to, From, attach);
            }

        }


    }//GEN-LAST:event_MailSendBtnActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            Attachpath = file.getPath();        //to get the path

            jTextField1.setText(path);

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void selectManuallyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectManuallyActionPerformed
        // TODO add your 

        seletEmails.setVisible(true);
        System.out.println("fired");

        emailList.clear();


    }//GEN-LAST:event_selectManuallyActionPerformed

    private void selectOpt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt1ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select feesPaid ,company from addmitions where company=NULL ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_selectOpt1ActionPerformed

    private void selectOpt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt3ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select name , email , batch from addmitions";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_selectOpt3ActionPerformed

    private void selectOpt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt4ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select feesPaid , email , totalFees from addmitions where feesPaid=totalFees ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_selectOpt4ActionPerformed

    private void selectOpt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt5ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select feesPaid , email , totalFees from addmitions where feesPaid!=totalFees ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_selectOpt5ActionPerformed

    private void selectOpt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt6ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select Status , email addmitions where Status='Active' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }

    }//GEN-LAST:event_selectOpt6ActionPerformed

    private void selectOpt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOpt2ActionPerformed
        // TODO add your handling code here:

        try {
            String sql = "select feesPaid ,company from addmitions where company!=NULL ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailList.add(rs.getString("email"));
            }

            seletEmails.setVisible(false);

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }//GEN-LAST:event_selectOpt2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        if(jPanel13.isVisible()){
            jPanel13.setVisible(false);
        }
        else{
            jPanel13.setVisible(true);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void PensingPannelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PensingPannelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PensingPannelMouseEntered

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Active;
    private javax.swing.JLabel ActiveSTD;
    private javax.swing.JButton AddBatchToggle;
    private javax.swing.JButton AddBtn;
    private javax.swing.JPanel AddNewBatch;
    private javax.swing.JLabel BatchNo1;
    private javax.swing.JTable BatchTable;
    private javax.swing.JPanel Batchpannel;
    private javax.swing.JTextField Bname;
    private javax.swing.JTextField Btimeing;
    private javax.swing.JButton Canclebtn;
    private javax.swing.JTextField Cname;
    private javax.swing.JPanel FeesPannel;
    private javax.swing.JTable FeesTable;
    private javax.swing.JPanel Homepannel;
    private javax.swing.JButton MailSendBtn;
    private javax.swing.JPanel MailStudentPannel;
    private javax.swing.JPanel NewAddPannel;
    private javax.swing.JPanel PendingAdd;
    private javax.swing.JTable PendingTable;
    private javax.swing.JLabel PensingPannel;
    private javax.swing.JLabel PlacedSTD;
    private javax.swing.JLabel StdID;
    private javax.swing.JTable StdTable;
    private javax.swing.JPanel StudentPannel;
    private javax.swing.JTextField Tfees;
    private javax.swing.JLabel TotalSTD;
    private javax.swing.JTextField TotalStd;
    private javax.swing.JTextArea addtf;
    private javax.swing.JTextField branchtf;
    private javax.swing.JTextField colltf;
    private javax.swing.JTextField emailtf;
    private com.toedter.calendar.JDateChooser enddate;
    private javax.swing.JTextField feetf;
    private javax.swing.JLabel imgLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel mailTab;
    private javax.swing.JTextField mobiletf;
    private javax.swing.JTextField namrtf;
    private javax.swing.JTextField searchTF;
    private javax.swing.JButton selectManually;
    private javax.swing.JButton selectOpt1;
    private javax.swing.JButton selectOpt2;
    private javax.swing.JButton selectOpt3;
    private javax.swing.JButton selectOpt4;
    private javax.swing.JButton selectOpt5;
    private javax.swing.JButton selectOpt6;
    private javax.swing.JLabel selectedBatch;
    private javax.swing.JPanel seletEmails;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JTextField stds;
    // End of variables declaration//GEN-END:variables
}
