package jms.core.bytesMsgTopic;

import com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;



@ExtendWith(ArquillianExtension.class)
public class ClientAppclientTest extends com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests {
    static final String VEHICLE_ARCHIVE = "bytesMsgTopic_appclient_vehicle";
    @TargetsContainer("tck-appclient")
    @OverProtocol("appclient")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentVehicle() {
        // Client
        // the jar with the correct archive name
        JavaArchive bytesMsgTopic_appclient_vehicle_client = ShrinkWrap.create(JavaArchive.class, "bytesMsgTopic_appclient_vehicle_client.jar");
        // The class files
        bytesMsgTopic_appclient_vehicle_client.addClasses(
                com.sun.ts.tests.jms.common.JmsTool.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.common.vehicle.EmptyVehicleRunner.class,
                com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class
        );
        // The application-client.xml descriptor
        URL resURL = BytesMsgTopicTests.class.getResource("/com/sun/ts/tests/common/vehicle/appclient/appclient_vehicle_client.xml");
        if(resURL != null) {
            bytesMsgTopic_appclient_vehicle_client.addAsManifestResource(resURL, "application-client.xml");
        }
        // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
        resURL = BytesMsgTopicTests.class.getResource("/com/sun/ts/tests/common/vehicle/appclient/appclient_vehicle_client.jar.sun-application-client.xml");
        if(resURL != null) {
            bytesMsgTopic_appclient_vehicle_client.addAsManifestResource(resURL, "application-client.xml");
        }
        bytesMsgTopic_appclient_vehicle_client.addAsManifestResource(new StringAsset("Main-Class: " + BytesMsgTopicTests.class.getName() + "\n"), "MANIFEST.MF");


        // Ear
        EnterpriseArchive bytesMsgTopic_appclient_vehicle_ear = ShrinkWrap.create(EnterpriseArchive.class, "bytesMsgTopic_appclient_vehicle.ear");

        // Any libraries added to the ear

        // The component jars built by the package target
        bytesMsgTopic_appclient_vehicle_ear.addAsModule(bytesMsgTopic_appclient_vehicle_client);


        // The application.xml descriptor
        URL earResURL = BytesMsgTopicTests.class.getResource("/com/sun/ts/tests/jms/core/bytesMsgTopic/");
        if(earResURL != null) {
            bytesMsgTopic_appclient_vehicle_ear.addAsManifestResource(earResURL, "application.xml");
        }
        // The sun-application.xml descriptor
        earResURL = BytesMsgTopicTests.class.getResource("/com/sun/ts/tests/jms/core/bytesMsgTopic/.ear.sun-application.xml");
        if(earResURL != null) {
            bytesMsgTopic_appclient_vehicle_ear.addAsManifestResource(earResURL, "sun-application.xml");
        }
        return bytesMsgTopic_appclient_vehicle_ear;
    }

    @Test
    @Override
    @TargetVehicle("appclient")
    public void bytesMsgNullStreamTopicTest() throws java.lang.Exception {
        super.bytesMsgNullStreamTopicTest();
    }

    @Test
    @Override
    @TargetVehicle("appclient")
    public void bytesMessageTopicTestsFullMsg() throws java.lang.Exception {
        super.bytesMessageTopicTestsFullMsg();
    }

    @Test
    @Override
    @TargetVehicle("appclient")
    public void bytesMessageTNotWriteable() throws java.lang.Exception {
        super.bytesMessageTNotWriteable();
    }


}