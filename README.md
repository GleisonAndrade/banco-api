# Banco API

API Restful genérica que realiza alguns serviços bancários.

#### Funcionalidades
1. Gerenciamento de banco (CRUD completo)
2. Gerenciamento de agências (CRUD Completo)
3. Gerenciamento de contas bancárias (CRUD completo) e opção para realizar depósito, saque, transferência e gerar extratos
4. Gerenciamento de clientes (CRUD completo)
5. Sistema de login com que tem como parâmetros de entrada o CPF e uma senha cadastrada

## Documentação
- A documentação da API foi gerada com Swagger e está disponível em `http://localhost:8080/banco-api/swagger-ui.html`

**OBSERVAÇÃO:** É necessário iniciar a aplicação para ter acesso ao documento.

## Requisitos para execução
 - [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
 - [Maven 3](https://maven.apache.org)
 - [MySQL](https://www.mysql.com/downloads/)

## Executar a aplicação

Primeiro é necessário iniciar seu banco de dados MySQL. É necessário criar as tabelas do banco. A API faz isso para você se na primeira execução você utilizar a seguinte propriedade ``spring.jpa.hibernate.ddl-auto=create`` a base é denominada 'bancoapi' e o banco por padrão é criado desde que o MYSQL tenha sido inicializado, os seguintes dados são utilizados:

#### application.properties
```
#Servidor Web
server.port=8080

#Path principal
server.servlet.context-path=/banco-api

#Definir perfil em execução
spring.profiles.active=prod

#Segredo utilizado para gerar os tokens
jwt.secret=!@Andrade#$%¨

#Tem de duração de um token de autenticação em segundos (3600000 = 1h)
jwt.expiration=3600000
```
#### application-prod.properties
```
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://localhost:3306/bancoapi?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=root

# Dialeto SQL melhorar o SQL gerado pelo Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=none

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
#### application-test.properties
```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:file:~/test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=false
#spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true
# No JDBC URL: jdbc:h2:file:~/test
```

**OBSERVAÇÃO:** Somente os profiles ``dev'' e ``test`` persistem um usuário admin no sistema, caso utilize o profile prod, será necessário inserir um admin diretamente no banco de dados. Propriedade ``spring.profiles.active=prod``. 
- CPF: 952.797.143-80
- Senha: 123456

**1. Clonando o repositório** 

```bash
git clone https://github.com/GleisonAndrade/banco-api.git
```

**2. Executar a aplicação utilizando o maven**

```bash
cd banco-api
mvn spring-boot:run
```

A aplicação pode ser acessada em `http://localhost:8080/banco-api`.

Você também pode empacotar o arquivo no formato .jar e executar o seguinte comando

```bash
mvn clean package
java -jar target/banco-api-0.0.1-RELEASE.jar
```

Para alterar uma das propriedades mostradas basta adicionar o seguinte argumento 

```bash
java -jar -Dserver.port=9000 target/banco-api-0.0.1-RELEASE.jar
```

Esse comando inicia o servidor do Spring na porta 9000

## Principais técnologias utilizadas
1. Java JDK 11
2. Maven 3
3. MySQL Database
3. Spring Boot 2.1.4
4. JWT
5. Spring Security (Auth 2.0)
6. Swagger 2.8.0

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
