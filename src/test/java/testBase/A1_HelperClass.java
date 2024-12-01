package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class A1_HelperClass {

	static WebDriver driver;

	public static WebDriver BrowserLaunch(String browsername) {

		switch(browsername.toLowerCase()) {

		case "chrome": WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); break;

		case "edge" : WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver(); break;

		default:
			System.out.println("No match");
			}

		return driver;
	}

	public static void main(String[] args) {



	}

}
