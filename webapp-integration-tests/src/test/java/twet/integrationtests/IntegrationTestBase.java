package twet.integrationtests;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.JarScanFilter;
import org.apache.tomcat.JarScanType;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;

public abstract class IntegrationTestBase {

	public final static String WEBAPP_BASE = "/webapp-implementation";
	public final static int SERVER_PORT = 7777;
	public final static int TIMEOUT = 30000;
	public final String baseUrl = "http://localhost:" + SERVER_PORT + WEBAPP_BASE + "/";

	static Tomcat tomcat;
	WebClient webClient;

	@BeforeClass
	public static void setupServer() throws Exception {
		// useful only for Spring applications; select an application profile
		System.setProperty("spring.profiles.active", "integrationtest");

		// tell Tomcat where to find the (deployed) application
		String webappDirLocation = "../webapp-implementation/target/webapp-implementation-0.0.3-SNAPSHOT";
		tomcat = new Tomcat();

		// give Tomcat a temporary space for its internal workings
		File temp = File.createTempFile("twet", ".tmp");
		temp.delete();
		temp.mkdir();
		tomcat.setBaseDir(temp.getAbsolutePath());
		tomcat.setPort(SERVER_PORT);

		// configure the web application
		Context context = tomcat.addWebapp(WEBAPP_BASE, new File(webappDirLocation).getAbsolutePath());

		// disable scanning of dependencies introduced by maven.
		// you might want to exclude taglib JARs, otherwise tomcat won't load their declarations
		StandardJarScanner discardingJarScanner = new StandardJarScanner();
		discardingJarScanner.setJarScanFilter(new JarScanFilter() {

			@Override
			public boolean check(JarScanType jarScanType, String jarName) {
				return jarName.contains("jstl-");
			}
		});
		context.setJarScanner(discardingJarScanner);

		// enable JNDI, e.g. for server-provided data sources
		tomcat.enableNaming();
		ContextResource resource = new ContextResource();
		resource.setName("jdbc/twetDataSource");
		resource.setAuth("Container");
		resource.setType(javax.sql.DataSource.class.getName());
		resource.setScope("Sharable");
		resource.setProperty("driverClassName", "org.hsqldb.jdbc.JDBCDriver");
		resource.setProperty("url", "jdbc:hsqldb:mem:testdb");
		context.getNamingResources().addResource(resource);
		tomcat.getServer().getGlobalNamingResources().addResource(resource);

		tomcat.start();
	}

	@Before
	public void setupBrowser() {
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(true);
		webClient.setCssErrorHandler(new SilentCssErrorHandler());
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
		webClient.getOptions().setThrowExceptionOnScriptError(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setAppletEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setPopupBlockerEnabled(true);
		webClient.getOptions().setTimeout(TIMEOUT);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.setAlertHandler(new AlertHandler() {

			public void handleAlert(Page page, String message) {
				System.err.println("[alert] " + message);
			}

		});
		webClient.waitForBackgroundJavaScript(TIMEOUT);
	}

	@AfterClass
	public static void afterAllTests() throws Exception {
		tomcat.stop();
		tomcat.destroy();
	}

	@After
	public void afterEachTest() throws Exception {
		webClient.close();
	}

}
