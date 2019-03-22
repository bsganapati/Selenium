package com.cleartrip.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 

/**
 * This class is related to Excel Reader
 * 
 * @author ganapati.bhat
 *
 */
public class ExcelReader {

	public static String excelFilePath = null;
	public static String sheetName = null;
	private static final Logger logger = Logger.getLogger(ExcelReader.class.getName());
/**
 * Method to get Data provide value
 * @param excelpath
 * @param sheetName1
 * @return
 * @throws IOException
 */
	public String[][] getDataProviderData(String excelpath, String sheetName1) throws IOException {
		excelFilePath = excelpath;
		sheetName = sheetName1;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName1);
		int totalRowCount = getTotalRowCount(firstSheet);

		int totalColCount = totalColumnCount(firstSheet, 1);

		String data[][] = new String[totalRowCount][totalColCount];

		for (int x = 1; x <= totalRowCount; x++) {

			Row row = firstSheet.getRow(x);

			for (int m = 0; m < totalColCount; m++) {
				Cell cell = row.getCell(m);
				try {

					CellType cellType = cell.getCellTypeEnum();

					switch (cellType) {
					case STRING:
						data[x - 1][m] = cell.getStringCellValue();
						break;

					case FORMULA:
						data[x - 1][m] = cell.getStringCellValue();
						break;
					default:
						break;

					}
				} catch (Exception e) {
					System.out.println("can not read cell data");
				}
			}

		}

		for (int x = 0; x < totalRowCount; x++) {

			for (int m = 0; m < totalColCount; m++) {
				System.out.println(data[x][m]);
			}

		}

		workbook.close();
		return data;

	}

	/**
	 * to get total row count which contains data
	 * 
	 * @param sheetTD
	 * @return
	 */
	private static int getTotalRowCount(XSSFSheet sheetTD) {
		int totalRowCount = sheetTD.getLastRowNum();
	
		boolean isRowEmpty = false;

		int r = totalRowCount;
		int totalloopCountRow = sheetTD.getRow(totalRowCount).getPhysicalNumberOfCells();
		do {

			for (int d = 1; d <= totalloopCountRow; d++) {
				try {

					if (sheetTD.getRow(r).getCell(d) == null || sheetTD.getRow(r).getCell(d).toString().equals("")) {

						isRowEmpty = true;

					} else {
						isRowEmpty = false;
					}

					if (d != 0 && isRowEmpty == false) {

						break;
					}

				} catch (NullPointerException e) {

				}

			}

			if (isRowEmpty == true) {
				r--;
			}
		} while (isRowEmpty == true);

		return r;
	}

	/**
	 * To get actual column count which contains data
	 * 
	 * @param rowNum
	 * @param sheetTD
	 */

	private static int totalColumnCount(XSSFSheet sheetTD, int tcRowNum) {

		boolean isColEmpty = false;
		int col = 0;
		do {

			try {

				if (sheetTD.getRow(tcRowNum).getCell(col) == null
						|| sheetTD.getRow(tcRowNum).getCell(col).toString().equals("")) {

					isColEmpty = true;

				} else {
					isColEmpty = false;
				}

			} catch (NullPointerException e) {

			}

			if (isColEmpty == false) {
				col++;
			}

			if (isColEmpty == true) {

				break;
			}

		} while (isColEmpty == false);
		return col;
	}
/**
 * Method to get excel first set of data
 * @param excelpath
 * @param sheetName1
 * @return
 * @throws IOException
 */
	public HashMap<String, String> getExcelFirstSetOfData(String excelpath, String sheetName1) throws IOException {
		HashMap<String, String> hmap = new HashMap<String, String>();
		excelFilePath = excelpath;
		sheetName = sheetName1;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName1);

		int totalColCount = totalColumnCount(firstSheet, 0);

		Row row = firstSheet.getRow(0);
		Row dataRow = firstSheet.getRow(1);

		for (int m = 0; m < totalColCount; m++) {
			Cell cell = row.getCell(m);
			Cell datacell = dataRow.getCell(m);
			try {

				CellType cellType = datacell.getCellTypeEnum();

				switch (cellType) {
				case STRING:
					hmap.put(cell.getStringCellValue(), datacell.getStringCellValue());

					break;

				case FORMULA:
					hmap.put(cell.getStringCellValue(), datacell.getStringCellValue());
					break;
				default:
					break;

				}
			} catch (Exception e) {
				System.out.println("can not read cell data");
			}
		}

	 
		workbook.close();
		return hmap;

	}
/**
 * Method related to getErrorValidationSetOfData
 * @param excelpath
 * @param sheetName1
 * @param columnName
 * @return
 * @throws IOException
 */
	public ArrayList<String> getErrorValidationSetOfData(String excelpath, String sheetName1, String columnName)
			throws IOException {
		ArrayList<String> aList = new ArrayList<String>();
		columnName = columnName.toLowerCase();
		excelFilePath = excelpath;
		sheetName = sheetName1;
		int columnNumber = 0;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName1);
		int totalRowCount = getTotalRowCount(firstSheet);

		int physicalcells = firstSheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i < physicalcells; i++) {
			if (firstSheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
				columnNumber = i;
				break;
			}
		}

		for (int r = 1; r <= totalRowCount; r++) {
			Row row = firstSheet.getRow(r);
			Cell cell = row.getCell(columnNumber);

			try {

				CellType cellType = cell.getCellTypeEnum();

				switch (cellType) {
				case STRING:
					aList.add(cell.getStringCellValue().trim());
					break;
				case NUMERIC:
					aList.add(String.valueOf(cell.getNumericCellValue()));
					break;
				case FORMULA:
					aList.add(cell.getStringCellValue().trim());
					break;
				default:
					break;

				}
			} catch (Exception e) {
				aList.add("");
				System.out.println("Cell data is empty");
			}
		}

		workbook.close();

		return aList;

	}
/**
 * Method related to getCommonData
 * @param parameters
 * @return
 * @throws IOException
 */
	public HashMap<String, String> getCommonData(String... parameters) throws IOException {
		HashMap<String, String> hmap = new HashMap<String, String>();
		excelFilePath = parameters[0];
		sheetName = parameters[1];

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName);

		int totalRowCount = getTotalRowCount(firstSheet);

		for (int k = 1; k <= totalRowCount; k++) {

			for (int i = 2; i < parameters.length; i++) {
				if (firstSheet.getRow(k).getCell(0).getStringCellValue().equalsIgnoreCase(parameters[i])) {
					hmap.put(parameters[i], firstSheet.getRow(k).getCell(1).getStringCellValue());

				}
			}

		}
		workbook.close();
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return hmap;

	}
/**
 * Method related to getFooterLinks
 * @param parameters
 * @return
 * @throws IOException
 */
	public HashMap<String, String> getFooterLinks(String... parameters) throws IOException {

		HashMap<String, String> hmap = new HashMap<String, String>();
		excelFilePath = parameters[0];
		sheetName = parameters[1];
		String urlRelatedTo = System.getProperty("urlrelatedto");

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName);

		int totalRowCount = getTotalRowCount(firstSheet);

		for (int k = 1; k <= totalRowCount; k++) {

			if (urlRelatedTo.equalsIgnoreCase("dev")) {
				hmap.put(firstSheet.getRow(k).getCell(0).getStringCellValue(),
						firstSheet.getRow(k).getCell(1).getStringCellValue());
			}
			if (urlRelatedTo.equalsIgnoreCase("prod")) {
				hmap.put(firstSheet.getRow(k).getCell(0).getStringCellValue(),
						firstSheet.getRow(k).getCell(2).getStringCellValue());
			}

		}
		workbook.close();
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return hmap;

	}
/**
 * Method related to getExcelData
 * @param excelpath
 * @param sheetname
 * @return
 * @throws IOException
 */
	public HashMap<String, String> getExcelData(String excelpath, String sheetname) throws IOException {

		HashMap<String, String> hmap = new HashMap<String, String>();
		excelFilePath = excelpath;
		sheetName = sheetname;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		System.out.println("Sheet name selected is  " + sheetName);

		int totalRowCount = getTotalRowCount(firstSheet);

		for (int k = 1; k <= totalRowCount; k++) {

			hmap.put(firstSheet.getRow(k).getCell(0).getStringCellValue(),
					firstSheet.getRow(k).getCell(1).getStringCellValue());

		}
		workbook.close();
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return hmap;

	}

	public void setCellValue(int totalColCount, XSSFSheet firstSheet, String value, int rownumber, String mapValue) {
		for (int i = 2; i <= totalColCount; i++) {
			String hdrname = firstSheet.getRow(0).getCell(i).getStringCellValue();

			if (hdrname.equalsIgnoreCase(value)) {
				firstSheet.getRow(rownumber).getCell(i).setCellValue(mapValue);

				break;
			}
		}
	}
/**
 * Method related to enterExcelData
 * @param excelpath
 * @param sheetname
 * @param hmap
 * @throws Exception
 */
	public void enterExcelData(String excelpath, String sheetname, HashMap<String, String> hmap) throws Exception {

		FileInputStream inputStream = new FileInputStream(new File(excelpath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet firstSheet = workbook.getSheet(sheetname);
		// System.out.println("Sheet name selected is "+sheetname);
		int totalRowCount = getTotalRowCount(firstSheet);
		int totalColCount = totalColumnCount(firstSheet, 1);

		int rownumber = 0;

		for (int i = 1; i <= totalRowCount; i++) {
			String tcname = firstSheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println("tcname " + tcname);
			if (tcname.equalsIgnoreCase(hmap.get("TestCaseName"))) {
				rownumber = i;
				break;
			}
		}

		System.out.println("rownumber " + rownumber);

		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			String keyvalue = entry.getKey();
			String mapValue = entry.getValue();
			String value;
			if (!keyvalue.equalsIgnoreCase("TestCaseName")) {
				switch (keyvalue) {
				case "ShippingSubToal":
					value = "ShippingSubToal";
					logger.info("Setting   ShippingSubToal column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);

					break;
				case "Webtax":
					value = "Webtax";
					logger.info("Setting   Webtax column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "ChargedTaxRate":
					value = "ChargedTaxRate";
					logger.info("Setting ChargedTaxRate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "OrderNumber":
					value = "OrderNumber";
					logger.info("Setting OrderNumber column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;

				case "ExpectedTaxRate":
					value = "ExpectedTaxRate";
					logger.info("Setting ExpectedTaxRate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "CalculatedAvalaraTax":
					value = "CalculatedAvalaraTax";
					logger.info("Setting CalculatedAvalaraTax column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "Date":
					value = "Date";
					logger.info("Setting Date column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "ExecutionStatus":
					value = "ExecutionStatus";
					logger.info("Setting ExecutionStatus column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "BillingTotal":
					value = "BillingTotal";
					logger.info("Setting BillingTotal column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;

				case "AvalaraTotalValue":
					value = "AvalaraTotalValue";
					logger.info("Setting AvalaraTotalValue column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;

				default:
					break;
				}
			}

		}
		FileOutputStream outputStream = new FileOutputStream(excelpath);
		inputStream.close();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}
/**
 * Method related to enterNetSuiteExcelData
 *  * @param excelpath
 * @param sheetname
 * @param hmap
 * @throws Exception
 */
	public void enterNetSuiteExcelData(String excelpath, String sheetname, HashMap<String, String> hmap)
			throws Exception {

		FileInputStream inputStream = new FileInputStream(new File(excelpath));

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet firstSheet = workbook.getSheet(sheetname);

		int totalRowCount = getTotalRowCount(firstSheet);
		int totalColCount = totalColumnCount(firstSheet, 0);
		System.out.println("totalRowCount " + totalRowCount);
		System.out.println("totalColCount " + totalColCount);
		int rownumber = 0;

		for (int i = 1; i <= totalRowCount; i++) {
			String tcname = firstSheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("tcname in excel is " + tcname);
			if (tcname.equalsIgnoreCase(hmap.get("TcName"))) {
				System.out.println("TestCaseName is " + tcname);
				rownumber = i;
				break;
			}
		}

		System.out.println("rownumber is " + rownumber);
		System.out.println("hmap.size() " + hmap.size());
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			String keyvalue = entry.getKey();
			System.out.println("keyvalue " + keyvalue);
			String mapValue = entry.getValue();
			String value;
			if (!keyvalue.equalsIgnoreCase("tcName")) {
				switch (keyvalue) {
				case "NetsuiteExecStatus":
					value = "NetsuiteExecStatus";
					logger.info("Setting   NetsuiteExecStatus column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);

					break;
				case "DemandWareExecStatus":
					value = "DemandWareExecStatus";
					logger.info("Setting   DemandWareExecStatus column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);

					break;
				case "ApplicationExecStatus":
					value = "ApplicationExecStatus";
					logger.info("Setting   ApplicationExecStatus column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "ExecutionDate":
					value = "ExecutionDate";
					logger.info("Setting ExecutionDate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "OrderNumber":
					value = "OrderNumber";
					logger.info("Setting OrderNumber column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;

				case "Orderdate":
					value = "Orderdate";
					logger.info("Setting Orderdate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "BackorderNumber":
					value = "BackorderNumber";
					logger.info("Setting BackorderNumber column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "Quantity":
					value = "Quantity";
					logger.info("Setting Quantity column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "Rate":
					value = "Rate";
					logger.info("Setting Rate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "Amount":
					value = "Amount";
					logger.info("Setting Amount column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;

				case "SummarySubTotal":
					value = "SummarySubTotal";
					logger.info("Setting SummarySubTotal column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryDiscount":
					value = "SummaryDiscount";
					logger.info("Setting SummaryDiscount column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryTax":
					value = "SummaryTax";
					logger.info("Setting SummaryTax column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryShippingCost":
					value = "SummaryShippingCost";
					logger.info("Setting SummaryShippingCost column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryHandlingCost":
					value = "SummaryHandlingCost";
					logger.info("Setting SummaryHandlingCost column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryGiftCertificate":
					value = "SummaryGiftCertificate";
					logger.info("Setting SummaryGiftCertificate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "SummaryTotal":
					value = "SummaryTotal";
					logger.info("Setting SummaryTotal column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "PaymentMethod":
					value = "PaymentMethod";
					logger.info("Setting PaymentMethod column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "PaymentCardNumber":
					value = "PaymentCardNumber";
					logger.info("Setting PaymentCardNumber column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "PaymentExpiryDate":
					value = "PaymentExpiryDate";
					logger.info("Setting PaymentExpiryDate column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "PaymentNameOnCard":
					value = "PaymentNameOnCard";
					logger.info("Setting PaymentNameOnCard column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "BillingAddress":
					value = "BillingAddress";
					logger.info("Setting BillingAddress column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				case "ShippingAddress":
					value = "ShippingAddress";
					logger.info("Setting ShippingAddress column with value " + mapValue);
					setCellValue(totalColCount, firstSheet, value, rownumber, mapValue);
					break;
				default:
					break;
				}
			}

		}
		FileOutputStream outputStream = new FileOutputStream(excelpath);
		inputStream.close();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

}