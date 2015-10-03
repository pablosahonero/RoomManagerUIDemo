package org.roommanager.test.tablet.search;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.pages.tablet.settings.StatusPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class VerifySearchByRoomName extends TestBase {
	/** roomName: Name of room to be used */
	private String roomName = PropertiesReader.getRoomName();

	/**
	 * errorMessage: It contains the error message that is displayed if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the "
			+ "Room wasn't available";

	/** connection: Name of a new RegisterPage */
	RegisterPage connection;

	/*Status page*/
	StatusPage statusPage;

	/*HomePage*/
	HomePage homePage;

	/**
	 * This method creates a new connection if there is not
	 */
	@BeforeTest
	public void beforeTest() {

		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
					PropertiesReader.getExchangeUserName(),
					PropertiesReader.getExchangePassWord(),
					PropertiesReader.getExchangeHostName());
		}
		connection = new RegisterPage(driver);
		String url = PropertiesReader.getRoomManagerApi();

		statusPage = connection.enterServiceUrl(url)
				.enterCredentials()
				.clickSignInButton();
		homePage = statusPage
				.selectRoom(roomName)
				.clickStartButton();
	}

	/**
	 * This method performs the test case: Verify that an specific room is
	 * displayed on [Rooms List] schedule table using the [Room Name] filter.
	 */
	@Test
	public void verifySearchByName() {

		SearchPage search = homePage.clickSearchButton();

		search.clickAdvancedButton().enterRoomName(roomName);

		Assert.assertTrue(search.isRoomPresent(roomName), errorMessage);
	}
}
