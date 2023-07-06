package bob.estacionar.inteface;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;
import bob.estacionar.servicos.SistemaCadastroVeiculos;
import bob.estacionar.exception.CancelarEntradaException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuCadastroVeiculosSwing extends JFrame {
    private final SistemaCadastroVeiculos sistemaCadastro;
    private JButton cadastrarButton;
    private JButton removerButton;
    private JButton exibirButton;
    private JPanel painelBotoes;
    private JTable tabelaVagas;
    private List<JLabel> vagaLabels;
    private JPanel painelTitulo;
    private JLabel labelTitulo;

    public MenuCadastroVeiculosSwing(SistemaCadastroVeiculos sistemaCadastro) {
        this.sistemaCadastro = sistemaCadastro;
        setTitle("Cadastro de Veiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        criarComponentes();
        adicionarComponentes();
        inicializarVagaLabels();
        atualizarStatusVagasLabels();
        anexarOuvintes();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void criarComponentes() {
        labelTitulo = new JLabel("Bem-vindo ao Estacionar!");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrarButton = new JButton("Cadastrar Veículo");
        removerButton = new JButton("Remover Veículo");
        exibirButton = new JButton("Exibir Veículos");
    }

    private void adicionarComponentes() {
        painelBotoes = new JPanel();
        painelBotoes.add(cadastrarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(exibirButton);

        painelTitulo = new JPanel();
        painelTitulo.add(labelTitulo);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        //Criar as colunas
        String[] colunas = {"VAGA", "STATUS DA VAGA"};

        //Cria dados da tabela
        Object[][] dados = new Object[SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS][2];
        for (int i = 0; i < SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS; i++) {
            dados[i][0] = "Vaga " + (i + 1);
            dados[i][1] = "Disponivel";
        }

        //Cria o modelo de tabela com os dados e colunas
        DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas);

        //Cria a tabela com o modelo
        tabelaVagas = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaVagas);
        tabelaVagas.setPreferredScrollableViewportSize(tabelaVagas.getPreferredSize());
        painelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

   private void inicializarVagaLabels(){
        vagaLabels = new ArrayList<>();

        for (int i = 0; i < SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS; i++) {
            JLabel label = new JLabel("Vaga " + (i + 1) + ": Disponivel");
            vagaLabels.add(label);
        }
    }

    public void atualizarStatusVagasLabels() {
        boolean[] vagasDisponiveis = sistemaCadastro.getVagasDisponiveis();
        DefaultTableModel modeloTabela = (DefaultTableModel) tabelaVagas.getModel();

        for (int i = 0; i < vagasDisponiveis.length; i++) {
            boolean vagaDisponivel = vagasDisponiveis[i];
            String vagaStatus = vagaDisponivel ? "Disponível" : "Ocupada";
            modeloTabela.setValueAt(vagaStatus, i, 1);
        }
    }

    private void anexarOuvintes() {
        cadastrarButton.addActionListener(e -> cadastrarVeiculo());

        removerButton.addActionListener(e -> removerVeiculo());

        exibirButton.addActionListener(e -> exibirVeiculos());
    }

    private void cadastrarVeiculo() {
        JOptionPane.showMessageDialog(null, "====== CADASTRO DE VEICULO ======");
        String placa = Utils.obterPlaca();

        if (placa == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada. Nenhum veículo cadastrado.");
            return;
        }

        try{
            int ano = Utils.obterAno();

            if (ano == 0){
                throw new CancelarEntradaException();
            }

            TipoVeiculo tipoVeiculo = Utils.obterTipoVeiculo();
            Veiculo veiculo = Utils.criarVeiculo(placa, ano, tipoVeiculo);
            sistemaCadastro.cadastrarVeiculo(veiculo);
            atualizarStatusVagasLabels();
            JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
            atualizarStatusVagasLabels();

        } catch (CancelarEntradaException e){
            e.printStackTrace();
        }
    }

    private void removerVeiculo() {
        JOptionPane.showMessageDialog(null, "====== REMOVER VEÍCULO ======");

        String placa = JOptionPane.showInputDialog("Informe a placa do veículo a ser removido: ");
        int qtdVeiculosAntesRemocao = sistemaCadastro.getQtdVeiculos();

        sistemaCadastro.removerVeiculo(placa);
        atualizarStatusVagasLabels();

        if (sistemaCadastro.getQtdVeiculos() < qtdVeiculosAntesRemocao) {
            JOptionPane.showMessageDialog(null, "Veiculo " + placa + " Removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Veiculo não encontrado");
        }
    }

    private void exibirVeiculos() {
        StringBuilder veiculosCadastrados = new StringBuilder("====== VEÍCULOS CADASTRADOS ======");

        if (sistemaCadastro.getQtdVeiculos() == 0) {
            veiculosCadastrados.append("\n" +
                    "Não há veículos cadastrados.");
        } else {

            sistemaCadastro.atualizarStatusVagas();

            for (int i = 0; i < sistemaCadastro.getVeiculos().length; i++) {
                Veiculo veiculo = sistemaCadastro.getVeiculos()[i];

                veiculosCadastrados.append("\n\nVaga: ").append(i + 1);
                veiculosCadastrados.append("\n").append(veiculo != null ? veiculo.exibirDados() : "Vaga vazia");
            }
        }

        JOptionPane.showMessageDialog(null, veiculosCadastrados.toString());
    }

}
