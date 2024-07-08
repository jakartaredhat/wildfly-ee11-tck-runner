package jms.core.bytesMsgTopic;

import com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import tck.arquillian.protocol.common.TargetVehicle;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URL;

/**
 * This is a port of the {@link BytesMsgTopicTests} to Arquillian/Junit 5 via a
 * subclass. This targets the jsp vehicle.
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
public class ClientJspTest extends  BytesMsgTopicTests {
    static final String VEHICLE_ARCHIVE = "bytesMsgTopic_jsp_vehicle";

    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static EnterpriseArchive createDeploymentServletVehicle() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, VEHICLE_ARCHIVE+"_web.war");
        war.addClasses(BytesMsgTopicTests.class,
                Fault.class,
                SetupException.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.servlet.ServletVehicle.class,
                com.sun.ts.tests.jms.common.JmsTool.class
        );
        // The jsp page
        URL resURL = ClientJspTest.class.getResource("/vehicle/jsp/contentRoot/jsp_vehicle.jsp");
        war.addAsWebResource(resURL, "/jsp_vehicle.jsp");
        resURL = ClientJspTest.class.getResource("/vehicle/jsp/contentRoot/client.html");
        war.addAsWebResource(resURL, "/client.html");
        // Question of how to handle these. Right now they are copied into local resources dir.
        resURL = BytesMsgTopicTests.class.getResource("/jms/core/bytesMsgTopic/jsp_vehicle_web.xml");
        if(resURL != null) {
            war.addAsWebInfResource(resURL, "web.xml");
        }
        resURL = BytesMsgTopicTests.class.getResource("/jms/core/bytesMsgTopic/jsp_vehicle_web.war.jboss-web.xml");
        if(resURL != null) {
            war.addAsWebInfResource(resURL, "jboss-web.xml");
        }

        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, VEHICLE_ARCHIVE+".ear");
        ear.addAsModule(war);
        return ear;
    }

    public ClientJspTest() {
        super();
    }

    @Test
    @TargetVehicle("jsp")
    public void bytesMsgNullStreamTopicTest() throws Exception {
        super.bytesMsgNullStreamTopicTest();
    }

    @Test
    @Override
    @TargetVehicle("jsp")
    public void bytesMessageTopicTestsFullMsg() throws Exception {
        super.bytesMessageTopicTestsFullMsg();
    }

    @Test
    @Override
    @TargetVehicle("jsp")
    public void bytesMessageTNotWriteable() throws Exception {
        super.bytesMessageTNotWriteable();
    }
}
