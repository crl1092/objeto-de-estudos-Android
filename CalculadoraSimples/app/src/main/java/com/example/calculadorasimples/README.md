[← Voltar ao Índice Principal](../../../../../../../../../../)

# 🧮 Calculadora Simples
...
# 🧮 Calculadora Simples - Jetpack Compose

Este projeto faz parte do meu portfólio de estudos em **Engenharia de Software**, focado no desenvolvimento Android moderno com **Jetpack Compose**.

## 🎯 Objetivo
Transformar uma lógica de calculadora desenvolvida em Kotlin puro para uma interface interativa, aplicando conceitos de gerenciamento de estado e arquitetura de pacotes.

## 🛠️ O que foi aplicado:
- **State Management**: Uso de `remember` e `mutableStateOf` para reatividade da UI.
- **Arquitetura**: Separação de responsabilidades criando pacotes específicos para `logic` (regras de negócio) e `ui`.
- **UX/UI**: Implementação de teclado numérico (`KeyboardOptions`) e espaçamentos dinâmicos com `Spacer`.
- **Segurança**: Tratamento de entradas nulas ou inválidas com `toDoubleOrNull()`.
- **Novas Funcionalidades**: Histórico de operações em tempo real , validação contra divisão por zero e uma forma de apagar o histórico.

### 🧠 Conceitos de Engenharia Aplicados
Neste projeto, apliquei padrões recomendados pelo Google para o desenvolvimento com Jetpack Compose:

* **State Hoisting (Elevação de Estado):** Implementei a separação entre componentes que gerenciam o estado (Stateful) e componentes que apenas exibem dados (Stateless). Isso torna a interface mais previsível e fácil de testar.
* **Unidirectional Data Flow (Fluxo de Dados Unidirecional):** O estado "desce" para os componentes de interface (Data Down) e os eventos de interação "sobem" para serem processados pela lógica central (Events Up).
* **Modularização de UI:** Criei componentes reutilizáveis, como o `CalculadoraInput`, reduzindo a duplicação de código e facilitando a manutenção.
* **Tratamento de Exceções:** Implementei verificações para entradas inválidas e proteção contra erros matemáticos (como divisão por zero), garantindo a estabilidade do app.

## 📸 Resultado Visual
<p align="center">
  <img src="./Prints_De_Projetos/CalculadoraSimples.png" width="300">
</p>

---
*Estudos realizados através dos cursos Google Developers e prática independente.*
