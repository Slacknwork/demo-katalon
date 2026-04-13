import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**This function execute:
 * Step 1: Navigate to home page
 * Step 2: Open Login form
 */
generalUtils.GeneralUtils.openLoginForm()

//fill password field
WebUI.setEncryptedText(findTestObject('LoginFormElements/input_Password'), Password)

//verify marked password
WebUI.verifyElementAttributeValue(findTestObject('LoginFormElements/input_Password'), 'type', 'password', 1)

