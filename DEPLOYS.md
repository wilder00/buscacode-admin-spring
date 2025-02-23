## Variables de entorno

### VSCode

se utilizó las configuraciones de la opción `Run > Open Configurations` de vscode para tener una configuración en `.vscode/launch.json` :

```
{
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot-BuscacodeAdminApplication<buscacode-admin>",
      "request": "launch",
      "cwd": "${workspaceFolder}",
      "mainClass": "com.buscacode.admin.buscacodeadmin.BuscacodeAdminApplication",
      "projectName": "buscacode-admin",
      "args": "",
      "envFile": "${workspaceFolder}/.env"
    }
  ]
}
```

## Generar un archivo final

- primero limpiar un package initial y luego crear el package nuevo

```bash
./mvnw clean package
```

- una vez ejecutado el comando deberíamos tener una carpeta `/target/buscacode-admin-*-SNAPSHOT.jar`
- Para ejecutar el .jar

```bash
java -jar ./PATH/TO/THE/JAR.jar
```

## Dockers

```
docker-compose --env-file .env.production build  > build.log 2>&1
```

## Las migraciones:

```bash
./mvnw flyway:migrate -Dspring.flyway.url=jdbc:mysql://localhost:3306/buscacode_admin -Dspring.flyway.user=root -Dspring.flyway.password=root
```
```bash
./mvnw flyway:migrate -Dspring.flyway.url=jdbc:mysql://127.0.0.1:3306/buscacode_admin -Dspring.flyway.user=root -Dspring.flyway.password=root
```


## using Makefiles

```bash
make migrate
```

```bash
make run
```
