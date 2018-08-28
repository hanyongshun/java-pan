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
        model.addAttribute("virtualFilePath", "/virtual");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }

    @RequestMapping(value = "/virtual", params = {"lid", "path"})
    public String fileList(@RequestParam("lid") Integer lid, @RequestParam("path") String path, Boolean online, Model model, HttpServletResponse response) {
        if (StringUtils.isEmpty(path)) {
            path = "/";
        }
        List<VirtualFileDto> virtualFileDtos = virtualFileService.findActualFilesByPath(lid, path, response, online);
        model.addAttribute("virtualFilePath", "/virtual");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }
}
