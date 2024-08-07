package ejb32.relaxedclientview.singleton;

import com.sun.ts.tests.ejb32.relaxedclientview.singleton.Client;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.HelperSingletonBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.LocalAnnotationBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.LocalDDBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.NoAnnotationBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.NoInterfaceViewBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.OneRemoteAnnotationOnEjbBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.OneRemoteAnnotationOnInterfaceBean;
import com.sun.ts.tests.ejb32.relaxedclientview.singleton.RemoteAnnotationBean;
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
    @Deployment(name = "singleton_relaxed_client_view", order = 2)
    public static EnterpriseArchive createDeployment(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
        // the jar with the correct archive name
        JavaArchive singleton_relaxed_client_view_client = ShrinkWrap.create(JavaArchive.class, "singleton_relaxed_client_view_client.jar");
        // The class files
        singleton_relaxed_client_view_client.addClasses(
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
        URL resURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/singleton_relaxed_client_view_client.xml");
        if(resURL != null) {
            singleton_relaxed_client_view_client.addAsManifestResource(resURL, "application-client.xml");
        }
        // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
        resURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/singleton_relaxed_client_view_client.jar.sun-application-client.xml");
        if(resURL != null) {
            singleton_relaxed_client_view_client.addAsManifestResource(resURL, "application-client.xml");
            archiveProcessor.processClientArchive(singleton_relaxed_client_view_client, Client.class, resURL);
        }
        singleton_relaxed_client_view_client.addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");


        // Ejb
        // the jar with the correct archive name
        JavaArchive singleton_relaxed_client_view_ejb = ShrinkWrap.create(JavaArchive.class, "singleton_relaxed_client_view_ejb.jar");
        // The class files
        singleton_relaxed_client_view_ejb.addClasses(
                com.sun.ts.tests.ejb30.common.helper.TestFailedException.class,
                OneRemoteAnnotationOnInterfaceBean.class,
                OneRemoteAnnotationOnEjbBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.BaseBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.TestConstants.class,
                LocalDDBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.RemoteAnnotationInterface1.class,
                HelperSingletonBean.class,
                NoAnnotationBean.class,
                NoInterfaceViewBean.class,
                LocalAnnotationBean.class,
                RemoteAnnotationBean.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface2.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.HelperSingleton.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.AbstractHelperSingleton.class,
                com.sun.ts.tests.ejb32.relaxedclientview.common.NormalInterface1.class
        );
        // The ejb-jar.xml descriptor
        URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/singleton_relaxed_client_view_ejb.xml");
        if(ejbResURL != null) {
            singleton_relaxed_client_view_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        // The sun-ejb-jar.xml file
        ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/singleton_relaxed_client_view_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            singleton_relaxed_client_view_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
            archiveProcessor.processEjbArchive(singleton_relaxed_client_view_ejb, Client.class, ejbResURL);
        }

        // Ear
        EnterpriseArchive singleton_relaxed_client_view_ear = ShrinkWrap.create(EnterpriseArchive.class, "singleton_relaxed_client_view.ear");

        // Any libraries added to the ear

        // The component jars built by the package target
        singleton_relaxed_client_view_ear.addAsModule(singleton_relaxed_client_view_ejb);
        singleton_relaxed_client_view_ear.addAsModule(singleton_relaxed_client_view_client);


        // The application.xml descriptor
        URL earResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/application.xml");
        if(earResURL != null) {
            singleton_relaxed_client_view_ear.addAsManifestResource(earResURL, "application.xml");
        }
        // The sun-application.xml descriptor
        earResURL = Client.class.getResource("/com/sun/ts/tests/ejb32/relaxedclientview/singleton/application.ear.sun-application.xml");
        if(earResURL != null) {
            singleton_relaxed_client_view_ear.addAsManifestResource(earResURL, "sun-application.xml");
            archiveProcessor.processEarArchive(singleton_relaxed_client_view_ear, Client.class, earResURL);
        }
        return singleton_relaxed_client_view_ear;
    }

    @Test
    @Override
    public void noAnnotationTest() throws com.sun.ts.tests.ejb30.common.helper.TestFailedException {
        super.noAnnotationTest();
    }


}