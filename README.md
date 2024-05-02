# Desafio Nexfar
### Descrição do projeto:
<p>
    Implementar um projeto Java, utilizando o framework Spring Boot que exponha uma
    API para receber requisições com instruções para geração de relatórios. Esses
    relatórios deverão ser disponibilizados no formato XLS e CSV, conforme instrução
    enviada no payload da requisição.
</p>

____

### Neste projeto:
- Java 17
- Spring Framework
- Maven
- MongoDB

____

### Instalação
- Clone o repositório: `git clone https://github.com/beltraliny/desafio-nexfar.git`
- Instale as dependências Maven
  - Se houver erro use Ctrl + Shift + A, digite 'maven' e selecione 'Reload All Maven Projects';
- Ajuste as configurações do MongoDB em `application.properties`

____

### Usabilidade
- Inicie a aplicação
- A API estará disponível em `http://localhost:8080`

____

### API

#### EndPoint
`POST: http://localhost:8080/report/generate`

- Filtros possíveis:
  - cnpj (tipo equals)
  - createdAt (tipo interval)
  - status (tipo equals)
  - netTotal (tipo gte ou lte)
- Formatos possíveis:
  - XLS
  - CSV
- Tipos de relatórios possíveis:
  - ORDER_SIMPLE (Relatório Simples)
  - ORDER_DETAILED (Relatório Detalhado)

#### Exemplo de requisição:

#### Body (JSON):
    {
        "key" : "ORDER_DETAILED",
        "format" : "XLS",
        "filters": [
            {
                "key" : "cnpj",
                "operation": "EQ",
                "value1" : "1111111111",
            },
            {
                "key" : "createdAt",
                "operation" : "INTERVAL",
                "value1" : "2022-11-25 10:30",
                "value2" : "2022-12-16 11:00"
            }
        ]
    }
