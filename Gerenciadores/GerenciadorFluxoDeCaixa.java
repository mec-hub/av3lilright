package Gerenciadores;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Fluxos.Despesa;
import Fluxos.Receita;

public class GerenciadorFluxoDeCaixa implements GerenciamentoDeRegistros {
    private ArrayList<FluxoDeCaixa> registros;
    private Scanner scanner;

    public GerenciadorFluxoDeCaixa() {
        this.registros = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void inicializarRegistros() {
        LocalDate dataInicial = LocalDate.now().minusMonths(6); 

        for (int i = 1; i <= 7; i++) {
            LocalDate dataReceita = dataInicial.plusDays(i * 18); // Incrementa 5 dias para cada Receita
            //dataReceita = dataReceita.plusYears(i);
            LocalDate dataDespesa = dataInicial.plusDays(i * 12); // Incrementa 7 dias para cada Despesa
            registros.add(new Receita("Receita " + i, dataReceita, i * 100.0, "Concluído"));
            registros.add(new Despesa("Despesa " + i, dataDespesa, i * 50.0, "Pendente"));
        }
    }

 
    @Override
public void cadastrarRegistro() {
    System.out.println("Certo! Cadastrar um novo Registro:");
    System.out.println("Primeiro, Informe se é uma Receita ou uma Despesa digitando no Campo Abaixo");
    System.out.print("Tipo (Receita/Despesa): ");
    String tipo = scanner.nextLine();
      // Verificar se o tipo é inválido
      if (!tipo.equalsIgnoreCase("Receita") && !tipo.equalsIgnoreCase("Despesa")) {
        System.out.println("Erro: Tipo inválido! Cadastre novamente, porfavor!");
        return; // Interrompe o método
    }
    System.out.print("Agora informe o Nome: ");
    String nome = scanner.nextLine();
    
    // Definindo o formato dd/MM/yyyy
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.print("Informe a Data seguindo esse formato (dd/MM/yyyy): ");
    String dataString = scanner.nextLine();
    LocalDate data;
    try {
        data = LocalDate.parse(dataString, formatter); // Converte para LocalDate
    } catch (Exception e) {
        System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
        return; // Encerra o método caso a data seja inválida
    }
    System.out.print("Valor em R$: ");
    String input2 = scanner.nextLine(); // Lê a entrada do usuário como String

    // Verifica se a entrada é um número
    double valor;
        try {
             valor = Double.parseDouble(input2); // Tenta converter a entrada para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!  Tente Novamente. " );
            return; // Retorna os registros sem ordenação caso a entrada não seja válida
        }


    scanner.nextLine(); // Consumir o "\n"
    System.out.println("Status (Pendente/Concluído/Cancelado): ");
    System.out.println("1. Pendente");
    System.out.println("2. Concluído");
    System.out.println("3. Cancelado");
    System.out.print("Escolha o número: ");
    String input = scanner.nextLine(); // Lê a entrada do usuário como String

    // Verifica se a entrada é um número
    int opcao;
        try {
            opcao = Integer.parseInt(input); // Tenta converter a entrada para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Opção Inválida! Tente Novamente. ");
            return; // Retorna os registros sem ordenação caso a entrada não seja válida
        }

    String status;
    switch (opcao) {
        case 1 -> status = "Pendente";
        case 2 -> status = "Concluído";
        case 3 -> status = "Cancelado";
        default -> {
            System.out.println("Opção inválida! O status será definido como 'Pendente'.");
            status = "Pendente"; // Valor padrão para opções inválidas

    }}
    
    if (tipo.equalsIgnoreCase("Receita")) {
        registros.add(new Receita(nome, data, valor, status));
    } else if (tipo.equalsIgnoreCase("Despesa")) {
        registros.add(new Despesa(nome, data, valor, status));
    } else {
        System.out.println("Tipo inválido!");
    }

}
@Override
public void removerRegistro() {
    exibirTabela(registros);
    System.out.print("Digite o nome do registro a ser removido: ");
    String nome = scanner.nextLine();

    // Verifica se há algum registro com o nome fornecido
    boolean registroRemovido = registros.removeIf(registro -> registro.getNome().equalsIgnoreCase(nome));

    // Mensagens de feedback
    if (registroRemovido) {
        System.out.println("Registro \"" + nome + "\" removido com sucesso!");
    } else {
        System.out.println("Erro: Nenhum registro encontrado com o nome \"" + nome + "\".");
    }
}
  
   
    @Override
public void alterarRegistro() {
    exibirTabela(registros);
    System.out.print("Digite o nome do registro a ser alterado: ");
    String nome = scanner.nextLine();
    
    for (FluxoDeCaixa registro : registros) {
        if (registro.getNome().equalsIgnoreCase(nome)) {
            System.out.println("Alterando registro: " + nome);

            // Alterar a data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.print("Nova data (dd/MM/yyyy) (pressione Enter para manter): ");
            String novaDataString = scanner.nextLine();
            if (!novaDataString.isEmpty()) {
                try {
                    registro.data = LocalDate.parse(novaDataString, formatter);
                } catch (Exception e) {
                    System.out.println("Data inválida! Alteração cancelada.");
                    return; // Sai do método
                }
            }

            System.out.print("Novo valor (pressione Enter para manter): ");
            String novoValorInput = scanner.nextLine();
            // Verifica se a entrada é um número
           
            if (!novoValorInput.isEmpty()){
                try{
                double novoValor = Double.parseDouble(novoValorInput);
                registro.valor = registro instanceof Despesa && novoValor > 0 ? -novoValor : novoValor;}
                catch (NumberFormatException e) {
                    System.out.println("Opção inválida!  Tente Novamente. " );
                    return; // Retorna os registros sem ordenação caso a entrada não seja válida
                }
            }
                
            System.out.print("Deseja alterar o status? (S/Enter) ");
            String alterarStatus = scanner.nextLine();
            if (alterarStatus.equalsIgnoreCase("S")) {
                System.out.println("Status (Pendente/Concluído/Cancelado):");
                System.out.println("1. Pendente");
                System.out.println("2. Concluído");
                System.out.println("3. Cancelado");
                System.out.print("Escolha uma opção: ");
                String input = scanner.nextLine(); // Lê a entrada do usuário como String

                // Verifica se a entrada é um número
                int opcao;
                    try {
                        opcao = Integer.parseInt(input); // Tenta converter a entrada para inteiro
                    } catch (NumberFormatException e) {
                        System.out.println("Opção inválida! O status será mantido como: " + registro.status);
                        return; // Retorna os registros sem ordenação caso a entrada não seja válida
                    }

                String novoStatus;
                switch (opcao) {
                    case 1 -> novoStatus = "Pendente";
                    case 2 -> novoStatus = "Concluído";
                    case 3 -> novoStatus = "Cancelado";
                    default -> {
                        System.out.println("Opção inválida! O status será mantido como: " + registro.status);
                        return;
                    }
                }
                registro.status = novoStatus;
            }

            System.out.println("Registro alterado com sucesso!");
            return;
        }
    }

    System.out.println("Registro não encontrado!");
}

    
  
    public void listarRegistros() {
        System.out.println("\n==== Listagem de Registros ====");
        System.out.println("1. Listar todos");
        System.out.println("2. Listar apenas receitas");
        System.out.println("3. Listar apenas despesas");
        System.out.print("Escolha uma opção: ");
        int opcaoListar = scanner.nextInt();
        scanner.nextLine();
    
        ArrayList<FluxoDeCaixa> listaFiltrada = filtrarRegistros(opcaoListar);
        if (listaFiltrada == null) {
            System.out.println("Ação indisponível no momento!");
            return;
        }
    
        exibirTabela(listaFiltrada);
    
        // Calcular e exibir os totais
        double totalReceitas = calcularTotal(listaFiltrada, Receita.class);
        double totalDespesas = calcularTotal(listaFiltrada, Despesa.class);
    
        if (opcaoListar == 1) { // Caso seja "Listar todos"
            System.out.println("Resumo Financeiro:");
            System.out.printf("\u001B[42m   - Total de Receitas: R$ %.2f\u001B[0m\n", totalReceitas);
            System.out.printf("\u001B[41m   - Total de Despesas: R$ %.2f\u001B[0m\n", totalDespesas);
            System.out.printf("\u001B[44m   - Lucro Líquido R$ %.2f\u001B[0m\n", (totalReceitas + totalDespesas));
            System.out.println("Receitas: ");
    int barrasReceitas = (int) (totalReceitas / 100); // Cada barra representa R$ 100
    for (int i = 0; i < barrasReceitas; i++) {
        System.out.print("█");
    }
    System.out.printf(" (R$ %.2f)\n", totalReceitas);

    System.out.println("Despesas: ");
    int barrasDespesas = (int) (-1*(totalDespesas / 100)); // Cada barra representa R$ 100
    for (int i = 0; i < barrasDespesas; i++) {
        System.out.print("█");
    }
    System.out.printf(" (R$ %.2f)\n", totalDespesas);

    System.out.println("==================================");
        } else if (opcaoListar == 2) { // Apenas receitas
            System.out.println("Resumo de Receitas");
            System.out.printf("\u001B[32m   - Total de Receitas: R$ %.2f\n", totalReceitas);
        } else if (opcaoListar == 3) { // Apenas despesas
            System.out.println("Resumo de Despesas:");
            System.out.printf("\u001B[31m   - Total de Despesas: R$ %.2f\n", totalDespesas);
        }
    }
    private ArrayList<FluxoDeCaixa> filtrarRegistros(int opcaoListar) {
        ArrayList<FluxoDeCaixa> listaFiltrada = new ArrayList<>();
        switch (opcaoListar) {
            case 1 -> listaFiltrada.addAll(registros); // Todos os registros
            case 2 -> registros.stream()
                    .filter(r -> r instanceof Receita)
                    .forEach(listaFiltrada::add); // Apenas receitas
            case 3 -> registros.stream()
                    .filter(r -> r instanceof Despesa)
                    .forEach(listaFiltrada::add); // Apenas despesas
            default -> {
                return null; // Caso de opção inválida
            }
        }
        return listaFiltrada;
    }
    private double calcularTotal(ArrayList<FluxoDeCaixa> lista, Class<?> tipo) {
        return lista.stream()
                .filter(r -> tipo.isInstance(r)) // Verifica se o registro é do tipo fornecido
                .mapToDouble(FluxoDeCaixa::getValor) // Mapeia para os valores
                .sum(); // Soma os valores
    }
            

    @Override
    public void buscarRegistro() {
        System.out.print("Digite o nome do registro a ser buscado: ");
        String nome = scanner.nextLine();
        for (FluxoDeCaixa registro : registros) {
            if (registro.getNome().equalsIgnoreCase(nome)) {
                registro.exibirDetalhes();
                return;
            }
        }
        System.out.println("Registro não encontrado!");
    }

    @Override
    public ArrayList<FluxoDeCaixa> ordenarRegistros() {
        System.out.println("\n==== Ordenação ====");
        System.out.println("1. Ordenar por nome");
        System.out.println("2. Ordenar por valor");
        System.out.println("3. Ordenar por data");
        System.out.print("Escolha uma opção: ");
       /*  int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n"*/
        String input = scanner.nextLine(); // Lê a entrada do usuário como String

    // Verifica se a entrada é um número
    int opcao;
        try {
            opcao = Integer.parseInt(input); // Tenta converter a entrada para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Exibindo registros sem ordenação.");
            return registros; // Retorna os registros sem ordenação caso a entrada não seja válida
        }
    
        ArrayList<FluxoDeCaixa> listaOrdenada = new ArrayList<>(registros);
    
        switch (opcao) {
            case 1 -> listaOrdenada.sort(Comparator
            .comparing((FluxoDeCaixa r) -> r.getNome().toLowerCase()) // Ignora maiúsculas/minúsculas
            .thenComparing(FluxoDeCaixa::getValor)); // Critério de desempate por valor
            case 2 ->{
                System.out.println("\nOrdenar por Valor:");
                System.out.println("1. Do Menor para o Maior");
                System.out.println("2. Do maior para o menor");
                System.out.print("Escolha uma opção: ");
                String input2 =scanner.nextLine();
                int opcaoValor;
                try {
                    opcaoValor = Integer.parseInt(input2); // Tenta converter a entrada para inteiro
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Exibindo registros sem ordenação.");
                    return registros; // Retorna os registros sem ordenação caso a entrada não seja válida
                }
            
                if (opcaoValor == 1) {
                    listaOrdenada.sort(Comparator.comparingDouble(FluxoDeCaixa::getValor)); // Valor decrescente // Da mais antiga para a mais nova
                } else if (opcaoValor == 2) {
                    listaOrdenada.sort(Comparator.comparingDouble(FluxoDeCaixa::getValor).reversed()); // Valor decrescente 
                } else {
                    System.out.println("Opção inválida! Exibindo sem ordenação.");
                }

            } 
            case 3 -> {
                System.out.println("\nOrdenar por data:");
                System.out.println("1. Da mais antiga para a mais nova");
                System.out.println("2. Da mais nova para a mais antiga");
                System.out.print("Escolha uma opção: ");
                String input3 = scanner.nextLine();
                int opcaoData;
                try {
                    opcaoData = Integer.parseInt(input3); // Tenta converter a entrada para inteiro
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Exibindo registros sem ordenação.");
                    return registros; // Retorna os registros sem ordenação caso a entrada não seja válida
                }
                if (opcaoData == 1) {
                    listaOrdenada.sort(Comparator.comparing(FluxoDeCaixa::getData)); // Da mais antiga para a mais nova
                } else if (opcaoData == 2) {
                    listaOrdenada.sort(Comparator.comparing(FluxoDeCaixa::getData).reversed()); // Da mais nova para a mais antiga
                } else {
                    System.out.println("Opção inválida! Exibindo sem ordenação.");
                }
            }
            default -> System.out.println("Opção inválida! Exibindo sem ordenação.");
        }
    
        return listaOrdenada;
    }
    

    public void exibirTabela(ArrayList<FluxoDeCaixa> lista) {
        System.out.println("\n| Nome            | Data       | Valor      | Status     | Tipo       |");
        System.out.println("---------------------------------------------------------------");
        for (FluxoDeCaixa registro : lista) {
            registro.exibirDetalhes();
        }
    }

}
