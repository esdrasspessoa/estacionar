package bob.estacionar.cadastroDeVeiculos.dominio;

public enum TipoVeiculo {
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
