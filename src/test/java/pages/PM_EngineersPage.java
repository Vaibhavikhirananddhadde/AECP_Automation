package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class PM_EngineersPage extends BaseClass{
	public PM_EngineersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void Check_SiteEngineersList() {
		List<WebElement> emp_List = driver.findElements(By.xpath("//div[@class='row']//div[@class='p-3 shadow-sm card']//div[@class='fw-bold mb-1']"));
		for(WebElement emp:emp_List) {
			System.out.println(emp.getText());
			Assert.assertTrue(emp.isDisplayed());
		}
	}

}
