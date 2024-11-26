package Fluxos;
import java.util.Scanner;

import Gerenciadores.FluxoDeCaixa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Receita extends FluxoDeCaixa {
    public Receita(String nome, LocalDate data, double valor, String status) {
        super(nome, data, valor, status);
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
        System.out.println("Alterando Receita: " + nome);
        System.out.print("Novo valor: ");
        this.valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir o "\n"
        System.out.print("Novo status: ");
        this.status = scanner.nextLine();
    }
}
