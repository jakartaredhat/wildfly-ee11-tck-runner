package ejb30.misc.sameejbclass;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.sun.ts.tests.ejb30.misc.sameejbclass.Client;

@ExtendWith(ArquillianExtension.class)
public class ClientTest extends com.sun.ts.tests.ejb30.misc.sameejbclass.Client {
    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = "misc_sameejbclass")
    public static EnterpriseArchive createDeployment() {
        // War
        // the war with the correct archive name
        WebArchive misc_sameejbclass_web = ShrinkWrap.create(WebArchive.class, "misc_sameejbclass_web.war");
        // The class files
        misc_sameejbclass_web.addClasses(
                com.sun.ts.tests.ejb30.misc.sameejbclass.TestServlet.class,
                com.sun.ts.tests.servlet.common.servlets.HttpTCKServlet.class,
                com.sun.ts.tests.servlet.common.util.Data.class
        );
        // The web.xml descriptor
        URL warResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/misc_sameejbclass_web.xml");
        if(warResURL != null) {
            misc_sameejbclass_web.addAsWebInfResource(warResURL, "web.xml");
        }
        // The sun-web.xml descriptor
        warResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/misc_sameejbclass_web.war.sun-web.xml");
        if(warResURL != null) {
            misc_sameejbclass_web.addAsWebInfResource(warResURL, "sun-web.xml");
        }
        // Web content


        // Ejb
        // the jar with the correct archive name
        JavaArchive misc_sameejbclass_ejb = ShrinkWrap.create(JavaArchive.class, "misc_sameejbclass_ejb.jar");
        // The class files
        misc_sameejbclass_ejb.addClasses(
                com.sun.ts.tests.ejb30.misc.sameejbclass.SameEJBClassBean.class
        );
        // The ejb-jar.xml descriptor
        URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/misc_sameejbclass_ejb.xml");
        if(ejbResURL != null) {
            misc_sameejbclass_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        // The sun-ejb-jar.xml file
        ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/misc_sameejbclass_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            misc_sameejbclass_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }
        // The jboss-ebj3.xml file
        ejbResURL = ClientTest.class.getResource("/ejb30/misc/sameejbclass/misc_sameejbclass_ejb.jar.jboss-ejb3.xml");
        if(ejbResURL != null) {
            misc_sameejbclass_ejb.addAsManifestResource(ejbResURL, "jboss-ejb3.xml");
        }

        // Ear
        EnterpriseArchive misc_sameejbclass_ear = ShrinkWrap.create(EnterpriseArchive.class, "misc_sameejbclass.ear");
        JavaArchive shared_lib = ShrinkWrap.create(JavaArchive.class, "shared.jar");
        // The class files
        shared_lib.addClasses(
                com.sun.ts.tests.ejb30.common.helper.TestFailedException.class,
                com.sun.ts.tests.ejb30.misc.sameejbclass.SameEJBClassRemoteIF.class,
                com.sun.ts.tests.ejb30.common.helper.Helper.class,
                com.sun.ts.tests.ejb30.misc.sameejbclass.SameEJBClassIF.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class
        );

        // The component jars built by the package target
        misc_sameejbclass_ear.addAsModule(misc_sameejbclass_ejb);
        misc_sameejbclass_ear.addAsModule(misc_sameejbclass_web);

        // The libraries added to the ear
        misc_sameejbclass_ear.addAsLibrary(shared_lib);

        // The application.xml descriptor
        URL earResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/application.xml");
        if(earResURL != null) {
            misc_sameejbclass_ear.addAsManifestResource(earResURL, "application.xml");
        }
        // The sun-application.xml descriptor
        earResURL = Client.class.getResource("/com/sun/ts/tests/ejb30/misc/sameejbclass/application.ear.sun-application.xml");
        if(earResURL != null) {
            misc_sameejbclass_ear.addAsManifestResource(earResURL, "sun-application.xml");
        }
        return misc_sameejbclass_ear;
    }

    @Test
    @Override
    public void checkEnvEntry() throws Exception {
        super.checkEnvEntry();
    }

    @Test
    @Override
    public void testDTO() throws Exception {
        super.testDTO();
    }

}
