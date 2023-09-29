
# Sistema de atendimento

Um projeto desenvolvido para aplicação do bootcamp da Cielo junto com a Ada, onde tem o objetivo de ser possível cadastrar um cliente, e o mesmo entrar para fila de atendimento, e essa fila seguir a estrutura FIFO, ou seja, o primeiro a entrar, será o primeiro a sair.



## Documentação da API

#### Retorna todos os itens

```http
  GET /clientes
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `buscar`   | `string`    | O texto que deseja buscar, o default é vazio |
| `page`     | `number`    | O número da pagina                           |
| `size`     | `number`    | A quantidade de itens a serem retornados                       |

#### Retorna uma lista de registros, de acordo com os parametros enviados.

```http
  GET /clientes/${userId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `userId`      | `string` | **Obrigatório**. O ID do item que você quer |

#### Retorna apenas um registro referente ao ID enviado


```http
  POST /clientes/cadastroCliente
```

```python
{
  "clienteType": "string",
   "cnpj": "string",
   "razao_social":"string",
   "mcc":"string",
   "cpf_contato":"string",
   "nome_contato":"string",
   "email":"string",
   "cpf": "string",
   "nome_pessoa_fisica": "string"
}
 
```
##### Quando o cadastro for do tipo Pessoa Fisica, somente os campos (email, cpf, mcc, nome_pessoa_fisica) serão obrigatório e caso seja pessoa juridica, esses passam a não ser obrigatórios, porem todos os outros campos sim.

#### O retorno será uma mensagem no corpo da requisição

```http
  PUT /clientes/{userId}
```

```python
{
  "clienteType": "string",
   "cnpj": "string",
   "razao_social":"string",
   "mcc":"string",
   "cpf_contato":"string",
   "nome_contato":"string",
   "email":"string",
   "cpf": "string",
   "nome_pessoa_fisica": "string"
}
 
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `userId`      | `string` | **Obrigatório**. O ID do item que você quer alterar |

##### Segue o padrão no método POST, porém tendo que passar como parametro o User ID.

#### O retorno será uma mensagem no corpo da requisição

```http
  DELETE /clientes/${userId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `userId`      | `string` | **Obrigatório**. O ID do item que você quer |

#### Retorna sucesso caso o id seja encontrado, caso contrario retorna uma excessão.


```http
  GET /atendimento/listar
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|    |     |  |


#### Busca todos os registros que estão na fila de espera.

```http
  GET /atendimento/atenderProximo
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|    |     |  |


#### Faz o atendimento ao primeiro da fila e retira o mesmo.


```http
  GET /atendimento/listarAtendidos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
|    |     |  |


#### Lista todos que já foram atendidos.



## Referência

 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [Angular](https://angular.io/)
 
 

## Variáveis de Ambiente

para acessar o pgadmin será necessário configurar as variaveis abaixo no arquivo docker-compose que se encontra dentro da pasta BaseDados

`PGADMIN_DEFAULT_EMAIL`

`PGADMIN_DEFAULT_PASSWORD`


Caso alguma outra variavel seja alterada nesse arquivo, é preciso também verificar o arquivo do spring boot de propiedades para analise se não é utilizada no projeto da api.

Para rodar o banco basta acessar a pasta do arquivo com terminal e rodar comando abaixo:


`docker-compose up`


## Acessando  PhpMyAdmin e o Gerenciador de container

 PHPMYADMIN **http://seuip:8889**

 PORTAINER **http://seuip:9000**
 
 ##### A porta que a api está rodando é a 8087
