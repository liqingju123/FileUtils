package cn.liqingju;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.liqingju.utils.CloseableUtil;

public class ClientForXls {

	private Workbook workbook;
	private Sheet sheet;
	public int rowint = 0;
	public int Cell = 0;
	private FileOutputStream fOut;
	private String path;
	private FileInputStream fileIn;
	/**
	 * //第一层 sheetName 第二层 sheet 内数据 Integer 行数   List<String> 列的值
	 */
	private ConcurrentHashMap<String, Map<Integer, List<String>>> reultMap; 
	private CountDownLatch countDownLatch;

	public ClientForXls(String path) {
		this.path = path;
	}

	public void writeXls(String sheetName) {
		workbook = path.endsWith("xls") ? new HSSFWorkbook() : new XSSFWorkbook();
		sheet = workbook.createSheet(sheetName);
	}

	public void readForXls() {
		try {
			fileIn = new FileInputStream(path);
			workbook = path.endsWith("xls") ? new HSSFWorkbook(fileIn) : new XSSFWorkbook(fileIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ConcurrentHashMap<String, Map<Integer, List<String>>> readData() {
		reultMap = new ConcurrentHashMap<>();
		int allSheetNum = workbook.getNumberOfSheets();
		countDownLatch = new CountDownLatch(allSheetNum);
		for (int i = 0; i < allSheetNum; i++) {
			new ReadXlsThread(workbook.getSheetAt(i), countDownLatch, reultMap).start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return reultMap;
	}

	public void createLocal() {
		try {
			fOut = new FileOutputStream(path);
			workbook.write(fOut);
			fOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtil.closeAble(fOut);
		}
	}

	public void createRow(String[] strs) {
		Row row = sheet.createRow(rowint);
		for (int i = 0; i < strs.length; i++) {
			row.createCell(i).setCellValue(strs[i]);
		}
		rowint++;
	}

	public void createRow(List<String> list) {
		String[] strs = new String[list.size()];
		createRow(list.toArray(strs));
	}

}
