Here is a list of commonly used Flyway Maven commands:

- Migrate: Applies all pending migrations to the database.

```bash
mvn flyway:migrate
```

- Clean: Drops all objects in the configured schemas.

```bash
mvn flyway:clean
```

- Info: Prints the details and status information about all the migrations.

```bash
mvn flyway:info
```

- Validate: Validates the applied migrations against the available ones to detect accidental changes.

```bash
mvn flyway:validate
```

- Baseline: Baselines an existing database at a specific version.

```bash
mvn flyway:baseline
```

- Repair: Repairs the Flyway schema history table.

```bash
mvn flyway:repair
```
