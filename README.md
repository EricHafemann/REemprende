🌟 REemprende --- Sistema de Agendamentos
=======================================

> **Projeto Acadêmico** desenvolvido para a disciplina de **Arquitetura de Sistemas**.
>
> Foco: Implementação do padrão **MVP (Model-View-Presenter)** e separação rigorosa de responsabilidades.

* * * * *

🎯 Sobre o Projeto
------------------

O **REemprende** é uma solução para microempreendedores que necessitam de uma gestão de horários simplificada. O projeto foca na excelência arquitetural, utilizando camadas bem definidas para garantir que a lógica de negócio seja independente da interface e da infraestrutura.

### 🏛️ Pilares Arquiteturais

-   **Padrão MVP:** Desacoplamento total da interface (View) e lógica de apresentação (Presenter).

-   **Camadas Definidas:** Organização clara entre Apresentação, Aplicação, Domínio e Infraestrutura.

-   **Abstração de Dados:** Implementação do padrão **Repository** para isolamento da camada de persistência.

-   **Qualidade de Código:** Aderência aos princípios **SOLID**, **Clean Code** e tratamento de exceções personalizado.

* * * * *

## 📝 Decisões Arquiteturais (ADR)

| Critério | Decisão | Justificativa |
| :--- | :--- | :--- |
| **Padrão** | MVP (Model-View-Presenter) | Isolar completamente a View (Console) da lógica, permitindo trocar a interface no futuro sem tocar nas regras de negócio. |
| **Estilo** | Monolítico | Como é um projeto simples, não há necessidade de utilizar Microserviços. |
| **Execução** | Client-Server | Estrutura clássica para persistência robusta em MySQL com acesso via JDBC. |

### 📊 Diagrama de Pacotes
https://lucid.app/lucidchart/10a099ef-23ea-4603-ade5-97ce3d2ce776/edit?viewport_loc=-1589%2C-1257%2C4977%2C2968%2C0_0&invitationId=inv_467dd0da-2036-464a-b5f9-f0c275ebdc81

✨ Funcionalidades
-----------------

### 👤 Gestão de Usuários

-   **Acesso Seguro:** Cadastro e Login diferenciados para **Clientes (CPF)** e **Comerciantes (CNPJ)**.

-   **Sessão Ativa:** Controle de estado do usuário logado durante o ciclo de vida da aplicação.

-   **Gestão de Perfil:** Atualização de dados sensíveis como Nome, E-mail e Senha.

### 🛠️ Serviços & Agendamentos

-   **Painel do Comerciante:**

    -   Gestão completa de serviços (CRUD).

    -   Monitoramento de agendamentos futuros e histórico de atendimentos.

-   **Painel do Cliente:**

    -   Visualização de horários disponíveis em tempo real.

    -   Reserva de serviços com interface intuitiva via console.

* * * * *

🏗️ Arquitetura do Sistema
--------------------------

A estrutura de pastas reflete a maturidade do projeto e a preocupação com a escalabilidade:

```
src/main/java/org/reempreende/
│
├── 🎨 presentation/                # Camada de Apresentação
│   ├── view/                       # CLI - InicialView, ClienteView
│   ├── presenter/                  # Mediação (LoginPresenter, ClientePresenter)
│   └── router/                     # Controle de navegação entre telas
│
├── ⚙️ application/                 # Camada de Aplicação
│   ├── service/                    # Regras de aplicação (UsuarioService, AgendamentoService)
│   ├── dto/                        # Request e Response Objects
│   └── mapper/                     # Conversão Entity ↔ DTO
│
├── ⚖️ domain/                      # Camada de Domínio (Core)
│   ├── entities/                   # Modelos: Usuario, Cliente, Servico, Agendamento
│   ├── repository/                 # Interfaces (Contratos) de dados
│   └── enums/                      # Definições de Status e Tipos
│
└── 🔌 infrastructure/              # Camada de Infraestrutura
    ├── repository/                 # Implementações JDBC (UsuarioRepositoryImpl)
    ├── persistence/                # Gestão de conexões (ConnectionFactory)
    └── utility/                    # Helpers: Cores, TextoUtil e Formatação
    └── sessao/                     # Armazena dados do usuário logado
```

* * * * *

📊 Modelo de Domínio
--------------------

O projeto utiliza **Herança** para tratar a especialização de usuários, garantindo um código limpo e reutilizável.

-   **Usuario (Abstrata):** Atributos base de autenticação.

-   **Cliente / Comerciante:** Especializações com documentos únicos.

-   **Servico:** Ofertas vinculadas a um prestador específico.

-   **Agendamento:** O elo final entre cliente, prestador e tempo.

* * * * *

🛠️ Tecnologias Utilizadas
--------------------------

| **Tecnologia** | **Versão** | **Descrição** |
| --- | --- | --- |
| **Java** | 22 | Linguagem principal de desenvolvimento |
| **MySQL** | 8.3.0 | Armazenamento de dados persistentes |
| **Maven** | 3.x | Build tool e gestão de dependências |
| **JDBC** | - | Acesso nativo a dados relacionais |
| **IntelliJ IDEA** | 2025.3.4 | Ambiente de Desenvolvimento Integrado |

* * * * *

👥 Equipe de Desenvolvimento
----------------------------

| **Contribuidor** | **GitHub** |
| --- | --- |
| **Eric Hafemann** | [@EricHafemann](https://github.com/EricHafemann) |
| **Enzo Venturi** | [@enzoventuri](https://github.com/enzoventuri) |
| **Murilo HJ** | [@MuriloHJ](https://github.com/MuriloHJ) |
| **Gabriel Beh** | [@gabrielbeh123](https://github.com/gabrielbeh123) |

* * * * *

> 🎓 **Contexto:** Trabalho prático para o curso de **Técnico em Informática para Internet**.
>
> 🏫 **Instituição:** CentroWEG | SENAI
>
> 📖 **Disciplina:** Arquitetura de Sistemas
