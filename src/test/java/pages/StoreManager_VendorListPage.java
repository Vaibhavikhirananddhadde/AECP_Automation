package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class StoreManager_VendorListPage extends BaseClass{
	public StoreManager_VendorListPage() {
		PageFactory.initElements(driver, this);
	}
	
	private By vendorList = By.xpath("//div[@class='g-3 row']/div");
	private By vendorDetails = By.xpath("//div[@class='g-3 row']/div//div[@class='col-8']");
	@FindBy(xpath="//button[text()='Add Vendor']") WebElement btn_addVendor;
	@FindBy(xpath="//input[@type='file']") WebElement btn_uploadImage;
	@FindBy(id="vendor_name") WebElement txt_vendorName;
	@FindBy(id="vendor_address") WebElement txt_vendorAddress;
	@FindBy(id="vendor_number") WebElement txt_vendorNumber;
	@FindBy(id="vendor_email") WebElement txt_vendorEmail;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit;
	@FindBy(xpath="(//span[@class='dropdown-toggle'])[last()]") WebElement card_keybabManu;
	
	//Edit details
	@FindBy(xpath="(//button[text()='Edit'])[last()]") WebElement btn_Edit;
	@FindBy(xpath="//input[@name='vendor_name']") WebElement txt_VendorName;
	@FindBy(xpath="//input[@name='vendor_address']") WebElement txt_Vendor_Address;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit_Edit;
	@FindBy(xpath="(//div[@class='g-3 row']/div//div[@class='fw-bold mb-1'])[last()]") WebElement newVendorName;
	
	public void checkVendorCards() {
		List<WebElement> vendorCards = driver.findElements(vendorList);
		for(WebElement vendor:vendorCards) {
			Assert.assertTrue(vendor.isDisplayed(), "Vendor list is not displayed!");
			List<WebElement> vendorsdetail = driver.findElements(vendorDetails);
			for(WebElement detail:vendorsdetail) {
				System.out.println("Vendor details are : "+detail.getText().trim());
				Assert.assertTrue(detail.isDisplayed(), "Vendor details are not displayed in the card");
			}
		}
	}
	
	public void addVendor() throws InterruptedException {
		btn_addVendor.click();
		//wait.until(ExpectedConditions.visibilityOf(btn_uploadImage));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.display='block';", btn_uploadImage);
	
		btn_uploadImage.sendKeys("/Users/admin/Downloads/ChatGPT Image Jun 24, 2025, 10_44_23 AM.png");
		Thread.sleep(3000);
		txt_vendorName.sendKeys("vendor name X");
		txt_vendorAddress.sendKeys("India");
		txt_vendorNumber.sendKeys("1234566789");
		txt_vendorEmail.sendKeys("test@example.com");
		btn_Submit.click();
		Thread.sleep(3000);
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendorlist");
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='g-3 row']/div)[last()]")).isDisplayed(), "New vendor is not added!");
		System.out.println(driver.findElement(By.xpath("(//div[@class='g-3 row']/div//div[@class='col-8'])[last()]")).getText().trim());
	}
	
	public void editVendorDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(card_keybabManu));
		card_keybabManu.click();
		btn_Edit.click();
		txt_VendorName.sendKeys("Vendor Y");
		txt_Vendor_Address.sendKeys("Bangalore");
		btn_Submit_Edit.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendorlist");
		wait.until(ExpectedConditions.visibilityOf(newVendorName));
		Assert.assertEquals(newVendorName.getText().trim(), "Vendor Y", "Details are not edited");
		System.out.println("Vendor name is edited to "+newVendorName.getText().trim()+ " Successfully");
		
	}

}
