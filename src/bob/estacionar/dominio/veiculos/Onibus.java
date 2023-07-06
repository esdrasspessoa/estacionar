package bob.estacionar.dominio.veiculos;

import bob.estacionar.dominio.TipoVeiculo;
import bob.estacionar.dominio.Veiculo;

public class Onibus extends Veiculo {
    private int qtdAssentos;

    public Onibus(String placa, int ano, TipoVeiculo tipoVeiculo) {
        super(placa, ano, tipoVeiculo);
    }

    @Override
    public String exibirDados() {
        StringBuilder dados = new StringBuilder(super.exibirDados());
        dados.append("Assentos: ").append(this.qtdAssentos).append("\n");
        return dados.toString();
    }

    public int getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }
}
