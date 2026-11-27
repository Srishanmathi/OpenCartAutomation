
package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "registerData")
    public String[][] getRegisterData() throws IOException {

        String path = System.getProperty("user.dir")
                + "/testData/Opencarttestdata.xlsx";

        ExcelUtils xlutil = new ExcelUtils(path);

        int totalrows = xlutil.getRowCount("Data");
        int totalcols = xlutil.getCellCount("Data", 1);

        String registerdata[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                registerdata[i - 1][j] = xlutil.getCellData("Data", i, j);
            }
        }

        return registerdata;
    }

    @DataProvider(name = "loginData")
    public String[][] getLoginData() throws IOException {

        Properties prop = new Properties();

        String path = System.getProperty("user.dir")
                + "/src/test/resources/config.properties";

        File configFile = new File(path);

        // Read local config file when available
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                prop.load(reader);
            }
        }

        // Read GitHub Actions environment variables
        String emailFromEnv = System.getenv("TEST_USERNAME");
        String passwordFromEnv = System.getenv("TEST_PASSWORD");

        if (emailFromEnv != null && !emailFromEnv.trim().isEmpty()) {
            prop.setProperty("username", emailFromEnv);
        }

        if (passwordFromEnv != null && !passwordFromEnv.trim().isEmpty()) {
            prop.setProperty("password", passwordFromEnv);
        }

        String email = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Login credentials are missing");
        }

        return new String[][] {
            { email, password, "valid" },
            { email, "Wrong@12345", "invalid" },
            { "unregistered123@gmail.com", "Test@123", "invalid" }
        };
    }

    @DataProvider(name = "searchLoginData")
    public String[][] getSearchLoginData() throws IOException {

        String path = System.getProperty("user.dir")
                + "/testData/Opencarttestdata.xlsx";

        ExcelUtils xl = new ExcelUtils(path);

        String[][] data = new String[1][2];

        data[0][0] = xl.getCellData("Data", 1, 2);
        data[0][1] = xl.getCellData("Data", 1, 4);

        return data;
    }
}
