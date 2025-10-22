/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package source;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class pnEdit extends javax.swing.JPanel {
    
    private int num,idAu,idCtg;
    private DefaultListModel dlm;
    private HashMap tagsOut = new HashMap<>();
    private ArrayList tagsIn = new ArrayList();
    private ResultSet bagData,bagDataArt,bagDataCtg,bagDataTg;
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
        tagsIn.clear();
        tagsOut.clear();
    }
    
    private void showLart(){
        dlm = new DefaultListModel();
        ltArt.setModel(dlm);
        try{
            bagData=truck.contains.executeQuery("SELECT title FROM article");
            while(bagData.next()){
                Object reg = bagData.getString("title");
                dlm.addElement(reg);
            }
        }catch(Exception e){
            e.getMessage();
        }
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
    
    private void cbxIncomeDB(){
        tagsOut.clear();
        if(tagsIn.contains(1)){
            cbx1.setSelected(true);
            tagsOut.put("One",1);
        }else{
            cbx1.setSelected(false);
        }
        
        if(tagsIn.contains(2)){
            cbx2.setSelected(true);
            tagsOut.put("Two",2);
        }else{
            cbx2.setSelected(false);
        }
        
        if(tagsIn.contains(3)){
            cbx3.setSelected(true);
            tagsOut.put("Three",3);
        }else{
            cbx3.setSelected(false);
        }
    }
    
    private void fCekBox(){
        cbx1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx1.isSelected()){
                    tagsOut.put("One",1);
                }else{
                    tagsOut.remove("One");
                }
            }
        });
        cbx2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx2.isSelected()){
                    tagsOut.put("Two",2);
                }else{
                    tagsOut.remove("Two");
                }
            }
        });
        cbx3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx3.isSelected()){
                    tagsOut.put("Three",3);
                }else{
                    tagsOut.remove("Three");
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
                    JOptionPane.showMessageDialog(null,"ID category checker is not working !","WARNING",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
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
    
    private void fLart(){
        ltArt.addMouseListener(new MouseAdapter (){
            @Override
            public void mouseClicked(MouseEvent hearFunction){
                try{
                    bagDataArt=truck.contains.executeQuery("SELECT * FROM article WHERE title='"+ltArt.getSelectedValue()+"'");
                    if(bagDataArt.next()){
                        num = bagDataArt.getInt(1);
                        txTtl.setText(bagDataArt.getString("title"));
                        txCtnt.setText(bagDataArt.getString("content"));
                        idAu=bagDataArt.getInt(4);
                    }
                    bagData=truck.contains.executeQuery("SELECT * FROM author WHERE id='"+idAu+"'");
                    if(bagData.next()){
                       txMail.setText(bagData.getString("email"));
                       cbNm.setSelectedItem(bagData.getString("name"));
                    }
                    bagDataCtg=truck.contains.executeQuery("SELECT * FROM art2ctg WHERE article_id='"+num+"'");
                    if(bagDataCtg.next()){
                        cbCtg.setSelectedIndex(bagDataCtg.getInt("category_id"));
                    }
                    tagsIn.clear();
                    bagDataTg=truck.contains.executeQuery("SELECT * FROM art2tg WHERE article_id='"+num+"'");
                    while(bagDataTg.next()){
                        tagsIn.add(Integer.valueOf(bagDataTg.getString("tag_id")));
                    }
                    //Bagian iko manambahan data dari variable tagsIn (ARRAYLIST) ka variable tagsOut (HASHMAP) 
                    for(int i=0;i<tagsIn.size();i++){
                        tagsOut.put(i, tagsIn.get(i));
                    }
                    cbxIncomeDB();
                    btEdit.setEnabled(true);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Database disconnected, try check or restart this application !","WARNING",JOptionPane.WARNING_MESSAGE);
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    
    private void fUpdate(){
        btEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                try{
                    int question = JOptionPane.showConfirmDialog(null, "Are you sure edit this article data?", "QUESTION", JOptionPane.YES_NO_OPTION);
                    if(question==JOptionPane.YES_OPTION){
                        truck.contains.executeUpdate("UPDATE article SET title='"+txTtl.getText()+"',content='"+txCtnt.getText()+"',author_id='"+idAu+"' WHERE id ='"+num+"'");
                        truck.contains.executeUpdate("UPDATE art2ctg SET category_id='"+idCtg+"' WHERE article_id = '"+num+"'");
                        
                        if(tagsOut.containsValue(1)&&!tagsIn.contains(1)){
                            truck.contains.executeUpdate("INSERT INTO art2tg VALUES('"+num+"','1')");
                        }else if(tagsIn.contains(1)&&!tagsOut.containsValue(1)){
                            truck.contains.executeUpdate("DELETE FROM art2tg WHERE article_id = '"+num+"' AND tag_id = '1'");
                        }else if(tagsOut.containsValue(1)&&tagsIn.contains(1)){
                            //DO NOT ANYTHING
                        }
                        
                        if(tagsOut.containsValue(2)&&!tagsIn.contains(2)){
                            truck.contains.executeUpdate("INSERT INTO art2tg VALUES('"+num+"','2')");
                        }else if(tagsIn.contains(2)&&!tagsOut.containsValue(2)){
                            truck.contains.executeUpdate("DELETE FROM art2tg WHERE article_id = '"+num+"' AND tag_id = '2'");
                        }else if(tagsOut.containsValue(2)&&tagsIn.contains(2)){
                            //DO NOT ANYTHING
                        }
                        
                        if(tagsOut.containsValue(3)&&!tagsIn.contains(3)){
                            truck.contains.executeUpdate("INSERT INTO art2tg VALUES('"+num+"','3')");
                        }else if(tagsIn.contains(3)&&!tagsOut.containsValue(3)){
                            truck.contains.executeUpdate("DELETE FROM art2tg WHERE article_id = '"+num+"' AND tag_id = '3'");
                        }else if(tagsOut.containsValue(3)&&tagsIn.contains(3)){
                            //DO NOT ANYTHING
                        }
                        
                        JOptionPane.showMessageDialog(null, "Article has been edited !", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        ltArt.setSelectedValue(null,false);
                        btEdit.setEnabled(false);
                        clear();
                        showLart();
                    }else{
                        ltArt.setSelectedValue(null,false);
                        btEdit.setEnabled(false);
                        clear();
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Article not edited !","WARNING",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        });
    }
    
    public pnEdit() {
        truck.routing();
        initComponents();
        txMail.setEnabled(false);
        btEdit.setEnabled(false);
        showLart();
        showCnm();
        showCtg();
        fSltname();
        fSltcgt();
        fLart();
        fCekBox();
        fUpdate();
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
        btEdit = new javax.swing.JButton();
        cbx1 = new javax.swing.JCheckBox();
        cbx2 = new javax.swing.JCheckBox();
        cbx3 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ltArt = new javax.swing.JList<>();

        setLayout(new java.awt.CardLayout());

        page.setBackground(new java.awt.Color(255, 255, 255));
        page.setLayout(new java.awt.CardLayout());

        content.setBackground(new java.awt.Color(255, 153, 51));

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

        btEdit.setText("UPDATE");

        cbx1.setText("Laravel");

        cbx2.setText("PHP");

        cbx3.setText("CodeIgniter");

        jScrollPane1.setViewportView(ltArt);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbNm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(cbNm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbMail)
                        .addGap(26, 26, 26)
                        .addComponent(txMail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbTtl)
                        .addGap(33, 33, 33)
                        .addComponent(txTtl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbCtnt)
                        .addGap(13, 13, 13)
                        .addComponent(txCtnt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbCtg)
                        .addGap(7, 7, 7)
                        .addComponent(cbCtg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lbTgs)
                        .addGap(31, 31, 31)
                        .addComponent(cbx1)
                        .addGap(4, 4, 4)
                        .addComponent(cbx2)
                        .addGap(5, 5, 5)
                        .addComponent(cbx3))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(contentLayout.createSequentialGroup()
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
                        .addComponent(btEdit)))
                .addGap(11, 11, 11))
        );

        page.add(content, "card2");

        add(page, "card16");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEdit;
    private javax.swing.JComboBox<String> cbCtg;
    private javax.swing.JComboBox<String> cbNm;
    private javax.swing.JCheckBox cbx1;
    private javax.swing.JCheckBox cbx2;
    private javax.swing.JCheckBox cbx3;
    private javax.swing.JPanel content;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCtg;
    private javax.swing.JLabel lbCtnt;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbNm;
    private javax.swing.JLabel lbTgs;
    private javax.swing.JLabel lbTtl;
    private javax.swing.JList<String> ltArt;
    private javax.swing.JPanel page;
    private javax.swing.JTextField txCtnt;
    private javax.swing.JTextField txMail;
    private javax.swing.JTextField txTtl;
    // End of variables declaration//GEN-END:variables
}
