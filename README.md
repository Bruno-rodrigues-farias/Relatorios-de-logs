# Relatórios de Logs com Spring Boot + Graylog + Docker

## 📌 Sobre o projeto

Projeto desenvolvido para estudo de:

* Observabilidade
* Logs centralizados
* Monitoramento de APIs
* Docker
* Graylog
* OpenSearch
* MongoDB
* Spring Boot

A aplicação envia logs estruturados do Spring Boot para o Graylog utilizando GELF.

---

# 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot
* Docker
* Docker Compose
* Graylog
* OpenSearch
* MongoDB
* Logback GELF
* Maven

---

# 📂 Estrutura do projeto

```bash
.
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── src
│   └── main
│       ├── java
│       └── resources
│           └── logback-spring.xml
```

---

# ⚙️ Como executar o projeto

## 1. Clonar repositório

```bash
git clone (https://github.com/Bruno-rodrigues-farias/Relatorios-de-logs)
```

---

## 2. Entrar na pasta

```bash
cd relatorios-logs
```

---

## 3. Subir containers

```bash
docker compose up -d
```

---

# 🌐 Acessos

## API Spring Boot

```text
http://localhost:8080/teste-log/teste
```

---

## Graylog

```text
http://localhost:9000
```

### Login

```text
Usuário: admin
Senha: admin
```

---

# 📊 Funcionalidades

✅ Logs centralizados

✅ Logs estruturados

✅ Logs INFO / WARN / ERROR

✅ Monitoramento em tempo real

✅ Integração Spring Boot + Graylog

✅ Observabilidade básica

✅ Dockerização completa

✅ Ambiente reproduzível

---

# 📝 Exemplo de MDC

```java
MDC.put("service", "relatorios-logs");
MDC.put("endpoint", "/teste-log/teste");
MDC.put("method", "GET");
MDC.put("user_id", "15");
```

---

# 🐳 Dockerfile

```dockerfile
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 🐳 Docker Compose

```yaml
services:

  mongodb:
    image: mongo:6.0
    container_name: graylog-mongo
    restart: unless-stopped

  opensearch:
    image: opensearchproject/opensearch:2.12.0
    container_name: graylog-opensearch
    environment:
      - discovery.type=single-node
      - bootstrap.memory_lock=true
      - OPENSEARCH_JAVA_OPTS=-Xms512m -Xmx512m
      - plugins.security.disabled=true
      - OPENSEARCH_INITIAL_ADMIN_PASSWORD=G7#xK9!vP2@LmQ8$Zr
    restart: unless-stopped

  graylog:
    image: graylog/graylog:5.2
    container_name: graylog
    environment:
      GRAYLOG_PASSWORD_SECRET: minhasenha123456
      GRAYLOG_ROOT_PASSWORD_SHA2: 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
      GRAYLOG_HTTP_EXTERNAL_URI: http://127.0.0.1:9000/
      GRAYLOG_MONGODB_URI: mongodb://mongodb:27017/graylog
      GRAYLOG_ELASTICSEARCH_HOSTS: http://opensearch:9200

    ports:
      - "9000:9000"
      - "12201:12201/udp"

    depends_on:
      - mongodb
      - opensearch

    restart: unless-stopped

  spring-app:
    build: .
    container_name: relatorios-logs-app
    ports:
      - "8080:8080"
    depends_on:
      - graylog
```

---



# 👨‍💻 Autor
# Bruno Rodrigues 
