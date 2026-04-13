import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//if expected result is empty then only click login button when login form is opened
if (expectedResult == GlobalVariable.EMPTY) {
	/**This function execute:
	 * Step 1: Navigate to home page
	 * Step 2: Open Login form
	 */
	generalUtils.GeneralUtils.openLoginForm()
	
	//Click login button
	loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Login'))
	
	//Verify 'No empty field!' message in email field
	WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/emptyEmail'))
	
	//Verify 'No empty field!' message in password field
	WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/emptyPassword'))
}else {
	
	/**This function execute:
	 * Step 1: Navigate to home page
	 * Step 2: Open Login form
	 * Step 3: Fill email and password field
	 * Step 4: Click on login button
	 */
	loginUtils.LoginElements.testLoginInLoginForm(email, password)
	
	//Verify 'No empty field!' message when other field is filled
	if (expectedResult == GlobalVariable.WRONG_EMAIL) {
		//Verify 'No empty field!' message in password field
		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
	}else if (expectedResult == GlobalVariable.WRONG_PASSWORD) {
		//Verify 'No empty field!' message in password field
		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
	}

}

