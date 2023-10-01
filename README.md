# Between Test

Project with hexagonal architecture, Value Objects, Unit test and Integration Test

## Requirements
* Java 11
* git

## Download project

```
git clone https://github.com/leobetancur/hexagonal.git
cd hexagonal
```

## Run project

Unix
```
./gradlew run
```

Windows
```
gradlew run
```

Windows from intellij terminal
```
.\gradlew run
```


## Run test

Unix
```
./gradlew test
```

Windows
```
gradlew test
```

Windows from intellij terminal
```
.\gradlew test
```

## Request example

By default the project run in 8081 port, if you wish to change it, please go to src\main\resources and modify it in application.properties. 

```
curl --location 'localhost:8081/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1'
```

## Postman Collection

Import file BetweenTest.postman_collection.json in Postman Application


