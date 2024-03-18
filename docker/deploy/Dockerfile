FROM eclipse-temurin:17-jdk-alpine as dependencies

RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

FROM dependencies AS build

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
RUN ./mvnw clean install

CMD ["./mvnw", "spring-boot:run"]