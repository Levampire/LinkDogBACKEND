package com.linkdog.service;

import com.frameWork.dto.ProjectDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public interface PythonRunner {
    @Async
    public void pythonRunner(String scriptPath, ProjectDto projectDto);
}
