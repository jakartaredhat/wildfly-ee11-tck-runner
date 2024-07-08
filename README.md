# WildFly EE 11 TCK test runner
An example WildFly runner for the ported EE 11 TCK tests.

So far, there are example runners for:
- appclient

## Running the test using mvn
From the command line run `mvn test`
`mvn test`
```shell
[starksm@scottryzen tck-test]$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< org.wildfly.ee:wildfly-tck-runner >------------------
...
[INFO] --- surefire:3.2.5:test (appclient-tests-tck) @ wildfly-tck-runner ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ejb30.bb.session.stateless.basic.ClientTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 63.36 s -- in ejb30.bb.session.stateless.basic.ClientTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:05 min
[INFO] Finished at: 2024-06-15T21:43:47-06:00
[INFO] ------------------------------------------------------------------------
```

## Running the test in an IDE
You can run the `ejb30.bb.session.stateless.basic.ClientTest` from within your IDE, but
you need to configure the system properties for the test runner to something similar to
the surefire. The most important system property is the `arquillian.xml` (-Darquillian.xml=appclient-arquillian.xml) since the Arquillian container configuration descriptor
uses a non-default name. If youadd another container configuration for your server, either
make it the default or change the `arquillian.launch` system property to the name of your
configuration.

## Running Vechicle tests in IDE
The jms.core.bytesMsgTopic.*Test are exploring using the appclient and javatest Arquillian protocols to run the existing JavaTest based vehicles as is by subclassing the existing test class. Currently the jsp, servlet and ejb vehicles have tests that run. You need to point the ts.home property to an EE 10 TCK dist that has been updated for wildfly using the cts-10-mods.
