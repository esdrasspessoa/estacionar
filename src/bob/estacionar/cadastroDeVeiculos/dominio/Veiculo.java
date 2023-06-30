package bob.estacionar.cadastroDeVeiculos.dominio;

public abstract class Veiculo {
    private String placa;
    private int ano;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String placa, int ano, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.ano = ano;
        this.tipoVeiculo = tipoVeiculo;
    }

    public String exibirDados() {
        StringBuilder dados = new StringBuilder();
        dados.append("--------------------------------\n");
        dados.append("Tipo de Veiculo: ").append(this.tipoVeiculo.getDescricao());
        dados.append("Placa: ").append(this.placa).append("\n");
        dados.append("Ano: ").append(this.ano).append("\n");
        return dados.toString();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
