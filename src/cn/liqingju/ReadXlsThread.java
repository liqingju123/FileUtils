package cn.liqingju;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadXlsThread extends Thread {
	private Sheet sheet;
	private CountDownLatch countDownLatch;
	private ConcurrentMap<String, Map<Integer, List<String>>> map;
	private List<String> list;
	private Map<Integer, List<String>> sheetMap;

	public ReadXlsThread(Sheet sheet, CountDownLatch counFtDownLatch,
			ConcurrentHashMap<String, Map<Integer, List<String>>> map) {
		this.countDownLatch = counFtDownLatch;
		this.sheet = sheet;
		this.map = map;
		sheetMap = new HashMap<>();
	}

	@Override
	public void run() {

		for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				list = new ArrayList<String>();
				for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					String value = null;
					switch (cell.getCellType()) {
					case 2:
						value = cell.getCellFormula();
						break;
					case 0:
						value = String.valueOf(cell.getNumericCellValue());
						break;
					case 1:
						value = cell.getStringCellValue();
						break;
					}
					list.add(value);
//					System.out.println(value);
				}
				sheetMap.put(rowIndex, list);
			}
		}
		
		map.put(sheet.getSheetName(), sheetMap);
		countDownLatch.countDown();

	}

}
