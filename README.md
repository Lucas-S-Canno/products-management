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

E o swagger em `http://localhost:8080/api/swagger-ui.html` e para PROD 'http://44.200.65.36:8080/api/swagger-ui.html'.

## Testando a API com Postman

### Importando a Coleção do Postman

1. Abra o Postman.
2. Clique em `Arquivo` -> `Importar` no canto superior esquerdo ou `ctrl + O`.
3. Copie e cole o JSON que está no final do README na área de texto:
4. Clique em `Importar`.

### Fazendo Requisições

Agora você pode usar a coleção importada para fazer requisições à API. Aqui estão alguns exemplos de requisições:

- **Create New Product**: Envia uma requisição \`POST\` para \`http://localhost:8080/api/products` com os detalhes do produto no corpo.
- **Get All Products**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products` para recuperar todos os produtos.
- **Update Product**: Envia uma requisição \`PUT\` para \`http://localhost:8080/api/products/{id}` com os detalhes atualizados do produto no corpo.
- **Delete Product**: Envia uma requisição \`DELETE\` para \`http://localhost:8080/api/products/{id}` para deletar um produto específico.
- **Get by Name**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products/search?name={name}` para recuperar um produto pelo nome.
- **Get by Category**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products/search?category={category}` para recuperar um produto pela categoria.
- **Get by Min Price**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products/search?minPrice={minPrice}` para recuperar um produto pelo preço mínimo.
- **Get by Max Price**: Envia uma requisição \`GET\` para \`http://localhost:8080/api/products/search?maxPrice={maxPrice}` para recuperar um produto pelo preço máximo.

#### OBS: o endpoint "Get By" funciona com junções de filtros como: name e category, category com min e max price, min e max price, etc


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

## Dockerfile

O Dockerfile está localizado na raiz do projeto.

Criado para facilitar a execução da aplicação em um container Docker e fazer upload em um
Container Registry como o Amazon ECR e rodar a api dentro de um orquestrador de containeres
como Amazon ECS ou Amazon EKS.

### Construindo a Imagem

Para construir a imagem Docker, execute o seguinte comando:

```sh
docker build -t products-management .
```

### Rodando a Imagem

Para rodar a imagem Docker, execute o seguinte comando:

```sh
docker run -p 8080:8080 products-management
```

#### OBS: Importante alterar o usuario e senha do RabbitMQ no arquivo `application.propperties` para a aplicação conseguir se conectar a sua instancia do RabbitMQ.

## Segurança de Dados Sensiveis da Aplicação

Usuário e senha do RabbitMQ são dados sensiveis e não devem ser expostos no código fonte.

Portanto foi feito uma configuração para que esses dados sejam passados como variáveis de ambiente a partir do github secrets.

## Principios SOLID

Para garantir que o código seja escalável e fácil de manter.

Foi alterado o código do controller para que o mesmo possua o minimo possível de regras e lógicas a serem executadas, deixando isso a cargo do Service.

## Banco de dados

Para o banco de dados foi utilizado uma instancia MySQL 8.0 no Amazon RDS.

### Migration (FlyWay)

As migrations estão localizadas em `src/main/resources/db/migration`.

Tendo duas migrations, uma de criação da tabela `V1__create_table_products.sql` e uma de inserção de dados `V2__populate_products_table.sql`.

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
      "name": "Local",
      "item": [
        {
          "name": "get by filter",
          "item": [
            {
              "name": "Get by Name",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?name=Produto 1",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "name",
                      "value": "Produto 1"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Category",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?category=Categoria 2",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "category",
                      "value": "Categoria 2"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Min Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?minPrice=30.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "minPrice",
                      "value": "30.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Max Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?maxPrice=40.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "maxPrice",
                      "value": "40.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Min and Max Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?minPrice=30.99&maxPrice=40.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "minPrice",
                      "value": "30.99"
                    },
                    {
                      "key": "maxPrice",
                      "value": "40.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Name and Category",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?name=Produto 3&category=Categoria 3",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "name",
                      "value": "Produto 3"
                    },
                    {
                      "key": "category",
                      "value": "Categoria 3"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Name and Min Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?name=Produto 4&minPrice=40.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "name",
                      "value": "Produto 4"
                    },
                    {
                      "key": "minPrice",
                      "value": "40.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Category and Max Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?category=Categoria 5&maxPrice=50.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "category",
                      "value": "Categoria 5"
                    },
                    {
                      "key": "maxPrice",
                      "value": "50.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Name, Category and Min Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?name=Produto 1&category=Categoria 1&minPrice=10.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "name",
                      "value": "Produto 1"
                    },
                    {
                      "key": "category",
                      "value": "Categoria 1"
                    },
                    {
                      "key": "minPrice",
                      "value": "10.99"
                    }
                  ]
                }
              },
              "response": []
            },
            {
              "name": "Get by Name, Category, Min and Max Price",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "localhost:8080/api/products/search?name=Produto 2&category=Categoria 2&minPrice=20.99&maxPrice=20.99",
                  "host": [
                    "localhost"
                  ],
                  "port": "8080",
                  "path": [
                    "api",
                    "products",
                    "search"
                  ],
                  "query": [
                    {
                      "key": "name",
                      "value": "Produto 2"
                    },
                    {
                      "key": "category",
                      "value": "Categoria 2"
                    },
                    {
                      "key": "minPrice",
                      "value": "20.99"
                    },
                    {
                      "key": "maxPrice",
                      "value": "20.99"
                    }
                  ]
                }
              },
              "response": []
            }
          ]
        },
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
            "url": {
              "raw": "localhost:8080/api/products/2",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "products",
                "2"
              ]
            }
          },
          "response": []
        }
      ]
    }
  ]
}
```
