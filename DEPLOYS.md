
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