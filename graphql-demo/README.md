# graphql-demo

This is a simple demo project to show how to create APIs using GraphQL.
This project was done during the Cyber Offense and Defense course at UniCal 2024.

## GraphQL dev-ui

The GraphQL dev-ui is available at <http://localhost:8082/q/graphql-ui/>.

## Run application in production mode

### Step 1: Build the application

```shell script
./mvnw clean package
```

### Step 2: Start PostgreSQL container as database

```shell script
docker run --name graphql-demo-postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=graphql-demo-db -p 5432:5432 -d postgres:latest
```

### Step 3: Run the Application in prod mode

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```




# Quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8082/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/graphql-demo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- SmallRye GraphQL ([guide](https://quarkus.io/guides/smallrye-graphql)): Create GraphQL Endpoints using the code-first approach from MicroProfile GraphQL

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)



### SmallRye GraphQL

Start coding with this Hello GraphQL Query

[Related guide section...](https://quarkus.io/guides/smallrye-graphql)
