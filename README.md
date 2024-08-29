# WildFly EE 11 TCK test runner
An example WildFly runner for the ported EE 11 TCK tests.

## Setup

The runner needs the following dependencies. These will be installed by the setup.sh script run in the next section.

* Jakarta EE 11 TCK build, https://github.com/jakartaee/platform-tck.git.
* The jakartaee-tck-tools, https://github.com/eclipse-ee4j/jakartaee-tck-tools.git. This alternates between the latest staged version and a local snapshot build depending on if we are working on fixes to the arquillian protocols. Currently the version.jakarta.tck.arquillian is set to 1.0.0-M12.
* The EE10 TCK cts-10-mods repo, https://gitlab.cee.redhat.com/j2eects/cts-10-mods.git. This requires VPN access to download, but not to use.
* https://gitlab.cee.redhat.com/j2eects/scripts.git. Also requires VPN to clone, but not run.

### TCK ENV Setup
Run the setup.sh script from the root of the wildfly-ee11-tck-runner repo on a host with a Red Hat VPN connection.

## Running the Tests

So far, there are example runners for:
* ejb30
* ejb32
* jms
* jpa

Each tck module has its own execution section with the id set to the module name. To run a single test module, use `mvn surefire:test@<module-name>`. For example, to run the JPA tests, use:

```shell
mvn -Pstaging surefire:test@jpa
mvn -Pstaging surefire:test@jpa -Dtest=org.jboss.spec.javax.persistence.jpa.test.criteria.CriteriaQueryTest
```
The second command runs a single test class from the JPA module.

## Running the test using mvn
From the command line run `mvn test`
`mvn test`
```shell
[starksm@scottryzen tck-test]$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] -----------------< org.wildfly.ee:wildfly-tck-runner >------------------
...
[INFO] Running com.sun.ts.tests.ejb32.lite.timer.schedule.tx.ClientEjblitejsfTest
... 
[ERROR] Tests run: 1322, Failures: 0, Errors: 709, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  04:21 h
[INFO] Finished at: 2024-08-15T04:15:13-06:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.2.5:test (ejb32) on project wildfly-tck-runner: 
[ERROR] 
[ERROR] Please refer to /home/starksm/Dev/Jakarta/tck-test/target/surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
```

## Running the test in an IDE
You can run a test like `com.sun.ts.tests.ejb32.lite.timer.schedule.tx.ClientEjblitejsfTest` from within your IDE, but you need to configure the system properties for the test runner to something similar to the surefire. The most important system property is the `arquillian.xml` (-Darquillian.xml=javatest-arquillian.xml) since the Arquillian container configuration descriptor uses a non-default name. If youadd another container configuration for your server, either make it the default or change the `arquillian.launch` system property to the name of your configuration.

