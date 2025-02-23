.PHONY:  db-migrate print-env run

install:##@General to install using ./mvnw install
	@./mvnw install

# Run another DB command using .env variables
db-migrate:##@Migration add migrations
	@echo "Migrating database for $(DB_URL_CONNECTION)..."
	@make flyway-migrate

run:##@General run with mvnw spring-boot:run
	@./mvnw spring-boot:run
