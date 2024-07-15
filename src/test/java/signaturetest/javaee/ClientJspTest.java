package signaturetest.javaee;

import com.sun.ts.tests.signaturetest.javaee.JavaEESigTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;

import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class ClientJspTest extends JavaEESigTest {
    static final String VEHICLE_ARCHIVE = "JavaEESigTest_jsp_vehicle";
    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentVehicle() {
        // War
        // the war with the correct archive name
        WebArchive JavaEESigTest_jsp_vehicle_war = ShrinkWrap.create(WebArchive.class, "JavaEESigTest_jsp_vehicle_web.war");
        // The class files
        JavaEESigTest_jsp_vehicle_war.addClasses(
                com.sun.ts.tests.signaturetest.SigTestResult.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.signaturetest.SigTest.class,
                com.sun.ts.tests.signaturetest.PackageList.class,
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
        // The web.xml descriptor
        URL resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/jsp/jsp_vehicle_web.xml");
        if(resURL != null) {
            JavaEESigTest_jsp_vehicle_war.addAsWebInfResource(resURL, "web.xml");
        }
        // TODO, does the sun-web.xml file need to be added or should this be in in the vendor Arquillian extension?
        // Web content
        resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/jsp/contentRoot/client.html");
        JavaEESigTest_jsp_vehicle_war.addAsWebResource(resURL, "/client.html");
        resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/jsp/contentRoot/jsp_vehicle.jsp");
        JavaEESigTest_jsp_vehicle_war.addAsWebResource(resURL, "/jsp_vehicle.jsp");


        // Ear
        EnterpriseArchive JavaEESigTest_jsp_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "JavaEESigTest_jsp_vehicle.ear");
        // The compnent jars built by the package target
        JavaEESigTest_jsp_vehicle_ear.addAsModule(JavaEESigTest_jsp_vehicle_war);

        return JavaEESigTest_jsp_vehicle_ear;
    }

    @Test
    @TargetVehicle("jsp")
    @Override
    public void signatureTest() throws Exception {
        super.signatureTest();
    }
}
