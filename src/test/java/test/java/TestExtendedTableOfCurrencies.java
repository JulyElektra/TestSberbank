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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import other.DataExtractor;
import other.ElementDataSetter;

@RunWith(value = Parameterized.class)
public class TestExtendedTableOfCurrencies {
	static WebDriver driver;
	
	/**
	 * @currentParametr - Текущий параметр в выполняемом тестировании
	 */
    private String currentParametr;
    
	/**
	 * Constructor	
	 * @param currentParametr
	 */
	public TestExtendedTableOfCurrencies(String currentParametr) {
		this.currentParametr = currentParametr;
	}
	
    @Parameters
    public static Collection<String[]> testData() throws IOException {
    	return DataExtractor.getTestData("extended_table_test_data.csv"); 
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
	public void testExtendedTableOfCurrencies(){
		
		/*
		 * Получить дату из параметра
		 */
		String[] parameter = currentParametr.split(";");
		String data = parameter[0];
		
		/*
		 * Найти элемент Расширеная таблица курсов и открыть её
		 */
		WebElement extendedTableEl = driver.findElement(By.cssSelector(".rates-tabs > li:nth-child(2) > span:nth-child(1)"));
		extendedTableEl.click();
		
		/*
		 * Найти элемент, в котором выбирается дата и установить её
		 */
		WebElement dateEl = driver.findElement(By.cssSelector(".hasDatepicker"));
		ElementDataSetter.setDateInElement(dateEl, data);
		
		/*
		 * Показать результат
		 */
		WebElement showEl = driver.findElement(By.cssSelector(".button"));
		showEl.click();
		
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
