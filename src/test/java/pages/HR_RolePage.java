package pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class HR_RolePage extends BaseClass{
	public HR_RolePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//i[@class='fa fa-edit']")WebElement icon_Edit;
	
	@FindBy(xpath="//input[@placeholder='Search Role']")WebElement txt_search;
	
	@FindBy(name="role_name")WebElement txt_RoleName;
	
	@FindBy(xpath="//button[text()='Update Role']")WebElement btn_Submit;
	
	@FindBy(xpath="//p[contains(text(),'Role configuration updated successfully.')]")WebElement msg_SuccessfulEdit;
	
	@FindBy(xpath="//button[text()='OK']")WebElement btn_OK;
	
	@FindBy(xpath="//button[text()='Cancel']")WebElement btn_cancelUpdate;
	
	@FindBy(xpath="//h1[text()='Roles List']")WebElement pageHeader;
	
	public void editRole() {
		txt_search.sendKeys("Project Engineer");
		icon_Edit.click();
		 // Wait for edit field to become visible and clickable
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(txt_RoleName));

	    // Use JavaScript to clear if normal clear() doesn't work
	    txt_RoleName.click();
	    txt_RoleName.sendKeys(Keys.chord(Keys.COMMAND, "a"));// select all text
	    txt_RoleName.sendKeys(Keys.DELETE); // delete selected text

	    // Now enter new text
	    txt_RoleName.sendKeys("Project Engineer");

	    btn_Submit.click();

	    // Wait for success message
	    wait.until(ExpectedConditions.visibilityOf(msg_SuccessfulEdit));
	    Assert.assertTrue(msg_SuccessfulEdit.isDisplayed(), "Successful message is not displayed!");

	    btn_OK.click();
	}
	
	
	public void cancelEditRole() {
		txt_search.sendKeys("Project Engineer");
		icon_Edit.click();
		txt_RoleName.clear();
		txt_RoleName.sendKeys("Project Engine");
		btn_cancelUpdate.click();
		Assert.assertTrue(pageHeader.isDisplayed(), "Role updation has not cancelled!");
	}

}
