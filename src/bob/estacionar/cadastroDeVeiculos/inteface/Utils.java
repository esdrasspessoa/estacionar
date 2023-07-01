package bob.estacionar.cadastroDeVeiculos.inteface;

import bob.estacionar.cadastroDeVeiculos.dominio.TipoVeiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Caminhao;
import bob.estacionar.cadastroDeVeiculos.dominio.veiculos.Onibus;
import bob.estacionar.exception.VeiculoInvalidoException;

import javax.swing.*;

public class Utils {
    public static String obterPlaca(){
        String placa = null;
        boolean placaValida = false;

        while (!placaValida) {
            try {
                String placaStr = JOptionPane.showInputDialog("Informe a placa do veículo (formato: XXX-9999):");

                if (placaStr == null){
                    // Usuário cancelou a entrada da placa
                    return null;
                }

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

        return placa;
    }

    public static int obterAno(){
        int ano = 0;
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

        return ano;
    }

    public static TipoVeiculo obterTipoVeiculo(){
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

        return tipoVeiculo;
    }

    public static Veiculo criarVeiculo(String placa, int ano, TipoVeiculo tipoVeiculo){
        if (tipoVeiculo == TipoVeiculo.ONIBUS) {
            int qtdAssentos = obterQuantidadeAssentos();
            Onibus onibus = new Onibus(placa, ano, tipoVeiculo);
            onibus.setQtdAssentos(qtdAssentos);
            return onibus;
        } else {
            int qtdEixos = obterQuantidadeEixos();
            Caminhao caminhao = new Caminhao(placa, ano, tipoVeiculo);
            caminhao.setQtdEixos(qtdEixos);
            return caminhao;
        }
    }

    public static int obterQuantidadeAssentos(){
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

        return qtdAssentos;
    }

    public static int obterQuantidadeEixos(){
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

        return qtdEixos;
    }
}
