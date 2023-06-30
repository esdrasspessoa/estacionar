package bob.estacionar.cadastroDeVeiculos.inteface;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Caminhao;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Onibus;
import bob.estacionar.cadastroDeVeiculos.servicos.SistemaCadastroVeiculos;
import bob.estacionar.exception.VeiculoInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCadastroVeiculosSwing extends JFrame {
    private SistemaCadastroVeiculos sistemaCadastro;
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

        String placa = null;
        int ano = 0;

        boolean placaValida = false;
        while (!placaValida) {
            try {
                String placaStr = JOptionPane.showInputDialog("Informe a placa do veículo (formato: XXX-9999):");

                if (placaStr.matches("[A-Z]{3}-\\d{4}")) {
                    placa = placaStr;
                    placaValida = true;
                } else {
                    throw new VeiculoInvalidoException("Placa inválida. Formato esperado: XXX-9999");
                }

            } catch (VeiculoInvalidoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        boolean anoValido = false;
        while (!anoValido) {
            try {
                String anoStr = JOptionPane.showInputDialog("Informe o ano do veículo: (formato: AAAA)");

                if (anoStr.matches("\\d{4}")) {
                    ano = Integer.parseInt(anoStr);
                    anoValido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ano inválido. Digite um número de 4 digitos: (ex: 2023");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ano inválido. Digite um numero inteiro!");
            }
        }

        String[] opcoesTipo = new String[]{TipoVeiculo.ONIBUS.getDescricao(), TipoVeiculo.CAMINHAO.getDescricao()};
        JComboBox<String> comboBox = new JComboBox<>(opcoesTipo);
        JOptionPane.showMessageDialog(null,comboBox,"Selecionne o tipo de veiculo:", JOptionPane.QUESTION_MESSAGE);

        String tipoVeiculoSelecionado = (String) comboBox.getSelectedItem();
        TipoVeiculo tipoVeiculo = null;

        if(tipoVeiculoSelecionado.equals(TipoVeiculo.ONIBUS.getDescricao())){
            tipoVeiculo = TipoVeiculo.ONIBUS;
        } else if(tipoVeiculoSelecionado.equals(TipoVeiculo.CAMINHAO.getDescricao())) {
            tipoVeiculo = TipoVeiculo.CAMINHAO;
        }

        Veiculo veiculo;

        if (tipoVeiculo == TipoVeiculo.ONIBUS) {
            int qtdAssentos = 0;
            boolean qtdAssentosValida = false;

            while (!qtdAssentosValida) {
                try {
                    String qtdAssentosStr = JOptionPane.showInputDialog("Informe a quantidade de assentos: ");

                    qtdAssentos = Integer.parseInt(qtdAssentosStr);
                    qtdAssentosValida = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Quantidade de assentos inválidas, digite um numero inteiro");
                }
            }

            veiculo = new Onibus(placa, ano, tipoVeiculo);
            ((Onibus) veiculo).setQtdAssentos(qtdAssentos);

        } else {
            int qtdEixos = 0;
            boolean qtdEixosValida = false;

            while (!qtdEixosValida) {
                try {
                    String qtdEixosStr = JOptionPane.showInputDialog("Informe a quantidade de eixos: ");

                    qtdEixos = Integer.parseInt(qtdEixosStr);
                    qtdEixosValida = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Quantidade de eixos invalida, digite um numeto inteiro.");
                }
            }

            veiculo = new Caminhao(placa, ano, tipoVeiculo);
            ((Caminhao) veiculo).setQtdEixos(qtdEixos);
        }

        sistemaCadastro.cadastrarVeiculo(veiculo);
        JOptionPane.showMessageDialog(null,"Veiculo cadastro com sucesso!");
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
                veiculosCadastrados.append(veiculo.exibirDados()).append("\n----------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(null, veiculosCadastrados.toString());
    }

}
