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

	public WebDriver driver;

	public LinkedList<OrderResponse> raspuns = new LinkedList<>();
	public LinkedList<SearchOrder> dateRaspuns = new LinkedList<>();
	public LinkedList<WorkflowObject> workflow = new LinkedList<>();
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
				// List<WebElement> frame =
				// driver.findElements(By.tagName("frame"));
				// for (WebElement frames : frame) {
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
				// }
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

	public void takeListFromOrderIL(String table1IlXpath) {

		WebElement myTable = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table[2]"));
		List<WebElement> rows_table = myTable.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();
		System.out.println("rows-count: " + rows_count);
		for (int row = 0; row < rows_count; row++) {
			OrderResponse or = new OrderResponse();
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			// System.out.println("Number of cells In Row "+row+" are
			// "+columns_count);
			if (columns_count == 0 || Columns_row.get(0).getText().isEmpty()) {
				continue;
			}

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				or.add(column, celtext);
				System.out.println(or.toString());
				// System.out.println("Cell Value Of row number "+row+" and
				// column number "+column+" Is "+celtext);
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
		// System.out.println("randuri: " + dimensiuneRand);

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
				// System.out.println("Cell Value Of row number " + i + " and
				// column number " + j + " Is " + celltext);
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
				// try {
				if (wla.getStatus().contains(status)) {
					return;
				}
				// } catch (Exception e) {
				// Thread.sleep(5000);
				// }
			}
			clearAndRetry(searchButton);
		}
	}

	public void waitForStatusIl(String status, String table1IlXpath, String refreshButton) throws InterruptedException {

		while (true) {
			takeListFromOrderIL(table1IlXpath);
			for (OrderResponse rsp : raspuns) {
				// try {
				if (rsp.getName().endsWith(status)) {
					return;
				}
				// } catch (Exception e) {
				// Thread.sleep(5000);
				// }
			}
			dateRaspuns.clear();
			clickButton(refreshButton);
		}
	}

	public void waitForActivateNGBCircuitStatusIl(WorkflowClient wfc, String table1IlXpath)
			throws InterruptedException, FileNotFoundException, IOException {

		while (true) {
			takeListFromOrderIL(table1IlXpath);
			for (OrderResponse rsp : raspuns) {
				if (rsp.getName().equals("Activate NGB Circuit")) {
					if (rsp.getStatus().equals("Failed")) {
						wfc.wfcActions();
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

	public void waitForCompletedOrderStatus(String status, String extServiceId, String table2IlXpath) throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(table2IlXpath);
			for (SearchOrder so : dateRaspuns) {
				// try {
				if (so.getExtServiceId().equals(extServiceId)) {
					if (so.getTargetPortId().equals("")) {
						if (so.getOrderStatus().equals(status)) {
							System.out.println("Order Completed!");
							return;
						}
					}
				}
				// } catch (Exception e) {
				// Thread.sleep(5000);
				// }
			}
			driver.navigate().refresh();
			// clearAndRetry(searchButton);
		}
	}

	public void waitForActivities(String activity, String table2IlXpath, String searchButton)
			throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(table2IlXpath);
			for (SearchOrder so : dateRaspuns) {
				// try {
				if (so.getActivities().equals(activity)) {
					return;
				}
				// } catch (Exception e) {
				// Thread.sleep(5000);
				// }
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

	public void enterIlCSOMOrder(String extServiceId, String table1IlXpath, String searchButton)
			throws FileNotFoundException, IOException, InterruptedException {

		String orderId = waitForCSOMOrderStatus(extServiceId, table1IlXpath, searchButton);
		StringBuilder sb = new StringBuilder();
		sb.append(
				"http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/order_management_servlet/showOrderDetails?baseline=false&showDetails=DEFAULT&requestId=")
				.append(orderId);
		openpage(sb.toString());
	}

	public String waitForCSOMOrderStatus(String activity, String table2IlXpath, String searchButton)
			throws InterruptedException {

		while (true) {
			getStatusOrderInstantLink(table2IlXpath);
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

	public void getStatusOrderInstantLink(String table2IlXpath) {

		WebElement tabel1 = findElement(table2IlXpath);
		List<WebElement> rand = tabel1.findElements(By.tagName("tr"));
		int dimensiuneRand = rand.size();
		// System.out.println("randuri: "+dimensiuneRand);

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
				// System.out.println("Cell Value Of row number "+i+" and column
				// number "+j+" Is "+celltext);
			}
			if (dimensiuneColoana != 0) {
				dateRaspuns.add(searchOrder);
			}
		}
	}
}
