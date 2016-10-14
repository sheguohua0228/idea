package com.aplus.lk.clothes.utils;

import java.io.File;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GenQrcodeUtils {
	
	@SuppressWarnings("deprecation")
	public static String encode(String contents, int width, int height,
			String imgPath) {
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		codeWidth = Math.max(codeWidth, width);
//		contents = "1234567890123";
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.EAN_13, codeWidth, height, null);
			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 12位的条形码
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String GetValiCode(String code) {
		if (StringUtils.isBlank(code) || code.length() != 12) {
			return null;
		}

		int A = 0;
		A += code.charAt(1) - 48;
		A += code.charAt(3) - 48;
		A += code.charAt(5) - 48;
		A += code.charAt(7) - 48;
		A += code.charAt(9) - 48;
		A += code.charAt(11) - 48;

		A = A * 3;

		int B = 0;
		B += code.charAt(0) - 48;
		B += code.charAt(2) - 48;
		B += code.charAt(4) - 48;
		B += code.charAt(6) - 48;
		B += code.charAt(8) - 48;
		B += code.charAt(10) - 48;

		B = A + B;

		int D = (int) Math.ceil(B / 10D) * 10;

		return (D - B) + "";

	}
	   
}
