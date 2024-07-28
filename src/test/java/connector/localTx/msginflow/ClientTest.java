package connector.localTx.msginflow;

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
public class ClientTest {
    @Deployment(name = "msginflow_mdb", order = 1, testable = false)
    public static EnterpriseArchive createCommonDeployment() {
        JavaArchive msginflow_mdb_msginflow_ejb = ShrinkWrap.create(JavaArchive.class, "msginflow_mdb_msginflow.jar");
        // The class files
        msginflow_mdb_msginflow_ejb.addClasses(
                com.sun.ts.tests.connector.mdb.MessageBean.class,
                com.sun.ts.tests.connector.util.DBSupport.class
        );
        URL ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow_mdb_msginflow_ejb.xml");
        if (ejbResURL != null) {
            msginflow_mdb_msginflow_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow_mdb_msginflow_ejb.jar.sun-ejb-jar.xml");
        if (ejbResURL != null) {
            msginflow_mdb_msginflow_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        JavaArchive msginflow1_mdb_msginflow_ejb = ShrinkWrap.create(JavaArchive.class, "msginflow1_mdb_msginflow.jar");
        // The class files
        msginflow1_mdb_msginflow_ejb.addClasses(
                com.sun.ts.tests.connector.mdb.MessageBeanOne.class,
                com.sun.ts.tests.connector.util.DBSupport.class
        );
        ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow1_mdb_msginflow_ejb.xml");
        if (ejbResURL != null) {
            msginflow1_mdb_msginflow_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow1_mdb_msginflow_ejb.jar.sun-ejb-jar.xml");
        if (ejbResURL != null) {
            msginflow1_mdb_msginflow_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        EnterpriseArchive msginflow_mdb_ear = ShrinkWrap.create(EnterpriseArchive.class, "msginflow_mdb.ear");
        msginflow_mdb_ear.addAsModule(msginflow_mdb_msginflow_ejb);
        msginflow_mdb_ear.addAsModule(msginflow1_mdb_msginflow_ejb);


        return msginflow_mdb_ear;
    }

    @Deployment(name = "msginflow_mdb_jca", order = 1, testable = false)
    public static EnterpriseArchive createCommonDeployment1() {
        JavaArchive msginflow_mdb_jca_msginflow_ejb = ShrinkWrap.create(JavaArchive.class, "msginflow_mdb_jca_msginflow.jar");
        // The class files
        msginflow_mdb_jca_msginflow_ejb.addClasses(
                com.sun.ts.tests.connector.mdb.JCAMessageBean.class,
                com.sun.ts.tests.connector.util.DBSupport.class
        );
        URL ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow_mdb_jca_msginflow_ejb.xml");
        if (ejbResURL != null) {
            msginflow_mdb_jca_msginflow_ejb.addAsManifestResource(ejbResURL, "ejb-jar.xml");
        }
        ejbResURL = ClientTest.class.getResource("/com/sun/ts/tests/connector/mdb/msginflow_mdb_jca_msginflow_ejb.jar.sun-ejb-jar.xml");
        if (ejbResURL != null) {
            msginflow_mdb_jca_msginflow_ejb.addAsManifestResource(ejbResURL, "sun-ejb-jar.xml");
        }

        EnterpriseArchive msginflow_mdb_jca_ear = ShrinkWrap.create(EnterpriseArchive.class, "msginflow_mdb_jca.ear");
        msginflow_mdb_jca_ear.addAsModule(msginflow_mdb_jca_msginflow_ejb);

        return msginflow_mdb_jca_ear;
    }
}
