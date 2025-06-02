# API de Filmes

Esta é uma API RESTful desenvolvida em Spring Boot para gerenciamento de filmes, avaliações e favoritos.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- MongoDB
- Spring Security
- JWT para autenticação
- Maven

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:
- Controller: Endpoints da API
- Service: Lógica de negócio
- Repository: Acesso ao banco de dados
- Model: Entidades do sistema
- Config: Configurações do Spring Security e JWT

## Endpoints da API

### Autenticação

#### POST /api/auth/registrar
Registra um novo usuário.

**Payload:**
```json
{
    "nome": "string",
    "email": "string",
    "senha": "string"
}
```

**Resposta (200 OK):**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Erros:**
- 400 Bad Request: Email já cadastrado

#### POST /api/auth/login
Realiza login do usuário.

**Payload:**
```json
{
    "email": "string",
    "senha": "string"
}
```

**Resposta (200 OK):**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Erros:**
- 400 Bad Request: Usuário não encontrado
- 400 Bad Request: Senha incorreta

### Filmes

#### GET /api/filmes
Lista todos os filmes.

**Resposta (200 OK):**
```json
[
    {
        "id": "68373c4fee8c89404745ad03",
        "titulo": "O Poderoso Chefão",
        "imagemUrl": "https://exemplo.com/imagens/poderoso-chefao.jpg",
        "ano": 1972,
        "generos": ["Drama", "Crime"],
        "sinopse": "Uma família mafiosa luta para estabelecer sua supremacia..."
    }
]
```

#### GET /api/filmes/{id}
Busca um filme específico por ID.

**Resposta (200 OK):**
```json
{
    "id": "68373c4fee8c89404745ad03",
    "titulo": "O Poderoso Chefão",
    "imagemUrl": "https://exemplo.com/imagens/poderoso-chefao.jpg",
    "ano": 1972,
    "generos": ["Drama", "Crime"],
    "sinopse": "Uma família mafiosa luta para estabelecer sua supremacia..."
}
```

**Erros:**
- 404 Not Found: Filme não encontrado

#### GET /api/filmes/search?titulo={titulo}
Busca filmes por título.

**Resposta (200 OK):**
```json
[
    {
        "id": "68373c4fee8c89404745ad03",
        "titulo": "O Poderoso Chefão",
        "imagemUrl": "https://exemplo.com/imagens/poderoso-chefao.jpg",
        "ano": 1972,
        "generos": ["Drama", "Crime"],
        "sinopse": "Uma família mafiosa luta para estabelecer sua supremacia..."
    }
]
```

#### GET /api/filmes/genero?genero={genero}
Filtra filmes por gênero.

**Resposta (200 OK):**
```json
[
    {
        "id": "68373c4fee8c89404745ad03",
        "titulo": "O Poderoso Chefão",
        "imagemUrl": "https://exemplo.com/imagens/poderoso-chefao.jpg",
        "ano": 1972,
        "generos": ["Drama", "Crime"],
        "sinopse": "Uma família mafiosa luta para estabelecer sua supremacia..."
    }
]
```

#### POST /api/filmes
Cria um novo filme (requer role ADMIN).

**Payload:**
```json
{
    "titulo": "string",
    "imagemUrl": "string",
    "ano": "integer",
    "generos": ["string"],
    "sinopse": "string"
}
```

**Resposta (201 Created):**
```json
{
    "id": "68373c4fee8c89404745ad03",
    "titulo": "Novo Filme",
    "imagemUrl": "https://exemplo.com/imagem.jpg",
    "ano": 2024,
    "generos": ["Ação", "Aventura"],
    "sinopse": "Sinopse do filme"
}
```

**Erros:**
- 403 Forbidden: Usuário não tem permissão de admin


#### DELETE /api/filmes/{id}
Remove um filme (requer role ADMIN).

**Resposta (204 No Content)**

**Erros:**
- 403 Forbidden: Usuário não tem permissão de admin
- 404 Not Found: Filme não encontrado

### Avaliações

#### GET /api/avaliacoes/{filmeId}
Lista todas as avaliações de um filme.

**Resposta (200 OK):**
```json
[
    {
        "id": "68373c4fee8c89404745ad03",
        "filmeId": "68373c4fee8c89404745ad03",
        "autor": "João Silva",
        "nota": 5,
        "comentario": "Ótimo filme!"
    }
]
```

#### POST /api/avaliacoes/{filmeId}
Adiciona uma nova avaliação (requer autenticação).

**Payload:**
```json
{
    "nota": "integer (1-5)",
    "comentario": "string"
}
```

**Resposta (201 Created):**
```json
{
    "id": "68373c4fee8c89404745ad03",
    "filmeId": "68373c4fee8c89404745ad03",
    "autor": "João Silva",
    "nota": 5,
    "comentario": "Ótimo filme!"
}
```

**Erros:**
- 401 Unauthorized: Usuário não autenticado
- 400 Bad Request: Nota inválida (deve estar entre 1 e 5)
- 404 Not Found: Filme não encontrado

### Favoritos

#### POST /api/favoritos/{filmeId}
Marca um filme como favorito (requer autenticação).

**Resposta (200 OK)**

**Erros:**
- 401 Unauthorized: Usuário não autenticado
- 404 Not Found: Filme não encontrado

#### DELETE /api/favoritos/{filmeId}
Remove um filme dos favoritos (requer autenticação).

**Resposta (204 No Content)**

**Erros:**
- 401 Unauthorized: Usuário não autenticado
- 404 Not Found: Filme não encontrado

#### GET /api/favoritos
Lista todos os filmes favoritos do usuário (requer autenticação).

**Resposta (200 OK):**
```json
[
    "68373c4fee8c89404745ad03",
    "68373c4fee8c89404745ad04"
]
```

**Erros:**
- 401 Unauthorized: Usuário não autenticado

#### GET /api/favoritos/{filmeId}
Verifica se um filme está nos favoritos (requer autenticação).

**Resposta (200 OK):**
```json
true
```

**Erros:**
- 401 Unauthorized: Usuário não autenticado
- 404 Not Found: Filme não encontrado

## Testes das Rotas

### Autenticação

#### Teste de Registro
```bash
curl -X POST http://localhost:8080/api/auth/registrar \
-H "Content-Type: application/json" \
-d '{
    "nome": "Teste Usuario",
    "email": "teste@email.com",
    "senha": "senha123"
}'
```

#### Teste de Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
    "email": "teste@email.com",
    "senha": "senha123"
}'
```

### Filmes

#### Teste Listar Todos
```bash
curl -X GET http://localhost:8080/api/filmes
```

#### Teste Buscar por ID
```bash
curl -X GET http://localhost:8080/api/filmes/68373c4fee8c89404745ad03
```

#### Teste Buscar por Título
```bash
curl -X GET "http://localhost:8080/api/filmes/search?titulo=Avengers"
```

#### Teste Filtrar por Gênero
```bash
curl -X GET "http://localhost:8080/api/filmes/genero?genero=Ação"
```

#### Teste Criar Filme (requer token de admin)
```bash
curl -X POST http://localhost:8080/api/filmes \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {seu_token_jwt}" \
-d '{
    "titulo": "Novo Filme",
    "imagemUrl": "http://exemplo.com/imagem.jpg",
    "ano": 2024,
    "generos": ["Ação", "Aventura"],
    "sinopse": "Sinopse do filme"
}'
```

#### Teste Deletar Filme (requer token de admin)
```bash
curl -X DELETE http://localhost:8080/api/filmes/68373c4fee8c89404745ad03 \
-H "Authorization: Bearer {seu_token_jwt}"
```

### Avaliações

#### Teste Listar Avaliações
```bash
curl -X GET http://localhost:8080/api/avaliacoes/68373c4fee8c89404745ad03
```

#### Teste Adicionar Avaliação
```bash
curl -X POST http://localhost:8080/api/avaliacoes/68373c4fee8c89404745ad03 \
-H "Content-Type: application/json" \
-H "Authorization: Bearer {seu_token_jwt}" \
-d '{
    "nota": 5,
    "comentario": "Ótimo filme!"
}'
```

### Favoritos

#### Teste Marcar Favorito
```bash
curl -X POST http://localhost:8080/api/favoritos/68373c4fee8c89404745ad03 \
-H "Authorization: Bearer {seu_token_jwt}"
```

#### Teste Desmarcar Favorito
```bash
curl -X DELETE http://localhost:8080/api/favoritos/68373c4fee8c89404745ad03 \
-H "Authorization: Bearer {seu_token_jwt}"
```

#### Teste Listar Favoritos
```bash
curl -X GET http://localhost:8080/api/favoritos \
-H "Authorization: Bearer {seu_token_jwt}"
```

#### Teste Verificar Favorito
```bash
curl -X GET http://localhost:8080/api/favoritos/68373c4fee8c89404745ad03 \
-H "Authorization: Bearer {seu_token_jwt}"
```

### Observações sobre os Testes

1. Substitua `{seu_token_jwt}` pelo token recebido após login
2. Substitua os IDs de exemplo pelos IDs reais dos filmes
3. Para testes de admin, use um token de usuário com role ADMIN
4. Todos os endpoints que requerem autenticação precisam do header `Authorization: Bearer {token}`
5. Os testes podem ser executados via Postman, Insomnia ou qualquer cliente HTTP
6. Para testes locais, a API deve estar rodando em `http://localhost:8080`

## Segurança

- Autenticação via JWT
- Senhas criptografadas com BCrypt
- Roles: USER e ADMIN
- Endpoints protegidos por autenticação
- CORS habilitado para todas as origens

## Modelos de Dados

### Usuario
```json
{
    "id": "string",
    "nome": "string",
    "email": "string",
    "senha": "string (criptografada)",
    "isAdmin": "integer (0 ou 1)"
}
```

### Filme
```json
{
    "id": "string",
    "titulo": "string",
    "imagemUrl": "string",
    "ano": "integer",
    "generos": ["string"],
    "sinopse": "string"
}
```

### Avaliacao
```json
{
    "id": "string",
    "filmeId": "string",
    "autor": "string",
    "nota": "integer (1-5)",
    "comentario": "string"
}
```

### Favorito
```json
{
    "id": "string",
    "userId": "string",
    "filmeId": "string"
}
```

## Como Executar

1. Certifique-se de ter o Java 21 e Maven instalados
2. Configure o MongoDB localmente ou atualize a URL de conexão no `application.properties`
3. Execute o comando:
```bash
mvn spring-boot:run
```

## Testes

Para executar os testes:
```bash
mvn test
```

## Observações

- A API utiliza MongoDB como banco de dados
- Todas as senhas são criptografadas antes de serem armazenadas
- Tokens JWT expiram em 24 horas
- Endpoints de filmes são públicos, mas criação e deleção requerem role ADMIN
- Endpoints de avaliações e favoritos requerem autenticação 