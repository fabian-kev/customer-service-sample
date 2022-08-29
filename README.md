# Customer Service



## Prerequisite
- Docker installed

## Run the application
- In /customer-service-data/resources/db/docker-compose run 'docker-compose up'
- Follow the instruction and execute the initial script under /customer-service-data/resources/db/init.sql
- After you've executed the SQL script, run `mvn liquibase:update` to create the initial tables to your local PosgreSQL Database
- If you are using intelliJ, just the run the application otherwise `mvn clean build` then at /customer-service-app/target/ run `java -jar .`