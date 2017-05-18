package cn.liqingju.txt.imp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.liqingju.txt.FileOperate;
import cn.liqingju.utils.CloseableUtil;

public class FileOpereteImp implements FileOperate {
	StringBuilder sb;
	BufferedReader reader;

	public String readFile(String pathName) {
		sb = new StringBuilder();
		String data = null;
		try {
			reader = new BufferedReader(new FileReader(pathName));
			while ((data = reader.readLine()) != null) {
				sb.append(data);
				sb.append("\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	public void outFile(String pathName, String result, boolean type) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(pathName, type));
			out.append(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			CloseableUtil.closeAble(out);
		}

	}
}
