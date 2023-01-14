## Run requirements:
### Required Tomcat version: 10.0.27
### Required Java version: 17


**Step 1:**
Run the following command to build the .war file:
```shell
mvn clean install
```

**Step 2:**
1. Run the application using InteliJ Idea 2022.3
![img.png](readme/img.png)

2. Add executable .war file to run the application via Tomcat.
3. Remove extra application context
4. Press Ok
![img_1.png](readme/img_1.png)
5. Run the application:
![img_2.png](readme/img_2.png)
6. Follow localhost:8080 in your web browser
![img.png](readme/login_form.png)
7. Input one of these in-memory credentials:
![img_1.png](readme/users.png)
8. Press Submit