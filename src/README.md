# Conversor de Moedas - Challenge ONE

![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-brightgreen)

Um conversor de moedas interativo que funciona via console, desenvolvido em Java. Este projeto consome a API **ExchangeRate-API** para obter taxas de câmbio em tempo real e oferece um menu com 6 opções distintas de conversão, além da opção de sair.

## Sobre o Projeto

Este projeto foi desenvolvido como parte do Challenge de Java da Alura em parceria com a Oracle (ONE - Oracle Next Education). O objetivo era criar uma aplicação Java do zero, passando pelas etapas de consumo de API, parsing de JSON, criação de interface de usuário no console e manipulação de dados.

## Funcionalidades

-   **Taxas de Câmbio em Tempo Real:** Conecta-se à [ExchangeRate-API](https://www.exchangerate-api.com/) para buscar as cotações mais recentes.
-   **Menu Interativo:** Interface de usuário baseada em texto no console, fácil de usar.
-   **6 Opções de Conversão:** Oferece conversões pré-definidas entre as principais moedas, como Dólar, Real, Euro e Peso Argentino.
-   **Estrutura Organizada:** O código é dividido em classes com responsabilidades bem definidas (interação com usuário, consulta à API, modelo de dados).
-   **Tratamento de Erros:** A aplicação lida com entradas inválidas do usuário (textos em vez de números) sem quebrar.

## Tecnologias Utilizadas

-   **Java 21**
-   **Maven:** Gerenciador de dependências e de build do projeto.
-   **Biblioteca Gson:** Para fazer o parsing (análise) da resposta JSON da API para objetos Java.
-   **Java HTTP Client:** Cliente HTTP nativo do Java (desde a versão 11) para realizar as requisições à API.

## Como Executar o Projeto

Siga os passos abaixo para configurar e executar o projeto em sua máquina local.

### Pré-requisitos

-   **JDK 17 ou superior** instalado.
-   **Maven** instalado e configurado no PATH do sistema.
-   **Git** para clonar o repositório.
-   Uma **IDE** de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Tavobrandao/Challenge-Conversor-Moedas.git](https://github.com/Tavobrandao/Challenge-Conversor-Moedas.git)
    cd Challenge-Conversor-Moedas
    ```

2.  **Obtenha uma Chave de API:**
    -   Acesse [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/) e cadastre-se no plano gratuito para obter sua API Key.

3.  **Configure a Chave de API no Código:**
    -   Abra o projeto na sua IDE.
    -   Navegue até o arquivo `src/br/com/conversor/ConsultaApi.java`.
    -   Localize a linha a seguir e substitua `"SUA_API_KEY_AQUI"` pela chave que você obteve:
        ```java
        String apiKey = "SUA_API_KEY_AQUI";
        ```

4.  **Execute a Aplicação:**
    -   **Via IDE:** Abra a classe `Principal.java` e execute o método `main()`.
    -   **Via Linha de Comando (usando Maven):**
        ```bash
        # Compila o projeto e executa a classe principal
        mvn compile exec:java -Dexec.mainClass="br.com.conversor.Principal"
        ```

## Como Usar

Ao executar o programa, um menu será exibido no console.

```
Bem-vindo(a) ao Conversor de Moedas!
Escolha uma das opções abaixo:

1) Dólar Americano (USD) => Real Brasileiro (BRL)
2) Real Brasileiro (BRL) => Dólar Americano (USD)
3) Dólar Americano (USD) => Peso Argentino (ARS)
4) Peso Argentino (ARS) => Dólar Americano (USD)
5) Dólar Americano (USD) => Euro (EUR)
6) Euro (EUR) => Real Brasileiro (BRL)
7) Sair

Sua opção:
```

1.  Digite o número da conversão desejada e pressione Enter.
2.  Digite o valor que você quer converter e pressione Enter.
3.  O resultado da conversão será exibido na tela.
4.  O menu aparecerá novamente para uma nova conversão.
5.  Digite `7` para sair do programa.

## Autor

-   **Gustavo Brandão** - [LinkedIn](https://www.linkedin.com/in/gustavobrandaobr/)

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.