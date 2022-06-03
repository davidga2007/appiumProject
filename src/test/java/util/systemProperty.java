package util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;



@Slf4j
public class systemProperty {
	
	String appName;
	
	public AndroidDriver<MobileElement> driver;
	
	  // set up new properties object
    public static Properties getAppiumConfigProperties() throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(System.getProperty("user.dir") +"\\serenity.properties");
        prop.load(input);
		return prop;

    }
	
	
	public static String getAppiumHub() throws IOException {
		log.info("Setting up Appium");
		String hub = String.format(getAppiumConfigProperties().getProperty("appium.hub"));
		return hub;	
	}
	
	public static String getPlatfornName() throws IOException {
		log.info("Appium Platform Name");
		String platformName = String.format(getAppiumConfigProperties().getProperty("appium.platformName"));
		return platformName;	
	}
	
	public static String getAutomationName() throws IOException {
		log.info("Appium Automation Name");
		String automationName = String.format(getAppiumConfigProperties().getProperty("appium.automationName"));
		return automationName;	
	}
	
	public static String getVersion() throws IOException {
		log.info("Appium Platform Version");
		String platformVersion = String.format(getAppiumConfigProperties().getProperty("appium.platformVersion"));
		return platformVersion;	
	}
	
	public static String getDeviceName() throws IOException {
		log.info("Appium Device Name");
		String deviceName = String.format(getAppiumConfigProperties().getProperty("appium.deviceName"));
		return deviceName;	
	}
	
	public static String apkLocation() throws FileNotFoundException, IOException	{
		log.info("Get apk file Location");
		String akpFile = String.format(getAppiumConfigProperties().getProperty("appium.apk.dir"));
		return akpFile;
	}
	
	public static String getApkFile() throws FileNotFoundException, IOException	{
		log.info("Get apk file");
		String akpFile = String.format(getAppiumConfigProperties().getProperty("GeneralStoreApp"));
		return akpFile;
	}
	
	public static String getBrowser() throws IOException {
		String browserName = String.format(getAppiumConfigProperties().getProperty("browserName"));
		return browserName;
	}
	
	@Step
	public static AndroidDriver<AndroidElement> capability() throws IOException { 

		File app = new File(apkLocation(), getApkFile());

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, getAutomationName());
		//cap.setCapability(MobileCapabilityType.BROWSER_NAME, getBrowser());
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, getPlatfornName());
		cap.setCapability(MobileCapabilityType.VERSION, getVersion());
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL(getAppiumHub()), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
		
	}



