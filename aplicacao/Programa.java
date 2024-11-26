package Aplicacao;

import java.util.Scanner;


import Gerenciadores.GerenciadorFluxoDeCaixa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Programa {
    public static void main(String[] args) {
        // Instância do gerenciador de registros
        GerenciadorFluxoDeCaixa gerenciador = new GerenciadorFluxoDeCaixa();
        gerenciador.inicializarRegistros(); // Inicializar registros com exemplos

        Scanner scanner = new Scanner(System.in);
        int opcao;
        LocalDate hoje = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
            System.out.println("\u001B[36m\u001B[44m============================================");
            System.out.println("       💰 SISTEMA DE GESTÃO FINANCEIRA 💰");
            System.out.println("============================================\u001B[0m");
            System.out.printf("📅 Data Atual: %s\u001B[0m\n", hoje.format(formatter));
        do {
            
            exibirMenu(); // Exibir menu principal
            String input = scanner.nextLine();
                
                try {
                    opcao = Integer.parseInt(input); // Tenta converter a entrada para inteiro
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! ");
                    return;
                }

            switch (opcao) {
                case 1 -> gerenciador.cadastrarRegistro(); // Cadastrar novo registro
                case 2 -> gerenciador.alterarRegistro();  // Alterar um registro existente
                case 3 -> gerenciador.removerRegistro();  // Remover um registro
                case 4 -> gerenciador.listarRegistros();  // Listar registros com filtro
                case 5 -> gerenciador.buscarRegistro();   // Buscar registro por nome
                case 6 -> {
                    // Ordenar registros e exibir a tabela ordenada
                    var listaOrdenada = gerenciador.ordenarRegistros();
                    gerenciador.exibirTabela(listaOrdenada);
                }
                case 7 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);

        System.out.println("Sistema encerrado. Até mais!");
    }

    // Método para exibir o menu principal
    private static void exibirMenu() {
        System.out.println("\u001B[32m--------------------------------------------\u001B[0m");
        System.out.println("🏦 Bem-vindo ao Gerenciador de Fluxo de Caixa!🏦\u001B[0m");
        System.out.println("       Selecione uma das opções abaixo:\u001B[0m");
        System.out.println("\u001B[32m--------------------------------------------\u001B[0m");
        System.out.println("1️⃣  Cadastrar um novo registro\u001B[0m");
        System.out.println("2️⃣  Alterar um registro existente\u001B[0m");
        System.out.println("3️⃣  Remover um registro\u001B[0m");
        System.out.println("4️⃣  Listar registros (Receitas/Despesas)\u001B[0m");
        System.out.println("5️⃣  Buscar um registro por nome\u001B[0m");
        System.out.println("6️⃣  Ordenar registros (Nome/Valor/Data)\u001B[0m");
        System.out.println("7️⃣  Sair do sistema\u001B[0m");
        System.out.println("\u001B[32m--------------------------------------------\u001B[0m");
        System.out.print("💡 Qual número da operação que deseja realizar, Senhor? : \u001B[0m");
       

    }}
