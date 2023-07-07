package bob.estacionar.dominio;

public enum TipoVeiculo {
    CARRO("Carro"),
    ONIBUS("Ônibus"),
    CAMINHAO("Caminhão");


   private String descricao;

    TipoVeiculo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
