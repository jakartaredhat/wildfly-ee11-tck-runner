package ejb30.bb.session.stateless.basic;

import com.sun.ts.tests.ejb30.bb.session.stateless.basic.Client;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.net.URL;

/**
 * Example of transforming a CTS appclient test into an Arquillian/Junit test
 */
@ExtendWith(ArquillianExtension.class)
public class ClientTest extends Client {
    @Deployment
    public static EnterpriseArchive createDeployment() {
        JavaArchive ejb3_bb_stateless_basic_ejb = ShrinkWrap.create(JavaArchive.class, "ejb3_bb_stateless_basic_ejb.jar");
        ejb3_bb_stateless_basic_ejb.addClasses(
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean2.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean3Super.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean3.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean4Super.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean4.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.RemoteCalculatorBean5.class,
                com.sun.ts.tests.ejb30.common.calc.BaseRemoteCalculator.class,
                com.sun.ts.tests.ejb30.common.calc.CalculatorException.class,
                com.sun.ts.tests.ejb30.common.calc.NoInterfaceRemoteCalculator.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class,
                com.sun.ts.tests.ejb30.common.calc.RemoteCalculator.class
        );
        URL resURL = Client.class.getResource("/com/sun/ts/tests/ejb30/bb/session/stateless/basic/ejb3_bb_stateless_basic_ejb.xml");
        ejb3_bb_stateless_basic_ejb.addAsManifestResource(resURL, "ejb-jar.xml");
        resURL = ClientTest.class.getResource("/ejb30/bb/session/stateless/basic/ejb3_bb_stateless_basic_ejb.jar.sun-ejb-jar.xml");
        ejb3_bb_stateless_basic_ejb.addAsManifestResource(resURL, "sun-ejb-jar.xml");

        JavaArchive ejb3_bb_stateless_basic_client = ShrinkWrap.create(JavaArchive.class, "ejb3_bb_stateless_basic_client.jar");
        ejb3_bb_stateless_basic_client.addClasses(
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.tests.ejb30.bb.session.stateless.basic.Client.class,
                com.sun.ts.tests.ejb30.common.calc.CalculatorException.class,
                com.sun.ts.tests.ejb30.common.calc.RemoteCalculator.class,
                com.sun.ts.tests.ejb30.common.helper.TLogger.class,
                com.sun.ts.tests.ejb30.common.helper.ServiceLocator.class,
                ClientTest.class
        );
        resURL = ClientTest.class.getResource("/ejb30/bb/session/stateless/basic/ejb3_bb_stateless_basic_client.xml");
        ejb3_bb_stateless_basic_client.addAsManifestResource(resURL, "application-client.xml");
        ejb3_bb_stateless_basic_client.addAsManifestResource(new StringAsset("Main-Class: " + ClientTest.class.getName() + "\n"), "MANIFEST.MF");

        File javatestJar = new File("target/appclient/javatest.jar");
        File libcommonJar = new File("target/appclient/libcommon.jar");
        File libutilJar = new File("target/appclient/libutil.jar");

        EnterpriseArchive ejb3_bb_stateless_basic = ShrinkWrap.create(EnterpriseArchive.class, "ejb3_bb_stateless_basic.ear");
        ejb3_bb_stateless_basic.addAsModule(ejb3_bb_stateless_basic_ejb);
        ejb3_bb_stateless_basic.addAsModule(ejb3_bb_stateless_basic_client);
        ejb3_bb_stateless_basic.addAsLibraries(javatestJar, libcommonJar, libutilJar);

        return ejb3_bb_stateless_basic;
    }

    // Overrides of @testName method to make them Junit 5 test methods
    @Override
    @Test
    public void postConstructInvokedEvenNoResourceInjection() throws Exception {
        super.postConstructInvokedEvenNoResourceInjection();
    }

    @Override
    @Test
    public void testRemoteAdd() throws Exception {
        super.testRemoteAdd();
    }

    @Override
    @Test
    public void postConstructCalledEvenNoResourceInjectionInBean() throws Exception {
        super.postConstructCalledEvenNoResourceInjectionInBean();
    }

    @Override
    @Test
    public void postConstructInSuperCalledEvenNoAnnotationInBean() throws Exception {
        super.postConstructInSuperCalledEvenNoAnnotationInBean();
    }

    @Override
    @Test
    public void injectedIntoSuperCalledEvenNoAnnotationInBean() throws Exception {
        super.injectedIntoSuperCalledEvenNoAnnotationInBean();
    }

    @Override
    @Test
    public void noComponentDefiningAnnotations() throws Exception {
        super.noComponentDefiningAnnotations();
    }

    @Override
    @Test
    public void testRemoteThrowIt() throws Exception {
        super.testRemoteThrowIt();
    }
}
