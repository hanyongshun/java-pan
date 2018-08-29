package com.zhongziyue.pan.infrastruction.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class FileDownloadUtils {
    public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + fileNameFormat(f.getName()));
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileNameFormat(f.getName()));
        }
        response.setContentLengthLong(f.length());

        OutputStream outputStream;
        FileInputStream fileInputStream = new FileInputStream(f);
        BufferedInputStream bufferedInputStream;
        outputStream = response.getOutputStream();

        bufferedInputStream = new BufferedInputStream(new FileInputStream(f));
        byte[] b = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(b);
        outputStream.write(b);
        outputStream.flush();

        bufferedInputStream.close();
        outputStream.close();
        fileInputStream.close();
    }

    public static String fileNameFormat(String fileName) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20").replaceAll("%28", "\\(").replaceAll("%29", "\\)").replaceAll("%3B", ";").replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&").replaceAll("%2C", "\\,");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
