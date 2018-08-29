package com.zhongziyue.pan.web.controller;

import com.zhongziyue.pan.service.VirtualFileService;
import com.zhongziyue.pan.service.dto.VirtualFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class VirtualFileController {
    private final VirtualFileService virtualFileService;

    @Autowired
    public VirtualFileController(VirtualFileService virtualFileService) {
        this.virtualFileService = virtualFileService;
    }

    @RequestMapping(value = {"/virtual", "/"})
    public String fileListIndex(Model model) {
        List<VirtualFileDto> virtualFileDtos = virtualFileService.findAllFilesByPath("/");
        model.addAttribute("virtualFileUrl", "/virtual");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }

    @RequestMapping(value = "/virtual", params = {"lid", "path"})
    public String fileList(@RequestParam("lid") Integer lid, @RequestParam("path") String path, Model model) {
        if (StringUtils.isEmpty(path)) {
            path = "/";
        }
        List<VirtualFileDto> virtualFileDtos = virtualFileService.findActualFilesByPath(lid, path);
        model.addAttribute("virtualFileUrl", "/virtual");
        model.addAttribute("fileDownloadUrl", "/virtual/download");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }

    @RequestMapping(value = "/virtual/download", params = {"lid", "path"})
    public void fileDownload(@RequestParam("lid") Integer lid, @RequestParam("path") String path, Boolean online, HttpServletResponse response) {
        if (StringUtils.isEmpty(path)) {
            path = "/";
        }
        virtualFileService.downloadActualFileByPath(lid, path, response, online);
    }
}
