package signaturetest.javaee;

import com.sun.ts.tests.signaturetest.javaee.JavaEESigTest;
import org.junit.jupiter.api.Test;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class ClientEjbTest extends JavaEESigTest {
    static final String VEHICLE_ARCHIVE = "JavaEESigTest_ejb_vehicle";
    @TargetsContainer("tck-appclient")
    @OverProtocol("appclient")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentVehicle() {
        // Client
        // the jar with the correct archive name
        JavaArchive JavaEESigTest_ejb_vehicle_client = ShrinkWrap.create(JavaArchive.class, "JavaEESigTest_ejb_vehicle_client.jar");
        // The class files
        JavaEESigTest_ejb_vehicle_client.addClasses(
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRemote.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.common.vehicle.EmptyVehicleRunner.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRunner.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class
        );
        // The application-client.xml descriptor
        URL resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/ejb/ejb_vehicle_client.xml");
        if(resURL != null) {
            JavaEESigTest_ejb_vehicle_client.addAsManifestResource(resURL, "application-client.xml");
        }
        // TODO, does the sun-client-jar.xml file need to be added or should this be in in the vendor Arquillian extension?

        JavaEESigTest_ejb_vehicle_client.addAsManifestResource(new StringAsset("Main-Class: " + JavaEESigTest.class.getName() + "\n"), "MANIFEST.MF");


        // Ejb
        // the jar with the correct archive name
        JavaArchive JavaEESigTest_ejb_vehicle_ejb = ShrinkWrap.create(JavaArchive.class, "JavaEESigTest_ejb_vehicle_ejb.jar");
        // The class files
        JavaEESigTest_ejb_vehicle_ejb.addClasses(
                com.sun.ts.tests.signaturetest.SigTestResult.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.signaturetest.SigTest.class,
                com.sun.ts.tests.signaturetest.PackageList.class,
                com.sun.ts.tests.signaturetest.SignatureTestDriver.SignatureFileInfo.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicle.class,
                com.sun.ts.tests.signaturetest.SigTestData.class,
                com.sun.ts.tests.signaturetest.javaee.JavaEESigTest.class,
                com.sun.ts.tests.signaturetest.javaee.JavaEESigTest.Containers.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRemote.class,
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
        // The ejb-jar.xml descriptor
        URL ejbResURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/ejb/ejb_vehicle_ejb.xml");
        if(ejbResURL != null) {
            JavaEESigTest_ejb_vehicle_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        // TODO, does the sun-ejb-jar.xml file need to be added or should this be in in the vendor Arquillian extension?

        // Ear
        EnterpriseArchive JavaEESigTest_ejb_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "JavaEESigTest_ejb_vehicle.ear");
        // The compnent jars built by the package target
        JavaEESigTest_ejb_vehicle_ear.addAsModule(JavaEESigTest_ejb_vehicle_ejb);
        JavaEESigTest_ejb_vehicle_ear.addAsModule(JavaEESigTest_ejb_vehicle_client);

        return JavaEESigTest_ejb_vehicle_ear;
    }

    @Test
    @TargetVehicle("ejb")
    @Override
    public void signatureTest() throws Exception {
        super.signatureTest();
    }
}
