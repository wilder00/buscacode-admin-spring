FROM eclipse-temurin:17-jdk-alpine as dependencies_base

RUN apk add --no-cache dos2unix

RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo

# RUN groupadd -g 197609 demogroup && useradd -m -u 197609 -g demogroup demo

FROM dependencies_base as dependencies_copy

USER demo
WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml ./pom.xml
COPY ./mvnw ./mvnw

FROM dependencies_base as dependencies

COPY --from=dependencies_copy /app /app
WORKDIR /app
RUN dos2unix ./mvnw
USER demo

RUN /app/mvnw dependency:go-offline

FROM dependencies AS build

COPY --from=dependencies /app /app
WORKDIR /app
USER demo

ARG PORT
ARG DB_URL_CONEXION
ARG DB_USER
ARG DB_PASSWORD
ARG FILES_BASEPATH
ARG JWT_SEED

ENV DB_URL_CONEXION=${DB_URL_CONEXION}
ENV PORT=${PORT}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV FILES_BASEPATH=${FILES_BASEPATH}
ENV JWT_SEED=${JWT_SEED}

COPY ./src ./src
RUN chmod -R 777 target

# # ⚠️ Cambia a root solo para asignar permisos
# USER root
# RUN chown -R demo:demogroup /app

# # ⚠️ Vuelve a usuario demo antes de compilar
# USER demo

RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run"]
