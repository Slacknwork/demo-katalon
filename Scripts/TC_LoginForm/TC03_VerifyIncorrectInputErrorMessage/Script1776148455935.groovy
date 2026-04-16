import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

//This test case verify visibility of error message when input invalid datas

/**This function execute:
 * Step 1: Navigate to home page
 * Step 2: Open Login form
 * Step 3: Fill email and password field
 * Step 4: Click on login button
 */
loginUtils.LoginElements.executeLoginTest(email, password, GlobalVariable.LOGIN_ONCE)

//Verify 'Wrong Email and Password' message visibility of incorrect input
loginUtils.LoginElements.verifyMessageOfLoginTest(expectedResult)




