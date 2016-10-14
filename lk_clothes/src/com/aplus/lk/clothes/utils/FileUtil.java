package com.aplus.lk.clothes.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * 转换spring的MultipartFile 到file
	 * @user coding云
	 * 2014年6月23日
	 */
	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File tmpFile = File.createTempFile("tmpFile", null);
		multipart.transferTo(tmpFile);
		return tmpFile;
	}
	
	/**
	 * 获取文件扩展名
	 * @param file
	 * @return
	 */
	public static String getFormatName(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	/**
	 * 
	 * @Title: copyImage
	 * @Description: TODO
	 * @param imageUpload
	 * @param request
	 * @param uploadPath
	 * @param @throws IllegalStateException
	 * @param @throws IOException
	 * @return String
	 * @throws
	 */
	public static String copyImage(MultipartFile imageUpload,HttpServletRequest request, String uploadPath) throws IllegalStateException, IOException{
		if (imageUpload!=null&&!imageUpload.isEmpty()) { // 文件上传
			String realPath = request.getSession().getServletContext()
					.getRealPath(uploadPath);
			String saveFileName = "IMG_" + UUIDUtils.getUUID() + "." + FileUtil.getFormatName(imageUpload
							.getOriginalFilename());
			FileUtils.copyFile(FileUtil.multipartToFile(imageUpload),new File(realPath, saveFileName));
			return uploadPath +"/" + saveFileName;
		}
		return null;
	}
	
	public static boolean deleteFile(String filePath){
		if(StringUtils.isNotEmpty(filePath)){
			File deleteFile = new File(filePath);
			if(deleteFile.exists()){
				return deleteFile.delete();
			}
		}
		return false;
	}

}
