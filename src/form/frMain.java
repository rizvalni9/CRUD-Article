/**
 * @author KELUARGA ZALONI
 * @see https://github.com/rizvalni9/
 **/
package form;

import source.pnIpt;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import source.*;

public class frMain extends javax.swing.JFrame {
    
    private void functionExt(){
        lbExt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent hearFunction){
                System.exit(0);
            }
        });
    }
    
    private void functionNext(){
        lbNxt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent hearFunction){
                if(lbPage.getText().equals("Halaman ke-1")){
                    pnMain.removeAll();
                    pnMain.add(new pnEdit());
                    lbNavpn.setText("EDIT FORM");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-2");
                }else if(lbPage.getText().equals("Halaman ke-2")){
                    pnMain.removeAll();
                    pnMain.add(new pnList());
                    lbNavpn.setText("LIST");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-3");
                }else if(lbPage.getText().equals("Halaman ke-3")){
                    pnMain.removeAll();
                    pnMain.add(new pnDetail());
                    lbNavpn.setText("ARTICLE TABLE");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-4");
                }
            }
            @Override
            public void mouseEntered(MouseEvent hearFunction){
                lbNxt.setFont(new Font("Segoe UI",Font.BOLD,14));
            }
            @Override
            public void mouseExited(MouseEvent hearFunction){
                lbNxt.setFont(new Font("Segoe UI",Font.PLAIN,14));
            }
        });
        
    }
    
    private void functionPrev(){
        lbPrev.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent hearFunction){
                if(lbPage.getText().equals("Halaman ke-4")){
                    pnMain.removeAll();
                    pnMain.add(new pnList());
                    lbNavpn.setText("LIST ");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-3");
                }else if(lbPage.getText().equals("Halaman ke-3")){
                    pnMain.removeAll();
                    pnMain.add(new pnEdit());
                    lbNavpn.setText("EDIT FORM");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-2");
                }else if(lbPage.getText().equals("Halaman ke-2")){
                    pnMain.removeAll();
                    pnMain.add(new pnIpt());
                    lbNavpn.setText("INPUT FORM");
                    pnMain.repaint();
                    pnMain.revalidate();
                    lbPage.setText("Halaman ke-1");
                }
            }
            @Override
            public void mouseEntered(MouseEvent hearFunction){
                lbPrev.setFont(new Font("Segoe UI",Font.BOLD,14));
            }
            @Override
            public void mouseExited(MouseEvent hearFunction){
                lbPrev.setFont(new Font("Segoe UI",Font.PLAIN,14));
            }
        });
        
    }
    
    private void started(){
        pnMain.removeAll();
        pnMain.add(new pnIpt());
        pnMain.repaint();
        pnMain.revalidate();
    }
    
    public frMain() {
        initComponents();
        functionExt();
        functionNext();
        functionPrev();
        started();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHead = new javax.swing.JPanel();
        lbExt = new javax.swing.JLabel();
        lbCreator = new javax.swing.JLabel();
        lbUserRepo = new javax.swing.JLabel();
        lbNavpn = new javax.swing.JLabel();
        pnFoot = new javax.swing.JPanel();
        lbPrev = new javax.swing.JLabel();
        lbNxt = new javax.swing.JLabel();
        lbPage = new javax.swing.JLabel();
        pnContent = new javax.swing.JPanel();
        slContent = new javax.swing.JScrollPane();
        pnMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnHead.setBackground(new java.awt.Color(238, 193, 193));
        pnHead.setPreferredSize(new java.awt.Dimension(600, 40));
        pnHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbExt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        pnHead.add(lbExt, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 5, -1, -1));

        lbCreator.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbCreator.setText("RIZQON NAUVAL ZALONI ");
        pnHead.add(lbCreator, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 3, -1, -1));

        lbUserRepo.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        lbUserRepo.setText("GitHub : rizvalni9");
        pnHead.add(lbUserRepo, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 22, 90, -1));

        lbNavpn.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lbNavpn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNavpn.setText("INPUT FORM");
        pnHead.add(lbNavpn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 250, 40));

        getContentPane().add(pnHead, java.awt.BorderLayout.PAGE_START);

        pnFoot.setBackground(new java.awt.Color(238, 193, 193));
        pnFoot.setPreferredSize(new java.awt.Dimension(600, 50));

        lbPrev.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPrev.setText("Prev");

        lbNxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNxt.setText("Next");

        lbPage.setText("Halaman ke-1");

        javax.swing.GroupLayout pnFootLayout = new javax.swing.GroupLayout(pnFoot);
        pnFoot.setLayout(pnFootLayout);
        pnFootLayout.setHorizontalGroup(
            pnFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208)
                .addComponent(lbPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addComponent(lbNxt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnFootLayout.setVerticalGroup(
            pnFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFootLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnFootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrev)
                    .addComponent(lbNxt)
                    .addComponent(lbPage))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(pnFoot, java.awt.BorderLayout.PAGE_END);

        slContent.setBorder(null);

        pnMain.setBackground(new java.awt.Color(255, 255, 255));
        pnMain.setLayout(new java.awt.CardLayout());
        slContent.setViewportView(pnMain);

        javax.swing.GroupLayout pnContentLayout = new javax.swing.GroupLayout(pnContent);
        pnContent.setLayout(pnContentLayout);
        pnContentLayout.setHorizontalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slContent, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnContentLayout.setVerticalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slContent, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        getContentPane().add(pnContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbCreator;
    private javax.swing.JLabel lbExt;
    private javax.swing.JLabel lbNavpn;
    private javax.swing.JLabel lbNxt;
    private javax.swing.JLabel lbPage;
    private javax.swing.JLabel lbPrev;
    private javax.swing.JLabel lbUserRepo;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnFoot;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnMain;
    private javax.swing.JScrollPane slContent;
    // End of variables declaration//GEN-END:variables
}
