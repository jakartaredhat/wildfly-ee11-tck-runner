package ejb30.assembly.initorder.warejb;

import com.sun.ts.tests.ejb30.assembly.initorder.warejb.Client;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import java.net.URL;

/**
 * An example of a non-vehicle client that needs an additiona common deployment
 * method for a common deployment archive as defined by the
 * src/com/sun/ts/lib/harness/commonarchives.properties file.
 */
@ExtendWith(ArquillianExtension.class)
public class ClientTest extends Client {
    // TODO, this need to be generated
    @Deployment(name = "ejb3_common_helloejbjar_standalone_component", order = 1, testable = false)
    public static JavaArchive createCommonDeployment() {
        JavaArchive ejb3_common_helloejbjar_standalone_component_ejb = ShrinkWrap.create(JavaArchive.class, "ejb3_common_helloejbjar_standalone_component_ejb.jar");
        // The class files
        ejb3_common_helloejbjar_standalone_component_ejb.addClasses(
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloCommonIF.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloRemoteIF.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloLocalIF.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloBeanBase.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloBean.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class
        );
        URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/common/helloejbjar/ejb3_common_helloejbjar_standalone_component_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            ejb3_common_helloejbjar_standalone_component_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        return ejb3_common_helloejbjar_standalone_component_ejb;
    }

    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = "ejb3_assembly_initorder_warejb", order = 2)
    public static EnterpriseArchive createDeployment() {
        // War
        // the war with the correct archive name
        WebArchive ejb3_assembly_initorder_warejb_web = ShrinkWrap.create(WebArchive.class, "ejb3_assembly_initorder_warejb_web.war");
        // The class files
        ejb3_assembly_initorder_warejb_web.addClasses(
                com.sun.ts.tests.ejb30.assembly.initorder.warejb.TestServlet.class,
                com.sun.ts.tests.servlet.common.servlets.HttpTCKServlet.class,
                com.sun.ts.tests.servlet.common.util.Data.class
        );
        // The web.xml descriptor
        URL warResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/assembly/initorder/warejb/ejb3_assembly_initorder_warejb_web.xml");
        if(warResURL != null) {
            ejb3_assembly_initorder_warejb_web.addAsWebInfResource(warResURL, "web.xml");
        }
        // TODO, does the sun-web.xml file need to be added or should this be in in the vendor Arquillian extension?
        // Web content


        // Ejb
        // the jar with the correct archive name
        JavaArchive ejb3_assembly_initorder_warejb_ejb = ShrinkWrap.create(JavaArchive.class, "ejb3_assembly_initorder_warejb_ejb.jar");
        // The class files
        ejb3_assembly_initorder_warejb_ejb.addClasses(
                com.sun.ts.tests.ejb30.assembly.initorder.common.InitOrderBean.class
        );
        // The ejb-jar.xml descriptor
        URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/assembly/initorder/warejb/ejb3_assembly_initorder_warejb_ejb.xml");
        if(ejbResURL != null) {
            ejb3_assembly_initorder_warejb_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        // The sun-ejb-jar.xml file
        ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/assembly/initorder/warejb/ejb3_assembly_initorder_warejb_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            ejb3_assembly_initorder_warejb_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }
        // The jboss-ejb3.xml file
        ejbResURL = ClientTest.class.getResource("/ejb30/assembly/initorder/warejb/ejb3_assembly_initorder_warejb_ejb.jar.jboss-ejb3.xml");
        if(ejbResURL != null) {
            ejb3_assembly_initorder_warejb_ejb.addAsManifestResource(ejbResURL, "jboss-ejb3.xml");
        }

        // Ear
        EnterpriseArchive ejb3_assembly_initorder_warejb_ear = ShrinkWrap.create(EnterpriseArchive.class, "ejb3_assembly_initorder_warejb.ear");
        JavaArchive shared_lib = ShrinkWrap.create(JavaArchive.class, "shared.jar");
        // The class files
        shared_lib.addClasses(
                com.sun.ts.tests.ejb30.common.helper.Helper.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloCommonIF.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloLocalIF.class,
                com.sun.ts.tests.ejb30.assembly.initorder.common.InitOrderRemoteIF.class,
                com.sun.ts.tests.ejb30.common.helper.ServiceLocator.class,
                com.sun.ts.tests.ejb30.common.helloejbjar.HelloRemoteIF.class
        );

        // The component jars built by the package target
        ejb3_assembly_initorder_warejb_ear.addAsModule(ejb3_assembly_initorder_warejb_ejb);
        ejb3_assembly_initorder_warejb_ear.addAsModule(ejb3_assembly_initorder_warejb_web);

        // The libraries added to the ear
        ejb3_assembly_initorder_warejb_ear.addAsLibrary(shared_lib);

        // The application.xml descriptor
        URL earResURL = ClientTest.class.getResource("/com/sun/ts/tests/ejb30/assembly/initorder/warejb/application.xml");
        if(earResURL != null) {
            ejb3_assembly_initorder_warejb_ear.addAsManifestResource(earResURL, "application.xml");
        }

        return ejb3_assembly_initorder_warejb_ear;
    }

    @Test
    @OperateOnDeployment("ejb3_assembly_initorder_warejb")
    @Override
    public void initOrder() throws Exception {
        super.initOrder();
    }

    @Test
    @OperateOnDeployment("ejb3_assembly_initorder_warejb")
    @Override
    public void appName() throws Exception {
        super.appName();
    }
}
