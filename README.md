# Distributed Systems Project
Sean Moylan - G00299424

As part of 4th Year Distributed Systems module I was asked to build a gRPC password service (Part 1) and an API Client (Part 2) that can create user accounts and user the password service to hash the user passwords and compare hashes

## Work Environment
- macOS Catalina
- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [Swagger](https://swagger.io)
- [Postman](https://www.getpostman.com)


## Step 1
To run the PasswordService jar file
- Open Terminal
- navigate to the directory `/Target` in the project folder
- run `java -jar PasswordService.jar`
- After that is run it should say server running on port 9999
- Server is now ready for client requests

## Step 2
Once you have the PasswordService running, you then need to start up the Client
- Open Terminal
- navigate to the directory `/Target` in the project folder
- run `java -jar target/ClientService-1.0-SNAPSHOT.jar server configuration.yml`
- By default the application will run on `port:8080` and admin will run on `port:8081`
- Server is now ready for client requests

## Step 3
If you dont have postman downloaded then click [here](https://www.getpostman.com) to download
- `GET`: All users = `http://localhost:8080/users/`
- `GET`: UserById = `http://localhost:8080/users/{enter id here}`
- `DELETE`: Delete UserById = `http://localhost:8080/users/{enter id here}`
- `POST`: Create a User = `http://localhost:8080/users/`
- `PUT`: Update UserById = `http://localhost:8080/users/{enter id here}`
- `PUT`: Login User = `http://localhost:8080/login/`

- Add a request body in json for CREATE and UPDATE requests in the following format:
```json
{
         "user_id": {int},
        "user_name": "{String}",
        "email": "{String}",
        "password": "{String}"
}
```
- Add a request body in json for CREATE and UPDATE requests in the following format:
```json
{
         "user_id": {int},
        "user_name": "{String}",
        "email": "{String}",
        "password": "{String}"
}
```


### Health Check
- `GET`: All users = `http://localhost:8081/healthcheck/`




## Part 1 References 
* https://grpc.io/
* https://www.youtube.com/watch?v=2hjIn3kKXuo

## Part 2 References
* https://www.youtube.com/watch?v=HHyjWc0ASl8
* https://swagger.io
* https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/
