# Catálogo de Filmes

Trabalho desenvolvido em Java para a disciplina de Estrutura de Dados e Ordenação.
O projeto tem como foco a aplicação prática de Tabelas Hash, algoritmos de ordenação e recursividade, seguindo boas práticas de programação e análise de complexidade.

---

## Objetivo

Implementar um sistema capaz de armazenar, buscar e ordenar filmes, permitindo ao usuário visualizar os resultados conforme diferentes critérios.

---

## Funcionalidades

- Inserir, remover e buscar filmes em uma Tabela Hash
- Exportar os dados para um vetor
- Ordenar os filmes utilizando:
    - BubbleSort
    - InsertionSort
    - QuickSort
- Exibir os filmes ordenados por:
    - Título
    - Ano de lançamento
    - Duração

 ---

 ## Estrutura do projeto

 ```
CatalogoFilmes/
 ├── .idea/
 ├── src/
 │   └── main/
 │       ├── java/
 │       │   └── example/
 │       │       ├── model/
 │       │       │   ├── Movie.java           # Classe que representa um filme
 │       │       │   └── Catalogue.java       # Implementação da Tabela Hash
 │       │       ├── sort/
 │       │       │   ├── BubbleSort.java
 │       │       │   ├── InsertionSort.java
 │       │       │   └── QuickSort.java       # Ordenações com análise de complexidade
 │       │       ├── compare/
 │       │       │   ├── MovieComparator.java
 │       │       │   ├── ByTitle.java
 │       │       │   ├── ByYear.java
 │       │       │   └── ByDuration.java      # Estratégias de comparação
 │       │       └── app/
 │       │           └── MainApp.java         # Classe principal (JavaFX ou console)
 │       └── resources/
 │           └── (arquivos auxiliares, se houver)
 ├── pom.xml                                  # Configuração do Maven
 ├── README.md
 └── target/                                  # Arquivos compilados
```

---

## Tecnologias Utilizadas

- Java 17+
- JavaFX
- Maven

---

# Como Executar o Projeto

1. Clone o repositório
```bash
git clone https://github.com/annaquezia/CatalogoFilmes.git
```

2. Entre na pasta do projeto
```bash
mvn clean install
```

3. Compile o projeto
```bash
mvn clean install
```

4. Execute a aplicação
```bash
mvn exec:java -Dexec.mainClass="example.app.MainApp"
```

---

## Integrantes
- Anna Quézia dos Santos
- Laura Rodrigues de Souza de Camargo
- Otávio Augsuto Fermino da Silva




