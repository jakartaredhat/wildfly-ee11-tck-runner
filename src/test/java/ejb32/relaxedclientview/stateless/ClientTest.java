package ejb32.relaxedclientview.stateless;

import com.sun.ts.tests.ejb32.relaxedclientview.stateless.Client;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.HelperSingletonBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.LocalAnnotationBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.LocalDDBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.NoAnnotationBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.NoInterfaceViewBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.OneRemoteAnnotationOnEjbBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.OneRemoteAnnotationOnInterfaceBean;
import com.sun.ts.tests.ejb32.relaxedclientview.stateless.RemoteAnnotationBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;

import java.net.URL;


@ExtendWith(ArquillianExtension.class)
public class ClientTest extends Client {
    @TargetsContainer("tck-appclient")
        @OverProtocol("appclient")
        @Deployment(name = "stateless_relaxed_client_view", order = 2)
        public static EnterpriseArchive createDeployment(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
            // the jar with the correct archive name
            JavaArchive stateless_relaxed_client_view_client = ShrinkWrap.create(JavaArchive.class, "stateless_relaxed_client_view_client.jar");
            // The class files
            stateless_relaxed_client_view_client.addClasses(
            com.sun.ts.tests.ejb30.common.helper.TestFailedException.class,
            Fault.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface2.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.TestConstants.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.RemoteAnnotationInterface1.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.HelperSingleton.class,
            Client.class,
            com.sun.ts.lib.harness.EETest.class,
            com.sun.ts.tests.ejb30.common.helper.TLogger.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.ClientBase.class,
            com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface1.class,
            SetupException.class
            );
            // The application-client.xml descriptor
            URL resURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/stateless_relaxed_client_view_client.xml");
            if(resURL != null) {
              stateless_relaxed_client_view_client.addAsManifestResource(resURL, "application-client.xml");
            }
            // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
            resURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/stateless_relaxed_client_view_client.jar.sun-application-client.xml");
            if(resURL != null) {
              stateless_relaxed_client_view_client.addAsManifestResource(resURL, "application-client.xml");
              archiveProcessor.processClientArchive(stateless_relaxed_client_view_client, Client.class, resURL);
            }
            stateless_relaxed_client_view_client.addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");


        // Ejb
            // the jar with the correct archive name
            JavaArchive stateless_relaxed_client_view_ejb = ShrinkWrap.create(JavaArchive.class, "stateless_relaxed_client_view_ejb.jar");
            // The class files
            stateless_relaxed_client_view_ejb.addClasses(
                com.sun.ts.tests.ejb30.common.helper.TestFailedException.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.BaseBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.TestConstants.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.RemoteAnnotationInterface1.class,
                LocalDDBean.class,
                OneRemoteAnnotationOnInterfaceBean.class,
                NoInterfaceViewBean.class,
                HelperSingletonBean.class,
                LocalAnnotationBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface2.class,
                NoAnnotationBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.HelperSingleton.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class,
                RemoteAnnotationBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.AbstractHelperSingleton.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface1.class,
                OneRemoteAnnotationOnEjbBean.class
            );
            // The ejb-jar.xml descriptor
            URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/stateless_relaxed_client_view_ejb.xml");
            if(ejbResURL != null) {
              stateless_relaxed_client_view_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
            }
            // The sun-ejb-jar.xml file
            ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/stateless_relaxed_client_view_ejb.jar.sun-ejb-jar.xml");
            if(ejbResURL != null) {
              stateless_relaxed_client_view_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
              archiveProcessor.processEjbArchive(stateless_relaxed_client_view_ejb, Client.class, ejbResURL);
            }

        // Ear
            EnterpriseArchive stateless_relaxed_client_view_ear = ShrinkWrap.create(EnterpriseArchive.class, "stateless_relaxed_client_view.ear");

            // Any libraries added to the ear

            // The component jars built by the package target
            stateless_relaxed_client_view_ear.addAsModule(stateless_relaxed_client_view_ejb);
            stateless_relaxed_client_view_ear.addAsModule(stateless_relaxed_client_view_client);


            // The application.xml descriptor
            URL earResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/application.xml");
            if(earResURL != null) {
              stateless_relaxed_client_view_ear.addAsManifestResource(earResURL, "application.xml");
            }
            // The sun-application.xml descriptor
            earResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/stateless/application.ear.sun-application.xml");
            if(earResURL != null) {
              stateless_relaxed_client_view_ear.addAsManifestResource(earResURL, "sun-application.xml");
              archiveProcessor.processEarArchive(stateless_relaxed_client_view_ear, Client.class, earResURL);
            }
        return stateless_relaxed_client_view_ear;
        }

        @Test
        @Override
        public void noAnnotationTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.noAnnotationTest();
        }

        @Test
        @Override
        public void localAnnotationTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.localAnnotationTest();
        }

        @Test
        @Override
        public void remoteAnnotationTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.remoteAnnotationTest();
        }

        @Test
        @Override
        public void oneRemoteAnnotationOnInterfaceTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.oneRemoteAnnotationOnInterfaceTest();
        }

        @Test
        @Override
        public void oneRemoteAnnotationOnEjbTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.oneRemoteAnnotationOnEjbTest();
        }

        @Test
        @Override
        public void noInterfaceViewTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.noInterfaceViewTest();
        }

        @Test
        @Override
        public void localDDTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
            super.localDDTest();
        }


}