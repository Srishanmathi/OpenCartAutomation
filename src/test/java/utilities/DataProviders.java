package utilities;
import java.io.IOException;
import java.io.FileReader;
import java.util.Properties;
import org.testng.annotations.DataProvider;
public class DataProviders {
	@DataProvider(name = "registerData")
	public String[][] getRegisterData() throws IOException {
	    String path = System.getProperty("user.dir") + "\\testData\\Opencarttestdata.xlsx";
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
	    String path = System.getProperty("user.dir")
	            + "/src/test/resources/config.properties";
	    Properties prop = new Properties();
	    try (FileReader reader = new FileReader(path)) {
	        prop.load(reader);
	    }
	    String email = prop.getProperty("username");
	    String password = prop.getProperty("password");
	    return new String[][] {
	        {email, password, "valid"},
	        {email, "Wrong@12345", "invalid"},
	        {"unregistered123@gmail.com", "Test@123", "invalid"}
	    };
	}
	@DataProvider(name = "searchLoginData")
	public String[][] getSearchLoginData() throws IOException {
	    String path = System.getProperty("user.dir") + "/testData/Opencarttestdata.xlsx";
	    ExcelUtils xl = new ExcelUtils(path);
	    String[][] data = new String[1][2];
	    data[0][0] = xl.getCellData("Data", 1, 2);
	    data[0][1] = xl.getCellData("Data", 1, 4);
	    return data;
	}
}