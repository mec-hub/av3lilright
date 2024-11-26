import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Despesa extends FluxoDeCaixa {
    public Despesa(String nome, LocalDate data, double valor, String status) {
        super(nome, data, valor < 0 ? valor : -valor, status); // Força o valor a ser negativo
    }

 @Override
public void exibirDetalhes() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.printf("| %-15s | %-10s | %-10.2f | %-10s | %s       |\n",
            nome, data.format(formatter), valor, status, getTipo());
}


    @Override
    public void alterar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alterando Despesa: " + nome);
        System.out.print("Novo valor: ");
        double novoValor = scanner.nextDouble();
        scanner.nextLine(); // Consumir o "\n"
        this.valor = novoValor < 0 ? novoValor : -novoValor; // Garante que o novo valor seja negativo
        System.out.print("Novo status: ");
        this.status = scanner.nextLine();
    }
}
