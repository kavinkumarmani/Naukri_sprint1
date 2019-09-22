package com.naukri.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelwrite {

	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	String tech;
	public static Row row;
	int rownum = 1;

	public Excelwrite(String tech) throws Exception {
		// excelwrite
		file = new File(
				"C:\\Users\\Saran\\Desktop\\kavin test materials\\Naukri_final\\src\\main\\java\\com\\naukri\\Exceldatas\\jobdetails.xlsx");
		if (file.exists()) {
			workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
		} else {
			workbook = new XSSFWorkbook();
		}
		sheet = workbook.createSheet(tech);
		// excel header
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("JobTitle");
		row.createCell(1).setCellValue("CompanyName");
		row.createCell(2).setCellValue("Exeperience");
		row.createCell(3).setCellValue("location");
		row.createCell(4).setCellValue("PostedDate");
		row.createCell(5).setCellValue("ContactDetails");
	}

	public void excelwriteutil(String jobtitle, String companyname, String requiredexperience, String location,
			String posteddate, String contactinfo) throws Exception {

		row = sheet.createRow(rownum++);
		row.createCell(0).setCellValue(jobtitle);
		row.createCell(1).setCellValue(companyname);
		row.createCell(2).setCellValue(requiredexperience);
		row.createCell(3).setCellValue(location);
		row.createCell(4).setCellValue(posteddate);
		row.createCell(5).setCellValue(contactinfo);
	}

	public void write() throws Exception {
		fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.flush();
		workbook.close();
	}

	public static LocalDate datefunction(String data) {
		LocalDate date = LocalDate.now();
		String[] arr = data.split(" ");
		try {
			int i = Integer.parseInt(arr[0]);
			date = LocalDate.now().minusDays(i);
		} catch (Exception e) {
			if (arr[0] == "30+") {
				int j = 27;
				date = LocalDate.now().minusDays(j);
			}
		}
		return date;
	}
}
