package dynamicclassload;

import static org.junit.Assert.*;

import org.junit.Test;
public class DynamicClassLoadingTest {

    @Test
    public void ensureDifferentClassesReturned() throws Exception {
        TheInterface impl1 = DynamicClassLoading.getImpl();
        assertNotNull(impl1);
        DynamicClassLoading.switchImpls();
        TheInterface impl2 = DynamicClassLoading.getImpl();
        assertNotNull(impl2);
        assertTrue(impl1.getClass() != impl2.getClass());
        assertFalse(impl1.whatsyourname().equals(impl2.whatsyourname()));
    }

    @Test
    public void ensureSameReturned() throws Exception {
        TheInterface impl1 = DynamicClassLoading.getImpl();
        assertNotNull(impl1);
        TheInterface impl2 = DynamicClassLoading.getImpl();
        assertNotNull(impl2);
        assertTrue(impl1.getClass() == impl2.getClass());
        assertTrue(impl1.whatsyourname().equals(impl2.whatsyourname()));
    }
}
