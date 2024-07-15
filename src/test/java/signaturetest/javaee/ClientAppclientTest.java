package signaturetest.javaee;

import com.sun.ts.tests.signaturetest.javaee.JavaEESigTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class ClientAppclientTest extends JavaEESigTest {
    static final String VEHICLE_ARCHIVE = "JavaEESigTest_appclient_vehicle";

    @TargetsContainer("tck-appclient")
    @OverProtocol("appclient")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentVehicle() {
        // Client
        // the jar with the correct archive name
        JavaArchive JavaEESigTest_appclient_vehicle_client = ShrinkWrap.create(JavaArchive.class, "JavaEESigTest_appclient_vehicle_client.jar");
        // The class files
        JavaEESigTest_appclient_vehicle_client.addClasses(
                com.sun.ts.tests.signaturetest.SigTestResult.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.signaturetest.SigTest.class,
                com.sun.ts.tests.signaturetest.PackageList.class,
                com.sun.ts.tests.common.vehicle.EmptyVehicleRunner.class,
                com.sun.ts.tests.signaturetest.SignatureTestDriver.SignatureFileInfo.class,
                com.sun.ts.tests.signaturetest.SigTestData.class,
                com.sun.ts.tests.signaturetest.javaee.JavaEESigTest.class,
                com.sun.ts.tests.signaturetest.javaee.JavaEESigTest.Containers.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.signaturetest.SigTestEE.class,
                com.sun.ts.tests.signaturetest.SigTestDriver.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.signaturetest.SignatureTestDriver.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.signaturetest.ApiCheckDriver.class,
                com.sun.ts.tests.signaturetest.SignatureTestDriverFactory.class
                );
        // The application-client.xml descriptor
        URL resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/appclient/appclient_vehicle_client.xml");
        if (resURL != null) {
            JavaEESigTest_appclient_vehicle_client.addAsManifestResource(resURL, "application-client.xml");
        }
        // TODO, does the sun-client-jar.xml file need to be added or should this be in in the vendor Arquillian extension?

        JavaEESigTest_appclient_vehicle_client.addAsManifestResource(new StringAsset("Main-Class: " + JavaEESigTest.class.getName() + "\n"), "MANIFEST.MF");


        EnterpriseArchive JavaEESigTest_appclient_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "JavaEESigTest_appclient_vehicle.ear");
        // The compnent jars built by the package target
        JavaEESigTest_appclient_vehicle_ear.addAsModule(JavaEESigTest_appclient_vehicle_client);

        return JavaEESigTest_appclient_vehicle_ear;
    }

    @Test
    @TargetVehicle("appclient")
    @Override
    public void signatureTest() throws Exception {
        super.signatureTest();
    }
}
