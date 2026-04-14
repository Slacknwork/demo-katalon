package generalUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestDataFactory
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
	
	//Open register form through in home page
	@Keyword
	static def openRegisterFormInLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/button_Login'))
		
		//Click register button in login form
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Register'))
	}
	
}
