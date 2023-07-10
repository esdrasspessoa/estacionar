package bob.estacionar.dominio.veiculos;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Onibus extends Veiculo {

    public Onibus(String placa, int ano) {
        super(placa, ano, TipoVeiculo.ONIBUS);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
        return dados.toString();
    }

}
