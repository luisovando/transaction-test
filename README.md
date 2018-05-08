# Transaction test

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Please install the following tools

```
Java 8
```

```
Maven
```

## Package application

1. Go to root application folder

If you have local Maven installation

```
mvn package 
```
Otherwise Spring boot provide local maven implementation, for use it, execute

```
mvnw package
```
Both command generate a *target* folder, under this folder .jar application file is create  

## Execute actions

### Add transaction
```
java -jar target/technicaltest-1.0.0.jar <userId> "add" <jsonBody>
```
This command make database.txt, on this file all transactions are saved.

### Show transaction
```
java -jar target/technicaltest-1.0.0.jar <userId> <transactionId>
```

### List transaction
```
java -jar target/technicaltest-1.0.0.jar <userId> "list"
```

### Sum amounts
```
java -jar target/technicaltest-1.0.0.jar <userId> "sum"
```

## Examples

### Add transaction
```
java -jar target/technicaltest-1.0.0.jar 345 "add" "{\"amount\": 100, \"description\": \"Joes Tacos\", \"date\":\"2018-01-35\", \"user_id\": 345}"
```

### Show transaction
```
java -jar target/technicaltest-1.0.0.jar 345 "cd61eada-644f-425b-8288-6517d0f133cb"
```

### List transaction
```
java -jar target/technicaltest-1.0.0.jar 345 "list"
```

### Sum amounts
```
java -jar target/technicaltest-1.0.0.jar 345 "sum"
```
