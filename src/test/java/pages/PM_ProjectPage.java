package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class PM_ProjectPage extends BaseClass{
	public PM_ProjectPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Search Project']") WebElement txt_searchfield;
	@FindBy(xpath="//a[@href='/project-admin/project-management/viewproject/169']") WebElement icon_eye;
	@FindBy(xpath="//div[@class='d-flex justify-content-center']")WebElement viewProjectDetails;
	
	public void checkProjectDetails() {
		txt_searchfield.sendKeys("Test Project1");
		
		List<WebElement> AllProjectCards = driver.findElements(By.xpath("//div[@class='row']//div[@class='card-body']//h1"));
		for(WebElement projectCard:AllProjectCards) {
			if(projectCard.getText().equals("Test Project1")) {
				projectCard.click();
				waitExplicit(icon_eye);
				icon_eye.click();
				waitExplicit(viewProjectDetails);
				Assert.assertTrue(viewProjectDetails.isDisplayed(), "Project Details are not displayed!");
			}
		}
	}

}
