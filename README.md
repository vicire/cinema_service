# Philharmonic

This convenient web service allows customers to check
available concerts in various philharmonics and book any available concert
session.

The application provides authorization with 2 roles and different functionality, 
which depends on them. Anybody has a permission to register <br>

##### User: <br>
- Display the list of available concerts;
- Display the list of stages;
- Show user order list;
- Possibility to find session by date;
- Possibility to add sessions to shopping cart;
- Ability to make an order;
##### Admin: <br>
- Possibility to add concert;
- Display the list of available concerts;
- Possibility to add stage;
- Display the list of stages;
- Possibility to add, update, delete concert session;
- Possibility to find session by date;
- Ability to find session by date;
- Ability to find user by email;


# Technologies used <br>
**backend:** Java, Spring Core/MVC/Security, Hibernate, Jackson, Tomcat <br>
**database:** MySQL <br>


# To start the project you need: <br>
1. Download and install JDK 11 or later
2. Download and install Apache Tomcat web server
3. Download and install MySQL.
4. Configure the database connection properties in the **db.properties** file: <br>
+ user: "your username"
+ password: "your password"
+ db.url=jdbc:mysql://*"your host name"*:*"your port"*/*"your schema name"*?useUnicode=true&serverTimezone=UTC <br>
5. Run a project 
