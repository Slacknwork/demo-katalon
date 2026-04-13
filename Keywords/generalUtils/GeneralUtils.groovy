package generalUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

//General steps to use in page
public class GeneralUtils {
	
	//Open login form in home page
	@Keyword
	public static void openLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarMenu/button_Login'))
	}
	
	//Open register form through in home page
	@Keyword
	public static void openRegisterFormThroughLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarMenu/button_Login'))
		
		//Click register button in login form
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Register'))
	}
	
//	@Keyword
//	static def implementTestData(String field, String status){
//		String input = ""
//		def data = TestDataFactory.findTestData('Data Files/LoginData')
//		int rowCount = data.getRowNumbers()
//		
//		for (int i = 1; i <= rowCount; i++) {
//			if (data.getValue('Status', i).equals(status)) {
//				input = data.getValue(field, i)
//				break
//			}
//		}	
//		return input	
//	}
	
}
