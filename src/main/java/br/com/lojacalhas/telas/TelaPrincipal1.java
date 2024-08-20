package br.com.lojacalhas.telas;

import br.com.lojacalhas.dal.ModuloConexao;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;



public class TelaPrincipal1 extends javax.swing.JFrame {

    Connection conn = null;

    public TelaPrincipal1() {
        initComponents();
        conn = ModuloConexao.getConexao();
        // o código abaixo serve para mostrar a data quando o form inicializar.
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.LONG);
        lblData.setText(formatador.format(data));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        lblImage = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        menuCad = new javax.swing.JMenu();
        menuCadCli = new javax.swing.JMenuItem();
        menuCadOs = new javax.swing.JMenuItem();
        menuCadUsu = new javax.swing.JMenuItem();
        menuOpc = new javax.swing.JMenu();
        menuOpcSair = new javax.swing.JMenuItem();
        menuRel = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        menuRelServ = new javax.swing.JMenuItem();
        menuAju = new javax.swing.JMenu();
        menuAjuSob = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calhas Rio Grande da Serra");
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);

        desktop.setPreferredSize(new java.awt.Dimension(770, 500));
        desktop.setRequestFocusEnabled(false);

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imageazulA.png"))); // NOI18N
        lblImage.setText("lblImage");
        lblImage.setMaximumSize(new java.awt.Dimension(770, 500));
        lblImage.setMinimumSize(new java.awt.Dimension(770, 500));
        lblImage.setPreferredSize(new java.awt.Dimension(770, 500));

        desktop.setLayer(lblImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsuario.setText("Usuário");

        lblData.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblData.setText("Data");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calha.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        menuCad.setText("Cadastro");
        menuCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadActionPerformed(evt);
            }
        });

        menuCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuCadCli.setText("Cliente");
        menuCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadCliActionPerformed(evt);
            }
        });
        menuCad.add(menuCadCli);

        menuCadOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuCadOs.setText("OS");
        menuCadOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadOsActionPerformed(evt);
            }
        });
        menuCad.add(menuCadOs);

        menuCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuCadUsu.setText("Usuários");
        menuCadUsu.setEnabled(false);
        menuCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadUsuActionPerformed(evt);
            }
        });
        menuCad.add(menuCadUsu);

        menu.add(menuCad);

        menuOpc.setText("Opcões");

        menuOpcSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuOpcSair.setText("Sair");
        menuOpcSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcSairActionPerformed(evt);
            }
        });
        menuOpc.add(menuOpcSair);

        menu.add(menuOpc);

        menuRel.setText("Relatório");
        menuRel.setEnabled(false);

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        menuRel.add(menRelCli);

        menuRelServ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuRelServ.setText("Serviços");
        menuRelServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelServActionPerformed(evt);
            }
        });
        menuRel.add(menuRelServ);

        menu.add(menuRel);

        menuAju.setText("Ajuda");

        menuAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuAjuSob.setText("Sobre");
        menuAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjuSobActionPerformed(evt);
            }
        });
        menuAju.add(menuAjuSob);

        menu.add(menuAju);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuario))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblUsuario)
                .addGap(39, 39, 39)
                .addComponent(lblData)
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1032, 537));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuOpcSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcSairActionPerformed
        //abaixo, uma caixa de diálogo confirmando se o usuário tem certeza que quer sair do sistema.
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menuOpcSairActionPerformed

    private void menuAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjuSobActionPerformed
        // Criamos um objeto do form que criamos TelaSobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);


    }//GEN-LAST:event_menuAjuSobActionPerformed

    private void menuCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadUsuActionPerformed
        //Chamando a TelaUsuario:
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
        lblImage.setVisible(false);


    }//GEN-LAST:event_menuCadUsuActionPerformed

    private void menuCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuCadActionPerformed

    private void menuCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadCliActionPerformed
        TelaCliente cliente = new TelaCliente();
        cliente.setVisible(true);
        desktop.add(cliente);
        lblImage.setVisible(false);

    }//GEN-LAST:event_menuCadCliActionPerformed

    private void menuCadOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadOsActionPerformed
        TelaOs os = new TelaOs();
        
        os.setVisible(true);
        desktop.add(os);
        lblImage.setVisible(false);
    }//GEN-LAST:event_menuCadOsActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
    // Relatório de clientes usando Jasper Reports (framework de bibliotecas para gerar relatórios.)
    try {
        // Caminho do relatório Jasper
        String caminhoRel = "src/main/java/br/com/lojacalhas/jasper/RelatorioCliente.jasper";
        
        // Preencher o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRel, null, conn);

        // Defina o caminho para o arquivo PDF
        String caminhoPdf = "relatorio_cliente.pdf"; // Ou o caminho desejado
        
        // Exportar o relatório para PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoPdf);
        
        // Abrir o PDF com o visualizador padrão do sistema
        if (Desktop.isDesktopSupported()) {
            Desktop telapdf = Desktop.getDesktop();
            if (telapdf.isSupported(Desktop.Action.OPEN)) {
                File pdfFile = new File(caminhoPdf);
                if (pdfFile.exists()) {
                    telapdf.open(pdfFile);
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo PDF não encontrado: " + caminhoPdf);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Abrir PDF não é suportado neste sistema.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desktop não é suportado neste sistema.");
        }
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo PDF: " + e.getMessage());
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
    }


    }//GEN-LAST:event_menRelCliActionPerformed

    private void menuRelServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelServActionPerformed
    // Relatório de clientes usando Jasper Reports (framework de bibliotecas para gerar relatórios.)
    try {
        // Caminho do relatório Jasper
        String caminhoRel = "src/main/java/br/com/lojacalhas/jasper/RelatorioServicos.jasper";
        
        // Preencher o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRel, null, conn);

        // Defina o caminho para o arquivo PDF
        String caminhoPdf = "relatorio_servicos.pdf";
        
        // Exportar o relatório para PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoPdf);
        
        // Abrir o PDF com o visualizador padrão do sistema
        if (Desktop.isDesktopSupported()) {
            Desktop telapdf = Desktop.getDesktop();
            if (telapdf.isSupported(Desktop.Action.OPEN)) {
                File pdfFile = new File(caminhoPdf);
                if (pdfFile.exists()) {
                    telapdf.open(pdfFile);
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo PDF não encontrado: " + caminhoPdf);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Abrir PDF não é suportado neste sistema.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desktop não é suportado neste sistema.");
        }
        
    } catch (JRException e) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo PDF: " + e.getMessage());
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
    }


    }//GEN-LAST:event_menuRelServActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal1().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblImage;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem menRelCli;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuAju;
    private javax.swing.JMenuItem menuAjuSob;
    private javax.swing.JMenu menuCad;
    private javax.swing.JMenuItem menuCadCli;
    private javax.swing.JMenuItem menuCadOs;
    public static javax.swing.JMenuItem menuCadUsu;
    private javax.swing.JMenu menuOpc;
    private javax.swing.JMenuItem menuOpcSair;
    public static javax.swing.JMenu menuRel;
    private javax.swing.JMenuItem menuRelServ;
    // End of variables declaration//GEN-END:variables
}
