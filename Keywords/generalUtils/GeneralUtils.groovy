package generalUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable




public class GeneralUtils {
	
	//Open login form in home page
	@Keyword
	static def openLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/button_Login'))
	}
	
	//Open register form by click login button in login form
	@Keyword
	static def openRegisterFormInLoginForm() {
		//Click avatar icon
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/icon_Avatar'))
		
		//Click login button in popup menu
		loginUtils.LoginElements.clickElement(findTestObject('ImgAvatarPopupMenu/button_Login'))
		
		//Click register button in login form
		loginUtils.LoginElements.clickElement(findTestObject('LoginFormElements/button_Register'))
	}
	
	//Verify elements is present
	@Keyword
	static def isElementPresent(def testObject) {
		//If test object is null, mark failed
		if (testObject == null) {
			KeywordUtil.markFailed("Test Object not found")
			return
		}
		
		//If test object is list then verify each element in list
		//If not then verify only single test object
		if (testObject instanceof List) {
			for (def object: testObject) {
				WebUI.verifyElementPresent(findTestObject(object), GlobalVariable.LONG_TIMEOUT)
			}
		} else {
			WebUI.verifyElementPresent(findTestObject(testObject), GlobalVariable.LONG_TIMEOUT)
		}
	}
	
	//Verify elements is not present
	@Keyword
	static def isElementNotPresent(def testObject) {
		//If test object is null, mark failed
		if (testObject == null) {
			KeywordUtil.markFailed("Test Object not found")
			return
		}
		
		//If test object is list then verify each element in list
		//If not then verify only single test object
		if (testObject instanceof List) {
			for (def object: testObject) {
				WebUI.verifyElementNotPresent(findTestObject(object), GlobalVariable.TIMEOUT)
			}
		} else {
			WebUI.verifyElementNotPresent(findTestObject(testObject), GlobalVariable.TIMEOUT)
		}
	}
	
	//Verify elements is visible
	@Keyword
	static def isElementVisible(def testObject) {
		//If test object is null, mark failed
		if (testObject == null) {
			KeywordUtil.markFailed("Test Object not found")
			return
		}
		
		//If test object is list then verify each element in list
		//If not then verify only single test object
		if (testObject instanceof List) {
			for (def object: testObject) {
				WebUI.verifyElementVisible(findTestObject(object), FailureHandling.STOP_ON_FAILURE)
			}
		} else {
			WebUI.verifyElementVisible(findTestObject(testObject), FailureHandling.STOP_ON_FAILURE)
		}
	}
	
	//Verify elements is not visible
	@Keyword
	static def isElementNotVisible(def testObject) {
		//If test object is null, mark failed 
		if (testObject == null) {
			KeywordUtil.markFailed("Test Object not found")
			return
		}
		
		//If test object is list then verify each element in list
		//If not then verify only single test object
		if (testObject instanceof List) {
			for (def object: testObject) {
				WebUI.verifyElementNotVisible(findTestObject(object), FailureHandling.STOP_ON_FAILURE)
			}
		} else {
			WebUI.verifyElementNotVisible(findTestObject(testObject), FailureHandling.STOP_ON_FAILURE)
		}
	}
	
	//get all elements file path in object repository folder, not include folder (if it in same folder)
	@Keyword
	static def getElementsFilePath(def filePath) {
		List<String> elementsList = new ArrayList<String>()
		new File(filePath).eachFile { file -> 
			if (file.getName().endsWith(".rs")) {
				String path = file.getPath().replace("\\", "/").replace("Object Repository/", "").replace(".rs", "")
				elementsList.add(path)
			}
		}
		return elementsList
	}
			
}
