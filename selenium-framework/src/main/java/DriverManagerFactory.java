public class DriverManagerFactory {
    public static DriverManager getManager(BrowserTypes.DriverType browserType) {

        DriverManager driverManager;
        switch (browserType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;

            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}
