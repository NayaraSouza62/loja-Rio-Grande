package br.com.lojacalhas.telas;

import java.sql.*;
import br.com.lojacalhas.dal.ModuloConexao;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class TelaOs extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaOs() {
        initComponents();
        conn = ModuloConexao.getConexao();
    }

    public void limparCampos() {
        // Este método serve para toda vez que for necessário fazer a limpeza nos componetes.
        txtOs.setText("");
        txtData.setText("");
        cboSituacao.setSelectedItem("");
        txtTipoMaterial.setText("");
        txtValor.setText("");
        txtVendedor.setText("");
        cboFormaPgto.setSelectedItem("");
        txtIdCli.setText("");
        // Limpa a tabela
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Nome", "Telefone", "Endereço", "Cpf"
                }
        ));
    }

    public void consultaClienteTabela() {
// Método para pesquisa de clientes. À medida que o usuário digita o nome, a tabela é atualizada automaticamente
        String sql = "select idcli as Id, nomecli as Nome, fone as Telefone, endcli as Endereço, cpf as Cpf from tbclientes where nomecli like ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();

            // Limpa a tabela antes de preencher com novos resultados
            tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Id", "Nome", "Telefone", "Endereço", "Cpf"
                    }
            ));
            // Preenche a tabela com os resultados da pesquisa
            while (rs.next()) {
                ((DefaultTableModel) tblClientes.getModel()).addRow(new Object[]{
                    rs.getInt("Id"),
                    rs.getString("Nome"),
                    rs.getString("Telefone"),
                    rs.getString("Endereço"),
                    rs.getString("Cpf")
                });

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setarCampos() {
        // Método essencial que preenche automaticamente os campos com os dados do cliente selecionado
        int setar = tblClientes.getSelectedRow(); // linha selecionada
        txtIdCli.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }

    private void emitirOs() {
        /**
 * Insere uma nova Ordem de Serviço (OS) na tabela `tbos`.
 * 
 * - Prepara a consulta SQL e define os valores dos campos da OS.
 * - Verifica se todos os campos obrigatórios estão preenchidos.
 * - Se os campos estiverem corretos, executa a inserção no banco de dados.
 * - Mostra uma mensagem de sucesso se a OS for inserida com sucesso.
 * - Habilita os botões de impressão, exclusão e alteração.
 * - Exibe mensagens de erro em caso de falha ou problemas de banco de dados.
 */
        String sql = "insert into tbos( situacao, material, valor, vendedor, idcli, formapto ) values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, cboSituacao.getSelectedItem().toString());
            pst.setString(2, txtTipoMaterial.getText());
            pst.setString(3, txtValor.getText().replace(",", "."));
            pst.setString(4, txtVendedor.getText());
            pst.setString(5, txtIdCli.getText());
            pst.setString(6, cboFormaPgto.getSelectedItem().toString());
            //validação dos campos obrigatórios:
            if (txtTipoMaterial.getText().isEmpty() || cboSituacao.getSelectedItem().equals("") || txtVendedor.getText().isEmpty()
                    || txtValor.getText().isEmpty() || cboFormaPgto.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios. * ");
            } else {
                int autenticacao = pst.executeUpdate();
                if (autenticacao > 0) {
                    JOptionPane.showMessageDialog(null, "OS emitida com sucesso!");
                    recuperarOs();
                    btnImprimir.setEnabled(true);
                    btnExcluirOs.setEnabled(true);
                    btnAlterar.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "erro");
                }

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void pesquisarOs() {
        /**
 * Pesquisa uma Ordem de Serviço (OS) pelo número fornecido e preenche os campos com as informações correspondentes.
 * 
 * - Habilita os botões de alteração, exclusão e impressão.
 * - Solicita ao usuário que informe o número da OS a ser pesquisada.
 * - Executa uma consulta SQL com INNER JOIN para obter detalhes da OS e informações do cliente associado.
 * - Se a OS for encontrada, preenche os campos de texto e a tabela com as informações obtidas.
 * - Desabilita os botões de adicionar e consultar após encontrar a OS.
 * - Exibe uma mensagem de erro se a OS não for encontrada.
 */
        btnAlterar.setEnabled(true);
        btnExcluirOs.setEnabled(true);
        btnImprimir.setEnabled(true);

        // Entrada de dados para o número da OS
        String numOs = JOptionPane.showInputDialog("Digite o número da OS");

        // Consulta com INNER JOIN
        String sql = "SELECT tbos.*, tbclientes.nomecli, tbclientes.fone, tbclientes.endcli, tbclientes.cpf "
                + "FROM tbos "
                + "INNER JOIN tbclientes ON tbos.idcli = tbclientes.idcli "
                + "WHERE tbos.os = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, numOs);
            rs = pst.executeQuery();

            txtData.setEnabled(true);
            txtOs.setEnabled(true);
            // Limpa a tabela antes de preencher com novos resultados
            tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Id", "Nome", "Telefone", "Endereço", "Cpf"
                    }
            ));

            // Verifica se há um registro correspondente
            if (rs.next()) {
                // Preenche os campos com os dados da tabela tbos
                txtOs.setText(rs.getString("os"));
                txtData.setText(rs.getString("data_os"));
                cboSituacao.setSelectedItem(rs.getString("situacao"));
                txtTipoMaterial.setText(rs.getString("material"));
                txtValor.setText(rs.getString("valor"));
                txtVendedor.setText(rs.getString("vendedor"));
                cboFormaPgto.setSelectedItem(rs.getString("formapto"));
                txtIdCli.setText(rs.getString("idcli"));

                // Preenche a tabela com os dados do cliente
                ((DefaultTableModel) tblClientes.getModel()).addRow(new Object[]{
                    rs.getString("idcli"),
                    rs.getString("nomecli"),
                    rs.getString("fone"),
                    rs.getString("endcli"),
                    rs.getString("cpf")
                });

                btnAdicionar.setEnabled(false);
                btnConsultar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "OS não encontrada.");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    private void atualizarOs() {
        /**
 * Atualiza uma Ordem de Serviço (OS) existente com os novos valores fornecidos.
 * 
 * - Prepara a consulta SQL para atualizar os dados da OS na tabela `tbos`.
 * - Define os valores dos campos a serem atualizados com base na entrada do usuário.
 * - Verifica se todos os campos obrigatórios estão preenchidos.
 * - Se os campos estiverem corretos, executa a atualização no banco de dados.
 * - Mostra uma mensagem de sucesso se a OS for atualizada com sucesso.
 * - Limpa os campos e reabilita os botões de adicionar e consultar.
 * - Exibe uma mensagem de erro em caso de falha na atualização.
 */
        String sql = "update tbos set situacao=? , material=?, valor=? , vendedor=?, formapto=? where os = ? ";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, cboSituacao.getSelectedItem().toString());
            pst.setString(2, txtTipoMaterial.getText());
            pst.setString(3, txtValor.getText().replace(",", "."));
            pst.setString(4, txtVendedor.getText());

            pst.setString(5, cboFormaPgto.getSelectedItem().toString());
            pst.setString(6, txtOs.getText());
            //validação dos campos obrigatórios:
            if (txtTipoMaterial.getText().isEmpty() || txtVendedor.getText().isEmpty() || cboSituacao.getSelectedItem().equals("  ")
                    || txtValor.getText().isEmpty() || cboFormaPgto.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios. * ");
            } else {
                int autenticacao = pst.executeUpdate();
                if (autenticacao > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso");

                    txtOs.setText(null);
                    txtData.setText(null);
                    //habilitar componentes
                    btnAdicionar.setEnabled(true);
                    btnConsultar.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "erro");
                }

            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void excluirOs() {
        /**
 * Exclui uma Ordem de Serviço (OS) do banco de dados.
 * 
 * - Solicita confirmação ao usuário antes de excluir a OS.
 * - Se o usuário confirmar, prepara a consulta SQL para deletar a OS da tabela `tbos`.
 * - Executa a exclusão e mostra uma mensagem de sucesso se a OS for excluída.
 * - Limpa os campos e reabilita os botões de adicionar e consultar.
 * - Exibe uma mensagem de erro em caso de falha na exclusão.
 */
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbos where os=? ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtOs.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
                    limparCampos();
                    txtOs.setText(null);
                    txtData.setText(null);
                    //habilitar componentes
                    btnAdicionar.setEnabled(true);
                    btnConsultar.setEnabled(true);
                    limparCampos();
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboSituacao = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTipoMaterial = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        cboFormaPgto = new javax.swing.JComboBox<>();
        btnConsultar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluirOs = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIdCli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnLimparCampos = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("OS");
        setPreferredSize(new java.awt.Dimension(640, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nº OS");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Data");

        txtOs.setEditable(false);
        txtOs.setPreferredSize(new java.awt.Dimension(13, 25));

        txtData.setEditable(false);
        txtData.setPreferredSize(new java.awt.Dimension(13, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtOs.getAccessibleContext().setAccessibleName("");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Situação *");

        cboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Serviço Realizado", "Entrega de material Ok", "Aguardando Aprovação", "Apenas Orçamento", " " }));
        cboSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSituacaoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Tipo de Material *");
        jLabel5.setPreferredSize(new java.awt.Dimension(122, 25));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Vendedor *");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Valor Total *");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Forma de pagamento *");
        jLabel8.setPreferredSize(new java.awt.Dimension(157, 25));

        txtTipoMaterial.setPreferredSize(new java.awt.Dimension(13, 25));
        txtTipoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoMaterialActionPerformed(evt);
            }
        });

        txtVendedor.setPreferredSize(new java.awt.Dimension(13, 25));

        txtValor.setText("0");
        txtValor.setOpaque(false);
        txtValor.setPreferredSize(new java.awt.Dimension(13, 25));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        cboFormaPgto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "À Vista", "Crédito", "A combinar" }));
        cboFormaPgto.setPreferredSize(new java.awt.Dimension(13, 25));
        cboFormaPgto.setRequestFocusEnabled(false);
        cboFormaPgto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFormaPgtoActionPerformed(evt);
            }
        });

        btnConsultar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pesquisarCli.png"))); // NOI18N
        btnConsultar.setText("Consultar OS");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnAdicionar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar.png"))); // NOI18N
        btnAdicionar.setText("Emitir OS");
        btnAdicionar.setActionCommand("");
        btnAdicionar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250)));
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(101, 37));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alterar.png"))); // NOI18N
        btnAlterar.setText("Atualizar OS");
        btnAlterar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250)));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluirOs.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluirOs.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExcluirOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excluir.png"))); // NOI18N
        btnExcluirOs.setText("Excluir OS");
        btnExcluirOs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250)));
        btnExcluirOs.setOpaque(false);
        btnExcluirOs.setPreferredSize(new java.awt.Dimension(101, 37));
        btnExcluirOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirOsActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 204), null, null));
        jPanel2.setToolTipText("CONSULTAR USUÁRIO");
        jPanel2.setName(""); // NOI18N

        jLabel10.setBackground(new java.awt.Color(135, 206, 250));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText(" Pesquisar Cliente");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setBackground(new java.awt.Color(135, 206, 250));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Nome");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        tblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        tblClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone", "Endereço", "Cpf"
            }
        ));
        tblClientes.setFocusable(false);
        tblClientes.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblClientes.setSelectionForeground(new java.awt.Color(204, 204, 255));
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pesquisarCliente.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("* Id");

        txtIdCli.setEditable(false);
        txtIdCli.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(32, 32, 32)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel4.setText("Preencha os campos abaixo para emitir uma OS : *");

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imprimir (1).png"))); // NOI18N
        btnImprimir.setText("Imprimir OS");
        btnImprimir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250)));
        btnImprimir.setOpaque(false);
        btnImprimir.setPreferredSize(new java.awt.Dimension(101, 37));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnLimparCampos.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparCampos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excluir.png"))); // NOI18N
        btnLimparCampos.setText("Limpar Campos");
        btnLimparCampos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250), new java.awt.Color(135, 206, 250)));
        btnLimparCampos.setOpaque(false);
        btnLimparCampos.setPreferredSize(new java.awt.Dimension(101, 37));
        btnLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluirOs, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimparCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(195, 195, 195)
                        .addComponent(btnConsultar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnConsultar)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(cboFormaPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(txtTipoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirOs, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        setBounds(0, 0, 769, 490);
    }// </editor-fold>//GEN-END:initComponents

    private void cboSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSituacaoActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void cboFormaPgtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFormaPgtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFormaPgtoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void recuperarOs() {
        String sql = "select max(os) from tbos";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOs.setText(rs.getString(1));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao imprimir OS: " + e.getMessage());

        }

    }


    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        pesquisarOs();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        emitirOs();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        atualizarOs();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirOsActionPerformed
        excluirOs();
    }//GEN-LAST:event_btnExcluirOsActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        consultaClienteTabela();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        //trecho abaixo serve para setar os campos ta tabela. Quando o usuário clicar, os campos serão preenchidos
        setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtTipoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoMaterialActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        try {
            // Verifique se o campo de texto não está vazio
            if (txtOs.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, insira o número da OS.");
                return;
            }

            // Classe para criar um filtro (neste caso, o número da OS)
            HashMap<String, Object> filtro = new HashMap<>();
            filtro.put("os", Integer.valueOf(txtOs.getText()));

            // Caminho do arquivo Jasper
            String caminhoRel = "src/main/java/br/com/lojacalhas/jasper/RelatorioOS.jasper";

            // Preencher o relatório com os parâmetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRel, filtro, conn);

            // Exportar o relatório para PDF
            String caminhoPDF = "relatorio_os.pdf"; // Defina o caminho desejado para salvar o PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoPDF);

            // Tente abrir o PDF gerado
            if (Desktop.isDesktopSupported()) {
                File pdfFile = new File(caminhoPDF);
                Desktop.getDesktop().open(pdfFile);
            }

        } catch (HeadlessException | IOException | NumberFormatException | JRException e) { // Para depuração
            // Para depuração
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
        }


    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExcluirOs;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimparCampos;
    private javax.swing.JComboBox<String> cboFormaPgto;
    private javax.swing.JComboBox<String> cboSituacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtTipoMaterial;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables

}
