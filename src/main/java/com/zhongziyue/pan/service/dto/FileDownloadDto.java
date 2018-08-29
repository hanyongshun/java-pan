package com.zhongziyue.pan.service.dto;

import com.zhongziyue.pan.infrastruction.utils.FileDownloadUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileDownloadDto {
    private String name;
    private byte[] data;
    private long size;

    public FileDownloadDto() {
    }

    public FileDownloadDto(File file) throws IOException {
        if (file != null) {
            this.name = FileDownloadUtils.fileNameFormat(file.getName());
            InputStream input = new FileInputStream(file);
            this.data = new byte[input.available()];
            this.size = input.read(this.data);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
