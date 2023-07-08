package com.linkdog.service;

import com.frameWork.dto.ProjectDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommonService {

    HttpServletResponse getFile(ProjectDto projectDto, HttpServletResponse response);

    List<ProjectDto> queryProjectList(ProjectDto projectDto);
}
