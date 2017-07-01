# Integration-testing a Java web application
This is the repository for [End-to-end testing Java web applications with an embedded Tomcat](https://blog.georgovassilis.com/2016/01/29/end-to-end-testing-java-web-applications-with-an-embedded-tomcat/) and [Getting test coverage reports for integration test](https://blog.georgovassilis.com/2017/06/23/getting-test-coverage-reports-for-integration-test/)
which discusses performing end-to-end tests on
a multi-module Java web application. It runs a simple (yet not trivial) Java web application from within the
Maven integration test phase, launches a test browser and interacts with the web application.

Topics covered:

- packaging web application in a WAR module
- inheriting dependencies from the WAR module as if it were a JAR
- running integration tests in a separate module
- reporting test coverage


Get a local copy with:
```git clone https://github.com/ggeorgovassilis/test-with-embedded-tomcat.git```

and run with

```mvn install```

