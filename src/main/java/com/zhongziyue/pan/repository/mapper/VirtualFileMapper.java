package com.zhongziyue.pan.repository.mapper;

import com.zhongziyue.pan.domain.VirtualFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface VirtualFileMapper {
    @Select("SELECT * FROM virtual_file;")
    List<VirtualFile> selectAll();

    @Select("SELECT * FROM virtual_file WHERE virtualPath=#{virtualPath}")
    List<VirtualFile> selectByVirtualPath(String virtualPath);

    @Select("SELECT * FROM virtual_file WHERE id=#{id}")
    VirtualFile selectById(Integer id);
}
