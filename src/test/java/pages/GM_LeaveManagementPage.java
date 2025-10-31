package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class GM_LeaveManagementPage extends BaseClass{
	@FindBy(xpath="//button[text()='OK']")
	WebElement btn_successfulOK;
	
	@FindBy(xpath="//p[text()='Leave status updated to Approved successfully!']")
	WebElement msg_SuccessApprove;
	
	@FindBy(xpath="//p[text()='Leave status updated to Rejected successfully!']")
	WebElement msg_SuccessReject;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[7]")
	WebElement btn_Action;
	
	@FindBy(xpath="(//button[text()='Approved'])[1]")
	WebElement btn_Approved;
	
	@FindBy(xpath="(//button[text()='Rejected'])[1]")
	WebElement btn_Rejected;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[5]")
	WebElement firstRowStatus;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[1]")
	WebElement firstRowName;
	
	public GM_LeaveManagementPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void approveHRLeave() {
		if(firstRowName.getText().equals("Sabari Raj S R")) {
	      if(!firstRowStatus.getText().equals("APPROVED")) {
	    	  btn_Action.click();
	    	  btn_Approved.click();
	    	  Assert.assertTrue(btn_successfulOK.isDisplayed());
	    	  btn_successfulOK.click();
	    	  Assert.assertTrue(msg_SuccessApprove.isDisplayed());
	    	  System.out.println(msg_SuccessApprove.getText());
	      }
		}	
	}
	
	public void rejectHRLeave() {
		if(firstRowName.getText().equals("Sabari Raj S R")) {
	      if(!firstRowStatus.getText().equals("REJECTED")) {
	    	  btn_Action.click();
	    	  btn_Rejected.click();
	    	  Assert.assertTrue(btn_successfulOK.isDisplayed());
	    	  btn_successfulOK.click();
	    	  Assert.assertTrue(msg_SuccessReject.isDisplayed());
	    	  System.out.println(msg_SuccessReject.getText());
	      }
		}	
	}
	
	public void leaveRequestsDisplayed() {
		waitExplicit(firstRowName);	
		if(firstRowName.getText().equals("Sabari Raj S R")) {
			Assert.assertEquals(firstRowName.getText(), "Sabari Raj S R");
			System.out.println("Leave requested by HR displayed in GM panel");
		}
	}

}
