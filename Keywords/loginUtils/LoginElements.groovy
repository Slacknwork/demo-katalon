package loginUtils
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

//Interact and test with login elements
class LoginElements {
 /**
 * Refresh browser
 */
@Keyword
def refreshBrowser() {
    KeywordUtil.logInfo("Refreshing")
    WebDriver webDriver = DriverFactory.getWebDriver()
    webDriver.navigate().refresh()
    KeywordUtil.markPassed("Refresh successfully")
}

/**
 * Wait for element to clickable and Click element
 * @param to Katalon test object
 */
@Keyword
static def clickElement(TestObject to) {
    try {
        WebElement element = WebUI.findWebElement(to);
        KeywordUtil.logInfo("Clicking element")
		WebUI.waitForElementClickable(to, GlobalVariable.LONG_TIMEOUT)
        element.click()
        KeywordUtil.markPassed("Element has been clicked")
    } catch (WebElementNotFoundException e) {
        KeywordUtil.markFailed("Element not found")
    } catch (Exception e) {
        KeywordUtil.markFailed("Fail to click on element")
    }
}

//Fill email and password field
@Keyword
static def fillAllLoginFields(def email, def password) {
	//Wait for email field visibility and fill it
	if (email != null ) {
		TestObject emailTextbox = findTestObject('LoginFormElements/input_email')
		WebUI.waitForElementVisible(emailTextbox, GlobalVariable.LONG_TIMEOUT)
		WebUI.setText(emailTextbox, email)
	}
	
	//Wait for password field visibility and fill it
	if (password != null) {
		TestObject passwordTextbox = findTestObject('LoginFormElements/input_Password')
		WebUI.waitForElementVisible(passwordTextbox, GlobalVariable.LONG_TIMEOUT)
		WebUI.setEncryptedText(passwordTextbox, password)
	}

}

//Test login function in login form 
@Keyword
static def login(def email, def password) {
	//Step 1: Fill email and password field
	loginUtils.LoginElements.fillAllLoginFields(email, password)

	//Step 2: Click login button
	loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Login'))
}
	
//Perform login test in login form
@Keyword
static def executeLoginTest(def email, def password, int loginRetry) {
	//Step 1: Open login form
	generalUtils.GeneralUtils.openLoginForm()

	/**Contain next 2 steps:
	* Step 2: Fill email and password field
	* Step 3: Click login button
	*/
	if (loginRetry == null || loginRetry > 0) {
		for (int i = 0; i < loginRetry; i++) {
			loginUtils.LoginElements.login(email, password)
		}
	}else {
		loginUtils.LoginElements.login(email, password)	
	}
}

//Verify message visibility based on expected result
@Keyword
static def verifyMessageOfLoginTest(def expectedResult) {
	
	//nho fix FailureHandling
	
	switch (expectedResult) {
		case GlobalVariable.SUCCESS:
			//verify 'login successfully' message and user name visibility 
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/SuccessMessage/message_LoginSuccessfully'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/text_Username'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case GlobalVariable.FAILURE:
		case GlobalVariable.WRONG_EMAIL:
		case GlobalVariable.WRONG_PASSWORD:
			//Verify 'Wrong Email and Password' message visibility
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case GlobalVariable.MULTIPLE_FAILURE:
		case GlobalVariable.INVALID_PASSWORD:
		case GlobalVariable.LONG_EMAIL:
		case GlobalVariable.LONG_PASSWORD:
		case GlobalVariable.SHORT_PASSWORD:
			//Verify 'Wrong Email and Password' message is not visible
			WebUI.verifyElementNotVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'))
			break;
		case GlobalVariable.EMPTY:
			/**Verify 'No empty field!' message in based on expected result
		 	* The error message is shown in their respectively empty field
		 	* In EMPTY case, both email and password field is empty so need to error message for both
		 	**/
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyEmail'), FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyPassword'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case GlobalVariable.EMPTY_EMAIL:
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyEmail'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case GlobalVariable.EMPTY_PASSWORD:
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyPassword'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		}
	
//	if (expectedResult.equals(GlobalVariable.SUCCESS)) {
//		//verify 'login successfully' message
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/SuccessMessage/message_LoginSuccessfully'), FailureHandling.CONTINUE_ON_FAILURE)
//		
//		//verify user name visibility after login
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/text_Username'), FailureHandling.CONTINUE_ON_FAILURE)
//	}else if (expectedResult.equals(GlobalVariable.FAILURE) || 
//		expectedResult.equals(GlobalVariable.WRONG_EMAIL) || 
//		expectedResult.equals(GlobalVariable.WRONG_PASSWORD)) {
//		
//		//Verify 'Wrong Email and Password' message visibility based on expected result
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
//	}else if (expectedResult.equals(GlobalVariable.MULTIPLE_FAILURE) || 
//		expectedResult == GlobalVariable.INVALID_PASSWORD ||
//		expectedResult == GlobalVariable.LONG_EMAIL ||
//		expectedResult == GlobalVariable.LONG_PASSWORD ||
//		expectedResult == GlobalVariable.SHORT_PASSWORD ) {
//		
//		//Verify 'Wrong Email and Password' message is not visible based on expected result
//		WebUI.verifyElementNotVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'))
//	}else if (expectedResult.equals(GlobalVariable.EMPTY)) {
//		/**Verify 'No empty field!' message in based on expected result
//		 * The error message is shown in their respectively empty field
//		 * In EMPTY case, both email and password field is empty so need to error message for both
//		 **/
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyEmail'), FailureHandling.CONTINUE_ON_FAILURE)
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyPassword'), FailureHandling.CONTINUE_ON_FAILURE)
//	}else if (expectedResult.equals(GlobalVariable.EMPTY_EMAIL)) {
//		
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyEmail'), FailureHandling.CONTINUE_ON_FAILURE)
//	}else if (expectedResult.equals(GlobalVariable.EMPTY_PASSWORD)) {
//		
//		WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_EmptyPassword'), FailureHandling.CONTINUE_ON_FAILURE)
//	}
	
	
		
}


	
}
