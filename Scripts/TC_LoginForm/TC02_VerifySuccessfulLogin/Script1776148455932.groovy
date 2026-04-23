import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

//This test case verify login is success when input is valid
/**This function execute:
 * Step 1: Navigate to home page
 * Step 2: Open Login form
 * Step 3: Fill email and password field
 * Step 4: Click on login button
 * Step 5: Verify successful login message
 */
loginUtils.LoginElements.executeLoginTest(email, password)

loginUtils.LoginElements.verifyMessageOfLoginTest(expectedResult)


