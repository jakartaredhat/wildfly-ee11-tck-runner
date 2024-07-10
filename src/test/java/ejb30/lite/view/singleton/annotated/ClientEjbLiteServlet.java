package ejb30.lite.view.singleton.annotated;

import com.sun.ts.tests.ejb30.lite.view.singleton.annotated.Client;
import com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tck.arquillian.protocol.common.TargetVehicle;

import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class ClientEjbLiteServlet extends Client {
    static final String VEHICLE_ARCHIVE = "ejblite_view_singleton_annotated_ejbliteservlet_vehicle";

    @TargetsContainer("tck-javatest")
    @OverProtocol("javatest")
    @Deployment(name = VEHICLE_ARCHIVE)
    public static WebArchive createDeploymentEjbLiteServletVehicle() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, VEHICLE_ARCHIVE+"_web.war");
        war.addClasses(com.sun.ts.lib.harness.EETest.Fault.class,
                com.sun.ts.lib.harness.EETest.SetupException.class,
                com.sun.ts.lib.harness.EETest.class,
                com.sun.ts.lib.harness.ServiceEETest.class,
                com.sun.ts.tests.common.vehicle.VehicleClient.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnable.class,
                com.sun.ts.tests.common.vehicle.VehicleRunnerFactory.class,
                com.sun.ts.tests.common.vehicle.ejbliteshare.EJBLiteClientIF.class,
                com.sun.ts.tests.common.vehicle.ejbliteshare.ReasonableStatus.class,
                com.sun.ts.tests.ejb30.common.busiface.AnnotatedLocalBusinessInterface1.class,
                com.sun.ts.tests.ejb30.common.busiface.AnnotatedLocalBusinessInterface2.class,
                com.sun.ts.tests.ejb30.common.busiface.BusinessBeanBase.class,
                com.sun.ts.tests.ejb30.common.busiface.BusinessLocal1Base.class,
                com.sun.ts.tests.ejb30.common.busiface.BusinessLocalIF1.class,
                com.sun.ts.tests.ejb30.common.busiface.BusinessLocalIF2.class,
                com.sun.ts.tests.ejb30.common.busiface.Constants.class,
                com.sun.ts.tests.ejb30.common.busiface.SessionBeanLocalBeanBase.class,
                com.sun.ts.tests.ejb30.common.helper.Helper.class,
                com.sun.ts.tests.ejb30.common.lite.EJBLiteClientBase.class,
                com.sun.ts.tests.ejb30.common.lite.EJBLiteJsfClientBase.class,
                com.sun.ts.tests.ejb30.common.lite.NumberEnum.class,
                com.sun.ts.tests.ejb30.common.lite.NumberIF.class,
                com.sun.ts.tests.ejb30.lite.view.common.ClientBase.class,
                com.sun.ts.tests.ejb30.lite.view.common.JsfClientBase.class,
                com.sun.ts.tests.ejb30.lite.view.common.SuperclassBean.class,
                com.sun.ts.tests.ejb30.lite.view.common.SuperclassBeanBase.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.AnnotatedInterfaceBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.BusinessBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.Client.class,
                EJBLiteServletVehicle.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.ExternalizableLocalBean.class,
                HttpServletDelegate.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.JsfClient.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.LocalAndNoInterfaceBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.SerializableLocalBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.SessionBeanLocalBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.SubclassExtendsBeanBean.class,
                com.sun.ts.tests.ejb30.lite.view.singleton.annotated.SubclassExtendsPOJOBean.class
        );
        // Question of how to handle these. Right now they are copied into local resources dir.
        URL resURL = BytesMsgTopicTests.class.getResource("/ejb30/lite/view/singleton/annotated/ejbliteservlet_vehicle_web.xml");
        if(resURL != null) {
            war.addAsWebInfResource(resURL, "web.xml");
        }
        resURL = BytesMsgTopicTests.class.getResource("/ejb30/lite/view/singleton/annotated/ejbliteservlet_vehicle_web.xml.jboss-web.xml");
        if(resURL != null) {
            war.addAsWebInfResource(resURL, "jboss-web.xml");
        }

        return war;
    }


    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void getBusinessObjectInSubclassBean() {
        super.getBusinessObjectInSubclassBean();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void multipleInterfacesLocal() {
        super.multipleInterfacesLocal();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void singleInterfaceLocal() {
        super.singleInterfaceLocal();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void multipleAnnotatedInterfacesLocal() {
        super.multipleAnnotatedInterfacesLocal();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void localAndNoInterfaceView() {
        super.localAndNoInterfaceView();
    }

    @Test
    @TargetVehicle("ejbliteservlet")
    @Override
    public void getBusinessObjectInSuperclassBean() {
        super.getBusinessObjectInSuperclassBean();
    }
}
