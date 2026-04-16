package generalUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


public class GeneralUtils {
	
	//Open login form in home page
	@Keyword
	static def openLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/button_Login'))
	}
	
	//Open register form by click login button in login form
	@Keyword
	static def openRegisterFormInLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/button_Login'))
		
		//Click register button in login form
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Register'))
	}
	
	//Verify elements is visible
	@Keyword
	static def isElementVisble(def to) {
		//If to is list then verify each element in list
		//If not then verify only to
		if (to instanceof List) {
			for (def object: to) {
				WebUI.verifyElementVisible(findTestObject(object))
			}
		} else {
			WebUI.verifyElementVisible(findTestObject(to))
		}
	}
	
	//Verify elements is not visible
	@Keyword
	static def isElementNotVisble(def to) {
		//If to is list then verify each element in list
		//If not then verify only to
		if (to instanceof List) {
			for (def object: to) {
				WebUI.verifyElementNotVisible(findTestObject(object))
			}
		} else {
			WebUI.verifyElementNotVisible(findTestObject(to))
		}
	}
	
	@Keyword
	static def getElementsFilePath(def filePath) {
		List<String> elementsList = new ArrayList<String>()
		new File(filePath).eachFile { file -> 
			if (file.getName().endsWith(".rs")) {
				String path = file.getPath().replace("\\", "/").replace("Object Repository/", "").replace(".rs", "")
				System.out.println(path)
				elementsList.add(path)
			}
		}
		return elementsList
	}
	
	//Verify elements in page based on forms
	@Keyword
	static def verifyElementsInForm(def form) {
		
		if (form.equals(GlobalVariable.LOGIN_FORM)){
			//verify elements of login form
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/text_LoginTitle'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/label_Email'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/input_email'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/label_Password'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/input_Password'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/button_Login'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/button_Register'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('LoginFormElements/icon_Close'), FailureHandling.CONTINUE_ON_FAILURE)
			
		} else if (form.equals(GlobalVariable.REGISTER_FORM)) {
			//verify elements of register form
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/text_RegisterTitle'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Name'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/textbox_Name'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Email'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/textbox_Email'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Password'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/textbox_Password'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Phonenumber'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/textbox_Phonenumber'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Birthday'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/datetimepicker_Birthday'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/label_Gender'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementPresent(findTestObject('RegisterFormElements/dropdownMenu_Gender'), GlobalVariable.LONG_TIMEOUT)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/button_Register'), FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.verifyElementVisible(findTestObject('RegisterFormElements/icon_Close'), FailureHandling.CONTINUE_ON_FAILURE)
		}
		
	}
	
	
	
}
