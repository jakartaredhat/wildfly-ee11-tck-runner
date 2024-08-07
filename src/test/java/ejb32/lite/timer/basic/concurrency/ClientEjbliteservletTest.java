package ejb32.lite.timer.basic.concurrency;

import com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.Client;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.porting.lib.spi.TestArchiveProcessor;
import tck.arquillian.protocol.common.TargetVehicle;
import java.util.concurrent.ExecutionException;
import java.net.URL;



@ExtendWith(ArquillianExtension.class)
public class ClientEjbliteservletTest extends Client {
    static final String VEHICLE_ARCHIVE = "ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle";
        @TargetsContainer("tck-javatest")
        @OverProtocol("javatest")
        @Deployment(name = VEHICLE_ARCHIVE, order = 2)
        public static WebArchive createDeploymentVehicle(@ArquillianResource TestArchiveProcessor archiveProcessor) {

        // War
            // the war with the correct archive name
            WebArchive ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web = ShrinkWrap.create(WebArchive.class, "ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web.war");
            // The class files
            ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web.addClasses(
            com.sun.ts.tests.ejb30.common.helper.TestFailedException.class,
            Fault.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.WriteSingletonTimerBean.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
            Client.class,
            com.sun.ts.tests.ejb30.common.helper.Helper.class,
            com.sun.ts.tests.ejb30.common.lite.EJBLiteClientBase.class,
            com.sun.ts.lib.harness.EETest.class,
            com.sun.ts.lib.harness.ServiceEETest.class,
            com.sun.ts.tests.common.vehicle.VehicleClient.class,
            com.sun.ts.tests.ejb30.common.lite.NumberIF.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.HttpServletDelegate.class,
            com.sun.ts.tests.common.vehicle.ejbliteshare.EJBLiteClientIF.class,
            com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
            com.sun.ts.tests.ejb30.timer.common.TimerInfo.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.TimerIF.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.JsfClient.class,
            com.sun.ts.tests.common.vehicle.ejbliteshare.ReasonableStatus.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.LockSingletonTimerBeanBase.class,
            com.sun.ts.tests.ejb30.timer.common.TimeoutStatusBean.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.ReadSingletonTimerBean.class,
            com.sun.ts.tests.ejb30.common.lite.NumberEnum.class,
            com.sun.ts.tests.ejb30.common.lite.EJBLiteJsfClientBase.class,
            com.sun.ts.tests.ejb30.timer.common.TimerUtil.class,
            com.sun.ts.tests.ejb32.lite.timer.basic.concurrency.EJBLiteServletVehicle.class,
            com.sun.ts.tests.ejb30.common.helper.ServiceLocator.class,
            SetupException.class
            );
            // The web.xml descriptor
            URL warResURL = Client.class.getResource("ejbliteservlet_vehicle_web.xml");
            if(warResURL != null) {
              ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web.addAsWebInfResource(warResURL, "web.xml");
            }
            // The sun-web.xml descriptor
            warResURL = Client.class.getResource("/vehicle/ejbliteservlet/ejbliteservlet_vehicle_web.war.sun-web.xml");
            if(warResURL != null) {
              ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web.addAsWebInfResource(warResURL, "sun-web.xml");
              archiveProcessor.processWebArchive(ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web, Client.class, warResURL);
            }
            // Web content


        return ejb32_lite_timer_basic_concurrency_ejbliteservlet_vehicle_web;
        }

        @Test
        @Override
        @TargetVehicle("ejbliteservlet")
        public void lookupTimerService() throws InterruptedException, ExecutionException {
            super.lookupTimerService();
        }

        @Test
        @Override
        @TargetVehicle("ejbliteservlet")
        public void writeLockTimeout()  {
            super.writeLockTimeout();
        }


}