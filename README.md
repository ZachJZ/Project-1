PROJECT NAME
Project Description

Here goes your awesome project description!
Technologies Used

    AWS RDS,
    Bootstrap,
    CSS, 
    DBBeaver, 
    Eclipse IDE,
    Git.
    HTML, 
    Java, 
    JavaScript,
    JDBC, 
    Log4J, 
    Maven
    PostgreSQL, 
    Tomcat,
    RDS,
    Servlets
    SQL    


Features

List of features ready and TODOs for future development

    Login
        Admin User
        Employee User
    Submitting Reimbursement Request
    Approval/Denial of Requests
    
    TODOs
        Password hashing
        User registration
        Admin promotion
        Reimbursement submission permissions  (an admin can't approve their own request)


Getting Started

(include git clone command) (include all environment setup steps)
git clone
open the project in eclipse
in the connection.properties file you will have to connect your own databse under the 'url' variable 
    and must set the user credentials for that databse under the 'username' and 'password' variables
    (this is used in the DBConnection.java file)
Inside of your database there will need to be a employee user and an admin user created for the application to be fully functional
    In order to do this, create your employee user with whatever username and password you like, but with a user_role_id of '1'
    Similarly for creating the admin user, you can use any username and password, but the user_rold_id must be '2' for an admin user. 



Usage

After the 'Getting Started' steps are completed, you can run the project as a Java application or on a Tomcat Server.
Once running, you can login as either user and submit a request, but only the admin user can approve the request. 


