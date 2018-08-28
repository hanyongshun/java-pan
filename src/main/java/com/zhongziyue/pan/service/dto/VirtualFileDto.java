package com.zhongziyue.pan.service.dto;

import com.zhongziyue.pan.domain.VirtualFile;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualFileDto {
    private int id;
    private String name;
    private String virtualPath;
    private boolean fileJudge;

    public VirtualFileDto() {
    }

    public VirtualFileDto(VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.id = virtualFile.getId();
            this.name = virtualFile.getName();
            this.virtualPath = virtualFile.getVirtualPath();
        }
    }

    public VirtualFileDto(ActualFileDto actualFileDto) {
        if (actualFileDto != null) {
            this.id = actualFileDto.getLid();
            this.name = actualFileDto.getName();
            this.virtualPath = actualFileDto.getTarget();
            this.fileJudge = actualFileDto.getFileJudge();
        }
    }

    public static List<VirtualFileDto> toDtos(List<VirtualFile> virtualFiles) {
        return virtualFiles.stream().map(VirtualFileDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public VirtualFileDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VirtualFileDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public VirtualFileDto setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
        return this;
    }

    public boolean isFileJudge() {
        return fileJudge;
    }

    public void setFileJudge(boolean fileJudge) {
        this.fileJudge = fileJudge;
    }

    public String toString() {
        return String.format("{id=%d, name='%s', virtualPath='%s'}", id, name, virtualPath);
    }
}
