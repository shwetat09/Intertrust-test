package test;
import static org.testng.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.WeatherData;


public class WeatherUI{
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	@Test
	public void intialize() {

		//Initialize
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofMillis(20));
		driver.get("https://www.visualcrossing.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.manage().window().maximize();
		
		//
		driver.findElement(HomePage.acceptAllCookies).click();
		driver.findElement(HomePage.weatherData).click();
        wait.until(ExpectedConditions.urlContains("www.visualcrossing.com/weather-data"));
        driver.findElement(WeatherData.enterLoaction).sendKeys("Delhi");
        driver.findElement(WeatherData.search).click();
        
        //Verification
        assertEquals(driver.getCurrentUrl(),"https://www.visualcrossing.com/weather-history/Delhi");
        String tittle=driver.findElement(By.xpath("//*[@class='container pt-5 mt-5 mb-3']")).getText();
        assertEquals(tittle,"Historical weather data for Delhi");
        driver.quit();
}
}
