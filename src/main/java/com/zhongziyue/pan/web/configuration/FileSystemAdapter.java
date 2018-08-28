package com.zhongziyue.pan.web.configuration;

import com.zhongziyue.pan.service.VirtualFileService;
import com.zhongziyue.pan.infrastruction.utils.SpringContextUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileSystemAdapter implements WebMvcConfigurer {

    private final VirtualFileService virtualFileService = SpringContextUtils.getBean(VirtualFileService.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        List<VirtualFileMapperDto> virtualFileMapperDtos = virtualFileService.findAllFiles();
//        for (VirtualFileMapperDto virtualFileMapperDto : virtualFileMapperDtos) {
//            registry.addResourceHandler("/vs/" + virtualFileMapperDto.getName() + virtualFileMapperDto.getVirtualPath() + "**").addResourceLocations("file:" + virtualFileMapperDto.getRealPath());
//        }
    }
}
