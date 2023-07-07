package bob.estacionar.servicos;

import bob.estacionar.dominio.Veiculo;
import bob.estacionar.interfaces.CadastroVeiculos;
import bob.estacionar.interfaces.Vagas;

public class SistemaCadastroVeiculos implements CadastroVeiculos, Vagas {
    private Veiculo[] veiculos;
    private int qtdVeiculos;
    private boolean[] vagasDisponiveis;
    public static final int LIMITE_MAXIMO_VEICULOS = 10;
    public SistemaCadastroVeiculos(int numVagas) {
        veiculos = new Veiculo[LIMITE_MAXIMO_VEICULOS];
        vagasDisponiveis = new boolean[numVagas];
        qtdVeiculos = 0;

        for (int i = 0; i < LIMITE_MAXIMO_VEICULOS; i++) {
            vagasDisponiveis[i] = true; // Todas as vagas estão inicialmente livres
        }
    }

    public int encontrarVagaPorVeiculo(String placa) {
        for (int i = 0; i < qtdVeiculos; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                return i; // Retorna o índice da vaga ocupada pelo veículo
            }
        }
        return -1;// Retorna -1 se o veículo não for encontrado
    }

    public int obterProximaVagaDisponivel() {
        for (int i = 0; i < veiculos.length; i++) {
            if (vagasDisponiveis[i]) {
                return i;// Retorna o índice da primeira vaga disponível encontrada
            }
        }
        return -1;// Retorna -1 se não houver vagas disponíveis
    }

    public void atualizarStatusVagas() {
        for (int i = 0; i < veiculos.length; i++) {
            Veiculo veiculo = veiculos[i];
            boolean vagaDisponivel = vagasDisponiveis[i];

            if (veiculo != null && !veiculoEstaEstacionado(veiculo)) {
                vagasDisponiveis[i] = true;
            } else if (veiculo == null && !vagaDisponivel) {
                vagasDisponiveis[i] = true;
            }
        }
    }

    public boolean veiculoEstaEstacionado(Veiculo veiculo) {
        for (int i = 0; i < qtdVeiculos; i++) {
            if (veiculos[i] != null && veiculos[i].equals(veiculo)) {
                return true;
            }
        }

        return false;
    }

    public boolean existePlaca(String placa){
        for (int i = 0; i < qtdVeiculos; i++) {
            if (veiculos[i] != null && veiculos[i].getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (qtdVeiculos >= LIMITE_MAXIMO_VEICULOS) {
            System.out.println("Não há vagas disponíveis para cadastrar o veículo!");
            return;
        }

        int vaga = obterProximaVagaDisponivel();

        if (vaga != -1) {
            veiculos[vaga] = veiculo;
            vagasDisponiveis[vaga] = false;
            qtdVeiculos++;
            System.out.println("Veículo cadastrado com sucesso!");
        } else {
            System.out.println("Não há vagas disponíveis para cadastrar o veículo!");
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
            // Libera a vaga ocupada pelo veículo removido
            int vaga = encontrarVagaPorVeiculo(placa);
            if (vaga != -1) {
                vagasDisponiveis[vaga] = true; // marca vaga como livre
            }

            // Desloca os elementos após o veículo removido para preencher a lacuna
            for (int i = index; i < qtdVeiculos - 1; i++) {
                veiculos[i] = veiculos[i + 1];
            }

            veiculos[qtdVeiculos - 1] = null;
            qtdVeiculos--;
        }
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public boolean[] getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public int getQtdVeiculos() {
        return qtdVeiculos;
    }
}
