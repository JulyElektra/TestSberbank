package test.java;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import other.DataExtractor;
import other.ElementDataSetter;

@RunWith(value = Parameterized.class)
public class TestDinamicCurrencyChanges {
	static WebDriver driver;
	
	/**
	 * @currentParametr - Текущий параметр в выполняемом тестировании
	 */
    private String currentParametr;
    
	/**
	 * Constructor	
	 * @param currentParametr
	 */
	public TestDinamicCurrencyChanges(String currentParametr) {
		this.currentParametr = currentParametr;
	}
	
    @Parameters
    public static Collection<String[]> testData() throws IOException {
    	return DataExtractor.getTestData("dinamic_changes_test_data.csv"); 
    }
    
	@Before
	public void set() {
		File file = new File("lib/selenium-java-3.3.1/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.get("http://www.sberbank.ru/ru/quotes/converter");
		/*
		 * Ждем пока загрузиться страница
		 */
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testDinamicCurrencyChanges()
	{
		String[] parameter = currentParametr.split(";");
		String dataFrom = parameter[0];
		String dataTo = parameter[1];
		/*
		 * Открыть окно динамика изменения курсов 
		 */
		try {
			WebElement curencyChangesDinamicEl = driver.findElement(By.cssSelector(".rates-tabs > li:nth-child(1)")); 
			curencyChangesDinamicEl.click();
		} catch (NoSuchElementException e){
			e.printStackTrace(); 
		}
		
		/*
		 * Найти элемент, в котором выбирается начальная дата и установить её
		 */
		
		WebElement dataFromInputEl = driver.findElement(By.cssSelector("div.filter-datepicker:nth-child(2) > input:nth-child(1)"));
		ElementDataSetter.setDateInElement(dataFromInputEl, dataFrom);
		
		
		/*
		 * Найти элемент, в котором выбирается конечная дата и установить её
		 */
		WebElement dataToEl = driver.findElement(By.cssSelector("div.filter-datepicker:nth-child(4) > input:nth-child(1)"));
		ElementDataSetter.setDateInElement(dataToEl, dataTo);
		
		WebElement backgroundEl = driver.findElement(By.cssSelector(".base-grid-3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
		backgroundEl.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		/*
		 * Показать результат
		 */
		try {
			WebElement showEl = driver.findElement(By.cssSelector("div.rates-details-period-datepicker-line:nth-child(2)"));
			showEl.click();
		} catch (WebDriverException e) {
			System.out.println("Button show do not loaded yet.");
			e.printStackTrace();
		}

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	    driver.quit();
	}
     
}
