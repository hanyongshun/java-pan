package com.zhongziyue.pan.infrastruction.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {
    public static String urlFormat(String url) {
        try {
            url = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
