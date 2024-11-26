Título do Repositório

Gerenciador de Fluxo de Caixa em Java

Descrição

Este projeto é um sistema de gerenciamento financeiro desenvolvido em Java, com foco na aplicação de conceitos de Programação Orientada a Objetos (POO). Ele permite cadastrar, alterar, remover e organizar registros financeiros, como Receitas e Despesas, e oferece funcionalidades como:
	
 •	Listagem detalhada de registros filtrados por tipo (Receitas, Despesas ou ambos).
	
 •	Ordenação de registros por nome, valor ou data.
	
 •	Exibição de um gráfico de barras horizontais para visualizar o fluxo financeiro.
	
 •	Validações robustas para evitar entradas inválidas, garantindo a integridade dos dados.

O projeto também explora conceitos fundamentais de POO, como herança, composição, polimorfismo e encapsulamento, aplicados de forma prática.

Funcionalidades

	
 •	Cadastro de Registros:
Permite adicionar novos registros, verificando a validade do tipo (Receita ou Despesa), data, valor e status.
	
 •	Alteração de Registros:
Modifica registros existentes com base no nome, incluindo validação de entradas.
	
 •	Listagem de Registros:
Lista todos os registros ou apenas receitas/despesas, com cálculo de totais (lucro líquido, soma de receitas e despesas).
	
 •	Ordenação de Registros:
Ordena registros de forma personalizada:
	
 •	Por nome, ignorando diferenças entre maiúsculas e minúsculas.
	
 •	Por valor, do maior para o menor(ou vice-versa).
	
 •	Por data, da mais antiga para a mais recente (ou vice-versa).


Conceitos Aplicados

	1.	POO:
	•	Herança: As classes Receita e Despesa herdam de uma classe abstrata FluxoDeCaixa.
	•	Polimorfismo: Métodos implementados de forma distinta em Receita e Despesa.
	•	Encapsulamento: Uso de métodos get e set para acesso controlado a atributos.
	•	Composição: Uso de listas (ArrayList) para armazenar registros.
	2.	Validação:
	•	Verificação de tipos e entradas inválidas (exemplo: tipo diferente de Receita/Despesa ou data em formato incorreto).
	3.	Interface com o Usuário:
	•	Menu interativo com opções numeradas e mensagens amigáveis.
	•	Utilização de cores (via códigos ANSI) para destacar elementos do menu.
	4.	Estrutura Modular:
	•	Métodos auxiliares organizados para filtragem e cálculos, seguindo boas práticas de separação de responsabilidades.

Pré-requisitos

	•	Java 17+ (ou versão mais recente).
	•	IDE ou ambiente de execução configurado para projetos em Java.
	•	Terminal com suporte a códigos ANSI (para exibir cores).

Exemplo de Execução

Menu Inicial:

============================================
       💰 SISTEMA DE GESTÃO FINANCEIRA 💰
============================================

1️⃣  Cadastrar um novo registro

2️⃣  Alterar um registro existente

3️⃣  Remover um registro

4️⃣  Listar registros (Receitas/Despesas)

5️⃣  Buscar um registro por nome

6️⃣  Ordenar registros (Nome/Valor/Data)

7️⃣  Sair do sistema
Escolha uma opção:

Contribuições:

Realizado por Marcos Menezes, Rafael Guerra, Rian Dultra e Vinicius Fernandes
