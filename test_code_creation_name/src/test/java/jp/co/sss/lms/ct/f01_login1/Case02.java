package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
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
		//テスト処理
		goTo("http://localhost:8080/lms/");
		assertEquals("ログイン | LMS", webDriver.getTitle());
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

	//ログインID	
	@FindBy(name = "loginId")
	private WebElement loginIdInput;
	//パスワード
	@FindBy(name = "password")
	private WebElement passwordInput;
	//ログインボタン
	@FindBy(css = ".btn.btn-primary")
	private WebElement loginButton;

	public void login(String id, String pw) {
		PageFactory.initElements(webDriver, this);

		clearAndSendKeys(loginIdInput, id);
		clearAndSendKeys(passwordInput, pw);
		clickLoginButton(loginButton);
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {

		PageFactory.initElements(WebDriverUtils.webDriver, this);
		login("StudentAA0111", "StudentAA0111");

		//テスト処理
		String errorMessage = webDriver.findElement(By.cssSelector(".help-inline.error")).getText();
		assertEquals("* ログインに失敗しました。", errorMessage);
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

}
