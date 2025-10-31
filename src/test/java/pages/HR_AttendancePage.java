package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class HR_AttendancePage extends BaseClass{
	public HR_AttendancePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][1]")
	WebElement dd_month;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][2]")
	WebElement dd_years;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][3]")
	WebElement dd_empName;
	
	@FindBy(xpath="//select[@class='form-select form-control-alternative custom-select'][4]")
	WebElement dd_status;
	
	@FindBy(xpath="(//button[text()='Select Date'])[2]")
	WebElement btn_date;
	
	@FindBy(xpath="(//button[text()='Download'])[2]")
	WebElement btn_download;
	
	public void checkAttendanceDisplayed() {
		Select months = new Select(dd_month);
		months.selectByVisibleText("September");
		
		Select years = new Select(dd_years);
		years.selectByVisibleText("2025");
		
		Select employees = new Select(dd_empName);
		employees.selectByVisibleText("Vaibhavi");
		
		Select status = new Select(dd_status);
		status.selectByVisibleText("online");
		
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[contains(@class,'table-flush table')]/tbody/tr"));
		for(int i=0; i<tableRows.size();i++) {
			System.out.println(tableRows.get(i).getText());
		}
		
		waitExplicit(btn_download);
		
		String expectedURL = "https://aecp.aecearth.io/hr-admin/hr-management/attendance";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "Attendance details are not downloaded!.");
	}

}
