package loginUtils
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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
 * Click element
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
static def fillLoginField(def email, def password) {
	//Wait for email field visibility and fill it
	TestObject emailTextbox = findTestObject('LoginFormElements/input_email')
	WebUI.waitForElementVisible(emailTextbox, GlobalVariable.LONG_TIMEOUT)
	WebUI.setText(emailTextbox, email)
	
	//Wait for password field visibility and fill it
	TestObject passwordTextbox = findTestObject('LoginFormElements/input_Password')
	WebUI.waitForElementVisible(passwordTextbox, GlobalVariable.LONG_TIMEOUT)
	WebUI.setEncryptedText(passwordTextbox, password)
 
}
	
//Navigate to home page and test login function in login form 
@Keyword
static def implementLoginTest(def email, def password) {
	//Step 1: Open login form
	generalUtils.GeneralUtils.openLoginForm()

	//Step 2: Fill email and password field
	loginUtils.LoginElements.fillLoginField(email, password)

	//Step 3: Wait for login button visibility and click it
	loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Login'))
}
	
	
}
