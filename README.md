# Java Roguelike Engine

Uma engine de RPG textual desenvolvida em Java puro, focada em Arquitetura de Software Modular, Design Patterns e Geração Procedural.

## 🚀 Sobre o Projeto
Este projeto não utiliza frameworks externos ou engines gráficas. O objetivo foi construir, do zero, uma arquitetura robusta capaz de gerenciar estados complexos de jogo, inventário, combate e progressão, aplicando princípios de **Engenharia de Software** e **SOLID**.

## 🛠️ Tecnologias e Conceitos Aplicados
* **Java 21** (Uso de Records, Pattern Matching, etc).
* **MVC Simplificado:** Separação clara entre Entidades (Model), Telas (View) e Lógica (Controller).
* **State Pattern:** Gerenciamento de fluxo de telas (Menu -> Combate -> Inventário) sem acoplamento.
* **Factory Pattern:** Geração procedural de itens e inimigos com atributos aleatórios controlados (Loot Table).
* **Strategy Pattern:** Sistema de efeitos de magias dinâmicos (Dano, Cura, Buffs) processados em tempo real.
* **Estrutura de Dados:** Uso intensivo de `HashMap` para atributos dinâmicos e `ArrayList` para gestão de inventário.

## 📦 Como Rodar
1.  Clone este repositório.
2.  Abra o projeto no VS Code ou IntelliJ.
3.  Execute o arquivo `src/main/Main.java`.

## 🚧 Roadmap e Funcionalidades
- [x] Sistema de Combate por Turnos
- [x] Geração Procedural de Armas e Magias
- [x] Sistema de Atributos e Buffs Temporários
- [x] Inventário e Gerenciamento de Equipamento
- [ ] Geração Procedural de Inimigos e Bosses
- [ ] Criação de Seleção de Atributos de Forma Aletória
- [ ] Implementação de Mapa/Matriz (Dungeon Crawler)
- [ ] Persistência de Dados (Save/Load)
- [ ] Sistema de Lendas
- [ ] Sistema de Nemesis
- [ ] Final Gerado Proceduralmente de Acordo com as ações do jogador
