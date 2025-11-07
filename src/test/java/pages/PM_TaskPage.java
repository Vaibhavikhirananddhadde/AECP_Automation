package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class PM_TaskPage extends BaseClass{
	public PM_TaskPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Add Task']") WebElement btn_AddTask;
	@FindBy(xpath="//select[@name='projectid']") WebElement dd_ProjectName;
	@FindBy(name="taskname") WebElement txt_TaskName;
	@FindBy(xpath="//div[@class='form-control-alternative']") WebElement dd_AssignTo;
	@FindBy(id="input-starting") WebElement date_start;
	@FindBy(id="input-ending") WebElement date_end;
	@FindBy(id="input-file") WebElement upload_file;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit;
	@FindBy(xpath="//p[text()='Task added successfully!']") WebElement msg_successfulTaskAddition;
	@FindBy(xpath="//button[text()='OK']") WebElement btn_OK;
	
	public void addTask() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    btn_AddTask.click();

	    // dynamic task name so backend doesn’t reject duplicates
	    String taskName = "Auto Task " + System.currentTimeMillis();

	    Select projectList = new Select(dd_ProjectName);
	    projectList.selectByVisibleText("Test Project1");

	    txt_TaskName.clear();
	    txt_TaskName.sendKeys(taskName);

	    dd_AssignTo.click();
	    List<WebElement> employees = driver.findElements(
	        By.xpath("//div[@class='assign-dropdown-container']//input[@type='checkbox']")
	    );
	    for (WebElement emp : employees) {
	        if (!emp.isSelected()) {
	            emp.click();
	        }
	    }

	  //  date_start.click();
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	 // --- Start date ---
	 js.executeScript("arguments[0].click();", date_start);   // focus the field without interception
	 date_start.sendKeys(Keys.chord(Keys.COMMAND, "a"));      // select all (CMD on Mac)
	 date_start.sendKeys("06-11-2025"+Keys.TAB+"10:00AM");                // valid datetime-local value
	 date_start.sendKeys(Keys.TAB);                           // blur -> fires change

	 // --- End date ---
	 js.executeScript("arguments[0].click();", date_end);
	 date_end.sendKeys(Keys.chord(Keys.COMMAND, "a"));
	 date_end.sendKeys("22-11-2025"+Keys.TAB+"12:00PM");
	 date_end.sendKeys(Keys.TAB);
	 
	 
	    upload_file.sendKeys("/Users/admin/Downloads/attendance_list.xlsx");

	    // SINGLE click with explicit wait
	    System.out.println("About to click Submit");
	    wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//form[@enctype='multipart/form-data']//button[@type='submit']")
	    )).click();
	    System.out.println("Clicked Submit – waiting for success toast");

	    // Now wait for the SUCCESS message.
	    // If it never appears, this is where we fail – not at the click.
	    try {
	        WebElement success = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//p[text()='Task added successfully!']")
	            )
	        );
	        Assert.assertTrue(success.isDisplayed(), "Success message not displayed!");
	        btn_OK.click();
	    } catch (Exception e) {
	        // Debug – no success toast → submission failed on backend or validation
	        System.out.println("Task was NOT added successfully – success toast never appeared.");
	        // Optionally: check for error message element here.
	        throw e;
	    }

	    driver.navigate().refresh();

	    // verify the new task title in To-do column using the same dynamic name
	    List<WebElement> allTaskTitle = driver.findElements(
	        By.xpath("(//div[@class='col-lg-4'])[1]//div[contains(@class,'card-stats') and contains(@class,'card')]//h3")
	    );
	    boolean found = false;
	    for (WebElement task : allTaskTitle) {
	        if (task.getText().equals(taskName)) {
	            found = true;
	            Assert.assertEquals(task.getText(), taskName, "Task not added successfully!");
	            break;
	        }
	    }
	    Assert.assertTrue(found, "Task title not found in To-do section.");
	}

}
