# CS2 Scout API

API REST desenvolvida com Spring Boot para gerenciamento de jogadores profissionais de Counter-Strike 2, incluindo times, perfis competitivos e histórico de transferências.

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

Responsável por registrar transferências e histórico entre times.

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

src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 └── exception
 
---

# Como Executar o Projeto

## Pré-requisitos

* Java 17
* Docker
* Docker Compose
* Maven

---

## Clonar o repositório

git clone https://github.com/SEU-USUARIO/api-scout-cs2.git

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

Arquivo json:
{
  "nickname": "KSCERATO",
  "nomeCompleto": "Kaike Cerato",
  "nacionalidade": "Brasil",
  "idade": 25,
  "hltvUrl": "https://www.hltv.org/player/15631/kscerato",
  "timeId": 1,
  "elo": 3250,
  "funcao": "RIFLER"
}

---

# Exemplo de Resposta

Arquivo json:
{
  "nickname": "KSCERATO",
  "nomeCompleto": "Kaike Cerato",
  "nacionalidade": "Brasil",
  "idade": 25,
  "hltvUrl": "https://www.hltv.org/player/15631/kscerato",
  "nomeTime": "FURIA",
  "elo": 3250,
  "funcao": "RIFLER"
}

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
