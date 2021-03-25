package com.yfl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 
 * @author liumeng
 *
 * create at 2018年7月21日, assembly-common
 */
public abstract class FileDownloadUtils {

	private static Logger logger = LoggerFactory.getLogger(FileDownloadUtils.class);

	/**
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String fileNameToUtf8String(HttpServletRequest request, String fileName) {
		String agent = request.getHeader("User-Agent");
		try {
			boolean isFireFox = agent != null && agent.toLowerCase().contains("firefox");
			if (isFireFox) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			} else {
//				fileName = TextUtils.toUtf8String(fileName);
				if (agent != null && agent.contains("MSIE") && fileName.length() > 150) {
					// see http://support.microsoft.com/default.aspx?kbid=816868

					// 根据request的locale 得出可能的编码

				}
				fileName = URLEncoder.encode(fileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			logger.warn("fileNameToUtf8String faild, fileName is " + fileName, e);
		}
		return fileName;
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @param fileTrueName
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file,
			String fileTrueName) throws IOException {

		if (file.length() > 0) {
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=" + FileDownloadUtils.fileNameToUtf8String(request, fileTrueName));
			response.resetBuffer();
			response.setContentLength((int) file.length());
			byte[] buf = new byte[4096];
			int readLength;
			ServletOutputStream servletOS = response.getOutputStream();
			InputStream inStream = new FileInputStream(file);
			while ((readLength = inStream.read(buf)) != -1) {
				servletOS.write(buf, 0, readLength);
			}
			inStream.close();
			servletOS.flush();
			servletOS.close();
			response.flushBuffer();
		}
	}

}
