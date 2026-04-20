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

import generalUtils.GeneralUtils
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
	if (loginRetry == null || loginRetry > 1) {
		for (int i = 0; i < loginRetry; i++) {
			loginUtils.LoginElements.login(email, password)
		}
	}else {
		loginUtils.LoginElements.login(email, password)	
	}
}

//Verify elements based on which form is chose
@Keyword
static def verifyFormElements(def form) {
	switch (form) {
		case GlobalVariable.LOGIN_FORM:
			List<String> loginFormElementsPath = generalUtils.GeneralUtils.getElementsFilePath("Object Repository\\LoginFormElements")
			generalUtils.GeneralUtils.isElementVisible(loginFormElementsPath)
			break
		case GlobalVariable.REGISTER_FORM:
			List<String> registerFormElementsPath = generalUtils.GeneralUtils.getElementsFilePath("Object Repository\\RegisterFormElements")
			generalUtils.GeneralUtils.isElementVisible(registerFormElementsPath)
			break
	}
		
}

//Verify message visibility based on expected result
@Keyword
static def verifyMessageOfLoginTest(def expectedResult) {
	switch (expectedResult) {
		case GlobalVariable.SUCCESS:
			//verify 'login successfully' message and user name visibility
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/SuccessMessage/message_LoginSuccessfully')
			generalUtils.GeneralUtils.isElementVisible('ImgAvatarPopupMenu/text_Username')
			break;
		case GlobalVariable.FAILURE:
		case GlobalVariable.WRONG_EMAIL:
		case GlobalVariable.WRONG_PASSWORD:
			//Verify 'Wrong Email and Password' message visibility
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword')
			break;
		case GlobalVariable.MULTIPLE_FAILURE:
		case GlobalVariable.INVALID_PASSWORD:
		case GlobalVariable.LONG_EMAIL:
		case GlobalVariable.LONG_PASSWORD:
		case GlobalVariable.SHORT_PASSWORD:
			//Verify 'Wrong Email and Password' message is not visible
			generalUtils.GeneralUtils.isElementNotVisble('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword')
			break;
		/**Verify 'No empty field!' message in based on expected result
		* The error message is shown in their respectively empty field
		* In EMPTY case, both email and password field is empty so need to error message for both**/
		case GlobalVariable.EMPTY:			
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/ErrorMessage/text_EmptyEmail')
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/ErrorMessage/text_EmptyPassword')
			break;
		case GlobalVariable.EMPTY_EMAIL:
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/ErrorMessage/text_EmptyEmail')
			break;
		case GlobalVariable.EMPTY_PASSWORD:
			generalUtils.GeneralUtils.isElementVisible('LoginFormElements/ErrorMessage/text_EmptyPassword')
			break;
		}	
		
}






//@Keyword
//static def executeVerifyLoginMessages(def email, def password, def loginRetry, def expectedResult) {
//	/**This function execute:
//	* Step 1: Navigate to home page
//	* Step 2: Open Login form
//	* Step 3: Fill email and password field
//	* Step 4: Click on login button
//	*/
//	loginUtils.LoginElements.executeLoginTest(email, password, loginRetry)
//
//	//Verify messages visibility of based on expected result
//	loginUtils.LoginElements.verifyMessageOfLoginTest(expectedResult)
//}

}
