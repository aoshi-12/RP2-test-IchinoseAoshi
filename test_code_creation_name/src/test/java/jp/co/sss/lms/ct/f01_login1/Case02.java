package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		goTo("http://localhost:8080/lms/");

		PageFactory.initElements(WebDriverUtils.webDriver, this);
	}

	@FindBy(name = "loginId")
	private WebElement loginIdInput;

	@FindBy(name = "password")
	private WebElement passwordInput;

	@FindBy(css = ".btn.btn-primary")
	private WebElement loginButton;

	public void enterUsername(String loginId) {
		loginIdInput.clear();
		loginIdInput.sendKeys(loginId);
	}

	public void enterPassword(String password) {
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public void login(String loginId, String password) {
		PageFactory.initElements(WebDriverUtils.webDriver, this);

		enterUsername(loginId);
		enterPassword(password);
		clickLoginButton();
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// TODO ここに追加

		login("StudentAA0111", "StudentAA0111");
		getEvidence(this);
	}

}
