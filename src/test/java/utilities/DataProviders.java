package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name="dataDDT")
	public String[][] datas() throws IOException {

		String path=System.getProperty("user.dir")+"\\testData\\OpenCart_loginDDT.xlsx";  //taking xl file from testData
		ExcelUtility xldata = new ExcelUtility(path); //creating an object for XLUtility

		int rowCount = xldata.getRowCount("users");
		int cellCount = xldata.getCellCount("users", 1);

		String logindata[][] = new String[rowCount][cellCount];//created for two dimension array which can store the data user and password

		for(int i=1; i<=rowCount; i++) {  //1   //read the data from xl storing in two deminsional array

			for(int j=0; j<cellCount;j++) { //0    i is rows j is col
				logindata[i-1][j] = xldata.getCellData("users", i, j); //1,0

			}
		}
		return logindata;
	}

	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4

}
