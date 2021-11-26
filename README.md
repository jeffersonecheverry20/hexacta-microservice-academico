# hexacta-microservice-academico

REQUIREMENTS

* Java 8
* Redis
* MySQL
* DynamoDB or MongoDB
* Spring Boot

1. REDIS

To raise Redis it is necessary to execute the following command

* docker run -d --name redis -p 6379:6379 redis redis-server --appendonly yes


2. MONGODB

To raise Redis it is necessary to execute the following commands

* docker pull mongo:latest
* docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongo

To create a database and document it is necessary to access the mongo image in docker throug the console, therefore you must execute the following commands

* docker exec -it mongo bash
* use inquiries
* db.createCollection("metrics")


3. DYNAMODB

* You are required to have an AWS account. 
* You must create your user, this user must have all permission to access DynamoDB. 
* Finally, you must create your tables in DynamoDB.
