# Customer Service



## Prerequisite
- Docker installed
- PostgreSQL Database running on Docker

## Run the application
- Follow the instruction and execute the initial script under /customer-service-data/resources/db/init.sql
- After you've executed the SQL script, run `mvn liquibase:update` to create the initial tables to your local PosgreSQL Database
- If you are using intelliJ, just the run the application otherwise `mvn clean build` then at /customer-service-app/target/ run `java -jar .`