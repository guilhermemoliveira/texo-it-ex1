# Teste Técnico - Analista de Sistemas TEXO IT

Teste técnico para a vaga de Analista de Sistemas da empresa TEXO IT.


## Tecnologias utilizadas

- Spring Boot 2.1.8
- JUnit 4
- Java 8
- Lombok 1.18.10
- Maven 3

Subindo a aplicação
---------------

- No Eclipse: Rode Application.java como uma Java Application. Nenhum outro parâmetro é necessário.
- Na linha de comando: Contendo o JAR empacotado, rode-o com: java -jar nome-do-jar.jar  

### Endpoint

http://localhost:8080/producers/intervals

Exemplo de retorno:
<pre>
{
    "min": [
        {
            "producer": "Joel Silver",
            "interval": 1,
            "previousWin": 1990,
            "followingWin": 1991
        }
    ],
    "max": [
        {
            "producer": "Matthew Vaughn",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ]
}
</pre>




Pré-requisitos (Eclipse)
--------------- 

Lombok:
Tenha Lombok aplicado ao Eclipse quando for importar o projeto. Veja: https://projectlombok.org/download  
Rode o JAR do Lombok e aplique ao Eclipse instalado.



Rodando testes integrados da aplicação
--------------- 
 
 - No Eclipse: Botão direito no diretório src/test/java e clique em Run As -> JUnit test  
 - (Requer Maven instalado) Na linha de comando: Rode com: mvn test 