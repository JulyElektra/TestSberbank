package other;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ElementDataSetter {

	/*
	 * Метод устанавливает дату в определенный элемент
	 */
	public static void setDateInElement(WebElement dataEl, String dateString) {
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
}
