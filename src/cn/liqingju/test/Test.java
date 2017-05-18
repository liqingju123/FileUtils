package cn.liqingju.test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import cn.liqingju.ClientForXls;

public class Test {

	public static void main(String[] args) {
		ClientForXls clientForXls = new ClientForXls("/Users/imac/Downloads/邻家好医诊所系统.xlsx");
		clientForXls.readForXls();
		ConcurrentMap<String, Map<Integer, List<String>>> concurrMap = clientForXls.readData();
		for (Map<Integer, List<String>> map : concurrMap.values()) {
			for (List<String> listValue : map.values()) {
				for (String value : listValue) {
					System.out.println(value);
				}
			}
		}
	}

}
