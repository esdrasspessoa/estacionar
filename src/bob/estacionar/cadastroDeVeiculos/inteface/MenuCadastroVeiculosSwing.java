package bob.estacionar.cadastroDeVeiculos.inteface;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCadastroVeiculosSwing extends JFrame {
    private final SistemaCadastroVeiculos sistemaCadastro;
    private JButton cadastrarButton;
    private JButton removerButton;
    private JButton exibirButton;
    private JTextArea veiculosTextArea;

    public MenuCadastroVeiculosSwing(SistemaCadastroVeiculos sistemaCadastro) {
        this.sistemaCadastro = sistemaCadastro;

        setTitle("Cadastro de Veiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        criarComponentes();
        adicionarComponentes();
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
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(cadastrarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(exibirButton);

        JScrollPane scrollPane = new JScrollPane(veiculosTextArea);

        add(painelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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

        if (placa != null){
            int ano = Utils.obterAno();
            TipoVeiculo tipoVeiculo = Utils.obterTipoVeiculo();
            Veiculo veiculo = Utils.criarVeiculo(placa, ano, tipoVeiculo);
            sistemaCadastro.cadastrarVeiculo(veiculo);
            JOptionPane.showMessageDialog(null,"Veiculo cadastro com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada. Nenhum veículo cadastrado.");
        }
    }

    private void removerVeiculo() {
        JOptionPane.showMessageDialog(null,"====== REMOVER VEÍCULO ======");

        String placa = JOptionPane.showInputDialog("Informe a placa do veículo a ser removido: ");
        int qtdVeiculosAntesRemocao = sistemaCadastro.getQtdVeiculos();

        sistemaCadastro.removerVeiculo(placa);

        if (sistemaCadastro.getQtdVeiculos() < qtdVeiculosAntesRemocao){
            JOptionPane.showMessageDialog(null, "Veiculo " + placa + " Removido com sucesso!");
        }else {
            JOptionPane.showMessageDialog(null, "Veiculo não encontrado");
        }
    }

    private void exibirVeiculos() {
        StringBuilder veiculosCadastrados = new StringBuilder("====== VEÍCULOS CADASTRADOS ======");

        if (sistemaCadastro.getQtdVeiculos() == 0) {
            veiculosCadastrados.append("Não há veículos cadastrados.");
        } else {
            for (int i = 0; i < sistemaCadastro.getQtdVeiculos(); i++) {
                Veiculo veiculo = sistemaCadastro.getVeiculos()[i];
                veiculosCadastrados.append(veiculo.exibirDados());
            }
        }

        JOptionPane.showMessageDialog(null, veiculosCadastrados.toString());
    }

}
