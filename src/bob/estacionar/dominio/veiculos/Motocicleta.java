package bob.estacionar.dominio.veiculos;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Motocicleta extends Veiculo {
    public Motocicleta(String placa, int ano) {
        super(placa, ano, TipoVeiculo.MOTOCICLETA);
    }

    @Override
    public String exibirDados() {
        return super.exibirDados();
    }
}
