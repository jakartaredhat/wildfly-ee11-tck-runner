package jms.core.bytesMsgTopic;

import com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests;
import ejb30.bb.session.stateless.basic.ClientTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import tck.arquillian.protocol.common.TargetVehicle;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URL;

/**
 * This is a port of the {@link BytesMsgTopicTests} to Arquillian/Junit 5 via a
 * subclass. This targets the ejb vehicle.
 *
 * [starksm@scottryzen wildflytck-new]$ ls jakartaeetck/dist/com/sun/ts/tests/jms/core/bytesMsgTopic
 * bytesMsgTopic_appclient_vehicle_client.jar
 * bytesMsgTopic_appclient_vehicle_client.jar.jboss-client.xml
 * bytesMsgTopic_appclient_vehicle_client.jar.sun-application-client.xml
 * bytesMsgTopic_appclient_vehicle.ear
 * bytesMsgTopic_ejb_vehicle_client.jar
 * bytesMsgTopic_ejb_vehicle_client.jar.jboss-client.xml
 * bytesMsgTopic_ejb_vehicle_client.jar.sun-application-client.xml
 * bytesMsgTopic_ejb_vehicle.ear
 * bytesMsgTopic_ejb_vehicle_ejb.jar
 * bytesMsgTopic_ejb_vehicle_ejb.jar.jboss-ejb3.xml
 * bytesMsgTopic_ejb_vehicle_ejb.jar.jboss-webservices.xml
 * bytesMsgTopic_ejb_vehicle_ejb.jar.sun-ejb-jar.xml
 * bytesMsgTopic_jsp_vehicle.ear
 * bytesMsgTopic_jsp_vehicle_web.war
 * bytesMsgTopic_jsp_vehicle_web.war.jboss-webservices.xml
 * bytesMsgTopic_jsp_vehicle_web.war.jboss-web.xml
 * bytesMsgTopic_jsp_vehicle_web.war.sun-web.xml
 * bytesMsgTopic_servlet_vehicle.ear
 * bytesMsgTopic_servlet_vehicle_web.war
 * bytesMsgTopic_servlet_vehicle_web.war.jboss-webservices.xml
 * bytesMsgTopic_servlet_vehicle_web.war.jboss-web.xml
 * bytesMsgTopic_servlet_vehicle_web.war.sun-web.xml
 */
@ExtendWith(ArquillianExtension.class)
public class ClientEjbTest extends  BytesMsgTopicTests {
    static final String VEHICLE_ARCHIVE = "bytesMsgTopic_ejb_vehicle";

    // The ejb vehicle actually uses an appclient deployment to invoke the ejb container which calls the test method
    // therefore, the protocol is appclient. It should be a TODO to remove that layer of indirection.
    @TargetsContainer("tck-appclient")
    @OverProtocol("appclient")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentEjbVehicle() {
        JavaArchive clientJar = ShrinkWrap.create(JavaArchive.class, VEHICLE_ARCHIVE+"_client.jar");
        clientJar.addClasses(com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.common.vehicle.EmptyVehicleRunner.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRemote.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRunner.class
        );
        URL resURL = BytesMsgTopicTests.class.getResource("/vehicle/ejb/ejb_vehicle_client.xml");
        if(resURL != null) {
            clientJar.addAsManifestResource(resURL, "application-client.xml");
        }
        clientJar.addAsManifestResource(new StringAsset("Main-Class: com.sun.ts.tests.common.vehicle.VehicleClient\n"), "MANIFEST.MF");

        JavaArchive ejbJar = ShrinkWrap.create(JavaArchive.class, VEHICLE_ARCHIVE+"_ejb.jar");
        ejbJar.addClasses(com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests.class,
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicle.class,
                com.sun.ts.tests.common.vehicle.ejb.EJBVehicleRemote.class,
                com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests.class,
                com.sun.ts.tests.jms.common.JmsTool.class
                );
        resURL = BytesMsgTopicTests.class.getResource("/vehicle/ejb/ejb_vehicle_ejb.xml");
        if(resURL != null) {
            ejbJar.addAsManifestResource(resURL, "ejb-jar.xml");
        }
        resURL = BytesMsgTopicTests.class.getResource("/jms/core/bytesMsgTopic/ejb_vehicle_ejb.jar.jboss-ejb3.xml");
        if(resURL != null) {
            ejbJar.addAsManifestResource(resURL, "jboss-ejb3.xml");
        }

        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, VEHICLE_ARCHIVE+".ear");
        ear.addAsModule(clientJar);
        ear.addAsModule(ejbJar);
        return ear;
    }

    public ClientEjbTest() {
        super();
    }

    @TargetVehicle("ejb")
    @Test
    @Override
    public void bytesMsgNullStreamTopicTest() throws Exception {
        super.bytesMsgNullStreamTopicTest();
    }

    @TargetVehicle("ejb")
    @Test
    @Override
    public void bytesMessageTopicTestsFullMsg() throws Exception {
        super.bytesMessageTopicTestsFullMsg();
    }

    @TargetVehicle("ejb")
    @Test
    @Override
    public void bytesMessageTNotWriteable() throws Exception {
        super.bytesMessageTNotWriteable();
    }
}
