.PHONY: flyway-migrate

flyway-migrate:##@FLYWAY migrate with flyway
	@echo "Running Flyway migrations..."
	@./mvnw flyway:migrate -Dspring.flyway.url=jdbc:$(DB_URL_CONNECTION) -Dspring.flyway.user=$(DB_USER) -Dspring.flyway.password=$(DB_PASSWORD)

