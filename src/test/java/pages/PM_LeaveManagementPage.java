package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class PM_LeaveManagementPage extends BaseClass{
	public PM_LeaveManagementPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Apply Leave']") WebElement btn_ApplyLeave;
	@FindBy(xpath="//select[@name='leave_type']") WebElement dd_leaveType;
	@FindBy(id="input-starting") WebElement date_starting;
	@FindBy(id="input-ending") WebElement date_ending;
	@FindBy(id="input-description") WebElement txt_description;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_submit;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody//td)[1]") WebElement row_name;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody//td[3])[1]") WebElement row_startDate;
	private By ids= By.xpath("//table[@class='align-items-center table-flush table']/tbody//th");
	
	public void applyLeave() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement>numberOfLeaves = driver.findElements(ids);
		wait.until(ExpectedConditions.visibilityOfAllElements(numberOfLeaves));
		int before_count = numberOfLeaves.size();
		
		btn_ApplyLeave.click();
		Select leaveTypes = new Select(dd_leaveType);
		leaveTypes.selectByVisibleText("Sabbatical Leave");
		date_starting.sendKeys("14/12/2025");
		date_ending.sendKeys("13/12/2025");
		txt_description.sendKeys("Testing applying leave");
		btn_submit.click();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.get("https://aecp.aecearth.io/project-admin/project-management/leave");
		Thread.sleep(3000);
	
		System.out.println("Leave Applied on "+row_startDate.getText());
		
		List<WebElement>after_numberOfLeaves = driver.findElements(ids);
		wait.until(ExpectedConditions.visibilityOfAllElements(after_numberOfLeaves));
		int after_count = after_numberOfLeaves.size();
		Assert.assertNotEquals(after_count, before_count, "New Leave is not added!");
		
	}

}
