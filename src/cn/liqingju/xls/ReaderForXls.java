package cn.liqingju.xls;

import java.util.List;
import java.util.Map;

public interface ReaderForXls<T> {

	public Map<Integer, List<T>> readXls(String pathName);

	public void outXls(Map<Integer, List<T>> result, String pathName);

}
