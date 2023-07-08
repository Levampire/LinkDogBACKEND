package com.linkdog.service.impl;

import com.frameWork.dto.ProjectDto;
import com.linkdog.baseConst.LinkDogConst;
import com.linkdog.service.CommonService;
import com.linkdog.utils.FileDownUtils;
import com.linkdog.utils.JsonParseUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.List;

@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    JsonParseUtil jsonParseUtil;

    @Autowired
    FileDownUtils fileDownUtils;
    @Override
    public HttpServletResponse getFile(ProjectDto projectDto, HttpServletResponse response) {
        ProjectDto project =  jsonParseUtil.queryProjectResult(projectDto);
        if(null==project){
            throw new RuntimeException("未找到项目");
        }
        if(StringUtils.isEmpty(project.getResult())){
            throw new RuntimeException("此项目没有可下载的结果模型");
        }
        String downLoadPath = LinkDogConst.DOWNLOAD_RESULT_ROOTPATH+"\\"+project.getResultPath();
       return fileDownUtils.download(downLoadPath,response);
    }

    @Override
    public List<ProjectDto> queryProjectList(ProjectDto projectDto) {
        return jsonParseUtil.queryProjectList(projectDto);
    }

    private Path resolvePath(String fileName) {
        String path = "";
        return Path.of(path);
    }
}
