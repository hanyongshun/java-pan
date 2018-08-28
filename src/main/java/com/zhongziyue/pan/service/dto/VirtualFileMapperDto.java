package com.zhongziyue.pan.service.dto;

import com.zhongziyue.pan.domain.VirtualFile;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualFileMapperDto {
    private int id;
    private String name;
    private String realPath;
    private String virtualPath;

    public VirtualFileMapperDto() {
    }

    private VirtualFileMapperDto(VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.id = virtualFile.getId();
            this.name = virtualFile.getName();
            this.realPath = virtualFile.getRealPath();
            this.virtualPath = virtualFile.getVirtualPath();
        }
    }

    public static List<VirtualFileMapperDto> toDtos(List<VirtualFile> virtualFiles) {
        return virtualFiles.stream().map(VirtualFileMapperDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public VirtualFileMapperDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VirtualFileMapperDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getRealPath() {
        return realPath;
    }

    public VirtualFileMapperDto setRealPath(String realPath) {
        this.realPath = realPath;
        return this;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public VirtualFileMapperDto setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
        return this;
    }
}
