package bob.estacionar.dominio.veiculos;


import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Caminhao extends Veiculo {

    public Caminhao(String placa, int ano) {
        super(placa, ano, TipoVeiculo.CAMINHAO);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
        return dados.toString();
    }
}
