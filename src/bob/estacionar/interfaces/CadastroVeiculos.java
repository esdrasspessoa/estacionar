package bob.estacionar.interfaces;

import bob.estacionar.dominio.Veiculo;

public interface CadastroVeiculos {
    void cadastrarVeiculo(Veiculo veiculo);
    void removerVeiculo(String placa);
}
