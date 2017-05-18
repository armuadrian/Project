package com.nokia.connect.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumActions {

	private Properties props;
	protected String keyToSendUser = "Administrator";
	protected String keyToSendPass = "guiadmin";

	public SeleniumActions() throws FileNotFoundException, IOException {
		props = new Properties();
		props.load(new FileInputStream("files/config.properties"));
	}

	public static WebDriver driver = null;

	public static LinkedList<OrderResponse> raspuns = new LinkedList<>();
	public static LinkedList<SearchOrder> dateRaspuns = new LinkedList<>();
	public static LinkedList<WorkflowObject> workflow = new LinkedList<>();
	private int primul;

	private void openWeb() throws FileNotFoundException, IOException {

		driver = WebDriverChrome.getWebDriver();

	}

	public void openpage(String link) throws FileNotFoundException, IOException {
		if (driver == null) {
			openWeb();
		}
		driver.get(link);
	}

	public void login(String nameId, String username, String passwordId, String password) {

		sendKey(nameId, username);
		sendKey(passwordId, password);
	}

	public WebElement findElement(String findElement) {

		final List<WebElement> iframes = driver.findElements(By.tagName("frameset"));
		final List<WebElement> wfcFrames = driver.findElements(By.tagName("iframe"));
		WebElement el = null;

		if (iframes.size() != 0) {
			for (WebElement iframe : iframes) {
				iframe.getSize();
				List<WebElement> frame = driver.findElements(By.tagName("frame"));
				for (WebElement frames : frame) {
					driver.switchTo().frame(frames);
					try {
						el = driver.findElement(By.id(findElement));
						if (el != null) {
							return el;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					try {
						el = driver.findElement(By.name(findElement));
						if (el != null) {
							return el;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					try {
						el = driver.findElement(By.xpath(findElement));
						if (el != null) {
							return el;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					try {
						el = driver.findElement(By.tagName(findElement));
						if (el != null) {
							return el;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					try {
						el = driver.findElement(By.cssSelector(findElement));
						if (el != null) {
							return el;
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					driver.switchTo().defaultContent();
				}
			}
		} else if (wfcFrames.size() != 0) {
			for (WebElement iframe : wfcFrames) {
				driver.switchTo().frame(iframe);
				try {
					el = driver.findElement(By.id(findElement));
					if (el != null) {
						return el;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				try {
					el = driver.findElement(By.name(findElement));
					if (el != null) {
						return el;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				try {
					el = driver.findElement(By.xpath(findElement));
					if (el != null) {
						return el;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				try {
					el = driver.findElement(By.tagName(findElement));
					if (el != null) {
						return el;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				try {
					el = driver.findElement(By.cssSelector(findElement));
					if (el != null) {
						return el;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				driver.switchTo().defaultContent();
			}
		} else {
			try {
				el = driver.findElement(By.id(findElement));
				if (el != null) {
					return el;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				el = driver.findElement(By.name(findElement));
				if (el != null) {
					return el;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				el = driver.findElement(By.xpath(findElement));
				if (el != null) {
					return el;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				el = driver.findElement(By.tagName(findElement));
				if (el != null) {
					return el;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				el = driver.findElement(By.cssSelector(findElement));
				if (el != null) {
					return el;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return null;
	}

	public void takeListFromOrderIL(String tableFromOrder) {

		WebElement fromOrderTable = findElement(tableFromOrder);
		List<WebElement> rows_table = fromOrderTable.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();
		System.out.println("rows-count: " + rows_count);
		for (int row = 0; row < rows_count; row++) {
			OrderResponse or = new OrderResponse();
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();

			if (columns_count == 0 || Columns_row.get(0).getText().isEmpty()) {
				continue;
			}

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				or.add(column, celtext);
			}
			if (columns_count != 0) {
				raspuns.add(or);
			}
		}
	}

	public void getOrderIdFromWorkflow(String tableWfcXpath) {

		WebElement tabel1 = findElement(tableWfcXpath);

		List<WebElement> rand = tabel1.findElements(By.tagName("tr"));

		int dimensiuneRand = rand.size();

		for (int i = 0; i < dimensiuneRand; i++) {

			WorkflowObject wfc = new WorkflowObject();
			List<WebElement> coloana = rand.get(i).findElements(By.tagName("td"));

			int dimensiuneColoana = coloana.size();
			if (dimensiuneColoana == 0) {
				continue;
			}

			for (int j = 0; j < dimensiuneColoana; j++) {
				String celltext = coloana.get(j).getText();
				wfc.add(i, j, celltext);
			}
			workflow.add(wfc);
		}
	}

	public void waitForAlert() {

		Alert alt = driver.switchTo().alert();
		alt.accept();

	}

	public void waitForStatusWfc(String status, String tableWfcXpath, String searchButton) throws InterruptedException {

		while (true) {
			getOrderIdFromWorkflow(tableWfcXpath);
			for (WorkflowObject wla : workflow) {
				if (wla.getStatus().contains(status)) {
					return;
				}
			}
			clearAndRetry(searchButton);
		}
	}

	public void waitForStatusIl(String status, String tableFromOrder, String refreshButton) throws InterruptedException {

		while (true) {
			takeListFromOrderIL(tableFromOrder);
			for (OrderResponse rsp : raspuns) {
				if (rsp.getName().endsWith(status)) {
					return;
				}
			}
			dateRaspuns.clear();
			clickButton(refreshButton);
		}
	}

	public void waitForActivateNGBCircuitStatusIl(WorkflowClient wfc, String tableFromOrder, String productId,
			String tableFromWfc, String wfcFirstNotifyStatus, String wfcSecondNotifyStatus)
			throws InterruptedException, FileNotFoundException, IOException {

		while (true) {
			takeListFromOrderIL(tableFromOrder);
			for (OrderResponse rsp : raspuns) {
				if (rsp.getName().equals("Activate NGB Circuit")) {
					if (rsp.getStatus().equals("Failed")) {
						wfc.wfcActions(productId, tableFromWfc, wfcFirstNotifyStatus, wfcSecondNotifyStatus);
						return;
					} else if (rsp.getStatus().equals("Completed")) {
						return;
					}
				}
			}
			raspuns.clear();
			driver.navigate().refresh();
		}
	}

	public void waitForCompletedOrderStatus(String status, String extServiceId, String tableWithOrders)
			throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(tableWithOrders);
			for (SearchOrder so : dateRaspuns) {
				if (so.getExtServiceId().equals(extServiceId)) {
					if (so.getTargetPortId().equals("")) {
						if (so.getOrderStatus().equals(status)) {
							System.out.println("Order Completed!");
							return;
						}
					}
				}
			}
			dateRaspuns.clear();
			driver.navigate().refresh();
		}
	}

	public void waitForActivities(String activity, String tableWithOrders, String searchButton)
			throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(tableWithOrders);
			for (SearchOrder so : dateRaspuns) {
				if (so.getActivities().equals(activity)) {
					return;
				}
			}
			clearAndRetry(searchButton);
		}
	}

	public void clearAndRetry(String searchButton) throws InterruptedException {

		// clear lists
		workflow.clear();
		dateRaspuns.clear();
		raspuns.clear();

		// sleep
		Thread.sleep(5000);

		// press search for refresh
		findElement(searchButton).click();
	}

	public void enterWfcOrder() {

		primul = workflow.getFirst().getRow();
		StringBuilder sb = new StringBuilder();
		sb.append("//*[@id=\"mainForm:workspace_working_area_view:actionTicketListingTable:").append(primul - 1)
				.append(":_idJsp60\"]");
		clickButton(sb.toString());
	}

	public void enterIlOrder() throws FileNotFoundException, IOException {

		String orderId = dateRaspuns.getLast().getInternalId();
		StringBuilder sb = new StringBuilder();
		sb.append(
				"http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/order_management_servlet/showOrderDetails?baseline=false&showDetails=DEFAULT&requestId=")
				.append(orderId);
		openpage(sb.toString());
	}

	public void enterIlCSOMOrder(String extServiceId, String tableFromOrder, String searchButton)
			throws FileNotFoundException, IOException, InterruptedException {

		String orderId = waitForCSOMOrderStatus(extServiceId, tableFromOrder, searchButton);
		StringBuilder sb = new StringBuilder();
		sb.append(
				"http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/order_management_servlet/showOrderDetails?baseline=false&showDetails=DEFAULT&requestId=")
				.append(orderId);
		openpage(sb.toString());
	}

	public String waitForCSOMOrderStatus(String activity, String tableWithOrders, String searchButton)
			throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(tableWithOrders);
			for (SearchOrder so : dateRaspuns) {
				if (so.getExtServiceId().equals(activity)) {
					if (!so.getTargetPortId().equals("")) {
						return so.getInternalId();
					}
				}
			}
			clearAndRetry(searchButton);
		}
	}

	public void exitOrder(String goBackToWorkItemsButton) {

		findElement(goBackToWorkItemsButton).click();
	}

	public void clickButton(String el) {
		findElement(el).click();
		driver.switchTo().defaultContent();
	}

	public void sendKey(String el, String keyToSend) {
		findElement(el).sendKeys(keyToSend);
		driver.switchTo().defaultContent();
	}

	public void doManualActivationNGBCircuitFallout() {

		clickButton("tab2href");
		clickButton("ACTION_MANUAL");
		driver.findElement(By.xpath("//*[@id=\"submitInnerFormButton\"]")).click();
		waitForAlert();
	}

	public void sendContinueManualActivation() {

		clickButton("tab2href");
		driver.findElement(By.xpath("//*[@id=\"submitInnerFormButton\"]")).click();
		waitForAlert();
	}

	public void getStatusOrderInstantLink(String tableWithOrders) {

		WebElement tabel1 = findElement(tableWithOrders);
		List<WebElement> rand = tabel1.findElements(By.tagName("tr"));
		int dimensiuneRand = rand.size();

		for (int i = 0; i < dimensiuneRand; i++) {
			SearchOrder searchOrder = new SearchOrder();
			List<WebElement> coloana = rand.get(i).findElements(By.tagName("td"));
			int dimensiuneColoana = coloana.size();
			if (dimensiuneColoana == 0 || coloana.get(0).getText().isEmpty()) {
				continue;
			}
			for (int j = 0; j < dimensiuneColoana; j++) {
				String celltext = coloana.get(j).getText();
				searchOrder.add(j, celltext);
			}
			if (dimensiuneColoana != 0) {
				dateRaspuns.add(searchOrder);
			}
		}
	}
}
