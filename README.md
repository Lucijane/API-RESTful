Desafio Java Spring Boot com PostgreSQL
Descrição
O desafio consiste em criar uma API RESTful utilizando Java Spring Boot
com uma base de dados PostgreSQL. A API deve permitir a criação,
atualização e consulta de pedidos. Especificamente, deve possuir os
seguintes endpoints:
POST para criação de um pedido.
PATCH para atualização do status de um pedido.
GET para consulta de um pedido por ID.
GET para listar pedidos com possibilidade de filtragem por status.
Requisitos
Tecnologias:
Java 11 ou superior
Spring Boot
Spring Data JPA
PostgreSQL
Hibernate
Maven ou Gradle
Banco de Dados:
PostgreSQL
Tabela pedido com as colunas:
id (UUID, primary key)
descricao (String)
status (String)
dataCriacao (Timestamp)
Endpoints e Contratos
1. Criação de Pedido
Endpoint: POST /pedidos
Request Body:
json
{
"descricao": "Descrição do pedido"
}
Response Body:
json
{
"id": "UUID do pedido",
"descricao": "Descrição do pedido",
"status": "CRIADO",
"dataCriacao": "Timestamp da criação"
}
2. Atualização do Status do Pedido
Endpoint: PATCH /pedidos/{id}
Request Body:
json
{
"status": "NOVO_STATUS"
}
Response Body:
json
{
"id": "UUID do pedido",
"descricao": "Descrição do pedido",
"status": "NOVO_STATUS",
"dataCriacao": "Timestamp da criação"
}
3. Consulta de Pedido por ID
Endpoint: GET /pedidos/{id}
Response Body:
json
{
"id": "UUID do pedido",
"descricao": "Descrição do pedido",
"status": "Status atual",
"dataCriacao": "Timestamp da criação"
}
4. Lista de Pedidos com Filtro por Status
Endpoint: GET /pedidos
Query Parameters:
status (opcional, filtra pedidos pelo status)
Response Body:
json
[
{
"id": "UUID do pedido 1",
"descricao": "Descrição do pedido 1",
"status": "Status do pedido 1",
"dataCriacao": "Timestamp da criação"
},
{
"id": "UUID do pedido 2",
"descricao": "Descrição do pedido 2",
"status": "Status do pedido 2",
"dataCriacao": "Timestamp da criação"
}
]
