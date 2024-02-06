<h1 align="center" style="font-weight: bold;">Script Burger 🍔</h1>

<p align="center">
 <a href="#tech">Technologies</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
    <b>This is a simple API created for a fictitious food company specialized only in delivery.</b>
</p>

<h2 id="technologies">💻 Technologies</h2>

- Java
- Spring
- H2 Database

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

These are the requirements you will need to run the project:

- [OpenJDK 17+](https://jdk.java.net/archive/)
- A REST Client to run HTTP requests such as [Postman](https://www.postman.com/)

<h3>Cloning</h3>

```bash
git clone https://github.com/nicolasperussi/script-burger-server.git
```

<h3>Config .env variables</h2>

Use the `.env.example` as reference to create your configuration file `.env` with your JWT secret key.

```yaml
JWT_SECRET=
```

<h3>Starting</h3>

You may open the project using your IDE of choice and run it normally, or you can follow the steps below to run it directly from the terminal.

Make sure you have JAVA_HOME set up correctly in your system's Environment Variables.

```bash
cd script-burger-server
./mvnw spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>

These are the available endpoints in this API:
​

## User

| route                          | description                                            |
| ------------------------------ | ------------------------------------------------------ |
| <kbd>POST /auth/register</kbd> | registers a new user - [see details](#register-detail) |
| <kbd>POST /auth/login</kbd>    | authenticate a user - [see details](#login-detail)     |

<h3 id="register-detail">POST /auth/register</h3>

**REQUEST**

```jsonc
{
  "name": "John Doe",
  "email": "johndoe@gmail.com",
  "password": "test1234",
  "phone": "999999999"
}
```

<h3 id="login-detail">POST /auth/login</h3>

**REQUEST**

```jsonc
{
  "email": "johndoe@gmail.com",
  "password": "test1234"
}
```

**RESPONSE**

```jsonc
{
  // This is a JWT token signed with the
  // secret you provided on the .env file
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzY3JpcHRidXJnZXItYXBpIiwic3ViIjoiYWRtaW5AYWRtaW4uY29tIiwiZXhwIjoxNzA1NzEyNTI2fQ.BhBb3FiDnd3eF2L4purdjAN9WIk4zBC-Tommg5fz2xg",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@gmail.com",
    "phone": "999999999",
    "role": "CLIENT",
    "address": [{ "cep": "01001-000", "street": "Praça da Sé", "number": "0" }],
    "username": "johndoe@gmail.com"
  }
}
```

## Products

| route                        | description                                                    |
| ---------------------------- | -------------------------------------------------------------- |
| <kbd>GET /products</kbd>     | fetch all products - [see details](#get-products-detail)       |
| <kbd>GET /products/:id</kbd> | fetch product by id - [see details](#get-product-by-id-detail) |
| <kbd>POST /products</kbd>    | create new product - [see details](#create-product-detail)     |

<h3 id="get-products-detail">GET /products</h3>

**RESPONSE**

```jsonc
[
  {
    "id": 1,
    "name": "JavaScript Burger",
    "description": "O JavaScript Burger é feito com 100% de carne bovina, alface crocante, tomate fresco, cebola suavemente temperada, picles em conserva e queijo derretido. É uma combinação deliciosa e suculenta, perfeita para satisfazer o seu desejo por hambúrguer. ",
    "slug": "javascript-burger",
    "overview": "Hambúrguer favorito de todos os tempos",
    "price": 20.99,
    "category": "SANDWICH"
  }
  // and all the other products
]
```

<h3 id="get-product-by-id-detail">GET /products/7</h3>

```jsonc
{
  "id": 7,
  "name": "PHP Bacon Cheeseburger",
  "description": "O PHP Bacon Cheeseburger é feito com 100% de carne bovina, queijo cheddar cremoso e delicioso bacon crocante. Este hambúrguer clássico é perfeito para quem adora um sabor defumado e suculento.",
  "slug": "php-bacon-cheeseburger",
  "overview": "Hambúrguer clássico com bacon",
  "price": 23.99,
  "category": "SANDWICH"
}
```

<h3 id="create-product-detail">POST /products</h3>

**REQUEST**

This is a `multipart/form-data` request. These are the parameters:

```ts
name: string;
description: string;
overview: string;
price: number;
category: SANDWICH | SIDE | DESSERT | DRINK;
image: file;
```

## Orders

| route                               | description                                                            |
| ----------------------------------- | ---------------------------------------------------------------------- |
| <kbd>GET /orders</kbd>              | fetch all orders - [see details](#get-orders-detail)                   |
| <kbd>GET /orders/user/:id</kbd>     | fetch product by client id - [see details](#get-orders-by-user-detail) |
| <kbd>POST /orders</kbd>             | place new order - [see details](#create-order-detail)                  |
| <kbd>PATCH /orders/:id</kbd>        | advance order to next step - [see details](#advance-order-detail)      |
| <kbd>PATCH /orders/cancel/:id</kbd> | cancel order - [see details](#cancel-order-detail)                     |

<h3 id="get-orders-detail">GET /orders</h3>

**RESPONSE**

<details>
  <summary>Click here to show</summary>

```jsonc
[
  {
    "id": 1,
    "moment": "2024-01-19T03:22:18Z",
    "status": "WAITING",
    "client": {
      "id": 1,
      "name": "John Doe",
      "email": "johndoe@gmail.com",
      "phone": "999999999",
      "role": "CLIENT",
      "address": [
        { "cep": "01001-000", "street": "Praça da Sé", "number": "0" }
      ],
      "username": "johndoe@gmail.com"
    },
    "items": [
      {
        "quantity": 2,
        "totalPrice": 22.99,
        "product": {
          "id": 2,
          "name": "Python Burger",
          "description": "O Python Burger é feito com 100% de carne bovina, queijo cheddar derretido, cebolas crocantes e molho BBQ com sabor agridoce. Essa combinação de sabores é uma verdadeira explosão para o paladar. ",
          "slug": "python-burger",
          "overview": "Explosão de sabor agridoce de churrasco",
          "price": 22.99,
          "category": "SANDWICH"
        }
      }
    ],
    "totalPrice": 45.98
  }
  // and other orders
]
```

</details>

<h3 id="get-orders-by-user-detail">GET /orders/user/1</h3>

**RESPONSE**

<details>
  <summary>Click here to show</summary>

```jsonc
[
  {
    "id": 1,
    "moment": "2024-01-19T03:22:18Z",
    "status": "WAITING",
    "items": [
      {
        "quantity": 2,
        "totalPrice": 22.99,
        "product": {
          "id": 2,
          "name": "Python Burger",
          "description": "O Python Burger é feito com 100% de carne bovina, queijo cheddar derretido, cebolas crocantes e molho BBQ com sabor agridoce. Essa combinação de sabores é uma verdadeira explosão para o paladar. ",
          "slug": "python-burger",
          "overview": "Explosão de sabor agridoce de churrasco",
          "price": 22.99,
          "category": "SANDWICH"
        }
      }
    ],
    "totalPrice": 45.98
  }
  // and other orders
]
```

</details>

<h3 id="create-order-detail">POST /orders</h3>

**REQUEST**

```jsonc
{
  "userId": 1,
  "items": [
    {
      "productId": 7,
      "quantity": 1
    },
    {
      "productId": 2,
      "quantity": 1
    },
    {
      "productId": 24,
      "quantity": 2
    }
  ]
}
```

<h3 id="advance-order-detail">PATCH /orders/1</h3>

**RESPONSE**

<details>
  <summary>Click here to show</summary>

```jsonc
{
  "id": 1,
  "moment": "2024-01-19T03:22:18Z",
  "status": "IN_PRODUCTION", // this will be update to next step
  "client": {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@gmail.com",
    "phone": "999999999",
    "role": "CLIENT",
    "address": [{ "cep": "01001-000", "street": "Praça da Sé", "number": "0" }],
    "username": "johndoe@gmail.com"
  },
  "items": [
    {
      "quantity": 2,
      "totalPrice": 22.99,
      "product": {
        "id": 2,
        "name": "Python Burger",
        "description": "O Python Burger é feito com 100% de carne bovina, queijo cheddar derretido, cebolas crocantes e molho BBQ com sabor agridoce. Essa combinação de sabores é uma verdadeira explosão para o paladar. ",
        "slug": "python-burger",
        "overview": "Explosão de sabor agridoce de churrasco",
        "price": 22.99,
        "category": "SANDWICH"
      }
    }
  ],
  "totalPrice": 45.98
}
```

</details>

<h3 id="cancel-order-detail">PATCH /orders/cancel/1</h3>

**RESPONSE**

<details>
  <summary>Click here to show</summary>

```jsonc
{
  "id": 1,
  "moment": "2024-01-19T03:22:18Z",
  "status": "CANCELED", // this will be changed to CANCELED
  "client": {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@gmail.com",
    "phone": "999999999",
    "role": "CLIENT",
    "address": [{ "cep": "01001-000", "street": "Praça da Sé", "number": "0" }],
    "username": "johndoe@gmail.com"
  },
  "items": [
    {
      "quantity": 2,
      "totalPrice": 22.99,
      "product": {
        "id": 2,
        "name": "Python Burger",
        "description": "O Python Burger é feito com 100% de carne bovina, queijo cheddar derretido, cebolas crocantes e molho BBQ com sabor agridoce. Essa combinação de sabores é uma verdadeira explosão para o paladar. ",
        "slug": "python-burger",
        "overview": "Explosão de sabor agridoce de churrasco",
        "price": 22.99,
        "category": "SANDWICH"
      }
    }
  ],
  "totalPrice": 45.98
}
```

</details>

<h2 id="contribute">📫 Contribute</h2>

Would like to contribute? Please do so following these steps:

1. `git clone https://github.com/nicolasperussi/script-burger-server.git`
2. `git checkout -b feature/NAME`
3. Follow commit patterns
4. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
