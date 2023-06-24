package bob.estacionar.cadastroDeVeiculos.servicos;

import bob.estacionar.cadastroDeVeiculos.dominio.Veiculo;

public class SistemaCadastroVeiculos {
    private Veiculo[] veiculos;
    private int qtdVeiculos;

    public static final int LIMITE_MAXIMO_VEICULOS = 10;

    public SistemaCadastroVeiculos(int LIMITE_MAXIMO_VEICULOS) {
        veiculos = new Veiculo[LIMITE_MAXIMO_VEICULOS];
        qtdVeiculos = 0;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (qtdVeiculos < veiculos.length) {
            veiculos[qtdVeiculos] = veiculo;
            qtdVeiculos++;
            //System.out.println("Veiculo Cadastrado com sucesso!");
        } else {
            System.out.println("Limite maximo de veiculos atingido!");
        }
    }

    public void removerVeiculo(String placa) {
        int index = -1;

        //Procurando veiculo pela placa
        for (int i = 0; i < qtdVeiculos; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Desloca os elementos após o veículo removido para preencher a lacuna
            for (int i = index; i < qtdVeiculos - 1; i++) {
                veiculos[i] = veiculos[i + 1];
            }

            veiculos[qtdVeiculos - 1] = null;
            qtdVeiculos--;

            System.out.println("Veiculo " + placa + " removido com sucesso!");
        } else {
            System.out.println("Veiculo não encontrado!");
        }
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Veiculo[] veiculos) {
        this.veiculos = veiculos;
    }

    public int getQtdVeiculos() {
        return qtdVeiculos;
    }

    public void setQtdVeiculos(int qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
    }
}
