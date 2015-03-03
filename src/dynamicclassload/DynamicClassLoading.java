package dynamicclassload;

public class DynamicClassLoading {
    private static TheIntefaceDyanmicClassLoader loader = new TheIntefaceDyanmicClassLoader(DynamicClassLoading.class.getClassLoader());

    public static TheInterface getImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (TheInterface) loader.loadClass("dynamicclassload.TheInterfaceImpl").newInstance();
    }

    public static void switchImpls() {
        loader = new TheIntefaceDyanmicClassLoader(DynamicClassLoading.class.getClassLoader());
    }
}
