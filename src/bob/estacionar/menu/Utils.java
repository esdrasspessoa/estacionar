package bob.estacionar.menu;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;
import bob.estacionar.dominio.veiculos.Caminhao;
import bob.estacionar.dominio.veiculos.Carro;
import bob.estacionar.dominio.veiculos.Motocicleta;
import bob.estacionar.dominio.veiculos.Onibus;
import bob.estacionar.exception.CancelarEntradaException;
import bob.estacionar.exception.VeiculoInvalidoException;

import javax.swing.*;
import java.time.Year;

public class Utils {
    public static String obterPlaca() {
        String placa = null;
        boolean placaValida = false;

        while (!placaValida) {
            try {
                String placaStr = JOptionPane.showInputDialog("Informe a placa do veículo (formato: XXX-9999):");

                if (placaStr == null) {
                    return null;
                }

                placaStr = placaStr.toUpperCase();

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

    public static int obterAno() {
        int ano = 0;
        boolean anoValido = false;

        int anoAtual = Year.now().getValue();
        String limiteSuperior = String.valueOf(anoAtual);

        while (!anoValido) {
            try {
                String anoStr = JOptionPane.showInputDialog("Informe o ano do veículo: (formato: AAAA)");

                if (anoStr == null) {
                    // Usuário cancelou a entrada do ano
                    throw new CancelarEntradaException();
                }

                if (anoStr.matches("\\d{4}")) {
                    ano = Integer.parseInt(anoStr);

                    //Verificação se o ano esta dentro de um intervalo valido
                    if (ano >= 1900 && ano <= Integer.parseInt(limiteSuperior)) {
                        anoValido = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ano inválido. Digite um ano entre 1900 e " + limiteSuperior + ".");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ano inválido. Digite um número de 4 digitos: (ex: " + limiteSuperior);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ano inválido. Digite um numero inteiro!");
            } catch (CancelarEntradaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return 0;
            }
        }

        return ano;
    }

    public static TipoVeiculo obterTipoVeiculo() {
        String[] opcoesTipo = new String[]{TipoVeiculo.ONIBUS.getDescricao(), TipoVeiculo.CAMINHAO.getDescricao(), TipoVeiculo.CARRO.getDescricao()};
        JComboBox<String> comboBox = new JComboBox<>(opcoesTipo);
        JOptionPane.showMessageDialog(null, comboBox, "Selecionne o tipo de veiculo:", JOptionPane.QUESTION_MESSAGE);

        String tipoVeiculoSelecionado = (String) comboBox.getSelectedItem();
        TipoVeiculo tipoVeiculo = null;

        if (tipoVeiculoSelecionado.equals(TipoVeiculo.CARRO.getDescricao())) {
            tipoVeiculo = TipoVeiculo.CARRO;
        } else if (tipoVeiculoSelecionado.equals(TipoVeiculo.CAMINHAO.getDescricao())) {
            tipoVeiculo = TipoVeiculo.CAMINHAO;
        } else if (tipoVeiculoSelecionado.equals(TipoVeiculo.ONIBUS.getDescricao())) {
            tipoVeiculo = TipoVeiculo.ONIBUS;
        }

        return tipoVeiculo;
    }

    public static Veiculo criarVeiculo(String placa, int ano, TipoVeiculo tipoVeiculo) {
        Veiculo veiculo;

        switch (tipoVeiculo) {
            case CARRO -> veiculo = new Carro(placa, ano);

            case ONIBUS -> veiculo = new Onibus(placa, ano);

            case CAMINHAO -> veiculo = new Caminhao(placa, ano);

            case MOTOCICLETA -> veiculo = new Motocicleta(placa, ano);

            default -> throw new IllegalArgumentException("Tipo de veiculo inválido: " + tipoVeiculo);
        }

        return veiculo;
    }
}
