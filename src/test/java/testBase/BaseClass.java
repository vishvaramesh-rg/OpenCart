package testBase;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters({"browser","OS"})
	public void setup(String browser,String OS) throws IOException {
		//log4j2 - 2 class
		logger = LogManager.getLogger(this.getClass());
		//config.properties - 4 class
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);


		//selenium grid concept
		DesiredCapabilities cap = new DesiredCapabilities();

		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			//os

			if(OS.equalsIgnoreCase("windows"))
				cap.setPlatform(Platform.WIN11);
			else if(OS.equalsIgnoreCase("mac"))
				cap.setPlatform(Platform.MAC);
			else
				System.out.println("Unmatched OS");

			//browser
			switch(browser.toLowerCase()) {
			case "chrome":cap.setBrowserName(browser);break;
			case "edge":cap.setBrowserName(browser);break;
			default: System.out.println("Unmatched browser");
			}

			//driver
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		}

		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

			driver= A1_HelperClass.BrowserLaunch(browser);
		}

		driver.manage().window().maximize();
		driver.get(p.getProperty("appUrl"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	@AfterClass(groups = {"sanity","regression","master"})
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.close();
	}

	//screenshot part
	public static BufferedImage screenShot() throws AWTException, IOException {

		Robot r = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(screenSize);
		BufferedImage source = r.createScreenCapture(rectangle);
		String path = System.getProperty("user.dir")+"\\screenshots\\errorScreenshot.png"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		ImageIO.write(source, "png", destination);

		return source;

	}

	public static String captureScreen(String tname) {

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\new\\" + tname + "_" + System.currentTimeMillis() + ".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

	//random vaules
	public String randomStrings() {
		String alphabetic = RandomStringUtils.randomAlphabetic(5);
		return alphabetic;

	}

	public String randomNumber() {
		String number = RandomStringUtils.randomNumeric(10);
		return number;
	}

	public String StringNumeric() {
		String name = RandomStringUtils.randomAlphabetic(4);
		String number = RandomStringUtils.randomNumeric(5);

		String password=name+"@"+number;
		return password;
	}


}
