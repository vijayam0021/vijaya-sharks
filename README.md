"# vijaya-sharks" 

Makersharks Assessment - Details
---------------------------------
The assigment to create a user registration and user details fetch API has been implemented in Spring Boot using MySQL database. 
I have implemented very basic security (allow all requests). The ideal way to implement this would be as follows:
- allow un-authenticated requests for user registration
- create an admin user role and admin user
- allow fetch api to work only for admin user (or perhaps any other user) after login (user should pass authentication) and authorization.
- This will require implementation of userDetailsService tied to the database that holds the user information. UserDetailsService will authenticate the user
- Subsequently, we can implement authorization with tokens.

Other nice to have things would be to include logging (with sl4j). Currently, logging is not implemented. 

Installation instructions
-------------------------

1. This assumes that a MySQL database is available for use.
2. Use script sql-dbscripts.sql to create the database and database user that the spring boot application will use
3. The script must be run with correct privileges (create database, create user and grant priveliges must be allowed). Ideally, an admin role will perform this action (in step #2).
4. In the application.properties file in the spring boot application, the user name and password from script are provided to connect to the database
5. In application.propertise file, the value for the following attribute must match the database installtion location in your setup (in step #2). For example, below, we assume the database is installed on localhost and is runnig on port 3306 with dbname msharks
     spring.datasource.url = jdbc:mysql://localhost:3306/msharks
6. In application.properties the value for the following attribute specifies the port where the Spring Boot application will run. It has been set to 8081
    server.port =8081

To Run the Application
-----------------------
1. The source code is located at a public repository on github at:
2. Clone the repository and compile the sources
3. Use command below to run the application from command line. If has successfully launched, you will see the line <b>Hello!</b> printed on the console

To test the API
---------------
Assuming the application is running on localhost at port 8081, use the following commands to test the API. 

User Registration API

The user registration uses the BCryptPasswordEncoder to encode the password before storing to the database. The BCryptPasswordEncoder stores the salt along with the encrypted password to the database.

Fetch User API

The fetch user API does not return the password for security purposes. 
