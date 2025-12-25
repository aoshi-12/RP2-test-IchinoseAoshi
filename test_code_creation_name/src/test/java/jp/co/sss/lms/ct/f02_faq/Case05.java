package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
		goTo("http://localhost:8080/lms/");
		//テスト処理
		assertEquals("ログイン | LMS", webDriver.getTitle());
		//エビデンスの取得
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		WebElement loginId = webDriver.findElement(By.id("loginId"));
		loginId.clear();
		loginId.sendKeys("StudentAA01");
		WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Aa123456");
		WebElement loginButton = webDriver.findElement(By.cssSelector("input[type='submit'][value='ログイン']"));
		loginButton.click();
		//テスト処理
		assertEquals("コース詳細 | LMS", webDriver.getTitle());
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		WebElement menuToggle = webDriver.findElement(By.className("dropdown-toggle"));
		menuToggle.click();
		WebElement help = webDriver.findElement(By.linkText("ヘルプ"));
		help.click();
		//テスト処理
		assertEquals("ヘルプ | LMS", webDriver.getTitle());
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		String currentWindow = webDriver.getWindowHandle();
		WebElement faq = webDriver.findElement(By.linkText("よくある質問"));
		faq.click();

		for (String windowHandle : webDriver.getWindowHandles()) {
			if (!windowHandle.equals(currentWindow)) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}
		//テスト処理
		assertEquals("よくある質問 | LMS", webDriver.getTitle());
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		WebElement form = webDriver.findElement(By.id("form"));
		form.clear();
		form.sendKeys("研修");
		WebElement button = webDriver.findElement(By.cssSelector("input[type='submit']"));
		button.click();
		//テスト処理
		List<WebElement> results = webDriver.findElements(By.cssSelector("dt.mb10 > span:last-child"));
		for (WebElement result : results) {
			assertTrue(result.getText().contains("研修"));
		}
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		WebElement clear = webDriver.findElement(By.cssSelector("input[value='クリア']"));
		clear.click();

		//テスト処理
		WebElement form = webDriver.findElement(By.id("form"));
		assertEquals("", form.getText());
		//エビデンスの取得
		getEvidence(new Object() {
		});
	}

}
