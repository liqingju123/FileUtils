package cn.liqingju.txt;

public interface FileOperate {

	/**
	 * 
	 * 
	 * @param pathName
	 *            文件路径
	 * @return 文本内容
	 */
	public String readFile(String pathName);

	/**
	 * 
	 * 写入文件
	 * 
	 * @param pathName
	 *            文件路径
	 * @param result
	 *            写入内容
	 * @param type
	 *            类型 false 新建 true 如果有新的就追加
	 */
	public void outFile(String pathName, String result, boolean type);

}
