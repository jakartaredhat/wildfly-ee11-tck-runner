package ejb32;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class MethodOrderTest {
    @Test
    public void testTimerClientMethodOrder() {
        Class<?> testClass = com.sun.ts.tests.ejb32.lite.timer.schedule.lifecycle.Client.class;
        Method[] methods = testClass.getMethods();
        for (Method method : methods) {
            System.out.println("MethodOrderTest.testTimerClientMethodOrder: " + method.getName());
        }
    }
}
