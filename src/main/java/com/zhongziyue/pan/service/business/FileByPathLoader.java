package com.zhongziyue.pan.service.business;


import com.zhongziyue.pan.domain.VirtualFile;
import com.zhongziyue.pan.infrastruction.utils.SpringContextUtils;
import com.zhongziyue.pan.infrastruction.utils.UrlUtils;
import com.zhongziyue.pan.repository.mapper.VirtualFileMapper;
import com.zhongziyue.pan.service.dto.ActualFileDto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileByPathLoader {
    private transient VirtualFileMapper virtualFileMapper = SpringContextUtils.getBean(VirtualFileMapper.class);
    private Integer lid;
    private String path;

    public FileByPathLoader(int lid, String path) {
        this.lid = lid;
        this.path = path;
    }

    public List<ActualFileDto> load() {
        List<ActualFileDto> actualFileDtos = new ArrayList<>();
        VirtualFile virtualFile = virtualFileMapper.selectById(lid);
        File file = new File(virtualFile.getRealPath(), path);
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
                actualFileDto.setFileJudge(false);
            } else {
                actualFileDto.setName(subFile.getName());
//                actualFileDto.setTarget("#");
                actualFileDto.setTarget(UrlUtils.urlFormat(path + subFile.getName()));
                actualFileDto.setLid(lid);
                actualFileDto.setFileJudge(true);
            }
            actualFileDtos.add(actualFileDto);
        }
        return actualFileDtos;
    }
}
