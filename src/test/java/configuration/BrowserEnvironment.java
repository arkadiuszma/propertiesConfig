package configuration;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
public class BrowserEnvironment {
    private String browserName;
    private boolean headlessBrowser;
    private int webElementTimeout;
    private boolean attachScreenshot;
    private WebDriver driver;

    public BrowserEnvironment() {
        this.browserName = "chrome";
        this.headlessBrowser = false;
        this.webElementTimeout = 5;
        this.attachScreenshot = false;
        this.initBrowserSettings();
    }

    private void initBrowserSettings() {
        this.browserName = PropertyStore.BROWSER.isSpecified() ? PropertyStore.BROWSER.getValue() : this.browserName;
        this.webElementTimeout = PropertyStore.BROWSER_WEBELEMENT_TIMEOUT.isSpecified() ? PropertyStore.BROWSER_WEBELEMENT_TIMEOUT.getIntValue() : this.webElementTimeout;
        this.attachScreenshot = PropertyStore.BROWSER_ATTACH_SCREENSHOT.isSpecified() ? PropertyStore.BROWSER_ATTACH_SCREENSHOT.getBooleanValue() : this.attachScreenshot;
        this.headlessBrowser = PropertyStore.BROWSER_HEADLESS.isSpecified() ? PropertyStore.BROWSER_HEADLESS.getBooleanValue() : this.headlessBrowser;
    }
    public WebDriver getDriver(){
        switch(this.browserName){
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.get(System.getProperty("url"));
            }
            case "firefox" -> {
                driver = new FirefoxDriver();
                driver.get(System.getProperty("url"));
                driver.manage().window().maximize();
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("start-maximized");
                driver = new EdgeDriver(options);
                driver.get(System.getProperty("url"));
            }
        }
        return driver;
    }
}
