package signaturetest.javaee;

import com.sun.ts.tests.signaturetest.javaee.JavaEESigTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
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
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class ClientServletTest extends JavaEESigTest {
    static final String VEHICLE_ARCHIVE = "JavaEESigTest_servlet_vehicle";
    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentVehicle() {
        // War
        // the war with the correct archive name
        WebArchive JavaEESigTest_servlet_vehicle_war = ShrinkWrap.create(WebArchive.class, "JavaEESigTest_servlet_vehicle_web.war");
        // The class files
        JavaEESigTest_servlet_vehicle_war.addClasses(
                com.sun.ts.tests.signaturetest.SigTestResult.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.signaturetest.SigTest.class,
                com.sun.ts.tests.signaturetest.PackageList.class,
                com.sun.ts.tests.signaturetest.SignatureTestDriver.SignatureFileInfo.class,
                com.sun.ts.tests.signaturetest.SigTestData.class,
                com.sun.ts.tests.signaturetest.javaee.JavaEESigTest.class,
                com.sun.ts.tests.common.vehicle.servlet.ServletVehicle.class,
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
        URL resURL = JavaEESigTest.class.getResource("/com/sun/ts/tests/common/vehicle/servlet/servlet_vehicle_web.xml");
        if(resURL != null) {
            JavaEESigTest_servlet_vehicle_war.addAsWebInfResource(resURL, "web.xml");
        }

        // Ear
        EnterpriseArchive JavaEESigTest_servlet_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "JavaEESigTest_servlet_vehicle.ear");
        // The compnent jars built by the package target
        JavaEESigTest_servlet_vehicle_ear.addAsModule(JavaEESigTest_servlet_vehicle_war);

        return JavaEESigTest_servlet_vehicle_ear;
    }

    @Test
    @TargetVehicle("servlet")
    @Override
    public void signatureTest() throws Exception {
        super.signatureTest();
    }
}
