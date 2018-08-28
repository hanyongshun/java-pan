package com.zhongziyue.pan.service;

import com.zhongziyue.pan.service.dto.VirtualFileDto;
import com.zhongziyue.pan.service.dto.VirtualFileMapperDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface VirtualFileService {
    List<VirtualFileMapperDto> findAllFiles();

    List<VirtualFileDto> findAllFilesByPath(String path);

    List<VirtualFileDto> findActualFilesByPath(Integer lid, String path, HttpServletResponse response, Boolean online);
}
