package ejb.ee.tx.session.stateless.bm.Tx_Multi;

import com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.Client;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;



@ExtendWith(ArquillianExtension.class)
public class ClientTest extends com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.Client {
    @Deployment(name = "ejb_tx_txbean", order = 1, testable = false)
    public static EnterpriseArchive createCommonDeployment() {
        JavaArchive ejb_tx_txbean_ejb = ShrinkWrap.create(JavaArchive.class, "ejb_tx_txbean_ejb.jar");
        // The class files
        ejb_tx_txbean_ejb.addClasses(
                com.sun.ts.tests.ejb.ee.tx.txbean.AppException.class,
                com.sun.ts.tests.ejb.ee.tx.txbean.SysException.class
        );
        URL ejbResURL = ClientTest.class.getResource("com/sun/ts/tests/ejb/ee/tx/txbean/ejb_tx_txbean_ejb.xml");
        if(ejbResURL != null) {
            ejb_tx_txbean_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        ejbResURL = ClientTest.class.getResource("com/sun/ts/tests/ejb/ee/tx/txbean/ejb_tx_txbean_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            ejb_tx_txbean_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        EnterpriseArchive ejb_tx_txbean_ear = ShrinkWrap.create(EnterpriseArchive.class, "ejb_tx_txbean.ear");
        ejb_tx_txbean_ear.addAsModule(ejb_tx_txbean_ejb);

        return ejb_tx_txbean_ear;
    }
    @Deployment(name = "ejb_tx_txbean2", order = 1, testable = false)
    public static EnterpriseArchive createCommonDeployment2() {
        JavaArchive ejb_tx_txbean2_ejb = ShrinkWrap.create(JavaArchive.class, "ejb_tx_txbean2_ejb.jar");
        // The class files
        ejb_tx_txbean2_ejb.addClasses(
                com.sun.ts.tests.ejb.ee.tx.txbean.AppException.class,
                com.sun.ts.tests.ejb.ee.tx.txbean.SysException.class
        );
        URL ejbResURL = ClientTest.class.getResource("com/sun/ts/tests/ejb/ee/tx/txbean/ejb_tx_txbean2_ejb.xml");
        if(ejbResURL != null) {
            ejb_tx_txbean2_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        ejbResURL = ClientTest.class.getResource("com/sun/ts/tests/ejb/ee/tx/txbean/ejb_tx_txbean2_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            ejb_tx_txbean2_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        EnterpriseArchive ejb_tx_txbean2_ear = ShrinkWrap.create(EnterpriseArchive.class, "ejb_tx_txbean2.ear");
        ejb_tx_txbean2_ear.addAsModule(ejb_tx_txbean2_ejb);

        return ejb_tx_txbean2_ear;
    }

    @TargetsContainer("tck-appclient")
    @OverProtocol("appclient")
    @Deployment(name = "ejb_txSslBm_TxMulti", order = 2)
    public static EnterpriseArchive createDeployment(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // Client
        // the jar with the correct archive name
        JavaArchive ejb_txSslBm_TxMulti_client = ShrinkWrap.create(JavaArchive.class, "ejb_txSslBm_TxMulti_client.jar");
        // The class files
        ejb_txSslBm_TxMulti_client.addClasses(
                com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.Client.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.TestBean.class
        );
        // The application-client.xml descriptor
        URL resURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/ejb_txSslBm_TxMulti_client.xml");
        if(resURL != null) {
            ejb_txSslBm_TxMulti_client.addAsManifestResource(resURL, "application-client.xml");
        }
        // The sun-application-client.xml file need to be added or should this be in in the vendor Arquillian extension?
        resURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/ejb_txSslBm_TxMulti_client.jar.sun-application-client.xml");
        if(resURL != null) {
            ejb_txSslBm_TxMulti_client.addAsManifestResource(resURL, "application-client.xml");
            archiveProcessor.processClientArchive(ejb_txSslBm_TxMulti_client, Client.class, resURL);
        }
        ejb_txSslBm_TxMulti_client.addAsManifestResource(new StringAsset("Main-Class: " + Client.class.getName() + "\n"), "MANIFEST.MF");


        // Ejb
        // the jar with the correct archive name
        JavaArchive ejb_txSslBm_TxMulti_ejb = ShrinkWrap.create(JavaArchive.class, "ejb_txSslBm_TxMulti_ejb.jar");
        // The class files
        ejb_txSslBm_TxMulti_ejb.addClasses(
                com.sun.ts.tests.ejb.ee.tx.txbean.SysException.class,
                com.sun.ts.tests.ejb.ee.tx.txbean.TxBean.class,
                com.sun.ts.tests.ejb.ee.tx.txbean.AppException.class,
                com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.TestBeanEJB.class,
                com.sun.ts.tests.ejb.ee.tx.session.stateless.bm.Tx_Multi.TestBean.class
        );
        // The ejb-jar.xml descriptor
        URL ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/ejb_txSslBm_TxMulti_ejb.xml");
        if(ejbResURL != null) {
            ejb_txSslBm_TxMulti_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        // The sun-ejb-jar.xml file
        ejbResURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/ejb_txSslBm_TxMulti_ejb.jar.sun-ejb-jar.xml");
        if(ejbResURL != null) {
            ejb_txSslBm_TxMulti_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
            archiveProcessor.processEjbArchive(ejb_txSslBm_TxMulti_ejb, Client.class, ejbResURL);
        }

        // Ear
        EnterpriseArchive ejb_txSslBm_TxMulti_ear = ShrinkWrap.create(EnterpriseArchive.class, "ejb_txSslBm_TxMulti.ear");

        // Any libraries added to the ear

        // The component jars built by the package target
        ejb_txSslBm_TxMulti_ear.addAsModule(ejb_txSslBm_TxMulti_ejb);
        ejb_txSslBm_TxMulti_ear.addAsModule(ejb_txSslBm_TxMulti_client);


        // The application.xml descriptor
        URL earResURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/");
        if(earResURL != null) {
            ejb_txSslBm_TxMulti_ear.addAsManifestResource(earResURL, "application.xml");
        }
        // The sun-application.xml descriptor
        earResURL = Client.class.getResource("/com/sun/ts/tests/ejb/ee/tx/session/stateless/bm/Tx_Multi/.ear.sun-application.xml");
        if(earResURL != null) {
            ejb_txSslBm_TxMulti_ear.addAsManifestResource(earResURL, "sun-application.xml");
            archiveProcessor.processEarArchive(ejb_txSslBm_TxMulti_ear, Client.class, earResURL);
        }
        return ejb_txSslBm_TxMulti_ear;
    }

    @Test
    @Override
    @OperateOnDeployment("ejb_txSslBm_TxMulti")
    public void test1() throws com.sun.ts.lib.harness.EETest.Fault {
        super.test1();
    }

    @Test
    @Override
    @OperateOnDeployment("ejb_txSslBm_TxMulti")
    public void test2() throws com.sun.ts.lib.harness.EETest.Fault {
        super.test2();
    }

    @Test
    @Override
    @OperateOnDeployment("ejb_txSslBm_TxMulti")
    public void test4() throws com.sun.ts.lib.harness.EETest.Fault {
        super.test4();
    }

    @Test
    @Override
    @OperateOnDeployment("ejb_txSslBm_TxMulti")
    public void test5() throws com.sun.ts.lib.harness.EETest.Fault {
        super.test5();
    }


}