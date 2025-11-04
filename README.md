# ☕ Cafeteria API - Sistema de Gestão

Este projeto é uma API REST para gerenciamento de uma cafeteria, construída com Spring Boot seguindo os padrões de Clean Architecture.

## 🚀 Funcionalidades Implementadas

### Backend (API REST)
- ✅ **Gestão de Usuários**: CRUD completo com sistema de status (ATIVO, BLOQUEADO, EXCLUIDO)
- ✅ **Gestão de Clientes**: Herança de Usuario com campos específicos
- ✅ **Gestão de Administradores**: Herança de Usuario com controle de acesso
- ✅ **Gestão de Produtos**: CRUD para cardápio da cafeteria
- ✅ **Autenticação JWT**: Sistema de login com tokens
- ✅ **Documentação Swagger**: API documentada automaticamente
- ✅ **Sistema de Notificações**: Email e SMS

### Frontend (Interface Web)
- ✅ **Dashboard Principal**: Página inicial com navegação
- ✅ **Gestão de Usuários**: Interface para listar, criar, bloquear/desbloquear
- ✅ **Gestão de Produtos**: Interface para gerenciar cardápio
- ✅ **Design Responsivo**: Interface moderna e intuitiva

## 🏗️ Arquitetura

O projeto segue os princípios de **Clean Architecture**:

```
src/main/java/com/senac/aula_api/
├── application/           # Camada de Aplicação
│   ├── dto/              # Data Transfer Objects
│   └── services/         # Serviços de aplicação
├── domain/               # Camada de Domínio
│   ├── entities/         # Entidades de negócio
│   ├── interfaces/       # Interfaces do domínio
│   ├── repository/       # Interfaces de repositório
│   └── valueobjects/     # Objetos de valor
├── infra/                # Camada de Infraestrutura
│   ├── config/           # Configurações
│   └── external/         # Integrações externas
└── presentation/         # Camada de Apresentação
    └── controllers/      # Controllers REST
```

## 🛠️ Tecnologias Utilizadas

- **Java 23**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **JWT (Auth0)**
- **Swagger/OpenAPI**
- **Maven**
- **HTML5/CSS3/JavaScript**

## 📋 Pré-requisitos

- Java 23 ou superior
- Maven 3.6+
- PostgreSQL 12+
- Navegador web moderno

## 🚀 Como Executar

### 1. Configurar Banco de Dados
```sql
CREATE DATABASE db_cafeteria;
```

### 2. Configurar application.properties
```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5434/db_cafeteria
spring.datasource.username=postgres
spring.datasource.password=Sen@c2023
```

### 3. Executar a Aplicação
```bash
# Compilar e executar
mvn spring-boot:run

# Ou executar o JAR
mvn clean package
java -jar target/aula_api-0.0.1-SNAPSHOT.jar
```

### 4. Acessar a Interface Web
- **Dashboard**: `http://localhost:8080/Front/index.html`
- **Swagger**: `http://localhost:8080/swagger-ui.html`
- **API Base**: `http://localhost:8080`

## 📱 Interface Web

### Páginas Disponíveis
- **`index.html`**: Dashboard principal com navegação
- **`HomeUsuario.html`**: Lista e gerencia usuários/clientes/administradores
- **`SalvarUsuario.html`**: Formulário para criar novos usuários
- **`HomeProdutos.html`**: Lista e gerencia produtos do cardápio
- **`SalvarProduto.html`**: Formulário para criar novos produtos

### Funcionalidades da Interface
- ✅ Listagem com filtros por tipo de usuário
- ✅ Criação de usuários com campos específicos por tipo
- ✅ Bloqueio/desbloqueio de usuários
- ✅ Gestão completa de produtos
- ✅ Design responsivo e moderno
- ✅ Feedback visual de operações

## 🔗 Endpoints Principais

### Usuários
- `GET /usuarios` - Listar todos os usuários
- `POST /usuarios` - Criar usuário
- `GET /usuarios/{id}` - Buscar usuário por ID
- `PUT /usuarios/{id}` - Atualizar usuário
- `DELETE /usuarios/{id}` - Excluir usuário (soft delete)
- `PATCH /usuarios/{id}/bloquear` - Bloquear usuário
- `PATCH /usuarios/{id}/desbloquear` - Desbloquear usuário

### Clientes
- `GET /clientes` - Listar clientes
- `POST /clientes` - Criar cliente
- `PATCH /clientes/{id}/bloquear` - Bloquear cliente
- `PATCH /clientes/{id}/desbloquear` - Desbloquear cliente

### Administradores
- `GET /administradores` - Listar administradores
- `POST /administradores` - Criar administrador
- `PATCH /administradores/{id}/bloquear` - Bloquear administrador
- `PATCH /administradores/{id}/desbloquear` - Desbloquear administrador

### Produtos
- `GET /produtos` - Listar produtos
- `POST /produtos` - Criar produto
- `PUT /produtos/{id}` - Atualizar produto
- `DELETE /produtos/{id}` - Excluir produto

## 🔐 Autenticação

### Login
```bash
POST /auth/login
{
  "email": "string",
  "senha": "string"
}
```

### Resposta
```json
{
  "token": "jwt-token-here"
}
```

## 📊 Sistema de Status

Todas as entidades de usuário possuem um sistema de status:

- **ATIVO**: Usuário ativo e pode usar o sistema
- **BLOQUEADO**: Usuário bloqueado temporariamente
- **EXCLUIDO**: Usuário excluído (soft delete)

## 🎨 Melhorias Implementadas

### Comparado ao Projeto Original
- ✅ **Interface Web Completa**: Páginas HTML modernas e funcionais
- ✅ **Sistema de Status**: Controle de estado dos usuários
- ✅ **Clean Architecture**: Estrutura organizada e escalável
- ✅ **Documentação Swagger**: API totalmente documentada
- ✅ **Design Responsivo**: Interface adaptável a diferentes telas
- ✅ **Feedback Visual**: Mensagens de sucesso/erro nas operações
- ✅ **Navegação Intuitiva**: Dashboard com acesso fácil a todas as funcionalidades

## 🔧 Configurações Adicionais

### JWT
```properties
jwt.secret=minha-palavra-secreta-super-segura-para-jwt-token
jwt.issuer=aula-api
```

### CORS
Configurado para permitir acesso de qualquer origem durante desenvolvimento.

## 📝 Próximos Passos

- [ ] Implementar testes unitários
- [ ] Adicionar validações mais robustas
- [ ] Implementar hash de senhas
- [ ] Adicionar sistema de logs
- [ ] Implementar cache
- [ ] Adicionar mais funcionalidades de relatórios

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

**Desenvolvido com ❤️ para o curso de Jovem Programador - Senac**




