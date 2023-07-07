package bob.estacionar.dominio.veiculos;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Carro extends Veiculo {
    public Carro(String placa, int ano, TipoVeiculo tipoVeiculo) {
        super(placa, ano, tipoVeiculo);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
        return dados.toString();
    }
}
