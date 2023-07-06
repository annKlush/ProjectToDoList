# ProjectToDoList
Description:
"NoteShare" is a service designed for storing textual notes with the ability to share them with other users. 
Each note is associated with a single user, and a user can have multiple notes. 
The project utilizes Java programming language and H2 database for efficient data management.

Features and Functionality:

Authorization:

Users can log in using their credentials. If a user is already logged in and accesses the root address ("/"), 
they are redirected to the note list page ("/note/list").
If a user is not logged in and accesses the root address ("/"), they are redirected to the login page ("/login").
User Registration:

Users can register by clicking the "Register" button on the login page. Upon successful registration, they are redirected 
to the login page to enter their credentials and log in.
If the registration fails due to invalid user data, the user is redirected back to the registration page with empty fields and an error message.
Default User:

Upon program startup, a default user with the name "admin" and the password "super_secret_password" is created.
Note List:

The note list page ("/note/list") displays all the notes associated with the logged-in user in a tabular format.
Create Note:

The create note page ("/note/create") allows users to create new notes by entering the note name, content, and selecting the note's access type (private or public).
Upon clicking the "Save" button, a POST request with the entered data is sent to the "/note/create" endpoint. If the note passes validation, 
the user is redirected to the note list page ("/note/list") where they can see their notes, including the newly created one.
If the note fails to save due to invalid fields, the user is redirected to the error page with an appropriate error message.
Edit Note:

The edit note page ("/note/edit") is similar to the create note page, but it allows users to edit an existing note.
The page receives the note's identifier as a hidden field in the form and submits a POST request to the "/note/edit" endpoint along with other edited fields.
All fields are pre-filled with the existing note's data, and the user can modify them.
The "Save" button behaves similarly to the create note page, either redirecting to the note list page ("/note/list") upon successful saving or 
redirecting to the error page with appropriate error messages if the saving fails.
Note Entity Constraints:

Identifier: A unique string generated based on UUID.
Name: A string with a length between 5 and 100 characters.
Content: A string with a length between 5 and 10,000 characters.
Access Type: An enum with two options: private and public. The access type is stored as a string in the database.
Database:

H2 Database: A lightweight, in-memory database that provides fast and efficient data storage for the "NoteShare" project.

Project utilizes the following technologies:

Java: The core programming language used for developing the project. Java provides a robust and reliable platform for building enterprise-level applications.

H2 Database: A lightweight and fast in-memory database. H2 is used for storing and managing the note data within the application.

Spring Boot: A popular Java framework for creating stand-alone, production-grade applications. 
Spring Boot simplifies the development process by providing a pre-configured environment and various libraries for building web applications.

Spring Security: A powerful authentication and authorization framework that ensures secure access to the application's resources. 
Spring Security is used to handle user authentication and protect sensitive data.

Spring MVC: The Model-View-Controller (MVC) architectural pattern implemented by the Spring framework. 
Spring MVC is used to handle HTTP requests, manage the flow of data, and render the views for the application.

Thymeleaf: A server-side Java templating engine that enables seamless integration of dynamic content into web pages. 
Thymeleaf is used for rendering the HTML views with dynamic data from the backend.

HTML/CSS: The standard markup and styling languages used for creating the user interface of the web application. 
HTML is responsible for structuring the page content, while CSS handles the visual presentation and layout.

Gradle: A build automation tool used for managing project dependencies, compiling source code, and creating executable artifacts.
Gradle simplifies the build process and allows for easy project configuration.
