package com.zhongziyue.pan.service.business;


import com.zhongziyue.pan.domain.VirtualFile;
import com.zhongziyue.pan.infrastruction.utils.FileDownloadUtils;
import com.zhongziyue.pan.infrastruction.utils.UrlUtils;
import com.zhongziyue.pan.repository.mapper.VirtualFileMapper;
import com.zhongziyue.pan.service.dto.ActualFileDto;
import com.zhongziyue.pan.infrastruction.utils.SpringContextUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileByPathLoader {
    private transient VirtualFileMapper virtualFileMapper = SpringContextUtils.getBean(VirtualFileMapper.class);
    private Integer lid;
    private String path;
    private HttpServletResponse response;

    public FileByPathLoader(int lid, String path, HttpServletResponse response) {
        this.lid = lid;
        this.path = path;
        this.response = response;
    }

    public List<ActualFileDto> load() {
        List<ActualFileDto> actualFileDtos = new ArrayList<>();
        VirtualFile virtualFile = virtualFileMapper.selectById(lid);
        File file = new File(virtualFile.getRealPath(), path);
        if (file.isFile()) {
            try {
                FileDownloadUtils.downLoad(file.getPath(), response, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File[] subFiles = file.listFiles();
        if (subFiles == null) {
            return new ArrayList<>();
        }
        for (File subFile : subFiles) {
            if (!subFile.canRead()) {
                continue;
            }
            ActualFileDto actualFileDto = new ActualFileDto();
            if (subFile.isDirectory()) {
                actualFileDto.setTarget(UrlUtils.urlFormat(path + subFile.getName() + "/"));
                actualFileDto.setName(subFile.getName());
                actualFileDto.setLid(lid);
            } else {
                actualFileDto.setName(subFile.getName());
//                actualFileDto.setTarget("#");
                actualFileDto.setTarget(UrlUtils.urlFormat(path + subFile.getName()));
                actualFileDto.setLid(lid);
            }
            actualFileDtos.add(actualFileDto);
        }
        return actualFileDtos;
    }
}
