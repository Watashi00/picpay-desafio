# PicPay - Backend Challenge

Este projeto é uma implementação de backend e frontend de demonstração inspirada no fluxo do **PicPay**, criada como desafio de desenvolvimento backend usando **Spring Boot** e **Java**.

O projeto permite criar usuários, fazer depósitos e enviar pagamentos via Pix de forma simplificada.

---

## Tecnologias utilizadas

- **Backend**
  - Java 21
  - Spring Boot 3
  - Spring Data JPA
  - H2 Database (em memória)
  - Spring Cache
  - Maven

- **Frontend**
  - HTML, CSS e JavaScript puro
  - Layout responsivo inspirado no PicPay
  - Gerenciamento de sessões simples usando `localStorage`

---

## Funcionalidades

- **Cadastro de usuários**
  - Nome, sobrenome, email, senha, documento e Pix Key
  - Diferenciação de conta comum e empresa (enterprise)

- **Depósitos**
  - Adição de saldo na conta do usuário via formulário

- **Pagamentos via Pix**
  - Envio de valores de um usuário para outro utilizando Pix

- **Dashboard**
  - Visualização do saldo atual
  - Histórico simplificado de logs
  - Navegação entre telas de cadastro, depósito e pagamento
  - Login simples usando email no `localStorage` (para futuras melhorias com JWT)

---

## Estrutura do projeto

```

picpay-desafio-backend/
├── src/main/java/com/linkedin/picpay
│   ├── controllers/
│   │   └── UserController.java
│   ├── services/
│   │   └── UserService.java
│   ├── schemas/
│   │   └── User.java
│   └── paymentservice/DTO/PaymentDTO.java
├── src/main/resources/
│   └── application.properties
├── src/main/resources/static/
│   └── index.html (frontend demo)
├── pom.xml
└── README.md

````

---

## Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/Watashi00/picpay-desafio.git
cd picpay-desafio
````

2. Compile e rode com Maven:

```bash
mvn clean spring-boot:run
```

3. Acesse o frontend via navegador:

```
http://localhost:8080/index.html
```

---

## Observações

* O projeto utiliza **H2 Database em memória**, então todos os dados serão resetados ao reiniciar o servidor.
* O login atualmente é **simples** (apenas email salvo no `localStorage`). Pode ser aprimorado com autenticação JWT.
* Frontend serve apenas como **prova de conceito** do fluxo de cadastro, depósito e pagamento.

---

## Possiveis melhorias

* Implementar autenticação com JWT
* Persistir dados em banco real (PostgreSQL, MySQL)
* Dashboard com histórico completo de transações
* Melhorar o layout para ficar idêntico ao app oficial do PicPay
* Testes unitários e de integração

