package javadb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import ee.tck.javadb.JavaDBController;

import java.nio.file.Path;
import java.nio.file.Paths;

@EnabledIfSystemProperty(named = "ts.home", matches = ".*")
public class StartStopTest {
    static boolean started = false;

    @BeforeAll
    public static void start() throws Exception {
        Path tsHome = Paths.get(System.getProperty("ts.home"));
        Path derbyHome = tsHome.resolve("../glassfish7/javadb");
        JavaDBController controller = new JavaDBController();
        System.out.println("StartStopTest.starting JavaDB with derbyHome="+derbyHome);
        controller.startJavaDB(derbyHome);
        System.out.println("StartStopTest.started JavaDB");
        started = true;
    }
    @AfterAll
    public static void stop() throws Exception {
        JavaDBController controller = new JavaDBController();
        controller.stopJavaDB();
        Thread.sleep(1000);
        Assertions.assertFalse(controller.isJavaDBRunning());
        System.out.println("StartStopTest.stopped JavaDB");
    }

    @Test
    public void testIsRunning() throws Exception{
        System.out.println("StartStopTest.testIsRunning");
        Thread.sleep(1000);
        if(!started) {
            Assertions.fail("JavaDB was not started");
        }
        JavaDBController controller = new JavaDBController();
        Assertions.assertTrue(controller.isJavaDBRunning(), "JavaDB is running");
    }
}
