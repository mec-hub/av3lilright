import java.time.LocalDate;

public abstract class FluxoDeCaixa {
    protected String nome;
    protected LocalDate data;
    protected double valor;
    protected String status;

    public FluxoDeCaixa(String nome, LocalDate data, double valor, String status) {
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.status = status;
    }

    public abstract void exibirDetalhes();

    public abstract void alterar();
    
    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return this instanceof Receita ? "Receita" : "Despesa";
    }
    public LocalDate getData() {
        return data;
    }
}
