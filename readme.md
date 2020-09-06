##ClientManager Application

This application is created based on test assignment described in task folder.

## Running the application

To run the application, run following command on Windows based computer:
     "mvnw.cmd spring-boot:run"
or following command on Linux / Mac:
    "./mvnw spring-boot:run "

## Test users
To use application, I created three test users, that can be used for logging in:
1) harry / potter
2) hermione / granger
3) ronald / weasley

## Technical decision
1) For application, I use Spring Boot as required by test assignment
2) For database, I used H2 in memory database and used JDBC driver to access it.
3) For database creation, I used Liquibase. For clarity, I split up changelog files: one for creating
schema and other one for filling in the data.
4) Passwords were encoded by org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
which was said to be one of the defacto password encoders for Java apllications. 
Created ee.srini.clientmanager.util.PasswordEncoder for managing application passwords encoding.
5) For UI side used Thymeleaf as has good support from Spring Boot out of the box.
