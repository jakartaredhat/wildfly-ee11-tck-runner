package ee.jakarta.tck.persistence.core.StoredProcedureQuery;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;


@ExtendWith(ArquillianExtension.class)
@Tag("persistence")
@Tag("platform")
@Tag("web")
@Tag("tck-javatest")

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Client1PmservletTest extends Client1 {
    static final String VEHICLE_ARCHIVE = "jpa_core_StoredProcedureQuery_pmservlet_vehicle";

        /**
        EE10 Deployment Descriptors:
        jpa_core_StoredProcedureQuery: myMappingFile.xml,META-INF/persistence.xml
        jpa_core_StoredProcedureQuery_appmanaged_vehicle_client: META-INF/application-client.xml
        jpa_core_StoredProcedureQuery_appmanaged_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_StoredProcedureQuery_appmanagedNoTx_vehicle_client: META-INF/application-client.xml
        jpa_core_StoredProcedureQuery_appmanagedNoTx_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_StoredProcedureQuery_pmservlet_vehicle_web: WEB-INF/web.xml
        jpa_core_StoredProcedureQuery_puservlet_vehicle_web: WEB-INF/web.xml
        jpa_core_StoredProcedureQuery_stateful3_vehicle_client: META-INF/application-client.xml
        jpa_core_StoredProcedureQuery_stateful3_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_StoredProcedureQuery_stateless3_vehicle_client: META-INF/application-client.xml
        jpa_core_StoredProcedureQuery_stateless3_vehicle_ejb: jar.sun-ejb-jar.xml
        jpa_core_StoredProcedureQuery_vehicles: 

        Found Descriptors:
        War:

        /com/sun/ts/tests/common/vehicle/pmservlet/pmservlet_vehicle_web.xml
        Ear:

        */
        @TargetsContainer("tck-javatest")
        @OverProtocol("javatest")
        @Deployment(name = VEHICLE_ARCHIVE, order = 2)
        public static EnterpriseArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {
        // War
            // the war with the correct archive name
            WebArchive jpa_core_StoredProcedureQuery_pmservlet_vehicle_web = ShrinkWrap.create(WebArchive.class, "jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.war");
            // The class files
            jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.addClasses(
            com.sun.ts.tests.common.vehicle.ejb3share.EJB3ShareBaseBean.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManager.class,
            com.sun.ts.tests.common.vehicle.ejb3share.EJB3ShareIF.class,
            Fault.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UseEntityManagerFactory.class,
            ee.jakarta.tck.persistence.common.PMClientBase.class,
            com.sun.ts.tests.common.vehicle.servlet.ServletVehicle.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
            com.sun.ts.tests.common.vehicle.ejb3share.UserTransactionWrapper.class,
            com.sun.ts.lib.harness.EETest.class,
            com.sun.ts.lib.harness.ServiceEETest.class,
            com.sun.ts.tests.common.vehicle.ejb3share.EntityTransactionWrapper.class,
            com.sun.ts.tests.common.vehicle.pmservlet.PMServletVehicle.class,
            Client1.class,
            SetupException.class,
            com.sun.ts.tests.common.vehicle.VehicleClient.class,
            com.sun.ts.tests.common.vehicle.ejb3share.NoopTransactionWrapper.class,
            Client.class
            );
            // The web.xml descriptor
            URL warResURL = Client1.class.getResource("/com/sun/ts/tests/common/vehicle/pmservlet/pmservlet_vehicle_web.xml");
            if(warResURL != null) {
              jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.addAsWebInfResource(warResURL, "web.xml");
            }
            // The sun-web.xml descriptor
            warResURL = Client1.class.getResource("//com/sun/ts/tests/common/vehicle/pmservlet/pmservlet_vehicle_web.war.sun-web.xml");
            if(warResURL != null) {
              jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.addAsWebInfResource(warResURL, "sun-web.xml");
            }

            // Any libraries added to the war

            // Web content
            warResURL = Client1.class.getResource("/com/sun/ts/tests/jpa/core/StoredProcedureQuery/jpa_core_StoredProcedureQuery.jar");
            if(warResURL != null) {
              jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.addAsWebResource(warResURL, "/WEB-INF/lib/jpa_core_StoredProcedureQuery.jar");
            }
            warResURL = Client1.class.getResource("/com/sun/ts/tests/common/vehicle/pmservlet/pmservlet_vehicle_web.xml");
            if(warResURL != null) {
              jpa_core_StoredProcedureQuery_pmservlet_vehicle_web.addAsWebResource(warResURL, "/WEB-INF/pmservlet_vehicle_web.xml");
            }

           // Call the archive processor
           archiveProcessor.processWebArchive(jpa_core_StoredProcedureQuery_pmservlet_vehicle_web, Client1.class, warResURL);


        // Par
            // the jar with the correct archive name
            JavaArchive jpa_core_StoredProcedureQuery = ShrinkWrap.create(JavaArchive.class, "jpa_core_StoredProcedureQuery.jar");
            // The class files
            jpa_core_StoredProcedureQuery.addClasses(
                Employee2.class,
                Employee.class,
                EmployeeMappedSC.class
            );
            // The persistence.xml descriptor
            URL parURL = Client1.class.getResource("persistence.xml");
            if(parURL != null) {
              jpa_core_StoredProcedureQuery.addAsManifestResource(parURL, "persistence.xml");
            }
            // Call the archive processor
            archiveProcessor.processParArchive(jpa_core_StoredProcedureQuery, Client1.class, parURL);
            // The orm.xml file
            parURL = Client1.class.getResource("orm.xml");
            if(parURL != null) {
              jpa_core_StoredProcedureQuery.addAsManifestResource(parURL, "orm.xml");
            }

        // Ear
            EnterpriseArchive jpa_core_StoredProcedureQuery_vehicles_ear = ShrinkWrap.create(EnterpriseArchive.class, "jpa_core_StoredProcedureQuery_vehicles.ear");

            // Any libraries added to the ear

            // The component jars built by the package target
            jpa_core_StoredProcedureQuery_vehicles_ear.addAsModule(jpa_core_StoredProcedureQuery_pmservlet_vehicle_web);

            jpa_core_StoredProcedureQuery_vehicles_ear.addAsLibrary(jpa_core_StoredProcedureQuery);



            // The application.xml descriptor
            URL earResURL = null;
            // The sun-application.xml descriptor
            earResURL = Client1.class.getResource("/.ear.sun-application.xml");
            if(earResURL != null) {
              jpa_core_StoredProcedureQuery_vehicles_ear.addAsManifestResource(earResURL, "sun-application.xml");
            }
            // Call the archive processor
            archiveProcessor.processEarArchive(jpa_core_StoredProcedureQuery_vehicles_ear, Client1.class, earResURL);
        return jpa_core_StoredProcedureQuery_vehicles_ear;
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void executeTest() throws Exception {
            super.executeTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getOutputParameterValueIntTest() throws Exception {
            super.getOutputParameterValueIntTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getOutputParameterValueIntIllegalArgumentExceptionTest() throws Exception {
            super.getOutputParameterValueIntIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getFirstResultTest() throws Exception {
            super.getFirstResultTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getMaxResultsTest() throws Exception {
            super.getMaxResultsTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getSingleResultTest() throws Exception {
            super.getSingleResultTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getSingleResultOrNullWithValueTest() throws Exception {
            super.getSingleResultOrNullWithValueTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getSingleResultOrNullWithNullTest() throws Exception {
            super.getSingleResultOrNullWithNullTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getSingleResultNoResultExceptionTest() throws Exception {
            super.getSingleResultNoResultExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getSingleResultNonUniqueResultExceptionTest() throws Exception {
            super.getSingleResultNonUniqueResultExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setgetFlushModeTest() throws Exception {
            super.setgetFlushModeTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setLockModeIllegalStateExceptionTest() throws Exception {
            super.setLockModeIllegalStateExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getLockModeIllegalStateExceptionTest() throws Exception {
            super.getLockModeIllegalStateExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setGetParameterIntTest() throws Exception {
            super.setGetParameterIntTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterStringExceptionTest() throws Exception {
            super.getParameterStringExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterIntIllegalArgumentExceptionTest() throws Exception {
            super.getParameterIntIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterParameterObjectTest() throws Exception {
            super.setParameterParameterObjectTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterParameterObjectIllegalArgumentExceptionTest() throws Exception {
            super.setParameterParameterObjectIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterIntObjectIllegalArgumentExceptionTest() throws Exception {
            super.setParameterIntObjectIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParametersTest() throws Exception {
            super.getParametersTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterIntDateTemporalTypeTest() throws Exception {
            super.setParameterIntDateTemporalTypeTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterIntDateTemporalTypeIllegalArgumentExceptionTest() throws Exception {
            super.setParameterIntDateTemporalTypeIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterParameterDateTemporalTypeTest() throws Exception {
            super.setParameterParameterDateTemporalTypeTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setParameterParameterDateTemporalTypeIllegalArgumentExceptionTest() throws Exception {
            super.setParameterParameterDateTemporalTypeIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void executeUpdateOfAnUpdateTest() throws Exception {
            super.executeUpdateOfAnUpdateTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void executeUpdateOfADeleteTest() throws Exception {
            super.executeUpdateOfADeleteTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void executeUpdateTransactionRequiredExceptionTest() throws Exception {
            super.executeUpdateTransactionRequiredExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueParameterTest() throws Exception {
            super.getParameterValueParameterTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueParameterIllegalArgumentExceptionTest() throws Exception {
            super.getParameterValueParameterIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueParameterIllegalStateExceptionTest() throws Exception {
            super.getParameterValueParameterIllegalStateExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueIntTest() throws Exception {
            super.getParameterValueIntTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueIntIllegalArgumentExceptionTest() throws Exception {
            super.getParameterValueIntIllegalArgumentExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void getParameterValueIntIllegalStateExceptionTest() throws Exception {
            super.getParameterValueIntIllegalStateExceptionTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void setHintStringObjectTest() throws Exception {
            super.setHintStringObjectTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void xmlOverridesNamedStoredProcedureQueryTest() throws Exception {
            super.xmlOverridesNamedStoredProcedureQueryTest();
        }

        @Test
        @Override
        @TargetVehicle("pmservlet")
        public void xmlOverridesSqlResultSetMappingAnnotationTest() throws Exception {
            super.xmlOverridesSqlResultSetMappingAnnotationTest();
        }


}