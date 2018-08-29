package com.zhongziyue.pan.service.impl;

import com.zhongziyue.pan.domain.VirtualFile;
import com.zhongziyue.pan.infrastruction.utils.FileDownloadUtils;
import com.zhongziyue.pan.repository.mapper.VirtualFileMapper;
import com.zhongziyue.pan.service.VirtualFileService;
import com.zhongziyue.pan.service.business.FileByPathLoader;
import com.zhongziyue.pan.service.dto.ActualFileDto;
import com.zhongziyue.pan.service.dto.VirtualFileDto;
import com.zhongziyue.pan.service.dto.VirtualFileMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service("virtualFileService")
public class VirtualFileServiceImpl implements VirtualFileService {
    private final VirtualFileMapper virtualFileMapper;

    @Autowired
    public VirtualFileServiceImpl(VirtualFileMapper virtualFileMapper) {
        this.virtualFileMapper = virtualFileMapper;
    }

    @Override
    public List<VirtualFileMapperDto> findAllFiles() {
        List<VirtualFile> virtualFiles = virtualFileMapper.selectAll();
        return VirtualFileMapperDto.toDtos(virtualFiles);
    }

    @Override
    public List<VirtualFileDto> findAllFilesByPath(String path) {
        List<VirtualFile> virtualFiles = virtualFileMapper.selectByVirtualPath(path);
        return VirtualFileDto.toDtos(virtualFiles);
    }

    @Override
    public List<VirtualFileDto> findActualFilesByPath(Integer lid, String path) {
        FileByPathLoader fileByPathLoader = new FileByPathLoader(lid, path);
        List<ActualFileDto> actualFileDtos = fileByPathLoader.load();
        return actualFileDtos.stream().map(VirtualFileDto::new).collect(Collectors.toList());
    }

    @Override
    public void downloadActualFileByPath(Integer lid, String path, HttpServletResponse response, Boolean online) {
        VirtualFile virtualFile = virtualFileMapper.selectById(lid);
        File file = new File(virtualFile.getRealPath(), path);
        if (file.isFile()) {
            try {
                FileDownloadUtils.downLoad(file.getPath(), response, online);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}
