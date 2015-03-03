package dynamicclassload;

public class TheInterfaceImpl implements TheInterface {

    @Override
    public String whatsyourname() {
        return getClass().getClassLoader().toString();
    }

}
