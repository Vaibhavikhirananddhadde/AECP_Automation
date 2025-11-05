package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class HR_DepartmentPage extends BaseClass{
	public HR_DepartmentPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='ADD']")
	WebElement btn_Add;
	
	@FindBy(xpath="//input[@name='dept_name']")
	WebElement txt_deptName;
	
	@FindBy(xpath="//input[@id='input-file']")
	WebElement file_DeptLogo;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']//tbody/tr//i[contains(@id,'delete-tooltip')])[last()]")
	WebElement icon_delete;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement btn_YES;
	
	@FindBy(xpath="//button[text()='No']")
	WebElement btn_NO;
	
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']//tbody/tr//h4)[last()]")
	WebElement rowLast_DeptName;
	
	public void addingDepartment() {
		btn_Add.click();
		txt_deptName.sendKeys("Test Department");
		file_DeptLogo.sendKeys("/Users/admin/Downloads/ChatGPT Image Sep 16, 2025, 03_02_23 PM.png");
		btn_Submit.click();
		List<WebElement> allDepartments = driver.findElements(By.xpath("//table[@class='align-items-center table-flush table']//tbody/tr"));
		for(WebElement department:allDepartments) {
			String deptName = department.getText();
			if(deptName.equals("Test Department")) {
				Assert.assertEquals(deptName, "Test Department", "Department has not added successfully.");
			}
		}
		
	}
	public void deleteDepartment() {
		icon_delete.click();
		btn_YES.click();
		driver.navigate().refresh();
		String departmentName = rowLast_DeptName.getText();
		Assert.assertNotEquals(departmentName, "Test Department", "Department has not deleted!");
	}
	

}
