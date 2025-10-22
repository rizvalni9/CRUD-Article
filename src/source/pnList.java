/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package source;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class pnList extends javax.swing.JPanel {

    private int idCtg;
    private DefaultListModel dlm;
    private ResultSet bagData;
    private conDB truck = new conDB();
    
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
    
    private void showArtCtg(){
        try{
            dlm = new DefaultListModel();
            ltArt.setModel(dlm);
            bagData=truck.contains.executeQuery("SELECT * FROM article INNER JOIN art2ctg ON article.id = art2ctg.article_id WHERE "
                                              + "art2ctg.category_id='"+idCtg+"'");
            while(bagData.next()){
                Object reg = bagData.getString("title");
                dlm.addElement(reg);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Data not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void showArtTg(int id){
        try{
            dlm = new DefaultListModel();
            ltArt.setModel(dlm);
            bagData=truck.contains.executeQuery("SELECT * FROM article INNER JOIN art2ctg ON article.id = art2ctg.article_id "
                                              + "INNER JOIN art2tg ON article.id = art2tg.article_id WHERE art2tg.tag_id='"+id+"' "
                                              + "AND art2ctg.category_id='"+idCtg+"'");
            while(bagData.next()){
                Object reg = bagData.getString("title");
                dlm.addElement(reg);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Data not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void logicOperate(){
        if(cbx1.isSelected()==true&&cbx2.isSelected()==false&&cbx3.isSelected()==false){
            showArtTg(1);
        }else if(cbx1.isSelected()==true&&cbx2.isSelected()==true&&cbx3.isSelected()==false){
            showArtTg(1);
            showArtTg(2);
        }else if(cbx1.isSelected()==false&&cbx2.isSelected()==true&&cbx3.isSelected()==false){
            showArtTg(2);
        }else if(cbx1.isSelected()==false&&cbx2.isSelected()==true&&cbx3.isSelected()==true){
            showArtTg(2);
            showArtTg(3);
        }else if(cbx1.isSelected()==false&&cbx2.isSelected()==false&&cbx3.isSelected()==true){
            showArtTg(3);
        }else if(cbx1.isSelected()==true&&cbx2.isSelected()==true&&cbx3.isSelected()==true){
            showArtTg(1);
            showArtTg(2);
            showArtTg(3);
        }else if(cbx1.isSelected()==false&&cbx2.isSelected()==false&&cbx3.isSelected()==false){
            showArtCtg();
        }
    }
    
    private void fSltctg(){
        cbCtg.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent hearFunction){
                if(cbCtg.getSelectedIndex()==0){
                    dlm.removeAllElements();
                }else{
                    try{
                        bagData=truck.contains.executeQuery("SELECT * FROM category WHERE name = '"+cbCtg.getSelectedItem()+"'");
                        if(bagData.next()){
                            idCtg = bagData.getInt(1);
                            showArtCtg();
                            cbx1.setSelected(false);
                            cbx2.setSelected(false);
                            cbx3.setSelected(false);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Data not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
    
    private void fCekBox(){
        cbx1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx1.isSelected()){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
        cbx2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx2.isSelected()){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
        cbx3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx3.isSelected()){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
    }
    
    public pnList() {
        truck.routing();
        initComponents();
        fCekBox();
        showCtg();
        fSltctg();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        page = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ltArt = new javax.swing.JList<>();
        cbCtg = new javax.swing.JComboBox<>();
        cbx1 = new javax.swing.JCheckBox();
        cbx2 = new javax.swing.JCheckBox();
        cbx3 = new javax.swing.JCheckBox();

        setLayout(new java.awt.CardLayout());

        page.setLayout(new java.awt.CardLayout());

        ltArt.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(ltArt);

        cbCtg.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbx1.setText("Laravel");

        cbx2.setText("PHP");

        cbx3.setText("CodeIgniter");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCtg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(cbx1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(57, 57, 57)
                        .addComponent(cbx2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52)
                        .addComponent(cbx3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(cbCtg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx2)
                    .addComponent(cbx1)
                    .addComponent(cbx3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
        );

        page.add(content, "card2");

        add(page, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCtg;
    private javax.swing.JCheckBox cbx1;
    private javax.swing.JCheckBox cbx2;
    private javax.swing.JCheckBox cbx3;
    private javax.swing.JPanel content;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> ltArt;
    private javax.swing.JPanel page;
    // End of variables declaration//GEN-END:variables
}
