package com.zhongziyue.pan.domain;

public class VirtualFile {
    private int id;
    private String name;
    private String realPath;
    private String virtualPath;
    //List<Privilege> privileges;

    public VirtualFile() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VirtualFile setName(String name) {
        this.name = name;
        return this;
    }

    public VirtualFile setId(int id) {
        this.id = id;
        return this;
    }

    public String getRealPath() {
        return realPath;
    }

    public VirtualFile setRealPath(String realPath) {
        this.realPath = realPath;
        return this;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public VirtualFile setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
        return this;
    }
}
