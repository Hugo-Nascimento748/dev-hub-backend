# 🚀 DevResources Hub - API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

> **Status do Projeto:** ![Build Status](https://github.com/Hugo-Nascimento748/dev-hub-backend/actions/workflows/ci.yml/badge.svg) ✅ (Finalizado)

## 📌 Sobre o Projeto
O **DevResources Hub** é uma plataforma de curadoria de recursos para desenvolvedores. A API permite que usuários compartilhem links úteis (artigos, cursos, ferramentas), que são automaticamente processados para extração de metadados. O sistema conta com um motor de votação e organização por tags, focado em performance e escalabilidade.

## ✨ Funcionalidades Principais
- **Scraping Automático:** Ao enviar uma URL, o sistema usa **Jsoup** para extrair automaticamente o título e a descrição do site.
- **Sistema de Votação (Toggle):** Lógica inteligente de Upvote/Downvote com proteção de integridade (um voto por usuário).
- **Gerenciamento de Tags:** Organização dinâmica de recursos com relacionamentos *Many-to-Many*.
- **Cache de Alta Performance:** Uso de **Redis** para acelerar a listagem dos recursos mais acessados.
- **Login Social:** Autenticação segura via **OAuth2 com GitHub**.
- **Documentação Interativa:** Interface completa via **Swagger/OpenAPI 3**.

## 🏗️ Arquitetura e Engenharia
O projeto foi desenvolvido seguindo princípios de **Clean Architecture / Hexagonal**, garantindo desacoplamento entre a lógica de negócio e os provedores de infraestrutura (Banco, Cache, Web).

### Diferenciais Técnicos:
- **Serialização JSON Customizada:** Implementação de suporte a JavaTime e Hibernate Lazy Loading no Redis.
- **Dockerização Multi-stage:** Dockerfile otimizado para gerar imagens leves de produção (JRE Alpine).
- **CI/CD:** Pipeline automatizado com GitHub Actions para execução de testes unitários a cada push.
- **Tratamento Global de Exceções:** Padronização de erros da API seguindo as melhores práticas REST.

## 🛠️ Tecnologias Utilizadas
- **Java 17 / Spring Boot 3**
- **Spring Data JPA & Hibernate**
- **Spring Security & OAuth2 Client**
- **Redis & Spring Cache**
- **PostgreSQL**
- **Jsoup (Web Scraping)**
- **Lombok**
- **JUnit 5 & Mockito (Testes Unitários)**
- **Docker & Docker Compose**

---

# 🚀 Como Rodar o Projeto

## Pré-requisitos

- Docker
- Docker Compose

## 1️⃣ Clone o repositório

```bash
git clone https://github.com/Hugo-Nascimento748/dev-hub-backend.git
```

## 2️⃣ Configure as credenciais OAuth2

Adicione o **Client ID** e o **Client Secret** do GitHub no arquivo:

```text
application.yml
```

Ou configure-os através de variáveis de ambiente.

## 3️⃣ Inicie os containers

Na raiz do projeto execute:

```bash
docker-compose up -d --build
```

## 4️⃣ Acesse a aplicação

```
http://localhost:8081
```

---

# 📄 Documentação da API (Swagger)

A API utiliza **Swagger UI (OpenAPI 3)** para disponibilizar uma documentação completa e interativa.

### Acesse:

```
http://localhost:8081/swagger-ui/index.html
```

## O que é possível fazer no Swagger?

- 📌 Visualizar todos os endpoints.
- 🧪 Testar requisições diretamente pelo navegador.
- 📚 Consultar os DTOs e Schemas utilizados.
- ✔️ Validar campos obrigatórios.
- 👍 Testar o sistema de votação autenticado via GitHub.

---

# 👨‍💻 Autor

**Seu Nome**

🔗 LinkedIn: https://www.linkedin.com/in/hugo-nascimento-gonçalves-973b29362/

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**.
