/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import dto.BookDto;
import controller.ManagementBook;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class BookManagement extends javax.swing.JFrame {
    private ManagementBook managementBook;
    private List<BookDto> bookList;
    
    public BookManagement() {
        initComponents();
        managementBook = new ManagementBook();
        showAllBookList();
        jTable1.setRowSelectionAllowed(true);
        jTable1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    editBookData();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        searchBotton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        button1.setLabel("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel1.setText("도서 관리");

        searchBotton.setText("검색");
        searchBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBottonActionPerformed(evt);
            }
        });

        jButton2.setText("추가");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("뒤로가기");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "title", "genre", "author", "status", "count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "title", "genre", "author", "status", "count" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBotton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1))
                .addGap(39, 39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(208, 208, 208))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBotton)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showAllBookList() {
        try {
            bookList = managementBook.getAllBookList();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setNumRows(0);
            for(int i = 0 ; i < bookList.size() ; i++) {
                BookDto data = bookList.get(i);
                Object[] row = {data.getTitle(), data.getGenre(), data.getAuthor(), data.getStatus(), data.getCount().toString()};
                model.addRow(row);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot load book list.");
        }
    }
    
    private void editBookData() {
       int i = jTable1.getSelectedRow();
       new EditBook(bookList.get(i)).setVisible(true);
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Management M = new Management();
        M.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new AddBook().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBottonActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setNumRows(0);
        try{
           bookList = managementBook.searchBook(jComboBox1.getSelectedItem().toString(), jTextField1.getText());
           for(int i = 0 ; i < bookList.size() ; i++) {
                BookDto data = bookList.get(i);
                Object[] row = {data.getTitle(), data.getGenre(), data.getAuthor(), data.getStatus(), data.getCount().toString()};
                model.addRow(row);
            }
        } catch (SQLException e) {
            Object[] row = {"", "", "", "", ""};
            model.addRow(row);
        }
       
       jTable1.setModel(model);
    }//GEN-LAST:event_searchBottonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton searchBotton;
    // End of variables declaration//GEN-END:variables
}