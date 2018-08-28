package com.zhongziyue.pan.web.controller;

import com.zhongziyue.pan.service.VirtualFileService;
import com.zhongziyue.pan.service.dto.ActualFileDto;
import com.zhongziyue.pan.service.dto.VirtualFileDto;
import com.zhongziyue.pan.service.dto.VirtualFileMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class VirtualFileController {
    private final VirtualFileService virtualFileService;

    @Autowired
    public VirtualFileController(VirtualFileService virtualFileService) {
        this.virtualFileService = virtualFileService;
    }

    @RequestMapping(value = "/virtual")
    public String fileListIndex(Model model) {
        List<VirtualFileDto> virtualFileDtos = virtualFileService.findAllFilesByPath("/");
        virtualFileDtos.forEach(virtualFileDto -> System.out.println(virtualFileDto.getName()));
        model.addAttribute("virtualFilePath", "/virtual");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }

    @RequestMapping(value = "/virtual", params = {"lid", "path"})
    public String fileList(@RequestParam("lid") Integer lid, @RequestParam("path") String path, Model model, HttpServletResponse response) {
        if (StringUtils.isEmpty(path)) {
            path = "/";
        }
//        List<VirtualFileMapperDto> virtualFileMapperDtos = virtualFileService.findAllFiles();
//        System.out.println(virtualFileMapperDtos.size());
//        virtualFileMapperDtos.forEach(virtualFileMapperDto -> System.out.println(virtualFileMapperDto.getRealPath()));
        List<VirtualFileDto> virtualFileDtos = virtualFileService.findActualFilesByPath(lid, path, response);
        virtualFileDtos.forEach(System.out::println);
        model.addAttribute("virtualFilePath", "/virtual");
        model.addAttribute("lists", virtualFileDtos);
        return "fileList";
    }
}
