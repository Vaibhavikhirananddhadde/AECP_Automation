package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class PM_MaterialRequestPage extends BaseClass{
	public PM_MaterialRequestPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary'][text()='Request Materials']") WebElement dd_RequestMaterials;
	
	@FindBy(xpath="//button[text()='Add Single Material']") WebElement btn_addSingleMaterial;
	
	@FindBy(xpath="//select[@name='p_id']") WebElement dd_projectName;
	
	@FindBy(xpath="//select[@name='g_m_id']") WebElement dd_generalManager;
	
	@FindBy(name="mat_name") WebElement txt_materialName;
	
	@FindBy(name="mat_quantity") WebElement txt_materialQuantity;
	
	@FindBy(xpath="//select[@name='mat_priority']") WebElement dd_materialPriority;
	
	@FindBy(name="mat_price") WebElement txt_materialPrice;
	
	@FindBy(xpath="//p[text()='Material request submitted successfully!']") WebElement msg_successMatReq;
	
	@FindBy(xpath="//button[text()='OK']") WebElement btn_OK;
	
	@FindBy(xpath="//button[text()='Upload']") WebElement btn_upload;
	
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit;
	
	//Request Single material
	public void requestMaterial() throws InterruptedException {
		dd_RequestMaterials.click();
		Thread.sleep(3000);
		btn_addSingleMaterial.click();
		/*List<WebElement> dd_options = driver.findElements(By.xpath("//div[@class='dropdown-menu show']/button"));
		for(int i=0; i<dd_options.size(); i++) {
			if(dd_options.get(i).getText().equals("Add Single Material")) {
				dd_options.get(i).click();
			}
		}*/
		
	    waitExplicit(dd_projectName);
		//select project name from dropdown
		Select project_name = new Select(dd_projectName);
		project_name.selectByVisibleText("Test Project1");
		
		//Select general manager.
		Select gm_name= new Select(dd_generalManager);
		gm_name.selectByVisibleText("Ashwini Satti [General Manager]");
		
		txt_materialName.sendKeys("Enter material name");
		txt_materialQuantity.sendKeys("23");
		
		//Select material priority
		Select priority = new Select(dd_materialPriority);
		priority.selectByVisibleText("Low");
		
		txt_materialPrice.sendKeys("20000");
		btn_Submit.click();
		
		//Check for successful message.
		waitExplicit(msg_successMatReq);
		Assert.assertTrue(msg_successMatReq.isDisplayed(), "Successful message is not displayed!");
		btn_OK.click();
	
	}

}
