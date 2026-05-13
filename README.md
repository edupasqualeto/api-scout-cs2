# CS2 Scout API

API REST desenvolvida com Spring Boot para gerenciamento de jogadores profissionais de Counter-Strike 2.

---

## Objetivo do Projeto

O projeto foi criado com foco em:

* prática de desenvolvimento backend com Java e Spring Boot
* modelagem de entidades relacionais, banco de dados
* arquitetura em camadas
* boas práticas de API REST
* uso de DTOs e validações
* integração com PostgreSQL e Docker

---

# Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Maven
* Lombok
* Jakarta Validation
* Hibernate

---

# Modelagem

## Entidades principais

### Jogador

Representa um jogador profissional de CS2.

### Time

Representa uma organização/equipe de CS2.

### PerfilCompetitivo

Armazena estatísticas e função competitiva do jogador.

### HistoricoTime

Responsável por registrar transferências entre times.

---

# Relacionamentos

* Um Time possui vários Jogadores
* Um Jogador possui um PerfilCompetitivo
* Um Jogador pode possuir vários registros de HistoricoTime

---

# Funcionalidades

* Cadastro de jogadores
* Atualização de jogadores
* Exclusão de jogadores
* Busca por ID
* Busca com filtragem

---

# Estrutura do Projeto

<img width="446" height="274" alt="Imagem colada (5)" src="https://github.com/user-attachments/assets/c6dcbeaa-febf-4532-9828-4a8a62dd1d63" />

---

# Como Executar o Projeto

## Pré-requisitos

* Java 17
* Docker
* Docker Compose
* Maven

---

## Clonar o repositório

git clone https://github.com/edupasqualeto/api-scout-cs2.git

cd api-scout-cs2

## Inicializar

O projeto possui um script automatizado:

./builder.sh

Esse script:

* gera o build do projeto
* sobe o PostgreSQL com Docker
* inicia os containers necessários

---

# Endpoints Principais

## Jogadores

| Método | Endpoint          | Descrição                 |
| ------ | ---------------   | --------------------      |
| GET    | /jogadores        | Lista jogadores           |
| GET    | /jogadores/{id}   | Busca jogador por ID      |
| POST   | /jogadores        | Cria jogador              |
| GET    | /jogadores/buscar | Busca jogador com filtros |
| PUT    | /jogadores/{id}   | Atualiza jogador          |
| DELETE | /jogadores/{id}   | Remove jogador            |

---

## Times

| Método | Endpoint | Descrição   |
| ------ | -------- | ----------- |
| GET    | /times   | Lista times |
| POST   | /times   | Cria time   |

---

# Exemplo de Requisição

## Criar Jogador

<img width="665" height="281" alt="Imagem colada (2)" src="https://github.com/user-attachments/assets/8fcc1370-2721-4a86-9d11-78313b2cf49b" />

---

# Exemplo de busca por ID

<img width="665" height="281" alt="Imagem colada (3)" src="https://github.com/user-attachments/assets/2c52842b-c819-479c-ae8a-2ec872b266e6" />

---

# Exemplo de busca com filtros (funcao == AWPER)

<img width="734" height="616" alt="Imagem colada (4)" src="https://github.com/user-attachments/assets/3c7ceb76-04d4-480f-9437-24e14fc40766" />

---

# Validações

O projeto utiliza Jakarta Validation para garantir integridade dos dados.

## Exemplos

* nickname obrigatório
* nome completo obrigatório
* idade mínima de 12 anos
* elo não pode ser negativo

---

# Tratamento Global de Erros

A API possui um GlobalExceptionHandler responsável por:

* tratar erros de validação
* tratar recursos inexistentes
* padronizar respostas de erro
* retornar mensagens amigáveis

---

# Melhorias Futuras

* Swagger/OpenAPI
* autenticação JWT
* testes automatizados
* deploy em nuvem
* CI/CD
* cache
* observabilidade

---

# Conceitos Trabalhados

* API REST
* DTO Pattern
* Exception Handling
* Relacionamentos JPA
* Cascade e orphanRemoval
* Dockerização
* Arquitetura em camadas
* Validação de dados
* PostgreSQL
