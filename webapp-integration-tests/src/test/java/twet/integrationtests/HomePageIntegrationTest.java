package twet.integrationtests;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@Category(IntegrationTestBase.class)
public class HomePageIntegrationTest extends IntegrationTestBase{

	@Test
	public void test_homepage_is_there() throws Exception{
		HtmlPage page = webClient.getPage(baseUrl);
		assertTrue(page.asText().contains("TWET News"));
	}

	@Test
	public void test_homepage_correct_date_and_headlines() throws Exception{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String expectedDate = sdf.format(now);

		HtmlPage page = webClient.getPage(baseUrl);
		List<?> list = page.getByXPath("//div[@class='today']");
		assertEquals(1, list.size());

		HtmlDivision div = (HtmlDivision)list.get(0);
		assertTrue(div.asText().contains(expectedDate));

		list = page.getByXPath("//div[contains(@class,'headline')]/b");
		assertEquals("Twetland election polls", ((HtmlElement)list.get(0)).getTextContent());
	}

	@Test
	public void test_lookup_stock() throws Exception{
		HtmlPage page = webClient.getPage(baseUrl);
		HtmlForm form = page.getForms().get(0);
		HtmlInput symbolField = form.getInputByName("stocksymbol");
		symbolField.type("test");
		HtmlInput submitButton = form.getInputByValue("Lookup stock symbol");
		submitButton.click();
		List<?> list = page.getByXPath("//div[contains(@class,'stock')]//div[contains(@class,'name')]");
		HtmlDivision ss = (HtmlDivision)list.get(0);
		assertEquals("TestSoft",ss.getTextContent().trim());
	}
}
