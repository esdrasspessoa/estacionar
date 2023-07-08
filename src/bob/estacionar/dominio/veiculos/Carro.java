package bob.estacionar.dominio.veiculos;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Carro extends Veiculo {
    public Carro(String placa, int ano) {
        super(placa, ano, TipoVeiculo.CARRO);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
        return dados.toString();
    }
}
