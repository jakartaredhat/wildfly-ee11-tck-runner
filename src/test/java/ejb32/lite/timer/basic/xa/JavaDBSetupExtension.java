package ejb32.lite.timer.basic.xa;

import com.sun.ts.tests.ejb32.lite.timer.basic.xa.XATimerSuite;
import ee.tck.javadb.JavaDBController;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.suite.api.Suite;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Extends the XATimerSuite to start and stop the JavaDB server before and after the suite.
 */
@Suite
public class JavaDBSetupExtension extends XATimerSuite implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.printf("beforeAll suite");
        Path tsHome = Paths.get(System.getProperty("ts.home"));
        Path derbyHome = tsHome.resolve("../glassfish7/javadb");
        JavaDBController controller = new JavaDBController();
        System.out.println("JavaDBSetupExtension.starting JavaDB with derbyHome="+derbyHome);
        extensionContext.publishReportEntry("JavaDBSetupExtension.beforeAll", "JavaDBSetupExtension.starting JavaDB with derbyHome="+derbyHome);
        controller.startJavaDB(derbyHome);
        System.out.println("JavaDBSetupExtension.started JavaDB");
        extensionContext.publishReportEntry("JavaDBSetupExtension.beforeAll", "Starting JavaDB");
    }

    @Override
    public void close() throws Throwable {
        System.out.printf("Closing suite");
        JavaDBController controller = new JavaDBController();
        controller.stopJavaDB();
        System.out.println("StartStopTest.stopped JavaDB");
    }
}
