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

public class StoreManager_StoreManagementPage extends BaseClass{
	public StoreManager_StoreManagementPage() {
		PageFactory.initElements(driver, this);
	}
	//Add inhouse stock	
	@FindBy(xpath="//button[text()='Add Stock ']") WebElement btn_AddStock;
	@FindBy(xpath="//button[text()='Add Single Material']") WebElement btn_AddSingleStock;
	@FindBy(name="product_name") WebElement txt_productName;
	@FindBy(name="total_stock") WebElement txt_totalStock;
	@FindBy(name="available_stock") WebElement txt_currentStock;
	@FindBy(name="cost") WebElement txt_cost;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_submit;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[last()])[1]") WebElement newRow_InhouseStock;
	
	//Material Delivery
	@FindBy(xpath="//button[text()='Material Delivery ']") WebElement btn_MaterialDelivery;
	@FindBy(xpath="//button[text()='Add Single Material Delivery']") WebElement btn_singleMaterialDelivery;
	@FindBy(xpath="(//button[text()='Select Project'])[3]") WebElement dd_selectProject_materialDelivery;
	@FindBy(xpath="(//button[text()='Test Project'])[4]") WebElement opt_testProject_materialDelivery;
	@FindBy(xpath="(//button[text()='Select Product'])[3]") WebElement dd_selectProduct_materialDelivery;
	@FindBy(xpath="(//button[text()='Enter Product Name'])[2]") WebElement opt_EnterProductName_materialDelivery;
	private By projects = By.xpath("//div[@class='dropdown-menu show']//button");
	@FindBy(name="quantity_delivered") WebElement txt_quantityDelivered;
	@FindBy(name="delivery_date") WebElement date_deliveryDate;
	@FindBy(name="delivered_to_name") WebElement txt_deliveredTo;
	@FindBy(xpath="//input[@name='product_cost']") WebElement Enter_cost;
	@FindBy(xpath="//input[@name='material_tracking_number']") WebElement txt_materialTrackingNumber;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_submitMaterialDelivery;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[1])[2]") WebElement newRow_MaterialDelivery;
	
	//Material return
	@FindBy(xpath="//button[text()='Add Return Materials']") WebElement btn_addReturnMaterial;
	@FindBy(xpath="//button[text()='Add Single Return Material']") WebElement btn_addSingleReturnMaterial;
	@FindBy(xpath="(//button[text()='Select Product'])[3]") WebElement dd_selectProduct_ReturnStock;
	@FindBy(xpath="(//button[text()='Test Product'])[3]") WebElement opt_TestProduct_returnStock;
	@FindBy(xpath="//select[@name='material_tracking_number']") WebElement materialTracking_return;
	@FindBy(name="quantity_returned") WebElement txt_quantityReturned;
	@FindBy(name="return_date") WebElement txt_returnDate;
	@FindBy(xpath="//select[@name='returned_by']") WebElement dd_returnedBy;
	@FindBy(xpath="(//button[text()='Enter Product Name'])[2]") WebElement prod_EnterProductName;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[last()])[3]") WebElement newRow_materialReturn;
	
	//ProjectReport
	@FindBy(xpath="//button[text()='Generate Report']") WebElement btn_generateReport;
	@FindBy(xpath="//button[text()='Export CSV']") WebElement btn_exportCSV;
	private By projectReport_columns = By.xpath("(//table[@class='align-items-center table-flush table']/tbody)[last()]/tr");
	
	public void addSingleInhouseStock() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));	
		btn_AddStock.click(); 
		btn_AddSingleStock.click();
		wait.until(ExpectedConditions.elementToBeClickable(txt_productName));
		txt_productName.sendKeys("Enter Product name");
		txt_totalStock.sendKeys("40");
		txt_currentStock.sendKeys("10");
		txt_cost.sendKeys("5000");
		btn_submit.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Storemanagement4");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(newRow_InhouseStock));
		Assert.assertTrue(newRow_InhouseStock.isDisplayed());
		System.out.println("New inhouse stock got added successfully the new added row is \n "+newRow_InhouseStock.getText().trim());
	}
	
	public void materialDelivery() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		scrolltoview(btn_MaterialDelivery);
		btn_MaterialDelivery.click();
		btn_singleMaterialDelivery.click();
		dd_selectProject_materialDelivery.click();
		//Select project
		opt_testProject_materialDelivery.click();
		dd_selectProduct_materialDelivery.click();
		//Select product
		opt_EnterProductName_materialDelivery.click();
		txt_quantityDelivered.sendKeys("10");
		date_deliveryDate.sendKeys("13/11/2025");
		txt_deliveredTo.sendKeys("Divya");
		Enter_cost.sendKeys("5000");
		txt_materialTrackingNumber.sendKeys("1234");
		btn_submitMaterialDelivery.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Storemanagement4");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(newRow_MaterialDelivery));
		Assert.assertTrue(newRow_MaterialDelivery.isDisplayed());
		System.out.println("New Material delivery got added successfully newly added row is \n"+newRow_MaterialDelivery.getText().trim());
		
	}
	
	public void materialReturn() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		scrolltoview(btn_addReturnMaterial);
		btn_addReturnMaterial.click();
		btn_addSingleReturnMaterial.click();
		dd_selectProduct_ReturnStock.click();
		wait.until(ExpectedConditions.elementToBeClickable(prod_EnterProductName));
		prod_EnterProductName.click();
		//Select project
		dd_selectProject_materialDelivery.click();
		List<WebElement> projectOptions = driver.findElements(projects);
		for(WebElement project:projectOptions) {
			if(project.getText().equals("Test Sabari 1")) {
				project.click();
			}
		}
		Select trackingNumber = new Select(materialTracking_return);
		trackingNumber.selectByVisibleText("1234");
		txt_quantityReturned.sendKeys("10");
		txt_returnDate.sendKeys("12/11/2025");
		dd_returnedBy.click();
		btn_submit.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Storemanagement4");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(newRow_materialReturn));
		Assert.assertTrue(newRow_materialReturn.isDisplayed());
		System.out.println("New Material return got added successfully newly added row is \n"+newRow_materialReturn.getText().trim());
	}
	
	public void generateReportAndDownload() throws InterruptedException {
		scrolltoview(btn_generateReport);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(btn_generateReport));
		btn_generateReport.click();
		Thread.sleep(3000);
		btn_exportCSV.click();
		List<WebElement> reportColumnsData = driver.findElements(projectReport_columns);
		for(WebElement columnRow :reportColumnsData) {
			wait.until(ExpectedConditions.visibilityOfAllElements(reportColumnsData));
			System.out.println(columnRow.getText().trim());
			Assert.assertTrue(columnRow.isDisplayed());
		}
		System.out.println("Report generated successfully.");
		
	}

}
