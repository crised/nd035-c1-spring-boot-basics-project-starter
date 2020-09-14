package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getFilesFromUser(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(Integer fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contenttype}, #{filesize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);

}
