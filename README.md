# Status

| Data | Autor | Comentários | Versão |
| --- | --- | --- | --- |
| 07/05/2019 | Paulo Uechi | Analise de Requisitos | 0.0.1-SNAPSHOT |  
| 08/05/2019 | Paulo Uechi | Inicio do desenvolvimento | 0.0.1-SNAPSHOT |  
| 09/05/2019 | Paulo Uechi | Disponibilização inicial, projeto em termino de desenvolvimento. Pendente Testes e Ambiente | 0.0.1-SNAPSHOT |  
| 10/05/2019 | Paulo Uechi | Previsão de conclusão | 0.0.1-SNAPSHOT |  


# Show me the code

### # DESAFIO:

API REST para Gestão de Gastos!

*Item 1/7*

```
Funcionalidade: Integração de gastos por cartão
  Apenas sistemas credenciados poderão incluir novos gastos
  É esperado um volume de 100.000 inclusões por segundo
  Os gastos, serão informados atraves do protoloco JSON, seguindo padrão:
    { "descricao": "alfanumerico", "valor": double americano, "codigousuario": numerico, "data": Data dem formato UTC }
```

**Status: OK** 

Endpoint: POST IntegracaoGastosCartao -	([http://localhost:8080/api/operacoes/integracao](http://localhost:8080/api/operacoes/integracao))

Request Parameter JSON

```
{
  "codigo": "string",
  "codigoUsuario": "string",
  "data": "2019-05-09T22:57:56.050Z",
  "descricao": "string",
  "valor": 0
}
```

Response JSON

```
{
  "data": {
    "codigo": "string",
    "codigoUsuario": "string",
    "data": "2019-05-09T22:57:56.046Z",
    "descricao": "string",
    "valor": 0
  },
  "datas": [
    {
      "codigo": "string",
      "codigoUsuario": "string",
      "data": "2019-05-09T22:57:56.046Z",
      "descricao": "string",
      "valor": 0
    }
  ],
  "errors": [
    "string"
  ]
}

```

*Item 2/7*

```
Funcionalidade: Listagem de gastos*
  Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
  Quando acesso a interface de listagem de gastos
  Então gostaria de ver meus gastos mais atuais.
 
*Para esta funcionalidade é esperado 2.000 acessos por segundo.
*O cliente espera ver gastos realizados a 5 segundos atrás.
```

**Status: OK** 

Endpoint: GET ListagemGastos -	([http://localhost:8080/api/operacoes/codigoUsuario/{codigoUsuario}](http://localhost:8080/api/operacoes/codigoUsuario/{codigoUsuario}))

Response JSON

```
{
  "data": {
    "codigo": "string",
    "codigoUsuario": "string",
    "data": "2019-05-09T22:57:56.039Z",
    "descricao": "string",
    "valor": 0
  },
  "datas": [
    {
      "codigo": "string",
      "codigoUsuario": "string",
      "data": "2019-05-09T22:57:56.039Z",
      "descricao": "string",
      "valor": 0
    }
  ],
  "errors": [
    "string"
  ]
}
```

*Item 3/7*

```
Funcionalidade: Filtro de gastos
  Dado que acesso como um cliente autenticado
  E acessei a interface de listagem de gastos
  E configure o filtro de data igual a 27/03/1992
  Então gostaria de ver meus gastos apenas deste dia.
```

**Status: OK** 

Endpoint: GET FiltroGastos -	 ([http://localhost:8080/api/operacoes/codigoUsuarios/{codigoUsuarios}/data/{data}](http://localhost:8080/api/operacoes/codigoUsuarios/{codigoUsuarios}/data/{data}))


Response JSON

```
{
  "data": {
    "codigo": "string",
    "codigoUsuario": "string",
    "data": "2019-05-09T22:57:56.039Z",
    "descricao": "string",
    "valor": 0
  },
  "datas": [
    {
      "codigo": "string",
      "codigoUsuario": "string",
      "data": "2019-05-09T22:57:56.039Z",
      "descricao": "string",
      "valor": 0
    }
  ],
  "errors": [
    "string"
  ]
}
```

*Item 4/7*

```
Funcionalidade: Categorização de gastos
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe de um gasto
  E este não possui uma categoria
  Então devo conseguir incluir uma categoria para este
```

**Status: OK** 

Endpoint: POST CategorizacaoGastos -	 ([http://localhost:8080/api/operacoes/categorizacao](http://localhost:8080/api/operacoes/categorizacao))

Request Parameter JSON

```
{
  "categoria": "string",
  "codigo": "string",
  "codigoUsuario": "string"
}
```

Response JSON

```
{
  "data": {
    "categoria": "string",
    "codigo": "string",
    "codigoUsuario": "string"
  },
  "datas": [
    {
      "categoria": "string",
      "codigo": "string",
      "codigoUsuario": "string"
    }
  ],
  "errors": [
    "string"
  ]
}

```

*Item 5/7*

```
Funcionalidade: Sugestão de categoria
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe do gasto que não possui categoria
  E começo a digitar a categoria que desejo
  Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.
```

**Status: OK**

Endpoint: GET SugestaoCategorias - ([http://localhost:8080/api/operacoes](http://localhost:8080/api/operacoes))


Response JSON

```
{
  "data": {
    "codigo": "string",
    "codigoUsuario": "string",
    "data": "2019-05-09T22:57:56.039Z",
    "descricao": "string",
    "valor": 0
  },
  "datas": [
    {
      "codigo": "string",
      "codigoUsuario": "string",
      "data": "2019-05-09T22:57:56.039Z",
      "descricao": "string",
      "valor": 0
    }
  ],
  "errors": [
    "string"
  ]
}
```

*Item 6/7*

```
Funcionalidade: Categorização automatica de gasto
  No processo de integração de gastos, a categoria deve ser incluida automaticamente 
  caso a descrição de um gasto seja igual a descrição de qualquer outro gasto já categorizado pelo cliente
  o mesmo deve receber esta categoria no momento da inclusão do mesmo
```

**Status: OK** 

Endpoint: POST CategorizacaoGastos -	 ([http://localhost:8080/api/operacoes/categorizacao](http://localhost:8080/api/operacoes/categorizacao))

Request Parameter JSON

```
{
  "categoria": "string",
  "codigo": "string",
  "codigoUsuario": "string"
}
```

Response JSON

```
{
  "data": {
    "categoria": "string",
    "codigo": "string",
    "codigoUsuario": "string"
  },
  "datas": [
    {
      "categoria": "string",
      "codigo": "string",
      "codigoUsuario": "string"
    }
  ],
  "errors": [
    "string"
  ]
}

```

### # Avaliação

*Itens 7/7*


Você será avaliado pela usabilidade, por respeitar o design e pela arquitetura da API. 
É esperado que você consiga explicar as decisões que tomou durante o desenvolvimento através de commits.

* Springboot - Java - Maven (preferêncialmente) ([https://projects.spring.io/spring-boot/](https://projects.spring.io/spring-boot/))

* **Status: OK** 
	
* RESTFul ([https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/](https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/))

* **Status: OK** 
	
* DDD ([https://airbrake.io/blog/software-design/domain-driven-design](https://airbrake.io/blog/software-design/domain-driven-design))

* **Status: OK** 
	
* Microservices ([https://martinfowler.com/microservices/](https://martinfowler.com/microservices/))

* **Status: OK** 
	
* Testes unitários, teste o que achar importante (De preferência JUnit + Mockito). Mas pode usar o que você tem mais experiência, só nos explique o que ele tem de bom.

* **Status: Em desenvolvimento** 
	
* SOAPUI para testes de carga ([https://www.soapui.org/load-testing/concept.html](https://www.soapui.org/load-testing/concept.html))
	
* **Status: Aplicado Swagger** Acesso: ([http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html))
	
* Uso de diferentes formas de armazenamento de dados (REDIS, Cassandra, Solr/Lucene)

* **Status: Aplicado REDIS**
	
* Uso do git

* **Status: OK** 
	
* Diferencial: Criptografia de comunicação, com troca de chaves. ([http://noiseprotocol.org/](http://noiseprotocol.org/))

* **Status: Aplicado JWT Bearer Authentication** 
	
    Exemplos de Autenticação
    
    ([http://localhost:8080/auth/](http://localhost:8080/auth/))
    
    Request JSON
    
    ```
    {
    	"email":"admin@email.com",
    	"senha":"123456"
    }
    ```
    
    Response JSON
    
    ```
    {
        "data": {
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBlbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9BRE1JTiIsImNyZWF0ZWQiOjE1NTc0NDAzNjQwMTksImV4cCI6MTU1ODA0NTE2NH0.ZhXGcYLmAE-owe2wNuo0l1ZFpKItKhbtadOQXDfxxxhGmhRMhgHRmWMGyhbzl-vSoACCozzE-7ZAOwo_ymy1cw"
        },
        "datas": null,
        "errors": []
    }
    ```
    	
    * Diferencial: CQRS ([https://martinfowler.com/bliki/CQRS.html](https://martinfowler.com/bliki/CQRS.html))
 
* **Status: OK** 
	
* Diferencial: Docker File + Docker Compose (com dbs) para rodar seus jars.

* **Status: Em desenvolvimento** 


### # Observações gerais

Adicione um arquivo [README.md](http://README.md) com os procedimentos para executar o projeto.
Pedimos que trabalhe sozinho e não divulgue o resultado na internet.

Faça um fork desse desse repositório em seu Github e nos envie um Pull Request com o resultado, por favor informe por qual empresa você esta se candidatando.

### # Importante: não há prazo de entrega, faça com qualidade!

# BOA SORTE!


	

