/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class pnList extends javax.swing.JPanel {

    private DefaultListModel dlm;
    private HashMap tags = new HashMap<>();
    private ResultSet bagData;
    private conDB truck = new conDB();
    
    private void showLart(){
        dlm = new DefaultListModel();
        ltArt.setModel(dlm);
        try{
            bagData=truck.contains.executeQuery("SELECT title FROM article");
            while(bagData.next()){
                Object reg = bagData.getString(1);
                dlm.addElement(reg);
            }
        }catch(Exception e){
            e.getMessage();
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
    
    public pnList() {
        initComponents();
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

        jScrollPane1.setViewportView(ltArt);

        cbCtg.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbx1.setText("Laravel");
        cbx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx1ActionPerformed(evt);
            }
        });

        cbx2.setText("PHP");

        cbx3.setText("CodeIgniter");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCtg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(cbx1)
                        .addGap(4, 4, 4)
                        .addComponent(cbx2)
                        .addGap(5, 5, 5)
                        .addComponent(cbx3)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(contentLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(cbCtg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx1)
                    .addComponent(cbx2)
                    .addComponent(cbx3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        page.add(content, "card2");

        add(page, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void cbx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx1ActionPerformed


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
