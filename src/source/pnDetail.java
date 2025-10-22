/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package source;


import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class pnDetail extends javax.swing.JPanel {
    
    private conDB truck = new conDB();
    private ResultSet bagData,bagDataTg;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer Tengah = new DefaultTableCellRenderer();
    private String [] title ={"Title","Content","Author","Email","Category"};
    
    private void logicOperate(){
        if(cbx1.isSelected()==true&&cbx2.isSelected()==false&&cbx3.isSelected()==false){
            showArtTb(1);
        }else if(cbx2.isSelected()==true&&cbx1.isSelected()==false&&cbx3.isSelected()==false){
            showArtTb(2);
        }else if(cbx3.isSelected()==true&&cbx2.isSelected()==false&&cbx1.isSelected()==false){
            showArtTb(3);
        }else if(cbx1.isSelected()==true&&cbx2.isSelected()==true&&cbx3.isSelected()==false){
            showArtTb(1);
            showArtTb(2);
        }else if(cbx2.isSelected()==true&&cbx3.isSelected()==true&&cbx1.isSelected()==false){
            showArtTb(2);
            showArtTb(3);
        }else if(cbx3.isSelected()==true&&cbx1.isSelected()==true&&cbx2.isSelected()==false){
            showArtTb(1);
            showArtTb(3);
        }else if(cbx1.isSelected()==true&&cbx2.isSelected()==true&&cbx3.isSelected()==true){
            showAllArtTb();
        }else if(cbx1.isSelected()==false&&cbx2.isSelected()==false&&cbx3.isSelected()==false){
            showAllArtTb();
        }
    }
    
    private void showArtTb(int id){
        dtm = new DefaultTableModel(null,title);
        tbArt.setModel(dtm);
        
        try{
            bagData=truck.contains.executeQuery("SELECT article.id, article.title, article.content, category.name, author.name, "
                                              + "author.email FROM article INNER JOIN art2ctg ON article.id = art2ctg.article_id "
                                              + "INNER JOIN category ON category.id = art2ctg.category_id INNER JOIN author ON "
                                              + "article.author_id = author.id INNER JOIN art2tg ON article.id = art2tg.article_id "
                                              + "WHERE art2tg.tag_id='"+id+"'");
            while(bagData.next()){
                Object [] row = {bagData.getString("article.title"),bagData.getString("article.content"),bagData.getString("author.name"),
                                 bagData.getString("author.email"),bagData.getString("category.name")};
                dtm.addRow(row);
            }
            Tengah.setHorizontalAlignment(SwingConstants.CENTER);
            tbArt.getColumnModel().getColumn(2).setCellRenderer(Tengah);
            tbArt.getColumnModel().getColumn(4).setCellRenderer(Tengah);
            
            tbArt.getColumnModel().getColumn(0).setMaxWidth(150);
            tbArt.getColumnModel().getColumn(1).setMaxWidth(200);
            tbArt.getColumnModel().getColumn(2).setMaxWidth(130);
            tbArt.getColumnModel().getColumn(3).setMaxWidth(150);
            tbArt.getColumnModel().getColumn(4).setMaxWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"All data set not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    private void showAllArtTb(){
        dtm = new DefaultTableModel(null,title);
        tbArt.setModel(dtm);
        
        try{
            bagData=truck.contains.executeQuery("SELECT article.id, article.title, article.content, category.name, author.name, "
                                              + "author.email FROM article INNER JOIN art2ctg ON article.id = art2ctg.article_id "
                                              + "INNER JOIN category ON category.id = art2ctg.category_id INNER JOIN author ON "
                                              + "article.author_id = author.id");
            while(bagData.next()){
                Object [] row = {bagData.getString("article.title"),bagData.getString("article.content"),bagData.getString("author.name"),
                                 bagData.getString("author.email"),bagData.getString("category.name")};
                dtm.addRow(row);
            }
            Tengah.setHorizontalAlignment(SwingConstants.CENTER);
            tbArt.getColumnModel().getColumn(2).setCellRenderer(Tengah);
            tbArt.getColumnModel().getColumn(4).setCellRenderer(Tengah);
            
            tbArt.getColumnModel().getColumn(0).setMaxWidth(150);
            tbArt.getColumnModel().getColumn(1).setMaxWidth(200);
            tbArt.getColumnModel().getColumn(2).setMaxWidth(130);
            tbArt.getColumnModel().getColumn(3).setMaxWidth(150);
            tbArt.getColumnModel().getColumn(4).setMaxWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"All data set not showing !","WARNING",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    private void fCekBox(){
        cbx1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx1.isSelected()==true){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
        cbx2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx2.isSelected()==true){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
        cbx3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent hearFunction){
                if(cbx3.isSelected()==true){
                    logicOperate();
                }else{
                    logicOperate();
                }
            }
        });
    }
    
    public pnDetail() {
        truck.routing();
        initComponents();
        fCekBox();
        showAllArtTb();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        page = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tbArt = new javax.swing.JTable();
        cbx1 = new javax.swing.JCheckBox();
        cbx2 = new javax.swing.JCheckBox();
        cbx3 = new javax.swing.JCheckBox();

        setLayout(new java.awt.CardLayout());

        page.setLayout(new java.awt.CardLayout());

        jScrollPane.setBorder(null);

        tbArt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbArt.setEnabled(false);
        jScrollPane.setViewportView(tbArt);

        cbx1.setText("Laravel");

        cbx2.setText("PHP");

        cbx3.setText("CodeIgniter");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane)
            .addGroup(contentLayout.createSequentialGroup()
                .addComponent(cbx1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(167, 167, 167)
                .addComponent(cbx2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(175, 175, 175)
                .addComponent(cbx3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx1)
                    .addComponent(cbx2)
                    .addComponent(cbx3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
        );

        page.add(content, "card2");

        add(page, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbx1;
    private javax.swing.JCheckBox cbx2;
    private javax.swing.JCheckBox cbx3;
    private javax.swing.JPanel content;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel page;
    private javax.swing.JTable tbArt;
    // End of variables declaration//GEN-END:variables
}
