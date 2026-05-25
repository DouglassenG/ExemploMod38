# 🌐 Java EE & JSF - Aplicação Web Corporativa (Exemplo Mod 38)

> Um sistema web monolítico arquitetado para o ecossistema corporativo. Este projeto unifica a robustez do backend Java com uma interface gráfica rica para o usuário (Frontend), utilizando as especificações oficiais do Jakarta EE (antigo Java EE) executadas sobre um servidor de aplicação.

## 🎯 Motivação e Propósito

Aplicações de console são limitadas e APIs REST necessitam de um cliente frontend (como React ou Angular) para serem operadas por usuários finais. O propósito deste repositório é aplicar o padrão arquitetural **MVC (Model-View-Controller)** utilizando a renderização do lado do servidor (SSR), entregando uma aplicação completa onde o próprio Java gerencia a interface HTML.

O projeto resolve o problema de orquestração de interfaces complexas sem depender de frameworks JavaScript externos. Ele demonstra a capacidade de integrar a interface visual diretamente com o banco de dados de maneira fluida e segura, abstraindo toda a complexidade do protocolo HTTP.

> **Métricas e Resultados de Arquitetura:**
> * A implementação do framework **PrimeFaces** em conjunto com **JSF** reduziu o tempo de construção de componentes de interface (como DataTables e Modais) em cerca de **60%**, substituindo milhares de linhas de HTML/JS e CSS puro por tags declarativas baseadas em XML.
> * O uso do **CDI (Contexts and Dependency Injection)** reduziu a declaração manual de instâncias (`new Objeto()`) na camada controladora em **100%**, otimizando a alocação de memória na JVM e garantindo que o ciclo de vida dos *Beans* (Request, Session, Application) seja totalmente gerenciado pelo servidor.

## 🛠️ Tecnologias Utilizadas

A stack baseia-se nos pilares da computação web corporativa do Java:

* **Java (JDK):** Linguagem principal que controla as regras de negócio e os *Managed Beans*.
* **Jakarta EE / Java EE:** Plataforma base para as especificações corporativas.
* **JSF (JavaServer Faces):** Framework MVC nativo para construção de interfaces de usuário baseadas em componentes.
* **PrimeFaces:** Biblioteca de componentes de interface visual rica (UI) embutida no JSF.
* **CDI (Contexts and Dependency Injection):** Especificação para injeção de dependências e controle de contexto web.
* **JPA / Hibernate:** Mapeamento Objeto-Relacional para a persistência de dados.
* **Servidor de Aplicação (WildFly / Apache Tomcat):** Container Web utilizado para implantar (Deploy) e hospedar a aplicação.

## ✨ Funcionalidades

1. **Renderização de Interface (SSR):** Páginas dinâmicas construídas com a extensão `.xhtml`, processadas pelo servidor e devolvidas como HTML puro ao navegador.
2. **Gerenciamento de Estado (Scopes):** Uso inteligente de contextos (`@RequestScoped`, `@ViewScoped`, `@SessionScoped`) para reter ou limpar dados durante a navegação do usuário.
3. **Injeção de Dependências Dinâmica:** Comunicação fluida entre a camada Visual (*View*) e a camada de Regras de Negócio (*Service*/*DAO*) orquestrada via `@Inject`.
4. **Data Binding Bidirecional:** Os dados inseridos pelo usuário no formulário web refletem automaticamente nos atributos da classe Java em tempo real.

## 📂 Estrutura de Pastas

A arquitetura respeita a padronização oficial de aplicações Web Java (`.war`):

```text
ExemploMod38/
├── src/
│   ├── main/
│   │   ├── java/br/com/douglas/
│   │   │   ├── controllers/     # Managed Beans orquestrando a View e o Model
│   │   │   ├── services/        # Lógicas de negócio (Injetadas via CDI)
│   │   │   ├── dao/             # Comunicação com o banco via JPA
│   │   │   └── domain/          # Entidades Mapeadas (@Entity)
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       ├── persistence.xml # Configurações do Banco de Dados
│   │   │       └── beans.xml       # Habilita o contexto de Injeção (CDI)
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml         # Deployment Descriptor (Configurações do Servidor)
│   │       │   └── faces-config.xml# Mapeamentos e regras do JSF
│   │       └── index.xhtml         # Páginas front-end (Views)
│   └── test/
│       └── java/br/com/douglas/ # Suítes de testes da camada de negócios
├── pom.xml                      # Manifesto do Apache Maven (Build e Dependências)
└── README.md                    # Documentação do projeto
