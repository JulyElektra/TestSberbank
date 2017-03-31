package test.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestForSber {
	static WebDriver driver;
	
	/*
	 * Данные полученные через переменные типа HashMap будут использованы для css selector
	 */
	static HashMap<String, Integer> currencyMap = new HashMap<>();
	static HashMap<String, Integer> resourcesAndRecieveringMap = new HashMap<>();
	static HashMap<String, Integer> exchangeMethodsMap = new HashMap<>();
	static HashMap<String, Integer> packagesOfServicesMap = new HashMap<>();
	static HashMap<String, Integer> timesMap = new HashMap<>();
	
	/**
	 * @currentParametr - Текущий параметр в выполняемом тестировании
	 */
    private String currentParametr;
	
	/*
	 * Заполнение HashMap значениями
	 */
    static {
		currencyMap.put("RUB", 1);
		currencyMap.put("CHF", 2);
		currencyMap.put("EUR", 3);
		currencyMap.put("GBP", 4);
		currencyMap.put("JPU", 5);
		currencyMap.put("USD", 6);	
	}
	
	static {
		resourcesAndRecieveringMap.put("Card", 2);
		resourcesAndRecieveringMap.put("Account", 3);
		resourcesAndRecieveringMap.put("Cash", 4);
	}
	
	
	static {
		exchangeMethodsMap.put("Internet bank", 2);
		exchangeMethodsMap.put("Department", 3);
		exchangeMethodsMap.put("ATM", 4);
	}
	
	
	static { 
		packagesOfServicesMap.put("No package", 2);
		packagesOfServicesMap.put("Premier", 3);
		packagesOfServicesMap.put("Sberbank is the first", 4);
	}

	static {
		timesMap.put("Current", 2);
		timesMap.put("Custom", 3);
	}
	
	/**
	 * Constructor	
	 * @param currentParametr
	 */
	public TestForSber(String currentParametr) {
		this.currentParametr = currentParametr;
	}


    @Parameters
    public static Collection<String[]> testData() throws IOException {
    	return getTestData("convertation_test_data.csv"); // 
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

	
	/*
	 * Тест проверяет конвертацию валют. Данные используются из файла convertation_test_data.csv
	 */
	@Test
	public void testConvertationofCurrency() {
		/**
		 * Извлечение конкретных параметров в отдельные переменные:
		 * @money - количество денег
		 * @currencyFromP - валюта из которой конветировать
		 * @currencyInP - валюта в которую конвертировать
		 * @resourceP - источник
		 * @recieveringP - получение
		 * @exchangeMethodP - метод обмена
		 * @packageOfServicesP - пакет услуг
		 * @timeP - время
		 * 
		 * Полученные перемены будут использоваться при получении значений для css selector 
		 */
		String[] parameter = currentParametr.split(";");
		String money = parameter[0];
		String currencyFromP = parameter[1];
		String currencyInP = parameter[2];
		String resourceP = parameter[3];
		String recieveringP = parameter[4];
		String exchangeMethodP = parameter[5];
		String packageOfServicesP = parameter[6];
		String timeP = parameter[7];
		
		try {
			WebElement backgroundEl = driver.findElement(By.cssSelector(".base-grid-3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
			
			/*
			 * Найти элемент, в который вносится количество денег
			 */
			WebElement moneyToConvertEl = driver.findElement(By.cssSelector("div.filter-block-line-right:nth-child(1) > form:nth-child(1) > input:nth-child(1)"));
			
			/*
			 * Отчистить поле. Внести нужное количество денег.
			 */
			moneyToConvertEl.sendKeys(Keys.CLEAR, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, money);
			backgroundEl.click();
			
			Thread.sleep(2000);
			
			/*
			 * Найти элемент, в котором выбирается валюта из которой конвертировать
			 */
			WebElement currencyFromPopUpEl = driver.findElement(By.cssSelector("div.filter-block-line:nth-child(3) > div:nth-child(2)"));
			currencyFromPopUpEl.click();
			Thread.sleep(2000);
			
			/*
			 * Конвертировать из:
			 * RUB div.visible > span:nth-child(1)
			 * CHF div.visible > span:nth-child(2)
			 * EUR div.visible > span:nth-child(3)
			 * GBP div.visible > span:nth-child(4)
			 * JPU div.visible > span:nth-child(5)
			 * USD div.visible > span:nth-child(6)
			 * 
			 */
			WebElement currencyFromEl = driver.findElement(By.cssSelector("div.visible > span:nth-child(" + currencyMap.get(currencyFromP) + ")"));
			currencyFromEl.click();
			
			/*
			 * Найти элемент, в котором выбирается валюта в которую конвертировать
			 */
			WebElement currencyToPopUpEl = driver.findElement(By.cssSelector("div.filter-block-line:nth-child(4) > div:nth-child(2)"));
			currencyToPopUpEl.click();
			Thread.sleep(2000);
			
			/*
			 * Конвертировать в:
			 * RUB div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(1)
			 * CHF div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(2)
			 * EUR div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(3)
			 * GBP div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(4)
			 * JPU div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(5)
			 * USD div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(6)
			 */
			WebElement currencyToEl = driver.findElement(By.cssSelector("div.filter-block-line:nth-child(4) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(" + currencyMap.get(currencyInP) + ")"));
			currencyToEl.click();
			
			/*
			 * Источник:
			 * Card div.filter-block:nth-child(2) > label:nth-child(2)
			 * Account div.filter-block:nth-child(2) > label:nth-child(3)
			 * Cash div.filter-block:nth-child(2) > label:nth-child(4)
			 */
			WebElement resourceEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(2) > label:nth-child(" + resourcesAndRecieveringMap.get(resourceP) + ")"));
			resourceEl.click();
			
			/*
			 * Получение:
			 * Card div.filter-block:nth-child(3) > label:nth-child(2)
			 * Account div.filter-block:nth-child(3) > label:nth-child(3)
			 * Cash div.filter-block:nth-child(3) > label:nth-child(4)
			 */
			WebElement receiveringEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(3) > label:nth-child(" + resourcesAndRecieveringMap.get(recieveringP) + ")"));
			receiveringEl.click();
						
			/*
			 * Способ обмена:
			 * Inernet bank div.filter-block:nth-child(4) > label:nth-child(2)
			 * Department div.filter-block:nth-child(4) > label:nth-child(3)
			 * ATM div.filter-block:nth-child(4) > label:nth-child(4)
			 */
			WebElement exchangeMethodEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(4) > label:nth-child(" + exchangeMethodsMap.get(exchangeMethodP) + ")"));
			exchangeMethodEl.click();

			/*
			 * Пакет услуг:
			 * No package div.filter-block:nth-child(5) > label:nth-child(2)
			 * Premier div.filter-block:nth-child(5) > label:nth-child(3)
			 * Sberbank is the first div.filter-block:nth-child(5) > label:nth-child(4)
			 */
			WebElement packageOfservicesEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(5) > label:nth-child(" + packagesOfServicesMap.get(packageOfServicesP) + ")"));
			packageOfservicesEl.click();
			
			/*
			 * Время:
			 * Current div.filter-block:nth-child(6) > label:nth-child(2)
			 * Custom div.filter-block:nth-child(6) > label:nth-child(3)
			 */
			WebElement timeEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(6) > label:nth-child(" + timesMap.get(timeP) + ")"));
			timeEl.click();
			
			/*
			 * Показать результат
			 */
			WebElement showEl = driver.findElement(By.cssSelector("div.filter-block:nth-child(7) > button:nth-child(1)"));
			showEl.click();			
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void tes2()
	{
		String[] parameter = currentParametr.split(";");
		String dataFrom = parameter[8];
		String dataTo = parameter[9];
		/*
		 * Открыть окно динамиука изменения курсов 
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
		setDate(dataFromInputEl, dataFrom);
		
		
		/*
		 * Найти элемент, в котором выбирается конечная дата и установить её
		 */
		WebElement dataToEl = driver.findElement(By.cssSelector("div.filter-datepicker:nth-child(4) > input:nth-child(1)"));
		setDate(dataToEl, dataTo);
		
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
	
	@Test
	public void test3(){
		
		/*
		 * Получить дату из параметра
		 */
		String[] parameter = currentParametr.split(";");
		String data = parameter[10];
		
		/*
		 * Найти элемент Расширеная таблица курсов и открыть её
		 */
		WebElement extendedTableEl = driver.findElement(By.cssSelector(".rates-tabs > li:nth-child(2) > span:nth-child(1)"));
		extendedTableEl.click();
		
		/*
		 * Найти элемент, в котором выбирается дата и установить её
		 */
		WebElement dateEl = driver.findElement(By.cssSelector(".hasDatepicker"));
		setDate(dateEl, data);
		
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
	
	/*
	 * Установить дату в определенный элемент
	 */
	private void setDate(WebElement dataEl, String dateString) {
		dataEl.clear();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		/*
		 * Удалить дату по умолчанию
		 */
		for (int i = 0; i < 10; i++) {
			dataEl.sendKeys(Keys.BACK_SPACE);
		}
		
		dataEl.sendKeys(dateString, Keys.ENTER); // 
		
	}
	
	/*
	 * Получить тестовые данные из файла
	 */
    public static Collection<String[]> getTestData(String fileName) throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while ((record = file.readLine()) != null) {
            String[] fields = record.split(",");
            records.add(fields);
        }
        file.close();
        return records;
    }


	@After
	public void tearDown() throws Exception {
	    driver.quit();
	}

}
