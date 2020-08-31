/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsassignment;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.HashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Imperfect
 */
public class NoteAdd extends javax.swing.JFrame {

    /**
     * Creates new form NoteAdd
     */
    public NoteAdd() {
        initComponents();
    }
    
    public SecretKeySpec generateSecretkeySpec(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        byte[] sb = Base64.getDecoder().decode(salt);
        final PBEKeySpec key = new PBEKeySpec(password.toCharArray(), sb, 12000, 256);
        final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        final byte[] kb = skf.generateSecret(key).getEncoded();
        return new SecretKeySpec(kb, "AES");
    }
    //@RequiresApi(api = Build.VERSION_CODES.O)
    public IvParameterSpec generateIvSpec(String iv)
    {
        byte[] i = Base64.getDecoder().decode(iv.getBytes());
        return new IvParameterSpec(i);
    }
    //@RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String,String> encrypt(String ... arg) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        SecureRandom rand = new SecureRandom();
        byte[] s = new byte[256];
        rand.nextBytes(s);
        final String salt = Base64.getEncoder().encodeToString(s);
        final String password = arg[0];
        final SecretKeySpec keySpec = generateSecretkeySpec(password,salt);
        rand = new SecureRandom();
        byte[] i = new byte[16];
        rand.nextBytes(i);
        final String iv = Base64.getEncoder().withoutPadding().encodeToString(i);
        final IvParameterSpec ivSpec = generateIvSpec(iv);
        final String message=arg[1];
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        final byte[] emsg = cipher.doFinal(message.getBytes());
        final String emessage = Base64.getEncoder().encodeToString(emsg);
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("salt", salt);map.put("iv",iv);map.put("message", emessage);return map;
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
        TitleUI = new javax.swing.JTextField();
        PasswordUI = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        MessageUI = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 0));

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));

        TitleUI.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TitleUI.setText("Please Enter Title");
        TitleUI.setToolTipText("");

        PasswordUI.setText("Please Enter Password");
        PasswordUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordUIActionPerformed(evt);
            }
        });

        MessageUI.setColumns(20);
        MessageUI.setRows(5);
        MessageUI.setText("Please Enter Your Message here");
        jScrollPane1.setViewportView(MessageUI);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 76, 60));
        jLabel1.setText("ADD A NOTE");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Submit.setBackground(new java.awt.Color(231, 76, 60));
        Submit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Submit.setText("SUBMIT");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordUI, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TitleUI, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addComponent(TitleUI, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(PasswordUI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jMenuBar1.setBackground(new java.awt.Color(231, 76, 60));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 0, 0), null, null));

        jMenu1.setText("ADD NOTE");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("ACCESS NOTE");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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

    private void PasswordUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordUIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordUIActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
        NoteAdd obj=new NoteAdd();

        String a=TitleUI.getText();
        String b=PasswordUI.getText();
        String c=MessageUI.getText();

        try{
            HashMap<String,String> map = obj.encrypt(b,c);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db?serverTimezone=UTC","root","");
            System.out.println(cn);
            String query1 ="insert into aesalgo(title,etext,iv,salt) values(?,?,?,?)";

            PreparedStatement pst = (PreparedStatement) cn.prepareStatement(query1);
            System.out.println(map.get("message"));

            pst.setString(1,a);
            pst.setString(2,map.get("message"));
            pst.setString(3,map.get("iv"));
            pst.setString(4,map.get("salt"));

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Note Saved Successfully");

            TitleUI.setText("");
            PasswordUI.setText("");
            MessageUI.setText("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error");
        }
    }//GEN-LAST:event_SubmitActionPerformed
    
                                         
   
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
            java.util.logging.Logger.getLogger(NoteAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NoteAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NoteAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NoteAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NoteAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea MessageUI;
    private javax.swing.JTextField PasswordUI;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField TitleUI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
