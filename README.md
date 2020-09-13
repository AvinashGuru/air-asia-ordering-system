The application is a Spring Boot App with Maven build tool.

You can build the application , by using maven clean install, the integration test is run and the jar file is created.

Once the integration tests are successfully completed you will see below message in console
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0

Once the jar file is created , you can run the jar file using  java -jar .\ordering-0.0.1-SNAPSHOT.jar command

Application is started with default port 8080 with context path  air-asia

URL to invoke:
http://localhost:8080/air-asia/ordering/create

Method: POST
Request Body: You can reuse air-asia-ordering-system\src\test\resources\success.json

The api code,  gets details from the user like Name, email address, phone number , hotel Id, room Id, guest count etc and creates order .

Data Model is designed with the above assumption and Data Model diagram is added in air-asia-ordering-system\db_model\db_model.jpg

Have used In Memory H2 Database, Table creation and static data population are added while server start using initialization scripts
Once Server started, table details can be validated from
http://localhost:8080/air-asia/h2
url=jdbc:h2:mem:orderingMaster
username=ordering
password=ordering@123

Java Docs for the project has been created and added inside air-asia-ordering-system\doc

Validations for all the mandatory input fields are added, some generic validations like Mandatory, Date Format validations are added

If Validation is failed, proper status code is returned along with proper message

Once validations are success, Order gets created and Order Id is returned as part of response

POSSIBLE STATUS CODE : 201 (CREATED), 400 (BAD REQUEST) , 404 ( RESOURCE_NOT_FOUND), 500 (INTERNA_SERVER_ERROR)
