/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package source;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.HashMap;

public class pnIpt extends javax.swing.JPanel {
    
    private int num,idAu,idCtg,idTg;
    private HashMap tags = new HashMap<>();
    private ResultSet bagData;
    private conDB truck = new conDB();
    
    private void clear(){
        cbNm.setSelectedIndex(0);
        txTtl.setText("");
        txMail.setText("");
        txCtnt.setText("");
        cbCtg.setSelectedIndex(0);
        cbx1.setSelected(false);
        cbx2.setSelected(false);
        cbx3.setSelected(false);
        tags.clear();
    }
    
    private void showCnm(){
        cbNm.addItem("Select Author Name...");
        try{
            bagData = truck.contains.executeQuery("SELECT * FROM author");
            while(bagData.next()){
                cbNm.addItem(bagData.getString("name"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Name not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void showCtg(){
        cbCtg.addItem("Select Category Article...");
        try{
            bagData = truck.contains.executeQuery("SELECT * FROM category");
            while(bagData.next()){
                cbCtg.addItem(bagData.getString("name"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Category not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void autoId(){
        try{
            bagData=truck.contains.executeQuery("SELECT * FROM article ORDER BY id DESC");
            if(bagData.next()){
                int nId = Integer.valueOf(bagData.getString("id"));
                num = nId + 1;
            }else{
                num = 1;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Nothing id !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void fSltname(){
        cbNm.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent hearFunction){
                try{
                    bagData=truck.contains.executeQuery("SELECT * FROM author WHERE name = '"+cbNm.getSelectedItem()+"'");
                    if(bagData.next()){
                        txMail.setText(bagData.getString("email"));
                        idAu=Integer.valueOf(bagData.getString("id"));
                    }else{
                        txMail.setText("");
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Name not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    
    private void fSltcgt(){
        cbCtg.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent hearFunction){
                try{
                    bagData=truck.contains.executeQuery("SELECT * FROM category WHERE name = '"+cbCtg.getSelectedItem()+"'");
                    if(bagData.next()){
                        idCtg=Integer.valueOf(bagData.getString("id"));
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Article not showing up !","WARNING",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    
    private void fCekBox(){
        cbx1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx1.isSelected()){
                    tags.put("One",1);
                }else{
                    tags.remove("One");
                }
            }
        });
        cbx2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx2.isSelected()){
                    tags.put("Two",2);
                }else{
                    tags.remove("Two");
                }
            }
        });
        cbx3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx3.isSelected()){
                    tags.put("Three",3);
                }else{
                    tags.remove("Three");
                }
            }
        });
    }
    
    private void fSave(){
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent hearFunction) {
                if(txMail.getText().isEmpty()||txTtl.getText().isEmpty() || txCtnt.getText().isEmpty() || cbCtg.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Make sure all fields are filled","CAUTION",JOptionPane.WARNING_MESSAGE);
                }else if(cbNm.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null,"Please select author name an article !","CAUTION",JOptionPane.WARNING_MESSAGE);
                }else{
                    try{
                        int question = JOptionPane.showConfirmDialog(null, "Are you sure insert the article ?", "QUESTION", JOptionPane.YES_NO_OPTION);
                        if(question==JOptionPane.YES_OPTION){
                            truck.contains.executeUpdate("INSERT INTO article VALUES('"+num+"','"+txTtl.getText()+"','"+txCtnt.getText()+"','"+idAu+"')");
                            truck.contains.executeUpdate("INSERT INTO art2ctg VALUES('"+num+"','"+idCtg+"')");
                            for(Object count : tags.values()){
                                truck.contains.executeUpdate("INSERT INTO art2tg VALUES('"+num+"','"+count+"')");
                            }
                            JOptionPane.showMessageDialog(null, "Article has been saved !", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            clear();
                            autoId();
                        }else{
                            clear();
                        } 
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Article not saved !","WARNING",JOptionPane.WARNING_MESSAGE);
                        System.out.println(e.getMessage());
                        System.exit(0);
                    }
                }
            }
        });
    }
                
    public pnIpt() {
        truck.routing();
        initComponents();
        txMail.setEnabled(false);
        showCnm();
        autoId();
        showCtg();
        fSltname();
        fSltcgt();
        fCekBox();
        fSave();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        page = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        lbNm = new javax.swing.JLabel();
        lbMail = new javax.swing.JLabel();
        lbTtl = new javax.swing.JLabel();
        lbCtnt = new javax.swing.JLabel();
        lbCtg = new javax.swing.JLabel();
        lbTgs = new javax.swing.JLabel();
        cbNm = new javax.swing.JComboBox<>();
        txMail = new javax.swing.JTextField();
        txTtl = new javax.swing.JTextField();
        txCtnt = new javax.swing.JTextField();
        cbCtg = new javax.swing.JComboBox<>();
        btSave = new javax.swing.JButton();
        cbx1 = new javax.swing.JCheckBox();
        cbx2 = new javax.swing.JCheckBox();
        cbx3 = new javax.swing.JCheckBox();

        setLayout(new java.awt.CardLayout());

        page.setBackground(new java.awt.Color(255, 255, 255));
        page.setLayout(new java.awt.CardLayout());

        content.setBackground(new java.awt.Color(255, 255, 0));

        lbNm.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbNm.setText("Name");

        lbMail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbMail.setText("Email");

        lbTtl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTtl.setText("Title");

        lbCtnt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbCtnt.setText("Content");

        lbCtg.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbCtg.setText("Category");

        lbTgs.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTgs.setText("Tags");

        cbNm.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txMail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txTtl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txCtnt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbCtg.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btSave.setText("SAVE");

        cbx1.setText("Laravel");

        cbx2.setText("PHP");

        cbx3.setText("CodeIgniter");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbTtl)
                .addGap(33, 33, 33)
                .addComponent(txTtl)
                .addGap(14, 14, 14))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbCtnt)
                .addGap(13, 13, 13)
                .addComponent(txCtnt)
                .addGap(14, 14, 14))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbCtg)
                .addGap(7, 7, 7)
                .addComponent(cbCtg, 0, 200, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbTgs)
                .addGap(31, 31, 31)
                .addComponent(cbx1)
                .addGap(4, 4, 4)
                .addComponent(cbx2)
                .addGap(5, 5, 5)
                .addComponent(cbx3))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbNm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(cbNm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbMail)
                        .addGap(26, 26, 26)
                        .addComponent(txMail)))
                .addGap(14, 14, 14))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbNm))
                    .addComponent(cbNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbMail))
                    .addComponent(txMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbTtl))
                    .addComponent(txTtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbCtnt))
                    .addComponent(txCtnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbCtg))
                    .addComponent(cbCtg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbTgs))
                    .addComponent(cbx1)
                    .addComponent(cbx2)
                    .addComponent(cbx3))
                .addGap(24, 24, 24)
                .addComponent(btSave))
        );

        page.add(content, "card2");

        add(page, "card16");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox<String> cbCtg;
    private javax.swing.JComboBox<String> cbNm;
    private javax.swing.JCheckBox cbx1;
    private javax.swing.JCheckBox cbx2;
    private javax.swing.JCheckBox cbx3;
    private javax.swing.JPanel content;
    private javax.swing.JLabel lbCtg;
    private javax.swing.JLabel lbCtnt;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbNm;
    private javax.swing.JLabel lbTgs;
    private javax.swing.JLabel lbTtl;
    private javax.swing.JPanel page;
    private javax.swing.JTextField txCtnt;
    private javax.swing.JTextField txMail;
    private javax.swing.JTextField txTtl;
    // End of variables declaration//GEN-END:variables
}
