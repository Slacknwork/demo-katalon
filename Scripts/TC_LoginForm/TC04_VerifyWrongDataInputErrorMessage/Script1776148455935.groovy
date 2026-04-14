import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

/**This function execute:
 * Step 1: Navigate to home page
 * Step 2: Open Login form
 * Step 3: Fill email and password field
 * Step 4: Click on login button
 */
loginUtils.LoginElements.implementLoginTest(email, password)

//Verify 'Wrong Email and Password' message visibility based on expected result
if (expectedResult == GlobalVariable.FAILURE) {
	WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
}else if (expectedResult == GlobalVariable.WRONG_EMAIL) {
	WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
}else if (expectedResult == GlobalVariable.WRONG_PASSWORD) {
	WebUI.verifyElementVisible(findTestObject('LoginFormElements/ErrorMessage/text_WrongEmailAndPassword'), FailureHandling.CONTINUE_ON_FAILURE)
}



