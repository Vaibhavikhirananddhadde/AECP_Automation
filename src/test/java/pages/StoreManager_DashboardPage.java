package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class StoreManager_DashboardPage extends BaseClass{
	public StoreManager_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Store Management']") WebElement navlink_StoreManager;
	@FindBy(xpath="//a[text()='Vendor List']") WebElement navlink_VendorList;
	@FindBy(xpath="//a[text()='Vendor Management']") WebElement navlink_VendorManagement;
	@FindBy(xpath="//a[text()='Wastage Maintenance']") WebElement navlink_wastageMaintenance;
	@FindBy(xpath="//span[text()='Invoice']") WebElement navlink_invoice;
	
	public void clickOnStoreManagement() {
		navlink_StoreManager.click();
		String exp_URL="https://aecp.aecearth.io/store-admin/store-management/Storemanagement4";
		String act_URL = driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Store management page is not displayed!");
	}
	
	public void clickOnVendorList() {
		navlink_VendorList.click();
		String exp_URL = "https://aecp.aecearth.io/store-admin/store-management/Vendorlist";
		String act_URL = driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Vendor list page is not displayed!");
	}
	
	public void clickOnVendorManagement() {
		navlink_VendorManagement.click();
		String exp_URL = "https://aecp.aecearth.io/store-admin/store-management/Vendormanagement";
		String act_URL = driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Vendor management page is not displayed!");
	}
	
	public void clickOnWastageMaintenance() {
		navlink_wastageMaintenance.click();
		String exp_URL = "https://aecp.aecearth.io/store-admin/store-management/Wasteage4";
		String act_URL=driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Wastage maintenance page is not displayed!");
	}
	
	public void clickOnInvoice() {
		navlink_invoice.click();
		String exp_URL = "https://aecp.aecearth.io/store-admin/store-management/Invoice4";
		String act_URL = driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Invoice page is not displayed!");
	}

}
