package cn.liqingju.utils;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtil {

	public static void closeAble(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
