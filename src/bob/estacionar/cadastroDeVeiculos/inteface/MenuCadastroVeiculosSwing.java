package bob.estacionar.cadastroDeVeiculos.inteface;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuCadastroVeiculosSwing extends JFrame {
    private final SistemaCadastroVeiculos sistemaCadastro;
    private JButton cadastrarButton;
    private JButton removerButton;
    private JButton exibirButton;
    private JTextArea veiculosTextArea;
    private JPanel painelBotoes;
    private List<JLabel> vagaLabels;

    public MenuCadastroVeiculosSwing(SistemaCadastroVeiculos sistemaCadastro) {
        this.sistemaCadastro = sistemaCadastro;
        setTitle("Cadastro de Veiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        criarComponentes();
        adicionarComponentes();
        atualizarStatusVagasLabels();
        anexarOuvintes();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void criarComponentes() {
        cadastrarButton = new JButton("Cadastrar Veículo");
        removerButton = new JButton("Remover Veículo");
        exibirButton = new JButton("Exibir Veículos");
        veiculosTextArea = new JTextArea(10, 30);
        veiculosTextArea.setEditable(false);
    }

    private void adicionarComponentes() {
        painelBotoes = new JPanel();
        painelBotoes.add(cadastrarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(exibirButton);

        JPanel painelVagas = new JPanel();
        painelVagas.setLayout(new GridLayout(SistemaCadastroVeiculos.LIMITE_MAXIMO_VEICULOS, 1));

        inicializarVagaLabels();

        for (JLabel label:vagaLabels) {
            painelVagas.add(label);
        }

        atualizarStatusVagasLabels();

        JScrollPane scrollPane = new JScrollPane(veiculosTextArea);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(painelBotoes, BorderLayout.NORTH);
        painelPrincipal.add(painelVagas, BorderLayout.CENTER);
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

        for (int i = 0; i < vagasDisponiveis.length; i++) {
            JLabel label = vagaLabels.get(i);
            boolean vagaDisponivel = vagasDisponiveis[i];
            String vagaStatus = vagaDisponivel ? "Disponível" : "Ocupada";
            label.setText("Vaga " + (i + 1) + ": " + vagaStatus);
        }
    }

    private void anexarOuvintes() {
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVeiculo();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerVeiculo();
            }
        });

        exibirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirVeiculos();
            }
        });
    }

    public void executarMenu() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuCadastroVeiculosSwing(sistemaCadastro);
            }
        });
    }

    private void cadastrarVeiculo() {
        JOptionPane.showMessageDialog(null, "====== CADASTRO DE VEICULO ======");
        String placa = Utils.obterPlaca();

        if (placa != null) {
            int ano = Utils.obterAno();
            TipoVeiculo tipoVeiculo = Utils.obterTipoVeiculo();
            Veiculo veiculo = Utils.criarVeiculo(placa, ano, tipoVeiculo);
            sistemaCadastro.cadastrarVeiculo(veiculo);
            atualizarStatusVagasLabels();
            JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
            atualizarStatusVagasLabels();
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada. Nenhum veículo cadastrado.");
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
            veiculosCadastrados.append("Não há veículos cadastrados.");
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
