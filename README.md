# Products Management API

Esta API foi desenvolvida para gerenciar uma lista de produtos. Ela permite criar, listar, atualizar e deletar produtos.

## Pré-requisitos

- Java 17
- Gradle 7.0
- Git
- Docker

## Primeiros Passos

### Clonando o Repositório

Para clonar o repositório, execute o seguinte comando:

```sh
git clone https://github.com/Lucas-S-Canno/products-management.git
cd products-management
```

### Construindo o Projeto

Para construir o projeto, execute o seguinte comando:

```sh
./gradlew build
```

### Executando a Aplicação

Para executar a aplicação, use o seguinte comando:

```sh
./gradlew bootRun
```

A aplicação será iniciada em `http://localhost:8080/api`.

## Testando a API com Postman

### Importando a Coleção do Postman

1. Abra o Postman.
2. Clique em `Arquivo` -> `Importar` no canto superior esquerdo ou `ctrl + O`.
3. Copie e cole o seguinte JSON na área de texto:
4. Clique em `Importar`.

```json
{
  "info": {
    "_postman_id": "43d926bb-d8af-448e-b6c3-26583fa2abdd",
    "name": "Product Management API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "13687818"
  },
  "item": [
    {
      "name": "Get All Products",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "localhost:8080/api/products",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "products"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Create New Product",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"name\": \"Laptop\",\r\n  \"description\": \"High-end gaming laptop\",\r\n  \"price\": 1500.00,\r\n  \"quantity\": 10,\r\n  \"category\": \"Electronics\"\r\n}\r\n\r\n// {\r\n//   \"name\": \"Smartphone\",\r\n//   \"description\": \"Latest model smartphone\",\r\n//   \"price\": 800.00,\r\n//   \"quantity\": 25,\r\n//   \"category\": \"Electronics\"\r\n// }\r\n\r\n// {\r\n//   \"name\": \"Headphones\",\r\n//   \"description\": \"Noise-cancelling headphones\",\r\n//   \"price\": 200.00,\r\n//   \"quantity\": 50,\r\n//   \"category\": \"Accessories\"\r\n// }",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "localhost:8080/api/products",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "products"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Update Product",
      "request": {
        "method": "PUT",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"name\": \"Laptop Gamer\",\r\n  \"description\": \"High-end gaming laptop\",\r\n  \"price\": 2500.00,\r\n  \"quantity\": 10,\r\n  \"category\": \"Electronics\"\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "localhost:8080/api/products/1",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "products",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Delete Product",
      "request": {
        "method": "DELETE",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"name\": \"Laptop Gamer\",\r\n  \"description\": \"High-end gaming laptop\",\r\n  \"price\": 2500.00,\r\n  \"quantity\": 10,\r\n  \"category\": \"Electronics\"\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "localhost:8080/api/products/1",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "products",
            "1"
          ]
        }
      },
      "response": []
    }
  ]
}
```

### Fazendo Requisições

Agora você pode usar a coleção importada para fazer requisições à API. Aqui estão alguns exemplos de requisições:

- **Create New Product**: Envia uma requisição \`POST\` para \`http://localhost:8080/api/products` com os detalhes do produto no corpo.
- **Get All Products**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products` para recuperar todos os produtos.
- **Update Product**: Envia uma requisição \`PUT\` para \`http://localhost:8080/api/products/{id}` com os detalhes atualizados do produto no corpo.
- **Delete Product**: Envia uma requisição \`DELETE\` para \`http://localhost:8080/api/products/{id}` para deletar um produto específico.

## Endpoints

- `POST /api/products`: Cria um novo produto.
- `GET /api/products`: Recupera todos os produtos.
- `PUT /api/products/{id}`: Atualiza um produto existente.
- `DELETE /api/products/{id}`: Deleta um produto.

## Testes Unitários

Os teste unitários estão localizados em `src/test/java/com/lucas/canno/productsmanagement`.

Para executar os testes, use o seguinte comando:

```sh
./gradlew test
```

### Github Actions

Os testes são executados automaticamente no Github Actions a cada push ou pull request.

A pipeline está localizada em `.github/workflows/ci.yml`.

## RabbitMQ

As configurações do RabbitMQ estão localizadas em `src/main/resources/application.yml`.

Para iniciar o RabbitMQ, execute o seguinte comando:

```sh
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Certifique-se de que o RabbitMQ está em execução.

Acessar o RabbitMQ Management Console: `http://localhost:15672/`.

- **Username**: guest
- **Password**: guest

### Testado a Fila

Execute a aplicação e envie uma requisição `POST` para `http://localhost:8080/api/products`.

Observe no console que a mensagem foi enviada para a fila e nos logs o produto enviado.
