package bob.estacionar.interfaces;

import bob.estacionar.dominio.Veiculo;

public interface Vagas {
    int encontrarVagaPorVeiculo(String placa);
    int obterProximaVagaDisponivel();
    void atualizarStatusVagas();
    boolean veiculoEstaEstacionado(Veiculo veiculo);
    boolean[] getVagasDisponiveis();
}
