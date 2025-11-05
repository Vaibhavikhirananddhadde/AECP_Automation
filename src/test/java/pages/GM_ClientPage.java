package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class GM_ClientPage extends BaseClass{
	
	@FindBy(css = "input[type='file'][accept*='image'], input[type='file'][name*='profile'], input[type='file']")
	WebElement upload_ProfileImage;
	
	@FindBy(xpath="//button[text()='Add Client']")
	WebElement btn_addClient;
	
	@FindBy(xpath="//input[@placeholder='Client Name']")
	WebElement txt_ClientName;
	
	@FindBy(xpath="//input[@name='client_email']")
	WebElement txt_ClientEmail;
	
	@FindBy(xpath="//input[@name='client_number']")
	WebElement txt_ClientNumber;
	
	@FindBy(xpath="//input[@name='company_name']")
	WebElement txt_companyName;
	
	@FindBy(xpath="//input[@name='address']")
	WebElement txt_Location;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	public GM_ClientPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addClient() throws InterruptedException {
		btn_addClient.click();
		upload_ProfileImage.sendKeys("/Users/admin/downloads/ChatGPT Image Aug 4, 2025, 12_00_24 PM (1).png");
		Thread.sleep(3000);
		txt_ClientName.sendKeys("Test Client");
		txt_ClientEmail.sendKeys("test@example.com");
		txt_ClientNumber.sendKeys("1230984567");
		txt_companyName.sendKeys("Mine IT");
		txt_Location.sendKeys("Address 123");
		btn_Submit.submit();
		try {
			isAlertPresent();
		}catch(Exception e) {
			
		}
		driver.navigate().refresh();
	
		List<WebElement> ClientsName = driver.findElements(By.xpath("//div[@class='col-md-3']//h3"));
		for(int i=0;i<ClientsName.size();i++) {
			if(ClientsName.get(i).getText().contains("Test Client")) {
				Assert.assertEquals(ClientsName.get(i).getText(), "Test Client,", "Client has not added");
			}
		}
		
	}

}
